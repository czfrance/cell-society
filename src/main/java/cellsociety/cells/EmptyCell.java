package cellsociety.cells;

public class EmptyCell extends Cell {

  public EmptyCell(int x, int y, int initState) {
    super(x, y, initState);
    ID = EMPTY;
  }

  @Override
  public int getNextState() {
    return myState;
  }

  public void death(){}
}
