package controller;

import java.io.IOException;

import model.IDungeon;

/**
 * Represents a Controller for Dungeon: handle user moves by executing them using the model;
 * convey move outcomes to the user in some form.
 */
public interface IDungeonController {

  /**
   * Execute a single game of dungeon given a dungeon Model. When the game is over,
   * the playGame method ends.
   *
   * @param model a non-null Dungeon Model
   */
  void playGame(IDungeon model) throws IOException;
}
