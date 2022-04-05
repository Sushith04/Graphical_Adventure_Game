import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import controller.DungeonController;
import controller.IDungeonController;
import model.Dungeon;
import model.IDungeon;
import model.MinRandomGenerator;
import model.RandomNumberGenerator;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the dungeon controller, using mocks for readable and appendable.
 */
public class ControllerTest {

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidModel() throws IOException {
    Readable input = new StringReader("G T Y");
    Appendable gameLog = new StringBuilder();
    IDungeonController c = new DungeonController(input, gameLog);
    c.playGame(null);
  }

  @Test
  public void testValidMove() throws IOException {
    RandomNumberGenerator rand = new MinRandomGenerator(65, 35, 31, 56, 63, 5, 50, 34, 20, 50
            , 59, 16, 6, 41, 21, 1, 25, 45, 51, 3, 16, 49, 24, 6, 5, 16, 6, 6, 41, 15, 3, 38, 34, 5
            , 14, 15, 29, 32, 0, 31, 14, 8, 3, 14, 16, 5, 4, 9, 13, 16, 21, 2, 9, 8, 15, 4, 14, 4
            , 3, 5, 2, 4, 8, 8, 1, 0, 4, 2, 3, 2, 1, 0, 1, 2, 1, 3, 5, 0, 5, 5, 4, 3, 4, 5, 4, 0, 3
            , 1, 4, 5, 1, 1, 5, 3, 1, 0, 2, 1, 3, 4, 2, 4, 4, 3, 3, 1, 5, 4, 2, 0, 3, 4, 5, 3, 1, 1
            , 1, 1, 5, 5, 5, 0, 1, 4, 3, 5, 2, 0, 5, 1, 2, 0, 0, 5, 5, 4, 0, 1, 0, 3, 2, 1, 3, 3, 5
            , 2, 1, 0, 1, 0, 1, 3, 2, 2, 3, 2, 4, 2, 0, 3, 2, 5, 2, 1, 0, 5, 2, 4, 2, 2, 3, 1, 2, 0
            , 0, 2, 0, 0, 4, 2, 2, 0, 2, 3, 2, 1, 2, 2, 5, 5, 1, 1, 2, 2, 3, 0, 3, 1, 0, 0, 2, 1, 0
            , 4, 5, 4, 5, 1, 2, 0, 3, 5, 0, 0, 2, 0, 5, 3, 1, 1, 3, 0, 5, 5, 2, 4, 0, 5, 5, 4, 3, 4
            , 2, 5, 0, 4, 1, 5);
    IDungeon dungeon = new Dungeon(6, 6, true, 1, 50, 8, "Sushith", rand);
    Readable input = new StringReader("M S Q");
    Appendable gameLog = new StringBuilder();
    IDungeonController c = new DungeonController(input, gameLog);
    c.playGame(dungeon);
    assertEquals("move SOUTH", DungeonMock.MOCKSTRING);
  }

  @Test
  public void testValidPickTreasure() throws IOException {
    RandomNumberGenerator rand = new MinRandomGenerator(65, 35, 31, 56, 63, 5, 50, 34, 20, 50
            , 59, 16, 6, 41, 21, 1, 25, 45, 51, 3, 16, 49, 24, 6, 5, 16, 6, 6, 41, 15, 3, 38, 34, 5
            , 14, 15, 29, 32, 0, 31, 14, 8, 3, 14, 16, 5, 4, 9, 13, 16, 21, 2, 9, 8, 15, 4, 14, 4
            , 3, 5, 2, 4, 8, 8, 1, 0, 4, 2, 3, 2, 1, 0, 1, 2, 1, 3, 5, 0, 5, 5, 4, 3, 4, 5, 4, 0, 3
            , 1, 4, 5, 1, 1, 5, 3, 1, 0, 2, 1, 3, 4, 2, 4, 4, 3, 3, 1, 5, 4, 2, 0, 3, 4, 5, 3, 1, 1
            , 1, 1, 5, 5, 5, 0, 1, 4, 3, 5, 2, 0, 5, 1, 2, 0, 0, 5, 5, 4, 0, 1, 0, 3, 2, 1, 3, 3, 5
            , 2, 1, 0, 1, 0, 1, 3, 2, 2, 3, 2, 4, 2, 0, 3, 2, 5, 2, 1, 0, 5, 2, 4, 2, 2, 3, 1, 2, 0
            , 0, 2, 0, 0, 4, 2, 2, 0, 2, 3, 2, 1, 2, 2, 5, 5, 1, 1, 2, 2, 3, 0, 3, 1, 0, 0, 2, 1, 0
            , 4, 5, 4, 5, 1, 2, 0, 3, 5, 0, 0, 2, 0, 5, 3, 1, 1, 3, 0, 5, 5, 2, 4, 0, 5, 5, 4, 3, 4
            , 2, 5, 0, 4, 1, 5);
    IDungeon dungeon = new Dungeon(6, 6, true, 1, 50, 8, "Sushith", rand);
    Readable input = new StringReader("P T Q");
    Appendable gameLog = new StringBuilder();
    IDungeonController c = new DungeonController(input, gameLog);
    c.playGame(dungeon);
    assertEquals("pickTreasure", DungeonMock.MOCKSTRING);
  }

  @Test
  public void testValidPickArrows() throws IOException {
    RandomNumberGenerator rand = new MinRandomGenerator(65, 35, 31, 56, 63, 5, 50, 34, 20, 50
            , 59, 16, 6, 41, 21, 1, 25, 45, 51, 3, 16, 49, 24, 6, 5, 16, 6, 6, 41, 15, 3, 38, 34, 5
            , 14, 15, 29, 32, 0, 31, 14, 8, 3, 14, 16, 5, 4, 9, 13, 16, 21, 2, 9, 8, 15, 4, 14, 4
            , 3, 5, 2, 4, 8, 8, 1, 0, 4, 2, 3, 2, 1, 0, 1, 2, 1, 3, 5, 0, 5, 5, 4, 3, 4, 5, 4, 0, 3
            , 1, 4, 5, 1, 1, 5, 3, 1, 0, 2, 1, 3, 4, 2, 4, 4, 3, 3, 1, 5, 4, 2, 0, 3, 4, 5, 3, 1, 1
            , 1, 1, 5, 5, 5, 0, 1, 4, 3, 5, 2, 0, 5, 1, 2, 0, 0, 5, 5, 4, 0, 1, 0, 3, 2, 1, 3, 3, 5
            , 2, 1, 0, 1, 0, 1, 3, 2, 2, 3, 2, 4, 2, 0, 3, 2, 5, 2, 1, 0, 5, 2, 4, 2, 2, 3, 1, 2, 0
            , 0, 2, 0, 0, 4, 2, 2, 0, 2, 3, 2, 1, 2, 2, 5, 5, 1, 1, 2, 2, 3, 0, 3, 1, 0, 0, 2, 1, 0
            , 4, 5, 4, 5, 1, 2, 0, 3, 5, 0, 0, 2, 0, 5, 3, 1, 1, 3, 0, 5, 5, 2, 4, 0, 5, 5, 4, 3, 4
            , 2, 5, 0, 4, 1, 5);
    IDungeon dungeon = new Dungeon(6, 6, true, 1, 50, 8, "Sushith", rand);
    Readable input = new StringReader("P A 2 Q");
    Appendable gameLog = new StringBuilder();
    IDungeonController c = new DungeonController(input, gameLog);
    c.playGame(dungeon);
    assertEquals("pickWeapon 2", DungeonMock.MOCKSTRING);
  }

