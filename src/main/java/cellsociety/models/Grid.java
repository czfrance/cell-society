package cellsociety.models;

import cellsociety.cells.Cell;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Grid implements Iterable<List<Cell>> {

  private List<List<Cell>> myGrid;

  public Grid() {
    myGrid = new ArrayList<>();
  }

  public Grid(List<List<Cell>> grid) {
    myGrid = grid;
  }

  public void setGrid(List<List<Cell>> newGrid) {
    myGrid = newGrid;
  }

  public List<List<Cell>> getGrid() {
    return myGrid;
  }

  public int size() {
    return myGrid.size();
  }

  @Override
  public Iterator<List<Cell>> iterator() {
    return myGrid.iterator();
  }

  public List<Cell> getRow(int x) {
    return myGrid.get(x);
  }

  public void addRow() {
    myGrid.add(new ArrayList<Cell>());
  }
}
