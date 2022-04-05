package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This represents the locations in the dungeon that maybe either tunnel or cave.
 * A tunnel will have two entrances and a cave might have either one, three or four
 * entrances respectively. They can be filled with treasures.
 */
public class Location implements ILocation {

  private final int name;
  private final Map<Direction, ILocation> neighbours;
  private final Otyugh otyugh;
  private List<Treasure> treasure;
  private int xCord;
  private int yCord;
  private int arrows;
  private int monsters;
  private int rCount;
  private int dCount;
  private int sCount;

  /**
   * Constructs a location object and initializes it to the name of the location.
   *
   * @param name is the name of the location
   * @param x    is the x-coordinate
   * @param y    is the y-coordinate
   */
  public Location(int name, int x, int y) {
    if (name < 0 || x < 0 || y < 0) {
      throw new IllegalArgumentException("Enter values greater than or equal to 0");
    }

    this.name = name;
    this.xCord = x;
    this.yCord = y;
    this.treasure = new ArrayList<>();
    this.xCord = getX();
    this.yCord = getY();
    this.neighbours = new HashMap<>();
    this.arrows = 0;
    this.monsters = 0;
    this.otyugh = new Otyugh();
    this.rCount = 0;
    this.sCount = 0;
    this.dCount = 0;
  }

  @Override
  public int getName() {
    return name;
  }

  @Override
  public List<Treasure> getTreasure() {
    return new ArrayList<>(treasure);
  }

  void setTreasure(Treasure t) {
    if (t == null) {
      throw new IllegalArgumentException("Null treasure");
    }
    List<Treasure> treasureList = new ArrayList<>();
    treasureList.add(t);
    this.treasure.addAll(treasureList);
  }

  @Override
  public int getRubies() {
    rCount = Collections.frequency(getTreasure(), Treasure.RUBIES);
    return rCount;
  }

  @Override
  public int getDiamonds() {
    dCount = Collections.frequency(getTreasure(), Treasure.DIAMONDS);
    return dCount;
  }

  @Override
  public int getSapphires() {
    sCount = Collections.frequency(getTreasure(), Treasure.SAPPHIRES);
    return sCount;
  }

  void removeTreasure() {
    this.treasure = new ArrayList<>();
  }

  @Override
  public int getWeapon() {
    return arrows;
  }

  void setWeapon() {
    this.arrows += 1;
  }

  void removeWeapon(int arrowNumber) {
    this.arrows -= arrowNumber;
  }

  @Override
  public int otyughCount() {
    return monsters;
  }

  void setOtyugh() {
    otyugh.setHealth(100);
    monsters++;
  }

  void removeOtyugh() {
    this.monsters = 0;
    otyugh.setHealth(0);
  }

  @Override
  public int getOtyughHealth() {
    return otyugh.getHealth();
  }

  void setOtyughHealth() {
    otyugh.setHealth(50);
  }

  @Override
  public LocationType typeOfLocation() {
    if (neighbours.size() == 2) {
      return LocationType.TUNNEL;
    }
    return LocationType.CAVE;
  }

  @Override
  public int getX() {
    return xCord;
  }

  @Override
  public int getY() {
    return yCord;
  }

  void linkNeighbours(Direction direction, ILocation l) {
    if (direction == null || l == null) {
      throw new IllegalArgumentException("Null location or Null Direction");
    }
    Map<Direction, ILocation> adjacentNodes = new HashMap<>();
    adjacentNodes.put(direction, l);
    this.neighbours.putAll(adjacentNodes);
  }

  @Override
  public Map<Direction, ILocation> getMap() {
    return new HashMap<>(this.neighbours);
  }

  @Override
  public String toString() {
    StringBuilder string = new StringBuilder();
    if (this.typeOfLocation() == LocationType.CAVE) {
      string.append("You are in a Cave and its doors lead to: ").append(locationAllocation());
    }
    if (this.typeOfLocation() == LocationType.TUNNEL) {
      string.append("You are in a Tunnel that continues to: ").append(locationAllocation());
    }
    return String.valueOf(string);
  }

  private String locationAllocation() {
    StringBuilder location = new StringBuilder();
    if (neighbours.containsKey(Direction.NORTH)) {
      location.append("North ");
    }
    if (neighbours.containsKey(Direction.SOUTH)) {
      location.append("South ");
    }
    if (neighbours.containsKey(Direction.WEST)) {
      location.append("West ");
    }
    if (neighbours.containsKey(Direction.EAST)) {
      location.append("East");
    }
    return location.toString();
  }
}

