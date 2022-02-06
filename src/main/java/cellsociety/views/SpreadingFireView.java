package cellsociety.views;

import cellsociety.models.Grid;
import cellsociety.models.SimulationModel;
import cellsociety.view_cells.BurningViewCell;
import java.util.ArrayList;

public class SpreadingFireView extends SimulationView{

  public static final int TREE = 0;
  public static final int BURNING = 1;
  public static final int DEAD = 2;

  public SpreadingFireView(SimulationModel simModel) {
    super(simModel);
  }


  @Override
  protected String getRules() {
    return "Rules: ";
  }

  @Override
  protected String getName() {

    return model.getMyResources().getString("SoF");
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

  @Override
  protected void updateGrid() {
    Grid cellGrid = model.getGrid();
    for (int row = 0; row < cellGrid.size(); row++) {
      for (int cell = 0; cell < cellGrid.getRow(row).size(); cell++) {
        int state = cellGrid.getRow(row).get(cell).getMyCurrentState();
        switch (state) {
          case TREE, BURNING, DEAD -> {grid.get(row).get(cell).updateState(state);}
          default -> {}
        }
      }
    }
  }
}
