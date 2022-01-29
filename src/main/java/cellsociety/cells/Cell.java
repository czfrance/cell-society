package cellsociety.cells;

import java.util.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Cell { // extends Rectangle {

  protected final int COLUMN;
  protected final int ROW;

  public final int TOP_LEFT = 1;
  public final int TOP_RIGHT = 2;
  public final int BOTTOM_LEFT = 3;
  public final int BOTTOM_RIGHT = 4;

  public final int LEFT_EDGE = 1;
  public final int RIGHT_EDGE = 2;
  public final int TOP_EDGE = 3;
  public final int BOTTOM_EDGE = 4;

  protected int myState;

  protected ArrayList<Cell> myNeighbors;

  public Cell(int x, int y, int initState) {
    COLUMN = x;
    ROW = y;

    myState = initState;
  }

  public int getState() {
    return myState;
  }

  // FIXME: ADD THIS BACK IN ONCE CELL SUBCLASS GETNEIGHBORS() METHODS ARE WRITTEN IN
  //    (COMMENTED IT OUT BC I DIDNT HAVE TIME TO WRITE LMAO)
  //public abstract ArrayList<Cell> getNeighbors();


  protected ArrayList<Cell> getMyNeighbors() {
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

  }
}
