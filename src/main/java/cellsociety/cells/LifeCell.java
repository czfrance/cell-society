package cellsociety.cells;

public class LifeCell extends Cell {

  public LifeCell(int x, int y, int initState) {
    super(x, y, initState);
  }

  @Override
  public void nextState() {
    int numAlive = numNeighborsAlive();

    if (numAlive == 2 && isAlive()) nextState = 1;
    else if (numAlive == 3) nextState = 1;
    else nextState = 0;

  }

  private int numNeighborsAlive() {
    int numAlive = 0;
    for (Cell myNeighbor : myNeighbors) {
      LifeCell cell = (LifeCell) myNeighbor;
      if (cell.isAlive()) numAlive++;
    }
    return numAlive;
  }

  public boolean isAlive() {
    return myState == 1;
  }
  public void update() {
    myState = nextState;
  }
}
