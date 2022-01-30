package cellsociety.views;

import cellsociety.view_cells.ViewCell;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import cellsociety.models.SimulationModel;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;


public abstract class SimulationView {

  protected BorderPane root = new BorderPane();
  protected SimulationModel model;
  protected List<List<ViewCell>> grid = new ArrayList<>();
  protected double cellSize;

  private Button GameOfLife;
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
    root.setRight(makePanel());
    root.setBottom(controlAnimation());

    makeGrid();

    Node tmp = addGridToNode();
    tmp.setLayoutX(200);
    root.setCenter(tmp);
    addTitle();

    Scene scene = new Scene(root, width, height);
    scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
    return scene;
  }
  private Node addGridToNode() {
    VBox temp = new VBox();
    temp.setAlignment(Pos.CENTER);
    for (List<ViewCell> row : grid) {
      HBox temp2 = new HBox();
      temp2.setAlignment(Pos.CENTER);
      for (ViewCell cell : row) {
        temp2.getChildren().add(cell);
      }
      temp.getChildren().add(temp2);
    }
    return temp;
  }

  private Node makePanel() {
    VBox result = new VBox(6);
    GameOfLife = makeButton("Game of Life", event -> GoL());
    Percolation = makeButton("Percolation", event -> Percolation());
    Segregation = makeButton("Segregation", event -> Segregation());
    SpreadingFire = makeButton("Spreading of Fire", event -> SoF());
    WaTor = makeButton("WaTor", event -> wator());

    result.getChildren().add(GameOfLife);
    result.getChildren().add(Percolation);
    result.getChildren().add(Segregation);
    result.getChildren().add(SpreadingFire);
    result.getChildren().add(WaTor);

    return result;
  }

  protected void addTitle() {
  }


  Button makeButton(String label, EventHandler<ActionEvent> handler) {
    Button result = new Button();
    result.setText(label);
    result.setOnAction(handler);
    return result;
  }
  private Node controlAnimation() {
    HBox mediaBar = new HBox();
    mediaBar.setAlignment(Pos.CENTER);

    final Button playButton  = new Button(">");
    final Button pauseButton  = new Button("||");
    mediaBar.getChildren().addAll(playButton, pauseButton);
    return mediaBar;
  }

  protected abstract void makeGrid();

  protected abstract void updateGrid();




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
