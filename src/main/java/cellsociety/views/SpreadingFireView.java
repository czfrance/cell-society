package cellsociety.views;

import cellsociety.models.Grid;
import cellsociety.models.SimulationModel;
import cellsociety.view_cells.BurningViewCell;

import java.util.ArrayList;

/**
 * This class implements the view for the Spreading of Fire simulation. The makeGrid() and updateGrid() methods
 * initialize and update the configuration of the cells specific to this simulation.
 *
 * @author Cynthia France, Diane Kim
 */
public class SpreadingFireView extends SimulationView{

  public static final int TREE = 0;
  public static final int BURNING = 1;
  public static final int DEAD = 2;

  /**
   *
   * @param simModel the simulation model
   */
  public SpreadingFireView(SimulationModel simModel) {
    super(simModel);
  }

  @Override
  protected String getName() {

    return model.getMyResources().getString("SoF");
  }

  @Override
  protected String getHeader() {
    return model.getMyResources().getString("SoFRules");
  }

  @Override
  protected String getHtml() {
    return "/SofRules.html";
  }

  @Override
  protected void makeGrid() {
    Grid cellGrid = model.getGrid();
    for (int row = 0; row < cellGrid.size(); row++) {
      grid.add(new ArrayList<>());
      for (int cell = 0; cell < cellGrid.getRow(row).size(); cell++) {
        int state = cellGrid.getRow(row).get(cell).getMyCurrentState();
        switch (state) {
          case TREE, BURNING, DEAD -> {
            grid.get(row).add(new BurningViewCell(cell, row, cellSize, state));
          }
          default -> {}
        }
      }
    }
  }
}