  @Test
  public void testValidShoot() throws IOException {
    RandomNumberGenerator rand = new MinRandomGenerator(65, 35, 31, 56, 63, 5, 50, 34, 20, 50
            , 59, 16, 6, 41, 21, 1, 25, 45, 51, 3, 16, 49, 24, 6, 5, 16, 6, 6, 41, 15, 3, 38, 34, 5
            , 14, 15, 29, 32, 0, 31, 14, 8, 3, 14, 16, 5, 4, 9, 13, 16, 21, 2, 9, 8, 15, 4, 14, 4
            , 3, 5, 2, 4, 8, 8, 1, 0, 4, 2, 3, 2, 1, 0, 1, 2, 1, 3, 5, 0, 5, 5, 4, 3, 4, 5, 4, 0, 3
            , 1, 4, 5, 1, 1, 5, 3, 1, 0, 2, 1, 3, 4, 2, 4, 4, 3, 3, 1, 5, 4, 2, 0, 3, 4, 5, 3, 1, 1
            , 1, 1, 5, 5, 5, 0, 1, 4, 3, 5, 2, 0, 5, 1, 2, 0, 0, 5, 5, 4, 0, 1, 0, 3, 2, 1, 3, 3, 5
            , 2, 1, 0, 1, 0, 1, 3, 2, 2, 3, 2, 4, 2, 0, 3, 2, 5, 2, 1, 0, 5, 2, 4, 2, 2, 3, 1, 2, 0
            , 0, 2, 0, 0, 4, 2, 2, 0, 2, 3, 2, 1, 2, 2, 5, 5, 1, 1, 2, 2, 3, 0, 3, 1, 0, 0, 2, 1, 0
            , 4, 5, 4, 5, 1, 2, 0, 3, 5, 0, 0, 2, 0, 5, 3, 1, 1, 3, 0, 5, 5, 2, 4, 0, 5, 5, 4, 3, 4
            , 2, 5, 0, 4, 1, 5);
    IDungeon dungeon = new Dungeon(6, 6, true, 1, 50, 8, "Sushith", rand);
    Readable input = new StringReader("S 5 W Q");
    Appendable gameLog = new StringBuilder();
    IDungeonController c = new DungeonController(input, gameLog);
    c.playGame(dungeon);
    assertEquals("shootArrow WEST 5", DungeonMock.MOCKSTRING);
  }

  @Test
  public void testInvalidCommandInput() throws IOException {
    RandomNumberGenerator rand = new MinRandomGenerator(65, 35, 31, 56, 63, 5, 50, 34, 20, 50
            , 59, 16, 6, 41, 21, 1, 25, 45, 51, 3, 16, 49, 24, 6, 5, 16, 6, 6, 41, 15, 3, 38, 34, 5
            , 14, 15, 29, 32, 0, 31, 14, 8, 3, 14, 16, 5, 4, 9, 13, 16, 21, 2, 9, 8, 15, 4, 14, 4
            , 3, 5, 2, 4, 8, 8, 1, 0, 4, 2, 3, 2, 1, 0, 1, 2, 1, 3, 5, 0, 5, 5, 4, 3, 4, 5, 4, 0, 3
            , 1, 4, 5, 1, 1, 5, 3, 1, 0, 2, 1, 3, 4, 2, 4, 4, 3, 3, 1, 5, 4, 2, 0, 3, 4, 5, 3, 1, 1
            , 1, 1, 5, 5, 5, 0, 1, 4, 3, 5, 2, 0, 5, 1, 2, 0, 0, 5, 5, 4, 0, 1, 0, 3, 2, 1, 3, 3, 5
            , 2, 1, 0, 1, 0, 1, 3, 2, 2, 3, 2, 4, 2, 0, 3, 2, 5, 2, 1, 0, 5, 2, 4, 2, 2, 3, 1, 2, 0
            , 0, 2, 0, 0, 4, 2, 2, 0, 2, 3, 2, 1, 2, 2, 5, 5, 1, 1, 2, 2, 3, 0, 3, 1, 0, 0, 2, 1, 0
            , 4, 5, 4, 5, 1, 2, 0, 3, 5, 0, 0, 2, 0, 5, 3, 1, 1, 3, 0, 5, 5, 2, 4, 0, 5, 5, 4, 3, 4
            , 2, 5, 0, 4, 1, 5);
    IDungeon dungeon = new Dungeon(6, 6, true, 1, 50, 8, "Sushith", rand);
    Readable input = new StringReader("J 1 - M E Q");
    Appendable gameLog = new StringBuilder();
    IDungeonController c = new DungeonController(input, gameLog);
    c.playGame(dungeon);
    String expectedValue = "Player name: Sushith.\n"
            + "You are in a Cave and its doors lead to: North West East\n"
            + "You find -> Sapphires: 5, Diamonds: 5\n" + "You find 2 arrows here.\n"
            + "You smell something terrible nearby.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Unknown command J\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Unknown command 1\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Unknown command -\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Where to (N-S-E-W)? "
            + "You are in a Cave and its doors lead to: North South West \n"
            + "You find -> Rubies: 3, Sapphires: 2, Diamonds: 4\n"
            + "You find 3 arrows here.\n"
            + "You smell something mild nearby.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Game quit! Ending game...";
    assertEquals(expectedValue, gameLog.toString());
  }

  @Test
  public void testNullTreasurePickup() throws IOException {
    RandomNumberGenerator rand = new MinRandomGenerator(65, 35, 31, 56, 63, 5, 50, 34, 20, 50
            , 59, 16, 6, 41, 21, 1, 25, 45, 51, 3, 16, 49, 24, 6, 5, 16, 6, 6, 41, 15, 3, 38, 34, 5
            , 14, 15, 29, 32, 0, 31, 14, 8, 3, 14, 16, 5, 4, 9, 13, 16, 21, 2, 9, 8, 15, 4, 14, 4
            , 3, 5, 2, 4, 8, 8, 1, 0, 4, 2, 3, 2, 1, 0, 1, 2, 1, 3, 5, 0, 5, 5, 4, 3, 4, 5, 4, 0, 3
            , 1, 4, 5, 1, 1, 5, 3, 1, 0, 2, 1, 3, 4, 2, 4, 4, 3, 3, 1, 5, 4, 2, 0, 3, 4, 5, 3, 1, 1
            , 1, 1, 5, 5, 5, 0, 1, 4, 3, 5, 2, 0, 5, 1, 2, 0, 0, 5, 5, 4, 0, 1, 0, 3, 2, 1, 3, 3, 5
            , 2, 1, 0, 1, 0, 1, 3, 2, 2, 3, 2, 4, 2, 0, 3, 2, 5, 2, 1, 0, 5, 2, 4, 2, 2, 3, 1, 2, 0
            , 0, 2, 0, 0, 4, 2, 2, 0, 2, 3, 2, 1, 2, 2, 5, 5, 1, 1, 2, 2, 3, 0, 3, 1, 0, 0, 2, 1, 0
            , 4, 5, 4, 5, 1, 2, 0, 3, 5, 0, 0, 2, 0, 5, 3, 1, 1, 3, 0, 5, 5, 2, 4, 0, 5, 5, 4, 3, 4
            , 2, 5, 0, 4, 1, 5);
    IDungeon dungeon = new Dungeon(6, 6, true, 1, 50, 8, "Sushith", rand);
    Readable input = new StringReader("M N P T P A 2 Q");
    Appendable gameLog = new StringBuilder();
    IDungeonController c = new DungeonController(input, gameLog);
    c.playGame(dungeon);
    String expectedValue = "Player name: Sushith.\n"
            + "You are in a Cave and its doors lead to: North West East\n"
            + "You find -> Sapphires: 5, Diamonds: 5\n"
            + "You find 2 arrows here.\n"
            + "You smell something terrible nearby.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Where to (N-S-E-W)?"
            + " You are in a Cave and its doors lead to: North South West \n"
            + "You find 1 arrows here.\n"
            + "You smell something terrible nearby.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?What (T-A)?"
            + " There should be at least one treasure to be picked up.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?What (T-A)?"
            + " How many? You have 1 arrows only at this location.\n"
            + "1 arrows have been picked up.\n"
            + "You have 4 arrows with you now.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Game quit! Ending game...";
    assertEquals(expectedValue, gameLog.toString());
  }

