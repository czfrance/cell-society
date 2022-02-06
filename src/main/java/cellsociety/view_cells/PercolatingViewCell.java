package cellsociety.view_cells;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.paint.Color;

public class PercolatingViewCell extends ViewCell {

  public static final int BLOCKED = 0;
  public static final int EMPTY = 1;
  public static final int FILLED = 2;

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
