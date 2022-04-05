package controller;

import java.util.LinkedList;
import java.util.Objects;

import model.Direction;
import model.Dungeon;
import model.IDungeon;
import model.MinRandomGenerator;
import model.RandomNumber;
import model.RandomNumberGenerator;
import view.DungeonSwingView;
import view.DungeonView;

/**
 * This controller is used to implement a console controller for the Dungeon.
 */
public class DungeonConsoleController implements IDungeonConsoleController {
  private final DungeonView view;
  private IDungeon dungeon;
  private LinkedList<Integer> reuse;

  /**
   * Constructor for the controller.
   */
  public DungeonConsoleController() {
    this.view = new DungeonSwingView();
  }

  /**
   * Constructor for the controller that is used for testing.
   *
   * @param model is the dungeon model
   * @param view  is the dungeon view
   */
  public DungeonConsoleController(IDungeon model, DungeonView view) {
    this.dungeon = model;
    this.view = view;
  }

  @Override
  public void startGame() {
    view.makeVisible();
    moveToIntro();
  }

  @Override
  public void createModel(int row, int col, Boolean wrap, int inter, int percentage, int otyughs
          , String name) {
    try {
      RandomNumberGenerator rand = new RandomNumber();
      dungeon = new Dungeon(row, col, wrap, inter, percentage, otyughs, name, rand);
      this.reuse = dungeon.getRandomList();
      view.setModel(dungeon, this);
    } catch (IllegalArgumentException e) {
      handle(e.getMessage());
    }
  }

  @Override
  public void reuseDungeon() {
    dungeon = new Dungeon(dungeon.getRows(), dungeon.getColumns(), dungeon.getWrapping()
            , dungeon.getInterconnectivity(), dungeon.getTreasureAndArrowPercentage()
            , dungeon.getNoOfOtyughs(), dungeon.getName(), new MinRandomGenerator(reuse));
    view.setModel(dungeon, this);
  }

  private void moveToIntro() {
    view.setIntro(this);
  }

  @Override
  public void movePlayer(Direction d) {
    try {
      dungeon.move(d);
    } catch (IllegalArgumentException ignored) {
    }
    view.setModel(dungeon, this);
  }

  @Override
  public void pickUp(String s, int count) {
    try {
      if (Objects.equals(s, "Treasure")) {
        dungeon.pickTreasure();
      }
      if (Objects.equals(s, "Arrows")) {
        dungeon.pickWeapon(count);
      }
    } catch (IllegalArgumentException | IllegalStateException e) {
      handle(e.getMessage());
    }
    view.setModel(dungeon, this);
  }

  @Override
  public void shoot(Direction d, int noOfCaves) {
    try {
      String str = dungeon.shootArrow(d, noOfCaves);
      switch (str) {
        case "You did not land a hit!!!":
          view.popUps("You did not land a hit!!!");
          break;
        case "You shoot an arrow.\nYou hear a little howl in the distance.":
          view.popUps("You hear a little howl in the distance.");
          break;
        case "You shoot an arrow.\nYou hear a great howl in the distance.":
          view.popUps("You hear a great howl in the distance.");
          break;
        case "You are out of arrows, explore the dungeon to find more.\n":
          view.popUps("You are out of arrows, explore the dungeon to find more.");
          break;
        default:
          break;
      }
    } catch (IllegalArgumentException | IllegalStateException e) {
      handle(e.getMessage());
    }
    view.setModel(dungeon, this);
  }

  @Override
  public void handle(String s) {
    view.popUps(s);
  }

  @Override
  public void quitGame() {
    view.close();
  }
}