package cellsociety.views;

import cellsociety.models.SimulationModel;
import javafx.scene.text.Text;

public class WaTorView extends SimulationView{

  public WaTorView(SimulationModel simModel) {
    super(simModel);
  }

  @Override
  protected void addTitle() {
    Text t = new Text();
    t.setText("WaTor");
    root.getChildren().add(t);
  }

  @Override
  protected void makeGrid() {

  }

  @Override
  protected void updateGrid() {

  }
}
