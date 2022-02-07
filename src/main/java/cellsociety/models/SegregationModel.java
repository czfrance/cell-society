package cellsociety.models;

import cellsociety.cells.*;
import cellsociety.cells.SchellingGroupCell;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.Random;

public class SegregationModel extends SimulationModel{

  private int numUnhappy1;
  private int numUnhappy2;

  public SegregationModel(Map<String, String> dataValues, String language) {
    super(dataValues, language);
  }

  @Override
  protected void createGrid() {
    myGrid.addRow();
    int rowNum = 0;
    int colNum = 0;
    for (int i = 0; i < simInfo.get(DATA_FIELDS.get(6)).toCharArray().length; i++) {
      char c = simInfo.get(DATA_FIELDS.get(6)).toCharArray()[i];

      //0 = empty, 1 = group 1, 2 = group 2
      switch (c) {
        case '.' -> {myGrid.addRow(); rowNum++; colNum = 0;}
        case '0' -> {myGrid.getRow(rowNum).add(new SchellingGroupCell(colNum, rowNum, 0, SATISFIED)); colNum++;}
        case '1' -> {myGrid.getRow(rowNum).add(new SchellingGroupCell(colNum, rowNum, 1, SATISFIED)); colNum++;}
        case '2' -> {myGrid.getRow(rowNum).add(new SchellingGroupCell(colNum, rowNum, 2, SATISFIED)); colNum++;}
        default -> {}
      }
    }
  }

  @Override
  public void updateGrid() {
    Random rand = new Random();
    List<Cell> emptyCells = getEmptyCells();
    for (int i = 0; i < numUnhappy1; i++) {
      int index = rand.nextInt(emptyCells.size());
      Cell c = emptyCells.get(index);
      c.setState(1);
      emptyCells.remove(index);
    }
    for (int i = 0; i < numUnhappy2; i++) {
      int index = rand.nextInt(emptyCells.size());
      Cell c = emptyCells.get(index);
      c.setState(2);
      emptyCells.remove(index);
    }
    numUnhappy1 = 0;
    numUnhappy2 = 0;
  }

  private List<Cell> getEmptyCells() {
    List<Cell> emptyCells = new ArrayList<>();
    List<List<Integer>> vacatedCellStates = getCellNextStates();
    for (int row = 0; row < vacatedCellStates.size(); row++) {
      for (int col = 0; col < vacatedCellStates.get(row).size(); col++) {
        int state = vacatedCellStates.get(row).get(col);
        if (state == -1) {
          numUnhappy1++;
          myGrid.getRow(row).get(col).setState(0);
          emptyCells.add(myGrid.getRow(row).get(col));
        }
        else if (state == -2) {
          numUnhappy2++;
          myGrid.getRow(row).get(col).setState(0);
          emptyCells.add(myGrid.getRow(row).get(col));
        }
        else if (state == 0) {
          emptyCells.add(myGrid.getRow(row).get(col));
        }
        else {
          continue;
        }
      }
    }
    return emptyCells;
  }
}
