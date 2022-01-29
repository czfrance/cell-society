package cellsociety.models;

import java.util.*;
import cellsociety.cells.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameOfLifeModel extends SimulationModel {

  private ArrayList<ArrayList<LifeCell>> myGrid;
  private Random DICE = new Random();

  public GameOfLifeModel(Map<String, String> dataValues) {
    super(dataValues);
    simType = "GameOfLife";
    createGrid();
    initGrid();
  }

  public void step() {
    updateGrid();
  }


  public void updateGrid() {
    ArrayList<ArrayList<LifeCell>> newGrid = new ArrayList<>();
    for (ArrayList<LifeCell> row : myGrid) {
      ArrayList<LifeCell> newRow = new ArrayList<>();
      for (LifeCell cell : row) {
        cell.nextState();
        newRow.add(cell);
      }
      newGrid.add(newRow);
    }

    myGrid = newGrid;
  }

  protected void createGrid() {
    myGrid = new ArrayList<>();
    for (int i = 0; i < HEIGHT; i++) {
      ArrayList<LifeCell> cellRow = new ArrayList<>();
      for (int j = 0; j < WIDTH; j++) {
        cellRow.add(new LifeCell(i * (int) CELL_SIZE, j * (int) CELL_SIZE, CELL_SIZE, "" + DICE.nextInt(2)));
      }
      myGrid.add(cellRow);
    }
  }

  private void initGrid() {
    for (ArrayList<LifeCell> l : myGrid) {
      for (LifeCell c : l) {
        c.initNeighbors(WIDTH, HEIGHT, myGrid);
      }
    }
  }


}
