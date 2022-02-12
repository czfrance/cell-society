package cellsociety.models;

import cellsociety.cells.Cell;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 * This class holds and performs actions on the data structure that is the foudnation of the girdlike system that
 * Cellular Automata depend on
 */
public class Grid implements Iterable<List<Cell>> {

  protected List<List<Cell>> myGrid;
  protected int WIDTH;
  protected int HEIGHT;

  /**
   * Constructs a grid and holds the desired width and height of the data structure
   * @param width
   * @param height
   */
  public Grid(int width, int height) {
    myGrid = new ArrayList<>();
    myGrid.add(new ArrayList<>());

    WIDTH = width;
    HEIGHT = height;
  }
  /**
   * A second constructor that initializes a grid based on a previous grid
   */
  public Grid(List<List<Cell>> grid) {
    myGrid = grid;
    WIDTH = grid.size();
    HEIGHT = (WIDTH == 0) ? grid.get(0).size() : 0;
  }

  /**
   * Sets the data structure to the paramter passed
   * @param newGrid
   */
  public void setGrid(List<List<Cell>> newGrid) {myGrid = newGrid;}

  /**
   * @return the Data Structure being held by this object
   */
  public List<List<Cell>> getGrid() {
    return myGrid;
  }

  /**
   * @return Simply returns the height of the Grid (the amount of rows in the Grid)
   */
  public int size() {
    return myGrid.size();
  }

  /**
   * @return A iterable that one can use to loop through (used as a For-Each loop)
   */
  @Override
  public Iterator<List<Cell>> iterator() {return myGrid.iterator();}

  /**
   * Gets the specificed row (0 index)
   * @param x the row a user would like to modify/view
   * @return a List of Cells that corresponds to the x row of the Grid
   */
  public List<Cell> getRow(int x) {
    return myGrid.get(x);
  }

  /**
   * Simply adds a new arraylist to the data structure
   */
  public void addRow() {
    myGrid.add(new ArrayList<>());
  }

  /**
   * This method updates the grid by updating all the states of the cells by using
   * the states passed as a parameter. The new grid is a 1 to 1 update of the new grid
   * i.e. the (3,5) new state is the new state for the (3,5) cell
   * @param newGrid A grid-like data structure with the new states
   */
  public void updateGrid(List<List<Integer>> newGrid) {
    for (int i = 0; i < HEIGHT; i++) {
      for (int k = 0; k < WIDTH; k++) {
        myGrid.get(i).get(k).setState(newGrid.get(i).get(k));
      }
    }
  }

  /**
   * This method initalizes the neighbors of all the cells of the current Grid
   * @param neighborSystem
   */
  public void initNeighbors(int neighborSystem) {
    for (List<Cell> list : myGrid) {
      for (Cell cell : list) {
        cell.initNeighbors(neighborSystem, this.WIDTH, this.HEIGHT, this);
      }
    }
  }

  /**
   * This method converts the current object into an English/Readable format
   * @return A string containing the current states of the grid
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (List<Cell> list : myGrid) {
      for (Cell cell : list) {
        sb.append(cell).append(" ");
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
