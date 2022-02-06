package cellsociety.views;

import cellsociety.models.SimulationModel;
import cellsociety.view_cells.ViewCell;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.util.ArrayList;

public abstract class SimulationView {

  public Scene scene;
  protected BorderPane root = new BorderPane();
  protected SimulationModel model;
  //TODO NEED TO CHANGE THIS TO A ABSTRACTION OF SOME SORT AND FIX ALL THE ISSUES THAT ARISE
  protected List<List<ViewCell>> grid = new ArrayList<>();
  protected double cellSize;

  private Button newConfigButton;
  private Button saveConfig;


  private HBox homeBox;
  private final double simulationSpeed; //generations per second
  private boolean play = true;

  public static final String DEFAULT_RESOURCE_PACKAGE = "/";
  public String stylesheet= "light.css";

  private Timeline animation;

  private double framesPerSecond;
  private double secondDelay;

  private Slider slider;

  public SimulationView(SimulationModel simModel) {
    model = simModel;
    simulationSpeed = model.getSpeed();
  }

  public double framesPerSec() {
    return simulationSpeed;
  }

  public Scene makeScene(int width, int height) {

    cellSize = Math.min((width / model.getGridSize()[0]), (height / model.getGridSize()[1]));

    //FlowPane topPane = new FlowPane();
    Node buttonPanel = makePanel();
    root.setBottom(buttonPanel);
    //root.setRight(topPane);

    root.setLeft(makePanel());
    root.setBottom(controlAnimation());
    addTitle();

    double topHeight = root.getTop().getBoundsInParent().getMaxY()-root.getTop().getBoundsInParent().getMinY();
    double botHeight = root.getBottom().getBoundsInParent().getMaxY()-root.getBottom().getBoundsInParent().getMinY();
    double gridHeight = height - topHeight - botHeight;
    double leftWidth = root.getLeft().getBoundsInParent().getMaxX()-root.getLeft().getBoundsInParent().getMinX();
    double rightWidth = 0; //root.getRight().getBoundsInParent().getMaxX()-root.getRight().getBoundsInParent().getMinX();
    double gridWidth = width - leftWidth - rightWidth;

    cellSize = Math.min((gridWidth / model.getGridSize()[0]), (gridHeight / model.getGridSize()[1]));
    makeGrid();
//    HBox sims = new HBox();
    Node tmp = addGridToNode();
//    tmp.setLayoutX(200);
//    Node tmp2 = addGridToNode();
//    tmp2.setLayoutX(400);
//    sims.getChildren().addAll(tmp, tmp2);

    root.setCenter(tmp);

    Timeline animation = new Timeline();
    playAnimation(animation);

    scene = new Scene(root, width + buttonPanel.getBoundsInParent().getWidth(), root.getBoundsInParent().getHeight() + 100);

    scene.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE + stylesheet);
    scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
    return scene;
  }

  public void step() {
    if (play) {
      model.updateGrid();
      updateGrid();
    }
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
    newConfigButton = makeButton("LoadNew", event -> doNewConfig());
    saveConfig = makeButton("Percolation", event -> doSaveConfig());

    result.getChildren().add(newConfigButton);

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

    Button infoButton = makeButton("Info", e -> dialog.showAndWait());

    homebox.getChildren().addAll(t, infoButton);
    // will move this to css file
    homebox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
            + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
            + "-fx-border-radius: 5;" + "-fx-border-color: gray;");
    homebox.setAlignment(Pos.CENTER);
    root.setTop(homebox);
  }

  protected abstract String getRules();
  protected abstract String getName();


  private Button makeButton(String property, EventHandler<ActionEvent> handler) {
    Button result = new Button();
    final String IMAGEFILE_SUFFIXES = String.format(".*\\.(%s)", String.join("|", ImageIO.getReaderFileSuffixes()));
    String label = model.getMyResources().getString(property);

    if (label.matches(IMAGEFILE_SUFFIXES)) {
      result.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(DEFAULT_RESOURCE_PACKAGE + label))));
    }
    else {
      result.setText(label);
    }


    result.setOnAction(handler);
    return result;
  }

  private Node controlAnimation() {
    HBox mediaBar = new HBox();
    mediaBar.setAlignment(Pos.CENTER);

    final Button togglePlayButton = makeButton(">||", e -> doTogglePlayButton());
    final Button stepButton = makeButton(">>|", e -> doStepButton());
    final Button toggleTheme = makeButton("ChangeTheme", e -> doChangeTheme());

    slider = new Slider(1, 5, 0.5);
    slider.setShowTickMarks(true);
    slider.setShowTickLabels(true);
    slider.setMajorTickUnit(0.25f);
    slider.setBlockIncrement(0.1f);

    mediaBar.getChildren().addAll(toggleTheme, togglePlayButton, stepButton, slider);
    mediaBar.setSpacing(10);
    return mediaBar;
  }

  public Button getNewConfigButton() {
    return newConfigButton;
  }

  public Button getSaveConfigButton() {
    return saveConfig;
  }

  protected abstract void makeGrid();

  protected abstract void updateGrid();


  private void doNewConfig() {

  }

  private void doSaveConfig() {

  }


  private void doChangeTheme() {
    if (stylesheet.equals("light.css")) {
      stylesheet = "dark.css";

    }
    else {
      stylesheet = "light.css";
    }
    scene.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE + stylesheet);
  }

  private void doTogglePlayButton() {
    play = !play;
  }

  private void doStepButton() {
    model.updateGrid();
    updateGrid();
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

  private void playAnimation(Timeline animation) {

    animation.setCycleCount(Timeline.INDEFINITE);
    framesPerSecond = framesPerSec();
    secondDelay = 1.0 / framesPerSecond;
    animation.getKeyFrames()
            .add(new KeyFrame(Duration.seconds(secondDelay), e -> step()));
    animation.play();



    animation.rateProperty().bind(slider.valueProperty());
  }
}
