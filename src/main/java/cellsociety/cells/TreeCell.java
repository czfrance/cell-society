package cellsociety.cells;

public class TreeCell extends Cell {

  public TreeCell(int x, int y, int initState) {
    super(x, y, initState);
  }

  @Override
  public int getNextState() {
    return currentState;
  }

  public void death() {}

}