  @Test
  public void testZeroArrowPickup() throws IOException {
    RandomNumberGenerator rand = new MinRandomGenerator(65, 35, 31, 56, 63, 5, 50, 34, 20, 50
            , 59, 16, 6, 41, 21, 1, 25, 45, 51, 3, 16, 49, 24, 6, 5, 16, 6, 6, 41, 15, 3, 38, 34, 5
            , 14, 15, 29, 32, 0, 31, 14, 8, 3, 14, 16, 5, 4, 9, 13, 16, 21, 2, 9, 8, 15, 4, 14, 4
            , 3, 5, 2, 4, 8, 8, 1, 0, 4, 2, 3, 2, 1, 0, 1, 2, 1, 3, 5, 0, 5, 5, 4, 3, 4, 5, 4, 0, 3
            , 1, 4, 5, 1, 1, 5, 3, 1, 0, 2, 1, 3, 4, 2, 4, 4, 3, 3, 1, 5, 4, 2, 0, 3, 4, 5, 3, 1, 1
            , 1, 1, 5, 5, 5, 0, 1, 4, 3, 5, 2, 0, 5, 1, 2, 0, 0, 5, 5, 4, 0, 1, 0, 3, 2, 1, 3, 3, 5
            , 2, 1, 0, 1, 0, 1, 3, 2, 2, 3, 2, 4, 2, 0, 3, 2, 5, 2, 1, 0, 5, 2, 4, 2, 2, 3, 1, 2, 0
            , 0, 2, 0, 0, 4, 2, 2, 0, 2, 3, 2, 1, 2, 2, 5, 5, 1, 1, 2, 2, 3, 0, 3, 1, 0, 0, 2, 1, 0
            , 4, 5, 4, 5, 1, 2, 0, 3, 5, 0, 0, 2, 0, 5, 3, 1, 1, 3, 0, 5, 5, 2, 4, 0, 5, 5, 4, 3, 4
            , 2, 5, 0, 4, 1, 5);
    IDungeon dungeon = new Dungeon(6, 6, true, 1, 50, 8, "Sushith", rand);
    Readable input = new StringReader("P A 2 M E P A 3 P T M N P A 3 M N Q");
    Appendable gameLog = new StringBuilder();
    IDungeonController c = new DungeonController(input, gameLog);
    c.playGame(dungeon);
    String expectedValue = "Player name: Sushith.\n"
            + "You are in a Cave and its doors lead to: North West East\n"
            + "You find -> Sapphires: 5, Diamonds: 5\n"
            + "You find 2 arrows here.\n"
            + "You smell something terrible nearby.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?What (T-A)? How many?"
            + " 2 arrows have been picked up.\n"
            + "You have 5 arrows with you now.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Where to (N-S-E-W)?"
            + " You are in a Cave and its doors lead to: North South West \n"
            + "You find -> Rubies: 3, Sapphires: 2, Diamonds: 4\n"
            + "You find 3 arrows here.\n"
            + "You smell something mild nearby.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?What (T-A)? How many?"
            + " 3 arrows have been picked up.\n"
            + "You have 8 arrows with you now.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?What (T-A)?"
            + " You pick up -> Rubies: 3, Sapphires: 2, Diamonds: 4\n"
            + "Treasures picked up-> Rubies: 3, Sapphires: 2, Diamonds: 4\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Where to (N-S-E-W)?"
            + " You are in a Tunnel that continues to: South East\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?What (T-A)? How many?"
            + " There are no arrows here.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Where to (N-S-E-W)?"
            + " There is no path to NORTH from this location.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Game quit! Ending game...";
    assertEquals(expectedValue, gameLog.toString());
  }

  @Test
  public void testPlayerWin() throws IOException {
    RandomNumberGenerator rand = new MinRandomGenerator(65, 35, 31, 56, 63, 5, 50, 34, 20, 50
            , 59, 16, 6, 41, 21, 1, 25, 45, 51, 3, 16, 49, 24, 6, 5, 16, 6, 6, 41, 15, 3, 38, 34, 5
            , 14, 15, 29, 32, 0, 31, 14, 8, 3, 14, 16, 5, 4, 9, 13, 16, 21, 2, 9, 8, 15, 4, 14, 4
            , 3, 5, 2, 4, 8, 8, 1, 0, 4, 2, 3, 2, 1, 0, 1, 2, 1, 3, 5, 0, 5, 5, 4, 3, 4, 5, 4, 0, 3
            , 1, 4, 5, 1, 1, 5, 3, 1, 0, 2, 1, 3, 4, 2, 4, 4, 3, 3, 1, 5, 4, 2, 0, 3, 4, 5, 3, 1, 1
            , 1, 1, 5, 5, 5, 0, 1, 4, 3, 5, 2, 0, 5, 1, 2, 0, 0, 5, 5, 4, 0, 1, 0, 3, 2, 1, 3, 3, 5
            , 2, 1, 0, 1, 0, 1, 3, 2, 2, 3, 2, 4, 2, 0, 3, 2, 5, 2, 1, 0, 5, 2, 4, 2, 2, 3, 1, 2, 0
            , 0, 2, 0, 0, 4, 2, 2, 0, 2, 3, 2, 1, 2, 2, 5, 5, 1, 1, 2, 2, 3, 0, 3, 1, 0, 0, 2, 1, 0
            , 4, 5, 4, 5, 1, 2, 0, 3, 5, 0, 0, 2, 0, 5, 3, 1, 1, 3, 0, 5, 5, 2, 4, 0, 5, 5, 4, 3, 4
            , 2, 5, 0, 4, 1, 5);
    IDungeon dungeon = new Dungeon(6, 6, true, 1, 50, 8, "Sushith", rand);
    Readable input = new StringReader("M E P A 3 P T M N M E P A 2 M N M E M S M E M N S 1 N S"
            + " 1 N M N S 1 W S 1 W M W");
    Appendable gameLog = new StringBuilder();
    IDungeonController c = new DungeonController(input, gameLog);
    c.playGame(dungeon);
    String expectedValue = "Player name: Sushith.\n"
            + "You are in a Cave and its doors lead to: North West East\n"
            + "You find -> Sapphires: 5, Diamonds: 5\n"
            + "You find 2 arrows here.\n"
            + "You smell something terrible nearby.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Where to (N-S-E-W)?"
            + " You are in a Cave and its doors lead to: North South West \n"
            + "You find -> Rubies: 3, Sapphires: 2, Diamonds: 4\n"
            + "You find 3 arrows here.\n"
            + "You smell something mild nearby.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?What (T-A)?"
            + " How many? 3 arrows have been picked up.\n"
            + "You have 6 arrows with you now.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?What (T-A)?"
            + " You pick up -> Rubies: 3, Sapphires: 2, Diamonds: 4\n"
            + "Treasures picked up-> Rubies: 3, Sapphires: 2, Diamonds: 4\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Where to (N-S-E-W)?"
            + " You are in a Tunnel that continues to: South East\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Where to (N-S-E-W)?"
            + " You are in a Tunnel that continues to: North West \n"
            + "You find 2 arrows here.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?What (T-A)?"
            + " How many? 2 arrows have been picked up.\n"
            + "You have 8 arrows with you now.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Where to (N-S-E-W)?"
            + " You are in a Cave and its doors lead to: North South East\n"
            + "You find 1 arrows here.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Where to (N-S-E-W)?"
            + " You are in a Tunnel that continues to: South West \n"
            + "You find 2 arrows here.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Where to (N-S-E-W)?"
            + " You are in a Tunnel that continues to: North East\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Where to (N-S-E-W)?"
            + " You are in a Tunnel that continues to: North West \n"
            + "You smell something mild nearby.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Where to (N-S-E-W)?"
            + " You are in a Tunnel that continues to: North South \n"
            + "You smell something terrible nearby.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?No. of caves (1-5)?"
            + " In which direction (N-S-E-W)? You shoot an arrow.\n"
            + "You hear a little howl in the distance.Move, Pickup, Shoot or Quit (M-P-S-Q)?"
            + "No. of caves (1-5)? In which direction (N-S-E-W)? You shoot an arrow.\n"
            + "You hear a great howl in the distance.Move, Pickup, Shoot or Quit (M-P-S-Q)?"
            + "Where to (N-S-E-W)? You are in a Cave and its doors lead to: North South West East\n"
            + "You find -> Rubies: 2, Sapphires: 3\n"
            + "You smell something terrible nearby.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?No. of caves (1-5)?"
            + " In which direction (N-S-E-W)? You shoot an arrow.\n"
            + "You hear a little howl in the distance.Move, Pickup, Shoot or Quit (M-P-S-Q)?"
            + "No. of caves (1-5)? In which direction (N-S-E-W)? You shoot an arrow.\n"
            + "You hear a great howl in the distance.Move, Pickup, Shoot or Quit (M-P-S-Q)?"
            + "Where to (N-S-E-W)? You are in a Cave and its doors lead to: East\n"
            + "Game Won!!!, player has reached the end.\n"
            + "Treasures picked up-> Rubies: 3, Sapphires: 2, Diamonds: 4";
    assertEquals(expectedValue, gameLog.toString());
  }

