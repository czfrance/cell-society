package cellsociety.models;

import cellsociety.cells.*;
import cellsociety.cells.PercolatingCell;
import java.util.ArrayList;
import java.util.Map;

public class PercolationModel extends SimulationModel {
  public static final int BLOCKED = 5;
  public static final int OPEN = 0;
  public static final int FULL = 1;
  //protected int[][] myGrid;
  private int myOpenCount;


  //percolates, isFull, isOpen, open
  public PercolationModel(Map<String, String> dataValues) {
    super(dataValues);
    //myGrid = new int[n][n];
    myOpenCount = 0;
    initFilledCells();
//    for (int[] row : myGrid)
//      Arrays.fill(row, BLOCKED);
  }

  @Override
  protected void createGrid() {
    myGrid.add(new ArrayList<>());
    int rowNum = 0;
    int colNum = 0;
    for (int i = 0; i < simInfo.get(DATA_FIELDS.get(6)).toCharArray().length; i++) {
      char c = simInfo.get(DATA_FIELDS.get(6)).toCharArray()[i];

      //0 = blocked, 1 = empty, 2 = filled
      switch (c) {
        case '.' -> {myGrid.add(new ArrayList<>()); rowNum++; colNum = 0;}
        case '0' -> {myGrid.get(rowNum).add(new BlockedPercolationCell(colNum, rowNum, 0)); colNum++;}
        case '1' -> {myGrid.get(rowNum).add(new PercolatingCell(colNum, rowNum, 0)); colNum++;}
        case '2' -> {myGrid.get(rowNum).add(new PercolatingCell(colNum, rowNum, 1)); colNum++;}
        //case '2', '3', '4', '5', '6', '7', '8', '9' -> myGrid.get(rowNum).add(Character.getNumericValue(c));
        default -> {}
      }
    }
  }

  private void initFilledCells() {
    for (Cell c : myGrid.get(0)) {
      if ((c instanceof PercolatingCell)) {
        ((PercolatingCell) c).makeFilled();;
      }
    }
  }

  public void open(int row, int col) {

    if (! inBounds(row,col)) {
      throw new IndexOutOfBoundsException(
          String.format("(%d,%d) not in bounds", row,col));
    }
    if (myGrid.get(row).get(col) instanceof BlockedPercolationCell)
      return;
    myOpenCount += 1;
    ((PercolatingCell) myGrid.get(row).get(col)).makeOpen();
    updateOnOpen(row,col);
  }

  public boolean isOpen(int row, int col) {

    if (! inBounds(row,col)) {
      throw new IndexOutOfBoundsException(
          String.format("(%d,%d) not in bounds", row,col));
    }
    return myGrid[row][col] != BLOCKED;
  }

  public boolean isFull(int row, int col) {

    if (! inBounds(row,col)) {
      throw new IndexOutOfBoundsException(
          String.format("(%d,%d) not in bounds", row,col));
    }

    return myGrid[row][col] == FULL;
  }

  private void clearFull() {
    for (int i = 0; i < myGrid.length; i++) {
      for (int j = 0; j < myGrid[0].length; j++) {
        if (myGrid[i][j] == FULL) {
          myGrid[i][j] = OPEN;
        }
      }
    }
  }

  protected void updateOnOpen(int row, int col) {
    clearFull();
    for (int k = 0; k < myGrid[0].length; k++)
      dfs(0, k);
  }

  public boolean percolates() {
    for (int col = 0; col < myGrid[myGrid.length - 1].length; col++)
      if (isFull(myGrid.length - 1, col))
        return true;
    return false;
  }

  /**
   * Private helper method to mark all cells that are open and reachable from
   * (row,col).
   *
   * @param row
   *            is the row coordinate of the cell being checked/marked
   * @param col
   *            is the col coordinate of the cell being checked/marked
   */
  protected void dfs(int row, int col) {
    // out of bounds?
    if (! inBounds(row,col)) return;

    // full or NOT open, don't process
    if (isFull(row, col) || !isOpen(row, col))
      return;

    myGrid[row][col] = FULL;
    dfs(row - 1, col);
    dfs(row, col - 1);
    dfs(row, col + 1);
    dfs(row + 1, col);
  }

  public int numberOfOpenSites() {
    return myOpenCount;
  }

  /**
   * Determine if (row,col) is valid for given grid
   * @param row specifies row
   * @param col specifies column
   * @return true if (row,col) on grid, false otherwise
   */
  protected boolean inBounds(int row, int col) {
    if (row < 0 || row >= myGrid.length) return false;
    if (col < 0 || col >= myGrid[0].length) return false;
    return true;
  }
}
