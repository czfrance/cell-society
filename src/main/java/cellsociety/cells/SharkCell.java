package cellsociety.cells;

public class SharkCell extends Cell {

  private int myHealth;
  private int myState;

  public SharkCell(int x, int y, int initState, int health) {
    super(x, y, initState);
    myHealth = health;
  }


}
