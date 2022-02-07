package cellsociety.views;

import cellsociety.cells.Cell;
import cellsociety.models.Grid;
import cellsociety.models.SimulationModel;
import cellsociety.view_cells.SchellingGroupViewCell;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Cynthia France
 */
public class SegregationView extends SimulationView{

  /**
   *
   * @param simModel the simulation model
   */
  public SegregationView(SimulationModel simModel) {
    super(simModel);
  }

  @Override
  protected WebView getRules() {
    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();
    webEngine.load( getClass().getResource("/SegregationRules.html").toString() );
    webView.setPrefSize(300, 400);
    return webView;
  }

  @Override
  protected String getName() {
    return model.getMyResources().getString("Segregation");
  }

  @Override
  protected void makeGrid() {
    Grid cellGrid = model.getGrid();
    for (int row = 0; row < cellGrid.size(); row++) {
      grid.add(new ArrayList<>());
      for (int cell = 0; cell < cellGrid.getRow(row).size(); cell++) {
        int state = cellGrid.getRow(row).get(cell).getMyCurrentState();
        switch (state) {
          case 0, 1, 2 -> {
            grid.get(row).add(new SchellingGroupViewCell(cell, row, cellSize, state));
          }
          default -> {}
        }
      }
    }
  }
}
