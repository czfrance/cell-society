package cellsociety.cells;

public class EmptyCell extends Cell {

  public EmptyCell(int x, int y, int initState) {
    super(x, y, initState);
    currentState = WaTorCell.EMPTY;
  }

  @Override
  public int getNextState() {
    return currentState;
  }

  public void death(){}
}
