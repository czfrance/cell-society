package cellsociety.cells;

public class GroupCell extends Cell {

  public GroupCell(int x, int y, int initState) {
    super(x, y, initState);
  }

  @Override
  public int getNextState() {
    return myState;
  }

  public Cell death() {return null;}
}
