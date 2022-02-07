package cellsociety.models;

import cellsociety.cells.Cell;
import cellsociety.cells.WaTorCell;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WaTorModel extends SimulationModel {

  private static final int EMPTY = 0;
  private static final int FISH = 1;
  private static final int SHARK = 2;

  public WaTorModel(Map<String, String> dataValues, String language) {
    super(dataValues, language);
    simType = "WaTor";
  }

  @Override
  protected void createGrid() {
    myGrid.addRow();
    int rowNum = 0;
    int colNum = 0;
    for (int i = 0; i < simInfo.get(DATA_FIELDS.get(6)).toCharArray().length; i++) {
      char c = simInfo.get(DATA_FIELDS.get(6)).toCharArray()[i];
      switch (c) {
        case '.' -> {myGrid.addRow();rowNum++;colNum = 0;}
        case '0', '1', '2' -> {myGrid.getRow(rowNum).add(new WaTorCell(colNum, rowNum,
            Character.getNumericValue(c), FISHTURNS, SHARKTURNS, SHARKSTARVE));colNum++;}
        default -> {}
        }
      }

  }

  @Override
  public void updateGrid() {
    List<Cell> movingSharks = getMovingObject(SHARK);
    List<Cell> movingFish = getMovingObject(FISH);

    move(movingSharks, SHARK);
    move(movingFish, FISH);
  }

  private void move(List<Cell> movingObjects, int type) {
    System.out.println("size: " + movingObjects.size());
    for (Cell c : movingObjects) {
      myGrid.initNeighbors(NEIGHBORTYPE);
      List<Cell> emptyNeighbors = c.getEmptyAdjacentCells();
      System.out.print(emptyNeighbors.size());
      if (emptyNeighbors.size() == 0) {
        System.out.print("zero");
        System.out.printf("%d %d\n", c.getRow(), c.getCol());
        myGrid.getRow(c.getRow()).set(c.getCol(),
            new WaTorCell(c.getCol(), c.getRow(), type, FISHTURNS, SHARKTURNS,
                SHARKSTARVE, c.getHealth(), c.getTurnsAlive()));
      }
      else {
        Random rand = new Random();
        System.out.print("not zero");
        int index = rand.nextInt(emptyNeighbors.size());
        Cell newLoc = emptyNeighbors.get(index);
        System.out.printf("%d %d\n", newLoc.getRow(), newLoc.getCol());
        Cell newCell = new WaTorCell(newLoc.getCol(), newLoc.getRow(), type, FISHTURNS, SHARKTURNS,
            SHARKSTARVE, c.getHealth(), c.getTurnsAlive());
        myGrid.getRow(newLoc.getRow()).set(newLoc.getCol(), newCell);
        newCell.setState(type);
      }
    }
  }

  private List<Cell> getMovingObject(int type) {
    List<Cell> objects = getObjects(type);
    List<Cell> movingObjects = new ArrayList<>();
    for (Cell object : objects) {
      int nextState = object.getNextState();
      if (nextState == 0) {
        object.setState(EMPTY);
      }
      else if (nextState == -1*type) {
        movingObjects.add(object);
        if (object.isReproducing()) {
          System.out.println("yessssssssss");
          movingObjects.add(new WaTorCell(object.getCol(), object.getRow(), type, FISHTURNS, SHARKTURNS, SHARKSTARVE));
        }
        object.setState(EMPTY);
      }
      else {
        if (object.isReproducing()) {
          System.out.println("yessssssssss");
          movingObjects.add(new WaTorCell(object.getCol(), object.getRow(), type, FISHTURNS, SHARKTURNS, SHARKSTARVE));
        }
      }
    }
    return movingObjects;
  }

  private List<Cell> getObjects(int type) {
    List<Cell> objects = new ArrayList<>();
    for (int row = 0; row < myGrid.size(); row++) {
      for (int cell = 0; cell < myGrid.getRow(row).size(); cell++) {
        Cell c = myGrid.getRow(row).get(cell);
        if (c.getMyCurrentState() == type) {
          objects.add(c);
        }
      }
    }
    return objects;
  }
}
