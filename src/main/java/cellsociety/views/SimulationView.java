package cellsociety.views;

import cellsociety.models.SimulationModel;
import cellsociety.view_cells.ViewCell;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.imageio.ImageIO;

/**
 * This class serves as the abstract class for the simulation views
 *
 * @author Diane Kim, Cynthia France
 */
public abstract class SimulationView {

  public Scene scene;
  protected BorderPane root = new BorderPane();
  protected SimulationModel model;
  //TODO NEED TO CHANGE THIS TO A ABSTRACTION OF SOME SORT AND FIX ALL THE ISSUES THAT ARISE
  protected List<List<ViewCell>> grid = new ArrayList<>();
  protected double cellSize;

  private Button newConfigButton;
  private Button saveConfigButton;

  private HBox homeBox;
  private final double simulationSpeed; //generations per second
  private boolean play = true;

  public static final String DEFAULT_RESOURCE_PACKAGE = "/";
  public String stylesheet = "light.css";

  private Slider slider;
  private Dialog newTitle;
  private Dialog newAuthor;
  private Dialog newDescription;
  private Dialog newFilename;

  public SimulationView(SimulationModel simModel) {
    model = simModel;
    simulationSpeed = model.getSpeed();
  }

  public double framesPerSec() {
    return simulationSpeed;
  }

  public Scene makeScene(int width, int height) {

    //FlowPane topPane = new FlowPane();
    Node buttonPanel = makePanel();
    root.setBottom(buttonPanel);
    //root.setRight(topPane);

    root.setLeft(makePanel());
    root.setBottom(controlAnimation());
    addTitle();

    double topHeight =
        root.getTop().getBoundsInParent().getMaxY() - root.getTop().getBoundsInParent().getMinY();
    double botHeight =
        root.getBottom().getBoundsInParent().getMaxY() - root.getBottom().getBoundsInParent()
            .getMinY();
    double gridHeight = height - topHeight - botHeight;
    double leftWidth =
        root.getLeft().getBoundsInParent().getMaxX() - root.getLeft().getBoundsInParent().getMinX();
    double rightWidth = 0; //root.getRight().getBoundsInParent().getMaxX()-root.getRight().getBoundsInParent().getMinX();
    double gridWidth = width - leftWidth - rightWidth;

    cellSize = Math.min((gridWidth / model.getGridSize()[0]),
        (gridHeight / model.getGridSize()[1]));
    makeGrid();
    Node tmp = addGridToNode();

    root.setCenter(tmp);
    newFilename = createInputDialog(model.getMyResources().getString("FileName"));
    newTitle = createInputDialog(model.getMyResources().getString("SimTitle"));
    newAuthor = createInputDialog(model.getMyResources().getString("SimAuthor"));
    newDescription = createInputDialog(model.getMyResources().getString("SimDesc"));

    scene = new Scene(root, width + buttonPanel.getBoundsInParent().getWidth(),
        root.getBoundsInParent().getHeight() + 100);

    scene.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE + stylesheet);
    scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
    return scene;
  }

  public SimulationModel getModel() {
    return model;
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

  private Dialog createInputDialog(String headerText) {
    TextInputDialog desc = new TextInputDialog();
    desc.setHeaderText(headerText);
    return desc;
  }

  public Map<String, Optional> getSaveInfo() {
    Map<String, Optional> saveInfo = new HashMap<>();
    saveInfo.put("filename", newFilename.showAndWait());
    saveInfo.put("title", newTitle.showAndWait());
    saveInfo.put("author", newAuthor.showAndWait());
    saveInfo.put("description", newDescription.showAndWait());
    return saveInfo;
  }

  private Node makePanel() {
    VBox result = new VBox();
    newConfigButton = new Button(model.getMyResources().getString("LoadNew"));
    saveConfigButton = new Button(model.getMyResources().getString("SaveConfig"));

    result.getChildren().addAll(newConfigButton, saveConfigButton);
    result.setSpacing(10);

    return result;
  }

  protected void addTitle() {
    HBox homebox = new HBox(10);
    Text t = new Text(getName());
//    t.setFont(Font.font("Courier New", 25));

    Button infoButton = makeButton("Info", e -> getRules().showAndWait());

    homebox.getChildren().addAll(t, infoButton);
    homebox.getStyleClass().add("hbox");
    homebox.setAlignment(Pos.CENTER);
    root.setTop(homebox);
  }

  protected Alert getRules() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(getName());
    alert.setHeaderText(getHeader());
    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();
    webEngine.load( getClass().getResource(getHtml()).toString());
    webView.setPrefSize(500, 400);
    alert.getDialogPane().setContent(webView);
    return alert;
  }


  protected abstract String getHeader();

  protected abstract String getName();

  protected abstract String getHtml();


  private Button makeButton(String property, EventHandler<ActionEvent> handler) {
    Button result = new Button();
    final String IMAGEFILE_SUFFIXES = String.format(".*\\.(%s)",
        String.join("|", ImageIO.getReaderFileSuffixes()));
    String label = model.getMyResources().getString(property);

    if (label.matches(IMAGEFILE_SUFFIXES)) {
      result.setGraphic(new ImageView(
          new Image(getClass().getResourceAsStream(DEFAULT_RESOURCE_PACKAGE + label))));
    } else {
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

    mediaBar.getChildren().addAll(toggleTheme, togglePlayButton, stepButton, slider);
    mediaBar.setSpacing(10);
    return mediaBar;
  }

  public Button getNewConfigButton() {
    return newConfigButton;
  }

  public Button getSaveConfigButton() {
    return saveConfigButton;
  }

  protected abstract void makeGrid();

  protected abstract void updateGrid();

  private void doChangeTheme() {
    if (stylesheet.equals("light.css")) {
      stylesheet = "dark.css";

    } else {
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

  public Slider getSlider() {
    return slider;
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
