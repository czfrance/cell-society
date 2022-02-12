package cellsociety.models;

import cellsociety.cells.BurningCell;
import java.util.Map;

/**
 * author: Cynthia France
 */
public class SpreadingFireModel extends SimulationModel {

  /**
   *
   * @param dataValues values from the xml file
   * @param language language to use
   */
  public SpreadingFireModel(Map<String, String> dataValues, String language) {
    super(dataValues, language);
  }

  @Override
  protected void createGrid() {
    myGrid.addRow();
    int rowNum = 0;
    int colNum = 0;
    for (int i = 0; i < simInfo.get(DATA_FIELDS.get(6)).toCharArray().length; i++) {
      char c = simInfo.get(DATA_FIELDS.get(6)).toCharArray()[i];
      switch (c) {
        case '.' -> {myGrid.addRow(); rowNum++; colNum = 0;}
        case '0', '1', '2' -> {myGrid.getRow(rowNum).add(new BurningCell(colNum, rowNum, Character.getNumericValue(c), PROBCATCH)); colNum++;}
        default -> {}
      }
    }
  }
}
