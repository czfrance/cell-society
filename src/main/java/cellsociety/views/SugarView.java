package cellsociety.views;

import cellsociety.cells.Cell;
import cellsociety.models.SimulationModel;
import cellsociety.view_cells.SugarViewCell;
import java.util.ArrayList;
import java.util.List;

public class SugarView extends SimulationView{

  private final int AGENT = 5;

  public SugarView(SimulationModel simModel ) {
    super(simModel);
  }

  @Override
  protected String getHeader() {
    return null;
  }

  @Override
  protected String getName() {
    return null;
  }

  @Override
  protected String getHtml() {
    return null;
  }

  @Override
  protected void makeGrid() {
    List<List<Cell>> cellGrid = model.getGrid().getGrid();
    for (int row = 0; row < cellGrid.size(); row++) {
      grid.add(new ArrayList<>());
      for (int cell = 0; cell < cellGrid.get(row).size(); cell++) {
        int state = cellGrid.get(row).get(cell).getMyCurrentState();
        switch (state) {
          case 0, 1, 2, 3, 4, 5 -> {grid.get(row).add(new SugarViewCell(cell, row, cellSize, state));}
          default -> {}
        }
      }
    }
  }

  @Override
  protected void updateGrid() {
    List<List<Cell>> cellGrid = model.getGrid().getGrid();
    for (int row = 0; row < cellGrid.size(); row++) {
      grid.add(new ArrayList<>());
      for (int cell = 0; cell < cellGrid.get(row).size(); cell++) {
        int state = cellGrid.get(row).get(cell).getMyCurrentState();
        switch (state) {
          case 0, 1, 2, 3, 4 -> {grid.get(row).get(cell).updateState(state);}
          default -> {}
        }
        Cell sugar = cellGrid.get(row).get(cell);
        if (sugar.hasAgent() && sugar.getAgent().isAlive()) grid.get(row).get(cell).updateState(AGENT);
      }
    }
  }
}
