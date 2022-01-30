package cellsociety.views;

import cellsociety.models.SimulationModel;
import javafx.scene.text.Text;

public class PercolationView extends SimulationView {

  public PercolationView(SimulationModel simModel) {
    super(simModel);
  }

  @Override
  protected void addTitle() {
    Text t = new Text();
    t.setText("Percolation");
    root.getChildren().add(t);
  }
}
