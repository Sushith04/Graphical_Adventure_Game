package model;

/**
 * The world for our game consists of a dungeon, a network of tunnels and caves that are
 * interconnected so that player can explore the entire world by traveling from cave to cave
 * through the tunnels that connect them. Each location in the grid represents a location in
 * the dungeon where a player can explore and can be connected to at most four other locations.
 */
public interface IDungeon extends IDungeonReadOnly {

  /**
   * Gets the starting point and all necessary initial details before starting the game.
   *
   * @return the starting point and all necessary initial details before starting the game
   **/
  String beginGame();

  /**
   * This method is used to move the player from their current location using
   * the possible moves (north, east, south, west).
   *
   * @param direction is the direction the player has to move
   * @return the information after picking up the weapon
   */
  String move(Direction direction);

  /**
   * This method is used to pick up the arrows in the current location.
   *
   * @param noOfArrows is the number of arrows to be picked up
   * @return the information after picking up the weapon
   */
  String pickWeapon(int noOfArrows);

  /**
   * This method picks the treasures from the locations the player has visited in the dungeon
   * if there is any. These treasures are added to the inventory of the player.
   *
   * @return the information after picking up the treasure
   */
  String pickTreasure();

  /**
   * This method is used to pick up the arrows in the current location.
   *
   * @param direction is the direction in which the arrow should be shot
   * @param distance  is the number of caves (but not tunnels) that an arrow travels
   */
  String shootArrow(Direction direction, int distance);
}
