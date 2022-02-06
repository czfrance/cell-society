package cellsociety.cells;

public class BlockedPercolationCell extends Cell {

  //FOR PERCOLATION
  public BlockedPercolationCell(int x, int y, int initState) {
    super(x, y, initState);
  }

  @Override
  public int getCurrentState() {
    return 0;
  }

  @Override
  public int getNextState() {
    return currentState;
  }

  public void death() {}
}
