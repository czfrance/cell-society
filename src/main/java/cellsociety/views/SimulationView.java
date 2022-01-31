package cellsociety.views;


import cellsociety.models.SimulationModel;
import cellsociety.view_cells.ViewCell;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


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


  public static final String DEFAULT_RESOURCE_PACKAGE = "/";
  public static final String STYLESHEET = "default.css";

  public SimulationView(SimulationModel simModel) {
    model = simModel;
  }

  public Scene makeScene(int width, int height) {
    root.setLeft(makePanel());
    root.setBottom(controlAnimation());
    addTitle();

    double topHeight = root.getTop().getBoundsInParent().getMaxY()-root.getTop().getBoundsInParent().getMinY();
    double botHeight = root.getBottom().getBoundsInParent().getMaxY()-root.getBottom().getBoundsInParent().getMinY();
    double gridHeight = height - topHeight - botHeight;
    double leftWidth = root.getLeft().getBoundsInParent().getMaxX()-root.getLeft().getBoundsInParent().getMinX();
    double rightWidth = 0; //root.getRight().getBoundsInParent().getMaxX()-root.getRight().getBoundsInParent().getMinX();
    double gridWidth = width - leftWidth - rightWidth;

    System.out.println(width + " " + gridWidth + " " + height + " " + gridHeight);

    cellSize = Math.min((gridWidth / model.getGridSize()[0]), (gridHeight / model.getGridSize()[1]));
    makeGrid();
    Node tmp = addGridToNode();
    tmp.setLayoutX(200);
    root.setCenter(tmp);


    Scene scene = new Scene(root, width, height);


//    String FILE_NAME = "src/main/resources/LevelOneConfig.txt";
//    List lst = ReadFileIntoList.readFileInList(FILE_NAME);
    scene.getStylesheets().add("/default.css");
//    scene.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE + STYLESHEET);

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
    VBox result = new VBox();
    GameOfLife = makeButton("Game of Life", event -> GoL());
    Percolation = makeButton("Percolation", event -> Percolation());
    Segregation = makeButton("Segregation", event -> Segregation());
    SpreadingFire = makeButton("Spreading of Fire", event -> SoF());
    WaTor = makeButton("WaTor", event -> Wator());

    result.getChildren().add(GameOfLife);
    result.getChildren().add(Percolation);
    result.getChildren().add(Segregation);
    result.getChildren().add(SpreadingFire);
    result.getChildren().add(WaTor);

    return result;
  }

  protected void addTitle() {
    HBox homebox = new HBox(10);
    Text t = new Text(getName());
    t.setFont(Font.font ("Courier New", 25));

    Dialog<String> dialog = new Dialog<String>();
    dialog.setTitle(getName()+" Rules");
    ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
    dialog.setContentText(getRules());
    dialog.getDialogPane().getButtonTypes().add(type);
    Text txt = new Text("Click the button to show the dialog");
    Font font = Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 12);
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

  protected abstract String getRules();
  protected abstract String getName();


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

  private void Wator() {
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
