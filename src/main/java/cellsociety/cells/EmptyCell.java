package cellsociety.cells;

import javafx.scene.paint.Color;

public class EmptyCell extends Cell {

  public EmptyCell(double x, double y, double size) {
    super(x, y, size);
    this.setFill(Color.YELLOW);
  }
}
