package cellsociety.view_cells;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.paint.Color;

public class BlockedPercolationViewCell extends ViewCell {

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
