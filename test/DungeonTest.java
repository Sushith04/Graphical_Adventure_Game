import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import model.Direction;
import model.Dungeon;
import model.IDungeon;
import model.ILocation;
import model.IPlayer;
import model.Location;
import model.LocationType;
import model.MinRandomGenerator;
import model.Player;
import model.RandomNumber;
import model.RandomNumberGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This method is used to test all the functionalities for dungeon interface.
 */
public class DungeonTest {
  RandomNumberGenerator rand;

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalRows() {
    rand = new RandomNumber();
    IDungeon dungeon = new Dungeon(0, 5, false, 0, 50, 1, "Sushith", rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalColumns() {
    rand = new RandomNumber();
    IDungeon dungeon = new Dungeon(4, 0, false, 0, 50, 2, "Sushith", rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalWrap() {
    rand = new RandomNumber();
    IDungeon dungeon = new Dungeon(0, 5, null, 0, 50, 1, "Sushith", rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalInterconnectivity() {
    rand = new RandomNumber();
    IDungeon dungeon = new Dungeon(0, 5, false, -1, 50, 1, "Sushith", rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeTreasureAndArrowPercentage() {
    rand = new RandomNumber();
    IDungeon dungeon = new Dungeon(5, 5, false, 0, -1, 1, "Sushith", rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGreaterThan100TreasureAndArrowPercentage() {
    rand = new RandomNumber();
    IDungeon dungeon = new Dungeon(5, 5, false, 0, 101, 1, "Sushith", rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLessThan1Monster() {
    rand = new RandomNumber();
    IDungeon dungeon = new Dungeon(5, 5, false, 0, 50, 0, "Sushith", rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalPlayerName() {
    rand = new RandomNumber();
    IPlayer player = new Player(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalLocationName() {
    rand = new RandomNumber();
    ILocation location = new Location(-1, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalXCoordinate() {
    rand = new RandomNumber();
    ILocation location = new Location(1, -1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalYCoordinate() {
    rand = new RandomNumber();
    ILocation location = new Location(1, 1, -3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveNullDirection() {
    rand = new RandomNumber();
    IDungeon dungeon = new Dungeon(5, 5, false, 0, 50, 0, "Sushith", rand);
    dungeon.beginGame();
    dungeon.move(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shootNullDirection() {
    rand = new RandomNumber();
    IDungeon dungeon = new Dungeon(5, 5, false, 0, 50, 0, "Sushith", rand);
    dungeon.beginGame();
    dungeon.shootArrow(null, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shootNegative() {
    rand = new RandomNumber();
    IDungeon dungeon = new Dungeon(5, 5, false, 0, 50, 0, "Sushith", rand);
    dungeon.beginGame();
    dungeon.shootArrow(Direction.EAST, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shootPositiveGreater() {
    rand = new RandomNumber();
    IDungeon dungeon = new Dungeon(5, 5, false, 0, 50, 0, "Sushith", rand);
    dungeon.beginGame();
    dungeon.shootArrow(Direction.EAST, 6);
  }

  @Test
  public void lengthOfThePath() {
    rand = new MinRandomGenerator(2, 24, 30, 32, 24, 14, 38, 25, 60, 62, 20, 18, 20, 48, 50
            , 56, 46, 50, 29, 49, 4, 16, 33, 41, 11, 41, 21, 8, 40, 0, 30, 24, 14, 21, 25, 36, 33
            , 10, 16, 19, 7, 29, 28, 9, 2, 1, 6, 12, 16, 14, 1, 15, 16, 12, 12, 12, 0, 0, 2, 7, 7
            , 5, 0, 2, 0, 0, 0, 0, 3, 0, 0, 0, 3, 5, 3, 5, 4, 4, 0, 5, 1, 0, 0, 5, 0, 4, 3, 4, 5, 2
            , 0, 1, 5, 5, 4, 5, 3, 4, 2, 4, 5, 3, 4, 4, 5, 2, 0, 5, 2, 2, 2, 2, 1, 0, 5, 5, 4, 3, 1
            , 3, 2, 0, 4, 2, 4, 5, 3, 1, 4, 2, 4, 5, 0, 5, 0, 5, 0, 3, 2, 0, 4, 5, 5, 2, 4, 3, 5, 1
            , 1, 3, 2, 0, 3, 5, 3, 4, 4, 3, 3, 3, 4, 3, 2, 2, 3, 3, 1, 0, 1, 2, 1, 3, 0, 3, 3, 0, 3
            , 5, 3, 2, 4, 5, 1, 3, 5, 1, 0, 1, 3, 5, 2, 2, 2, 0, 3, 1, 0, 3, 4, 1, 1, 0, 4, 3, 3, 1
            , 3, 1, 2, 2, 0, 1, 3, 1, 2, 3, 5, 0, 2, 0, 4, 3, 5, 1, 3, 4, 5, 0, 5, 3, 5, 3, 4, 2, 5
            , 4, 2, 1, 1, 0, 0, 2, 1, 1, 0, 3, 2, 1, 4, 1, 0, 0, 1, 2, 2, 3, 0, 1, 3, 2, 5, 5, 5, 0
            , 0, 3, 4, 1, 3, 3, 2, 3, 2, 4, 1, 4, 0, 4, 3, 0, 1);

    IDungeon dungeon = new Dungeon(6, 6, true, 0, 50, 10, "Sushith", rand);
    int distanceCount = 0;
    dungeon.beginGame();
    dungeon.pickTreasure();
    distanceCount++;
    dungeon.move(Direction.EAST);
    distanceCount++;
    dungeon.move(Direction.EAST);
    dungeon.pickWeapon(2);
    dungeon.shootArrow(Direction.SOUTH, 1);
    dungeon.shootArrow(Direction.SOUTH, 1);
    distanceCount++;
    dungeon.move(Direction.SOUTH);
    dungeon.shootArrow(Direction.EAST, 1);
    dungeon.shootArrow(Direction.EAST, 1);
    distanceCount++;
    dungeon.move(Direction.EAST);
    dungeon.pickWeapon(3);
    distanceCount++;
    dungeon.move(Direction.NORTH);
    dungeon.pickTreasure();
    distanceCount++;
    dungeon.move(Direction.EAST);
    distanceCount++;
    dungeon.move(Direction.NORTH);
    distanceCount++;
    dungeon.move(Direction.NORTH);
    dungeon.shootArrow(Direction.WEST, 1);
    distanceCount++;
    dungeon.move(Direction.WEST);
    dungeon.pickTreasure();
    distanceCount++;
    dungeon.shootArrow(Direction.NORTH, 1);
    dungeon.shootArrow(Direction.NORTH, 1);
    dungeon.move(Direction.NORTH);
    distanceCount++;
    assertEquals(11, distanceCount);
  }

  @Test
  public void reachableCaves() {
    rand = new MinRandomGenerator(21, 29, 11, 28, 27, 25, 2, 4, 4, 20, 12, 20, 7, 9, 6, 2, 10
            , 3, 11, 6, 6, 0, 9, 8, 3, 5, 1, 4, 1, 2, 1, 0, 0, 0, 4, 2, 0, 0, 1, 3, 0, 2, 1, 0, 3
            , 2, 2, 1, 3, 3, 1, 1, 2, 2, 4, 1, 0, 1, 1, 0, 4, 4, 3, 2, 3, 1, 2, 0, 2, 1, 0, 3, 0, 3
            , 2, 0, 2, 3, 2, 1, 1, 0, 2, 2, 2, 2, 3, 3, 3, 3, 2, 0, 1, 0, 2, 0, 2, 3, 2, 0, 0, 1, 3
            , 0, 1, 3, 1, 2, 2, 2, 3, 1, 1, 3, 0, 0, 1, 1, 0, 2, 3, 0, 3, 2, 2, 2, 0, 0, 2, 0, 2, 3
            , 2, 3, 3, 1, 0, 2, 3, 2, 2, 2, 2, 3, 0, 0, 0, 1, 2, 2, 3, 3, 1, 0, 3, 1, 2, 3, 2, 3, 1
            , 2, 2, 2, 3, 3, 3, 1, 3, 0, 1, 3, 0, 0, 2, 0, 1, 0, 0, 2, 3, 1, 1, 0, 2);

    IDungeon dungeon = new Dungeon(4, 4, true, 2, 50, 3, "Sushith", rand);
    Set<Integer> ways = new HashSet<>();
    Set<Integer> movements = new HashSet<>();
    for (int i = 0; i < 16; i++) {
      ways.add(i);
    }
    int caveCount = 0;
    int monsterCount = 0;
    dungeon.beginGame();
    movements.add(dungeon.getPlayerLocation().getName());
    dungeon.pickTreasure();
    dungeon.move(Direction.NORTH);
    movements.add(dungeon.getPlayerLocation().getName());
    dungeon.pickTreasure();
    dungeon.move(Direction.EAST);
    movements.add(dungeon.getPlayerLocation().getName());
    dungeon.move(Direction.WEST);
    movements.add(dungeon.getPlayerLocation().getName());
    dungeon.move(Direction.WEST);
    movements.add(dungeon.getPlayerLocation().getName());
    dungeon.pickWeapon(3);
    dungeon.move(Direction.SOUTH);
    movements.add(dungeon.getPlayerLocation().getName());
    dungeon.move(Direction.SOUTH);
    movements.add(dungeon.getPlayerLocation().getName());
    dungeon.move(Direction.EAST);
    movements.add(dungeon.getPlayerLocation().getName());
    dungeon.pickTreasure();
    dungeon.move(Direction.WEST);
    movements.add(dungeon.getPlayerLocation().getName());
    dungeon.move(Direction.WEST);
    movements.add(dungeon.getPlayerLocation().getName());
    dungeon.pickTreasure();
    dungeon.shootArrow(Direction.NORTH, 1);
    dungeon.shootArrow(Direction.NORTH, 1);
    monsterCount++;
    dungeon.move(Direction.NORTH);
    if (dungeon.getPlayerLocation().typeOfLocation() == LocationType.CAVE) {
      caveCount++;
    }
    movements.add(dungeon.getPlayerLocation().getName());
    dungeon.move(Direction.NORTH);
    movements.add(dungeon.getPlayerLocation().getName());
    dungeon.move(Direction.NORTH);
    movements.add(dungeon.getPlayerLocation().getName());
    dungeon.move(Direction.WEST);
    movements.add(dungeon.getPlayerLocation().getName());
    dungeon.pickTreasure();
    dungeon.shootArrow(Direction.NORTH, 1);
    dungeon.shootArrow(Direction.NORTH, 1);
    monsterCount++;
    dungeon.move(Direction.NORTH);
    if (dungeon.getPlayerLocation().typeOfLocation() == LocationType.CAVE) {
      caveCount++;
    }
    movements.add(dungeon.getPlayerLocation().getName());
    dungeon.move(Direction.NORTH);
    movements.add(dungeon.getPlayerLocation().getName());
    dungeon.move(Direction.SOUTH);
    movements.add(dungeon.getPlayerLocation().getName());
    dungeon.move(Direction.SOUTH);
    movements.add(dungeon.getPlayerLocation().getName());
    dungeon.move(Direction.WEST);
    movements.add(dungeon.getPlayerLocation().getName());
    dungeon.shootArrow(Direction.WEST, 1);
    dungeon.shootArrow(Direction.WEST, 1);
    monsterCount++;
    dungeon.move(Direction.WEST);
    if (dungeon.getPlayerLocation().typeOfLocation() == LocationType.CAVE) {
      caveCount++;
    }
    assertEquals(3, monsterCount);
    assertEquals(3, caveCount);
    movements.add(dungeon.getPlayerLocation().getName());
    assertEquals(ways.toString(), movements.toString());
  }

  @Test
  public void startLocation() {
    rand = new RandomNumber();
    IDungeon dungeon = new Dungeon(6, 6, true, 0, 50, 10, "Sushith", rand);
    dungeon.beginGame();
    assertEquals(dungeon.getStart().getName(), dungeon.getPlayerLocation().getName());
  }

  @Test
  public void endLocationAndAllPossibleDirections() {
    rand = new MinRandomGenerator(2, 24, 30, 32, 24, 14, 38, 25, 60, 62, 20, 18, 20, 48, 50
            , 56, 46, 50, 29, 49, 4, 16, 33, 41, 11, 41, 21, 8, 40, 0, 30, 24, 14, 21, 25, 36, 33
            , 10, 16, 19, 7, 29, 28, 9, 2, 1, 6, 12, 16, 14, 1, 15, 16, 12, 12, 12, 0, 0, 2, 7, 7
            , 5, 0, 2, 0, 0, 0, 0, 3, 0, 0, 0, 3, 5, 3, 5, 4, 4, 0, 5, 1, 0, 0, 5, 0, 4, 3, 4, 5, 2
            , 0, 1, 5, 5, 4, 5, 3, 4, 2, 4, 5, 3, 4, 4, 5, 2, 0, 5, 2, 2, 2, 2, 1, 0, 5, 5, 4, 3, 1
            , 3, 2, 0, 4, 2, 4, 5, 3, 1, 4, 2, 4, 5, 0, 5, 0, 5, 0, 3, 2, 0, 4, 5, 5, 2, 4, 3, 5, 1
            , 1, 3, 2, 0, 3, 5, 3, 4, 4, 3, 3, 3, 4, 3, 2, 2, 3, 3, 1, 0, 1, 2, 1, 3, 0, 3, 3, 0, 3
            , 5, 3, 2, 4, 5, 1, 3, 5, 1, 0, 1, 3, 5, 2, 2, 2, 0, 3, 1, 0, 3, 4, 1, 1, 0, 4, 3, 3, 1
            , 3, 1, 2, 2, 0, 1, 3, 1, 2, 3, 5, 0, 2, 0, 4, 3, 5, 1, 3, 4, 5, 0, 5, 3, 5, 3, 4, 2, 5
            , 4, 2, 1, 1, 0, 0, 2, 1, 1, 0, 3, 2, 1, 4, 1, 0, 0, 1, 2, 2, 3, 0, 1, 3, 2, 5, 5, 5, 0
            , 0, 3, 4, 1, 3, 3, 2, 3, 2, 4, 1, 4, 0, 4, 3, 0, 1);

    IDungeon dungeon = new Dungeon(6, 6, true, 0, 50, 10, "Sushith", rand);
    dungeon.beginGame();
    dungeon.pickTreasure();
    assertTrue(dungeon.getPlayerLocation().getMap().containsKey(Direction.EAST));
    dungeon.move(Direction.EAST);
    dungeon.move(Direction.EAST);
    dungeon.pickWeapon(2);
    dungeon.shootArrow(Direction.SOUTH, 1);
    dungeon.shootArrow(Direction.SOUTH, 1);
    assertTrue(dungeon.getPlayerLocation().getMap().containsKey(Direction.SOUTH));
    dungeon.move(Direction.SOUTH);
    dungeon.shootArrow(Direction.EAST, 1);
    dungeon.shootArrow(Direction.EAST, 1);
    dungeon.move(Direction.EAST);
    dungeon.pickWeapon(3);
    assertTrue(dungeon.getPlayerLocation().getMap().containsKey(Direction.NORTH));
    dungeon.move(Direction.NORTH);
    dungeon.pickTreasure();
    dungeon.move(Direction.EAST);
    dungeon.move(Direction.NORTH);
    dungeon.move(Direction.NORTH);
    dungeon.shootArrow(Direction.WEST, 1);
    assertTrue(dungeon.getPlayerLocation().getMap().containsKey(Direction.WEST));
    dungeon.move(Direction.WEST);
    dungeon.pickTreasure();
    dungeon.shootArrow(Direction.NORTH, 1);
    dungeon.shootArrow(Direction.NORTH, 1);
    dungeon.move(Direction.NORTH);
    assertEquals(dungeon.getPlayerLocation().getName(), dungeon.getEnd().getName());
  }


  @Test
  public void playerLocationDescription() {
    rand = new MinRandomGenerator(2, 24, 30, 32, 24, 14, 38, 25, 60, 62, 20, 18, 20, 48, 50
            , 56, 46, 50, 29, 49, 4, 16, 33, 41, 11, 41, 21, 8, 40, 0, 30, 24, 14, 21, 25, 36, 33
            , 10, 16, 19, 7, 29, 28, 9, 2, 1, 6, 12, 16, 14, 1, 15, 16, 12, 12, 12, 0, 0, 2, 7, 7
            , 5, 0, 2, 0, 0, 0, 0, 3, 0, 0, 0, 3, 5, 3, 5, 4, 4, 0, 5, 1, 0, 0, 5, 0, 4, 3, 4, 5, 2
            , 0, 1, 5, 5, 4, 5, 3, 4, 2, 4, 5, 3, 4, 4, 5, 2, 0, 5, 2, 2, 2, 2, 1, 0, 5, 5, 4, 3, 1
            , 3, 2, 0, 4, 2, 4, 5, 3, 1, 4, 2, 4, 5, 0, 5, 0, 5, 0, 3, 2, 0, 4, 5, 5, 2, 4, 3, 5, 1
            , 1, 3, 2, 0, 3, 5, 3, 4, 4, 3, 3, 3, 4, 3, 2, 2, 3, 3, 1, 0, 1, 2, 1, 3, 0, 3, 3, 0, 3
            , 5, 3, 2, 4, 5, 1, 3, 5, 1, 0, 1, 3, 5, 2, 2, 2, 0, 3, 1, 0, 3, 4, 1, 1, 0, 4, 3, 3, 1
            , 3, 1, 2, 2, 0, 1, 3, 1, 2, 3, 5, 0, 2, 0, 4, 3, 5, 1, 3, 4, 5, 0, 5, 3, 5, 3, 4, 2, 5
            , 4, 2, 1, 1, 0, 0, 2, 1, 1, 0, 3, 2, 1, 4, 1, 0, 0, 1, 2, 2, 3, 0, 1, 3, 2, 5, 5, 5, 0
            , 0, 3, 4, 1, 3, 3, 2, 3, 2, 4, 1, 4, 0, 4, 3, 0, 1);

    IDungeon dungeon = new Dungeon(6, 6, true, 0, 50, 10, "Sushith", rand);
    String playerLocationDescription = dungeon.beginGame() +
            dungeon.pickTreasure() +
            dungeon.move(Direction.EAST) +
            dungeon.move(Direction.EAST) +
            dungeon.pickWeapon(2) +
            dungeon.shootArrow(Direction.SOUTH, 1) +
            dungeon.shootArrow(Direction.SOUTH, 1) +
            dungeon.move(Direction.SOUTH) +
            dungeon.shootArrow(Direction.EAST, 1) +
            dungeon.shootArrow(Direction.EAST, 1) +
            dungeon.move(Direction.EAST) +
            dungeon.pickWeapon(3) +
            dungeon.move(Direction.NORTH) +
            dungeon.pickTreasure() +
            dungeon.move(Direction.EAST) +
            dungeon.move(Direction.NORTH) +
            dungeon.move(Direction.NORTH) +
            dungeon.shootArrow(Direction.WEST, 1) +
            dungeon.move(Direction.WEST) +
            dungeon.pickTreasure() +
            dungeon.shootArrow(Direction.NORTH, 1) +
            dungeon.shootArrow(Direction.NORTH, 1) +
            dungeon.move(Direction.NORTH);
    assertEquals("Player name: Sushith.\n"
                    + "You are in a Cave and its doors lead to: North South East\n"
                    + "You find -> Rubies: 5, Sapphires: 4, Diamonds: 3\n"
                    + "You smell something terrible nearby.\n"
                    + "You pick up -> Rubies: 5, Sapphires: 4, Diamonds: 3\n"
                    + "Treasures picked up-> Rubies: 5, Sapphires: 4, Diamonds: 3\n"
                    + "You are in a Tunnel that continues to: West East\n"
                    + "You smell something mild nearby.\n"
                    + "You are in a Tunnel that continues to: South West \n"
                    + "You find 2 arrows here.\n" + "You smell something terrible nearby.\n"
                    + "2 arrows have been picked up.\n" + "You have 5 arrows with you now.\n"
                    + "You shoot an arrow.\n"
                    + "You hear a little howl in the distance.You shoot an arrow.\n"
                    + "You hear a great howl in the distance.You are in a Cave and its doors"
                    + " lead to: North West East\n" + "You smell something terrible nearby.\n"
                    + "You shoot an arrow.\n" + "You hear a little howl in the distance.You shoot"
                    + " an arrow.\n" + "You hear a great howl in the distance.You are in a Cave "
                    + "and its doors lead to: North West East\n" + "You find 3 arrows here.\n"
                    + "You smell something mild nearby.\n" + "3 arrows have been picked up.\n"
                    + "You have 4 arrows with you now.\n"
                    + "You are in a Cave and its doors lead to: North South East\n"
                    + "You find -> Sapphires: 5, Diamonds: 0\n" + "You find 3 arrows here.\n"
                    + "You pick up -> Sapphires: 5, Diamonds: 0\n"
                    + "Treasures picked up-> Rubies: 5, Sapphires: 9, Diamonds: 3\n"
                    + "You are in a Cave and its doors lead to: North West East\n"
                    + "You find 2 arrows here.\n" + "You smell something mild nearby.\n"
                    + "You are in a Tunnel that continues to: North South \n"
                    + "You are in a Tunnel that continues to: South West \n"
                    + "You smell something terrible nearby.\n"
                    + "You did not land a hit!!!You are in a Cave and its doors lead"
                    + " to: North West East\n"
                    + "You find -> Rubies: 3, Diamonds: 2\n" + "You find 3 arrows here.\n"
                    + "You smell something terrible nearby.\n"
                    + "You pick up -> Rubies: 3, Diamonds: 2\n"
                    + "Treasures picked up-> Rubies: 8, Sapphires: 9, Diamonds: 5\n"
                    + "You shoot an arrow.\n"
                    + "You hear a little howl in the distance.You shoot an arrow.\n"
                    + "You hear a great howl in the distance.You are in a Cave and its doors"
                    + " lead to: South West East\n" + "You find 3 arrows here.\n"
                    + "Game Won!!!, player has reached the end.\n"
                    + "Treasures picked up-> Rubies: 8, Sapphires: 9, Diamonds: 5"
            , playerLocationDescription);
  }


  @Test
  public void playerDescription() {
    rand = new MinRandomGenerator(2, 24, 30, 32, 24, 14, 38, 25, 60, 62, 20, 18, 20, 48, 50
            , 56, 46, 50, 29, 49, 4, 16, 33, 41, 11, 41, 21, 8, 40, 0, 30, 24, 14, 21, 25, 36, 33
            , 10, 16, 19, 7, 29, 28, 9, 2, 1, 6, 12, 16, 14, 1, 15, 16, 12, 12, 12, 0, 0, 2, 7, 7
            , 5, 0, 2, 0, 0, 0, 0, 3, 0, 0, 0, 3, 5, 3, 5, 4, 4, 0, 5, 1, 0, 0, 5, 0, 4, 3, 4, 5, 2
            , 0, 1, 5, 5, 4, 5, 3, 4, 2, 4, 5, 3, 4, 4, 5, 2, 0, 5, 2, 2, 2, 2, 1, 0, 5, 5, 4, 3, 1
            , 3, 2, 0, 4, 2, 4, 5, 3, 1, 4, 2, 4, 5, 0, 5, 0, 5, 0, 3, 2, 0, 4, 5, 5, 2, 4, 3, 5, 1
            , 1, 3, 2, 0, 3, 5, 3, 4, 4, 3, 3, 3, 4, 3, 2, 2, 3, 3, 1, 0, 1, 2, 1, 3, 0, 3, 3, 0, 3
            , 5, 3, 2, 4, 5, 1, 3, 5, 1, 0, 1, 3, 5, 2, 2, 2, 0, 3, 1, 0, 3, 4, 1, 1, 0, 4, 3, 3, 1
            , 3, 1, 2, 2, 0, 1, 3, 1, 2, 3, 5, 0, 2, 0, 4, 3, 5, 1, 3, 4, 5, 0, 5, 3, 5, 3, 4, 2, 5
            , 4, 2, 1, 1, 0, 0, 2, 1, 1, 0, 3, 2, 1, 4, 1, 0, 0, 1, 2, 2, 3, 0, 1, 3, 2, 5, 5, 5, 0
            , 0, 3, 4, 1, 3, 3, 2, 3, 2, 4, 1, 4, 0, 4, 3, 0, 1);

    IDungeon dungeon = new Dungeon(6, 6, true, 0, 50, 10, "Sushith", rand);
    dungeon.beginGame();
    dungeon.pickTreasure();
    dungeon.move(Direction.EAST);
    dungeon.move(Direction.EAST);
    dungeon.pickWeapon(2);
    dungeon.shootArrow(Direction.SOUTH, 1);
    dungeon.shootArrow(Direction.SOUTH, 1);
    dungeon.move(Direction.SOUTH);
    dungeon.shootArrow(Direction.EAST, 1);
    dungeon.shootArrow(Direction.EAST, 1);
    dungeon.move(Direction.EAST);
    dungeon.pickWeapon(3);
    dungeon.move(Direction.NORTH);
    dungeon.pickTreasure();
    dungeon.move(Direction.EAST);
    dungeon.move(Direction.NORTH);
    dungeon.move(Direction.NORTH);
    dungeon.shootArrow(Direction.WEST, 1);
    dungeon.move(Direction.WEST);
    dungeon.pickTreasure();
    dungeon.shootArrow(Direction.NORTH, 1);
    dungeon.shootArrow(Direction.NORTH, 1);
    dungeon.move(Direction.NORTH);
    assertEquals("[Player name: Sushith, No. of Arrows: 1, Treasures picked up-> "
            + "Rubies: 8, Sapphires: 9, Diamonds: 5]", dungeon.playerDescription().toString());
  }

  @Test
  public void testNoOfMonsters() {
    rand = new MinRandomGenerator(22, 14, 17, 15, 13, 1, 0, 9, 15, 12, 12, 5, 4, 8, 6, 7, 6, 4
            , 2, 4, 3, 0, 0, 0, 0, 1, 5, 1, 1, 3, 1, 2, 1, 4, 1, 1, 1, 1, 1, 2, 2, 5, 4, 1, 0, 1, 2
            , 2, 3, 1, 0, 2, 2, 3, 1, 1, 2, 2, 0, 2, 1, 3, 0, 2, 3, 1, 2, 2, 1, 2, 1, 2, 2, 0, 3, 2
            , 2, 1, 1, 3, 3, 2, 2, 1, 0, 3, 3, 0, 0, 0, 2, 1, 2, 2, 2, 3, 3, 2, 0, 0, 2, 1, 1, 3, 2
            , 2, 2, 2, 2, 0, 1, 3, 3, 3, 3, 0, 0, 1, 0, 0, 0, 1, 3, 0, 2, 3, 1, 2, 2, 0, 3, 0, 1, 3
            , 2, 3, 1, 0, 2, 1, 1, 0, 0, 0, 0, 3, 3, 3, 0, 0, 1, 3, 1, 0, 3, 3, 3, 0, 1, 0, 2, 2, 3
            , 3, 1, 1, 0, 2, 0, 2, 1, 0, 2, 0, 3, 3, 3, 1, 2, 0, 1, 1, 0, 2, 0, 3, 3, 0, 3, 3, 2, 3
            , 0, 1, 0, 2, 1, 2, 1, 2, 1, 0, 2, 0, 3, 3, 0, 3, 1, 3, 2, 1, 3, 1, 1, 2, 2, 3, 1, 3, 1
            , 1, 3, 0, 0, 3, 3, 0, 1, 1, 0, 2, 1, 1, 1, 3, 0, 0, 2, 2, 3, 0, 3, 2, 3, 2, 1, 0, 2, 1
            , 1, 1, 3, 0, 2, 2, 3, 1, 2);
    IDungeon dungeon = new Dungeon(4, 4, false, 2, 50, 5, "Sushith", rand);
    int monsterCount = 0;
    dungeon.beginGame();
    dungeon.pickWeapon(2);
    dungeon.move(Direction.NORTH);
    dungeon.shootArrow(Direction.EAST, 1);
    assertEquals("You shoot an arrow.\n" + "You hear a little howl in the distance.\n" +
            "You have 4 left now.", dungeon.description().get(0));
    dungeon.shootArrow(Direction.EAST, 1);
    if (dungeon.description().get(0).equals("You shoot an arrow.\n" +
            "You hear a great howl in the distance.\n" + "You have 3 left now.")) {
      monsterCount++;
    }
    dungeon.move(Direction.EAST);
    dungeon.pickWeapon(2);
    dungeon.pickTreasure();
    dungeon.move(Direction.SOUTH);
    dungeon.pickWeapon(2);
    dungeon.move(Direction.EAST);
    dungeon.move(Direction.NORTH);
    dungeon.pickWeapon(2);
    dungeon.pickTreasure();
    dungeon.shootArrow(Direction.EAST, 1);
    dungeon.shootArrow(Direction.EAST, 1);
    if (dungeon.description().get(0).equals("You shoot an arrow.\n" +
            "You hear a great howl in the distance.\n" + "You have 6 left now.")) {
      monsterCount++;
    }
    dungeon.move(Direction.EAST);
    dungeon.pickTreasure();
    dungeon.shootArrow(Direction.NORTH, 1);
    dungeon.shootArrow(Direction.NORTH, 1);
    if (dungeon.description().get(0).equals("You shoot an arrow.\n" +
            "You hear a great howl in the distance.\n" + "You have 4 left now.")) {
      monsterCount++;
    }
    dungeon.move(Direction.NORTH);
    dungeon.pickWeapon(3);
    dungeon.shootArrow(Direction.WEST, 1);
    dungeon.shootArrow(Direction.WEST, 1);
    if (dungeon.description().get(0).equals("You shoot an arrow.\n" +
            "You hear a great howl in the distance.\n" + "You have 5 left now.")) {
      monsterCount++;
    }
    dungeon.move(Direction.WEST);
    dungeon.pickWeapon(2);
    dungeon.pickTreasure();
    dungeon.move(Direction.EAST);
    dungeon.move(Direction.NORTH);
    dungeon.pickWeapon(2);
    dungeon.shootArrow(Direction.WEST, 1);
    dungeon.shootArrow(Direction.WEST, 1);
    if (dungeon.description().get(0).equals("You shoot an arrow.\n" +
            "You hear a great howl in the distance.\n" + "You have 7 left now.")) {
      monsterCount++;
    }
    dungeon.move(Direction.WEST);
    assertEquals(5, monsterCount);
    assertEquals(dungeon.getPlayerLocation().getName(), dungeon.getEnd().getName());
  }

  @Test
  public void gameOverOrNot() {
    rand = new MinRandomGenerator(22, 14, 17, 15, 13, 1, 0, 9, 15, 12, 12, 5, 4, 8, 6, 7, 6, 4
            , 2, 4, 3, 0, 0, 0, 0, 1, 5, 1, 1, 3, 1, 2, 1, 4, 1, 1, 1, 1, 1, 2, 2, 5, 4, 1, 0, 1, 2
            , 2, 3, 1, 0, 2, 2, 3, 1, 1, 2, 2, 0, 2, 1, 3, 0, 2, 3, 1, 2, 2, 1, 2, 1, 2, 2, 0, 3, 2
            , 2, 1, 1, 3, 3, 2, 2, 1, 0, 3, 3, 0, 0, 0, 2, 1, 2, 2, 2, 3, 3, 2, 0, 0, 2, 1, 1, 3, 2
            , 2, 2, 2, 2, 0, 1, 3, 3, 3, 3, 0, 0, 1, 0, 0, 0, 1, 3, 0, 2, 3, 1, 2, 2, 0, 3, 0, 1, 3
            , 2, 3, 1, 0, 2, 1, 1, 0, 0, 0, 0, 3, 3, 3, 0, 0, 1, 3, 1, 0, 3, 3, 3, 0, 1, 0, 2, 2, 3
            , 3, 1, 1, 0, 2, 0, 2, 1, 0, 2, 0, 3, 3, 3, 1, 2, 0, 1, 1, 0, 2, 0, 3, 3, 0, 3, 3, 2, 3
            , 0, 1, 0, 2, 1, 2, 1, 2, 1, 0, 2, 0, 3, 3, 0, 3, 1, 3, 2, 1, 3, 1, 1, 2, 2, 3, 1, 3, 1
            , 1, 3, 0, 0, 3, 3, 0, 1, 1, 0, 2, 1, 1, 1, 3, 0, 0, 2, 2, 3, 0, 3, 2, 3, 2, 1, 0, 2, 1
            , 1, 1, 3, 0, 2, 2, 3, 1, 2);
    IDungeon dungeon = new Dungeon(4, 4, false, 2, 50, 5, "Sushith", rand);
    dungeon.beginGame();
    dungeon.pickWeapon(2);
    dungeon.move(Direction.NORTH);
    dungeon.shootArrow(Direction.EAST, 1);
    dungeon.shootArrow(Direction.EAST, 1);
    dungeon.move(Direction.EAST);
    dungeon.pickWeapon(2);
    dungeon.pickTreasure();
    dungeon.move(Direction.SOUTH);
    dungeon.pickWeapon(2);
    dungeon.move(Direction.EAST);
    dungeon.move(Direction.NORTH);
    dungeon.pickWeapon(2);
    dungeon.pickTreasure();
    dungeon.shootArrow(Direction.EAST, 1);
    dungeon.shootArrow(Direction.EAST, 1);
    dungeon.move(Direction.EAST);
    dungeon.pickTreasure();
    dungeon.shootArrow(Direction.NORTH, 1);
    dungeon.shootArrow(Direction.NORTH, 1);
    dungeon.move(Direction.NORTH);
    dungeon.pickWeapon(3);
    dungeon.shootArrow(Direction.WEST, 1);
    dungeon.shootArrow(Direction.WEST, 1);
    dungeon.move(Direction.WEST);
    dungeon.pickWeapon(2);
    dungeon.pickTreasure();
    dungeon.move(Direction.EAST);
    dungeon.move(Direction.NORTH);
    dungeon.pickWeapon(2);
    dungeon.shootArrow(Direction.WEST, 1);
    dungeon.shootArrow(Direction.WEST, 1);
    assertFalse(dungeon.isGameOver());
    dungeon.move(Direction.WEST);
    assertTrue(dungeon.isGameOver());
  }

  @Test
  public void checkValidStartAndEndCaves() {
    rand = new MinRandomGenerator(22, 14, 17, 15, 13, 1, 0, 9, 15, 12, 12, 5, 4, 8, 6, 7, 6, 4
            , 2, 4, 3, 0, 0, 0, 0, 1, 5, 1, 1, 3, 1, 2, 1, 4, 1, 1, 1, 1, 1, 2, 2, 5, 4, 1, 0, 1, 2
            , 2, 3, 1, 0, 2, 2, 3, 1, 1, 2, 2, 0, 2, 1, 3, 0, 2, 3, 1, 2, 2, 1, 2, 1, 2, 2, 0, 3, 2
            , 2, 1, 1, 3, 3, 2, 2, 1, 0, 3, 3, 0, 0, 0, 2, 1, 2, 2, 2, 3, 3, 2, 0, 0, 2, 1, 1, 3, 2
            , 2, 2, 2, 2, 0, 1, 3, 3, 3, 3, 0, 0, 1, 0, 0, 0, 1, 3, 0, 2, 3, 1, 2, 2, 0, 3, 0, 1, 3
            , 2, 3, 1, 0, 2, 1, 1, 0, 0, 0, 0, 3, 3, 3, 0, 0, 1, 3, 1, 0, 3, 3, 3, 0, 1, 0, 2, 2, 3
            , 3, 1, 1, 0, 2, 0, 2, 1, 0, 2, 0, 3, 3, 3, 1, 2, 0, 1, 1, 0, 2, 0, 3, 3, 0, 3, 3, 2, 3
            , 0, 1, 0, 2, 1, 2, 1, 2, 1, 0, 2, 0, 3, 3, 0, 3, 1, 3, 2, 1, 3, 1, 1, 2, 2, 3, 1, 3, 1
            , 1, 3, 0, 0, 3, 3, 0, 1, 1, 0, 2, 1, 1, 1, 3, 0, 0, 2, 2, 3, 0, 3, 2, 3, 2, 1, 0, 2, 1
            , 1, 1, 3, 0, 2, 2, 3, 1, 2);
    IDungeon dungeon = new Dungeon(4, 4, false, 2, 50, 5, "Sushith", rand);
    dungeon.beginGame();
    int otyughCount = 0;
    boolean finalCaveMonster = false;
    assertEquals("CAVE", dungeon.getStart().typeOfLocation().toString());
    if (dungeon.getPlayerLocation().otyughCount() > 0) {
      otyughCount++;
    }
    assertEquals(0, otyughCount);
    dungeon.pickWeapon(2);
    dungeon.move(Direction.NORTH);
    dungeon.shootArrow(Direction.EAST, 1);
    dungeon.shootArrow(Direction.EAST, 1);
    dungeon.move(Direction.EAST);
    dungeon.pickWeapon(2);
    dungeon.pickTreasure();
    dungeon.move(Direction.SOUTH);
    dungeon.pickWeapon(2);
    dungeon.move(Direction.EAST);
    dungeon.move(Direction.NORTH);
    dungeon.pickWeapon(2);
    dungeon.pickTreasure();
    dungeon.shootArrow(Direction.EAST, 1);
    dungeon.shootArrow(Direction.EAST, 1);
    dungeon.move(Direction.EAST);
    dungeon.pickTreasure();
    dungeon.shootArrow(Direction.NORTH, 1);
    dungeon.shootArrow(Direction.NORTH, 1);
    dungeon.move(Direction.NORTH);
    dungeon.pickWeapon(3);
    dungeon.shootArrow(Direction.WEST, 1);
    dungeon.shootArrow(Direction.WEST, 1);
    dungeon.move(Direction.WEST);
    dungeon.pickWeapon(2);
    dungeon.pickTreasure();
    dungeon.move(Direction.EAST);
    dungeon.move(Direction.NORTH);
    dungeon.pickWeapon(2);
    if (dungeon.getPlayerLocation().getMap().get(Direction.WEST).otyughCount() == 1
            && dungeon.getPlayerLocation().getMap().get(Direction.WEST)
            .getName() == dungeon.getEnd().getName()) {
      finalCaveMonster = true;
    }
    dungeon.shootArrow(Direction.WEST, 1);
    dungeon.shootArrow(Direction.WEST, 1);
    dungeon.move(Direction.WEST);
    assertEquals("CAVE", dungeon.getEnd().typeOfLocation().toString());
    assertTrue(finalCaveMonster);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDirectionInMove() {
    rand = new MinRandomGenerator(22, 14, 17, 15, 13, 1, 0, 9, 15, 12, 12, 5, 4, 8, 6, 7, 6, 4
            , 2, 4, 3, 0, 0, 0, 0, 1, 5, 1, 1, 3, 1, 2, 1, 4, 1, 1, 1, 1, 1, 2, 2, 5, 4, 1, 0, 1, 2
            , 2, 3, 1, 0, 2, 2, 3, 1, 1, 2, 2, 0, 2, 1, 3, 0, 2, 3, 1, 2, 2, 1, 2, 1, 2, 2, 0, 3, 2
            , 2, 1, 1, 3, 3, 2, 2, 1, 0, 3, 3, 0, 0, 0, 2, 1, 2, 2, 2, 3, 3, 2, 0, 0, 2, 1, 1, 3, 2
            , 2, 2, 2, 2, 0, 1, 3, 3, 3, 3, 0, 0, 1, 0, 0, 0, 1, 3, 0, 2, 3, 1, 2, 2, 0, 3, 0, 1, 3
            , 2, 3, 1, 0, 2, 1, 1, 0, 0, 0, 0, 3, 3, 3, 0, 0, 1, 3, 1, 0, 3, 3, 3, 0, 1, 0, 2, 2, 3
            , 3, 1, 1, 0, 2, 0, 2, 1, 0, 2, 0, 3, 3, 3, 1, 2, 0, 1, 1, 0, 2, 0, 3, 3, 0, 3, 3, 2, 3
            , 0, 1, 0, 2, 1, 2, 1, 2, 1, 0, 2, 0, 3, 3, 0, 3, 1, 3, 2, 1, 3, 1, 1, 2, 2, 3, 1, 3, 1
            , 1, 3, 0, 0, 3, 3, 0, 1, 1, 0, 2, 1, 1, 1, 3, 0, 0, 2, 2, 3, 0, 3, 2, 3, 2, 1, 0, 2, 1
            , 1, 1, 3, 0, 2, 2, 3, 1, 2);
    IDungeon dungeon = new Dungeon(4, 4, false, 2, 50, 5, "Sushith", rand);
    dungeon.beginGame();
    dungeon.pickWeapon(2);
    dungeon.move(Direction.SOUTH);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDirectionWhileShooting() {
    rand = new MinRandomGenerator(22, 14, 17, 15, 13, 1, 0, 9, 15, 12, 12, 5, 4, 8, 6, 7, 6, 4
            , 2, 4, 3, 0, 0, 0, 0, 1, 5, 1, 1, 3, 1, 2, 1, 4, 1, 1, 1, 1, 1, 2, 2, 5, 4, 1, 0, 1, 2
            , 2, 3, 1, 0, 2, 2, 3, 1, 1, 2, 2, 0, 2, 1, 3, 0, 2, 3, 1, 2, 2, 1, 2, 1, 2, 2, 0, 3, 2
            , 2, 1, 1, 3, 3, 2, 2, 1, 0, 3, 3, 0, 0, 0, 2, 1, 2, 2, 2, 3, 3, 2, 0, 0, 2, 1, 1, 3, 2
            , 2, 2, 2, 2, 0, 1, 3, 3, 3, 3, 0, 0, 1, 0, 0, 0, 1, 3, 0, 2, 3, 1, 2, 2, 0, 3, 0, 1, 3
            , 2, 3, 1, 0, 2, 1, 1, 0, 0, 0, 0, 3, 3, 3, 0, 0, 1, 3, 1, 0, 3, 3, 3, 0, 1, 0, 2, 2, 3
            , 3, 1, 1, 0, 2, 0, 2, 1, 0, 2, 0, 3, 3, 3, 1, 2, 0, 1, 1, 0, 2, 0, 3, 3, 0, 3, 3, 2, 3
            , 0, 1, 0, 2, 1, 2, 1, 2, 1, 0, 2, 0, 3, 3, 0, 3, 1, 3, 2, 1, 3, 1, 1, 2, 2, 3, 1, 3, 1
            , 1, 3, 0, 0, 3, 3, 0, 1, 1, 0, 2, 1, 1, 1, 3, 0, 0, 2, 2, 3, 0, 3, 2, 3, 2, 1, 0, 2, 1
            , 1, 1, 3, 0, 2, 2, 3, 1, 2);
    IDungeon dungeon = new Dungeon(4, 4, false, 2, 50, 5, "Sushith", rand);
    dungeon.beginGame();
    dungeon.pickWeapon(2);
    dungeon.move(Direction.NORTH);
    dungeon.shootArrow(Direction.WEST, 1);
    dungeon.shootArrow(Direction.WEST, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void zeroAndNegativeDistanceWhileShooting() {
    rand = new MinRandomGenerator(22, 14, 17, 15, 13, 1, 0, 9, 15, 12, 12, 5, 4, 8, 6, 7, 6, 4
            , 2, 4, 3, 0, 0, 0, 0, 1, 5, 1, 1, 3, 1, 2, 1, 4, 1, 1, 1, 1, 1, 2, 2, 5, 4, 1, 0, 1, 2
            , 2, 3, 1, 0, 2, 2, 3, 1, 1, 2, 2, 0, 2, 1, 3, 0, 2, 3, 1, 2, 2, 1, 2, 1, 2, 2, 0, 3, 2
            , 2, 1, 1, 3, 3, 2, 2, 1, 0, 3, 3, 0, 0, 0, 2, 1, 2, 2, 2, 3, 3, 2, 0, 0, 2, 1, 1, 3, 2
            , 2, 2, 2, 2, 0, 1, 3, 3, 3, 3, 0, 0, 1, 0, 0, 0, 1, 3, 0, 2, 3, 1, 2, 2, 0, 3, 0, 1, 3
            , 2, 3, 1, 0, 2, 1, 1, 0, 0, 0, 0, 3, 3, 3, 0, 0, 1, 3, 1, 0, 3, 3, 3, 0, 1, 0, 2, 2, 3
            , 3, 1, 1, 0, 2, 0, 2, 1, 0, 2, 0, 3, 3, 3, 1, 2, 0, 1, 1, 0, 2, 0, 3, 3, 0, 3, 3, 2, 3
            , 0, 1, 0, 2, 1, 2, 1, 2, 1, 0, 2, 0, 3, 3, 0, 3, 1, 3, 2, 1, 3, 1, 1, 2, 2, 3, 1, 3, 1
            , 1, 3, 0, 0, 3, 3, 0, 1, 1, 0, 2, 1, 1, 1, 3, 0, 0, 2, 2, 3, 0, 3, 2, 3, 2, 1, 0, 2, 1
            , 1, 1, 3, 0, 2, 2, 3, 1, 2);
    IDungeon dungeon = new Dungeon(4, 4, false, 2, 50, 5, "Sushith", rand);
    dungeon.beginGame();
    dungeon.pickWeapon(2);
    dungeon.move(Direction.NORTH);
    dungeon.shootArrow(Direction.EAST, -1);
    dungeon.shootArrow(Direction.EAST, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void greaterDistanceWhileShooting() {
    rand = new MinRandomGenerator(22, 14, 17, 15, 13, 1, 0, 9, 15, 12, 12, 5, 4, 8, 6, 7, 6, 4
            , 2, 4, 3, 0, 0, 0, 0, 1, 5, 1, 1, 3, 1, 2, 1, 4, 1, 1, 1, 1, 1, 2, 2, 5, 4, 1, 0, 1, 2
            , 2, 3, 1, 0, 2, 2, 3, 1, 1, 2, 2, 0, 2, 1, 3, 0, 2, 3, 1, 2, 2, 1, 2, 1, 2, 2, 0, 3, 2
            , 2, 1, 1, 3, 3, 2, 2, 1, 0, 3, 3, 0, 0, 0, 2, 1, 2, 2, 2, 3, 3, 2, 0, 0, 2, 1, 1, 3, 2
            , 2, 2, 2, 2, 0, 1, 3, 3, 3, 3, 0, 0, 1, 0, 0, 0, 1, 3, 0, 2, 3, 1, 2, 2, 0, 3, 0, 1, 3
            , 2, 3, 1, 0, 2, 1, 1, 0, 0, 0, 0, 3, 3, 3, 0, 0, 1, 3, 1, 0, 3, 3, 3, 0, 1, 0, 2, 2, 3
            , 3, 1, 1, 0, 2, 0, 2, 1, 0, 2, 0, 3, 3, 3, 1, 2, 0, 1, 1, 0, 2, 0, 3, 3, 0, 3, 3, 2, 3
            , 0, 1, 0, 2, 1, 2, 1, 2, 1, 0, 2, 0, 3, 3, 0, 3, 1, 3, 2, 1, 3, 1, 1, 2, 2, 3, 1, 3, 1
            , 1, 3, 0, 0, 3, 3, 0, 1, 1, 0, 2, 1, 1, 1, 3, 0, 0, 2, 2, 3, 0, 3, 2, 3, 2, 1, 0, 2, 1
            , 1, 1, 3, 0, 2, 2, 3, 1, 2);
    IDungeon dungeon = new Dungeon(4, 4, false, 2, 50, 5, "Sushith", rand);
    dungeon.beginGame();
    dungeon.pickWeapon(2);
    dungeon.move(Direction.NORTH);
    dungeon.shootArrow(Direction.EAST, 5);
    dungeon.shootArrow(Direction.EAST, 6);
  }

  @Test
  public void pickUpArrows() {
    rand = new MinRandomGenerator(22, 14, 17, 15, 13, 1, 0, 9, 15, 12, 12, 5, 4, 8, 6, 7, 6, 4
            , 2, 4, 3, 0, 0, 0, 0, 1, 5, 1, 1, 3, 1, 2, 1, 4, 1, 1, 1, 1, 1, 2, 2, 5, 4, 1, 0, 1, 2
            , 2, 3, 1, 0, 2, 2, 3, 1, 1, 2, 2, 0, 2, 1, 3, 0, 2, 3, 1, 2, 2, 1, 2, 1, 2, 2, 0, 3, 2
            , 2, 1, 1, 3, 3, 2, 2, 1, 0, 3, 3, 0, 0, 0, 2, 1, 2, 2, 2, 3, 3, 2, 0, 0, 2, 1, 1, 3, 2
            , 2, 2, 2, 2, 0, 1, 3, 3, 3, 3, 0, 0, 1, 0, 0, 0, 1, 3, 0, 2, 3, 1, 2, 2, 0, 3, 0, 1, 3
            , 2, 3, 1, 0, 2, 1, 1, 0, 0, 0, 0, 3, 3, 3, 0, 0, 1, 3, 1, 0, 3, 3, 3, 0, 1, 0, 2, 2, 3
            , 3, 1, 1, 0, 2, 0, 2, 1, 0, 2, 0, 3, 3, 3, 1, 2, 0, 1, 1, 0, 2, 0, 3, 3, 0, 3, 3, 2, 3
            , 0, 1, 0, 2, 1, 2, 1, 2, 1, 0, 2, 0, 3, 3, 0, 3, 1, 3, 2, 1, 3, 1, 1, 2, 2, 3, 1, 3, 1
            , 1, 3, 0, 0, 3, 3, 0, 1, 1, 0, 2, 1, 1, 1, 3, 0, 0, 2, 2, 3, 0, 3, 2, 3, 2, 1, 0, 2, 1
            , 1, 1, 3, 0, 2, 2, 3, 1, 2);
    IDungeon dungeon = new Dungeon(4, 4, false, 2, 50, 5, "Sushith", rand);
    dungeon.beginGame();
    assertTrue(dungeon.getPlayerLocation().getWeapon() != 0);
    dungeon.pickWeapon(1);
    assertEquals(dungeon.description().get(0), "1 arrows have been picked up.\n" +
            "You have 4 arrows with you now.");
    assertEquals(1, dungeon.getPlayerLocation().getWeapon());
    dungeon.pickWeapon(1);
    assertEquals(dungeon.description().get(0), "1 arrows have been picked up.\n" +
            "You have 5 arrows with you now.");
    dungeon.move(Direction.NORTH);
    dungeon.shootArrow(Direction.EAST, 1);
    dungeon.shootArrow(Direction.EAST, 1);
    dungeon.move(Direction.EAST);
    dungeon.pickWeapon(3);
    assertEquals(dungeon.description().get(0)
            , "You have 2 arrows only at this location.\n" +
                    "2 arrows have been picked up.\n" + "You have 5 arrows with you now.");
  }

  @Test
  public void shootArrow() {
    rand = new MinRandomGenerator(22, 14, 17, 15, 13, 1, 0, 9, 15, 12, 12, 5, 4, 8, 6, 7, 6, 4
            , 2, 4, 3, 0, 0, 0, 0, 1, 5, 1, 1, 3, 1, 2, 1, 4, 1, 1, 1, 1, 1, 2, 2, 5, 4, 1, 0, 1, 2
            , 2, 3, 1, 0, 2, 2, 3, 1, 1, 2, 2, 0, 2, 1, 3, 0, 2, 3, 1, 2, 2, 1, 2, 1, 2, 2, 0, 3, 2
            , 2, 1, 1, 3, 3, 2, 2, 1, 0, 3, 3, 0, 0, 0, 2, 1, 2, 2, 2, 3, 3, 2, 0, 0, 2, 1, 1, 3, 2
            , 2, 2, 2, 2, 0, 1, 3, 3, 3, 3, 0, 0, 1, 0, 0, 0, 1, 3, 0, 2, 3, 1, 2, 2, 0, 3, 0, 1, 3
            , 2, 3, 1, 0, 2, 1, 1, 0, 0, 0, 0, 3, 3, 3, 0, 0, 1, 3, 1, 0, 3, 3, 3, 0, 1, 0, 2, 2, 3
            , 3, 1, 1, 0, 2, 0, 2, 1, 0, 2, 0, 3, 3, 3, 1, 2, 0, 1, 1, 0, 2, 0, 3, 3, 0, 3, 3, 2, 3
            , 0, 1, 0, 2, 1, 2, 1, 2, 1, 0, 2, 0, 3, 3, 0, 3, 1, 3, 2, 1, 3, 1, 1, 2, 2, 3, 1, 3, 1
            , 1, 3, 0, 0, 3, 3, 0, 1, 1, 0, 2, 1, 1, 1, 3, 0, 0, 2, 2, 3, 0, 3, 2, 3, 2, 1, 0, 2, 1
            , 1, 1, 3, 0, 2, 2, 3, 1, 2);
    IDungeon dungeon = new Dungeon(4, 4, false, 2, 50, 5, "Sushith", rand);
    dungeon.beginGame();
    assertTrue(dungeon.getPlayerLocation().getWeapon() != 0);
    dungeon.pickWeapon(2);
    assertEquals(dungeon.getPlayerLocation().getMap().get(Direction.NORTH).typeOfLocation()
            , LocationType.TUNNEL);
    assertEquals(dungeon.getPlayerLocation().getMap().get(Direction.NORTH).getMap()
            .get(Direction.EAST).typeOfLocation(), LocationType.CAVE);
    dungeon.shootArrow(Direction.NORTH, 1);
    assertEquals("You shoot an arrow.\n" + "You hear a little howl in the distance.\n"
            + "You have 4 left now.", dungeon.description().get(0));
    dungeon.shootArrow(Direction.NORTH, 1);
    assertEquals("You shoot an arrow.\n" + "You hear a great howl in the distance.\n"
            + "You have 3 left now.", dungeon.description().get(0));
    dungeon.move(Direction.NORTH);
    dungeon.shootArrow(Direction.EAST, 1);
    assertEquals("You did not land a hit!!!\n" + "You have 2 left now."
            , dungeon.description().get(0));
    assertEquals(dungeon.getPlayerLocation().getMap().get(Direction.EAST).typeOfLocation()
            , LocationType.CAVE);
    assertEquals(dungeon.getPlayerLocation().getMap().get(Direction.EAST).getMap()
            .get(Direction.EAST).typeOfLocation(), LocationType.CAVE);
    assertEquals(dungeon.getPlayerLocation().getMap().get(Direction.EAST).getMap()
            .get(Direction.EAST).getMap().get(Direction.EAST).typeOfLocation(), LocationType.CAVE);
    dungeon.shootArrow(Direction.EAST, 3);
    assertEquals("You shoot an arrow.\n" + "You hear a little howl in the distance.\n"
            + "You have 1 left now.", dungeon.description().get(0));
    dungeon.shootArrow(Direction.EAST, 3);
    assertEquals("You shoot an arrow.\n" + "You hear a great howl in the distance.\n"
            + "You have 0 left now.", dungeon.description().get(0));
    dungeon.move(Direction.EAST);
    dungeon.pickWeapon(2);
    dungeon.move(Direction.EAST);
    dungeon.pickWeapon(1);
    dungeon.shootArrow(Direction.EAST, 2);
    assertEquals("You did not land a hit!!!\n" + "You have 2 left now."
            , dungeon.description().get(0));
    dungeon.move(Direction.EAST);
    dungeon.shootArrow(Direction.NORTH, 1);
    dungeon.shootArrow(Direction.NORTH, 1);
    dungeon.move(Direction.NORTH);
    dungeon.pickWeapon(3);
    dungeon.move(Direction.SOUTH);
    dungeon.shootArrow(Direction.NORTH, 2);
    assertEquals("You shoot an arrow.\n" + "You hear a little howl in the distance.\n"
            + "You have 2 left now.", dungeon.description().get(0));
    dungeon.move(Direction.NORTH);
    dungeon.shootArrow(Direction.NORTH, 1);
    assertEquals("You shoot an arrow.\n" + "You hear a great howl in the distance.\n"
            + "You have 1 left now.", dungeon.description().get(0));
    dungeon.move(Direction.NORTH);
    dungeon.shootArrow(Direction.WEST, 1);
    dungeon.move(Direction.WEST);
  }

  @Test
  public void pickUpTreasure() {
    rand = new MinRandomGenerator(2, 24, 30, 32, 24, 14, 38, 25, 60, 62, 20, 18, 20, 48, 50
            , 56, 46, 50, 29, 49, 4, 16, 33, 41, 11, 41, 21, 8, 40, 0, 30, 24, 14, 21, 25, 36, 33
            , 10, 16, 19, 7, 29, 28, 9, 2, 1, 6, 12, 16, 14, 1, 15, 16, 12, 12, 12, 0, 0, 2, 7, 7
            , 5, 0, 2, 0, 0, 0, 0, 3, 0, 0, 0, 3, 5, 3, 5, 4, 4, 0, 5, 1, 0, 0, 5, 0, 4, 3, 4, 5, 2
            , 0, 1, 5, 5, 4, 5, 3, 4, 2, 4, 5, 3, 4, 4, 5, 2, 0, 5, 2, 2, 2, 2, 1, 0, 5, 5, 4, 3, 1
            , 3, 2, 0, 4, 2, 4, 5, 3, 1, 4, 2, 4, 5, 0, 5, 0, 5, 0, 3, 2, 0, 4, 5, 5, 2, 4, 3, 5, 1
            , 1, 3, 2, 0, 3, 5, 3, 4, 4, 3, 3, 3, 4, 3, 2, 2, 3, 3, 1, 0, 1, 2, 1, 3, 0, 3, 3, 0, 3
            , 5, 3, 2, 4, 5, 1, 3, 5, 1, 0, 1, 3, 5, 2, 2, 2, 0, 3, 1, 0, 3, 4, 1, 1, 0, 4, 3, 3, 1
            , 3, 1, 2, 2, 0, 1, 3, 1, 2, 3, 5, 0, 2, 0, 4, 3, 5, 1, 3, 4, 5, 0, 5, 3, 5, 3, 4, 2, 5
            , 4, 2, 1, 1, 0, 0, 2, 1, 1, 0, 3, 2, 1, 4, 1, 0, 0, 1, 2, 2, 3, 0, 1, 3, 2, 5, 5, 5, 0
            , 0, 3, 4, 1, 3, 3, 2, 3, 2, 4, 1, 4, 0, 4, 3, 0, 1);

    IDungeon dungeon = new Dungeon(6, 6, true, 0, 50, 10, "Sushith", rand);
    dungeon.beginGame();
    assertEquals("[Player name: Sushith, No. of Arrows: 3, Treasures picked up->"
            + " Rubies: 0, Sapphires: 0, Diamonds: 0]", dungeon.playerDescription().toString());
    dungeon.pickTreasure();
    assertEquals("Treasures picked up-> Rubies: 5, Sapphires: 4, Diamonds: 3"
            , dungeon.description().get(0));
    dungeon.move(Direction.EAST);
    dungeon.move(Direction.EAST);
    dungeon.pickWeapon(2);
    dungeon.shootArrow(Direction.SOUTH, 1);
    dungeon.shootArrow(Direction.SOUTH, 1);
    dungeon.move(Direction.SOUTH);
    assertEquals(1, dungeon.getPlayerLocation().getMap().get(Direction.EAST).otyughCount());
    dungeon.shootArrow(Direction.EAST, 1);
    dungeon.shootArrow(Direction.EAST, 1);
    assertEquals(0, dungeon.getPlayerLocation().getMap().get(Direction.EAST).otyughCount());
    dungeon.move(Direction.EAST);
    assertTrue(dungeon.getPlayerLocation().getWeapon() > 0);
    dungeon.pickWeapon(3);
    dungeon.move(Direction.NORTH);
    dungeon.pickTreasure();
    dungeon.move(Direction.EAST);
    dungeon.move(Direction.NORTH);
    dungeon.move(Direction.NORTH);
    dungeon.shootArrow(Direction.WEST, 1);
    dungeon.move(Direction.WEST);
    dungeon.pickTreasure();
    dungeon.shootArrow(Direction.NORTH, 2);
    assertEquals("You did not land a hit!!!\n" + "You have 2 left now."
            , dungeon.description().get(0));
    dungeon.shootArrow(Direction.NORTH, 1);
    dungeon.shootArrow(Direction.NORTH, 1);
    dungeon.move(Direction.NORTH);
  }

  @Test
  public void playerDeadWhenFullAliveOtyugh() {
    rand = new MinRandomGenerator(2, 24, 30, 32, 24, 14, 38, 25, 60, 62, 20, 18, 20, 48, 50
            , 56, 46, 50, 29, 49, 4, 16, 33, 41, 11, 41, 21, 8, 40, 0, 30, 24, 14, 21, 25, 36, 33
            , 10, 16, 19, 7, 29, 28, 9, 2, 1, 6, 12, 16, 14, 1, 15, 16, 12, 12, 12, 0, 0, 2, 7, 7
            , 5, 0, 2, 0, 0, 0, 0, 3, 0, 0, 0, 3, 5, 3, 5, 4, 4, 0, 5, 1, 0, 0, 5, 0, 4, 3, 4, 5, 2
            , 0, 1, 5, 5, 4, 5, 3, 4, 2, 4, 5, 3, 4, 4, 5, 2, 0, 5, 2, 2, 2, 2, 1, 0, 5, 5, 4, 3, 1
            , 3, 2, 0, 4, 2, 4, 5, 3, 1, 4, 2, 4, 5, 0, 5, 0, 5, 0, 3, 2, 0, 4, 5, 5, 2, 4, 3, 5, 1
            , 1, 3, 2, 0, 3, 5, 3, 4, 4, 3, 3, 3, 4, 3, 2, 2, 3, 3, 1, 0, 1, 2, 1, 3, 0, 3, 3, 0, 3
            , 5, 3, 2, 4, 5, 1, 3, 5, 1, 0, 1, 3, 5, 2, 2, 2, 0, 3, 1, 0, 3, 4, 1, 1, 0, 4, 3, 3, 1
            , 3, 1, 2, 2, 0, 1, 3, 1, 2, 3, 5, 0, 2, 0, 4, 3, 5, 1, 3, 4, 5, 0, 5, 3, 5, 3, 4, 2, 5
            , 4, 2, 1, 1, 0, 0, 2, 1, 1, 0, 3, 2, 1, 4, 1, 0, 0, 1, 2, 2, 3, 0, 1, 3, 2, 5, 5, 5, 0
            , 0, 3, 4, 1, 3, 3, 2, 3, 2, 4, 1, 4, 0, 4, 3, 0, 1);

    IDungeon dungeon = new Dungeon(6, 6, true, 0, 50, 10, "Sushith", rand);
    dungeon.beginGame();
    dungeon.pickTreasure();
    dungeon.move(Direction.EAST);
    dungeon.move(Direction.EAST);
    dungeon.pickWeapon(2);
    dungeon.move(Direction.SOUTH);
    assertEquals("Chomp, chomp, you are eaten by an Otyugh!\n" +
            "Better luck next time.", dungeon.description().get(0));
  }

  @Test
  public void playerDeadWhenHalfAliveOtyughInCaves() {
    rand = new MinRandomGenerator(2, 24, 30, 32, 24, 14, 38, 25, 60, 62, 20, 18, 20, 48, 50
            , 56, 46, 50, 29, 49, 4, 16, 33, 41, 11, 41, 21, 8, 40, 0, 30, 24, 14, 21, 25, 36, 33
            , 10, 16, 19, 7, 29, 28, 9, 2, 1, 6, 12, 16, 14, 1, 15, 16, 12, 12, 12, 0, 0, 2, 7, 7
            , 5, 0, 2, 0, 0, 0, 0, 3, 0, 0, 0, 3, 5, 3, 5, 4, 4, 0, 5, 1, 0, 0, 5, 0, 4, 3, 4, 5, 2
            , 0, 1, 5, 5, 4, 5, 3, 4, 2, 4, 5, 3, 4, 4, 5, 2, 0, 5, 2, 2, 2, 2, 1, 0, 5, 5, 4, 3, 1
            , 3, 2, 0, 4, 2, 4, 5, 3, 1, 4, 2, 4, 5, 0, 5, 0, 5, 0, 3, 2, 0, 4, 5, 5, 2, 4, 3, 5, 1
            , 1, 3, 2, 0, 3, 5, 3, 4, 4, 3, 3, 3, 4, 3, 2, 2, 3, 3, 1, 0, 1, 2, 1, 3, 0, 3, 3, 0, 3
            , 5, 3, 2, 4, 5, 1, 3, 5, 1, 0, 1, 3, 5, 2, 2, 2, 0, 3, 1, 0, 3, 4, 1, 1, 0, 4, 3, 3, 1
            , 3, 1, 2, 2, 0, 1, 3, 1, 2, 3, 5, 0, 2, 0, 4, 3, 5, 1, 3, 4, 5, 0, 5, 3, 5, 3, 4, 2, 5
            , 4, 2, 1, 1, 0, 0, 2, 1, 1, 0, 3, 2, 1, 4, 1, 0, 0, 1, 2, 2, 3, 0, 1, 3, 2, 5, 5, 5, 0
            , 0, 3, 4, 1, 3, 3, 2, 3, 2, 4, 1, 4, 0, 4, 3, 0, 0);

    IDungeon dungeon = new Dungeon(6, 6, true, 0, 50, 10, "Sushith", rand);
    dungeon.beginGame();
    dungeon.pickTreasure();
    dungeon.move(Direction.EAST);
    dungeon.move(Direction.EAST);
    dungeon.pickWeapon(2);
    dungeon.shootArrow(Direction.SOUTH, 1);
    dungeon.move(Direction.SOUTH);
    assertEquals("Chomp, chomp, you are eaten by an Otyugh!\n" +
            "Better luck next time.", dungeon.description().get(0));
  }

  @Test
  public void playerWinWhenHalfAliveOtyughInEnd() {
    rand = new MinRandomGenerator(2, 24, 30, 32, 24, 14, 38, 25, 60, 62, 20, 18, 20, 48, 50
            , 56, 46, 50, 29, 49, 4, 16, 33, 41, 11, 41, 21, 8, 40, 0, 30, 24, 14, 21, 25, 36, 33
            , 10, 16, 19, 7, 29, 28, 9, 2, 1, 6, 12, 16, 14, 1, 15, 16, 12, 12, 12, 0, 0, 2, 7, 7
            , 5, 0, 2, 0, 0, 0, 0, 3, 0, 0, 0, 3, 5, 3, 5, 4, 4, 0, 5, 1, 0, 0, 5, 0, 4, 3, 4, 5, 2
            , 0, 1, 5, 5, 4, 5, 3, 4, 2, 4, 5, 3, 4, 4, 5, 2, 0, 5, 2, 2, 2, 2, 1, 0, 5, 5, 4, 3, 1
            , 3, 2, 0, 4, 2, 4, 5, 3, 1, 4, 2, 4, 5, 0, 5, 0, 5, 0, 3, 2, 0, 4, 5, 5, 2, 4, 3, 5, 1
            , 1, 3, 2, 0, 3, 5, 3, 4, 4, 3, 3, 3, 4, 3, 2, 2, 3, 3, 1, 0, 1, 2, 1, 3, 0, 3, 3, 0, 3
            , 5, 3, 2, 4, 5, 1, 3, 5, 1, 0, 1, 3, 5, 2, 2, 2, 0, 3, 1, 0, 3, 4, 1, 1, 0, 4, 3, 3, 1
            , 3, 1, 2, 2, 0, 1, 3, 1, 2, 3, 5, 0, 2, 0, 4, 3, 5, 1, 3, 4, 5, 0, 5, 3, 5, 3, 4, 2, 5
            , 4, 2, 1, 1, 0, 0, 2, 1, 1, 0, 3, 2, 1, 4, 1, 0, 0, 1, 2, 2, 3, 0, 1, 3, 2, 5, 5, 5, 0
            , 0, 3, 4, 1, 3, 3, 2, 3, 2, 4, 1, 4, 0, 4, 3, 0, 1);

    IDungeon dungeon = new Dungeon(6, 6, true, 0, 50, 10, "Sushith", rand);
    dungeon.beginGame();
    dungeon.pickTreasure();
    dungeon.move(Direction.EAST);
    dungeon.move(Direction.EAST);
    dungeon.pickWeapon(2);
    dungeon.shootArrow(Direction.SOUTH, 1);
    dungeon.shootArrow(Direction.SOUTH, 1);
    dungeon.move(Direction.SOUTH);
    dungeon.shootArrow(Direction.EAST, 1);
    dungeon.shootArrow(Direction.EAST, 1);
    dungeon.move(Direction.EAST);
    dungeon.pickWeapon(3);
    dungeon.move(Direction.NORTH);
    dungeon.pickTreasure();
    dungeon.move(Direction.EAST);
    dungeon.move(Direction.NORTH);
    dungeon.move(Direction.NORTH);
    dungeon.shootArrow(Direction.WEST, 1);
    dungeon.move(Direction.WEST);
    dungeon.pickTreasure();
    dungeon.shootArrow(Direction.NORTH, 1);
    dungeon.move(Direction.NORTH);
    assertEquals(dungeon.getPlayerLocation().getName(), dungeon.getEnd().getName());
    assertTrue(dungeon.isGameOver());
    assertEquals("Game Won!!!, player has reached the end.\n" +
                    "Treasures picked up-> Rubies: 8, Sapphires: 9, Diamonds: 5"
            , dungeon.description().get(0));
  }

  @Test
  public void playerStartsWith3Arrows() {
    rand = new MinRandomGenerator(2, 24, 30, 32, 24, 14, 38, 25, 60, 62, 20, 18, 20, 48, 50
            , 56, 46, 50, 29, 49, 4, 16, 33, 41, 11, 41, 21, 8, 40, 0, 30, 24, 14, 21, 25, 36, 33
            , 10, 16, 19, 7, 29, 28, 9, 2, 1, 6, 12, 16, 14, 1, 15, 16, 12, 12, 12, 0, 0, 2, 7, 7
            , 5, 0, 2, 0, 0, 0, 0, 3, 0, 0, 0, 3, 5, 3, 5, 4, 4, 0, 5, 1, 0, 0, 5, 0, 4, 3, 4, 5, 2
            , 0, 1, 5, 5, 4, 5, 3, 4, 2, 4, 5, 3, 4, 4, 5, 2, 0, 5, 2, 2, 2, 2, 1, 0, 5, 5, 4, 3, 1
            , 3, 2, 0, 4, 2, 4, 5, 3, 1, 4, 2, 4, 5, 0, 5, 0, 5, 0, 3, 2, 0, 4, 5, 5, 2, 4, 3, 5, 1
            , 1, 3, 2, 0, 3, 5, 3, 4, 4, 3, 3, 3, 4, 3, 2, 2, 3, 3, 1, 0, 1, 2, 1, 3, 0, 3, 3, 0, 3
            , 5, 3, 2, 4, 5, 1, 3, 5, 1, 0, 1, 3, 5, 2, 2, 2, 0, 3, 1, 0, 3, 4, 1, 1, 0, 4, 3, 3, 1
            , 3, 1, 2, 2, 0, 1, 3, 1, 2, 3, 5, 0, 2, 0, 4, 3, 5, 1, 3, 4, 5, 0, 5, 3, 5, 3, 4, 2, 5
            , 4, 2, 1, 1, 0, 0, 2, 1, 1, 0, 3, 2, 1, 4, 1, 0, 0, 1, 2, 2, 3, 0, 1, 3, 2, 5, 5, 5, 0
            , 0, 3, 4, 1, 3, 3, 2, 3, 2, 4, 1, 4, 0, 4, 3, 0, 1);

    IDungeon dungeon = new Dungeon(6, 6, true, 0, 50, 10, "Sushith", rand);
    dungeon.beginGame();
    assertEquals("No. of Arrows: 3", dungeon.playerDescription().get(1));
  }

  @Test
  public void playerCreatedSuccessfully() {
    rand = new MinRandomGenerator(2, 24, 30, 32, 24, 14, 38, 25, 60, 62, 20, 18, 20, 48, 50
            , 56, 46, 50, 29, 49, 4, 16, 33, 41, 11, 41, 21, 8, 40, 0, 30, 24, 14, 21, 25, 36, 33
            , 10, 16, 19, 7, 29, 28, 9, 2, 1, 6, 12, 16, 14, 1, 15, 16, 12, 12, 12, 0, 0, 2, 7, 7
            , 5, 0, 2, 0, 0, 0, 0, 3, 0, 0, 0, 3, 5, 3, 5, 4, 4, 0, 5, 1, 0, 0, 5, 0, 4, 3, 4, 5, 2
            , 0, 1, 5, 5, 4, 5, 3, 4, 2, 4, 5, 3, 4, 4, 5, 2, 0, 5, 2, 2, 2, 2, 1, 0, 5, 5, 4, 3, 1
            , 3, 2, 0, 4, 2, 4, 5, 3, 1, 4, 2, 4, 5, 0, 5, 0, 5, 0, 3, 2, 0, 4, 5, 5, 2, 4, 3, 5, 1
            , 1, 3, 2, 0, 3, 5, 3, 4, 4, 3, 3, 3, 4, 3, 2, 2, 3, 3, 1, 0, 1, 2, 1, 3, 0, 3, 3, 0, 3
            , 5, 3, 2, 4, 5, 1, 3, 5, 1, 0, 1, 3, 5, 2, 2, 2, 0, 3, 1, 0, 3, 4, 1, 1, 0, 4, 3, 3, 1
            , 3, 1, 2, 2, 0, 1, 3, 1, 2, 3, 5, 0, 2, 0, 4, 3, 5, 1, 3, 4, 5, 0, 5, 3, 5, 3, 4, 2, 5
            , 4, 2, 1, 1, 0, 0, 2, 1, 1, 0, 3, 2, 1, 4, 1, 0, 0, 1, 2, 2, 3, 0, 1, 3, 2, 5, 5, 5, 0
            , 0, 3, 4, 1, 3, 3, 2, 3, 2, 4, 1, 4, 0, 4, 3, 0, 1);

    IDungeon dungeon = new Dungeon(6, 6, true, 0, 50, 10, "Sushith", rand);
    dungeon.beginGame();
    assertEquals("Player name: Sushith", dungeon.playerDescription().get(0));
  }

  @Test
  public void checkSmell() {
    rand = new MinRandomGenerator(2, 24, 30, 32, 24, 14, 38, 25, 60, 62, 20, 18, 20, 48, 50
            , 56, 46, 50, 29, 49, 4, 16, 33, 41, 11, 41, 21, 8, 40, 0, 30, 24, 14, 21, 25, 36, 33
            , 10, 16, 19, 7, 29, 28, 9, 2, 1, 6, 12, 16, 14, 1, 15, 16, 12, 12, 12, 0, 0, 2, 7, 7
            , 5, 0, 2, 0, 0, 0, 0, 3, 0, 0, 0, 3, 5, 3, 5, 4, 4, 0, 5, 1, 0, 0, 5, 0, 4, 3, 4, 5, 2
            , 0, 1, 5, 5, 4, 5, 3, 4, 2, 4, 5, 3, 4, 4, 5, 2, 0, 5, 2, 2, 2, 2, 1, 0, 5, 5, 4, 3, 1
            , 3, 2, 0, 4, 2, 4, 5, 3, 1, 4, 2, 4, 5, 0, 5, 0, 5, 0, 3, 2, 0, 4, 5, 5, 2, 4, 3, 5, 1
            , 1, 3, 2, 0, 3, 5, 3, 4, 4, 3, 3, 3, 4, 3, 2, 2, 3, 3, 1, 0, 1, 2, 1, 3, 0, 3, 3, 0, 3
            , 5, 3, 2, 4, 5, 1, 3, 5, 1, 0, 1, 3, 5, 2, 2, 2, 0, 3, 1, 0, 3, 4, 1, 1, 0, 4, 3, 3, 1
            , 3, 1, 2, 2, 0, 1, 3, 1, 2, 3, 5, 0, 2, 0, 4, 3, 5, 1, 3, 4, 5, 0, 5, 3, 5, 3, 4, 2, 5
            , 4, 2, 1, 1, 0, 0, 2, 1, 1, 0, 3, 2, 1, 4, 1, 0, 0, 1, 2, 2, 3, 0, 1, 3, 2, 5, 5, 5, 0
            , 0, 3, 4, 1, 3, 3, 2, 3, 2, 4, 1, 4, 0, 4, 3, 0, 1);

    IDungeon dungeon = new Dungeon(6, 6, true, 0, 50, 10, "Sushith", rand);
    dungeon.beginGame();
    assertEquals("You smell something terrible nearby.", dungeon.description().get(1));
    assertEquals(1, dungeon.getPlayerLocation().getMap().get(Direction.SOUTH)
            .otyughCount());
    dungeon.pickTreasure();
    dungeon.move(Direction.EAST);
    dungeon.move(Direction.EAST);
    dungeon.pickWeapon(2);
    dungeon.shootArrow(Direction.SOUTH, 1);
    dungeon.shootArrow(Direction.SOUTH, 1);
    dungeon.move(Direction.SOUTH);
    dungeon.shootArrow(Direction.EAST, 1);
    dungeon.shootArrow(Direction.EAST, 1);
    dungeon.move(Direction.EAST);
    assertEquals("You smell something mild nearby.", dungeon.description().get(0));
    assertEquals(1, dungeon.getPlayerLocation().getMap().get(Direction.EAST).getMap()
            .get(Direction.SOUTH).otyughCount());
    dungeon.pickWeapon(3);
    dungeon.move(Direction.NORTH);
    dungeon.pickTreasure();
    dungeon.move(Direction.EAST);
    dungeon.move(Direction.NORTH);
    dungeon.move(Direction.NORTH);
    assertEquals("You smell something terrible nearby.", dungeon.description().get(0));
    assertEquals(1, dungeon.getPlayerLocation().getMap().get(Direction.WEST).getMap()
            .get(Direction.WEST).otyughCount());
    assertEquals(1, dungeon.getPlayerLocation().getMap().get(Direction.WEST).getMap()
            .get(Direction.NORTH).otyughCount());
    dungeon.shootArrow(Direction.WEST, 1);
    dungeon.move(Direction.WEST);
    dungeon.pickTreasure();
    dungeon.shootArrow(Direction.NORTH, 1);
    dungeon.move(Direction.NORTH);
    assertEquals(dungeon.getPlayerLocation().getName(), dungeon.getEnd().getName());
  }

  @Test
  public void checkWrap() {
    rand = new MinRandomGenerator(2, 24, 30, 32, 24, 14, 38, 25, 60, 62, 20, 18, 20, 48, 50
            , 56, 46, 50, 29, 49, 4, 16, 33, 41, 11, 41, 21, 8, 40, 0, 30, 24, 14, 21, 25, 36, 33
            , 10, 16, 19, 7, 29, 28, 9, 2, 1, 6, 12, 16, 14, 1, 15, 16, 12, 12, 12, 0, 0, 2, 7, 7
            , 5, 0, 2, 0, 0, 0, 0, 3, 0, 0, 0, 3, 5, 3, 5, 4, 4, 0, 5, 1, 0, 0, 5, 0, 4, 3, 4, 5, 2
            , 0, 1, 5, 5, 4, 5, 3, 4, 2, 4, 5, 3, 4, 4, 5, 2, 0, 5, 2, 2, 2, 2, 1, 0, 5, 5, 4, 3, 1
            , 3, 2, 0, 4, 2, 4, 5, 3, 1, 4, 2, 4, 5, 0, 5, 0, 5, 0, 3, 2, 0, 4, 5, 5, 2, 4, 3, 5, 1
            , 1, 3, 2, 0, 3, 5, 3, 4, 4, 3, 3, 3, 4, 3, 2, 2, 3, 3, 1, 0, 1, 2, 1, 3, 0, 3, 3, 0, 3
            , 5, 3, 2, 4, 5, 1, 3, 5, 1, 0, 1, 3, 5, 2, 2, 2, 0, 3, 1, 0, 3, 4, 1, 1, 0, 4, 3, 3, 1
            , 3, 1, 2, 2, 0, 1, 3, 1, 2, 3, 5, 0, 2, 0, 4, 3, 5, 1, 3, 4, 5, 0, 5, 3, 5, 3, 4, 2, 5
            , 4, 2, 1, 1, 0, 0, 2, 1, 1, 0, 3, 2, 1, 4, 1, 0, 0, 1, 2, 2, 3, 0, 1, 3, 2, 5, 5, 5, 0
            , 0, 3, 4, 1, 3, 3, 2, 3, 2, 4, 1, 4, 0, 4, 3, 0, 1);

    IDungeon dungeon = new Dungeon(6, 6, true, 0, 50, 10, "Sushith", rand);
    dungeon.beginGame();
    dungeon.pickTreasure();
    dungeon.move(Direction.EAST);
    assertEquals(35, dungeon.getPlayerLocation().getName());
    assertEquals(30, dungeon.getPlayerLocation().getMap().get(Direction.EAST).getName());
    dungeon.move(Direction.EAST);
    assertEquals(30, dungeon.getPlayerLocation().getName());
    dungeon.pickWeapon(2);
    dungeon.shootArrow(Direction.SOUTH, 1);
    dungeon.shootArrow(Direction.SOUTH, 1);
    assertEquals(0, dungeon.getPlayerLocation().getMap().get(Direction.SOUTH).getName());
    dungeon.move(Direction.SOUTH);
    assertEquals(0, dungeon.getPlayerLocation().getName());
    dungeon.shootArrow(Direction.EAST, 1);
    dungeon.shootArrow(Direction.EAST, 1);
    dungeon.move(Direction.EAST);
    dungeon.pickWeapon(3);
    dungeon.move(Direction.NORTH);
    dungeon.pickTreasure();
    dungeon.move(Direction.EAST);
    dungeon.move(Direction.NORTH);
    dungeon.move(Direction.NORTH);
    dungeon.shootArrow(Direction.WEST, 1);
    dungeon.move(Direction.WEST);
    dungeon.pickTreasure();
    dungeon.shootArrow(Direction.NORTH, 1);
    dungeon.move(Direction.NORTH);
  }

  @Test
  public void arrowAndTreasureDistributedCorrectly() {
    rand = new MinRandomGenerator(22, 14, 17, 15, 13, 1, 0, 9, 15, 12, 12, 5, 4, 8, 6, 7, 6, 4
            , 2, 4, 3, 0, 0, 0, 0, 1, 5, 1, 1, 3, 1, 2, 1, 4, 1, 1, 1, 1, 1, 2, 2, 5, 4, 1, 0, 1, 2
            , 2, 3, 1, 0, 2, 2, 3, 1, 1, 2, 2, 0, 2, 1, 3, 0, 2, 3, 1, 2, 2, 1, 2, 1, 2, 2, 0, 3, 2
            , 2, 1, 1, 3, 3, 2, 2, 1, 0, 3, 3, 0, 0, 0, 2, 1, 2, 2, 2, 3, 3, 2, 0, 0, 2, 1, 1, 3, 2
            , 2, 2, 2, 2, 0, 1, 3, 3, 3, 3, 0, 0, 1, 0, 0, 0, 1, 3, 0, 2, 3, 1, 2, 2, 0, 3, 0, 1, 3
            , 2, 3, 1, 0, 2, 1, 1, 0, 0, 0, 0, 3, 3, 3, 0, 0, 1, 3, 1, 0, 3, 3, 3, 0, 1, 0, 2, 2, 3
            , 3, 1, 1, 0, 2, 0, 2, 1, 0, 2, 0, 3, 3, 3, 1, 2, 0, 1, 1, 0, 2, 0, 3, 3, 0, 3, 3, 2, 3
            , 0, 1, 0, 2, 1, 2, 1, 2, 1, 0, 2, 0, 3, 3, 0, 3, 1, 3, 2, 1, 3, 1, 1, 2, 2, 3, 1, 3, 1
            , 1, 3, 0, 0, 3, 3, 0, 1, 1, 0, 2, 1, 1, 1, 3, 0, 0, 2, 2, 3, 0, 3, 2, 3, 2, 1, 0, 2, 1
            , 1, 1, 3, 0, 2, 2, 3, 1, 2);
    IDungeon dungeon = new Dungeon(4, 4, false, 2, 50, 5, "Sushith", rand);
    Set<Integer> caves = new HashSet<>();
    int arrowAvailability = 0;
    int treasureAvailability = 0;
    int treasureCaveCount = 0;
    Set<Integer> arrows = new HashSet<>();
    for (int i = 0; i < 16; i++) {
      arrows.add(i);
    }
    dungeon.beginGame();
    if (dungeon.getPlayerLocation().typeOfLocation() == LocationType.CAVE) {
      caves.add(dungeon.getPlayerLocation().getName());
    }
    assertEquals("CAVE", dungeon.getPlayerLocation().typeOfLocation().toString());
    dungeon.pickWeapon(2);
    arrowAvailability++;
    dungeon.move(Direction.NORTH);
    if (dungeon.getPlayerLocation().typeOfLocation() == LocationType.CAVE) {
      caves.add(dungeon.getPlayerLocation().getName());
    }
    dungeon.shootArrow(Direction.EAST, 1);
    dungeon.shootArrow(Direction.EAST, 1);
    dungeon.move(Direction.EAST);
    assertEquals("CAVE", dungeon.getPlayerLocation().typeOfLocation().toString());
    dungeon.pickWeapon(2);
    arrowAvailability++;
    dungeon.pickTreasure();
    treasureAvailability++;
    if (dungeon.getPlayerLocation().typeOfLocation() == LocationType.CAVE) {
      treasureCaveCount++;
    }
    dungeon.move(Direction.NORTH);
    if (dungeon.getPlayerLocation().typeOfLocation() == LocationType.CAVE) {
      caves.add(dungeon.getPlayerLocation().getName());
    }
    dungeon.move(Direction.WEST);
    if (dungeon.getPlayerLocation().typeOfLocation() == LocationType.CAVE) {
      caves.add(dungeon.getPlayerLocation().getName());
    }
    dungeon.move(Direction.NORTH);
    if (dungeon.getPlayerLocation().typeOfLocation() == LocationType.CAVE) {
      caves.add(dungeon.getPlayerLocation().getName());
    }
    assertEquals("TUNNEL", dungeon.getPlayerLocation().typeOfLocation().toString());
    dungeon.pickWeapon(2);
    arrowAvailability++;
    dungeon.move(Direction.EAST);
    if (dungeon.getPlayerLocation().typeOfLocation() == LocationType.CAVE) {
      caves.add(dungeon.getPlayerLocation().getName());
    }
    dungeon.pickTreasure();
    treasureAvailability++;
    if (dungeon.getPlayerLocation().typeOfLocation() == LocationType.CAVE) {
      treasureCaveCount++;
    }
    dungeon.move(Direction.WEST);
    if (dungeon.getPlayerLocation().typeOfLocation() == LocationType.CAVE) {
      caves.add(dungeon.getPlayerLocation().getName());
    }
    dungeon.move(Direction.SOUTH);
    if (dungeon.getPlayerLocation().typeOfLocation() == LocationType.CAVE) {
      caves.add(dungeon.getPlayerLocation().getName());
    }
    dungeon.move(Direction.EAST);
    if (dungeon.getPlayerLocation().typeOfLocation() == LocationType.CAVE) {
      caves.add(dungeon.getPlayerLocation().getName());
    }
    dungeon.move(Direction.SOUTH);
    if (dungeon.getPlayerLocation().typeOfLocation() == LocationType.CAVE) {
      caves.add(dungeon.getPlayerLocation().getName());
    }
    dungeon.move(Direction.SOUTH);
    if (dungeon.getPlayerLocation().typeOfLocation() == LocationType.CAVE) {
      caves.add(dungeon.getPlayerLocation().getName());
    }
    assertEquals("TUNNEL", dungeon.getPlayerLocation().typeOfLocation().toString());
    dungeon.pickWeapon(2);
    arrowAvailability++;
    dungeon.move(Direction.EAST);
    if (dungeon.getPlayerLocation().typeOfLocation() == LocationType.CAVE) {
      caves.add(dungeon.getPlayerLocation().getName());
    }
    dungeon.move(Direction.NORTH);
    if (dungeon.getPlayerLocation().typeOfLocation() == LocationType.CAVE) {
      caves.add(dungeon.getPlayerLocation().getName());
    }
    assertEquals("CAVE", dungeon.getPlayerLocation().typeOfLocation().toString());
    dungeon.pickWeapon(1);
    arrowAvailability++;
    dungeon.pickTreasure();
    treasureAvailability++;
    if (dungeon.getPlayerLocation().typeOfLocation() == LocationType.CAVE) {
      treasureCaveCount++;
    }
    dungeon.shootArrow(Direction.EAST, 1);
    dungeon.shootArrow(Direction.EAST, 1);
    dungeon.move(Direction.EAST);
    if (dungeon.getPlayerLocation().typeOfLocation() == LocationType.CAVE) {
      caves.add(dungeon.getPlayerLocation().getName());
    }
    dungeon.pickTreasure();
    treasureAvailability++;
    if (dungeon.getPlayerLocation().typeOfLocation() == LocationType.CAVE) {
      treasureCaveCount++;
    }
    dungeon.shootArrow(Direction.NORTH, 1);
    dungeon.shootArrow(Direction.NORTH, 1);
    dungeon.move(Direction.NORTH);
    if (dungeon.getPlayerLocation().typeOfLocation() == LocationType.CAVE) {
      caves.add(dungeon.getPlayerLocation().getName());
    }
    assertEquals("CAVE", dungeon.getPlayerLocation().typeOfLocation().toString());
    dungeon.pickWeapon(3);
    arrowAvailability++;
    dungeon.shootArrow(Direction.WEST, 1);
    dungeon.shootArrow(Direction.WEST, 1);
    dungeon.move(Direction.WEST);
    if (dungeon.getPlayerLocation().typeOfLocation() == LocationType.CAVE) {
      caves.add(dungeon.getPlayerLocation().getName());
    }
    assertEquals("CAVE", dungeon.getPlayerLocation().typeOfLocation().toString());
    dungeon.pickWeapon(2);
    arrowAvailability++;
    dungeon.pickTreasure();
    treasureAvailability++;
    if (dungeon.getPlayerLocation().typeOfLocation() == LocationType.CAVE) {
      treasureCaveCount++;
    }
    dungeon.move(Direction.EAST);
    if (dungeon.getPlayerLocation().typeOfLocation() == LocationType.CAVE) {
      caves.add(dungeon.getPlayerLocation().getName());
    }
    dungeon.move(Direction.NORTH);
    if (dungeon.getPlayerLocation().typeOfLocation() == LocationType.CAVE) {
      caves.add(dungeon.getPlayerLocation().getName());
    }
    assertEquals("TUNNEL", dungeon.getPlayerLocation().typeOfLocation().toString());
    dungeon.pickWeapon(2);
    arrowAvailability++;
    dungeon.shootArrow(Direction.WEST, 1);
    dungeon.shootArrow(Direction.WEST, 1);
    dungeon.move(Direction.WEST);
    if (dungeon.getPlayerLocation().typeOfLocation() == LocationType.CAVE) {
      caves.add(dungeon.getPlayerLocation().getName());
    }
    int locationsCount = (int) Math.round(arrows.size() * 0.01 * 50);
    int cavesCount = (int) Math.round(caves.size() * 0.01 * 50);
    assertEquals(locationsCount, arrowAvailability);
    assertEquals(cavesCount, treasureAvailability);
    assertEquals(cavesCount, treasureCaveCount);
  }

  @Test
  public void crookedArrows() {
    rand = new MinRandomGenerator(21, 29, 11, 28, 27, 25, 2, 4, 4, 20, 12, 20, 7, 9, 6, 2, 10
            , 3, 11, 6, 6, 0, 9, 8, 3, 5, 1, 4, 1, 2, 1, 0, 0, 0, 4, 2, 0, 0, 1, 3, 0, 2, 1, 0, 3
            , 2, 2, 1, 3, 3, 1, 1, 2, 2, 4, 1, 0, 1, 1, 0, 4, 4, 3, 2, 3, 1, 2, 0, 2, 1, 0, 3, 0, 3
            , 2, 0, 2, 3, 2, 1, 1, 0, 2, 2, 2, 2, 3, 3, 3, 3, 2, 0, 1, 0, 2, 0, 2, 3, 2, 0, 0, 1, 3
            , 0, 1, 3, 1, 2, 2, 2, 3, 1, 1, 3, 0, 0, 1, 1, 0, 2, 3, 0, 3, 2, 2, 2, 0, 0, 2, 0, 2, 3
            , 2, 3, 3, 1, 0, 2, 3, 2, 2, 2, 2, 3, 0, 0, 0, 1, 2, 2, 3, 3, 1, 0, 3, 1, 2, 3, 2, 3, 1
            , 2, 2, 2, 3, 3, 3, 1, 3, 0, 1, 3, 0, 0, 2, 0, 1, 0, 0, 2, 3, 1, 1, 0, 2);

    IDungeon dungeon = new Dungeon(4, 4, true, 2, 50, 3, "Sushith", rand);
    dungeon.beginGame();
    dungeon.pickTreasure();
    dungeon.move(Direction.NORTH);
    dungeon.pickTreasure();
    dungeon.move(Direction.WEST);
    dungeon.pickWeapon(3);
    dungeon.move(Direction.SOUTH);
    dungeon.move(Direction.SOUTH);
    dungeon.shootArrow(Direction.WEST, 2);
    assertEquals("CAVE", dungeon.getPlayerLocation().getMap().get(Direction.WEST)
            .typeOfLocation().toString());
    assertEquals("You shoot an arrow.\n" + "You hear a little howl in the distance.\n"
            + "You have 5 left now.", dungeon.description().get(0));
    dungeon.shootArrow(Direction.WEST, 2);
    assertEquals("CAVE", dungeon.getPlayerLocation().getMap().get(Direction.WEST)
            .getMap().get(Direction.WEST).typeOfLocation().toString());
    assertEquals("You shoot an arrow.\n" + "You hear a great howl in the distance.\n"
            + "You have 4 left now.", dungeon.description().get(0));
    dungeon.move(Direction.WEST);
    dungeon.pickTreasure();
    dungeon.move(Direction.WEST);
    dungeon.shootArrow(Direction.NORTH, 1);
    assertEquals("TUNNEL", dungeon.getPlayerLocation().getMap().get(Direction.NORTH)
            .typeOfLocation().toString());
    assertEquals("You shoot an arrow.\n" + "You hear a little howl in the distance.\n"
            + "You have 3 left now.", dungeon.description().get(0));
    dungeon.shootArrow(Direction.NORTH, 1);
    assertEquals("CAVE", dungeon.getPlayerLocation().getMap().get(Direction.NORTH)
            .getMap().get(Direction.EAST).typeOfLocation().toString());
    assertEquals("You shoot an arrow.\n" + "You hear a great howl in the distance.\n"
            + "You have 2 left now.", dungeon.description().get(0));
    dungeon.move(Direction.SOUTH);
    dungeon.pickTreasure();
    dungeon.shootArrow(Direction.WEST, 1);
    assertEquals("TUNNEL", dungeon.getPlayerLocation().getMap().get(Direction.WEST)
            .typeOfLocation().toString());
    assertEquals("You shoot an arrow.\n" + "You hear a little howl in the distance.\n"
            + "You have 1 left now.", dungeon.description().get(0));
    dungeon.shootArrow(Direction.WEST, 1);
    assertEquals("CAVE", dungeon.getPlayerLocation().getMap().get(Direction.WEST)
            .getMap().get(Direction.WEST).typeOfLocation().toString());
    assertEquals("You shoot an arrow.\n" + "You hear a great howl in the distance.\n"
            + "You have 0 left now.", dungeon.description().get(0));
    dungeon.move(Direction.WEST);
    dungeon.move(Direction.WEST);
    assertTrue(dungeon.isGameOver());
  }
}