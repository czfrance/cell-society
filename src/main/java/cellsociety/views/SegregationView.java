package cellsociety.views;

import cellsociety.models.SimulationModel;
import javafx.scene.text.Text;

public class SegregationView extends SimulationView{

  public SegregationView(SimulationModel simModel) {
    super(simModel);
  }

  @Override
  protected String getRules() {
    return "Rules: ";
  }

  @Override
  protected String getName() {
    return "Segregation";
  }

  @Override
  protected void addTitle() {
    Text t = new Text();
    t.setText("Segregation");
    root.getChildren().add(t);
  }

  @Override
  protected void makeGrid() {

  }

  @Override
  protected void updateGrid() {

  }
}
