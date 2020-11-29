package battleship.ship;

import battleship.board.Coordinates;

public class Destroyer extends Ship{
  public Destroyer(Coordinates x1, Coordinates x2) {
    this.startCoordinate = x1;
    this.endCoordinate = x2;
    this.size = 2;
    this.hits = 0;
    this.sunken = false;
  }

  public Destroyer() {
    this.size = 2;
    this.hits = 0;
    this.sunken = false;
  }

  @Override
  public String printType() {
    return "Destroyer";
  }
}
