package cellsociety.cells;

/**
 * author: Cynthia France
 */
public class SchellingGroupCell extends Cell {

  private double satisfied;

  /**
   *
   * @param x the x location of the cell
   * @param y the y location of the cell
   * @param initState cell's initial state
   * @param happy fraction of neighbors that must be of the same type to be happy (not move)
   */
  public SchellingGroupCell(int x, int y, int initState, double happy) {
    super(x, y, initState);
    satisfied = happy;
  }

  /**
   *
   * @return next state of cell
   */
  @Override
  public int getNextState() {
    double totalNeighbors = 0;
    double totalSameGroupNeighbors = 0;
    if (this.currentState == 0) {
      return currentState;
    }

    for (Cell c : myNeighbors) {
      if (c.getMyCurrentState() != 0) {
        totalNeighbors++;
        if (c.getMyCurrentState() == this.currentState) {
          totalSameGroupNeighbors++;
        }
      }
    }
    if (totalNeighbors == 0) return currentState;
    if (totalSameGroupNeighbors/totalNeighbors < satisfied) return currentState *-1;
    return currentState;
  }
}
