package battleship.board;

import battleship.ship.*;

import java.util.*;

public class Board {

  HashMap<Coordinates, Tile> tiles;
  AircraftCarrier aircraftCarrier;
  Battleship battleship;
  Submarine submarine;
  Cruiser cruiser;
  Destroyer destroyer;

  public Board() {
    tiles = new HashMap<>();
    aircraftCarrier = new AircraftCarrier();
    battleship = new Battleship();
    submarine = new Submarine();
    cruiser = new Cruiser();
    destroyer = new Destroyer();
    for (int x = 1; x <= 10; x++) {
      for (int y = 1; y <= 10; y++) {
        Coordinates coordinates = new Coordinates(x, y);
        tiles.putIfAbsent(coordinates, new Tile(coordinates));
      }
    }
  }

  public void makeBoard() {
    Ship[] ships = {aircraftCarrier, battleship, submarine, cruiser, destroyer};
    int pickShip = 0;

    print();

    while (pickShip < 5) {
      System.out.println("\nEnter the coordinates of the " + ships[pickShip].printType() + " (" +
          ships[pickShip].getSize() + " cells):\n");

      List<Coordinates> coordinates = getCoordinates();
      if (coordinates.isEmpty()) {
        System.out.println("Error! Wrong ship location! Try again:");
        continue;
      }

      if (!ships[pickShip].setCoordinates(coordinates)) {
        continue;
      }

      if (!placeShip(ships[pickShip])) {
        System.out.println("\nError! You placed it too close to another one. Try again:");
        continue;
      }

      pickShip++;
      System.out.println("");
      print();
    }
    System.out.println();
    moveToAnotherPlayer();
    prepareForTheGame();
  }

  private Optional<Tile> getTileFromCoordinates(Coordinates coords) {
    if (tiles.containsKey(coords)) {
      return Optional.of(tiles.get(coords));
    }
    return Optional.empty();
  }

  private boolean placeShip(Ship ship) {
    List<Coordinates> coords = ship.getShipTiles();

    for (Coordinates c:coords) {
      if (getTileFromCoordinates(c).get().getTileState() == TileState.UNAVAILABLE) {
        return false;
      }
    }

    for (Coordinates c:coords) {
      getTileFromCoordinates(c).get().setShip(ship);
    }

    HashSet<Coordinates> neighbors = ship.getNeighborCoordinates();
    for (Coordinates c:neighbors) {
      getTileFromCoordinates(c).get().setUnavailable();
    }

    return true;
  }

  public void print() {
    System.out.println("  1 2 3 4 5 6 7 8 9 10");
    for (int x = 1; x <= 10; x++) {
      System.out.print((char)(64 + x));
      for (int y = 1; y <= 10; y++) {
        System.out.print(" " + getTileFromCoordinates(new Coordinates(x, y)).get().print());
      }
      System.out.print("\n");
    }
  }

  private List<Coordinates> getCoordinates() {
    List<Coordinates> coordinates = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    String[] coords = scanner.nextLine().split(" ");

    for (String s:coords) {
      Coordinates candidateCoordinates = new Coordinates(s.charAt(0), (Integer.parseInt(s.substring(1))));
      if (getTileFromCoordinates(candidateCoordinates).isEmpty()) {
        return new ArrayList<>();
      }
      coordinates.add(candidateCoordinates);
    }

    return coordinates;
  }

  private boolean isGameOver(){
    if (aircraftCarrier.isSunken() && battleship.isSunken() && submarine.isSunken() && cruiser.isSunken() && destroyer.isSunken()) {
      return true;
    }
    return false;
  }

  public boolean takeTurn(){
    Scanner scanner = new Scanner(System.in);
    while (true) {
      String str = scanner.nextLine();
      Optional<Tile> gameTile = getTileFromCoordinates(new Coordinates(str.charAt(0), Integer.parseInt(str.substring(1))));
      if (gameTile.isEmpty()) {
        System.out.println("Error! You entered the wrong coordinates! Try again:");
        continue;
      }
      System.out.println("");
      if (gameTile.get().hit()) {
        if (gameTile.get().getShip().get().isSunken()) {
          if (isGameOver()) {
            System.out.println("You sank the last ship. You won. Congratulations!\n");
            return true;
          }
          System.out.println("You sank a ship!\n");
        } else {
          System.out.println("You hit a ship!\n");
        }
      } else {
        System.out.println("You missed!\n");
      }
      break;
    }
    moveToAnotherPlayer();
    return isGameOver();
  }

  private void moveToAnotherPlayer() {
    System.out.println("Press Enter and pass the move to another player");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine();
  }

  private void prepareForTheGame() {
    for (Tile t: tiles.values()) {
      t.setFog();
    }
  }

  public void printRevealed() {
    System.out.println("  1 2 3 4 5 6 7 8 9 10");
    for (int x = 1; x <= 10; x++) {
      System.out.print((char)(64 + x));
      for (int y = 1; y <= 10; y++) {
        System.out.print(" " + getTileFromCoordinates(new Coordinates(x, y)).get().printRevealed());
      }
      System.out.print("\n");
    }
  }
}
