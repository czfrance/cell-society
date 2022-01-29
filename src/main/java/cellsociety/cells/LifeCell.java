package cellsociety.cells;

import java.util.*;

public class LifeCell extends Cell {

  //FOR GAME OF LIFE
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
  public void update() {
    myState = nextState;
  }
}
