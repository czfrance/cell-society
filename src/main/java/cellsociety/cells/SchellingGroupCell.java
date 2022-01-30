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
    if (this.myState == 0) {
      return myState;
    }

    for (Cell c : myNeighbors) {
      if (c.getMyState() != 0) {
        totalNeighbors++;
        if (c.getMyState() == this.myState) {
          totalSameGroupNeighbors++;
        }
      }
    }
    if (totalSameGroupNeighbors/totalNeighbors < satisfied) return myState*-1;
    return myState;
  }
}
