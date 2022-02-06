package cellsociety.models;

import cellsociety.cells.SandCell;
import java.util.Map;
import java.util.Random;
public class FallingSandModel extends SimulationModel{

  private final int AIR = 0;
  private final int SAND = 1;

  private final Random DICE = new Random();
  public FallingSandModel(Map<String, String> dataValues, String language) {
    super(dataValues, language);

  }

  @Override
  protected void createGrid() {
    myGrid.addRow();
    int rowNum = 0;
    int colNum = 0;
    for (int i = 0; i < simInfo.get(DATA_FIELDS.get(CONFIG)).toCharArray().length; i++) {
      char c = simInfo.get(DATA_FIELDS.get(CONFIG)).toCharArray()[i];
      switch (c) {
        case '.' -> {myGrid.addRow();rowNum++;colNum = 0;}
        case '0' -> {myGrid.getRow(rowNum).add(new SandCell(colNum, rowNum, AIR, HEIGHT));colNum++;}
        case '1' -> {myGrid.getRow(rowNum).add(new SandCell(colNum, rowNum, SAND, HEIGHT));colNum++;}
        default -> {}
      }
    }
  }
}
