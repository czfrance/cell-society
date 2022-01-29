package cellsociety.cells;

public class PercolatingCell extends Cell {

  //FOR PERCOLATION
  public PercolatingCell(int x, int y, int initState) {
    super(x, y, initState);
  }

  public void makeFilled() {
    myState = 1;
  }
  public void makeOpen() {
    myState = 0;
  }
}
