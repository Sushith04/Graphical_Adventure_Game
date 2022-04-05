package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Dungeon is a place where a player can explore and can be connected to at most
 * four other locations: one to the north, one to the east, one to the south, and one to the west.
 * In this dungeon locations "wrap" to the one on the other side of the grid.
 * This dungeon is generated at random following some set of constraints resulting in a
 * different network each time the game begins.
 */
public class Dungeon implements IDungeon {
  private final ILocation[][] dungeon;
  private final int rows;
  private final int columns;
  private final int interconnectivity;
  private final Boolean wrap;
  private final int treasureAndArrowPercentage;
  private final int noOfOtyughs;
  private final RandomNumberGenerator rand;
  private final List<List<Integer>> allEdges;
  private final List<List<Integer>> unnecessaryEdges;
  private final int[] set;
  private final Set<ILocation> movements;
  private final List<String> pDescription;
  private final IPlayer player;
  private final List<Integer> visited;
  private List<String> description;
  private ILocation start;
  private ILocation end;
  private int playerArrows;

  /**
   * Constructs a Dungeon object and initializes it to the x-coordinate, y-coordinate, wrapping,
   * interconnectivity, treasure and arrow percentages, otyugh count, name of the player.
   *
   * @param x                          is the number of rows
   * @param y                          is the number of columns
   * @param wrapping                   tells whether the dungeon locations "wrap" to the
   *                                   other side of the grid
   * @param interconnectivity          tells how many paths should be there from every cave in the
   *                                   dungeon to every other cave in the dungeon
   * @param treasureAndArrowPercentage is the treasure to be added to a specified percentage of
   *                                   caves
   * @param otyughCount                is the number of otyughs in the dungeon
   * @param name                       is the name of the player
   */
  public Dungeon(int x, int y, Boolean wrapping, int interconnectivity
          , int treasureAndArrowPercentage, int otyughCount, String name
          , RandomNumberGenerator rand) {
    if (x <= 3 || y <= 3 || wrapping == null || interconnectivity < 0
            || treasureAndArrowPercentage < 0 || treasureAndArrowPercentage > 100
            || otyughCount < 1 || name == null || name.equals("") || rand == null) {
      throw new IllegalArgumentException("Invalid Dungeon creation");
    }

    this.rows = x;
    this.columns = y;
    this.interconnectivity = interconnectivity;
    this.wrap = wrapping;
    this.treasureAndArrowPercentage = treasureAndArrowPercentage;
    this.noOfOtyughs = otyughCount;
    this.rand = rand;
    this.dungeon = new Location[rows][columns];
    this.allEdges = new ArrayList<>();
    this.unnecessaryEdges = new ArrayList<>();
    this.set = new int[rows * columns];
    this.movements = new HashSet<>();
    this.description = new ArrayList<>();
    this.pDescription = new ArrayList<>();
    this.playerArrows = 3;
    this.player = new Player(name);
    this.visited = new ArrayList<>();
    constructDungeon();
    addTreasure();
    addWeapon();
    setStartEnd();
    addOtyugh();
  }

