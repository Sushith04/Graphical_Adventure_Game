package model;

/**
 * They are adapted to eat whatever organic material that they can find, but love it when fresh
 * meat happens into the cave in which they dwell. This means that a player entering a cave with
 * an Otyugh that has not been slayed will be killed and eaten (see next section on how to slay an
 * Otyugh).
 */
class Otyugh implements IOtyugh {

  private int health;

  Otyugh() {
    this.health = getHealth();
  }

  @Override
  public int getHealth() {
    return this.health;
  }

  @Override
  public void setHealth(int healthPoints) {
    int aid;
    aid = healthPoints;
    this.health = aid;
  }
}
