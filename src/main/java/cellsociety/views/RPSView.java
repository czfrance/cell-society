package cellsociety.views;

import cellsociety.cells.Cell;
import cellsociety.models.SimulationModel;
import cellsociety.view_cells.RPSViewCell;
import javafx.scene.web.WebView;

import java.util.ArrayList;
import java.util.List;

public class RPSView extends SimulationView{

  public RPSView(SimulationModel simModel) {super(simModel);}

//  @Override
//  protected WebView getRules() {
//    WebView webView = new WebView();
//    webView.getEngine().loadContent("<html>Pay attention, there are Pay attention, there are" +
//            "Pay attention, there are" +
//            "Pay attention, there are" +
//            "<b>HTML</b> tags, here.</html>");
//    webView.setPrefSize(300, 400);
//    return webView;
//  }

  @Override
  protected String getName() {
    return model.getMyResources().getString("RPS");
  }

  @Override
  protected String getHeader() {
    return model.getMyResources().getString("RPSRules");
  }

  @Override
  protected String getHtml() {
    return "/RPSRules.html";
  }

  @Override
  protected void makeGrid() {
    List<List<Cell>> cellGrid = model.getGrid().getGrid();
    for (int row = 0; row < cellGrid.size(); row++) {
      grid.add(new ArrayList<>());
      for (int cell = 0; cell < cellGrid.get(row).size(); cell++) {
        int state = cellGrid.get(row).get(cell).getMyCurrentState();
        switch (state) {
          case 0, 1, 2 -> {grid.get(row).add(new RPSViewCell(cell, row, cellSize, state));}
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
        int state = cellGrid.get(row).get(cell).getMyCurrentState();
        switch (state) {
          case 0, 1, 2 -> {grid.get(row).get(cell).updateState(state);}
          default -> {}
        }
      }
    }
  }
}
