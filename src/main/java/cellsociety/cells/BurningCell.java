package cellsociety.cells;

public class BurningCell extends Cell{

  public BurningCell(int x, int y, int initState) {
    super(x, y, initState);
  }

  @Override
  public int getNextState() {
    return myState;
  }
}
