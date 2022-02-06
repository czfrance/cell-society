package cellsociety.views;

import cellsociety.xml.XMLParser;
import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.util.Map;


public class Splash
{
//    public final static FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_EXTENSION);

    static Scene splash;
    static Rectangle rect = new Rectangle();
    final private Pane pane;
    final private SequentialTransition seqT;

    public Splash()
    {
        pane = new Pane();
        pane.setStyle("-fx-background-color:black");

        splash = new Scene(pane);
        seqT = new SequentialTransition();
    }

    public void show() {

    }

//    public void parse() {
//        File dataFile = FILE_CHOOSER.showOpenDialog(stage);
//        try {
//            String name = dataFile.getName();
//
//            Map<String, String> info = new XMLParser().getInformation(dataFile);
//    }
//
}