  @Test
  public void testPlayerLose() throws IOException {
    RandomNumberGenerator rand = new MinRandomGenerator(65, 35, 31, 56, 63, 5, 50, 34, 20, 50
            , 59, 16, 6, 41, 21, 1, 25, 45, 51, 3, 16, 49, 24, 6, 5, 16, 6, 6, 41, 15, 3, 38, 34, 5
            , 14, 15, 29, 32, 0, 31, 14, 8, 3, 14, 16, 5, 4, 9, 13, 16, 21, 2, 9, 8, 15, 4, 14, 4
            , 3, 5, 2, 4, 8, 8, 1, 0, 4, 2, 3, 2, 1, 0, 1, 2, 1, 3, 5, 0, 5, 5, 4, 3, 4, 5, 4, 0, 3
            , 1, 4, 5, 1, 1, 5, 3, 1, 0, 2, 1, 3, 4, 2, 4, 4, 3, 3, 1, 5, 4, 2, 0, 3, 4, 5, 3, 1, 1
            , 1, 1, 5, 5, 5, 0, 1, 4, 3, 5, 2, 0, 5, 1, 2, 0, 0, 5, 5, 4, 0, 1, 0, 3, 2, 1, 3, 3, 5
            , 2, 1, 0, 1, 0, 1, 3, 2, 2, 3, 2, 4, 2, 0, 3, 2, 5, 2, 1, 0, 5, 2, 4, 2, 2, 3, 1, 2, 0
            , 0, 2, 0, 0, 4, 2, 2, 0, 2, 3, 2, 1, 2, 2, 5, 5, 1, 1, 2, 2, 3, 0, 3, 1, 0, 0, 2, 1, 0
            , 4, 5, 4, 5, 1, 2, 0, 3, 5, 0, 0, 2, 0, 5, 3, 1, 1, 3, 0, 5, 5, 2, 4, 0, 5, 5, 4, 3, 4
            , 2, 5, 0, 4, 1, 5);
    IDungeon dungeon = new Dungeon(6, 6, true, 1, 50, 8, "Sushith", rand);
    Readable input = new StringReader("M W");
    Appendable gameLog = new StringBuilder();
    IDungeonController c = new DungeonController(input, gameLog);
    c.playGame(dungeon);
    String expectedValue = "Player name: Sushith.\n"
            + "You are in a Cave and its doors lead to: North West East\n"
            + "You find -> Sapphires: 5, Diamonds: 5\n"
            + "You find 2 arrows here.\n"
            + "You smell something terrible nearby.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Where to (N-S-E-W)?"
            + " Chomp, chomp, you are eaten by an Otyugh!\n"
            + "Better luck next time.";
    assertEquals(expectedValue, gameLog.toString());
  }

  @Test
  public void testRandomAlphabetInMove() throws IOException {
    RandomNumberGenerator rand = new MinRandomGenerator(65, 35, 31, 56, 63, 5, 50, 34, 20, 50
            , 59, 16, 6, 41, 21, 1, 25, 45, 51, 3, 16, 49, 24, 6, 5, 16, 6, 6, 41, 15, 3, 38, 34, 5
            , 14, 15, 29, 32, 0, 31, 14, 8, 3, 14, 16, 5, 4, 9, 13, 16, 21, 2, 9, 8, 15, 4, 14, 4
            , 3, 5, 2, 4, 8, 8, 1, 0, 4, 2, 3, 2, 1, 0, 1, 2, 1, 3, 5, 0, 5, 5, 4, 3, 4, 5, 4, 0, 3
            , 1, 4, 5, 1, 1, 5, 3, 1, 0, 2, 1, 3, 4, 2, 4, 4, 3, 3, 1, 5, 4, 2, 0, 3, 4, 5, 3, 1, 1
            , 1, 1, 5, 5, 5, 0, 1, 4, 3, 5, 2, 0, 5, 1, 2, 0, 0, 5, 5, 4, 0, 1, 0, 3, 2, 1, 3, 3, 5
            , 2, 1, 0, 1, 0, 1, 3, 2, 2, 3, 2, 4, 2, 0, 3, 2, 5, 2, 1, 0, 5, 2, 4, 2, 2, 3, 1, 2, 0
            , 0, 2, 0, 0, 4, 2, 2, 0, 2, 3, 2, 1, 2, 2, 5, 5, 1, 1, 2, 2, 3, 0, 3, 1, 0, 0, 2, 1, 0
            , 4, 5, 4, 5, 1, 2, 0, 3, 5, 0, 0, 2, 0, 5, 3, 1, 1, 3, 0, 5, 5, 2, 4, 0, 5, 5, 4, 3, 4
            , 2, 5, 0, 4, 1, 5);
    IDungeon dungeon = new Dungeon(6, 6, true, 1, 50, 8, "Sushith", rand);
    Readable input = new StringReader("M I E Q");
    Appendable gameLog = new StringBuilder();
    IDungeonController c = new DungeonController(input, gameLog);
    c.playGame(dungeon);
    String expectedValue = "Player name: Sushith.\n"
            + "You are in a Cave and its doors lead to: North West East\n"
            + "You find -> Sapphires: 5, Diamonds: 5\n"
            + "You find 2 arrows here.\n"
            + "You smell something terrible nearby.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Where to (N-S-E-W)? Enter a valid direction: "
            + "You are in a Cave and its doors lead to: North South West \n"
            + "You find -> Rubies: 3, Sapphires: 2, Diamonds: 4\n"
            + "You find 3 arrows here.\n"
            + "You smell something mild nearby.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Game quit! Ending game...";
    assertEquals(expectedValue, gameLog.toString());
  }

