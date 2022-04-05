import org.junit.Before;
import org.junit.Test;

import model.RandomNumber;
import model.RandomNumberGenerator;

import static org.junit.Assert.assertTrue;

/**
 * This method is used to check the functionalities of the random number generator.
 */
public class RandomNumberTest {
  RandomNumberGenerator generator;

  @Before
  public void setUp() {
    generator = new RandomNumber();
  }

  @Test
  public void generateRandomNumber() {
    for (int i = 0; i <= 1000; i++) {
      assertTrue(generator.generateRandomNumber(1, 15) >= 1
              && generator.generateRandomNumber(1, 15) <= 15);
    }
  }
}