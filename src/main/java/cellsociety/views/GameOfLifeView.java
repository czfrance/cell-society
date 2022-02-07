package cellsociety.views;

import cellsociety.cells.Cell;
import cellsociety.models.SimulationModel;
import cellsociety.view_cells.LifeViewCell;
import java.util.ArrayList;
import java.util.List;

/**
 * Feel free to completely change this code or delete it entirely.
 *
 * @author Cynthia France, Diane Kim
 */
public class GameOfLifeView extends SimulationView {

  public GameOfLifeView(SimulationModel simModel) {
    super(simModel);
  }

  @Override
  protected String getName() {
    return model.getMyResources().getString("GoL");
  }

  @Override
  protected String getHeader() {
    return model.getMyResources().getString("GoLRules");
  }

  @Override
  protected String getHtml() {
    return "/GoLRules.html";
  }

  @Override
  protected void makeGrid() {
    List<List<Cell>> cellGrid = model.getGrid().getGrid();
    for (int row = 0; row < cellGrid.size(); row++) {
      grid.add(new ArrayList<>());
      for (int cell = 0; cell < cellGrid.get(row).size(); cell++) {
        int state = cellGrid.get(row).get(cell).getMyCurrentState();
        switch (state) {
          case 0, 1 -> {grid.get(row).add(new LifeViewCell(cell, row, cellSize, state));}
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
          case 0, 1 -> {grid.get(row).get(cell).updateState(state);}
          default -> {}
        }
      }
    }
  }
}
