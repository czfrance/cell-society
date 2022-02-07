package cellsociety.views;

import cellsociety.cells.Cell;
import cellsociety.cells.PercolatingCell;
import cellsociety.models.Grid;
import cellsociety.models.SimulationModel;
import cellsociety.view_cells.BlockedPercolationViewCell;
import cellsociety.view_cells.PercolatingViewCell;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the view for the Percolation simulation. The makeGrid() and updateGrid() methods
 * initialize and update the configuration of the cells specific to this simulation.
 *
 * @author Cynthia France, Diane Kim
 */
public class PercolationView extends SimulationView {

  public PercolationView(SimulationModel simModel) {
    super(simModel);
  }

  @Override
  protected String getName() {
    return model.getMyResources().getString("Percolation");
  }

  @Override
  protected String getHeader() {
    return model.getMyResources().getString("PercRules");
  }

  @Override
  protected String getHtml() {
    return "/PercolationRules.html";
  }

  @Override
  protected void makeGrid() {
    List<List<Cell>> cellGrid = model.getGrid().getGrid();
    for (int row = 0; row < cellGrid.size(); row++) {
      grid.add(new ArrayList<>());
      for (int cell = 0; cell < cellGrid.get(row).size(); cell++) {
        int state = cellGrid.get(row).get(cell).getMyCurrentState();
        switch (state) {
          case 0 -> {
            if (cellGrid.get(row).get(cell) instanceof PercolatingCell) {
              grid.get(row).add(new PercolatingViewCell(cell, row, cellSize, state));
            }
            else grid.get(row).add(new BlockedPercolationViewCell(cell, row, cellSize, state));
          }
          case 1 -> {grid.get(row).add(new PercolatingViewCell(cell, row, cellSize, state));}
          default -> {}
        }
      }
    }
  }

  @Override
  protected void updateGrid() {
    Grid cellGrid = model.getGrid();
    for (int row = 0; row < cellGrid.size(); row++) {
      for (int cell = 0; cell < cellGrid.getRow(row).size(); cell++) {
        int state = cellGrid.getRow(row).get(cell).getMyCurrentState();
        switch (state) {
          case 0, 1 -> {grid.get(row).get(cell).updateState(state);}
          default -> {}
        }
      }
    }
  }
}
