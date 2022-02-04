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

  public WaTorModel(Map<String, String> dataValues) {
    super(dataValues);
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

  private Cell cellUpdater(Cell cell) {
    cell.update();
    int x = cell.getRow();
    int y = cell.getColumn();
    //TODO: BE CAREFUL ABOUT INFINITE LOOPS HERE IF A BLOCK HAS NO WHERE TO GO
    while (tempGrid[x][y] != null) {
      cell = cell.reupdate();
      cell.update();
      x = cell.getRow();
      y = cell.getColumn();
    }
    return cell;
  }

  private void makeTempGrid() {

    tempGrid = new Cell[HEIGHT][WIDTH];

    for (List<Cell> list : myGrid) {
      for (Cell cell : list) {
        int x = cell.getRow();
        int y = cell.getColumn();
        //Step 1: place cell's into new random location
        cell = cellUpdater(cell);

        if (cell.getState() != EMPTY) {
          tempGrid[cell.getRow()][cell.getColumn()] = cell;
        }

        //Step 2: check for reproduction, if they reprodocued, place
        if (cell.getCurrentObject().isReproducing()) {
          tempGrid[x][y] = new WaTorCell(x, y, cell.getCurrentObject().getID(), 5, 5, 5);
        }
      }
    }
  }

  private List<List<Cell>> convertTempList() {
    List<List<Cell>> ret = new ArrayList<>();
    for (Cell[] list : tempGrid) {
      List<Cell> temp = new ArrayList<>(Arrays.asList(list));
      ret.add(temp);
    }
    return ret;
  }


  public void updateGrid() {
    makeTempGrid();

    myGrid.setGrid(convertTempList());
  }

}

