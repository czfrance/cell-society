package cellsociety.view_cells;

import java.util.Map;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * author: Cynthia France
 */
public abstract class ViewCell extends Rectangle {

  protected final int COLUMN;
  protected final int ROW;

  protected int myState;
  protected Map<Integer, Color> states;

  /**
   *
   * @param x the x location of the cell
   * @param y the y location of the cell
   * @param size size of a cell
   * @param initState initial state of cell
   */
  public ViewCell(int x, int y, double size, int initState) {
    super(x * size, y * size, size, size);
    setFill(Color.GRAY);
    setStroke(Color.GRAY);
    setStrokeWidth(2);
    COLUMN = x;
    ROW = y;

    myState = initState;
  }

  /**
   *
   * @param newState the new state of the cell
   */
  public void updateState(int newState) {
    myState = newState;
    setFill(states.get(myState));
  }
}
