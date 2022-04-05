import org.junit.Test;

import controller.DungeonConsoleController;
import model.Direction;
import model.IDungeon;
import view.DungeonView;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the dungeon view, using mocks for appendable.
 */
public class ViewTest {

  @Test
  public void testStartAndIntroPageCreation() {
    Appendable gameLog = new StringBuilder();
    IDungeon c = new DungeonMock(gameLog);
    DungeonView view = new DungeonMockView(gameLog);
    DungeonConsoleController cc = new DungeonConsoleController(c, view);
    cc.startGame();
    assertEquals("make visible\n" +
            "setIntro, controller added\n", gameLog.toString());
  }

  @Test
  public void testCreationOfModelAndRestart() {
    Appendable gameLog = new StringBuilder();
    IDungeon c = new DungeonMock(gameLog);
    DungeonView view = new DungeonMockView(gameLog);
    DungeonConsoleController cc = new DungeonConsoleController(c, view);
    cc.createModel(6, 6, false, 5, 60, 3, "Sushith");
    assertEquals("setModel, model and controller added\n", gameLog.toString());
  }

  @Test
  public void testInvalidRows() {
    Appendable gameLog = new StringBuilder();
    IDungeon c = new DungeonMock(gameLog);
    DungeonView view = new DungeonMockView(gameLog);
    DungeonConsoleController cc = new DungeonConsoleController(c, view);
    cc.createModel(-6, 6, false, 5, 60, 3, "Sushith");
    assertEquals("popUpsInvalid Dungeon creation\n", gameLog.toString());
  }

  @Test
  public void testInvalidColumns() {
    Appendable gameLog = new StringBuilder();
    IDungeon c = new DungeonMock(gameLog);
    DungeonView view = new DungeonMockView(gameLog);
    DungeonConsoleController cc = new DungeonConsoleController(c, view);
    cc.createModel(6, -6, true, 5, 60, 3, "Sushith");
    assertEquals("popUpsInvalid Dungeon creation\n", gameLog.toString());
  }

  @Test
  public void testInvalidWrap() {
    Appendable gameLog = new StringBuilder();
    IDungeon c = new DungeonMock(gameLog);
    DungeonView view = new DungeonMockView(gameLog);
    DungeonConsoleController cc = new DungeonConsoleController(c, view);
    cc.createModel(6, 6, null, 5, 60, 3, "Sushith");
    assertEquals("popUpsInvalid Dungeon creation\n", gameLog.toString());
  }

  @Test
  public void testInvalidInterconnectivity() {
    Appendable gameLog = new StringBuilder();
    IDungeon c = new DungeonMock(gameLog);
    DungeonView view = new DungeonMockView(gameLog);
    DungeonConsoleController cc = new DungeonConsoleController(c, view);
    cc.createModel(6, 6, false, -1, 60, 3, "Sushith");
    assertEquals("popUpsInvalid Dungeon creation\n", gameLog.toString());
  }

  @Test
  public void testInvalidNegativePercentage() {
    Appendable gameLog = new StringBuilder();
    IDungeon c = new DungeonMock(gameLog);
    DungeonView view = new DungeonMockView(gameLog);
    DungeonConsoleController cc = new DungeonConsoleController(c, view);
    cc.createModel(6, 6, false, 1, -1, 3, "Sushith");
    assertEquals("popUpsInvalid Dungeon creation\n", gameLog.toString());
  }

  @Test
  public void testInvalidGreaterPercentage() {
    Appendable gameLog = new StringBuilder();
    IDungeon c = new DungeonMock(gameLog);
    DungeonView view = new DungeonMockView(gameLog);
    DungeonConsoleController cc = new DungeonConsoleController(c, view);
    cc.createModel(6, 6, false, 1, 101, 3, "Sushith");
    assertEquals("popUpsInvalid Dungeon creation\n", gameLog.toString());
  }

  @Test
  public void testInvalidOtyugh() {
    Appendable gameLog = new StringBuilder();
    IDungeon c = new DungeonMock(gameLog);
    DungeonView view = new DungeonMockView(gameLog);
    DungeonConsoleController cc = new DungeonConsoleController(c, view);
    cc.createModel(6, 6, false, 1, 1, -3, "Sushith");
    assertEquals("popUpsInvalid Dungeon creation\n", gameLog.toString());
  }

  @Test
  public void testInvalidName() {
    Appendable gameLog = new StringBuilder();
    IDungeon c = new DungeonMock(gameLog);
    DungeonView view = new DungeonMockView(gameLog);
    DungeonConsoleController cc = new DungeonConsoleController(c, view);
    cc.createModel(6, 6, false, 1, 1, 3, null);
    assertEquals("popUpsInvalid Dungeon creation\n", gameLog.toString());
  }

  @Test
  public void testMove() {
    Appendable gameLog = new StringBuilder();
    IDungeon c = new DungeonMock(gameLog);
    DungeonView view = new DungeonMockView(gameLog);
    DungeonConsoleController cc = new DungeonConsoleController(c, view);
    cc.movePlayer(Direction.NORTH);
    assertEquals("move NORTH\n"
            + "setModel, model and controller added\n", gameLog.toString());
  }

