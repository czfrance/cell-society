package cellsociety.cells;

public class PercolatingCell extends Cell {

  public static final int EMPTY = 0;
  public static final int FILLED = 1;

  //FOR PERCOLATION
  public PercolatingCell(int x, int y, int initState) {
    super(x, y, initState);
  }

  public void makeFilled() {
    myState = FILLED;
  }

  @Override
  public int getNextState() {
    for (Cell c : myNeighbors) {
      if ((c instanceof PercolatingCell) && (c.getMyState() == FILLED) || myState == FILLED) {
        return FILLED;
      }
    }
    return EMPTY;
  }
}
