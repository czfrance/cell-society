package cellsociety.models;

import cellsociety.cells.Cell;
import cellsociety.cells.WaTorCell;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WaTorModel extends SimulationModel {

  private Cell[][] tempGrid;

  private static final int EMPTY = 0;
  private static final int FISH = 1;
  private static final int SHARK = 2;

  public WaTorModel(Map<String, String> dataValues, String language) {
    super(dataValues, language);
    simType = "WaTor";
    tempGrid = new WaTorCell[HEIGHT][WIDTH];

  }

  @Override
  protected void createGrid() {
    myGrid.addRow();
    int rowNum = 0;
    int colNum = 0;
    for (int i = 0; i < simInfo.get(DATA_FIELDS.get(6)).toCharArray().length; i++) {
      char c = simInfo.get(DATA_FIELDS.get(6)).toCharArray()[i];
      switch (c) {
        case '.' -> {myGrid.addRow();rowNum++;colNum = 0;}
        case '0' -> {myGrid.getRow(rowNum).add(new WaTorCell(colNum, rowNum, EMPTY, 0, 0, 0));colNum++;}
        case '1' -> {myGrid.getRow(rowNum).add(new WaTorCell(colNum, rowNum, FISH, 5, 5, 5));colNum++;}
        case '2' -> {myGrid.getRow(rowNum).add(new WaTorCell(colNum, rowNum, SHARK, 5, 5, 5));colNum++;}
        default -> {}
        }
      }

  }

  public void updateGrid() {
    unBlockAll();

    makeTempGrid();

    myGrid = newGrid();
  }
  private Cell cellUpdater(Cell cell) {
    cell.update(WIDTH, HEIGHT, myGrid);
    if (cell.getState() == EMPTY) return cell;
    int x = cell.getRow();
    int y = cell.getColumn();
    //TODO: BE CAREFUL ABOUT INFINITE LOOPS HERE IF A BLOCK HAS NO WHERE TO GO
    int counter = 0;
    while (tempGrid[x][y] != null) {
      cell = cell.reupdate();

      if (counter != 10) cell.update(WIDTH, HEIGHT, myGrid);

      x = cell.getRow();
      y = cell.getColumn();
      counter++;
    }
    return cell;
  }

  private void makeTempGrid() {

    tempGrid = new Cell[HEIGHT][WIDTH];
    int i = 0;
    for (List<Cell> list : myGrid) {
      for (Cell cell : list) {
        int row = cell.getRow();
        int col = cell.getColumn();

        //Step 1: place cell's into new random location
        cell = cellUpdater(cell);
        if (cell.getState() != EMPTY) {
          tempGrid[cell.getRow()][cell.getColumn()] = cell;
          cell.block();
        }

        //Step 2: Reproduce
        if (cell.getCurrentObject().isReproducing()) {
          tempGrid[row][col] = new WaTorCell(col, row, cell.getCurrentObject().getID(), 5, 5, 5);
          cell.getCurrentObject().resetReproductionTimer();
        }
      }
    }
  }

  public Grid newGrid() {
    Grid newGrid = new Grid(WIDTH, HEIGHT);
    newGrid.getGrid().clear();
    for (int i = 0; i < HEIGHT; i++) {
      List<Cell> temp = new ArrayList<>();
      for (int k = 0; k < WIDTH; k++) {
        Cell cell = tempGrid[i][k];
        if (cell == null) temp.add(new WaTorCell(i, k, EMPTY, 5, 5, 5));
        else if (!cell.getCurrentObject().isDead()) temp.add(cell);
        else temp.add(new WaTorCell(i, k, EMPTY, 5, 5, 5));
        System.out.print(String.format("(%d, %d)", temp.get(k).getColumn(), temp.get(k).getRow()));
      }
      System.out.println();
      newGrid.addRow(temp);
    }
    return newGrid;
  }

  private void unBlockAll() {
    for(List<Cell> list : myGrid) {
      for (Cell cell : list) {
        cell.unblock();
      }
    }
  }

  private void printTempGrid() {
    for (Cell[] list : tempGrid) {
      for (Cell c : list) {
        if (c == null) System.out.print("0");
        else System.out.print(c.getState());
      }
      System.out.println();
    }
  }
}

/*
(0, 0)(0, 1)(0, 2)(0, 3)(0, 4)(0, 5)(6, 0)(0, 7)(8, 0)(0, 9)(10, 0)(11, 0)(0, 12)(0, 13)
(1, 0)(1, 1)(1, 2)(3, 1)(4, 1)(1, 5)(6, 1)(1, 7)(1, 8)(9, 1)(1, 10)(1, 11)(12, 1)(1, 13)
(2, 0)(1, 2)(2, 2)(2, 3)(4, 2)(2, 5)(2, 6)(7, 2)(2, 8)(9, 2)(10, 2)(2, 11)(12, 2)(2, 13)
(3, 0)(3, 1)(2, 3)(3, 3)(4, 3)(3, 5)(3, 6)(7, 3)(3, 8)(3, 9)(3, 10)(3, 11)(3, 12)(13, 3)
(4, 0)(4, 1)(4, 2)(4, 3)(4, 4)(4, 5)(4, 6)(4, 7)(8, 4)(4, 9)(10, 4)(11, 4)(4, 12)(4, 13)
(5, 0)(5, 1)(5, 2)(5, 3)(4, 5)(5, 5)(5, 6)(5, 7)(8, 5)(5, 9)(5, 10)(11, 5)(5, 12)(5, 13)
(6, 0)(6, 1)(6, 2)(6, 3)(6, 4)(6, 5)(6, 6)(7, 6)(6, 8)(6, 9)(6, 10)(11, 6)(6, 12)(6, 13)
(7, 0)(1, 7)(7, 2)(7, 3)(4, 7)(5, 7)(7, 6)(7, 7)(8, 7)(7, 9)(7, 10)(11, 7)(12, 7)(7, 13)
(8, 0)(8, 1)(2, 8)(8, 3)(8, 4)(5, 8)(8, 6)(8, 7)(8, 8)(8, 9)(8, 10)(8, 11)(8, 12)(8, 13)
 */