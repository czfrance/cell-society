package cellsociety.views;

import cellsociety.models.SimulationModel;
import javafx.scene.text.Text;

public class SpreadingFireView extends SimulationView{

  public SpreadingFireView(SimulationModel simModel) {
    super(simModel);
  }


  @Override
  protected String getRules() {
    return "Rules: ";
  }

  @Override
  protected String getName() {
    return "Spreading of Fire";
  }

  @Override
  protected void makeGrid() {

  }

  @Override
  protected void updateGrid() {

  }
}
