package battleship.ship;

import battleship.board.Coordinates;

public class Cruiser extends Ship{
  public Cruiser(Coordinates x1, Coordinates x2) {
    this.startCoordinate = x1;
    this.endCoordinate = x2;
    this.size = 3;
    this.hits = 0;
    this.sunken = false;
  }

  public Cruiser() {
    this.size = 3;
    this.hits = 0;
    this.sunken = false;
  }

  @Override
  public String printType() {
    return "Cruiser";
  }
}
