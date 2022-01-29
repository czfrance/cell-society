package cellsociety.views;

import cellsociety.cells.Cell;
import cellsociety.cells.LifeCell;
import cellsociety.models.*;
import cellsociety.view_cells.*;
import java.util.ArrayList;
import java.util.List;

public class GameOfLifeView extends SimulationView {

  public GameOfLifeView(SimulationModel simModel) {
    super(simModel);
  }

  protected void makeGrid() {
    List<List<Cell>> cellGrid = model.getGrid();
    for (int row = 0; row < cellGrid.size(); row++) {
      grid.add(new ArrayList<>());
      for (int cell = 0; cell < cellGrid.get(row).size(); cell++) {
        int c = cellGrid.get(row).get(cell).getMyState();
        switch (c) {
          case 0, 1 -> {grid.get(row).add(new LifeViewCell(cell, row, cellSize, c));}
          default -> {}
        }
      }
    }
  }
}
