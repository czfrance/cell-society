package cellsociety.models;

import cellsociety.cells.LifeCell;
import java.util.Map;

/**
 * author: Cynthia France, Jose Santillan
 */
public class GameOfLifeModel extends SimulationModel {

  private static final int DEAD = 0;
  private static final int ALIVE = 1;

  public GameOfLifeModel(Map<String, String> dataValues, String language) {
    super(dataValues, language);
    simType = "GameOfLife";
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
        case '0' -> {myGrid.getRow(rowNum).add(new LifeCell(colNum, rowNum, DEAD));colNum++;}
        case '1' -> {myGrid.getRow(rowNum).add(new LifeCell(colNum, rowNum, ALIVE));colNum++;}
        default -> {}
      }
    }
  }
}
