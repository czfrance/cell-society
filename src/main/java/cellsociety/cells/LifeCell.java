package cellsociety.cells;

import javafx.scene.paint.Color;

public class LifeCell extends Cell{

  public LifeCell(double x, double y, double size) {
    super(x, y, size);
    this.setFill(Color.BLUE);
  }
}
