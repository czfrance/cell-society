package cellsociety.view_cells;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.paint.Color;

/**
 * author: Cynthia France
 */
public class PercolatingViewCell extends ViewCell {

  public static final int BLOCKED = 0;
  public static final int EMPTY = 1;
  public static final int FILLED = 2;

  /**
   *
   * @param x the x location of the cell
   * @param y the y location of the cell
   * @param size size of a cell
   * @param initState initial state of cell
   */
  public PercolatingViewCell(int x, int y, double size, int initState) {
    super(x, y, size, initState);

    states = new HashMap<>(
        Map.of(
            BLOCKED, Color.BLACK,
            EMPTY, Color.WHITE,
            FILLED, Color.LIGHTBLUE
        )
    );

    setFill(states.get(initState));
  }
}
