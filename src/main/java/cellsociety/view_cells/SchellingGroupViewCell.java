package cellsociety.view_cells;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.paint.Color;

/**
 * author: Cynthia France
 */
public class SchellingGroupViewCell extends ViewCell {

  public static final int EMPTY = 0;
  public static final int GROUP_1 = 1;
  public static final int GROUP_2 = 2;

  /**
   *
   * @param x the x location of the cell
   * @param y the y location of the cell
   * @param size size of a cell
   * @param initState initial state of cell
   */
  public SchellingGroupViewCell(int x, int y, double size, int initState) {
    super(x, y, size, initState);

    states = new HashMap<>(
        Map.of(
            EMPTY, Color.WHITE,
            GROUP_1, Color.RED,
            GROUP_2, Color.BLUE
        )
    );

    setFill(states.get(initState));
  }
}
