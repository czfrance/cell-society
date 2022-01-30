package cellsociety.views;

import cellsociety.cells.Cell;
import cellsociety.cells.LifeCell;
import cellsociety.models.*;
import cellsociety.view_cells.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;

import java.util.ArrayList;
import java.util.List;

public class GameOfLifeView extends SimulationView {

  public GameOfLifeView(SimulationModel simModel) {
    super(simModel);
  }

  @Override
  protected void addTitle() {
    HBox homebox = new HBox(10);
    Text t = new Text("Game of Life");
    t.setFont(Font.font ("Verdana", 20));

    Dialog<String> dialog = new Dialog<String>();
    dialog.setTitle("Game of Life Rules");
    ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
    dialog.setContentText("Game of Life Rules");
    dialog.getDialogPane().getButtonTypes().add(type);
    Text txt = new Text("Click the button to show the dialog");
    Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
    txt.setFont(font);
    Button button = new Button("Info");
    button.setOnAction(e -> {
      dialog.showAndWait();
    });


    homebox.getChildren().addAll(t, button);
    // will move this to css file
    homebox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
            + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
            + "-fx-border-radius: 5;" + "-fx-border-color: gray;");
    homebox.setAlignment(Pos.CENTER);
    root.setTop(homebox);
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
