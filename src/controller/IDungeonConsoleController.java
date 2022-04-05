package controller;

import model.Direction;

/**
 * Represents a Controller for Dungeon: handle user moves by executing them using the model;
 * convey move outcomes to the user in some form.
 */
public interface IDungeonConsoleController {

  /**
   * Execute a single game of dungeon given a Dungeon Model. When the game is over,
   * the startGame method ends.
   */
  void startGame();

  /**
   * Creates a dungeon given all the dungeon parameters.
   *
   * @param row        is the number of rows
   * @param col        is the number of columns
   * @param wrap       tells whether the dungeon locations "wrap" to the
   *                   other side of the grid
   * @param inter      tells how many paths should be there from every cave in the
   *                   dungeon to every other cave in the dungeon
   * @param percentage is the treasure to be added to a specified percentage of
   *                   caves
   * @param otyughs    is the number of otyughs in the dungeon
   * @param name       is the name of the player
   */
  void createModel(int row, int col, Boolean wrap, int inter, int percentage, int otyughs
          , String name);

  /**
   * Reuses the same dungeon taking the parameters of the existing dungeon into consideration.
   */
  void reuseDungeon();

  /**
   * Quits the game when quit is clicked on the game menu.
   */
  void quitGame();

  /**
   * Moves the player on the board based on the user interaction.
   *
   * @param d is the direction in which the player should be moved
   */
  void movePlayer(Direction d);

  /**
   * Picks up arrows or treasure from the board based on the user interaction.
   *
   * @param s     is the item that should be picked
   * @param count is the number of the respective items to be picked
   */
  void pickUp(String s, int count);

  /**
   * Shoots arrows in the dungeon based on the user interaction.
   *
   * @param d         is the direction in which an arrow should be shot
   * @param noOfCaves is the number of caves the arrow should travel
   */
  void shoot(Direction d, int noOfCaves);

  /**
   * Whenever an exception is caught, the handle method handles those exceptions by sending them
   * to popups.
   *
   * @param s is the message the popup should display
   */
  void handle(String s);
}
