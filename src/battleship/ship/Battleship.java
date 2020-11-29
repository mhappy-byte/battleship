package battleship.ship;

import battleship.board.Coordinates;
import battleship.ship.Ship;

public class Battleship  extends Ship {

  public Battleship(Coordinates x1, Coordinates x2) {
    this.startCoordinate = x1;
    this.endCoordinate = x2;
    this.size = 4;
    this.hits = 0;
    this.sunken = false;
  }

  public Battleship() {
    this.size = 4;
    this.hits = 0;
    this.sunken = false;
  }

  @Override
  public String printType() {
    return "Battleship";
  }
}
