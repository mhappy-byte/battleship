package battleship.game;

import battleship.board.Board;

public class Game {
  private Board player1;
  private Board player2;
  private GameState gameState;

  public Game() {
    gameState = GameState.GAME_IN_PROGRESS_PLAYER_ONE;
    player1 = new Board();
    System.out.println("Player 1, place your ships on the game field\n");
    player1.makeBoard();

    player2 = new Board();
    System.out.println("Player 2, place your ships on the game field\n");
    player2.makeBoard();
  }

  public void letsPlay() {
    Board[] boards = { player1, player2};

    while (gameState != GameState.PLAYER_ONE_WON || gameState != GameState.PLAYER_TWO_WON) {
      boards[gameState.getOppositeSide() - 1].print();
      System.out.println("---------------------");
      boards[gameState.getPlayer() - 1].printRevealed();
      System.out.println("\nPlayer " + Integer.toString(gameState.getPlayer()) + ", it's your turn:\n");

      if (boards[gameState.getOppositeSide() - 1].takeTurn()) {
        gameState = (gameState.getPlayer() == 1) ? GameState.PLAYER_ONE_WON : GameState.PLAYER_TWO_WON;
      } else {
        gameState = (gameState.getPlayer() == 1) ? GameState.GAME_IN_PROGRESS_PLAYER_TWO : GameState.GAME_IN_PROGRESS_PLAYER_ONE;
      }
    }

  }
}
