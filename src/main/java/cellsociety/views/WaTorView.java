package cellsociety.views;

import cellsociety.models.SimulationModel;

public class WaTorView extends SimulationView{

  public WaTorView(SimulationModel simModel) {
    super(simModel);
  }

  @Override
  protected String getRules() {
    return "Rules: ";
  }

  @Override
  protected String getName() {
    return "WaTor";
  }

  @Override
  protected void makeGrid() {

  }

  @Override
  protected void updateGrid() {

  }
}
