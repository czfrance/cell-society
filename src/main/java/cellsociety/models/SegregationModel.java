package cellsociety.models;

import cellsociety.cells.BlockedPercolationCell;
import cellsociety.cells.PercolatingCell;
import cellsociety.cells.SchellingGroupCell;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SegregationModel extends SimulationModel{

  private int numUnhappy1;
  private int numUnhappy2;

  public SegregationModel(Map<String, String> dataValues) {
    super(dataValues);
  }

  @Override
  protected void createGrid() {
    myGrid.add(new ArrayList<>());
    int rowNum = 0;
    int colNum = 0;
    for (int i = 0; i < simInfo.get(DATA_FIELDS.get(6)).toCharArray().length; i++) {
      char c = simInfo.get(DATA_FIELDS.get(6)).toCharArray()[i];

      //0 = empty, 1 = group 1, 2 = group 2
      switch (c) {
        case '.' -> {myGrid.add(new ArrayList<>()); rowNum++; colNum = 0;}
        case '0' -> {myGrid.get(rowNum).add(new SchellingGroupCell(colNum, rowNum, 0, SATISFIED)); colNum++;}
        case '1' -> {myGrid.get(rowNum).add(new SchellingGroupCell(colNum, rowNum, 1, SATISFIED)); colNum++;}
        case '2' -> {myGrid.get(rowNum).add(new SchellingGroupCell(colNum, rowNum, 2, SATISFIED)); colNum++;}
        default -> {}
      }
    }
  }

//  @Override
//  public void updateGrid() {
//    if (!initStateIsSet) {
//      initFilledCells();
//      initStateIsSet = true;
//    }
//    else {
//      super.updateGrid();
//    }
//  }

//  private List<List<Integer>> tempVacatedStates getVacatedGrid() {
//    List<List<Integer>> vacatedCellStates = getCellNextStates();
//    for (List<>)
//  }
}
