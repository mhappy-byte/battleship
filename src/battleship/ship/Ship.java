package battleship.ship;

import battleship.board.Coordinates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public abstract class Ship {
  protected int size;
  protected int hits;
  protected boolean sunken;
  protected Coordinates startCoordinate;
  protected Coordinates endCoordinate;
  protected Coordinates offset;

  public boolean setCoordinates(List<Coordinates> coordinates) {
    this.offset = coordinates.get(0).getOffset(coordinates.get(1));
    if (this.offset.getX() > 0 && this.offset.getY() > 0) {
      System.out.println("\nError! Wrong ship location! Try again:\n");
      return false;
    }

    if (this.offset.getX() == size - 1 || this.offset.getY() == size - 1) {
      if (coordinates.get(0).getX() > coordinates.get(1).getX() ||
            coordinates.get(0).getY() > coordinates.get(1).getY()) {
        startCoordinate = coordinates.get(1);
        endCoordinate = coordinates.get(0);
      } else {
        startCoordinate = coordinates.get(0);
        endCoordinate = coordinates.get(1);
      }
      return true;
    }
    System.out.println("\nError! Wrong length of the " + printType() + "! Try again:\n");
    return false;
  }

  public  List<Coordinates> getShipTiles(){
    List<Coordinates> tiles = new ArrayList<>();
    if (this.offset.getX() > 0) {
      for(int i = 0; i <= offset.getX(); i++) {
        tiles.add(new Coordinates(startCoordinate.getX() + i, startCoordinate.getY()));
      }
    } else {
      for(int i = 0; i <= offset.getY(); i++) {
        tiles.add(new Coordinates(startCoordinate.getX(), startCoordinate.getY() + i));
      }
    }
    return tiles;
  }

  public HashSet<Coordinates> getNeighborCoordinates() {
    List<Coordinates> tiles = getShipTiles();
    HashSet<Coordinates> neighbors = new HashSet<>();

    for (Coordinates c: tiles) {
      for (Coordinates n: c.getNeighbors()) {
        neighbors.add(n);
      }
    }

    for (Coordinates c:tiles) {
      neighbors.remove(c);
    }
    return neighbors;
  }

  public int getSize() {
    return size;
  }

  public String printType() {
    return "Ship";
  }

  public void hit(){
    if (++hits == size) {
      sunken = true;
    }
  }

  public boolean isSunken() {
    return sunken;
  }
}