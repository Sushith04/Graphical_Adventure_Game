package model;

/**
 * An interface for random number generator.
 * A random number generator will give a random number from the given lower and upper bounds.
 */
public interface RandomNumberGenerator {

  /**
   * Gets a random number within the specified bounds.
   *
   * @return a random number within the specified bounds.
   */
  int generateRandomNumber(int lowerBound, int upperBound);
}
