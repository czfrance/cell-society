package cellsociety.cells;

import java.util.*;

public abstract class Cell {
  protected int ID;

  protected int COLUMN;
  protected int ROW;

  public final int TOP_LEFT = 1;
  public final int TOP_RIGHT = 2;
  public final int BOTTOM_LEFT = 3;
  public final int BOTTOM_RIGHT = 4;

  public final int LEFT_EDGE = 1;
  public final int RIGHT_EDGE = 2;
  public final int TOP_EDGE = 3;
  public final int BOTTOM_EDGE = 4;

  protected int myState;
  protected int nextState;

  public final int EMPTY = 0;
  public final int FISH = 1;
  public final int SHARK = 2;

  protected List<Cell> myNeighbors;

  public Cell(int x, int y, int initState) {
    COLUMN = x;
    ROW = y;

    myState = initState;
  }

  public List<Cell> getMyNeighbors() {
    return myNeighbors;
  }

  public void nextState() {

  }

  /**
   * This method is unique in the fact that it identifies if the cell is not only an
   * edge piece, but identifies which edge it is on

   * @param width of the simulation
   * @param height of the simulation
   * @return  1 = Left Edge
   *          2 = Right Edge
   *          3 = Top Edge
   *          4 = Bottom Edge
   */
  public int isEdge(int width, int height) {
    if (COLUMN == 0) return LEFT_EDGE;
    if (COLUMN == width-1) return RIGHT_EDGE;
    if (ROW == 0) return TOP_EDGE;
    if (ROW == height-1) return BOTTOM_EDGE;
    return -1;
  }

  /**
   * This method is unique in the fact that it identifies if the cell is not only a
   * corner piece, but identifies which corner it is on
   *
   * @param width of the simulation
   * @param height of the simulation
   * @return  1 = TOP LEFT
   *          2 = BOTTOM LEFT
   *          3 = TOP RIGHT
   *          4 = BOTTOM RIGHT
   */
  public int isCorner(int width, int height) {
    if (COLUMN == 0 && ROW == 0) return TOP_LEFT;
    if (COLUMN == 0 && ROW == height-1) return BOTTOM_LEFT;
    if (COLUMN == width-1 && ROW == 0) return TOP_RIGHT;
    if (COLUMN == width-1 && ROW == height-1) return BOTTOM_RIGHT;
    return -1;
  }

  public int getMyState() {
    return myState;
  }

  public void initNeighbors(int width, int height, List<List<Cell>> grid) {
    int corner = isCorner(width, height);
    int edge = isEdge(width, height);
    if (corner != -1) {
      myNeighbors = cornerNeighbors(corner, grid);
    } else if (edge != -1) {
      myNeighbors = edgeNeighbors(edge, grid);
    } else {
      myNeighbors = centerNeighbors(grid);
    }
  }

  private List<Cell> searchNeighbors(int width, int height, List<List<Cell>> grid) {
    List<Cell> ret = new ArrayList<Cell>();
    for (int i = -1; i < -2; i++) {
      for (int j = -1; j < -2; j++) {
        if (inBounds(width, height, ROW + i, COLUMN + j)) {
          ret.add(grid.get(ROW + i).get(COLUMN + j));
        }
      }
    }
    return ret;
  }

  private boolean inBounds(int width, int height, int x, int y) {
    return (x > 0 && x < width) && (y > 0 && y < height);
  }

  @Override
  public String toString() {
    return String.format("State %s, Neighbors %d, numNeighborsAlive %d, row: %d, column: %d",
                          myState, myNeighbors == null ? 0 : myNeighbors.size(),
                          myNeighbors == null ? 0 : numAlive(),
                          ROW, COLUMN);
  }

  public boolean isAlive() {return myState == 1;}

  public int numAlive() {
    int counter = 0;
    for (Cell c : myNeighbors) {
      if (c.isAlive()) counter++;
    }
    return counter;
  }

  public void update() {

  }

  public int getID() {return ID;}

  public int getRow() {return ROW;}

  public int getColumn() {return COLUMN;}

  protected int getNutrition() {
    return -1;
  }
}
