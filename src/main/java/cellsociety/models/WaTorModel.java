package cellsociety.models;

import cellsociety.cells.FishCell;
import cellsociety.cells.SharkCell;
import cellsociety.cells.EmptyCell;

import java.util.ArrayList;
import java.util.Map;

public class WaTorModel extends SimulationModel {

  public WaTorModel(Map<String, String> dataValues) {
    super(dataValues);
    simType = "WaTor";
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
      case '1' -> {myGrid.get(rowNum).add(new FishCell(colNum, rowNum, 0, 0, 0));colNum++;}
      case '2' -> {myGrid.get(rowNum).add(new SharkCell(colNum, rowNum, 0, 2));colNum++;}
      case '3' -> {myGrid.get(rowNum).add(new EmptyCell(colNum, rowNum, 0));colNum++;}
      default -> {}
      }
    }
  }
}

