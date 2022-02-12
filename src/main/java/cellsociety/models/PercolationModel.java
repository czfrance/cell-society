package cellsociety.models;

import cellsociety.cells.*;
import cellsociety.cells.PercolatingCell;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author: Cynthia France
 */
public class PercolationModel extends SimulationModel {

  public static final int EMPTY = 1;
  boolean initStateIsSet = false;

  /**
   *
   * @param dataValues values from the xml file
   * @param language language to use
   */
  public PercolationModel(Map<String, String> dataValues, String language) {
    super(dataValues, language);
  }

  /**
   * updates the grid to new states
   */
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
        case '0' -> {myGrid.getRow(rowNum).add(new PercolatingCell(colNum, rowNum, 0)); colNum++;}
        case '1' -> {myGrid.getRow(rowNum).add(new PercolatingCell(colNum, rowNum, 1)); colNum++;}
        case '2' -> {myGrid.getRow(rowNum).add(new PercolatingCell(colNum, rowNum, 2)); colNum++;}
        default -> {}
      }
    }
  }

  private void initFilledCells() {
    for (Cell c : myGrid.getRow(0)) {
      if ((c.getMyCurrentState() == EMPTY)) {
        ((PercolatingCell) c).makeFilled();
      }
    }
  }
}
