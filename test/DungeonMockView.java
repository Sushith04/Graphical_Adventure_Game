import java.io.IOException;

import controller.IDungeonConsoleController;
import model.IDungeonReadOnly;
import view.DungeonView;

/**
 * This is a mock view for the dungeon.
 */
class DungeonMockView implements DungeonView {
  private final Appendable log;

  DungeonMockView(Appendable log) {
    this.log = log;
  }

  @Override
  public void makeVisible() {
    try {
      log.append("make visible\n");
    } catch (IOException e) {
      // do nothing
    }
  }

  @Override
  public void setIntro(IDungeonConsoleController controller) {
    try {
      log.append("setIntro, controller added\n");
    } catch (IOException e) {
      // do nothing
    }
  }

  @Override
  public void setModel(IDungeonReadOnly model, IDungeonConsoleController controller) {
    try {
      log.append("setModel, model and controller added\n");
    } catch (IOException e) {
      // do nothing
    }
  }

  @Override
  public void popUps(String s) {
    try {
      log.append("popUps").append(s).append("\n");
    } catch (IOException e) {
      // do nothing
    }
  }

  @Override
  public void close() {
    try {
      log.append("quit\n");
    } catch (IOException e) {
      // do nothing
    }
  }
}
