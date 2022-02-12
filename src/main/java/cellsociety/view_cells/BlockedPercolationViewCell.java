package cellsociety.view_cells;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.paint.Color;

/**
 * author: Cynthia France
 */
public class BlockedPercolationViewCell extends ViewCell {

  /**
   *
   * @param x the x location of the cell
   * @param y the y location of the cell
   * @param size size of a cell
   * @param initState initial state of cell
   */
  public BlockedPercolationViewCell(int x, int y, double size, int initState) {
    super(x, y, size, initState);

    states = new HashMap<>(
        Map.of(
            0, Color.BLACK
        )
    );
    setFill(states.get(initState));
  }
}
