package cellsociety.models;

import cellsociety.cells.LifeCell;
import java.util.ArrayList;
import java.util.Map;

public class GameOfLifeModel extends SimulationModel {

  private final int DEAD = 0;
  private final int ALIVE = 1;

  public GameOfLifeModel(Map<String, String> dataValues) {
    super(dataValues);
    simType = "GameOfLife";
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
        case '0' -> {myGrid.get(rowNum).add(new LifeCell(colNum, rowNum, DEAD));colNum++;}
        case '1' -> {myGrid.get(rowNum).add(new LifeCell(colNum, rowNum, ALIVE));colNum++;}
        default -> {}
      }
    }
  }
}
