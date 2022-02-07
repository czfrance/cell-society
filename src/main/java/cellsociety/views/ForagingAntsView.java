package cellsociety.views;

import cellsociety.models.SimulationModel;
import javafx.scene.control.Alert;

public class ForagingAntsView extends SimulationView {

  public ForagingAntsView(SimulationModel simModel) {
    super(simModel);
  }

  @Override
  protected Alert getRules() {
    return null;
  }

  @Override
  protected String getHeader() {
    return null;
  }

  @Override
  protected String getName() {
    return null;
  }

  @Override
  protected String getHtml() {
    return null;
  }

  @Override
  protected void makeGrid() {

  }

  @Override
  protected void updateGrid() {

  }
}
