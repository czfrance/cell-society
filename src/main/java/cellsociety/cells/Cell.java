package cellsociety.cells;

import java.util.*;

public abstract class Cell {

  public final int COLUMN;
  public final int ROW;

  private String myState;

  public Cell(int x, int y, String initState) {
      COLUMN = x;
      ROW = y;

      myState = initState;
  }

  public String getState() {
    return myState;
  }

  public abstract ArrayList<Cell> getNeighbors();


}
