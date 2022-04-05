import java.io.InputStreamReader;

import controller.DungeonConsoleController;
import controller.DungeonController;
import model.Dungeon;
import model.IDungeon;
import model.RandomNumber;
import model.RandomNumberGenerator;

/**
 * Run a Dungeon game interactively on the console.
 */
public class Main {
  /**
   * Run a Dungeon game interactively on the console.
   */
  public static void main(String[] args) {
    if (args.length > 0) {
      try {
        Readable input = new InputStreamReader(System.in);
        Appendable output = System.out;
        RandomNumberGenerator rand = new RandomNumber();
        int rows = Integer.parseInt(args[0]);
        int columns = Integer.parseInt(args[1]);
        Boolean wrapping = Boolean.parseBoolean(args[2]);
        int interconnectivity = Integer.parseInt(args[3]);
        int percentage = Integer.parseInt(args[4]);
        int otyughCount = Integer.parseInt(args[5]);
        String name = String.valueOf(args[6]);
        IDungeon dungeon = new Dungeon(rows, columns, wrapping, interconnectivity, percentage
                , otyughCount, name, rand);
        new DungeonController(input, output).playGame(dungeon);
      } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
        System.out.println("Enter valid input values");
        System.exit(0);
      }
    } else {
      DungeonConsoleController start = new DungeonConsoleController();
      start.startGame();
    }
  }
}
