package battleship.ship;

import battleship.board.Coordinates;

public class Submarine extends Ship{
  public Submarine(Coordinates x1, Coordinates x2) {
    this.startCoordinate = x1;
    this.endCoordinate = x2;
    this.size = 3;
    this.hits = 0;
    this.sunken = false;
  }

  public Submarine() {
    this.size = 3;
    this.hits = 0;
    this.sunken = false;
  }

  @Override
  public String printType() {
    return "Submarine";
  }
}
