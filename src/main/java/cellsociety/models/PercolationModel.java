package cellsociety.models;

import cellsociety.cells.*;
import cellsociety.cells.PercolatingCell;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PercolationModel extends SimulationModel {

  boolean initStateIsSet = false;

  public PercolationModel(Map<String, String> dataValues) {
    super(dataValues);
  }

  @Override
  protected void createGrid() {
    myGrid.addRow();
    int rowNum = 0;
    int colNum = 0;
    for (int i = 0; i < simInfo.get(DATA_FIELDS.get(6)).toCharArray().length; i++) {
      char c = simInfo.get(DATA_FIELDS.get(6)).toCharArray()[i];

      //0 = blocked, 1 = empty, 2 = filled
      switch (c) {
        case '.' -> {myGrid.addRow(); rowNum++; colNum = 0;}
        case '0' -> {myGrid.getRow(rowNum).add(new BlockedPercolationCell(colNum, rowNum, 0)); colNum++;}
        case '1' -> {myGrid.getRow(rowNum).add(new PercolatingCell(colNum, rowNum, 0)); colNum++;}
        case '2' -> {myGrid.getRow(rowNum).add(new PercolatingCell(colNum, rowNum, 1)); colNum++;}
        //case '2', '3', '4', '5', '6', '7', '8', '9' -> myGrid.get(rowNum).add(Character.getNumericValue(c));
        default -> {}
      }
    }
  }

  @Override
  public void updateGrid() {
    if (!initStateIsSet) {
      initFilledCells();
      initStateIsSet = true;
    }
    else {
      super.updateGrid();
    }
  }

  private void initFilledCells() {
    for (Cell c : myGrid.getRow(0)) {
      if ((c instanceof PercolatingCell)) {
        ((PercolatingCell) c).makeFilled();;
      }
    }
  }
}
