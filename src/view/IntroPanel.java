package view;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.IDungeonConsoleController;

class IntroPanel extends JPanel {
  private final GridBagConstraints constraints;
  private JTextField tRows;
  private JTextField tColumns;
  private JRadioButton wrap;
  private JRadioButton nonWrap;
  private JTextField tInter;
  private JTextField tTAP;
  private JTextField tName;
  private JTextField tOtyugh;
  private JButton sub;

  /**
   * Constructor for the intro panel.
   */
  IntroPanel() {
    this.setLayout(new GridBagLayout());
    constraints = new GridBagConstraints();
    setBackground(Color.black);
    createForm();
  }

  private void createForm() {
    JLabel title = new JLabel("Create your Dungeon");
    title.setFont(new Font("Arial", Font.PLAIN, 30));
    title.setForeground(Color.white);
    constraints.gridx = 1;
    constraints.gridy = 0;
    constraints.insets = new Insets(40, 0, 50, 0);
    this.add(title, constraints);
    constraints.insets = new Insets(0, 0, 0, 0);
    JLabel rows = new JLabel("Rows");
    rows.setFont(new Font("Arial", Font.PLAIN, 25));
    rows.setForeground(Color.white);
    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.insets = new Insets(25, 25, 25, 25);
    this.add(rows, constraints);
    tRows = new JTextField();
    tRows.setFont(new Font("Arial", Font.PLAIN, 20));
    tRows.setText("6");
    constraints.gridx = 2;
    constraints.gridy = 1;
    constraints.ipadx = 50;
    this.add(tRows, constraints);
    JLabel columns = new JLabel("Columns");
    columns.setFont(new Font("Arial", Font.PLAIN, 25));
    columns.setForeground(Color.white);
    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.ipadx = 0;
    this.add(columns, constraints);
    tColumns = new JTextField();
    tColumns.setFont(new Font("Arial", Font.PLAIN, 20));
    tColumns.setText("6");
    constraints.gridx = 2;
    constraints.gridy = 2;
    constraints.ipadx = 50;
    this.add(tColumns, constraints);
    JLabel wrapping = new JLabel("Wrapping");
    wrapping.setFont(new Font("Arial", Font.PLAIN, 25));
    wrapping.setForeground(Color.white);
    constraints.gridx = 0;
    constraints.gridy = 3;
    constraints.ipadx = 0;
    this.add(wrapping, constraints);
    wrap = new JRadioButton("Yes");
    wrap.setFont(new Font("Arial", Font.PLAIN, 20));
    wrap.setForeground(Color.white);
    wrap.setSelected(false);
    constraints.gridx = 2;
    constraints.gridy = 3;
    this.add(wrap, constraints);
    nonWrap = new JRadioButton("No");
    nonWrap.setFont(new Font("Arial", Font.PLAIN, 20));
    nonWrap.setForeground(Color.white);
    nonWrap.setSelected(true);
    constraints.gridx = 3;
    constraints.gridy = 3;
    this.add(nonWrap, constraints);
    ButtonGroup w = new ButtonGroup();
    w.add(wrap);
    w.add(nonWrap);
    JLabel inter = new JLabel("Interconnectivity");
    inter.setFont(new Font("Arial", Font.PLAIN, 25));
    inter.setForeground(Color.white);
    constraints.gridx = 0;
    constraints.gridy = 4;
    this.add(inter, constraints);
    tInter = new JTextField();
    tInter.setFont(new Font("Arial", Font.PLAIN, 20));
    tInter.setText("0");
    constraints.gridx = 2;
    constraints.gridy = 4;
    constraints.ipadx = 50;
    this.add(tInter, constraints);
    JLabel tAP = new JLabel("Treasure/Arrow Percent");
    tAP.setFont(new Font("Arial", Font.PLAIN, 25));
    tAP.setForeground(Color.white);
    constraints.gridx = 0;
    constraints.gridy = 5;
    constraints.ipadx = 0;
    this.add(tAP, constraints);
    tTAP = new JTextField();
    tTAP.setFont(new Font("Arial", Font.PLAIN, 20));
    tTAP.setText("20");
    constraints.gridx = 2;
    constraints.gridy = 5;
    constraints.ipadx = 50;
    this.add(tTAP, constraints);
    JLabel otyugh = new JLabel("No of Otyughs");
    otyugh.setFont(new Font("Arial", Font.PLAIN, 25));
    otyugh.setForeground(Color.white);
    constraints.gridx = 0;
    constraints.gridy = 6;
    constraints.ipadx = 0;
    this.add(otyugh, constraints);
    tOtyugh = new JTextField();
    tOtyugh.setFont(new Font("Arial", Font.PLAIN, 20));
    tOtyugh.setText("4");
    constraints.gridx = 2;
    constraints.gridy = 6;
    constraints.ipadx = 50;
    this.add(tOtyugh, constraints);
    JLabel name = new JLabel("Name");
    name.setFont(new Font("Arial", Font.PLAIN, 25));
    name.setForeground(Color.white);
    constraints.gridx = 0;
    constraints.gridy = 7;
    constraints.ipadx = 0;
    this.add(name, constraints);
    tName = new JTextField();
    tName.setFont(new Font("Arial", Font.PLAIN, 20));
    tName.setText("Sushith");
    constraints.gridx = 2;
    constraints.gridy = 7;
    constraints.ipadx = 100;
    this.add(tName, constraints);
    sub = new JButton("Submit");
    sub.setFont(new Font("Arial", Font.PLAIN, 30));
    constraints.gridx = 1;
    constraints.gridy = 8;
    constraints.ipadx = 0;
    this.add(sub, constraints);
    setVisible(false);
  }

  void popUps(IDungeonConsoleController controller) {
    sub.addActionListener(e -> {
      int row;
      int column;
      boolean wraps = false;
      int inter;
      int percentage;
      int otyugh;
      String name;
      if (wrap.isSelected()) {
        wraps = true;
      }
      if (nonWrap.isSelected()) {
        wraps = false;
      }
      try {
        row = Integer.parseInt(tRows.getText());
      } catch (IllegalArgumentException x) {
        JOptionPane.showMessageDialog((Component) e.getSource()
                , "Enter a valid value for rows");
        return;
      }
      try {
        column = Integer.parseInt(tColumns.getText());
      } catch (IllegalArgumentException x) {
        JOptionPane.showMessageDialog((Component) e.getSource()
                , "Enter a valid value for columns");
        return;
      }
      try {
        inter = Integer.parseInt(tInter.getText());
      } catch (IllegalArgumentException x) {
        JOptionPane.showMessageDialog((Component) e.getSource()
                , "Enter a valid value for interconnectivity");
        return;
      }
      try {
        percentage = Integer.parseInt(tTAP.getText());
      } catch (IllegalArgumentException x) {
        JOptionPane.showMessageDialog((Component) e.getSource()
                , "Enter a valid value for percentage");
        return;
      }
      try {
        otyugh = Integer.parseInt(tOtyugh.getText());
      } catch (IllegalArgumentException x) {
        JOptionPane.showMessageDialog((Component) e.getSource()
                , "Enter a valid value for otyugh count");
        return;
      }
      try {
        name = tName.getText();
      } catch (IllegalArgumentException x) {
        JOptionPane.showMessageDialog((Component) e.getSource()
                , "Enter a valid name");
        return;
      }
      controller.createModel(row, column, wraps, inter, percentage, otyugh, name);
    });
  }
}
