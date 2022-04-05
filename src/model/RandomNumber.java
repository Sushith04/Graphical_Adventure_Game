package model;

import java.util.LinkedList;
import java.util.Random;

/**
 * A random number generator will give a random number from the given lower and upper bounds.
 */
public class RandomNumber extends Random implements RandomNumberGenerator {
  Random random = new Random();
  LinkedList<Integer> randomList = new LinkedList<>();

  @Override
  public int generateRandomNumber(int lowerBound, int upperBound) {
    int ran = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
    randomList.add(ran);
    return ran;
  }

  LinkedList<Integer> getListOfRandom() {
    return randomList;
  }
}
