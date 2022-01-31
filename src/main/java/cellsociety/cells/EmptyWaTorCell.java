package cellsociety.cells;

public class EmptyWaTorCell extends Cell{

  public EmptyWaTorCell(int x, int y, int initState) {
    super(x, y, initState);
  }

  @Override
  public int getNextState() {
    return 0;
  }
}
