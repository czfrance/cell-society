package cellsociety.views;

import cellsociety.cells.Cell;
import cellsociety.cells.LifeCell;
import cellsociety.models.*;
import cellsociety.view_cells.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class GameOfLifeView extends SimulationView {

  public GameOfLifeView(SimulationModel simModel) {
    super(simModel);
  }

  @Override
  protected void addTitle() {
    Text t = new Text(400, 100, "Game of Life");
    t.setFont(Font.font ("Verdana", 20));
    t.setText("Game of Life");
    root.setTop(t);
  }

  @Override
  protected void makeGrid() {
    List<List<Cell>> cellGrid = model.getGrid();
    for (int row = 0; row < cellGrid.size(); row++) {
      grid.add(new ArrayList<>());
      for (int cell = 0; cell < cellGrid.get(row).size(); cell++) {
        int state = cellGrid.get(row).get(cell).getMyState();
        switch (state) {
          case 0, 1 -> {grid.get(row).add(new LifeViewCell(cell, row, cellSize, state));}
          default -> {}
        }
      }
    }
  }

  @Override
  protected void updateGrid() {
    List<List<Cell>> cellGrid = model.getGrid();
    for (int row = 0; row < cellGrid.size(); row++) {
      grid.add(new ArrayList<>());
      for (int cell = 0; cell < cellGrid.get(row).size(); cell++) {
        int state = cellGrid.get(row).get(cell).getMyState();
        switch (state) {
          case 0, 1 -> {grid.get(row).get(cell).updateState(state);}
          default -> {}
        }
      }
    }
  }
}
