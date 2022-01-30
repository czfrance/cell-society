package cellsociety.models;

import cellsociety.cells.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class SimulationModel {

  public static final List<String> DATA_FIELDS = List.of("simulationType", "title", "author",
      "description", "width", "height", "config");

  protected Map<String, String> simInfo;
  protected String simType;

  public final String HEIGHT_INFO = "height";
  public final String WIDTH_INFO = "width";

  protected List<List<Cell>> myGrid = new ArrayList<>();

  public final int WIDTH;
  public final int HEIGHT;

  private int iteration;
  private int simulationSpeed;

  public SimulationModel(Map<String, String> dataValues) {
    simInfo = dataValues;
    WIDTH = Integer.parseInt(simInfo.get(WIDTH_INFO));
    HEIGHT = Integer.parseInt(simInfo.get(HEIGHT_INFO));
    // FIXME: IMPLEMENT SIMULATIONSPEED IN XML FILES AND INCORPORATE (DOES IT GO IN HERE OR MAIN?)
    createGrid();
    initGrid();
  }

  private void init() {

  }

  private void step() {

  }

//  public void updateGrid() {
//    for (int row = 0; row < myGrid.size(); row++) {
//      for (int cell = 0; cell < myGrid.get(row).size(); cell++) {
//        myGrid.get(row).get(cell).nextState();
//      }
//    }
//  }

  public void updateGrid() {
    List<List<Integer>> newStates = getCellNextStates();
    for (int row = 0; row < myGrid.size(); row++) {
      for (int cell = 0; cell < myGrid.get(row).size(); cell++) {
        myGrid.get(row).get(cell).setState(newStates.get(row).get(cell));
      }
    }
  }

  private List<List<Integer>> getCellNextStates() {
    List<List<Integer>> newStates = new ArrayList<>();
    newStates.add(new ArrayList<>());

    for (int row = 0; row < myGrid.size(); row++) {
      for (Cell c : myGrid.get(row)) {
        newStates.get(row).add(c.getNextState());
      }
      newStates.add(new ArrayList<>());
    }

    return newStates;
  }

  protected abstract void createGrid();

  public List<List<Cell>> getGrid() {
    return myGrid;
  }

  public int[] getGridSize() {
    return new int[]{Integer.parseInt(simInfo.get("width")),
        Integer.parseInt(simInfo.get("height"))};
  }

  /**
   * Prints the current grid
   */
  public void printGrid() {
    for (List l : myGrid) {
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
  public String toString() {
    return String.join("\n",
        String.format("%s = [", simInfo.get(DATA_FIELDS.get(0))),
        String.format("  %s = '%s',", DATA_FIELDS.get(1), simInfo.get(DATA_FIELDS.get(1))),
        String.format("  %s = '%s',", DATA_FIELDS.get(2), simInfo.get(DATA_FIELDS.get(2))),
        String.format("  %s = '%s',", DATA_FIELDS.get(3), simInfo.get(DATA_FIELDS.get(3))),
        String.format("  %s = '%d',", DATA_FIELDS.get(4),
            Integer.parseInt(simInfo.get(DATA_FIELDS.get(4)))),
        String.format("  %s = '%d',", DATA_FIELDS.get(5),
            Integer.parseInt(simInfo.get(DATA_FIELDS.get(5)))),
        String.format("  %s = '%s',", DATA_FIELDS.get(6), simInfo.get(DATA_FIELDS.get(6))),
        "]");
  }

  /**
   * getter method
   *
   * @return Height of the grid
   */
  public int getHeight() {
    return HEIGHT;
  }

  /**
   * Getter method
   *
   * @return Width of the grid
   */
  public int getWidth() {
    return WIDTH;
  }

  /**
   * Getter method
   *
   * @return The current iteration the simulation is on
   */
  public int getIteration() {
    return iteration;
  }

  /**
   * Getter method
   *
   * @return the speed at which the simulation is operating at
   */
  public int getSpeed() {
    return simulationSpeed;
  }

  /**
   * Setter method
   *
   * @param newSpeed, the new speed the simulation should operate at
   */
  public void setSpeed(int newSpeed) {
    simulationSpeed = newSpeed;
  }

  /**
   * Handles user input such as mouseclicks and button presses
   */
  public void HandleKeyInput() {
    //need to implement within extended classes
  }

  private void initGrid() {
    for (List<Cell> l : myGrid) {
      for (Cell c : l) {
        c.initNeighbors(WIDTH, HEIGHT, myGrid);
      }
    }
  }
}
