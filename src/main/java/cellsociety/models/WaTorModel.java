package cellsociety.models;

import cellsociety.cells.Cell;
import cellsociety.cells.FishCell;
import cellsociety.cells.SharkCell;
import cellsociety.cells.WaTorCell;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WaTorModel extends SimulationModel {

  Cell[][] grid;

  private final int EMPTY = 0;
  private final int FISH = 1;
  private final int SHARK = 2;

  public WaTorModel(Map<String, String> dataValues) {
    super(dataValues);
    simType = "WaTor";
    grid = new WaTorCell[HEIGHT][WIDTH];

  }

  @Override
  protected void createGrid() {
    myGrid = new ArrayList<>();

    myGrid.add(new ArrayList<>());
    int rowNum = 0;
    int colNum = 0;
    for (int i = 0; i < simInfo.get(DATA_FIELDS.get(6)).toCharArray().length; i++) {
      char c = simInfo.get(DATA_FIELDS.get(6)).toCharArray()[i];
      switch (c) {
        case '.' -> {myGrid.add(new ArrayList<>());rowNum++;colNum = 0;}
        case '0' -> {myGrid.get(rowNum).add(new WaTorCell(colNum, rowNum, 0, 0, 0, 0, 0));colNum++;}
        case '1' -> {myGrid.get(rowNum).add(new WaTorCell(colNum, rowNum, FISH, FISH, 5, 5, 5));colNum++;}
        case '2' -> {myGrid.get(rowNum).add(new WaTorCell(colNum, rowNum, SHARK, SHARK, 5, 5, 5));colNum++;}
        default -> {}
        }
      }

  }

  private void clashPrevention() {

  }

  private void makeTempGrid() {
    grid = new Cell[HEIGHT][WIDTH];
    for (List<Cell> list : myGrid) {
      for (Cell cell : list) {

        ((WaTorCell) cell).updateCell(WIDTH, HEIGHT, myGrid);
        int code = ((WaTorCell) cell).getCurrentState();

        if (code == FISH) {
          FishCell fish = ((WaTorCell) cell).getFish();
          grid[fish.getRow()][fish.getColumn()] = fish;

        } else if (code == SHARK && !((WaTorCell) cell).getShark().isDead()) {
          SharkCell shark = ((WaTorCell) cell).getShark();
          grid[shark.getRow()][shark.getColumn()] = shark;
          ((WaTorCell) myGrid.get(shark.getRow()).get(shark.getColumn())).block();
        }
      }
    }
  }

  private void transferTempToGrid() {
    printTempGrid();
    for (int i = 0; i < HEIGHT; i++) {
      for (int j = 0; j < WIDTH; j++) {
        int id = grid[i][j] == null ? EMPTY : grid[i][j].getID();
        WaTorCell cell = ((WaTorCell) myGrid.get(i).get(j));

        if (id == SHARK) {
          if (cell.getID() == FISH) {
            cell.getFish().death();
            cell.setEmpty(true);
          }
          cell.setShark((SharkCell) grid[i][j]);

        }  else if (id == FISH && !((FishCell) grid[i][j]).isDead()) {
          cell.setFish((FishCell) grid[i][j]);

        } else {
          ((WaTorCell) myGrid.get(i).get(j)).setEmpty(false);
        }
      }
    }
  }

  public void updateGrid() {
    makeTempGrid();

    System.out.println(printBoard() + "\n\n\n");

    transferTempToGrid();

  }
  public void printTempGrid() {
    for (Cell[] list : grid) {
      for (Cell c : list) {
        int d = c == null ? 0 : c.getID();
        if (d == 2) {
          int l = ((SharkCell) c).getHealth();
          System.out.print(l + " ");
        } else {
          System.out.print(d + " ");
        }
      }
      System.out.println();
    }
  }

  public String printBoard() {
    for (List<Cell> list : myGrid) {
      for (Cell cell : list) {
        int d = cell == null ? 0 : cell.getID();
        System.out.print(d + " ");
      }
      System.out.println();
    }
    return "";
  }
}

