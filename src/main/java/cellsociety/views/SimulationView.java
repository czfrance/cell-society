package cellsociety.views;

import cellsociety.view_cells.ViewCell;
import javafx.scene.Node;
import cellsociety.cells.*;
import cellsociety.models.SimulationModel;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.Scene;

public class SimulationView {

  private final Group root = new Group();
  protected SimulationModel model;
  protected List<List<ViewCell>> grid = new ArrayList<>();
  protected double cellSize;

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

  protected void makeGrid() {
//    List<List<Cell>> cellGrid = model.getGrid();
//    for (int row = 0; row < cellGrid.size(); row++) {
//      grid.add(new ArrayList<Cell>());
//      for (int cell = 0; cell < cellGrid.get(row).size(); cell++) {
//        switch (cellGrid.get(row).get(cell)) {
//          case 0 -> {grid.get(row).add(new LifeViewCell(cell, row, cellSize, Integer.toString(cellGrid.get(row).get(cell))));}
//          case 1 -> {grid.get(row).add(new LifeCell(cell, row, cellSize, Integer.toString(cellGrid.get(row).get(cell))));}
//          default -> {}
//        }
//      }
//    }
  }

  private void addGridToRoot() {
    for (List<ViewCell> row : grid) {
      for (ViewCell cell : row) {
        root.getChildren().add(cell);
      }
    }
  }

  private void initUI() {

  }
  private void update() {

  }

  private Node initButtons() {
    return null;
  }



}
