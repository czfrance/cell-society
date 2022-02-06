package cellsociety.views;

import cellsociety.cells.Cell;
import cellsociety.models.SimulationModel;
import cellsociety.view_cells.LifeViewCell;
import java.util.ArrayList;
import java.util.List;

public class GameOfLifeView extends SimulationView {

  public GameOfLifeView(SimulationModel simModel) {
    super(simModel);
  }

  @Override
  protected String getRules() {
    return "Rules: " +
            "1. A location that has zero or one neighbors will be empty in the next generation. If a cell was in that location, it dies of loneliness.\n" +
            "2. A location with two neighbors is stable—that is, if it contained a cell, it still contains a cell. If it was empty, it's still empty.\n" +
            "3. A location with three neighbors will contain a cell in the next generation. If it was unoccupied before, a new cell is born. If it currently contains a cell, the cell remains. Good times.\n" +
            "4. A location with four or more neighbors will be empty in the next generation. If there was a cell in that location, it dies of overcrowding.\n" +
            "5. The births and deaths that transform one generation to the next must all take effect simultaneously. Thus, when computing a new generation, new births and deaths in that generation don’t impact other births and deaths in that generation. To keep the two generations separate, you will need to work on two versions of the  grid—one for the current generation, and a second that allows you to compute and store the next generation without changing the current one.";
  }

  @Override
  protected String getName() {
    return "Game of Life";
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
