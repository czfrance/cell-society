package cellsociety.cells;

import java.util.ArrayList;
import java.util.List;

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
  }

  public List<Cell> getMyNeighbors() {
    return myNeighbors;
  }

  public void nextState() {}

  public void setState(int state) {myState = state;}

  public abstract int getNextState();

  public int getMyState() {return myState;}

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

  private List<Cell> edgeNeighbors(int code, List<List<Cell>> grid) {
    switch (code) {
      case TOP_EDGE:
        return findEdgeNeighbors(0, 2, -1, 2, grid);
      case BOTTOM_EDGE:
        return findEdgeNeighbors(-1, 1, -1, 2, grid);
      case RIGHT_EDGE:
        return findEdgeNeighbors(-1, 2, -1, 1, grid);
      case LEFT_EDGE:
        return findEdgeNeighbors(-1, 2, 0, 2, grid);
      default:
        System.out.println("NOT AN EDGE");
        return null;
    }
  }

  private List<Cell> findEdgeNeighbors(int outerStart, int outerEnd, int innerStart, int innerEnd, List<List<Cell>> grid) {
    List<Cell> neighbors = new ArrayList<>();
    int currOut = outerStart;
    int currIn;
    for (;currOut < outerEnd; currOut++) {
      currIn = innerStart;
      for (;currIn < innerEnd; currIn++) {
        if (currOut!=0 || currIn!=0) {
          neighbors.add(grid.get(ROW + currOut).get(COLUMN + currIn));
        }
      }
    }
    return neighbors;
  }

  private List<Cell> cornerNeighbors(int code, List<List<Cell>> grid) {
    List<Cell> neighbors = new ArrayList<>();

    switch (code) {
      case TOP_LEFT:
        neighbors.add(grid.get(ROW + 1).get(COLUMN + 1));
        neighbors.add(grid.get(ROW).get(COLUMN + 1));
        neighbors.add(grid.get(ROW + 1).get(COLUMN));
        break;
      case TOP_RIGHT:
        neighbors.add(grid.get(ROW + 1).get(COLUMN));
        neighbors.add(grid.get(ROW + 1).get(COLUMN - 1));
        neighbors.add(grid.get(ROW).get(COLUMN - 1));
        break;

      case BOTTOM_LEFT:
        neighbors.add(grid.get(ROW - 1).get(COLUMN + 1));
        neighbors.add(grid.get(ROW - 1).get(COLUMN));
        neighbors.add(grid.get(ROW).get(COLUMN + 1));
        break;
      case BOTTOM_RIGHT:
        neighbors.add(grid.get(ROW - 1).get(COLUMN - 1));
        neighbors.add(grid.get(ROW).get(COLUMN - 1));
        neighbors.add(grid.get(ROW - 1).get(COLUMN));
        break;
      default:
        System.out.println("NOT A CORNER");
    }
    return neighbors;
  }

  private List<Cell> centerNeighbors(List<List<Cell>> grid) {
    List<Cell> neighbors = new ArrayList<>();
    for (int i = -1; i < 2; i++) {
      for (int j = -1; j < 2; j++) {
        if (i!=0 || j!=0) {
          neighbors.add(grid.get(ROW + i).get(COLUMN + j));
        }
      }
    }
    return neighbors;
  }

  @Override
  public String toString() {return String.format("State %s, Neighbors %d, numNeighborsAlive %d, row: %d, column: %d", myState, myNeighbors == null ? 0 : myNeighbors.size(), myNeighbors == null ? 0 : numAlive(), ROW, COLUMN);}

  public boolean isAlive() {return myState == 1;}

  public int numAlive() {
    int counter = 0;
    for (Cell c : myNeighbors) {
      if (c.isAlive()) counter++;
    }
    return counter;
  }

  public void update() {}

  public int getID() {return ID;}

  public int getRow() {return ROW;}

  public int getColumn() {return COLUMN;}

  public int getState() {return myState;}

  protected void death() {}

  protected int getNutrition() {return -1;}
}
