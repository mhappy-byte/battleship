package battleship.board;

public enum TileState {

  FOG('~'), MISS('M'), HIT('X'), SHIP('O'), UNAVAILABLE('~');

  char c;

  TileState(char c) {
    this.c = c;
  }

  public char print() {
    return c;
  }

}
