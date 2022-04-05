import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Direction;
import model.IDungeon;
import model.ILocation;
import model.Smell;

/**
 * This is a mock model for the dungeon.
 */
class DungeonMock implements IDungeon {
  public static String MOCKSTRING;
  private final Appendable log;

  DungeonMock(Appendable log) {
    this.log = log;
  }

  @Override
  public String beginGame() {
    MOCKSTRING = "beginGame";
    return MOCKSTRING;
  }

  @Override
  public String move(Direction direction) {
    MOCKSTRING = "move " + direction;
    try {
      log.append("move ").append(String.valueOf(direction)).append("\n");
    } catch (IOException e) {
      // do nothing
    }
    return MOCKSTRING;
  }

  @Override
  public String pickWeapon(int noOfArrows) {
    try {
      log.append("pickWeapon ").append(String.valueOf(noOfArrows));
    } catch (IOException e) {
      // do nothing
    }
    MOCKSTRING = "pickWeapon " + noOfArrows;
    return MOCKSTRING;
  }

  @Override
  public String pickTreasure() {
    try {
      log.append("pickTreasure\n");
    } catch (IOException e) {
      // do nothing
    }
    MOCKSTRING = "pickTreasure";
    return MOCKSTRING;
  }

  @Override
  public String shootArrow(Direction direction, int distance) {
    try {
      log.append("shoot ").append(String.valueOf(direction)).append(String.valueOf(distance));
    } catch (IOException e) {
      // do nothing
    }
    MOCKSTRING = "shootArrow " + direction + " " + distance;
    return MOCKSTRING;
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public Smell checkSmell(ILocation playerLocation) {
    return null;
  }

  @Override
  public int getRuby() {
    return 0;
  }

  @Override
  public int getDiamond() {
    return 0;
  }

  @Override
  public int getSapphire() {
    return 0;
  }

  @Override
  public LinkedList<Integer> getRandomList() {
    return new LinkedList<>();
  }

  @Override
  public ILocation getStart() {
    return null;
  }

  @Override
  public ILocation getEnd() {
    return null;
  }

  @Override
  public String getName() {
    return "";
  }

  @Override
  public int getRows() {
    return 0;
  }

  @Override
  public int getColumns() {
    return 0;
  }

  @Override
  public boolean getWrapping() {
    return false;
  }

  @Override
  public int getInterconnectivity() {
    return 0;
  }

  @Override
  public int getTreasureAndArrowPercentage() {
    return 0;
  }

  @Override
  public int getNoOfOtyughs() {
    return 0;
  }

  @Override
  public int getPlayerArrows() {
    return 0;
  }

  @Override
  public ILocation getDungeon(Integer name) {
    return null;
  }

  @Override
  public List<Integer> visitedLocations() {
    return new ArrayList<>();
  }

  @Override
  public ILocation getPlayerLocation() {
    return null;
  }

  @Override
  public List<String> description() {
    return new ArrayList<>();
  }

  @Override
  public List<String> playerDescription() {
    return new ArrayList<>();
  }
}