  @Test
  public void testInvalidDirectionInMove() throws IOException {
    RandomNumberGenerator rand = new MinRandomGenerator(65, 35, 31, 56, 63, 5, 50, 34, 20, 50
            , 59, 16, 6, 41, 21, 1, 25, 45, 51, 3, 16, 49, 24, 6, 5, 16, 6, 6, 41, 15, 3, 38, 34, 5
            , 14, 15, 29, 32, 0, 31, 14, 8, 3, 14, 16, 5, 4, 9, 13, 16, 21, 2, 9, 8, 15, 4, 14, 4
            , 3, 5, 2, 4, 8, 8, 1, 0, 4, 2, 3, 2, 1, 0, 1, 2, 1, 3, 5, 0, 5, 5, 4, 3, 4, 5, 4, 0, 3
            , 1, 4, 5, 1, 1, 5, 3, 1, 0, 2, 1, 3, 4, 2, 4, 4, 3, 3, 1, 5, 4, 2, 0, 3, 4, 5, 3, 1, 1
            , 1, 1, 5, 5, 5, 0, 1, 4, 3, 5, 2, 0, 5, 1, 2, 0, 0, 5, 5, 4, 0, 1, 0, 3, 2, 1, 3, 3, 5
            , 2, 1, 0, 1, 0, 1, 3, 2, 2, 3, 2, 4, 2, 0, 3, 2, 5, 2, 1, 0, 5, 2, 4, 2, 2, 3, 1, 2, 0
            , 0, 2, 0, 0, 4, 2, 2, 0, 2, 3, 2, 1, 2, 2, 5, 5, 1, 1, 2, 2, 3, 0, 3, 1, 0, 0, 2, 1, 0
            , 4, 5, 4, 5, 1, 2, 0, 3, 5, 0, 0, 2, 0, 5, 3, 1, 1, 3, 0, 5, 5, 2, 4, 0, 5, 5, 4, 3, 4
            , 2, 5, 0, 4, 1, 5);
    IDungeon dungeon = new Dungeon(6, 6, true, 1, 50, 8, "Sushith", rand);
    Readable input = new StringReader("M S M E Q");
    Appendable gameLog = new StringBuilder();
    IDungeonController c = new DungeonController(input, gameLog);
    c.playGame(dungeon);
    String expectedValue = "Player name: Sushith.\n"
            + "You are in a Cave and its doors lead to: North West East\n"
            + "You find -> Sapphires: 5, Diamonds: 5\n"
            + "You find 2 arrows here.\n"
            + "You smell something terrible nearby.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Where to (N-S-E-W)?"
            + " There is no path to SOUTH from this location.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Where to (N-S-E-W)?"
            + " You are in a Cave and its doors lead to: North South West \n"
            + "You find -> Rubies: 3, Sapphires: 2, Diamonds: 4\n"
            + "You find 3 arrows here.\n"
            + "You smell something mild nearby.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Game quit! Ending game...";
    assertEquals(expectedValue, gameLog.toString());
  }

  @Test
  public void testInvalidInputInPickUp() throws IOException {
    RandomNumberGenerator rand = new MinRandomGenerator(65, 35, 31, 56, 63, 5, 50, 34, 20, 50
            , 59, 16, 6, 41, 21, 1, 25, 45, 51, 3, 16, 49, 24, 6, 5, 16, 6, 6, 41, 15, 3, 38, 34, 5
            , 14, 15, 29, 32, 0, 31, 14, 8, 3, 14, 16, 5, 4, 9, 13, 16, 21, 2, 9, 8, 15, 4, 14, 4
            , 3, 5, 2, 4, 8, 8, 1, 0, 4, 2, 3, 2, 1, 0, 1, 2, 1, 3, 5, 0, 5, 5, 4, 3, 4, 5, 4, 0, 3
            , 1, 4, 5, 1, 1, 5, 3, 1, 0, 2, 1, 3, 4, 2, 4, 4, 3, 3, 1, 5, 4, 2, 0, 3, 4, 5, 3, 1, 1
            , 1, 1, 5, 5, 5, 0, 1, 4, 3, 5, 2, 0, 5, 1, 2, 0, 0, 5, 5, 4, 0, 1, 0, 3, 2, 1, 3, 3, 5
            , 2, 1, 0, 1, 0, 1, 3, 2, 2, 3, 2, 4, 2, 0, 3, 2, 5, 2, 1, 0, 5, 2, 4, 2, 2, 3, 1, 2, 0
            , 0, 2, 0, 0, 4, 2, 2, 0, 2, 3, 2, 1, 2, 2, 5, 5, 1, 1, 2, 2, 3, 0, 3, 1, 0, 0, 2, 1, 0
            , 4, 5, 4, 5, 1, 2, 0, 3, 5, 0, 0, 2, 0, 5, 3, 1, 1, 3, 0, 5, 5, 2, 4, 0, 5, 5, 4, 3, 4
            , 2, 5, 0, 4, 1, 5);
    IDungeon dungeon = new Dungeon(6, 6, true, 1, 50, 8, "Sushith", rand);
    Readable input = new StringReader("P W A 2 P 3 T M E Q");
    Appendable gameLog = new StringBuilder();
    IDungeonController c = new DungeonController(input, gameLog);
    c.playGame(dungeon);
    String expectedValue = "Player name: Sushith.\n"
            + "You are in a Cave and its doors lead to: North West East\n"
            + "You find -> Sapphires: 5, Diamonds: 5\n"
            + "You find 2 arrows here.\n"
            + "You smell something terrible nearby.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?What (T-A)?"
            + " Enter a valid item: How many? 2 arrows have been picked up.\n"
            + "You have 5 arrows with you now.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?What (T-A)?"
            + " Enter a valid item: You pick up -> Sapphires: 5, Diamonds: 5\n"
            + "Treasures picked up-> Rubies: 0, Sapphires: 5, Diamonds: 5\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Where to (N-S-E-W)?"
            + " You are in a Cave and its doors lead to: North South West \n"
            + "You find -> Rubies: 3, Sapphires: 2, Diamonds: 4\n"
            + "You find 3 arrows here.\n"
            + "You smell something mild nearby.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Game quit! Ending game...";
    assertEquals(expectedValue, gameLog.toString());
  }

  @Test
  public void testRandomAlphabetDirectionInShoot() throws IOException {
    RandomNumberGenerator rand = new MinRandomGenerator(65, 35, 31, 56, 63, 5, 50, 34, 20, 50
            , 59, 16, 6, 41, 21, 1, 25, 45, 51, 3, 16, 49, 24, 6, 5, 16, 6, 6, 41, 15, 3, 38, 34, 5
            , 14, 15, 29, 32, 0, 31, 14, 8, 3, 14, 16, 5, 4, 9, 13, 16, 21, 2, 9, 8, 15, 4, 14, 4
            , 3, 5, 2, 4, 8, 8, 1, 0, 4, 2, 3, 2, 1, 0, 1, 2, 1, 3, 5, 0, 5, 5, 4, 3, 4, 5, 4, 0, 3
            , 1, 4, 5, 1, 1, 5, 3, 1, 0, 2, 1, 3, 4, 2, 4, 4, 3, 3, 1, 5, 4, 2, 0, 3, 4, 5, 3, 1, 1
            , 1, 1, 5, 5, 5, 0, 1, 4, 3, 5, 2, 0, 5, 1, 2, 0, 0, 5, 5, 4, 0, 1, 0, 3, 2, 1, 3, 3, 5
            , 2, 1, 0, 1, 0, 1, 3, 2, 2, 3, 2, 4, 2, 0, 3, 2, 5, 2, 1, 0, 5, 2, 4, 2, 2, 3, 1, 2, 0
            , 0, 2, 0, 0, 4, 2, 2, 0, 2, 3, 2, 1, 2, 2, 5, 5, 1, 1, 2, 2, 3, 0, 3, 1, 0, 0, 2, 1, 0
            , 4, 5, 4, 5, 1, 2, 0, 3, 5, 0, 0, 2, 0, 5, 3, 1, 1, 3, 0, 5, 5, 2, 4, 0, 5, 5, 4, 3, 4
            , 2, 5, 0, 4, 1, 5);
    IDungeon dungeon = new Dungeon(6, 6, true, 1, 50, 8, "Sushith", rand);
    Readable input = new StringReader("S 1 W S 1 Y W S 2 W Q");
    Appendable gameLog = new StringBuilder();
    IDungeonController c = new DungeonController(input, gameLog);
    c.playGame(dungeon);
    String expectedValue = "Player name: Sushith.\n"
            + "You are in a Cave and its doors lead to: North West East\n"
            + "You find -> Sapphires: 5, Diamonds: 5\n"
            + "You find 2 arrows here.\n"
            + "You smell something terrible nearby.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?No. of caves (1-5)?"
            + " In which direction (N-S-E-W)? You shoot an arrow.\n"
            + "You hear a little howl in the distance.Move, Pickup, Shoot or Quit (M-P-S-Q)?"
            + "No. of caves (1-5)? In which direction (N-S-E-W)?"
            + " Enter a valid direction: You shoot an arrow.\n"
            + "You hear a great howl in the distance.Move, Pickup, Shoot or Quit (M-P-S-Q)?"
            + "No. of caves (1-5)? In which direction (N-S-E-W)? You shoot an arrow.\n"
            + "You hear a little howl in the distance.Move, Pickup, Shoot or Quit (M-P-S-Q)?"
            + "Game quit! Ending game...";
    assertEquals(expectedValue, gameLog.toString());
  }

