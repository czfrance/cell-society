package cellsociety.cells;

/**
 * author: Cynthia France
 */
public class PercolatingCell extends Cell {

  public static final int BLOCKED = 0;
  public static final int EMPTY = 1;
  public static final int FILLED = 2;

  /**
   *
   * @param x the x location of the cell
   * @param y the y location of the cell
   * @param initState the cell's initial state
   */
  public PercolatingCell(int x, int y, int initState) {
    super(x, y, initState);
  }

  /**
   * makes the cell filled
   */
  public void makeFilled() {
    currentState = FILLED;
  }

  /**
   *
   * @return the next state of the cell
   */
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
