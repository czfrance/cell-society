package cellsociety.models;

import cellsociety.cells.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimulationModel {
  // name in data file that will indicate it represents data for this type of object
  //public static final String DATA_TYPE = "Game";
  // field names expected to appear in data file holding values for this object

  public static final List<String> DATA_FIELDS = List.of("simulationType", "title", "author", "description",
                                                              "width", "height", "config");

  protected Map<String, String> simInfo;
  protected String simType;

  public final String HEIGHT_INFO = "height";
  public final String WIDTH_INFO = "width";

  protected List<List<Integer>> rawGrid = new ArrayList<>();

  //private Cell[][] myGrid;
  private final int WIDTH;
  private final int HEIGHT;

  private int iteration;
  private int simulationSpeed;

  public SimulationModel(Map<String, String> dataValues) {
    simInfo = dataValues;
    WIDTH = Integer.parseInt(simInfo.get(WIDTH_INFO));
    HEIGHT = Integer.parseInt(simInfo.get(HEIGHT_INFO));

    createGrid();
  }

  private void init() {

  }

  private void step() {

  }

//  public Cell[][] getGrid() {
//    return myGrid;
//  }

  public void updateGrid() {

  }

  protected void createGrid() {
    rawGrid.add(new ArrayList<Integer>());
    int rowNum = 0;
    for (int i = 0; i < simInfo.get(DATA_FIELDS.get(6)).toCharArray().length; i++) {
      char c = simInfo.get(DATA_FIELDS.get(6)).toCharArray()[i];
      switch (c) {
        case '.' -> {rawGrid.add(new ArrayList<Integer>()); rowNum++;}
        case '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' -> rawGrid.get(rowNum).add(Character.getNumericValue(c));
        default -> {}
      }
    }
  }

  public List<List<Integer>> getGrid() {
    return rawGrid;
  }

  public int[] getGridSize() {
    return new int[] {Integer.parseInt(simInfo.get("width")), Integer.parseInt(simInfo.get("height"))};
  }

  /**
   * Prints the current grid
   */
  public void printGrid() {
    for (List l : rawGrid) {
      for (Object i : l) {
        System.out.print(i);
      }
      System.out.println();
    }
  }

  /**
   * @see Object#toString()
   */
  @Override
  public String toString () {
    return String.join("\n",
        String.format("%s = [", simInfo.get(DATA_FIELDS.get(0))),
        String.format("  %s = '%s',", DATA_FIELDS.get(1), simInfo.get(DATA_FIELDS.get(1))),
        String.format("  %s = '%s',", DATA_FIELDS.get(2), simInfo.get(DATA_FIELDS.get(2))),
        String.format("  %s = '%s',", DATA_FIELDS.get(3), simInfo.get(DATA_FIELDS.get(3))),
        String.format("  %s = '%d',", DATA_FIELDS.get(4), Integer.parseInt(simInfo.get(DATA_FIELDS.get(4)))),
        String.format("  %s = '%d',", DATA_FIELDS.get(5), Integer.parseInt(simInfo.get(DATA_FIELDS.get(5)))),
        String.format("  %s = '%s',", DATA_FIELDS.get(6), simInfo.get(DATA_FIELDS.get(6))),
        "]");
  }

  /**
   * getter method
   * @return Height of the grid
   */
  public int getHeight() {return HEIGHT;}

  /**
   * Getter method
   * @return Width of the grid
   */
  public int getWidth() {return WIDTH;}

  /**
   * Getter method
   * @return The current iteration the simulation is on
   */
  public int getIteration() {return iteration;}

  public int getSpeed() {return simulationSpeed;}

  public void setSpeed(int newSpeed) {simulationSpeed = newSpeed;}
  public void HandleKeyInput() {
    //need to implement within extended classes
  }
}
