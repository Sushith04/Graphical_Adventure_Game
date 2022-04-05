package model;

import java.util.LinkedList;

/**
 * A predefined list of random numbers from the given lower and upper bounds.
 */
public class MinRandomGenerator implements RandomNumberGenerator {
  private final LinkedList<Integer> fixedRand;

  /**
   * Constructs a MinRandomGenerator and initializes it to the n.
   *
   * @param n is the random numbers
   */
  public MinRandomGenerator(int... n) {
    fixedRand = new LinkedList<>();
    for (int i : n) {
      this.fixedRand.add(i);
    }
  }

  /**
   * Constructs a MinRandomGenerator and initializes it to the n.
   *
   * @param reusableList is the random numbers list
   */
  public MinRandomGenerator(LinkedList<Integer> reusableList) {
    fixedRand = new LinkedList<>();
    this.fixedRand.addAll(reusableList);
  }

  @Override
  public int generateRandomNumber(int min, int max) {
    if (fixedRand.isEmpty()) {
      RandomNumberGenerator rand = new RandomNumber();
      int extra = rand.generateRandomNumber(0, 2);
      fixedRand.add(extra);
    }
    int x = this.fixedRand.get(0);
    fixedRand.remove();
    return x;
  }
}

