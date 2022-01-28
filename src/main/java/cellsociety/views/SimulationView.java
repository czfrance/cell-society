package cellsociety.views;

import cellsociety.cells.*;
import cellsociety.models.SimulationModel;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.Scene;

public class SimulationView {

  private final Group root = new Group();
  private SimulationModel model;
  private List<List<Cell>> grid = new ArrayList<>();
  private double cellSize;

  public SimulationView(SimulationModel simModel) {
    model = simModel;
  }

  public Scene makeScene(int width, int height) {
    cellSize = Math.min((width / model.getGridSize()[0]), (height / model.getGridSize()[1]));
    makeGrid();
    addGridToRoot();

    Scene scene = new Scene(root, width, height);
    return scene;
  }

  private void makeGrid() {
    List<List<Integer>> cellGrid = model.getGrid();
    for (int row = 0; row < cellGrid.size(); row++) {
      grid.add(new ArrayList<Cell>());
      for (int cell = 0; cell < cellGrid.get(row).size(); cell++) {
        switch (cellGrid.get(row).get(cell)) {
          case 0 -> {grid.get(row).add(new EmptyCell(cell*cellSize, row*cellSize, cellSize));}
          case 1 -> {grid.get(row).add(new LifeCell(cell*cellSize, row*cellSize, cellSize));}
          case 2 -> {grid.get(row).add(new Cell(cell*cellSize, row*cellSize, cellSize));}
          default -> {}
        }
      }
    }
  }

  private void addGridToRoot() {
    for (List<Cell> row : grid) {
      for (Cell cell : row) {
        root.getChildren().add(cell);
      }
    }
  }
}
