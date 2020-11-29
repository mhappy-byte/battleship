package battleship.board;

import battleship.ship.Ship;

import java.util.Optional;

public class Tile {
  private Coordinates coordinate;
  private TileState tileState;
  private Optional<Ship> ship;

  public Tile(Coordinates coordinate) {
    this.coordinate = coordinate;
    this.tileState = TileState.FOG;
    this.ship = Optional.empty();
  }

  public char print(){
    return tileState.print();
  }

  public TileState getTileState() {
    return tileState;
  }

  public Optional<Ship> getShip() {
    return ship;
  }

  public void setShip(Ship ship) {
    this.ship = Optional.of(ship);
    this.tileState = TileState.SHIP;
  }

  public void setUnavailable() {
    this.tileState = TileState.UNAVAILABLE;
  }

  public boolean hit() {
    if (ship.isPresent()) {
      ship.get().hit();
      tileState = TileState.HIT;
      return true;
    }
    tileState = TileState.MISS;
    return false;
  }

  public void setFog() {
    tileState = TileState.FOG;
  }

  public char printRevealed() {
    if (tileState == TileState.MISS || tileState == TileState.HIT) {
      return tileState.print();
    } else if (ship.isEmpty()) {
      return '~';
    }
    return 'O';
  }
}
