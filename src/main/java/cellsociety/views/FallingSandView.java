package cellsociety.views;

import cellsociety.cells.Cell;
import cellsociety.models.SimulationModel;
import cellsociety.view_cells.SandViewCell;
import java.util.ArrayList;
import java.util.List;

public class FallingSandView extends SimulationView {

  public FallingSandView(SimulationModel simModel) {
    super(simModel);

  }

  @Override
  protected String getRules() {
    return "Rules: ";
  }

  @Override
  protected String getName() {
    return "Falling Sand";
  }

  @Override
  protected void makeGrid() {
    List<List<Cell>> cellGrid = model.getGrid().getGrid();
    for (int row = 0; row < cellGrid.size(); row++) {
      grid.add(new ArrayList<>());
      for (int cell = 0; cell < cellGrid.get(row).size(); cell++) {
        int state = cellGrid.get(row).get(cell).getMyCurrentState();
        switch (state) {
          case 0, 1, 2 -> {grid.get(row).add(new SandViewCell(cell, row, cellSize, state));}
          default -> {}
        }
      }
    }
  }

  @Override
  protected void updateGrid() {
    List<List<Cell>> cellGrid = model.getGrid().getGrid();
    for (int row = 0; row < cellGrid.size(); row++) {
      for (int cell = 0; cell < cellGrid.get(row).size(); cell++) {
        int state = cellGrid.get(row).get(cell).getMyCurrentState();
        switch (state) {
          case 0, 1, 2 -> {grid.get(row).get(cell).updateState(state);}
          default -> {}
        }
      }
    }
  }
}