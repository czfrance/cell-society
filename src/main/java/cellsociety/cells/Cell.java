package cellsociety.cells;

import java.util.ArrayList;
import java.util.List;
import cellsociety.models.Grid;

public abstract class Cell {
  protected int currentState;

  protected int COLUMN;
  protected int ROW;

  protected int nextState;

  protected List<Cell> myNeighbors;

  public Cell(int column, int row, int initState) {
    COLUMN = column;
    ROW = row;

    currentState = initState;
    myNeighbors = new ArrayList<>();
  }

  public void setState(int state) {
    currentState = state;}

  public int getCurrentState() {return currentState;}

  public abstract int getNextState();

  public int getMyCurrentState() {return currentState;}

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
    return String.format("State %s, Neighbors %d, row: %d, column: %d",
           currentState, myNeighbors == null ? 0 : myNeighbors.size(), ROW, COLUMN);}

  public void update() {}

  public void update(int width, int height, Grid grid) {}

  public int getRow() {return ROW;}

  public int getColumn() {return COLUMN;}

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
