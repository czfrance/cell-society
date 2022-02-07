package cellsociety.views;

import cellsociety.cells.Cell;
import cellsociety.cells.PercolatingCell;
import cellsociety.models.Grid;
import cellsociety.models.SimulationModel;
import cellsociety.view_cells.BlockedPercolationViewCell;
import cellsociety.view_cells.PercolatingViewCell;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Cynthia France
 */
public class PercolationView extends SimulationView {

  /**
   *
   * @param simModel the simulation model
   */
  public PercolationView(SimulationModel simModel) {
    super(simModel);
  }

  @Override
  protected WebView getRules() {
    WebView webView = new WebView();

    WebEngine webEngine = webView.getEngine();
    webEngine.load( getClass().getResource("/PercolationRules.html").toString() );

    webView.setPrefSize(300, 400);
    return webView;
  }

  @Override
  protected String getName() {
    return model.getMyResources().getString("Percolation");
  }

  @Override
  protected void makeGrid() {
    List<List<Cell>> cellGrid = model.getGrid().getGrid();
    for (int row = 0; row < cellGrid.size(); row++) {
      grid.add(new ArrayList<>());
      for (int cell = 0; cell < cellGrid.get(row).size(); cell++) {
        int state = cellGrid.get(row).get(cell).getMyCurrentState();
        switch (state) {
          case 0 -> {
            if (cellGrid.get(row).get(cell) instanceof PercolatingCell) {
              grid.get(row).add(new PercolatingViewCell(cell, row, cellSize, state));
            }
            else grid.get(row).add(new BlockedPercolationViewCell(cell, row, cellSize, state));
          }
          case 1 -> {grid.get(row).add(new PercolatingViewCell(cell, row, cellSize, state));}
          default -> {}
        }
      }
    }
  }

  @Override
  protected void updateGrid() {
    Grid cellGrid = model.getGrid();
    for (int row = 0; row < cellGrid.size(); row++) {
      for (int cell = 0; cell < cellGrid.getRow(row).size(); cell++) {
        int state = cellGrid.getRow(row).get(cell).getMyCurrentState();
        switch (state) {
          case 0, 1 -> {grid.get(row).get(cell).updateState(state);}
          default -> {}
        }
      }
    }
  }
}
