package model;

import java.util.LinkedList;
import java.util.List;

/**
 * This is a read only interface for the model and is extended by the IDungeon.
 */
public interface IDungeonReadOnly {

  /**
   * Gets the starting point of where the player starts in dungeon.
   *
   * @return the starting point of where the player starts in dungeon
   **/
  ILocation getStart();

  /**
   * Gets the end point of where the player ends in dungeon.
   *
   * @return the end point of where the player ends in dungeon
   **/
  ILocation getEnd();

  /**
   * Gets the name of the player.
   *
   * @return the name of the player
   **/
  String getName();

  /**
   * Gets the number of rows in the created dungeon.
   *
   * @return the number of rows in the created dungeon
   **/
  int getRows();

  /**
   * Gets the number of columns in the created dungeon.
   *
   * @return the number of columns in the created dungeon
   **/
  int getColumns();

  /**
   * Gets whether the dungeon is wrapping or not.
   *
   * @return whether the dungeon is wrapping or not
   **/
  boolean getWrapping();

  /**
   * Gets the number of interconnectivity in the created dungeon.
   *
   * @return the number of interconnectivity in the created dungeon
   **/
  int getInterconnectivity();

  /**
   * Gets the percentage of treasure and arrows in the created dungeon.
   *
   * @return the percentage of treasure and arrows in the created dungeon
   **/
  int getTreasureAndArrowPercentage();

  /**
   * Gets the number of otyughs in the created dungeon.
   *
   * @return the number of otyughs in the created dungeon
   **/
  int getNoOfOtyughs();

  /**
   * Gets the number of arrows the player has.
   *
   * @return the number of arrows the player has
   **/
  int getPlayerArrows();

  /**
   * Gets the location based on the location number.
   *
   * @param name is the location name
   * @return the location based on the location number
   **/
  ILocation getDungeon(Integer name);

  /**
   * Gets the list of locations names that were visited by the player.
   *
   * @return the list of locations names that were visited by the player
   **/
  List<Integer> visitedLocations();

  /**
   * Get the name of the player and list of treasures that the player has picked.
   *
   * @return the name of the player and the list of treasures that the player has picked
   **/
  ILocation getPlayerLocation();

  /**
   * Get the smell in the player's location.
   *
   * @param playerLocation is the player's location
   * @return the smell in the player's location
   **/
  Smell checkSmell(ILocation playerLocation);

  /**
   * Provides a description of the player that, at a minimum, includes a description of what
   * treasure the player has collected since the start of the game.
   *
   * @return the List of strings consisting of the required information
   */
  List<String> description();

  /**
   * Provides a description of the player that has their name and their inventory.
   *
   * @return a description of the player that has their name and their inventory
   */
  List<String> playerDescription();

  /**
   * Checks whether the game is over.
   *
   * @return whether the game is over
   */
  boolean isGameOver();

  /**
   * Gets the number of rubies a player has.
   *
   * @return number of rubies a player has
   */
  int getRuby();

  /**
   * Gets the number of diamonds a player has.
   *
   * @return number of diamonds a player has
   */
  int getDiamond();

  /**
   * Gets the number of sapphires a player has.
   *
   * @return number of sapphires a player has
   */
  int getSapphire();

  /**
   * Gets the list of numbers that are used when a retry is called.
   *
   * @return the list of numbers that are used when a retry is called
   */
  LinkedList<Integer> getRandomList();
}
