package cellsociety.cells;

import javafx.scene.paint.Color;

public class LifeCell extends Cell{

  public LifeCell(int x, int y, double size, String initState) {
    super(x, y, size, initState);
    this.setFill(Color.BLUE);
  }
}
