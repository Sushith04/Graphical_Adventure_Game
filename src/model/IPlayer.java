package model;

/**
 * An interface to the player that will explore the entire world by traveling from cave to cave
 * through the tunnels that connect them. While travelling through the caves, the player is to
 * collect all the treasure that are located in that cave and reach the end cave.
 */
public interface IPlayer {

  /**
   * Get the name of the player.
   *
   * @return the name of the player
   **/
  String getName();

  /**
   * Get the list of treasures that the player has picked.
   *
   * @return the list of treasures that the player has picked
   **/
  String getInventory();

  /**
   * Gets the current location of the player in the dungeon.
   *
   * @return the current location of the player in the dungeon
   **/
  ILocation currentLocation();

  /**
   * Gets the total number of arrows that the player has.
   *
   * @return the total number of arrows that the player has
   **/
  int getArrows();

  /**
   * Gets the health of the player.
   *
   * @return the health of the player
   **/
  int getHealth();
}
