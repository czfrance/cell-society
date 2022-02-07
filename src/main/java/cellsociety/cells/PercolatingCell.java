package cellsociety.cells;

public class PercolatingCell extends Cell {

  public static final int BLOCKED = 0;
  public static final int EMPTY = 1;
  public static final int FILLED = 2;

  //FOR PERCOLATION
  public PercolatingCell(int x, int y, int initState) {
    super(x, y, initState);
  }

  public void makeFilled() {
    currentState = FILLED;
  }

  @Override
  public int getNextState() {
    if (currentState == BLOCKED) {
      return BLOCKED;
    }

    for (Cell c : myNeighbors) {
      if (flowsInFrom(c) || currentState == FILLED) {
        return FILLED;
      }
    }
    return EMPTY;
  }

  private boolean flowsInFrom(Cell c) {
    return (c.getMyCurrentState() == FILLED) && (c.row <= this.row);
  }
}