  private void constructDungeon() {
    int count = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        ILocation location = new Location(count, i, j);
        dungeon[i][j] = location;
        set[count] = dungeon[i][j].getName();
        count++;
      }
    }
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (j + 1 < columns) {
          List<Integer> edge = new ArrayList<>();
          edge.add(dungeon[i][j].getName());
          edge.add(dungeon[i][j + 1].getName());
          allEdges.add(edge);
        }
        if (i + 1 < rows) {
          List<Integer> edge = new ArrayList<>();
          edge.add(dungeon[i][j].getName());
          edge.add(dungeon[i + 1][j].getName());
          allEdges.add(edge);
        }
      }
    }
    if (canWrap()) {
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < columns; j++) {
          if (columns == columns + j) {
            List<Integer> edge = new ArrayList<>();
            edge.add(dungeon[i][j].getName());
            edge.add(dungeon[i][j + columns - 1].getName());
            allEdges.add(edge);
          }
          if (rows == rows + i) {
            List<Integer> edge = new ArrayList<>();
            edge.add(dungeon[i][j].getName());
            edge.add(dungeon[i + rows - 1][j].getName());
            allEdges.add(edge);
          }
        }
      }
    }
    kruskal(allEdges);
    int interCount = Math.min(unnecessaryEdges.size(), interconnectivity);
    for (int i = 0; i < interCount; i++) {
      allEdges.add(unnecessaryEdges.get(i));
      ILocation location1 = getLocation(allEdges.get(i).get(0));
      ILocation location2 = getLocation(allEdges.get(i).get(1));
      assert location1 != null;
      assert location2 != null;
      linkLocations(location1, location2);
    }
  }

  /**
   * A helper method for using kruskal's algorithm in the process of creating the dungeon.
   */
  private void kruskal(List<List<Integer>> edgesPassed) {
    if (edgesPassed == null) {
      throw new IllegalArgumentException("Edges passed are null");
    }
    while (edgesPassed.size() > 0) {
      int ran = rand.generateRandomNumber(0, edgesPassed.size() - 1);
      List<Integer> edge = edgesPassed.get(ran);
      if (find(edge.get(0)) != find(edge.get(1))) {
        union(edge.get(0), edge.get(1));
        ILocation location1 = getLocation(edge.get(0));
        ILocation location2 = getLocation(edge.get(1));
        assert location1 != null;
        assert location2 != null;
        linkLocations(location1, location2);
      } else {
        unnecessaryEdges.add(edge);
      }
      edgesPassed.remove(edge);
    }
  }

  private int find(int ver) {
    if (set[ver] != ver) {
      set[ver] = find(set[ver]);
    }
    return set[ver];
  }

  private void union(int x, int y) {
    int xRoot = find(x);
    int yRoot = find(y);
    if (xRoot == yRoot) {
      return;
    }
    set[yRoot] = xRoot;
  }

  private ILocation getLocation(Integer name) {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (name == dungeon[i][j].getName()) {
          return dungeon[i][j];
        }
      }
    }
    return null;
  }

  /**
   * A helper method to link two locations in the dungeon.
   */
  private void linkLocations(ILocation location1, ILocation location2) {
    if (location1 == null || location2 == null) {
      throw new IllegalArgumentException("Locations are null");
    }
    if (location1.getX() + dungeon[0].length == location2.getX() + 1) {
      ((Location) location1).linkNeighbours(Direction.NORTH, location2);
      ((Location) location2).linkNeighbours(Direction.SOUTH, location1);
    }
    if (location1.getY() + dungeon.length == location2.getY() + 1) {
      ((Location) location1).linkNeighbours(Direction.WEST, location2);
      ((Location) location2).linkNeighbours(Direction.EAST, location1);
    }
    if (location1.getX() == location2.getX() - 1) {
      ((Location) location1).linkNeighbours(Direction.SOUTH, location2);
      ((Location) location2).linkNeighbours(Direction.NORTH, location1);
    }
    if (location1.getY() == location2.getY() - 1) {
      ((Location) location1).linkNeighbours(Direction.EAST, location2);
      ((Location) location2).linkNeighbours(Direction.WEST, location1);
    }
  }

  private Boolean canWrap() {
    return wrap;
  }

  private void addTreasure() {
    List<ILocation> caves = new ArrayList<>();
    int cavesCount = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        dungeon[i][j].typeOfLocation();
        if (dungeon[i][j].typeOfLocation() == LocationType.CAVE) {
          caves.add(dungeon[i][j]);
          cavesCount = (int) Math.round(caves.size() * 0.01 * treasureAndArrowPercentage);
        }
      }
    }
    while (cavesCount > 0) {
      int iRand = rand.generateRandomNumber(0, rows - 1);
      int jRand = rand.generateRandomNumber(0, columns - 1);
      if (dungeon[iRand][jRand].typeOfLocation() == LocationType.CAVE
              && dungeon[iRand][jRand].getTreasure().isEmpty()) {
        int ran = rand.generateRandomNumber(0, 5);
        while (ran > 0) {
          ((Location) dungeon[iRand][jRand]).setTreasure(Treasure.RUBIES);
          ran--;
        }
        int ran1 = rand.generateRandomNumber(0, 5);
        while (ran1 > 0) {
          ((Location) dungeon[iRand][jRand]).setTreasure(Treasure.DIAMONDS);
          ran1--;
        }
        int ran2 = rand.generateRandomNumber(0, 5);
        while (ran2 > 0) {
          ((Location) dungeon[iRand][jRand]).setTreasure(Treasure.SAPPHIRES);
          ran2--;
        }
        cavesCount--;
      }
    }
  }

  private void setStartEnd() {
    int startX = rand.generateRandomNumber(0, rows - 1);
    int startY = rand.generateRandomNumber(0, columns - 1);
    int endX = rand.generateRandomNumber(0, rows - 1);
    int endY = rand.generateRandomNumber(0, columns - 1);
    start = dungeon[startX][startY];
    end = dungeon[endX][endY];
    if (start.typeOfLocation() == LocationType.CAVE
            && end.typeOfLocation() == LocationType.CAVE) {
      int distance = Math.abs(startX - endX) + Math.abs(startY - endY);
      if (start == end || distance < 5) {
        setStartEnd();
      }
      ((Player) player).setCurrentLocation(start);
      visited.add(player.currentLocation().getName());
    } else {
      setStartEnd();
    }
  }

  private void addWeapon() {
    List<ILocation> locations = new ArrayList<>();
    int locationsCount = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        locations.add(dungeon[i][j]);
        locationsCount = (int) Math.round(locations.size() * 0.01 * treasureAndArrowPercentage);
      }
    }
    while (locationsCount > 0) {
      int iRand = rand.generateRandomNumber(0, rows - 1);
      int jRand = rand.generateRandomNumber(0, columns - 1);
      if (dungeon[iRand][jRand].getWeapon() == 0) {
        int ran = rand.generateRandomNumber(1, 3);
        while (ran > 0) {
          ((Location) dungeon[iRand][jRand]).setWeapon();
          ran--;
        }
        locationsCount--;
      }
    }
  }

  private void addOtyugh() {
    List<ILocation> caves = new ArrayList<>();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (dungeon[i][j].typeOfLocation() == LocationType.CAVE) {
          caves.add(dungeon[i][j]);
        }
      }
    }
    int otyughCount = Math.min(caves.size() - 1, noOfOtyughs);
    int xCord = end.getX();
    int yCord = end.getY();
    ((Location) dungeon[xCord][yCord]).setOtyugh();
    if (otyughCount != 1) {
      otyughCount--;
      while (otyughCount > 0) {
        int iRand = rand.generateRandomNumber(0, rows - 1);
        int jRand = rand.generateRandomNumber(0, columns - 1);
        if (dungeon[iRand][jRand].typeOfLocation() == LocationType.CAVE
                && dungeon[iRand][jRand].otyughCount() == 0
                && dungeon[iRand][jRand] != start) {
          ((Location) dungeon[iRand][jRand]).setOtyugh();
          otyughCount--;
        }
      }
    }
  }

  @Override
  public String beginGame() {
    description = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    pDescription.add("Player name: " + player.getName());
    sb.append("Player name: ").append(player.getName()).append(".\n");
    sb.append(player.currentLocation()).append("\n");
    if (!(player.currentLocation().getTreasure().isEmpty())) {
      description.add(arrangeTreasure());
      sb.append(arrangeTreasure()).append("\n");
    }
    if (player.currentLocation().getWeapon() > 0) {
      description.add("You find " + player.currentLocation().getWeapon() + " arrows here.");
      sb.append("You find ").append(player.currentLocation().getWeapon()).append(" arrows here.\n");
    }
    if (checkSmell(player.currentLocation()) == Smell.MORE_PUNGENT) {
      description.add("You smell something terrible nearby.");
      sb.append("You smell something terrible nearby.\n");
    } else if (checkSmell(player.currentLocation()) == Smell.LESS_PUNGENT) {
      description.add("You smell something mild nearby.");
      sb.append("You smell something mild nearby.\n");
    }
    return sb.toString();
  }

  private String arrangeTreasure() {
    StringBuilder sb = new StringBuilder();
    int rCount = Collections.frequency(player.currentLocation().getTreasure()
            , Treasure.RUBIES);
    int sCount = Collections.frequency(player.currentLocation().getTreasure()
            , Treasure.SAPPHIRES);
    int dCount = Collections.frequency(player.currentLocation().getTreasure()
            , Treasure.DIAMONDS);
    if (rCount == 0) {
      return sb.append("You find -> Sapphires: ").append(sCount).append(", Diamonds: ")
              .append(dCount).toString();
    } else if (sCount == 0) {
      return sb.append("You find -> Rubies: ").append(rCount).append(", Diamonds: ")
              .append(dCount).toString();
    } else if (dCount == 0) {
      return sb.append("You find -> Rubies: ").append(rCount).append(", Sapphires: ")
              .append(sCount).toString();
    } else {
      return sb.append("You find -> Rubies: ").append(rCount).append(", Sapphires: ")
              .append(sCount).append(", Diamonds: ").append(dCount).toString();
    }
  }

  @Override
  public String move(Direction direction) {
    if (direction == null) {
      throw new IllegalArgumentException("Direction is null");
    }
    StringBuilder sb = new StringBuilder();
    if (!isGameOver()) {
      description = new ArrayList<>();
      if (player.currentLocation().getMap().containsKey(direction)) {
        if (player.currentLocation().getMap().get(direction).otyughCount() == 1) {
          if (player.currentLocation().getMap().get(direction).getOtyughHealth() == 100) {
            description.add("Chomp, chomp, you are eaten by an Otyugh!\nBetter luck next time.");
            sb.append("Chomp, chomp, you are eaten by an Otyugh!\nBetter luck next time.");
            ((Player) player).setCurrentLocation(player.currentLocation().getMap().get(direction));
            visited.add(player.currentLocation().getName());
            ((Player) player).setHealth();
            isGameOver();
            allEdges.clear();
            unnecessaryEdges.clear();
          } else if (player.currentLocation().getMap().get(direction).getOtyughHealth() == 50) {
            int alive = rand.generateRandomNumber(0, 1);
            if (alive == 0) {
              description.add("Chomp, chomp, you are eaten by an Otyugh!\nBetter luck next time.");
              sb.append("Chomp, chomp, you are eaten by an Otyugh!\nBetter luck next time.");
              ((Player) player).setCurrentLocation(player.currentLocation().getMap()
                      .get(direction));
              visited.add(player.currentLocation().getName());
              ((Player) player).setHealth();
              isGameOver();
              allEdges.clear();
              unnecessaryEdges.clear();
            } else if (alive == 1) {
              if (player.currentLocation() != end) {
                sb.append(endPossibilityHelper(direction));
              } else if (player.currentLocation() == end) {
                description.add("Game Won!!!, player has reached the end.\n" + player
                        .getInventory());
                sb.append("Game Won!!!, player has reached the end.\n").append(player
                        .getInventory());
                pDescription.add(player.getInventory());
                ((Player) player).setCurrentLocation(player.currentLocation().getMap()
                        .get(direction));
                visited.add(player.currentLocation().getName());
                ((Player) player).setHealth();
                isGameOver();
                allEdges.clear();
                unnecessaryEdges.clear();
              }
            }
          }
        } else {
          sb.append(endPossibilityHelper(direction));
        }
      } else {
        throw new IllegalArgumentException("There is no path to " + direction
                + " from this location.");
      }
    }
    return sb.toString();
  }

  private String endPossibilityHelper(Direction direction) {
    if (direction == null) {
      throw new IllegalArgumentException("Direction is null");
    }
    StringBuilder sb = new StringBuilder();
    if (movements.size() == 0) {
      movements.add(player.currentLocation());
      ILocation nextLocation = dungeon[player.currentLocation().getMap().get(direction).getX()]
              [player.currentLocation().getMap().get(direction).getY()];
      ((Player) player).setCurrentLocation(nextLocation);
      visited.add(player.currentLocation().getName());
      sb.append(playerMovement(direction));
      if (checkSmell(player.currentLocation()) == Smell.MORE_PUNGENT) {
        description.add("You smell something terrible nearby.");
        sb.append("You smell something terrible nearby.\n");
      } else if (checkSmell(player.currentLocation()) == Smell.LESS_PUNGENT) {
        description.add("You smell something mild nearby.");
        sb.append("You smell something mild nearby.\n");
      }
    } else {
      ILocation nextLocation = dungeon[player.currentLocation().getMap().get(direction).getX()]
              [player.currentLocation().getMap().get(direction).getY()];
      ((Player) player).setCurrentLocation(nextLocation);
      visited.add(player.currentLocation().getName());
      sb.append(playerMovement(direction));
      if (player.currentLocation() == end) {
        description.add("Game Won!!!, player has reached the end.\n" + player.getInventory());
        sb.append("Game Won!!!, player has reached the end.\n").append(player.getInventory());
        ((Player) player).setHealth();
        isGameOver();
        allEdges.clear();
        unnecessaryEdges.clear();
      } else {
        if (checkSmell(player.currentLocation()) == Smell.MORE_PUNGENT) {
          description.add("You smell something terrible nearby.");
          sb.append("You smell something terrible nearby.\n");
        } else if (checkSmell(player.currentLocation()) == Smell.LESS_PUNGENT) {
          description.add("You smell something mild nearby.");
          sb.append("You smell something mild nearby.\n");
        }
      }
    }
    return sb.toString();
  }

  private String playerMovement(Direction d) {
    if (d == null) {
      throw new IllegalArgumentException("Direction is null");
    }
    StringBuilder sb = new StringBuilder();
    sb.append(player.currentLocation()).append("\n");
    if (!(player.currentLocation().getTreasure().isEmpty())) {
      sb.append(arrangeTreasure()).append("\n");
    }
    if (player.currentLocation().getWeapon() > 0) {
      sb.append("You find ").append(player.currentLocation().getWeapon()).append(" arrows here.\n");
    }
    movements.add(player.currentLocation());
    return sb.toString();
  }

  @Override
  public ILocation getStart() {
    return copying(start);
  }

  @Override
  public ILocation getEnd() {
    return copying(end);
  }

  @Override
  public ILocation getDungeon(Integer name) {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (name == dungeon[i][j].getName()) {
          return dungeon[i][j];
        }
      }
    }
    return null;
  }

  @Override
  public List<Integer> visitedLocations() {
    return new ArrayList<>(visited);
  }

  @Override
  public ILocation getPlayerLocation() {
    return copying(player.currentLocation());
  }

  private ILocation copying(ILocation l) {
    if (l == null) {
      throw new IllegalArgumentException("Location is null");
    }
    ILocation deepCopy = new Location(l.getName(),
            l.getX(), l.getY());
    for (Map.Entry<Direction, ILocation> s : l.getMap().entrySet()) {
      ((Location) deepCopy).linkNeighbours(s.getKey(), s.getValue());
    }
    for (Treasure t : l.getTreasure()) {
      ((Location) deepCopy).setTreasure(t);
    }
    if (l == end) {
      ((Location) deepCopy).setOtyugh();
    }
    if (l == player.currentLocation()) {
      ((Location) deepCopy).setWeapon();
    }
    return deepCopy;
  }

  @Override
  public boolean isGameOver() {
    return player.getHealth() == 0;
  }

  @Override
  public String pickTreasure() {
    description = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    if (isGameOver()) {
      description.add("The game is already over.");
      sb.append("The game is already over.");
      throw new IllegalStateException("The game is already over.");
    }
    if (!(player.currentLocation().getTreasure().isEmpty())) {
      int rCount = Collections.frequency(player.currentLocation().getTreasure()
              , Treasure.RUBIES);
      int sCount = Collections.frequency(player.currentLocation().getTreasure()
              , Treasure.SAPPHIRES);
      int dCount = Collections.frequency(player.currentLocation().getTreasure()
              , Treasure.DIAMONDS);
      if (rCount == 0) {
        sb.append("You pick up -> Sapphires: ").append(sCount).append(", Diamonds: ")
                .append(dCount).append("\n");
      } else if (sCount == 0) {
        sb.append("You pick up -> Rubies: ").append(rCount).append(", Diamonds: ")
                .append(dCount).append("\n");
      } else if (dCount == 0) {
        sb.append("You pick up -> Rubies: ").append(rCount).append(", Sapphires: ")
                .append(sCount).append("\n");
      } else {
        sb.append("You pick up -> Rubies: ").append(rCount).append(", Sapphires: ")
                .append(sCount).append(", Diamonds: ").append(dCount).append("\n");
      }
      ((Player) player).setInventory(rCount, sCount, dCount);
      description.add(player.getInventory());
      sb.append(player.getInventory()).append("\n");
    } else if (player.currentLocation().getTreasure().isEmpty()) {
      description.add("There is no treasure in this location.");
      sb.append("There is no treasure in this location.\n");
      throw new IllegalStateException("There should be at least one treasure to be picked up.");
    }
    ((Location) player.currentLocation()).removeTreasure();
    return sb.toString();
  }

  @Override
  public String pickWeapon(int noOfArrows) {
    description = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    if (isGameOver()) {
      description.add("The game is already over.");
      sb.append("The game is already over.");
      throw new IllegalStateException("The game is already over.");
    } else if (player.currentLocation().getWeapon() == 0) {
      throw new IllegalStateException("There are no arrows here.");
    } else if (noOfArrows == 0) {
      description.add("There are no arrows in this location.");
      sb.append("There are no arrows in this location.\n");
    } else if (noOfArrows > player.currentLocation().getWeapon()) {
      noOfArrows = Math.min(noOfArrows, player.currentLocation().getWeapon());
      playerArrows += noOfArrows;
      ((Player) player).setArrows(playerArrows);
      description.add("You have " + player.currentLocation().getWeapon()
              + " arrows only at this location.\n" + noOfArrows + " arrows have been picked up.\n"
              + "You have " + player.getArrows() + " arrows with you now.");
      sb.append("You have ").append(player.currentLocation().getWeapon())
              .append(" arrows only at this location.\n").append(noOfArrows)
              .append(" arrows have been picked up.\nYou have ")
              .append(player.getArrows()).append(" arrows with you now.\n");
      ((Location) player.currentLocation()).removeWeapon(noOfArrows);
    } else {
      playerArrows += noOfArrows;
      ((Player) player).setArrows(playerArrows);
      description.add(noOfArrows + " arrows have been picked up.\nYou have "
              + player.getArrows() + " arrows with you now.");
      sb.append(noOfArrows).append(" arrows have been picked up.\nYou have ")
              .append(player.getArrows()).append(" arrows with you now.\n");
      ((Location) player.currentLocation()).removeWeapon(noOfArrows);
      if (player.currentLocation().getWeapon() != 0) {
        description.add("There are " + player.currentLocation().getWeapon()
                + " arrows left at this location.");
        sb.append("There are ").append(player.currentLocation().getWeapon())
                .append(" arrows left at this location.\n");
      }
    }
    return sb.toString();
  }

  @Override
  public Smell checkSmell(ILocation playerLocation) {
    if (playerLocation == null) {
      throw new IllegalArgumentException("Player Location is null");
    }
    int otyughCount = 0;
    if (playerLocation.otyughCount() == 1) {
      return Smell.MORE_PUNGENT;
    }
    if (checkSmellHelper(playerLocation) > 0) {
      return Smell.MORE_PUNGENT;
    } else {
      if (playerLocation.getMap().containsKey(Direction.NORTH)) {
        otyughCount += checkSmellHelper(playerLocation.getMap().get(Direction.NORTH));
        if (otyughCount > 1) {
          return Smell.MORE_PUNGENT;
        } else if (otyughCount == 1) {
          return Smell.LESS_PUNGENT;
        }
      }
      if (playerLocation.getMap().containsKey(Direction.SOUTH)) {
        otyughCount += checkSmellHelper(playerLocation.getMap().get(Direction.SOUTH));
        if (otyughCount > 1) {
          return Smell.MORE_PUNGENT;
        } else if (otyughCount == 1) {
          return Smell.LESS_PUNGENT;
        }
      }
      if (playerLocation.getMap().containsKey(Direction.WEST)) {
        otyughCount += checkSmellHelper(playerLocation.getMap().get(Direction.WEST));
        if (otyughCount > 1) {
          return Smell.MORE_PUNGENT;
        } else if (otyughCount == 1) {
          return Smell.LESS_PUNGENT;
        }
      }
      if (playerLocation.getMap().containsKey(Direction.EAST)) {
        otyughCount += checkSmellHelper(playerLocation.getMap().get(Direction.EAST));
        if (otyughCount > 1) {
          return Smell.MORE_PUNGENT;
        } else if (otyughCount == 1) {
          return Smell.LESS_PUNGENT;
        }
      }
    }
    return Smell.NO_SMELL;
  }

  private int checkSmellHelper(ILocation location) {
    if (location == null) {
      throw new IllegalArgumentException("Location is null");
    }
    int count = 0;
    if (location.getMap().containsKey(Direction.NORTH)) {
      if (location.getMap().get(Direction.NORTH).otyughCount() == 1) {
        count++;
      }
    }
    if (location.getMap().containsKey(Direction.SOUTH)) {
      if (location.getMap().get(Direction.SOUTH).otyughCount() == 1) {
        count++;
      }
    }
    if (location.getMap().containsKey(Direction.WEST)) {
      if (location.getMap().get(Direction.WEST).otyughCount() == 1) {
        count++;
      }
    }
    if (location.getMap().containsKey(Direction.EAST)) {
      if (location.getMap().get(Direction.EAST).otyughCount() == 1) {
        count++;
      }
    }
    return count;
  }

  @Override
  public String shootArrow(Direction direction, int distance) {
    if (direction == null) {
      throw new IllegalArgumentException("Direction is null");
    }
    description = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    if (isGameOver()) {
      throw new IllegalStateException("The game is already over");
    }
    if (distance < 1 || distance > 5) {
      throw new IllegalArgumentException("Distance must be between 1-5");
    }
    if (player.currentLocation().getMap().containsKey(direction)) {
      if (player.getArrows() < 1) {
        description.add("You are out of arrows, explore the dungeon to find more.");
        sb.append("You are out of arrows, explore the dungeon to find more.\n");
      } else {
        ILocation arrowLocation = player.currentLocation();
        playerArrows--;
        ((Player) player).setArrows(playerArrows);
        while (distance > 0) {
          if (arrowLocation.typeOfLocation() == LocationType.CAVE) {
            arrowLocation = arrowLocation.getMap().get(direction);
            if (arrowLocation == null) {
              break;
            }
            if (arrowLocation.typeOfLocation() == LocationType.CAVE) {
              distance--;
            } else if (arrowLocation.typeOfLocation() == LocationType.TUNNEL) {
              while (arrowLocation.typeOfLocation() == LocationType.TUNNEL) {
                Map<Direction, ILocation> lamp = tunnelNavigation(direction, arrowLocation);
                Map.Entry<Direction, ILocation> entry = lamp.entrySet().iterator().next();
                arrowLocation = entry.getValue();
                direction = entry.getKey();
              }
              if (arrowLocation.typeOfLocation() == LocationType.CAVE) {
                distance--;
              }
            }
          }
          if (arrowLocation.typeOfLocation() == LocationType.TUNNEL) {
            while (arrowLocation.typeOfLocation() == LocationType.TUNNEL) {
              Map<Direction, ILocation> lamp = tunnelNavigation(direction, arrowLocation);
              Map.Entry<Direction, ILocation> entry = lamp.entrySet().iterator().next();
              arrowLocation = entry.getValue();
              direction = entry.getKey();
            }
            if (arrowLocation.typeOfLocation() == LocationType.CAVE) {
              distance--;
            }
          }
        }
        if (arrowLocation != null) {
          if (arrowLocation.otyughCount() == 1 && arrowLocation.getOtyughHealth() == 100) {
            ((Location) arrowLocation).setOtyughHealth();
            description.add("You shoot an arrow.\nYou hear a little howl in the distance."
                    + "\nYou have " + player.getArrows() + " left now.");
            sb.append("You shoot an arrow.\n").append("You hear a little howl in the distance.");
          } else if (arrowLocation.otyughCount() == 1 && arrowLocation.getOtyughHealth() == 50) {
            ((Location) arrowLocation).removeOtyugh();
            description.add("You shoot an arrow.\nYou hear a great howl in the distance.\nYou have "
                    + player.getArrows() + " left now.");
            sb.append("You shoot an arrow.\n").append("You hear a great howl in the distance.");
          } else {
            description.add("You did not land a hit!!!\nYou have " + player.getArrows()
                    + " left now.");
            sb.append("You did not land a hit!!!");
          }
        } else {
          description.add("You did not land a hit!!!\nYou have " + player.getArrows()
                  + " left now.");
          sb.append("You did not land a hit!!!");
        }
      }
    } else {
      throw new IllegalArgumentException("Path to the specified direction does not exist.");
    }
    return sb.toString();
  }

  private Map<Direction, ILocation> tunnelNavigation(Direction direction, ILocation arrowLocation) {
    if (direction == null || arrowLocation == null) {
      throw new IllegalArgumentException("Direction or Arrow Location is null");
    }
    Map<Direction, ILocation> arrowPaths = new HashMap<>();
    if (direction == Direction.NORTH) {
      if (arrowLocation.getMap().containsKey(Direction.NORTH)) {
        arrowLocation = arrowLocation.getMap().get(Direction.NORTH);
      } else if (arrowLocation.getMap().containsKey(Direction.WEST)) {
        arrowLocation = arrowLocation.getMap().get(Direction.WEST);
        direction = Direction.WEST;
      } else if (arrowLocation.getMap().containsKey(Direction.EAST)) {
        arrowLocation = arrowLocation.getMap().get(Direction.EAST);
        direction = Direction.EAST;
      }
    } else if (direction == Direction.SOUTH) {
      if (arrowLocation.getMap().containsKey(Direction.SOUTH)) {
        arrowLocation = arrowLocation.getMap().get(Direction.SOUTH);
      } else if (arrowLocation.getMap().containsKey(Direction.WEST)) {
        arrowLocation = arrowLocation.getMap().get(Direction.WEST);
        direction = Direction.WEST;
      } else if (arrowLocation.getMap().containsKey(Direction.EAST)) {
        arrowLocation = arrowLocation.getMap().get(Direction.EAST);
        direction = Direction.EAST;
      }
    } else if (direction == Direction.WEST) {
      if (arrowLocation.getMap().containsKey(Direction.WEST)) {
        arrowLocation = arrowLocation.getMap().get(Direction.WEST);
      } else if (arrowLocation.getMap().containsKey(Direction.NORTH)) {
        arrowLocation = arrowLocation.getMap().get(Direction.NORTH);
        direction = Direction.NORTH;
      } else if (arrowLocation.getMap().containsKey(Direction.SOUTH)) {
        arrowLocation = arrowLocation.getMap().get(Direction.SOUTH);
        direction = Direction.SOUTH;
      }
    } else if (direction == Direction.EAST) {
      if (arrowLocation.getMap().containsKey(Direction.EAST)) {
        arrowLocation = arrowLocation.getMap().get(Direction.EAST);
      } else if (arrowLocation.getMap().containsKey(Direction.NORTH)) {
        arrowLocation = arrowLocation.getMap().get(Direction.NORTH);
        direction = Direction.NORTH;
      } else if (arrowLocation.getMap().containsKey(Direction.SOUTH)) {
        arrowLocation = arrowLocation.getMap().get(Direction.SOUTH);
        direction = Direction.SOUTH;
      }
    }
    arrowPaths.put(direction, arrowLocation);
    return new HashMap<>(arrowPaths);
  }

  @Override
  public List<String> description() {
    return new ArrayList<>(description);
  }

  @Override
  public List<String> playerDescription() {
    if (pDescription.size() != 0) {
      pDescription.clear();
      pDescription.add("Player name: " + player.getName());
    }
    pDescription.add("No. of Arrows: " + player.getArrows());
    pDescription.add(player.getInventory());
    return new ArrayList<>(pDescription);
  }

  @Override
  public String getName() {
    return player.getName();
  }

  @Override
  public int getRows() {
    return rows;
  }

  @Override
  public int getColumns() {
    return columns;
  }

  @Override
  public boolean getWrapping() {
    return wrap;
  }

  @Override
  public int getInterconnectivity() {
    return interconnectivity;
  }

  @Override
  public int getTreasureAndArrowPercentage() {
    return treasureAndArrowPercentage;
  }

  @Override
  public int getNoOfOtyughs() {
    return noOfOtyughs;
  }

  @Override
  public int getPlayerArrows() {
    return playerArrows;
  }

  @Override
  public int getRuby() {
    return ((Player) player).getRubyCount();
  }

  @Override
  public int getDiamond() {
    return ((Player) player).getDiamondCount();
  }

  @Override
  public int getSapphire() {
    return ((Player) player).getSapphireCount();
  }

  @Override
  public LinkedList<Integer> getRandomList() {
    return new LinkedList<>(((RandomNumber) rand).getListOfRandom());
  }

  private String dungeonDisplay() {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < rows; ++i) {
      StringBuilder top = new StringBuilder("+");
      StringBuilder mid = new StringBuilder();
      StringBuilder bot = new StringBuilder("+");
      for (int j = 0; j < columns; ++j) {
        if (dungeon[i][j].getMap().containsKey(Direction.NORTH)) {
          top.append("   +");
        } else {
          top.append("---+");
        }
        if (j == 0 && dungeon[i][j].getMap().containsKey(Direction.WEST)) {
          mid.append(" ");
        } else if (j == 0) {
          mid.append("|");
        }
        if (dungeon[i][j].getMap().containsKey(Direction.EAST)) {
          mid.append(" ").append((dungeon[i][j].typeOfLocation() == LocationType.CAVE)
                  ? ((dungeon[i][j].otyughCount() == 1) ? ((dungeon[i][j] == end) ? "E" : "M")
                  : ((dungeon[i][j] == start) ? "S" : "C")) : "T").append("  ");
        } else if (j + 1 == columns) {
          mid.append(" ").append((dungeon[i][j].typeOfLocation() == LocationType.CAVE)
                  ? ((dungeon[i][j].otyughCount() == 1) ? ((dungeon[i][j] == end) ? "E" : "M")
                  : ((dungeon[i][j] == start) ? "S" : "C")) : "T").append(" |");
        } else {
          mid.append(" ").append((dungeon[i][j].typeOfLocation() == LocationType.CAVE)
                  ? ((dungeon[i][j].otyughCount() == 1) ? ((dungeon[i][j] == end) ? "E" : "M")
                  : ((dungeon[i][j] == start) ? "S" : "C")) : "T").append(" |");
        }
        if (dungeon[i][j].getMap().containsKey(Direction.SOUTH)) {
          bot.append("   +");
        } else {
          bot.append("---+");
        }
      }
      stringBuilder.append(top).append("\n").append(mid).append("\n");
      if (i + 1 == rows) {
        stringBuilder.append(bot);
      }
    }
    return stringBuilder.toString();
  }
}
