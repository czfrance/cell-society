package cellsociety.models;

import cellsociety.cells.Cell;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Grid implements Iterable<List<Cell>> {

  private List<List<Cell>> myGrid;
  private int WIDTH;
  private int HEIGHT;

  public Grid(int width, int height) {
    myGrid = new ArrayList<>();
    myGrid.add(new ArrayList<>());

    WIDTH = width;
    HEIGHT = height;
  }

  public Grid(List<List<Cell>> grid) {
    myGrid = grid;
    WIDTH = grid.size();
    HEIGHT = (WIDTH == 0) ? grid.get(0).size() : 0;
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

  public void updateGrid(List<List<Integer>> newGrid) {
    for (int i = 0; i < WIDTH; i++) {
      for (int k = 0; k < HEIGHT; k++) {
        myGrid.get(i).get(k).setState(newGrid.get(i).get(k));
      }
    }
  }
}
