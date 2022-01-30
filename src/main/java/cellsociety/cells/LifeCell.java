package cellsociety.cells;

import java.util.*;

public class LifeCell extends Cell {

  public static final int DEAD = 0;
  public static final int ALIVE = 1;

  //FOR GAME OF LIFE
  public LifeCell(int x, int y, int initState) {
    super(x, y, initState);
  }

//  @Override
//  public void nextState() {
//    int numAlive = numNeighborsAlive();
//
//    if (numAlive == 2 || numAlive == 3) myState = 1;
//    else myState = 0;
//  }

  @Override
  public int getNextState() {
    int numAlive = numNeighborsAlive();

    if (this.isAlive() && (numAlive == 2 || numAlive == 3)) {
      return ALIVE;
    }
    else if (numAlive == 3) {
      return ALIVE;
    }
    else {
      return DEAD;
    }
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
