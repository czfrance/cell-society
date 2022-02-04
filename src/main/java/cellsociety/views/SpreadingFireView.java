package cellsociety.views;

import cellsociety.cells.Cell;
import cellsociety.models.SimulationModel;
import cellsociety.view_cells.BurningViewCell;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.text.Text;

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
    return "Spreading of Fire";
  }

  @Override
  protected void makeGrid() {
    List<List<Cell>> cellGrid = model.getGrid();
    for (int row = 0; row < cellGrid.size(); row++) {
      grid.add(new ArrayList<>());
      for (int cell = 0; cell < cellGrid.get(row).size(); cell++) {
        int state = cellGrid.get(row).get(cell).getMyState();
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
    List<List<Cell>> cellGrid = model.getGrid();
    for (int row = 0; row < cellGrid.size(); row++) {
      for (int cell = 0; cell < cellGrid.get(row).size(); cell++) {
        int state = cellGrid.get(row).get(cell).getMyState();
        switch (state) {
          case TREE, BURNING, DEAD -> {grid.get(row).get(cell).updateState(state);}
          default -> {}
        }
      }
    }
  }
}
