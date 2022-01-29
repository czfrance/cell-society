package cellsociety.views;

import cellsociety.view_cells.ViewCell;
import javafx.scene.Node;
import cellsociety.cells.*;
import cellsociety.models.SimulationModel;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

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
    scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
    return scene;
  }

  protected void makeGrid() {
  }

  protected void updateGrid() {}

  private void addGridToRoot() {
    for (List<ViewCell> row : grid) {
      for (ViewCell cell : row) {
        root.getChildren().add(cell);
      }
    }
  }

  private void initUI() {

  }
//  private void update() {
//
//  }

  private Node initButtons() {
    return null;
  }

  private void handleKeyInput(KeyCode code) {
    switch (code) {
      case ENTER -> {
        model.updateGrid();
        updateGrid();
      }
      default -> {
      }
    }
  }

}
