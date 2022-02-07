package cellsociety.cells;

import java.util.ArrayList;
import java.util.List;
import cellsociety.models.Grid;

/**
 * author: Cynthia France
 */
public abstract class Cell {
  //CONSTANTS
  public static final int ODD = 1;
  public static final int EVEN = 0;
  public static final int FINITE = 0;
  public static final int TOROIDAL = 1;
  public static final int TRIANGULAR_TOROIDAL = 2;
  public static final int HEXAGON = 3;

  protected int currentState;

  protected int column;
  protected int row;

  protected int nextState;

  protected List<Cell> myNeighbors;

  protected int orientation;

  /**
   *
   * @param x the x location of the cell
   * @param y the y location of the cell
   * @param initState the cell's initial state
   */
  public Cell(int col, int row, int initState) {
    column = col;
    this.row = row;

    currentState = initState;
    myNeighbors = new ArrayList<>();
    orientation = (col + row) % 2;
  }

  /**
   *
   * @param state state of the cell to be set
   */
  public void setState(int state) {
    currentState = state;}

  /**
   *
   * @return next state of the cell
   */
  public abstract int getNextState();

  /**
   *
   * @return cell's current state
   */
  public int getMyCurrentState() {return currentState;}

  /**
   *
   * @return list of nearby empty cells
   */
  public List<Cell> getEmptyAdjacentCells() {return new ArrayList<>();}

  /**
   *
   * @return health of the cell
   */
  public int getHealth() {
    return 0;
  }

  /**
   *
   * @return turns the cell has lived
   */
  public int getTurnsAlive() {
    return 0;
  }

  /**
   *
   * @return column that the cell is in
   */
  public int getCol() {
    return column;
  }

  /**
   *
   * @return row that the cell is in
   */
  public int getRow() {
    return row;
  }

  public void initNeighbors(int code, int width, int height, Grid grid) {
    myNeighbors = new ArrayList<>();
    switch (code) {
      case FINITE -> {finiteNeighbors(width, height, grid);}
      case TOROIDAL -> {toroidalNeighbors(width, height, grid);}
      case TRIANGULAR_TOROIDAL -> {triangleNeighbors(width, height, grid);}
      case HEXAGON -> {initHexagonNeighbors(width, height, grid);}
      default -> toroidalNeighbors(width, height, grid);
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
        currentState, myNeighbors == null ? 0 : myNeighbors.size(), row, column);}

  public boolean isReproducing() {return false;}

  public Cell getCurrentObject() {return this;}

  private void finiteNeighbors(int width, int height, Grid grid) {
    for (int i = -1; i < 2; i++) {
      for (int k = -1; k < 2; k++) {
        if (inBounds(column + k, row + i, width, height) && (i !=0 || k !=0)) {
          myNeighbors.add(grid.getRow(row + i).get(column + k));
        }
      }
    }
  }

  private void toroidalNeighbors(int width, int height, Grid grid) {
    for (int i = -1; i < 2; i++) {
      for (int k = -1; k < 2; k++) {
        if (i !=0 || k !=0) {
          int x = flip(column + k, width);
          int y = flip(row + i, height);
          myNeighbors.add(grid.getRow(y).get(x));
        }
      }
    }
  }

  private void triangleNeighbors(int width, int height, Grid grid) {

    for (int i = -1; i < 2; i++) {
      int[] vals = getVals(i);
      int k = vals[0];
      int b = vals[1];

      for (; k < b; k++) {
        if (i !=0 || k !=0) {
          int x = flip(column + k, width);
          int y = flip(row + i, height);
          myNeighbors.add(grid.getRow(y).get(x));
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
  private boolean isFacingDown() {return orientation == EVEN;}

  private boolean isFacingUp() {return orientation == ODD;}

  private void initHexagonNeighbors(int width, int height, Grid grid) {
    for (int i = -1; i < 2; i++) {
      int guard;
      if (i == 0) guard = 2;
      else guard = 1;
      for (int k = -1; k < guard; k++) {
        if (i !=0 || k !=0) {
          int x = flip(column + k, width);
          int y = flip(row + i, height);
          myNeighbors.add(grid.getRow(y).get(x));
        }
      }
    }
  }

  protected void death() {return;}


}
