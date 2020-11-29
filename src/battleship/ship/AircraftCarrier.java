package battleship.ship;

import battleship.board.Coordinates;

public class AircraftCarrier extends Ship {
  public AircraftCarrier(Coordinates x1, Coordinates x2) {
    this.startCoordinate = x1;
    this.endCoordinate = x2;
    this.size = 5;
    this.hits = 0;
    this.sunken = false;
  }

  public AircraftCarrier() {
    this.size = 5;
    this.hits = 0;
    this.sunken = false;
  }

  @Override
  public String printType() {
    return "Aircraft Carrier";
  }
}
