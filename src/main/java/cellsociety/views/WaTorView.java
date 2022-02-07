package cellsociety.views;

import cellsociety.cells.Cell;
import cellsociety.cells.WaTorCell;
import cellsociety.models.SimulationModel;

import cellsociety.view_cells.WaTorViewCell;

import java.util.ArrayList;
import java.util.List;

/**
 * Feel free to completely change this code or delete it entirely.
 *
 * @author Cynthia France, Diane Kim
 */
public class WaTorView extends SimulationView{

  public WaTorView(SimulationModel simModel) {
    super(simModel);
  }

  @Override
  protected String getName() {
    return model.getMyResources().getString("WaTor");
  }

  @Override
  protected String getHeader() {
    return model.getMyResources().getString("WaTorRules");
  }

  @Override
  protected String getHtml() {
    return "/WaTorRules.html";
  }

  @Override
  protected void makeGrid() {
    List<List<Cell>> cellGrid = model.getGrid().getGrid();
    for (int row = 0; row < cellGrid.size(); row++) {
      grid.add(new ArrayList<>());
      for (int cell = 0; cell < cellGrid.get(row).size(); cell++) {
        Cell c1 = cellGrid.get(row).get(cell);
        int state = c1.getMyCurrentState();
        switch (state) {
          case 0, 1, 2 -> {grid.get(row).add(new WaTorViewCell(cell, row, cellSize, state, (WaTorCell) c1));}
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
        int state = ((WaTorCell) cellGrid.get(row).get(cell)).getCurrentState();
        switch (state) {
          case 0, 1, 2 -> {grid.get(row).get(cell).updateState(state);}
          default -> {}
        }
      }
    }
  }
}