  @Test
  public void testInvalidDirectionInShoot() throws IOException {
    RandomNumberGenerator rand = new MinRandomGenerator(65, 35, 31, 56, 63, 5, 50, 34, 20, 50
            , 59, 16, 6, 41, 21, 1, 25, 45, 51, 3, 16, 49, 24, 6, 5, 16, 6, 6, 41, 15, 3, 38, 34, 5
            , 14, 15, 29, 32, 0, 31, 14, 8, 3, 14, 16, 5, 4, 9, 13, 16, 21, 2, 9, 8, 15, 4, 14, 4
            , 3, 5, 2, 4, 8, 8, 1, 0, 4, 2, 3, 2, 1, 0, 1, 2, 1, 3, 5, 0, 5, 5, 4, 3, 4, 5, 4, 0, 3
            , 1, 4, 5, 1, 1, 5, 3, 1, 0, 2, 1, 3, 4, 2, 4, 4, 3, 3, 1, 5, 4, 2, 0, 3, 4, 5, 3, 1, 1
            , 1, 1, 5, 5, 5, 0, 1, 4, 3, 5, 2, 0, 5, 1, 2, 0, 0, 5, 5, 4, 0, 1, 0, 3, 2, 1, 3, 3, 5
            , 2, 1, 0, 1, 0, 1, 3, 2, 2, 3, 2, 4, 2, 0, 3, 2, 5, 2, 1, 0, 5, 2, 4, 2, 2, 3, 1, 2, 0
            , 0, 2, 0, 0, 4, 2, 2, 0, 2, 3, 2, 1, 2, 2, 5, 5, 1, 1, 2, 2, 3, 0, 3, 1, 0, 0, 2, 1, 0
            , 4, 5, 4, 5, 1, 2, 0, 3, 5, 0, 0, 2, 0, 5, 3, 1, 1, 3, 0, 5, 5, 2, 4, 0, 5, 5, 4, 3, 4
            , 2, 5, 0, 4, 1, 5);
    IDungeon dungeon = new Dungeon(6, 6, true, 1, 50, 8, "Sushith", rand);
    Readable input = new StringReader("S 1 S S 1 W S 1 W Q");
    Appendable gameLog = new StringBuilder();
    IDungeonController c = new DungeonController(input, gameLog);
    c.playGame(dungeon);
    String expectedValue = "Player name: Sushith.\n"
            + "You are in a Cave and its doors lead to: North West East\n"
            + "You find -> Sapphires: 5, Diamonds: 5\n"
            + "You find 2 arrows here.\n"
            + "You smell something terrible nearby.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?No. of caves (1-5)?"
            + " In which direction (N-S-E-W)? Path to the specified direction does not exist.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?No. of caves (1-5)?"
            + " In which direction (N-S-E-W)? You shoot an arrow.\n"
            + "You hear a little howl in the distance.Move, Pickup, Shoot or Quit (M-P-S-Q)?"
            + "No. of caves (1-5)? In which direction (N-S-E-W)? You shoot an arrow.\n"
            + "You hear a great howl in the distance.Move, Pickup, Shoot or Quit (M-P-S-Q)?"
            + "Game quit! Ending game...";
    assertEquals(expectedValue, gameLog.toString());
  }

  @Test
  public void testInvalidDistanceInShoot() throws IOException {
    RandomNumberGenerator rand = new MinRandomGenerator(65, 35, 31, 56, 63, 5, 50, 34, 20, 50
            , 59, 16, 6, 41, 21, 1, 25, 45, 51, 3, 16, 49, 24, 6, 5, 16, 6, 6, 41, 15, 3, 38, 34, 5
            , 14, 15, 29, 32, 0, 31, 14, 8, 3, 14, 16, 5, 4, 9, 13, 16, 21, 2, 9, 8, 15, 4, 14, 4
            , 3, 5, 2, 4, 8, 8, 1, 0, 4, 2, 3, 2, 1, 0, 1, 2, 1, 3, 5, 0, 5, 5, 4, 3, 4, 5, 4, 0, 3
            , 1, 4, 5, 1, 1, 5, 3, 1, 0, 2, 1, 3, 4, 2, 4, 4, 3, 3, 1, 5, 4, 2, 0, 3, 4, 5, 3, 1, 1
            , 1, 1, 5, 5, 5, 0, 1, 4, 3, 5, 2, 0, 5, 1, 2, 0, 0, 5, 5, 4, 0, 1, 0, 3, 2, 1, 3, 3, 5
            , 2, 1, 0, 1, 0, 1, 3, 2, 2, 3, 2, 4, 2, 0, 3, 2, 5, 2, 1, 0, 5, 2, 4, 2, 2, 3, 1, 2, 0
            , 0, 2, 0, 0, 4, 2, 2, 0, 2, 3, 2, 1, 2, 2, 5, 5, 1, 1, 2, 2, 3, 0, 3, 1, 0, 0, 2, 1, 0
            , 4, 5, 4, 5, 1, 2, 0, 3, 5, 0, 0, 2, 0, 5, 3, 1, 1, 3, 0, 5, 5, 2, 4, 0, 5, 5, 4, 3, 4
            , 2, 5, 0, 4, 1, 5);
    IDungeon dungeon = new Dungeon(6, 6, true, 1, 50, 8, "Sushith", rand);
    Readable input = new StringReader("S 0 1 W S 6 2 S 1 W Q");
    Appendable gameLog = new StringBuilder();
    IDungeonController c = new DungeonController(input, gameLog);
    c.playGame(dungeon);
    String expectedValue = "Player name: Sushith.\n"
            + "You are in a Cave and its doors lead to: North West East\n"
            + "You find -> Sapphires: 5, Diamonds: 5\n"
            + "You find 2 arrows here.\n"
            + "You smell something terrible nearby.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?No. of caves (1-5)?"
            + " Enter a valid distance: In which direction (N-S-E-W)? You shoot an arrow.\n"
            + "You hear a little howl in the distance.Move, Pickup, Shoot or Quit (M-P-S-Q)?"
            + "No. of caves (1-5)? Enter a valid distance: In which direction (N-S-E-W)?"
            + " Path to the specified direction does not exist.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Unknown command 1\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Unknown command W\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Game quit! Ending game...";
    assertEquals(expectedValue, gameLog.toString());
  }

