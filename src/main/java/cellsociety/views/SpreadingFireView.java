package cellsociety.views;

import cellsociety.models.Grid;
import cellsociety.models.SimulationModel;
import cellsociety.view_cells.BurningViewCell;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.util.ArrayList;

/**
 * author: Cynthia France
 */
public class SpreadingFireView extends SimulationView{

  public static final int TREE = 0;
  public static final int BURNING = 1;
  public static final int DEAD = 2;

  /**
   *
   * @param simModel the simulation model
   */
  public SpreadingFireView(SimulationModel simModel) {
    super(simModel);
  }


  @Override
  protected WebView getRules() {
    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();
    webEngine.load( getClass().getResource("/SimulationRules.html").toString() );
    webView.setPrefSize(300, 400);
    return webView;
  }

  @Override
  protected String getName() {

    return model.getMyResources().getString("SoF");
  }

  @Override
  protected void makeGrid() {
    Grid cellGrid = model.getGrid();
    for (int row = 0; row < cellGrid.size(); row++) {
      grid.add(new ArrayList<>());
      for (int cell = 0; cell < cellGrid.getRow(row).size(); cell++) {
        int state = cellGrid.getRow(row).get(cell).getMyCurrentState();
        switch (state) {
          case TREE, BURNING, DEAD -> {
            grid.get(row).add(new BurningViewCell(cell, row, cellSize, state));
          }
          default -> {}
        }
      }
    }
  }
}
