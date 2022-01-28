package cellsociety;

import cellsociety.models.GameOfLifeModel;
import cellsociety.models.PercolationModel;
import cellsociety.models.SegregationModel;
import cellsociety.models.SimulationModel;
import cellsociety.models.SpreadingFireModel;
import cellsociety.models.WaTorModel;
import cellsociety.views.GameOfLifeView;
import cellsociety.views.PercolationView;
import cellsociety.views.SegregationView;
import cellsociety.views.SimulationView;
import cellsociety.views.SpreadingFireView;
import cellsociety.views.WaTorView;
import cellsociety.xml.XMLException;
import cellsociety.xml.XMLParser;
//import javafx.application.Application;
import java.awt.Dimension;
import java.util.List;
import java.util.Map;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
//import javafx.stage.Stage;
import javafx.scene.shape.Circle;

import java.io.File;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
//import generic.Pair;


/**
 * Feel free to completely change this code or delete it entirely.
 *
 * @author YOUR NAME HERE
 */
public class Main extends Application {
    // useful names for constant values used
    public static final String TITLE = "Example JavaFX Animation";
    public static final int SIZE = 700;

    // kind of data files to look for
    public static final String DATA_FILE_EXTENSION = "*.xml";
    // NOTE: make ONE chooser since generally accepted behavior is that it remembers where user left it last
    public final static FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_EXTENSION);
    public static final Dimension DEFAULT_SIZE = new Dimension(800, 600);
//    private static final List<SimulationModel> POSSIBLE_BETS = List.of(
//        new RedBlackBet("Red or Black", 1),
//        new OddEvenBet("Odd or Even", 1),
//        new ConsecutiveBet("Three in a Row", 11)
//    );
//
//    private static final Map<String, SimulationModel> SIM_MODELS = Map.of(
//        "GameOfLife", new GameOfLifeModel(),
//    );


    /**
     * Initialize what will be displayed.
     */
    @Override
    public void start (Stage stage) {
//        Circle shape = new Circle(SIZE / 2, SIZE / 2, 20);
//        shape.setFill(Color.LIGHTSTEELBLUE);

        //Group root = new Group();
        //root.getChildren().add(shape);

        //Scene scene = new Scene(root, SIZE, SIZE, Color.DARKBLUE);
        //stage.setScene(scene);

        //stage.setTitle(TITLE);
        //stage.show();

        File dataFile = FILE_CHOOSER.showOpenDialog(stage);
        //while (dataFile != null) {
            try {
                System.out.println("starting");
                // just showing how to use a pair :)
                String name = dataFile.getName();
//<<<<<<< HEAD
                Map<String, String> info = new XMLParser().getInformation(dataFile);
//                SimulationModel model = new SimulationModel(info);
//                GameOfLifeModel game = new GameOfLifeModel(info);
//                System.out.println(game.getHeight());
//=======
                //SimulationModel model = new XMLParser().getSimulation(dataFile);
                SimulationView view;
//>>>>>>> 0dedd0e (added constructors for view, model, and cell classes, wrote basic view to draw grid)
                //Pair<String, Game> p = new Pair<>(dataFile.getName(), new XMLParser().getSimulation(dataFile));
                // do something "interesting" with the resulting data
                //model.printGrid();
                //System.out.println(model.getSimType());
                //showMessage(AlertType.INFORMATION, name + "\n" + model.toString());
                //System.out.println("parser: " + info.get(SimulationModel.DATA_FIELDS.get(0)));
                switch (info.get(SimulationModel.DATA_FIELDS.get(0))) {
                    case "GameOfLife" -> {view = new GameOfLifeView(new GameOfLifeModel(info));}
                    case "Percolation" -> {view = new PercolationView(new PercolationModel(info));}
                    case "Segregation" -> {view = new SegregationView(new SegregationModel(info));}
                    case "SpreadingFire" -> {view = new SpreadingFireView(new SpreadingFireModel(info));}
                    case "WaTor" -> {view = new WaTorView(new WaTorModel(info));}
                    default -> throw new XMLException("not a simulation type", SimulationModel.DATA_FIELDS.get(0));
                }

                stage.setTitle(TITLE);
                // add our user interface components to Frame and show it
                stage.setScene(view.makeScene(DEFAULT_SIZE.width, DEFAULT_SIZE.height));
                stage.show();
                // start somewhere, less typing for debugging
                //browser.showPage(DEFAULT_START_PAGE);
            }
            catch (XMLException e) {
                // handle error of unexpected file formatgetSi
                showMessage(AlertType.ERROR, e.getMessage());
            }
            //dataFile = FILE_CHOOSER.showOpenDialog(stage);
        //}

        // nothing selected, so quit the application
        //Platform.exit();
    }

    // display given message to user using the given type of Alert dialog box
    private void showMessage (AlertType type, String message) {
        new Alert(type, message).showAndWait();
    }

    // set some sensible defaults when the FileChooser is created
    private static FileChooser makeChooser (String extensionAccepted) {
        FileChooser result = new FileChooser();
        result.setTitle("Open Data File");
        // pick a reasonable place to start searching for files
        result.setInitialDirectory(new File(System.getProperty("user.dir")));
        result.getExtensionFilters().setAll(new ExtensionFilter("Text Files", extensionAccepted));
        return result;
    }
}
