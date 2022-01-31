package cellsociety.views;

import cellsociety.cells.Cell;
import cellsociety.models.SimulationModel;
import cellsociety.view_cells.LifeViewCell;
import cellsociety.view_cells.WaTorViewCell;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.text.Text;

public class WaTorView extends SimulationView{

  public WaTorView(SimulationModel simModel) {
    super(simModel);
  }

  @Override
  protected void addTitle() {
    Text t = new Text();
    t.setText("WaTor");
    root.getChildren().add(t);
  }

  @Override
  protected void makeGrid() {
    List<List<Cell>> cellGrid = model.getGrid();
    for (int row = 0; row < cellGrid.size(); row++) {
      grid.add(new ArrayList<>());
      for (int cell = 0; cell < cellGrid.get(row).size(); cell++) {
        Cell c1 = cellGrid.get(row).get(cell);
        int state = c1.getMyState();
        switch (state) {
          case 0, 1, 2 -> {grid.get(row).add(new WaTorViewCell(cell, row, cellSize, state));}
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
          case 0, 1, 2 -> {grid.get(row).get(cell).updateState(state);}
          default -> {}
        }
      }
    }
  }
}
