package cellsociety.cells;

public class SchellingGroupCell extends Cell {

  private double satisfied;

  public SchellingGroupCell(int x, int y, int initState, double happy) {
    super(x, y, initState);
    satisfied = happy;
  }

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
