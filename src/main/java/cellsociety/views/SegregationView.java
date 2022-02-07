package cellsociety.views;

import cellsociety.cells.Cell;
import cellsociety.models.Grid;
import cellsociety.models.SimulationModel;
import cellsociety.view_cells.SchellingGroupViewCell;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the view for the Segregation simulation. The makeGrid() and updateGrid() methods
 * initialize and update the configuration of the cells specific to this simulation.
 *
 * @author Cynthia France, Diane Kim
 */
public class SegregationView extends SimulationView{

  public SegregationView(SimulationModel simModel) {
    super(simModel);
  }

  @Override
  protected String getName() {
    return model.getMyResources().getString("Segregation");
  }

  @Override
  protected String getHeader() {
    return model.getMyResources().getString("SegRules");
  }

  @Override
  protected String getHtml() {
    return "/SegregationRules.html";
  }

  @Override
  protected void makeGrid() {
    List<List<Cell>> cellGrid = model.getGrid().getGrid();
    for (int row = 0; row < cellGrid.size(); row++) {
      grid.add(new ArrayList<>());
      for (int cell = 0; cell < cellGrid.get(row).size(); cell++) {
        int state = cellGrid.get(row).get(cell).getMyCurrentState();
        switch (state) {
          case 0, 1, 2 -> {
            grid.get(row).add(new SchellingGroupViewCell(cell, row, cellSize, state));
          }
          default -> {}
        }
      }
    }
  }
}
