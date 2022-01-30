package cellsociety.cells;

public class FishCell extends Cell {

  public FishCell(int x, int y, int initState) {
    super(x, y, initState);
  }

  @Override
  public int getNextState() {
    return myState;
  }
}
