package model;

/**
 * They are adapted to eat whatever organic material that they can find, but love it when fresh
 * meat happens into the cave in which they dwell. This means that a player entering a cave with
 * an Otyugh that has not been slayed will be killed and eaten (see next section on how to slay an
 * Otyugh).
 */
interface IOtyugh {

  /**
   * Gets the health of the Otyugh.
   *
   * @return the health of the Otyugh
   */
  int getHealth();

  /**
   * Sets the health of the location.
   *
   * @param healthPoints is the health of the Otyugh
   */
  void setHealth(int healthPoints);
}
