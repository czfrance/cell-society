package cellsociety.models;

import cellsociety.cells.Cell;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class SimulationModel {

  public static final List<String> DATA_FIELDS = List.of("simulationType", "title", "author", "description", "width", "height", "config", "satisfied");

  public static final int SIMULATION_TYPE = 0;
  public static final int TITLE = 1;
  public static final int AUTHOR = 2;
  public static final int DESCRIPTION = 3;
  public static final int WIDTH_FIELD = 4;
  public static final int HEIGHT_FIELD = 5;
  public static final int CONFIG = 6;
  public static final int SATISFIED_FIELD = 7;

  protected Map<String, String> simInfo;
  protected String simType;

  public static final String HEIGHT_INFO = "height";
  public static final String WIDTH_INFO = "width";
  public static final String SATISFIED_INFO = "satisfied";

  protected List<List<Cell>> myGrid = new ArrayList<>();

  public final int WIDTH;
  public final int HEIGHT;
  public final double SATISFIED;

  private int iteration;
  private int simulationSpeed;


  public SimulationModel(Map<String, String> dataValues) {
    simInfo = dataValues;
    WIDTH = Integer.parseInt(simInfo.get(WIDTH_INFO));
    HEIGHT = Integer.parseInt(simInfo.get(HEIGHT_INFO));
    if (simInfo.get(SATISFIED_INFO) != "") SATISFIED = Double.parseDouble(simInfo.get(SATISFIED_INFO));
    else SATISFIED = 0;
    // FIXME: IMPLEMENT SIMULATIONSPEED IN XML FILES AND INCORPORATE (DOES IT GO IN HERE OR MAIN?)
    createGrid();
    initGrid();
  }

  private void init() {
    for (List<Cell> row : myGrid) {
      for (Cell cell : row) {
        cell.getNextState();
      }
    }

    for (List<Cell> row : myGrid) {
      for (Cell cell : row) {
        cell.update();
      }
    }
  }

  private void step() {

  }

  public void updateGrid() {
    List<List<Integer>> newStates = getCellNextStates();
    for (int row = 0; row < myGrid.size(); row++) {
      for (int cell = 0; cell < myGrid.get(row).size(); cell++) {
        myGrid.get(row).get(cell).setState(newStates.get(row).get(cell));
      }
    }
  }

  protected List<List<Integer>> getCellNextStates() {
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
        String.format("%s = [", simInfo.get(DATA_FIELDS.get(SIMULATION_TYPE))),
        String.format("  %s = '%s',", DATA_FIELDS.get(TITLE), simInfo.get(DATA_FIELDS.get(TITLE))),
        String.format("  %s = '%s',", DATA_FIELDS.get(AUTHOR), simInfo.get(DATA_FIELDS.get(AUTHOR))),
        String.format("  %s = '%s',", DATA_FIELDS.get(DESCRIPTION), simInfo.get(DATA_FIELDS.get(DESCRIPTION))),
        String.format("  %s = '%d',", DATA_FIELDS.get(WIDTH_FIELD),
            Integer.parseInt(simInfo.get(DATA_FIELDS.get(WIDTH_FIELD)))),
        String.format("  %s = '%d',", DATA_FIELDS.get(HEIGHT_FIELD),
            Integer.parseInt(simInfo.get(DATA_FIELDS.get(HEIGHT_FIELD)))),
        String.format("  %s = '%s',", DATA_FIELDS.get(CONFIG), simInfo.get(DATA_FIELDS.get(CONFIG))),
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