  @Test
  public void testShootWithNoArrows() throws IOException {
    RandomNumberGenerator rand = new MinRandomGenerator(65, 35, 31, 56, 63, 5, 50, 34, 20, 50
            , 59, 16, 6, 41, 21, 1, 25, 45, 51, 3, 16, 49, 24, 6, 5, 16, 6, 6, 41, 15, 3, 38, 34, 5
            , 14, 15, 29, 32, 0, 31, 14, 8, 3, 14, 16, 5, 4, 9, 13, 16, 21, 2, 9, 8, 15, 4, 14, 4
            , 3, 5, 2, 4, 8, 8, 1, 0, 4, 2, 3, 2, 1, 0, 1, 2, 1, 3, 5, 0, 5, 5, 4, 3, 4, 5, 4, 0, 3
            , 1, 4, 5, 1, 1, 5, 3, 1, 0, 2, 1, 3, 4, 2, 4, 4, 3, 3, 1, 5, 4, 2, 0, 3, 4, 5, 3, 1, 1
            , 1, 1, 5, 5, 5, 0, 1, 4, 3, 5, 2, 0, 5, 1, 2, 0, 0, 5, 5, 4, 0, 1, 0, 3, 2, 1, 3, 3, 5
            , 2, 1, 0, 1, 0, 1, 3, 2, 2, 3, 2, 4, 2, 0, 3, 2, 5, 2, 1, 0, 5, 2, 4, 2, 2, 3, 1, 2, 0
            , 0, 2, 0, 0, 4, 2, 2, 0, 2, 3, 2, 1, 2, 2, 5, 5, 1, 1, 2, 2, 3, 0, 3, 1, 0, 0, 2, 1, 0
            , 4, 5, 4, 5, 1, 2, 0, 3, 5, 0, 0, 2, 0, 5, 3, 1, 1, 3, 0, 5, 5, 2, 4, 0, 5, 5, 4, 3, 4
            , 2, 5, 0, 4, 1, 5);
    IDungeon dungeon = new Dungeon(6, 6, true, 1, 50, 8, "Sushith", rand);
    Readable input = new StringReader("S 1 W S 1 W S 2 W S 2 W Q");
    Appendable gameLog = new StringBuilder();
    IDungeonController c = new DungeonController(input, gameLog);
    c.playGame(dungeon);
    String expectedValue = "Player name: Sushith.\n"
            + "You are in a Cave and its doors lead to: North West East\n"
            + "You find -> Sapphires: 5, Diamonds: 5\n"
            + "You find 2 arrows here.\n"
            + "You smell something terrible nearby.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?No. of caves (1-5)?"
            + " In which direction (N-S-E-W)? You shoot an arrow.\n"
            + "You hear a little howl in the distance.Move, Pickup, Shoot or Quit (M-P-S-Q)?"
            + "No. of caves (1-5)? In which direction (N-S-E-W)? You shoot an arrow.\n"
            + "You hear a great howl in the distance.Move, Pickup, Shoot or Quit (M-P-S-Q)?"
            + "No. of caves (1-5)? In which direction (N-S-E-W)? You shoot an arrow.\n"
            + "You hear a little howl in the distance.Move, Pickup, Shoot or Quit (M-P-S-Q)?"
            + "No. of caves (1-5)? In which direction (N-S-E-W)?"
            + " You are out of arrows, explore the dungeon to find more.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Game quit! Ending game...";
    assertEquals(expectedValue, gameLog.toString());
  }

  @Test
  public void testShootWhen0() throws IOException {
    RandomNumberGenerator rand = new MinRandomGenerator(65, 35, 31, 56, 63, 5, 50, 34, 20, 50
            , 59, 16, 6, 41, 21, 1, 25, 45, 51, 3, 16, 49, 24, 6, 5, 16, 6, 6, 41, 15, 3, 38, 34, 5
            , 14, 15, 29, 32, 0, 31, 14, 8, 3, 14, 16, 5, 4, 9, 13, 16, 21, 2, 9, 8, 15, 4, 14, 4
            , 3, 5, 2, 4, 8, 8, 1, 0, 4, 2, 3, 2, 1, 0, 1, 2, 1, 3, 5, 0, 5, 5, 4, 3, 4, 5, 4, 0, 3
            , 1, 4, 5, 1, 1, 5, 3, 1, 0, 2, 1, 3, 4, 2, 4, 4, 3, 3, 1, 5, 4, 2, 0, 3, 4, 5, 3, 1, 1
            , 1, 1, 5, 5, 5, 0, 1, 4, 3, 5, 2, 0, 5, 1, 2, 0, 0, 5, 5, 4, 0, 1, 0, 3, 2, 1, 3, 3, 5
            , 2, 1, 0, 1, 0, 1, 3, 2, 2, 3, 2, 4, 2, 0, 3, 2, 5, 2, 1, 0, 5, 2, 4, 2, 2, 3, 1, 2, 0
            , 0, 2, 0, 0, 4, 2, 2, 0, 2, 3, 2, 1, 2, 2, 5, 5, 1, 1, 2, 2, 3, 0, 3, 1, 0, 0, 2, 1, 0
            , 4, 5, 4, 5, 1, 2, 0, 3, 5, 0, 0, 2, 0, 5, 3, 1, 1, 3, 0, 5, 5, 2, 4, 0, 5, 5, 4, 3, 4
            , 2, 5, 0, 4, 1, 5);
    IDungeon dungeon = new Dungeon(6, 6, true, 1, 50, 8, "Sushith", rand);
    Readable input = new StringReader("S 1 W S 1 W S 2 W S 2 W Q");
    Appendable gameLog = new StringBuilder();
    IDungeonController c = new DungeonController(input, gameLog);
    c.playGame(dungeon);
    String expectedValue = "Player name: Sushith.\n"
            + "You are in a Cave and its doors lead to: North West East\n"
            + "You find -> Sapphires: 5, Diamonds: 5\n"
            + "You find 2 arrows here.\n"
            + "You smell something terrible nearby.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?No. of caves (1-5)?"
            + " In which direction (N-S-E-W)? You shoot an arrow.\n"
            + "You hear a little howl in the distance.Move, Pickup, Shoot or Quit (M-P-S-Q)?"
            + "No. of caves (1-5)? In which direction (N-S-E-W)? You shoot an arrow.\n"
            + "You hear a great howl in the distance.Move, Pickup, Shoot or Quit (M-P-S-Q)?"
            + "No. of caves (1-5)? In which direction (N-S-E-W)? You shoot an arrow.\n"
            + "You hear a little howl in the distance.Move, Pickup, Shoot or Quit (M-P-S-Q)?"
            + "No. of caves (1-5)? In which direction (N-S-E-W)?"
            + " You are out of arrows, explore the dungeon to find more.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Game quit! Ending game...";
    assertEquals(expectedValue, gameLog.toString());
  }

