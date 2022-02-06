package cellsociety.cells;

import java.util.ArrayList;
import java.util.List;
import cellsociety.models.Grid;

public abstract class Cell {
  protected int ID;

  protected int COLUMN;
  protected int ROW;

  public static final int TOP_LEFT = 1;
  public static final int TOP_RIGHT = 2;
  public static final int BOTTOM_LEFT = 3;
  public static final int BOTTOM_RIGHT = 4;

  public static final int LEFT_EDGE = 1;
  public static final int RIGHT_EDGE = 2;
  public static final int TOP_EDGE = 3;
  public static final int BOTTOM_EDGE = 4;

  protected int myState;
  protected int nextState;

  public static final int EMPTY = 0;
  public static final int FISH = 1;
  public static final int SHARK = 2;

  protected List<Cell> myNeighbors;

  public Cell(int x, int y, int initState) {
    COLUMN = x;
    ROW = y;

    myState = initState;
    ID = initState;
    myNeighbors = new ArrayList<>();
  }

  public void setState(int state) {myState = state;}

  public int getCurrentState() {return ID;}

  public abstract int getNextState();

  public int getMyState() {return myState;}

  public void initFiniteNeighbors(int width, int height, Grid grid) {
    for (int i = -1; i < 2; i++) {
      for (int k = -1; k < 2; k++) {
        if (inBounds(COLUMN + k, ROW + i, width, height) && (i !=0 || k !=0)) {
          myNeighbors.add(grid.getRow(ROW + i).get(COLUMN + k));
        }
      }
    }
  }

  public void initWrapNeighbors(int width, int height, Grid grid) {
    myNeighbors = new ArrayList<>();
    for (int i = -1; i < 2; i++) {
      for (int k = -1; k < 2; k++) {
        if ((i !=0 || k !=0)) {
          int x = flip(COLUMN + k, width);
          int y = flip(ROW + i, height);
          myNeighbors.add(grid.getRow(y).get(x));
        }
      }
    }
  }

  public boolean inBounds(int x, int y, int width, int height) {
    return (x >= 0 && x < width) && (y >= 0 && y < height);
  }
  public int flip(int x, int boundary) {
    if (x >= boundary) return 0;
    else if (x < 0) return boundary - 1;
    return x;
  }




  @Override
  public String toString() {
    return String.format("State %s, Neighbors %d, numNeighborsAlive %d, row: %d, column: %d", myState, myNeighbors == null ? 0 : myNeighbors.size(), myNeighbors == null ? 0 : numAlive(), ROW, COLUMN);}

  public boolean isAlive() {return myState == 1;}

  public int numAlive() {
    int counter = 0;
    for (Cell c : myNeighbors) {
      if (c.isAlive()) counter++;
    }
    return counter;
  }

  public void update() {}

  public void update(int width, int height, Grid grid) {}

  public int getID() {return ID;}

  public int getRow() {return ROW;}

  public int getColumn() {return COLUMN;}

  public int getState() {return myState;}

  protected int getNutrition() {return -1;}

  public Cell reupdate() { return null;}

  public boolean isReproducing() {return false;}

  public Cell getCurrentObject() {return null;}

  public boolean isDead() {return false;}

  public void block() {}
  public void unblock() {}
  public boolean isBlocked() {return false;}
  public void resetReproductionTimer() {}
  public void setFish(FishCell f){}
  public void setShark(SharkCell s){}
  public FishCell getFish() {return null;}
  public SharkCell getShark(){return null;}
  public void setEmpty() {}
  public void setNew(int state, int repoTimer, int nutVal) {}
}
