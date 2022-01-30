package cellsociety.cells;

import javafx.scene.paint.Color;

public class EmptyCell extends Cell {

  public EmptyCell(int x, int y, int initState) {
    super(x, y, initState);
  }

  @Override
  public int getNextState() {
    return myState;
  }
}
