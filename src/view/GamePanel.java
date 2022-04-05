package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.IDungeonConsoleController;
import model.Direction;
import model.IDungeonReadOnly;
import model.Smell;

class GamePanel extends JPanel {

  private final IDungeonReadOnly model;
  private final IDungeonConsoleController controller;
  private final GridBagConstraints constraints;
  private final JMenuBar menuBar;
  private final JPanel screen;
  private final JPanel boardPanel;
  private JMenuItem newGame;
  private JMenuItem same;
  private JMenuItem different;
  private JMenuItem quit;

  /**
   * Constructor for the game panel.
   *
   * @param model      is the dungeon model
   * @param controller is the controller
   */
  GamePanel(IDungeonReadOnly model, IDungeonConsoleController controller) {
    this.model = model;
    this.controller = controller;
    this.setLayout(new BorderLayout());
    menuBar = new JMenuBar();
    boardPanel = new JPanel();
    boardPanel.setLayout(new GridBagLayout());
    boardPanel.setBackground(Color.black);
    constraints = new GridBagConstraints();
    screen = new JPanel();
    createDungeon();
  }

  private void createDungeon() {
    JLabel jLabel = new JLabel();
    JMenu home = new JMenu("Menu");
    JMenu restart = new JMenu("Restart");
    newGame = new JMenuItem("Home");
    same = new JMenuItem("Same Dungeon");
    different = new JMenuItem("Different Dungeon");
    quit = new JMenuItem("Quit");
    home.add(newGame);
    restart.add(same);
    restart.add(different);
    home.add(restart);
    home.add(quit);
    menuBar.add(home);
    JMenu info = new JMenu("Game Settings");
    JMenuItem row = new JMenuItem("Rows: " + model.getRows());
    info.add(row);
    JMenuItem col = new JMenuItem("Columns: " + model.getColumns());
    info.add(col);
    if (model.getWrapping()) {
      JMenuItem wrap = new JMenuItem("Wrapping: Yes");
      info.add(wrap);
    } else if (!model.getWrapping()) {
      JMenuItem wrap = new JMenuItem("Wrapping: No");
      info.add(wrap);
    }
    JMenuItem inter = new JMenuItem("Interconnectivity: " + model.getInterconnectivity());
    info.add(inter);
    JMenuItem percent = new JMenuItem("Artifacts Percentage: "
            + model.getTreasureAndArrowPercentage());
    info.add(percent);
    JMenuItem otyugh = new JMenuItem("Otyughs: " + model.getNoOfOtyughs());
    info.add(otyugh);
    menuBar.add(info);
    add(menuBar, BorderLayout.NORTH);
    JPanel location = new JPanel();
    JPanel locationDescription = new JPanel();
    locationDescription.setLayout(new GridBagLayout());
    GridBagConstraints lc = new GridBagConstraints();
    location.add(locationDescription);
    JPanel player1 = new JPanel();
    JPanel playerDescription = new JPanel();
    playerDescription.setLayout(new GridBagLayout());
    GridBagConstraints pc = new GridBagConstraints();
    player1.add(playerDescription);
    player1.setBackground(Color.lightGray);
    for (int i = 0; i < model.getRows() * model.getColumns(); i++) {
      JPanel jPanel = new JPanel();
      jPanel.setName("" + model.getDungeon(i).getName());
      int moveName = Integer.parseInt(jPanel.getName());
      List<Direction> directionList = new ArrayList<>(model.getDungeon(i).getMap().keySet());
      StringBuilder sb = new StringBuilder();
      for (Direction direction : directionList) {
        sb.append(direction.toString().charAt(0));
      }
      char[] chars = sb.toString().toCharArray();
      Arrays.sort(chars);
      sb = new StringBuilder();
      sb.append(new String(chars));
      sb.append(".png");
      try {
        BufferedImage image = ImageIO.read(ClassLoader.getSystemResource(
                ("images/" + sb)));
        BufferedImage stench01 = ImageIO.read(ClassLoader
                .getSystemResource("images/stench01.png"));
        BufferedImage stench02 = ImageIO.read(ClassLoader
                .getSystemResource("images/stench02.png"));
        BufferedImage monster = ImageIO.read(ClassLoader
                .getSystemResource("images/otyugh.png"));
        BufferedImage wArrow = ImageIO.read(ClassLoader
                .getSystemResource("images/arrow-white.png"));
        BufferedImage bArrow = ImageIO.read(ClassLoader
                .getSystemResource("images/arrow-black.png"));
        BufferedImage ruby = ImageIO.read(ClassLoader
                .getSystemResource("images/ruby.png"));
        BufferedImage diamond = ImageIO.read(ClassLoader
                .getSystemResource("images/diamond.png"));
        BufferedImage sapphire = ImageIO.read(ClassLoader
                .getSystemResource("images/emerald.png"));
        BufferedImage player = ImageIO.read(ClassLoader
                .getSystemResource("images/char.png"));
        BufferedImage blank = ImageIO.read(ClassLoader
                .getSystemResource("images/blank.png"));
        JLabel title1 = new JLabel("Location Description");
        title1.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        lc.gridx = 0;
        lc.gridy = 0;
        lc.insets = new Insets(10, 0, 0, 0);
        locationDescription.add(title1, lc);
        StringBuilder sb1 = new StringBuilder();
        Set<Direction> stringsSet = model.getPlayerLocation().getMap().keySet();
        if (stringsSet.contains(Direction.NORTH)) {
          sb1.append("North ");
        }
        if (stringsSet.contains(Direction.WEST)) {
          sb1.append("West ");
        }
        if (stringsSet.contains(Direction.SOUTH)) {
          sb1.append("South ");
        }
        if (stringsSet.contains(Direction.EAST)) {
          sb1.append("East ");
        }
        JLabel lArrows = new JLabel(new ImageIcon(bArrow));
        lArrows.setText(": " + model.getPlayerLocation().getWeapon());
        lArrows.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lc.gridx = 0;
        lc.gridy = 1;
        lc.insets = new Insets(30, 0, 0, 0);
        locationDescription.add(lArrows, lc);
        JLabel lRuby = new JLabel(new ImageIcon(ruby));
        lRuby.setText("     : " + model.getPlayerLocation().getRubies());
        lRuby.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lc.gridx = 0;
        lc.gridy = 2;
        lc.insets = new Insets(25, 0, 0, 0);
        locationDescription.add(lRuby, lc);
        JLabel lDiamond = new JLabel(new ImageIcon(diamond));
        lDiamond.setText("     : " + model.getPlayerLocation().getDiamonds());
        lDiamond.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lc.gridx = 0;
        lc.gridy = 3;
        lc.insets = new Insets(25, 0, 0, 0);
        locationDescription.add(lDiamond, lc);
        JLabel lSapphire = new JLabel(new ImageIcon(sapphire));
        lSapphire.setText("     : " + model.getPlayerLocation().getSapphires());
        lSapphire.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lc.gridx = 0;
        lc.gridy = 4;
        lc.insets = new Insets(25, 0, 0, 0);
        locationDescription.add(lSapphire, lc);
        JLabel directions = new JLabel("Directions: " + sb1);
        directions.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lc.gridx = 0;
        lc.gridy = 5;
        lc.insets = new Insets(25, 0, 0, 0);
        locationDescription.add(directions, lc);
        String s = "";
        if (model.checkSmell(model.getPlayerLocation()) == Smell.NO_SMELL) {
          s = "Nothing fishy here";
        } else if (model.checkSmell(model.getPlayerLocation()) == Smell.LESS_PUNGENT) {
          s = "You smell something mild here";
        } else if (model.checkSmell(model.getPlayerLocation()) == Smell.MORE_PUNGENT) {
          s = "You smell something strong here";
        }
        JLabel smells = new JLabel(s);
        smells.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lc.gridx = 0;
        lc.gridy = 6;
        lc.insets = new Insets(25, 0, 0, 0);
        locationDescription.add(smells, lc);
        JLabel title2 = new JLabel("Player Description");
        title2.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        pc.gridx = 0;
        pc.gridy = 0;
        pc.insets = new Insets(10, 0, 0, 0);
        playerDescription.add(title2, pc);
        JLabel name = new JLabel("Name: " + model.getName());
        name.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        pc.gridx = 0;
        pc.gridy = 1;
        pc.insets = new Insets(25, 0, 0, 0);
        playerDescription.add(name, pc);
        JLabel pArrows = new JLabel(new ImageIcon(bArrow));
        pArrows.setText(": " + model.getPlayerArrows());
        pArrows.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        pc.gridx = 0;
        pc.gridy = 2;
        pc.insets = new Insets(20, 0, 0, 0);
        playerDescription.add(pArrows, pc);
        JLabel pRuby = new JLabel(new ImageIcon(ruby));
        pRuby.setText("     : " + model.getRuby());
        pRuby.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        pc.gridx = 0;
        pc.gridy = 3;
        pc.insets = new Insets(20, 0, 0, 0);
        playerDescription.add(pRuby, pc);
        JLabel pDiamond = new JLabel(new ImageIcon(diamond));
        pDiamond.setText("     : " + model.getDiamond());
        pDiamond.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        pc.gridx = 0;
        pc.gridy = 4;
        pc.insets = new Insets(20, 0, 0, 0);
        playerDescription.add(pDiamond, pc);
        JLabel pSapphire = new JLabel(new ImageIcon(sapphire));
        pSapphire.setText("     : " + model.getSapphire());
        pSapphire.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        pc.gridx = 0;
        pc.gridy = 5;
        pc.insets = new Insets(20, 0, 0, 0);
        playerDescription.add(pSapphire, pc);
        playerDescription.setBackground(Color.lightGray);
        Image scaledImage = image.getScaledInstance(image.getWidth() * 2
                , image.getHeight() * 2, Image.SCALE_SMOOTH);
        try {
          Image scaledStench01 = stench01.getScaledInstance(stench01.getWidth() * 2
                  , stench01.getHeight() * 2, Image.SCALE_SMOOTH);
          if (model.checkSmell(model.getDungeon(i)) == Smell.LESS_PUNGENT) {
            scaledImage = overlay(toBuffered(scaledImage), toBuffered(scaledStench01)
                    , 0, 0);
          }
          Image scaledStench02 = stench02.getScaledInstance(stench02.getWidth() * 2
                  , stench02.getHeight() * 2, Image.SCALE_SMOOTH);
          if (model.checkSmell(model.getDungeon(i)) == Smell.MORE_PUNGENT) {
            scaledImage = overlay(toBuffered(scaledImage), toBuffered(scaledStench02)
                    , 0, 0);
          }
          Image scaledMonster = monster.getScaledInstance(monster.getWidth() * 3 / 2
                  , monster.getHeight() * 3 / 2, Image.SCALE_SMOOTH);
          if (model.getDungeon(i).otyughCount() == 1) {
            scaledImage = overlay(toBuffered(scaledImage), toBuffered(scaledMonster)
                    , 20, 25);
          }
          Image scaledArrow = wArrow.getScaledInstance(wArrow.getWidth() * 2
                  , wArrow.getHeight() * 2, Image.SCALE_SMOOTH);
          if (model.getDungeon(i).getWeapon() > 0) {
            scaledImage = overlay(toBuffered(scaledImage), toBuffered(scaledArrow)
                    , 15, 12);
          }
          if (model.getDungeon(i).getRubies() > 0) {
            scaledImage = overlay(toBuffered(scaledImage), ruby, 5, 95);
          }
          if (model.getDungeon(i).getDiamonds() > 0) {
            scaledImage = overlay(toBuffered(scaledImage), diamond, 50, 97);
          }
          Image scaledSapphire = sapphire.getScaledInstance(sapphire.getWidth() * 4 / 5
                  , sapphire.getHeight() * 4 / 5, Image.SCALE_SMOOTH);
          if (model.getDungeon(i).getSapphires() > 0) {
            scaledImage = overlay(toBuffered(scaledImage), toBuffered(scaledSapphire)
                    , 95, 95);
          }
          Image scaledPlayer = player.getScaledInstance(player.getWidth() * 7 / 4
                  , player.getHeight() * 7 / 4, Image.SCALE_SMOOTH);
          if (model.getDungeon(i).getX() == model.getPlayerLocation().getX()
                  && model.getDungeon(i).getY() == model.getPlayerLocation().getY()) {
            scaledImage = overlay(toBuffered(scaledImage), toBuffered(scaledPlayer)
                    , 35, 26);
          }
          Image scaledBlank = blank.getScaledInstance(blank.getWidth() * 2
                  , blank.getHeight() * 2, Image.SCALE_SMOOTH);
          if (!model.visitedLocations().contains(model.getDungeon(i).getName())) {
            scaledImage = overlay(toBuffered(scaledImage), toBuffered(scaledBlank)
                    , 0, 0);
          }
        } catch (IOException e) {
          controller.handle(e.getMessage());
        }
        jLabel = new JLabel(new ImageIcon(toBuffered(scaledImage)));
        MouseAdapter clickAdapter = new MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            if (model.getPlayerLocation().getMap().containsKey(Direction.NORTH)
                    && model.getPlayerLocation().getMap().get(Direction.NORTH)
                    .getName() == moveName) {
              controller.movePlayer(Direction.NORTH);
              deathAndWinPopUp();
            } else if (model.getPlayerLocation().getMap().containsKey(Direction.SOUTH)
                    && model.getPlayerLocation().getMap().get(Direction.SOUTH)
                    .getName() == moveName) {
              controller.movePlayer(Direction.SOUTH);
              deathAndWinPopUp();
            } else if (model.getPlayerLocation().getMap().containsKey(Direction.EAST)
                    && model.getPlayerLocation().getMap().get(Direction.EAST)
                    .getName() == moveName) {
              controller.movePlayer(Direction.EAST);
              deathAndWinPopUp();
            } else if (model.getPlayerLocation().getMap().containsKey(Direction.WEST)
                    && model.getPlayerLocation().getMap().get(Direction.WEST)
                    .getName() == moveName) {
              controller.movePlayer(Direction.WEST);
              deathAndWinPopUp();
            }
          }
        };
        jPanel.addMouseListener(clickAdapter);
      } catch (IOException e) {
        controller.handle(e.getMessage());
      }
      constraints.gridx = model.getDungeon(i).getY();
      constraints.gridy = model.getDungeon(i).getX();
      constraints.insets = new Insets(-3, -3, -3, -3);
      jPanel.add(jLabel);
      boardPanel.add(jPanel, constraints);
      JScrollPane scroll = new JScrollPane(boardPanel);
      scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      scroll.getHorizontalScrollBar().setUnitIncrement(5);
      scroll.getVerticalScrollBar().setUnitIncrement(5);
      add(scroll);
      screen.setLayout(new BoxLayout(screen, BoxLayout.Y_AXIS));
      screen.add(location);
      screen.add(player1);
      JScrollPane scroll1 = new JScrollPane(screen);
      scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      scroll1.getHorizontalScrollBar().setUnitIncrement(6);
      scroll1.getVerticalScrollBar().setUnitIncrement(8);
      add(scroll1, BorderLayout.EAST);
    }
  }

  void listeners(IDungeonConsoleController controller) {
    newGame.addActionListener(e -> controller.startGame());
    different.addActionListener(e -> controller.createModel(model.getRows(), model.getColumns(), model.getWrapping()
            , model.getInterconnectivity(), model.getTreasureAndArrowPercentage()
            , model.getNoOfOtyughs(), model.getName()));
    same.addActionListener(e -> controller.reuseDungeon());
    quit.addActionListener(e -> controller.quitGame());
    keys();
  }

  private void keys() {
    screen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent
            .VK_UP, 0, false), "NORTH");
    screen.getActionMap().put("NORTH", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.movePlayer(Direction.NORTH);
        deathAndWinPopUp();
      }
    });
    screen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent
            .VK_DOWN, 0, false), "SOUTH");
    screen.getActionMap().put("SOUTH", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.movePlayer(Direction.SOUTH);
        deathAndWinPopUp();
      }
    });
    screen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent
            .VK_RIGHT, 0, false), "EAST");
    screen.getActionMap().put("EAST", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.movePlayer(Direction.EAST);
        deathAndWinPopUp();
      }
    });
    screen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent
            .VK_LEFT, 0, false), "WEST");
    screen.getActionMap().put("WEST", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.movePlayer(Direction.WEST);
        deathAndWinPopUp();
      }
    });
    screen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent
            .VK_T, 0, false), "TREASURE");
    screen.getActionMap().put("TREASURE", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.pickUp("Treasure", 0);
      }
    });
    screen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent
            .VK_A, 0, false), "ARROW");
    screen.getActionMap().put("ARROW", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.pickUp("Arrows", 1);
      }
    });
    screen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent
            .VK_S, 0, false), "SHOOT");
    screen.getActionMap().put("SHOOT", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        screen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent
                .VK_UP, 0, false), "UP");
        screen.getActionMap().put("UP", new AbstractAction() {
          @Override
          public void actionPerformed(ActionEvent e) {
            sDirection(Direction.NORTH);
          }
        });
        screen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent
                .VK_DOWN, 0, false), "DOWN");
        screen.getActionMap().put("DOWN", new AbstractAction() {
          @Override
          public void actionPerformed(ActionEvent e) {
            sDirection(Direction.SOUTH);
          }
        });
        screen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent
                .VK_LEFT, 0, false), "LEFT");
        screen.getActionMap().put("LEFT", new AbstractAction() {
          @Override
          public void actionPerformed(ActionEvent e) {
            sDirection(Direction.WEST);
          }
        });
        screen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent
                .VK_RIGHT, 0, false), "RIGHT");
        screen.getActionMap().put("RIGHT", new AbstractAction() {
          @Override
          public void actionPerformed(ActionEvent e) {
            sDirection(Direction.EAST);
          }
        });
      }
    });
  }

  private void sDirection(Direction d) {
    final String caves = JOptionPane.showInputDialog("Enter the number of caves", "1");
    int i = 0;
    try {
      i = Integer.parseInt(caves);
    } catch (NumberFormatException n) {
      controller.handle("Invalid value");
    }
    controller.shoot(d, i);
  }

  private BufferedImage overlay(BufferedImage initial, BufferedImage replace, int offsetX
          , int offsetY) throws IOException {
    int w = Math.max(initial.getWidth(), replace.getWidth());
    int h = Math.max(initial.getHeight(), replace.getHeight());
    BufferedImage mixed = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics g = mixed.getGraphics();
    g.drawImage(initial, 0, 0, null);
    g.drawImage(replace, offsetX, offsetY, null);
    return mixed;
  }

  private BufferedImage toBuffered(Image imgToChange) {
    if (imgToChange instanceof BufferedImage) {
      return (BufferedImage) imgToChange;
    }
    BufferedImage bufferedImage = new BufferedImage(imgToChange.getWidth(null)
            , imgToChange.getHeight(null), BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = bufferedImage.createGraphics();
    g.drawImage(imgToChange, 0, 0, null);
    g.dispose();
    return bufferedImage;
  }

  private void deathAndWinPopUp() {
    if (model.isGameOver() && model.getPlayerLocation().otyughCount() == 1) {
      int result = JOptionPane.showOptionDialog(null, "You were eaten."
              , "Game Over!!!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE
              , null, new Object[]{"Restart", "Retry", "Home"}, JOptionPane.NO_OPTION);
      if (result == JOptionPane.YES_OPTION) {
        controller.createModel(model.getRows(), model.getColumns(), model.getWrapping()
                , model.getInterconnectivity(), model.getTreasureAndArrowPercentage()
                , model.getNoOfOtyughs(), model.getName());
      } else if (result == JOptionPane.NO_OPTION) {
        controller.reuseDungeon();
      } else if (result == JOptionPane.CANCEL_OPTION) {
        controller.startGame();
      }
    } else if (model.isGameOver() && model.getPlayerLocation().otyughCount() == 0) {
      int result = JOptionPane.showOptionDialog(null
              , "You have reached the end cave.", "Congratulations!!!"
              , JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null
              , new Object[]{"Retry", "Restart", "Home"}, JOptionPane.YES_OPTION);
      if (result == JOptionPane.YES_OPTION) {
        controller.reuseDungeon();
      } else if (result == JOptionPane.NO_OPTION) {
        controller.createModel(model.getRows(), model.getColumns(), model.getWrapping()
                , model.getInterconnectivity(), model.getTreasureAndArrowPercentage()
                , model.getNoOfOtyughs(), model.getName());
      } else if (result == JOptionPane.CANCEL_OPTION) {
        controller.startGame();
      }
    }
  }
}
