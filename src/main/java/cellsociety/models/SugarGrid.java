package cellsociety.models;

import java.util.List;
import cellsociety.cells.Cell;

public class SugarGrid extends Grid{

  private List<Cell> myAgents;

  public SugarGrid(int width, int height) {
    super(width, height);

  }

  @Override
  public void updateGrid(List<List<Integer>> newGrid) {
    initNeighbors(SimulationModel.NEIGHBORTYPE);
    for (List<Cell> list : myGrid) {
      for (Cell cell : list) {
        cell.update();
      }
    }
    for (Cell cell : myAgents) {
      myGrid.get(cell.getRow()).get(cell.getColumn()).setAgent(cell);
    }
  }

  public void setMyAgents(List<Cell> list) {
    myAgents = list;
  }
}
