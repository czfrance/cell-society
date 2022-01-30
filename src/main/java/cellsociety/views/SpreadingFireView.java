package cellsociety.views;

import cellsociety.models.SimulationModel;
import javafx.scene.text.Text;

public class SpreadingFireView extends SimulationView{

  public SpreadingFireView(SimulationModel simModel) {
    super(simModel);
  }


  @Override
  protected void addTitle() {
    Text t = new Text();
    t.setText("Spreading of Fire");
    root.getChildren().add(t);
  }
}
