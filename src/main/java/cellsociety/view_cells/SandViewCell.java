package cellsociety.view_cells;

import java.util.Map;
import java.util.HashMap;
import javafx.scene.paint.Color;

public class SandViewCell extends ViewCell{

  public SandViewCell(int x, int y, double size, int initState) {
    super(x, y, size, initState);

    states = new HashMap<>(
        Map.of(
            0, Color.BLACK,
            1, Color.YELLOW,
            2, Color.DARKGRAY
        )
    );

    setFill(states.get(initState));
  }



}
