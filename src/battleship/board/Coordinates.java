package battleship.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Coordinates {
  private int x;
  private int y;

  public Coordinates(char x, int y) {
    this.x = x - 64;
    this.y = y;
  }

  public Coordinates(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public String printCoordinates() {
    StringBuilder coord = new StringBuilder();
    coord.append((char)(x + 'A'));
    coord.append(Integer.toString(y));
    return coord.toString();
  }

  public Coordinates getOffset(Coordinates coord) {
    return new Coordinates((char) (Math.abs(this.x - coord.x) + 64), Math.abs(this.y - coord.y));
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public List<Coordinates> getNeighbors() {
    int xOff, yOff;
    List<Coordinates> neighbors = new ArrayList<>();
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        xOff = x + i;
        yOff = y + j;
        if ((xOff > 0 && xOff < 11 && yOff > 0 && yOff < 11)) {
          neighbors.add(new Coordinates(xOff, yOff));
        }
      }
    }
    neighbors.remove(this);
    return neighbors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Coordinates that = (Coordinates) o;
    return x == that.x &&
        y == that.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
