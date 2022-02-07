package cellsociety.view_cells;

import java.util.Map;
import java.util.HashMap;
import javafx.scene.paint.Color;

public class SugarViewCell extends ViewCell{

  public SugarViewCell(int x, int y, double size, int initState) {
    super(x, y, size, initState);

    states = new HashMap<>(Map.of(
        0, Color.WHITE,
        1, Color.LIGHTCORAL,
        2, Color.CORAL,
        3, Color.LIGHTSALMON,
        4, Color.ORANGE,
        5, Color.RED
    ));

    setFill(states.get(initState));
  }
}
