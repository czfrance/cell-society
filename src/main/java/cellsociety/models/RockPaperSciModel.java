package cellsociety.models;

import cellsociety.cells.RPSCell;
import java.util.Map;

public class RockPaperSciModel extends SimulationModel {

  private final int ROCK = 0;
  private final int PAPER = 1;
  private final int SCISSORS = 2;

  public RockPaperSciModel(Map<String, String> dataValues) {
    super(dataValues);
    simType = "RockPaperSci";
  }


  @Override
  protected void createGrid() {
    int rowNum = 0;
    int colNum = 0;
    for (int i = 0; i < simInfo.get(DATA_FIELDS.get(6)).toCharArray().length; i++) {
      char c = simInfo.get(DATA_FIELDS.get(6)).toCharArray()[i];

      //0 = rock, paper, scisscors
      switch (c) {
        case '.' -> {myGrid.addRow();rowNum++;colNum = 0;}
        case '1' -> {myGrid.getRow(rowNum).add(new RPSCell(colNum, rowNum, ROCK, 3));colNum++;}
        case '2' -> {myGrid.getRow(rowNum).add(new RPSCell(colNum, rowNum, PAPER, 3));colNum++;}
        case '3' -> {myGrid.getRow(rowNum).add(new RPSCell(colNum, rowNum, SCISSORS, 3));colNum++;}
        default -> {
        }
      }
    }
    System.out.println("Done");
  }
}
