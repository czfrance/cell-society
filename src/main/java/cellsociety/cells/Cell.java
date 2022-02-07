package cellsociety.cells;

import java.util.ArrayList;
import java.util.List;;

public abstract class Cell {
  //CONSTANTS
  public static final int ODD = 1;
  public static final int EVEN = 0;
  public static final int FINITE = 0;
  public static final int TOROIDAL = 1;
  public static final int TRIANGULAR_TOROIDAL = 2;
  public static final int HEXAGON = 3;

  protected int currentState;

  protected int COLUMN;
  protected int ROW;

  protected int nextState;

  protected List<Cell> myNeighbors;

  protected int orientation;

  public Cell(int column, int row, int initState) {
    COLUMN = column;
    ROW = row;

    currentState = initState;
    myNeighbors = new ArrayList<>();
    orientation = (column + row) % 2;
  }

  public void setState(int state) {
    currentState = state;}

  public int getCurrentState() {return currentState;}

  public abstract int getNextState();

  public int getMyCurrentState() {return currentState;}


  private boolean isFacingDown() {return orientation == EVEN;}

  private boolean isFacingUp() {return orientation == ODD;}

  public void initNeighbors(int code, int width, int height, List<List<Cell>> grid) {
    myNeighbors = new ArrayList<>();
    switch (code) {
      case FINITE -> {finiteNeighbors(width, height, grid);}

      case TOROIDAL -> {toroidalNeighbors(width, height, grid);}
      case TRIANGULAR_TOROIDAL -> {triangleNeighbors(width, height, grid);}
      case HEXAGON -> {initHexagonNeighbors(width, height, grid);}
      default -> toroidalNeighbors(width, height, grid);
    }

  }

  private void finiteNeighbors(int width, int height, List<List<Cell>> grid) {
    for (int i = -1; i < 2; i++) {
      for (int k = -1; k < 2; k++) {
        if (inBounds(COLUMN + k, ROW + i, width, height) && (i !=0 || k !=0)) {
          myNeighbors.add(grid.get(ROW + i).get(COLUMN + k));
        }
      }
    }
  }

  private void toroidalNeighbors(int width, int height, List<List<Cell>> grid) {
    for (int i = -1; i < 2; i++) {
      for (int k = -1; k < 2; k++) {
        if (i !=0 || k !=0) {
          int x = flip(COLUMN + k, width);
          int y = flip(ROW + i, height);
          myNeighbors.add(grid.get(y).get(x));
        }
      }
    }
  }

  public int flip(int x, int boundary) {
    if (x >= boundary) return 0;
    else if (x < 0) return boundary - 1;
    return x;
  }


  private void triangleNeighbors(int width, int height, List<List<Cell>> grid) {

    for (int i = -1; i < 2; i++) {
      int[] vals = getVals(i);
      int k = vals[0];
      int b = vals[1];

      for (; k < b; k++) {
        if (i !=0 || k !=0) {
          int x = flip(COLUMN + k, width);
          int y = flip(ROW + i, height);
          myNeighbors.add(grid.get(y).get(x));
        }
      }
    }
  }
  private int[] getVals(int i) {
    int[] ret = new int[2];

    if (isFacingDown() && (i == -1 || i == 0)) {
      ret[0] = -2;
      ret[1] = 3;
    } else if (isFacingUp() && (i == 1 || i == 0)){
      ret[0] = -2;
      ret[1] = 3;
    } else {
      ret[0] = -1;
      ret[1] = 2;
    }

    return ret;
  }

  private void initHexagonNeighbors(int width, int height, List<List<Cell>> grid) {
    for (int i = -1; i < 2; i++) {
      int guard;
      if (i == 0) guard = 2;
      else guard = 1;
      for (int k = -1; k < guard; k++) {
        if (i !=0 || k !=0) {
          int x = flip(COLUMN + k, width);
          int y = flip(ROW + i, height);
          myNeighbors.add(grid.get(y).get(x));
        }
      }
    }
  }

  public boolean inBounds(int x, int y, int width, int height) {
    return (x >= 0 && x < width) && (y >= 0 && y < height);
  }

  @Override
  public String toString() {
    return String.format("State %s, Neighbors %d, row: %d, column: %d",
           currentState, myNeighbors == null ? 0 : myNeighbors.size(), ROW, COLUMN);}

  public void update() {}

  public void update(int width, int height, List<List<Cell>> grid) {}

  public int getRow() {return ROW;}

  public int getColumn() {return COLUMN;}

  protected int getNutrition() {return -1;}

  public Cell reupdate() { return null;}

  public boolean isReproducing() {return false;}

  public Cell getCurrentObject() {return null;}

  public boolean isDead() {return false;}
  //
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

  public int homePheromones() {return -1;}
  public int foodPheromones() {return -1;}

  protected int takeSugar(int mySugar) {
    return -1;
  }

  protected void update(List<Cell> myNeighbors) {
  }

  public boolean hasAgent() {
    return false;
  }

  public void setAgent(Cell cell) {
  }

  public Cell getAgent() { return null;
  }

  public boolean isAlive() {return false;}
}
