package cellsociety;

import cellsociety.models.FallingSandModel;
import cellsociety.models.GameOfLifeModel;
import cellsociety.models.PercolationModel;
import cellsociety.models.RockPaperSciModel;
import cellsociety.models.SegregationModel;
import cellsociety.models.SimulationModel;
import cellsociety.models.SpreadingFireModel;
import cellsociety.models.WaTorModel;
import cellsociety.views.FallingSandView;
import cellsociety.views.GameOfLifeView;
import cellsociety.views.PercolationView;
import cellsociety.views.RPSView;
import cellsociety.views.SegregationView;
import cellsociety.views.SimulationView;
import cellsociety.views.SpreadingFireView;
import cellsociety.views.WaTorView;
import cellsociety.xml.XMLException;
import cellsociety.xml.XMLParser;
import cellsociety.xml.XMLSaver;
import java.awt.Dimension;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;


/**
 * Feel free to completely change this code or delete it entirely.
 *
 * @author Cynthia France, Jose Santillan
 */
public class Main extends Application {

  // useful names for constant values used
  public static final String TITLE = "Cell Society";
  public static final int SIZE = 700;

  // kind of data files to look for
  public static final String DATA_FILE_EXTENSION = "*.xml";
  // NOTE: make ONE chooser since generally accepted behavior is that it remembers where user left it last
  public final static FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_EXTENSION);
  public static final Dimension DEFAULT_SIZE = new Dimension(750, 600);
  private static final int GAME_SIZE = 900;
  private double framesPerSecond;
  private double secondDelay;
  public static double RATE_CHANGE = 0.1;
  private SimulationView view;
  private double rate = 1.0;
  private Stage myStage;
  public static final String LANGUAGE = "English";
  XMLSaver saver = new XMLSaver();

  /**
   * Initialize what will be displayed.
   */
  @Override
  public void start(Stage stage) {
    myStage = stage;
    File dataFile = FILE_CHOOSER.showOpenDialog(stage);
//    Splash splash = new Splash();

    try {
      String name = dataFile.getName();

      Map<String, String> info = new XMLParser().getInformation(dataFile);

      view = selectView(info.get(SimulationModel.DATA_FIELDS.get(SimulationModel.SIMULATION_TYPE)), info);
      framesPerSecond = view.framesPerSec();
      secondDelay = 1.0 / framesPerSecond;

      stage.setTitle(TITLE);
      Scene scene = view.makeScene(DEFAULT_SIZE.width, DEFAULT_SIZE.height);
      // add our user interface components to Frame and show it
      stage.setScene(scene);
      stage.setHeight(760);
      stage.setWidth(810);
      stage.show();
      Timeline animation = new Timeline();
      playAnimation(animation, view);

      scene.setOnKeyReleased(e -> handleKeyInput(e.getCode(), animation));
      Button newConfigButton = view.getNewConfigButton();
      newConfigButton.setOnAction(e -> doNewConfig());
      Button saveConfigButton = view.getSaveConfigButton();
      saveConfigButton.setOnAction(e -> doSaveConfig());

    } catch (XMLException e) {
      // handle error of unexpected file format
      showMessage(AlertType.ERROR, e.getMessage());
    }
  }

  private void doNewConfig() {
    start(myStage);
  }

  private void doSaveConfig() {
    try {
      saver.save(view.getModel().getSimInfo(), view.getModel().DATA_FIELDS, view.getModel().getGrid(), view.getSaveInfo());
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (TransformerException e) {
      e.printStackTrace();
    }
  }

  private void handleKeyInput(KeyCode code, Timeline animation) {
    switch (code) {
      case LEFT -> {
        if (rate > 0.2) {
          rate -= RATE_CHANGE;
          animation.setRate(rate);
        }
      }
      case RIGHT -> {
        rate += RATE_CHANGE;
        animation.setRate(rate);
      }
    }
    System.out.println(rate);
  }

  private void playAnimation(Timeline animation, SimulationView view) {
    animation.setCycleCount(Timeline.INDEFINITE);
    framesPerSecond = view.framesPerSec();
    animation.getKeyFrames()
        .add(new KeyFrame(Duration.seconds(secondDelay), e -> view.step()));
    animation.play();



    animation.rateProperty().bind(view.getSlider().valueProperty());
  }

  private SimulationView selectView(String type, Map<String, String> info) {
    switch (type) {
      case "GameOfLife" -> {return new GameOfLifeView(new GameOfLifeModel(info, LANGUAGE));}

      case "Percolation" -> {return new PercolationView(new PercolationModel(info, LANGUAGE));}

      case "Segregation" -> {return new SegregationView(new SegregationModel(info, LANGUAGE));}

      case "SpreadingFire" -> {return new SpreadingFireView(new SpreadingFireModel(info, LANGUAGE));}

      case "WaTor" -> {return new WaTorView(new WaTorModel(info, LANGUAGE));}

      case "Rock Paper Scissors" -> {return new RPSView(new RockPaperSciModel(info, LANGUAGE));}

      case "Falling Sand" -> {return new FallingSandView(new FallingSandModel(info, LANGUAGE));}

      default -> throw new XMLException("not a simulation type", type);
    }
  }
  // display given message to user using the given type of Alert dialog box
  private void showMessage(AlertType type, String message) {
    new Alert(type, message).showAndWait();
  }

  // set some sensible defaults when the FileChooser is created
  private static FileChooser makeChooser(String extensionAccepted) {
    FileChooser result = new FileChooser();
    result.setTitle("Open Data File");
    // pick a reasonable place to start searching for files
    result.setInitialDirectory(new File(System.getProperty("user.dir")));
    result.getExtensionFilters().setAll(new ExtensionFilter("Text Files", extensionAccepted));
    return result;
  }
}
