package cellsociety.cells;

import cellsociety.models.Grid;

public class EmptyWaTorCell extends Cell{

  public EmptyWaTorCell(int x, int y, int initState) {
    super(x, y, initState);
  }

  @Override
  public int getNextState() {
    return 0;
  }
  @Override
  public void update(int width, int height, Grid grid) {

  }
}
