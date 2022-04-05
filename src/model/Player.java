package model;

/**
 * The player will explore the entire world by traveling from cave to cave through the tunnels
 * that connect them. While travelling through the caves, the player is to collect all the treasure
 * that are located in that cave and reach the end cave.
 */
public class Player implements IPlayer {

  private final String name;
  private ILocation currentLocation;
  private int arrows;
  private int health;
  private int rCount;
  private int sCount;
  private int dCount;

  /**
   * Constructs a Player object and initializes it to the name of the player.
   *
   * @param name is the name of the player
   */
  public Player(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Enter a valid name");
    }

    this.name = name;
    this.currentLocation = currentLocation();
    this.arrows = 3;
    this.health = 100;
    this.rCount = 0;
    this.sCount = 0;
    this.dCount = 0;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getInventory() {
    return "Treasures picked up-> Rubies: " + rCount + ", Sapphires: " + sCount + ", Diamonds: "
            + dCount;
  }

  void setInventory(int rCount, int sCount, int dCount) {
    this.rCount += rCount;
    this.sCount += sCount;
    this.dCount += dCount;
  }

  @Override
  public ILocation currentLocation() {
    return currentLocation;
  }

  void setCurrentLocation(ILocation l) {
    if (l == null) {
      throw new IllegalArgumentException("Location is null");
    }
    ILocation temporaryLocation;
    temporaryLocation = l;
    this.currentLocation = temporaryLocation;
  }

  @Override
  public int getArrows() {
    return arrows;
  }

  void setArrows(int arrowCount) {
    int count;
    count = arrowCount;
    this.arrows = count;
  }

  @Override
  public int getHealth() {
    return health;
  }

  void setHealth() {
    this.health = 0;
  }

  int getRubyCount() {
    return rCount;
  }

  int getDiamondCount() {
    return dCount;
  }

  int getSapphireCount() {
    return sCount;
  }


}
