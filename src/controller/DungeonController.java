package controller;

import java.io.IOException;
import java.util.Scanner;

import model.Direction;
import model.IDungeon;

/**
 * This controller is used to implement a console controller for the Dungeon.
 */
public class DungeonController implements IDungeonController {
  private final Appendable out;
  private final Scanner scan;

  /**
   * Constructor for the controller.
   *
   * @param in  the source to read from
   * @param out the target to print to
   */
  public DungeonController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
  }

  @Override
  public void playGame(IDungeon model) {
    if (model == null) {
      throw new IllegalArgumentException("Invalid model.");
    }
    try {
      out.append(model.beginGame());
      while (!model.isGameOver()) {
        out.append("Move, Pickup, Shoot or Quit (M-P-S-Q)?");
        String in = scan.next();
        switch (in.toUpperCase()) {
          case "Q":
          case "QUIT":
            out.append("Game quit! Ending game...");
            return;
          case "M":
          case "MOVE":
            try {
              out.append("Where to (N-S-E-W)? ");
              while (true) {
                String direction = scan.next();
                if (direction.equalsIgnoreCase("n") || direction.equalsIgnoreCase("north")) {
                  out.append(model.move(Direction.NORTH));
                  break;
                } else if (direction.equalsIgnoreCase("s") || direction.equalsIgnoreCase("south")) {
                  out.append(model.move(Direction.SOUTH));
                  break;
                } else if (direction.equalsIgnoreCase("w") || direction.equalsIgnoreCase("west")) {
                  out.append(model.move(Direction.WEST));
                  break;
                } else if (direction.equalsIgnoreCase("e") || direction.equalsIgnoreCase("east")) {
                  out.append(model.move(Direction.EAST));
                  break;
                } else {
                  out.append("Enter a valid direction: ");
                }
              }
            } catch (IllegalArgumentException | IllegalStateException move) {
              out.append(move.getMessage()).append("\n");
            }
            break;
          case "P":
          case "PICKUP":
            try {
              out.append("What (T-A)? ");
              while (true) {
                String item = scan.next();
                if (item.equalsIgnoreCase("a")
                        || item.equalsIgnoreCase("arrow")
                        || item.equalsIgnoreCase("arrows")) {
                  out.append("How many? ");
                  int countArrows = Integer.parseInt(scan.next());
                  out.append(model.pickWeapon(countArrows));
                  break;
                } else if (item.equalsIgnoreCase("t")
                        || item.equalsIgnoreCase("treasure")
                        || item.equalsIgnoreCase("treasures")) {
                  out.append(model.pickTreasure());
                  break;
                } else {
                  out.append("Enter a valid item: ");
                }
              }
            } catch (IllegalArgumentException | IllegalStateException pick) {
              out.append(pick.getMessage()).append("\n");
            }
            break;
          case "S":
          case "SHOOT":
            int caveCount = 0;
            try {
              out.append("No. of caves (1-5)? ");
              while (true) {
                caveCount = Integer.parseInt(scan.next());
                if (caveCount < 1 || caveCount > 5) {
                  out.append("Enter a valid distance: ");
                  continue;
                }
                break;
              }
            } catch (IllegalArgumentException | IllegalStateException invalidDistance) {
              out.append(invalidDistance.getMessage()).append("\n");
            }
            try {
              out.append("In which direction (N-S-E-W)? ");
              while (true) {
                String path = scan.next();
                if (path.equalsIgnoreCase("n") || path.equalsIgnoreCase("north")) {
                  out.append(model.shootArrow(Direction.NORTH, caveCount));
                  break;
                } else if (path.equalsIgnoreCase("s") || path.equalsIgnoreCase("south")) {
                  out.append(model.shootArrow(Direction.SOUTH, caveCount));
                  break;
                } else if (path.equalsIgnoreCase("w") || path.equalsIgnoreCase("west")) {
                  out.append(model.shootArrow(Direction.WEST, caveCount));
                  break;
                } else if (path.equalsIgnoreCase("e") || path.equalsIgnoreCase("east")) {
                  out.append(model.shootArrow(Direction.EAST, caveCount));
                  break;
                } else {
                  out.append("Enter a valid direction: ");
                }
              }
            } catch (IllegalArgumentException | IllegalStateException invalidDirection) {
              out.append(invalidDirection.getMessage()).append("\n");
            }
            break;
          default:
            out.append(String.format("Unknown command %s", in)).append("\n");
            break;
        }
      }
    } catch (IOException ioException) {
      throw new IllegalStateException(ioException.getMessage());
    }
  }
}
