package cellsociety.cells;

public class PercolatingCell extends Cell {

  public static final int EMPTY = 0;
  public static final int FILLED = 1;

  //FOR PERCOLATION
  public PercolatingCell(int x, int y, int initState) {
    super(x, y, initState);
  }

  public void makeFilled() {
    currentState = FILLED;
  }

  @Override
  public int getNextState() {
    for (Cell c : myNeighbors) {
      if (flowsInFrom(c) || currentState == FILLED) {
        return FILLED;
      }
    }
    return EMPTY;
  }

  public void death() {}

  private boolean flowsInFrom(Cell c) {
    return (c instanceof PercolatingCell) && (c.getMyCurrentState() == FILLED) && (c.ROW <= this.ROW);
  }
}
