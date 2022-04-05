package model;

import java.util.List;
import java.util.Map;

/**
 * This interface represents the locations in the dungeon that maybe either tunnel or cave.
 */
public interface ILocation {

  /**
   * Gets the name of the location.
   *
   * @return the name of the location
   */
  int getName();

  /**
   * Get the list of treasures in a particular location.
   *
   * @return the list of treasures in a particular location
   */
  List<Treasure> getTreasure();

  /**
   * Gets the number of rubies in a particular location.
   *
   * @return number of rubies in a particular location
   */
  int getRubies();

  /**
   * Gets the number of diamonds in a particular location.
   *
   * @return number of diamonds in a particular location
   */
  int getDiamonds();

  /**
   * Get the number of sapphires in a particular location.
   *
   * @return the number of sapphires in a particular location
   */
  int getSapphires();

  /**
   * Get the number of arrows in a particular location.
   *
   * @return the number of arrows in a particular location
   */
  int getWeapon();

  /**
   * Get the number of otyughs in a particular location.
   *
   * @return the type of the location, whether it is a cave or a tunnel
   */
  int otyughCount();

  /**
   * Get the otyugh health.
   *
   * @return the otyugh health
   */
  int getOtyughHealth();

  /**
   * Get the type of the location, whether it is a cave or a tunnel.
   *
   * @return the type of the location, whether it is a cave or a tunnel
   */
  LocationType typeOfLocation();

  /**
   * Get the x-coordinate of the location in the dungeon.
   *
   * @return the x-coordinate of the location in the dungeon
   */
  int getX();

  /**
   * Get the y-coordinate of the location in the dungeon.
   *
   * @return the y-coordinate of the location in the dungeon
   */
  int getY();

  /**
   * This method is used to show all caves with their following neighbours.
   *
   * @return the neighbours of a particular location.
   */
  Map<Direction, ILocation> getMap();
}
