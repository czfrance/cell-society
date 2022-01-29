package cellsociety.cells;

import java.util.*;

public class LifeCell extends Cell {

  public LifeCell(int x, int y, int initState) {
    super(x, y, initState);
  }

  @Override
  public void nextState() {
    int numAlive = numNeighborsAlive();

    if (numAlive == 2 || numAlive == 3) myState = 1;
    else myState = 0;
  }

  private int numNeighborsAlive() {
    int numAlive = 0;
    for (int c = 0; c < myNeighbors.size(); c++) {
      LifeCell cell = (LifeCell) myNeighbors.get(c);
      if (cell.isAlive()) {
        numAlive++;
      }
    }
    return numAlive;
  }

  public boolean isAlive() {
    return myState == 1;
  }
}
