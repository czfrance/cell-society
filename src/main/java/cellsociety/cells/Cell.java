package cellsociety.cells;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
  public Cell(double x, double y, double size) {
    super (x, y, size, size);
    this.setFill(Color.BLACK);
  }
}
