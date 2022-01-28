package cellsociety.cells;

import java.util.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Cell extends Rectangle {

  public final int COLUMN;
  public final int ROW;

  private String myState;

  public Cell(int x, int y, double size, String initState) {
    super(x * size, y * size, size, size);
    this.setFill(Color.BLACK);
    COLUMN = x;
    ROW = y;

    myState = initState;
  }

  public String getState() {
    return myState;
  }

  // FIXME: ADD THIS BACK IN ONCE CELL SUBCLASS GETNEIGHBORS() METHODS ARE WRITTEN IN
  //    (COMMENTED IT OUT BC I DIDNT HAVE TIME TO WRITE LMAO)
  //public abstract ArrayList<Cell> getNeighbors();

}