  @Test
  public void testMoveInvalid() {
    Appendable gameLog = new StringBuilder();
    IDungeon c = new DungeonMock(gameLog);
    DungeonView view = new DungeonMockView(gameLog);
    DungeonConsoleController cc = new DungeonConsoleController(c, view);
    cc.movePlayer(null);
    assertEquals("move null\n"
            + "setModel, model and controller added\n", gameLog.toString());
  }

  @Test
  public void testPickUpArrow() {
    Appendable gameLog = new StringBuilder();
    IDungeon c = new DungeonMock(gameLog);
    DungeonView view = new DungeonMockView(gameLog);
    DungeonConsoleController cc = new DungeonConsoleController(c, view);
    cc.pickUp("Arrows", 3);
    assertEquals("pickWeapon 3setModel, model and controller added\n", gameLog.toString());
  }

  @Test
  public void testPickupInvalidArrow() {
    Appendable gameLog = new StringBuilder();
    IDungeon c = new DungeonMock(gameLog);
    DungeonView view = new DungeonMockView(gameLog);
    DungeonConsoleController cc = new DungeonConsoleController(c, view);
    cc.pickUp(null, 2);
    assertEquals("setModel, model and controller added\n", gameLog.toString());
  }

  @Test
  public void testPickUpTreasure() {
    Appendable gameLog = new StringBuilder();
    IDungeon c = new DungeonMock(gameLog);
    DungeonView view = new DungeonMockView(gameLog);
    DungeonConsoleController cc = new DungeonConsoleController(c, view);
    cc.pickUp("Treasure", 0);
    assertEquals("pickTreasure\n"
            + "setModel, model and controller added\n", gameLog.toString());
  }

  @Test
  public void testPickUpInvalidTreasure() {
    Appendable gameLog = new StringBuilder();
    IDungeon c = new DungeonMock(gameLog);
    DungeonView view = new DungeonMockView(gameLog);
    DungeonConsoleController cc = new DungeonConsoleController(c, view);
    cc.pickUp(null, 0);
    assertEquals("setModel, model and controller added\n", gameLog.toString());
  }

  @Test
  public void testShoot() {
    Appendable gameLog = new StringBuilder();
    IDungeon c = new DungeonMock(gameLog);
    DungeonView view = new DungeonMockView(gameLog);
    DungeonConsoleController cc = new DungeonConsoleController(c, view);
    cc.shoot(Direction.NORTH, 2);
    assertEquals("shoot NORTH2setModel, model and controller added\n", gameLog.toString());
  }

  @Test
  public void testInvalidDirectionInShoot() {
    Appendable gameLog = new StringBuilder();
    IDungeon c = new DungeonMock(gameLog);
    DungeonView view = new DungeonMockView(gameLog);
    DungeonConsoleController cc = new DungeonConsoleController(c, view);
    cc.shoot(null, 2);
    assertEquals("shoot null2setModel, model and controller added\n", gameLog.toString());
  }

  @Test
  public void testReuse() {
    Appendable gameLog = new StringBuilder();
    IDungeon c = new DungeonMock(gameLog);
    DungeonView view = new DungeonMockView(gameLog);
    DungeonConsoleController cc = new DungeonConsoleController(c, view);
    cc.createModel(6, 6, false, 5, 60, 3, "Sushith");
    cc.reuseDungeon();
    assertEquals("setModel, model and controller added\n" +
            "setModel, model and controller added\n", gameLog.toString());
  }

  @Test
  public void testPopUps() {
    Appendable gameLog = new StringBuilder();
    IDungeon c = new DungeonMock(gameLog);
    DungeonView view = new DungeonMockView(gameLog);
    DungeonConsoleController cc = new DungeonConsoleController(c, view);
    cc.handle("There are no arrows here.");
    assertEquals("popUpsThere are no arrows here.\n", gameLog.toString());
  }

  @Test
  public void testQuit() {
    Appendable gameLog = new StringBuilder();
    IDungeon c = new DungeonMock(gameLog);
    DungeonView view = new DungeonMockView(gameLog);
    DungeonConsoleController cc = new DungeonConsoleController(c, view);
    cc.quitGame();
    assertEquals("quit\n", gameLog.toString());
  }

  @Test
  public void testEverything() {
    Appendable gameLog = new StringBuilder();
    IDungeon c = new DungeonMock(gameLog);
    DungeonView view = new DungeonMockView(gameLog);
    DungeonConsoleController cc = new DungeonConsoleController(c, view);
    cc.startGame();
    cc.movePlayer(Direction.NORTH);
    cc.pickUp("Arrows", 2);
    cc.pickUp("Treasure", 0);
    cc.shoot(Direction.NORTH, 2);
    cc.handle("There are no arrows here.");
    cc.quitGame();
    assertEquals("make visible\n" + "setIntro, controller added\n" + "move NORTH\n"
            + "setModel, model and controller added\n"
            + "pickWeapon 2setModel, model and controller added\n" + "pickTreasure\n"
            + "setModel, model and controller added\n"
            + "shoot NORTH2setModel, model and controller added\n"
            + "popUpsThere are no arrows here.\n" + "quit\n", gameLog.toString());
  }
}
