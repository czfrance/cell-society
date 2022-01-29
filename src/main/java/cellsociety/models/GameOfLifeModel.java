package cellsociety.models;

import java.util.*;
import cellsociety.cells.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameOfLifeModel extends SimulationModel {


  public GameOfLifeModel(Map<String, String> dataValues) {
    super(dataValues);
    simType = "GameOfLife";
    initGrid();
  }

  public void step() {
    updateGrid();
  }


  public void updateGrid() {
    for (int row = 0; row < myGrid.size(); row++) {
      for (int cell = 0; cell < myGrid.get(row).size(); cell++) {
        myGrid.get(row).get(cell).nextState();
      }
    }
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
        case '0' -> {myGrid.get(rowNum).add(new LifeCell(colNum, rowNum, 0)); colNum++;}
        case '1' -> {myGrid.get(rowNum).add(new LifeCell(colNum, rowNum, 1)); colNum++;}
        //case '2', '3', '4', '5', '6', '7', '8', '9' -> myGrid.get(rowNum).add(Character.getNumericValue(c));
        default -> {}
      }
    }
  }

  private void initGrid() {
    for (List<Cell> l : myGrid) {
      for (Cell c : l) {
        c.initNeighbors(WIDTH, HEIGHT, myGrid);
      }
    }
  }
}
