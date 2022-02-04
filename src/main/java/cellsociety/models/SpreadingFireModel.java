package cellsociety.models;

import cellsociety.cells.BlockedPercolationCell;
import cellsociety.cells.BurningCell;
import cellsociety.cells.PercolatingCell;
import java.util.ArrayList;
import java.util.Map;

public class SpreadingFireModel extends SimulationModel {

  public SpreadingFireModel(Map<String, String> dataValues) {
    super(dataValues);
  }

  @Override
  protected void createGrid() {
    myGrid.add(new ArrayList<>());
    int rowNum = 0;
    int colNum = 0;
    for (int i = 0; i < simInfo.get(DATA_FIELDS.get(6)).toCharArray().length; i++) {
      char c = simInfo.get(DATA_FIELDS.get(6)).toCharArray()[i];
      switch (c) {
        case '.' -> {myGrid.add(new ArrayList<>()); rowNum++; colNum = 0;}
        case '0', '1', '2' -> {myGrid.get(rowNum).add(new BurningCell(colNum, rowNum, Character.getNumericValue(c), PROBCATCH)); colNum++;}
        //case '1' -> {myGrid.get(rowNum).add(new PercolatingCell(colNum, rowNum, 0)); colNum++;}
        //case '2' -> {myGrid.get(rowNum).add(new PercolatingCell(colNum, rowNum, 1)); colNum++;}
        //case '2', '3', '4', '5', '6', '7', '8', '9' -> myGrid.get(rowNum).add(Character.getNumericValue(c));
        default -> {}
      }
    }
  }
}
