package cellsociety.views;

import cellsociety.cells.Cell;
import cellsociety.models.Grid;
import cellsociety.models.SimulationModel;
import cellsociety.view_cells.SandViewCell;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.util.ArrayList;
import java.util.List;

public class FallingSandView extends SimulationView {

  public FallingSandView(SimulationModel simModel) {
    super(simModel);

  }

  @Override
  protected WebView getRules() {
    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();
    webEngine.load( getClass().getResource("/SandRules.html").toString() );
    webView.setPrefSize(300, 400);
    return webView;
  }

  @Override
  protected String getName() {
    return "Falling Sand";
  }

  @Override
  protected void makeGrid() {
    List<List<Cell>> cellGrid = model.getGrid().getGrid();
    for (int row = 0; row < cellGrid.size(); row++) {
      grid.add(new ArrayList<>());
      for (int cell = 0; cell < cellGrid.get(row).size(); cell++) {
        int state = cellGrid.get(row).get(cell).getMyCurrentState();
        switch (state) {
          case 0, 1, 2 -> {grid.get(row).add(new SandViewCell(cell, row, cellSize, state));}
          default -> {}
        }
      }
    }
  }
}
