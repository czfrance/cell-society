package cellsociety.views;

import cellsociety.models.SimulationModel;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PercolationView extends SimulationView {

  public PercolationView(SimulationModel simModel) {
    super(simModel);
  }

  @Override
  protected void addTitle() {
    HBox homebox = new HBox();
    Text t = new Text(400, 100, "Percolation");
    t.setFont(Font.font ("Verdana", 20));

    Dialog<String> dialog = new Dialog<String>();
    dialog.setTitle("Dialog");
    ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
    dialog.setContentText("Percolation Rules");
    dialog.getDialogPane().getButtonTypes().add(type);
    Text txt = new Text("Click the button to show the dialog");
    Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
    txt.setFont(font);
    Button button = new Button("Info");
    button.setOnAction(e -> {
      dialog.showAndWait();
    });


    homebox.getChildren().addAll(t, button);

    root.setTop(homebox);
  }
}
