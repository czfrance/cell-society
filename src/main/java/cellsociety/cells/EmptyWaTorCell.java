package cellsociety.cells;

import java.util.List;

public class EmptyWaTorCell extends Cell{

  public EmptyWaTorCell(int x, int y, int initState) {
    super(x, y, initState);
  }

  @Override
  public int getNextState() {
    return currentState;
  }
  @Override
  public void update(int width, int height, List<List<Cell>> grid) {
  }
}
