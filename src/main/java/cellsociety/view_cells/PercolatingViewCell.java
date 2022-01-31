package cellsociety.view_cells;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.paint.Color;

public class PercolatingViewCell extends ViewCell {

  public static final int EMPTY = 0;
  public static final int FILLED = 1;

  public PercolatingViewCell(int x, int y, double size, int initState) {
    super(x, y, size, initState);

    states = new HashMap<>(
        Map.of(
            EMPTY, Color.WHITE,
            FILLED, Color.LIGHTBLUE
        )
    );

    setFill(states.get(initState));
  }
}