  @Test
  public void pickEmptyTreasure() throws IOException {
    RandomNumberGenerator rand = new MinRandomGenerator(65, 35, 31, 56, 63, 5, 50, 34, 20, 50
            , 59, 16, 6, 41, 21, 1, 25, 45, 51, 3, 16, 49, 24, 6, 5, 16, 6, 6, 41, 15, 3, 38, 34, 5
            , 14, 15, 29, 32, 0, 31, 14, 8, 3, 14, 16, 5, 4, 9, 13, 16, 21, 2, 9, 8, 15, 4, 14, 4
            , 3, 5, 2, 4, 8, 8, 1, 0, 4, 2, 3, 2, 1, 0, 1, 2, 1, 3, 5, 0, 5, 5, 4, 3, 4, 5, 4, 0, 3
            , 1, 4, 5, 1, 1, 5, 3, 1, 0, 2, 1, 3, 4, 2, 4, 4, 3, 3, 1, 5, 4, 2, 0, 3, 4, 5, 3, 1, 1
            , 1, 1, 5, 5, 5, 0, 1, 4, 3, 5, 2, 0, 5, 1, 2, 0, 0, 5, 5, 4, 0, 1, 0, 3, 2, 1, 3, 3, 5
            , 2, 1, 0, 1, 0, 1, 3, 2, 2, 3, 2, 4, 2, 0, 3, 2, 5, 2, 1, 0, 5, 2, 4, 2, 2, 3, 1, 2, 0
            , 0, 2, 0, 0, 4, 2, 2, 0, 2, 3, 2, 1, 2, 2, 5, 5, 1, 1, 2, 2, 3, 0, 3, 1, 0, 0, 2, 1, 0
            , 4, 5, 4, 5, 1, 2, 0, 3, 5, 0, 0, 2, 0, 5, 3, 1, 1, 3, 0, 5, 5, 2, 4, 0, 5, 5, 4, 3, 4
            , 2, 5, 0, 4, 1, 5);
    IDungeon dungeon = new Dungeon(6, 6, true, 1, 50, 8, "Sushith", rand);
    Readable input = new StringReader("P T P T Q");
    Appendable gameLog = new StringBuilder();
    IDungeonController c = new DungeonController(input, gameLog);
    c.playGame(dungeon);
    String expectedValue = "Player name: Sushith.\n"
            + "You are in a Cave and its doors lead to: North West East\n"
            + "You find -> Sapphires: 5, Diamonds: 5\n"
            + "You find 2 arrows here.\n"
            + "You smell something terrible nearby.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?What (T-A)?"
            + " You pick up -> Sapphires: 5, Diamonds: 5\n"
            + "Treasures picked up-> Rubies: 0, Sapphires: 5, Diamonds: 5\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?What (T-A)?"
            + " There should be at least one treasure to be picked up.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Game quit! Ending game...";
    assertEquals(expectedValue, gameLog.toString());
  }

  @Test
  public void testPickEmptyArrowsCount() throws IOException {
    RandomNumberGenerator rand = new MinRandomGenerator(65, 35, 31, 56, 63, 5, 50, 34, 20, 50
            , 59, 16, 6, 41, 21, 1, 25, 45, 51, 3, 16, 49, 24, 6, 5, 16, 6, 6, 41, 15, 3, 38, 34, 5
            , 14, 15, 29, 32, 0, 31, 14, 8, 3, 14, 16, 5, 4, 9, 13, 16, 21, 2, 9, 8, 15, 4, 14, 4
            , 3, 5, 2, 4, 8, 8, 1, 0, 4, 2, 3, 2, 1, 0, 1, 2, 1, 3, 5, 0, 5, 5, 4, 3, 4, 5, 4, 0, 3
            , 1, 4, 5, 1, 1, 5, 3, 1, 0, 2, 1, 3, 4, 2, 4, 4, 3, 3, 1, 5, 4, 2, 0, 3, 4, 5, 3, 1, 1
            , 1, 1, 5, 5, 5, 0, 1, 4, 3, 5, 2, 0, 5, 1, 2, 0, 0, 5, 5, 4, 0, 1, 0, 3, 2, 1, 3, 3, 5
            , 2, 1, 0, 1, 0, 1, 3, 2, 2, 3, 2, 4, 2, 0, 3, 2, 5, 2, 1, 0, 5, 2, 4, 2, 2, 3, 1, 2, 0
            , 0, 2, 0, 0, 4, 2, 2, 0, 2, 3, 2, 1, 2, 2, 5, 5, 1, 1, 2, 2, 3, 0, 3, 1, 0, 0, 2, 1, 0
            , 4, 5, 4, 5, 1, 2, 0, 3, 5, 0, 0, 2, 0, 5, 3, 1, 1, 3, 0, 5, 5, 2, 4, 0, 5, 5, 4, 3, 4
            , 2, 5, 0, 4, 1, 5);
    IDungeon dungeon = new Dungeon(6, 6, true, 1, 50, 8, "Sushith", rand);
    Readable input = new StringReader("P A 2 P A 2 Q");
    Appendable gameLog = new StringBuilder();
    IDungeonController c = new DungeonController(input, gameLog);
    c.playGame(dungeon);
    String expectedValue = "Player name: Sushith.\n"
            + "You are in a Cave and its doors lead to: North West East\n"
            + "You find -> Sapphires: 5, Diamonds: 5\n"
            + "You find 2 arrows here.\n"
            + "You smell something terrible nearby.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?What (T-A)?"
            + " How many? 2 arrows have been picked up.\n"
            + "You have 5 arrows with you now.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?What (T-A)?"
            + " How many? There are no arrows here.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?Game quit! Ending game...";
    assertEquals(expectedValue, gameLog.toString());
  }

  @Test
  public void testDistanceString() throws IOException {
    RandomNumberGenerator rand = new MinRandomGenerator(65, 35, 31, 56, 63, 5, 50, 34, 20, 50
            , 59, 16, 6, 41, 21, 1, 25, 45, 51, 3, 16, 49, 24, 6, 5, 16, 6, 6, 41, 15, 3, 38, 34, 5
            , 14, 15, 29, 32, 0, 31, 14, 8, 3, 14, 16, 5, 4, 9, 13, 16, 21, 2, 9, 8, 15, 4, 14, 4
            , 3, 5, 2, 4, 8, 8, 1, 0, 4, 2, 3, 2, 1, 0, 1, 2, 1, 3, 5, 0, 5, 5, 4, 3, 4, 5, 4, 0, 3
            , 1, 4, 5, 1, 1, 5, 3, 1, 0, 2, 1, 3, 4, 2, 4, 4, 3, 3, 1, 5, 4, 2, 0, 3, 4, 5, 3, 1, 1
            , 1, 1, 5, 5, 5, 0, 1, 4, 3, 5, 2, 0, 5, 1, 2, 0, 0, 5, 5, 4, 0, 1, 0, 3, 2, 1, 3, 3, 5
            , 2, 1, 0, 1, 0, 1, 3, 2, 2, 3, 2, 4, 2, 0, 3, 2, 5, 2, 1, 0, 5, 2, 4, 2, 2, 3, 1, 2, 0
            , 0, 2, 0, 0, 4, 2, 2, 0, 2, 3, 2, 1, 2, 2, 5, 5, 1, 1, 2, 2, 3, 0, 3, 1, 0, 0, 2, 1, 0
            , 4, 5, 4, 5, 1, 2, 0, 3, 5, 0, 0, 2, 0, 5, 3, 1, 1, 3, 0, 5, 5, 2, 4, 0, 5, 5, 4, 3, 4
            , 2, 5, 0, 4, 1, 5);
    IDungeon dungeon = new Dungeon(6, 6, true, 1, 50, 8, "Sushith", rand);
    Readable input = new StringReader("S str W S 1 W Q");
    Appendable gameLog = new StringBuilder();
    IDungeonController c = new DungeonController(input, gameLog);
    c.playGame(dungeon);
    String expectedValue = "Player name: Sushith.\n"
            + "You are in a Cave and its doors lead to: North West East\n"
            + "You find -> Sapphires: 5, Diamonds: 5\n"
            + "You find 2 arrows here.\n"
            + "You smell something terrible nearby.\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?No. of caves (1-5)? For input string:"
            + " \"str\"\n" + "In which direction (N-S-E-W)? Distance must be between 1-5\n"
            + "Move, Pickup, Shoot or Quit (M-P-S-Q)?No. of caves (1-5)?"
            + " In which direction (N-S-E-W)? You shoot an arrow.\n"
            + "You hear a little howl in the distance.Move, Pickup, Shoot or Quit (M-P-S-Q)?"
            + "Game quit! Ending game...";
    assertEquals(expectedValue, gameLog.toString());
  }
}
