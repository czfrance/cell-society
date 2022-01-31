package cellsociety.view_cells;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.paint.Color;

public class LifeViewCell extends ViewCell {

  public LifeViewCell(int x, int y, double size, int initState) {
    super(x, y, size, initState);

    states = new HashMap<>(
        Map.of(
            0, Color.BLACK,
            1, Color.WHITE
        )
    );

    setFill(states.get(initState));
  }
}
