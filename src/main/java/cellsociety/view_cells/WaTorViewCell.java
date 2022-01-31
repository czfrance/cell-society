package cellsociety.view_cells;

import cellsociety.cells.WaTorCell;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.paint.Color;

public class WaTorViewCell extends ViewCell {


  public WaTorViewCell(int x, int y, double size, int initState, WaTorCell cell) {
    super(x, y, size, initState);

    states = new HashMap<>(
        Map.of(
            0, Color.BLACK,
            1, Color.GREEN,
            2, Color.BLUE
        )
    );
    setFill(states.get(cell.getCurrentState()));
  }

  @Override
  public void updateState(int newState) {
    myState = newState;
    setFill(states.get(myState));
  }
}
