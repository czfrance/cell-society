package cellsociety.cells;

public class BlockedPercolationCell extends Cell {

  //FOR PERCOLATION
  public BlockedPercolationCell(int x, int y, int initState) {
    super(x, y, initState);
  }

  @Override
  public int getNextState() {
    return myState;
  }

  public void death() {}
}
