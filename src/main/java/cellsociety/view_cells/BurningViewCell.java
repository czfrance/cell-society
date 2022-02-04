package cellsociety.view_cells;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.paint.Color;

public class BurningViewCell extends ViewCell {

  public static final int TREE = 0;
  public static final int BURNING = 1;
  public static final int DEAD = 2;

  public BurningViewCell(int x, int y, double size, int initState) {
    super(x, y, size, initState);

    states = new HashMap<>(
        Map.of(
            TREE, Color.LIGHTGREEN,
            BURNING, Color.RED,
            DEAD, Color.SADDLEBROWN
        )
    );

    setFill(states.get(initState));
  }
}
