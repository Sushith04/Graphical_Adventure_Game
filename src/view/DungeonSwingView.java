package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import controller.IDungeonConsoleController;
import model.IDungeonReadOnly;

/**
 * A view for Dungeon: displays the introduction panel and game board and provides visual interface
 * for the users.
 */
public class DungeonSwingView extends JFrame implements DungeonView {

  private IntroPanel introPanel;

  /**
   * Constructor for the view.
   */
  public DungeonSwingView() {
    super("Dungeon Adventure");
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    this.setSize(screenSize.width, screenSize.height);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    introPanel = new IntroPanel();
  }

  @Override
  public void makeVisible() {
    setVisible(true);
  }

  @Override
  public void setIntro(IDungeonConsoleController controller) {
    introPanel = new IntroPanel();
    introPanel.popUps(controller);
    this.setContentPane(introPanel);
    introPanel.setVisible(true);
  }

  @Override
  public void setModel(IDungeonReadOnly model, IDungeonConsoleController controller) {
    GamePanel gamePanel = new GamePanel(model, controller);
    this.setContentPane(gamePanel);
    makeVisible();
    gamePanel.listeners(controller);
  }

  @Override
  public void popUps(String s) {
    JOptionPane.showMessageDialog(this, s);
  }

  @Override
  public void close() {
    System.exit(0);
  }
}
