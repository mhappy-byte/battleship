package battleship.game;

public enum GameState { PLAYER_ONE_WON(1), PLAYER_TWO_WON(2), GAME_IN_PROGRESS_PLAYER_ONE(1), GAME_IN_PROGRESS_PLAYER_TWO(2);

  private final int i;

  GameState(int i) {
    this.i = i;
  }

  public int getPlayer() {
    return i;
  }

  public int getOppositeSide() {
    return (i == 1) ? 2 : 1;
  }
}
