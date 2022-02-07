package cellsociety.views;

import cellsociety.cells.Cell;
import cellsociety.cells.WaTorCell;
import cellsociety.models.SimulationModel;

import cellsociety.view_cells.WaTorViewCell;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.util.ArrayList;
import java.util.List;


public class WaTorView extends SimulationView{

  public WaTorView(SimulationModel simModel) {
    super(simModel);
  }

//  @Override
//  protected WebView getRules() {
//    WebView webView = new WebView();
//    WebEngine webEngine = webView.getEngine();
//    webEngine.load( getClass().getResource("/WaTorRules.html").toString() );
//    webView.setPrefSize(300, 400);
//    return webView;
//  }

  @Override
  protected String getName() {
    return model.getMyResources().getString("WaTor");
  }

  @Override
  protected String getHeader() {
    return model.getMyResources().getString("WaTorRules");
  }

  @Override
  protected String getHtml() {
    return "/WaTorRules.html";
  }

  @Override
  protected void makeGrid() {
    List<List<Cell>> cellGrid = model.getGrid().getGrid();
    for (int row = 0; row < cellGrid.size(); row++) {
      grid.add(new ArrayList<>());
      for (int cell = 0; cell < cellGrid.get(row).size(); cell++) {
        Cell c1 = cellGrid.get(row).get(cell);
        int state = c1.getMyCurrentState();
        switch (state) {
          case 0, 1, 2 -> {grid.get(row).add(new WaTorViewCell(cell, row, cellSize, state, (WaTorCell) c1));}
          default -> {}
        }
      }
    }
  }

  @Override
  protected void updateGrid() {
    List<List<Cell>> cellGrid = model.getGrid().getGrid();
    for (int row = 0; row < cellGrid.size(); row++) {
      for (int cell = 0; cell < cellGrid.get(row).size(); cell++) {
        int state = ((WaTorCell) cellGrid.get(row).get(cell)).getCurrentState();
        switch (state) {
          case 0, 1, 2 -> {grid.get(row).get(cell).updateState(state);}
          default -> {}
        }
      }
    }
  }
}
