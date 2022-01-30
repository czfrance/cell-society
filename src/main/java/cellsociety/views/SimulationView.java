package cellsociety.views;


import cellsociety.view_cells.ViewCell;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import cellsociety.models.SimulationModel;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import javafx.scene.layout.FlowPane;

import javafx.scene.layout.*;

public class SimulationView {

  protected BorderPane root = new BorderPane();
  protected SimulationModel model;
  protected List<List<ViewCell>> grid = new ArrayList<>();
  protected double cellSize;

  private Button GameofLife;
  private Button Percolation;
  private Button Segregation;
  private Button SpreadingFire;
  private Button WaTor;

  private HBox homeBox;

  public SimulationView(SimulationModel simModel) {
    model = simModel;
  }

  public Scene makeScene(int width, int height) {
    cellSize = Math.min((width / model.getGridSize()[0]), (height / model.getGridSize()[1]));
    FlowPane topPane = new FlowPane();
    Node buttonPanel = makePanel();
    root.setBottom(buttonPanel);
    root.setRight(topPane);

    makeGrid();

    Node tmp = addGridToNode();
    root.setCenter(tmp);
    addTitle();

    Scene scene = new Scene(root, width, root.getBoundsInParent().getHeight() + 100);
    scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
    return scene;
  }

  private Node makePanel() {
    HBox result = new HBox();
    GameofLife = makeButton("Game of Life", event -> GoL());
    Percolation = makeButton("Percolation", event -> Percolation());
    Segregation = makeButton("Segregation", event -> Segregation());
    SpreadingFire = makeButton("Spreading of Fire", event -> SoF());
    WaTor = makeButton("WaTor", event -> wator());

    result.getChildren().add(GameofLife);
    result.getChildren().add(Percolation);
    result.getChildren().add(Segregation);
    result.getChildren().add(SpreadingFire);
    result.getChildren().add(WaTor);

    return result;
  }

  protected void addTitle() {
  }
  
  private Button makeButton(String label, EventHandler<ActionEvent> handler) {
    Button result = new Button();
    result.setText(label);
    result.setOnAction(handler);
    return result;
  }

  protected void makeGrid() {
  }

  protected void updateGrid() {}

  private Node addGridToNode() {
    FlowPane temp = new FlowPane();
    for (List<ViewCell> row : grid) {
      HBox temp2 = new HBox();
      for (ViewCell cell : row) {
        temp2.getChildren().add(cell);
      }
      temp.getChildren().add(temp2);
    }
    return temp;
  }


  private void Segregation() {
  }

  private void Percolation() {
  }

  private void SoF() {
  }

  private void wator() {
  }

  void GoL() {
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
