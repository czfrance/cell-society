package cellsociety.models;

import cellsociety.cells.Cell;
import cellsociety.cells.FishCell;
import cellsociety.cells.SharkCell;
import cellsociety.cells.EmptyCell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class WaTorModel extends SimulationModel {

  Cell[][] grid;

  private final int EMPTY = 0;
  private final int FISH = 1;
  private final int SHARK = 2;

  public WaTorModel(Map<String, String> dataValues) {
    super(dataValues);
    simType = "WaTor";
    grid = new Cell[HEIGHT][WIDTH];
  }

  @Override
  protected void createGrid() {
    myGrid.add(new ArrayList<>());
    int rowNum = 0;
    int colNum = 0;
    for (int i = 0; i < simInfo.get(DATA_FIELDS.get(6)).toCharArray().length; i++) {
      char c = simInfo.get(DATA_FIELDS.get(6)).toCharArray()[i];
      switch (c) {
        case '.' -> {myGrid.add(new ArrayList<>());rowNum++;colNum = 0;}
        case '0' -> {myGrid.get(rowNum).add(new EmptyCell(colNum, rowNum, 0));colNum++;}
        case '1' -> {myGrid.get(rowNum).add(new FishCell(colNum, rowNum, 1, 0, 0));colNum++;}
        case '2' -> {myGrid.get(rowNum).add(new SharkCell(colNum, rowNum, 2, 2, 5));colNum++;}
        default -> {}
        }
      }
    System.out.println(this);

  }

  private void clashPrevention() {

  }

  public void updateGrid() {
    for (List<Cell> cellList : myGrid) {
      for (Cell cell : cellList) {
        int id = cell.getID();
        if (id == SHARK) {
          SharkCell c = ((SharkCell)cell).update(WIDTH, HEIGHT, myGrid);
          grid[c.getRow()][c.getColumn()] = c;

        } else if (id == FISH) {
          FishCell c = ((FishCell)cell).update(WIDTH, HEIGHT, myGrid);
          grid[c.getRow()][c.getColumn()] = c;
        }
      }
    }

    myGrid.clear();
    for (Cell[] cellList : grid) {
      List<Cell> list = new ArrayList<Cell>();
      Collections.addAll(list, cellList);
      myGrid.add(list);
    }
    System.out.println("\n\n");
    System.out.println(this);

  }

  public String toString() {
    for (List<Cell> list : myGrid) {
      for (Cell c : list) {
        System.out.print(c.getState() + " ");
      }
      System.out.println();
    }
    return "";
  }

}

