package cellsociety.cells;

import javafx.scene.paint.Color;

public class EmptyCell extends Cell {

  public EmptyCell(int x, int y, double size, String initState) {
    super(x, y, size, initState);
    this.setFill(Color.YELLOW);
  }
}
