package cellsociety.view_cells;

import java.util.Map;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class ViewCell extends Rectangle {

  protected final int COLUMN;
  protected final int ROW;

  protected int myState;
  protected Map<Integer, Color> states;

  public ViewCell(int x, int y, double size, int initState) {
    super(x * size, y * size, size, size);
    setFill(Color.GRAY);
    COLUMN = x;
    ROW = y;

    myState = initState;
  }

  public void updateState(int newState) {
    myState = newState;
  }
}
