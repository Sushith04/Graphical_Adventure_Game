package view;

import controller.IDungeonConsoleController;
import model.IDungeonReadOnly;

/**
 * A view for Dungeon: display the game board and provide visual interface for users.
 */
public interface DungeonView {

  /**
   * Make the view visible to start the game session.
   */
  void makeVisible();

  /**
   * Sets the intro panel to the view and makes it visible.
   *
   * @param controller is the controller
   */
  void setIntro(IDungeonConsoleController controller);

  /**
   * Sets the game panel to the view and makes it visible.
   *
   * @param model      is the created model
   * @param controller is the controller
   */
  void setModel(IDungeonReadOnly model, IDungeonConsoleController controller);

  /**
   * Whenever an exception is caught, the popUps method is called to display the message onto
   * the screen.
   *
   * @param s is the message the popup should display
   */
  void popUps(String s);

  /**
   * Closes the game when quit is clicked on the game menu.
   */
  void close();
}
