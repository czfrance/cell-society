package cellsociety.models;

import cellsociety.cells.Cell;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class SimulationModel {

  public static final List<String> DATA_FIELDS = List.of("simulationType", "title", "author", "description", "width", "height", "config", "speed", "satisfied", "probCatch");

  public static final int SIMULATION_TYPE = 0;
  public static final int TITLE = 1;
  public static final int AUTHOR = 2;
  public static final int DESCRIPTION = 3;
  public static final int WIDTH_FIELD = 4;
  public static final int HEIGHT_FIELD = 5;
  public static final int CONFIG = 6;
  //public static final int SATISFIED_FIELD = 7;

  protected Map<String, String> simInfo;
  protected String simType;

  public static final String HEIGHT_INFO = "height";
  public static final String WIDTH_INFO = "width";
  public static final String SATISFIED_INFO = "satisfied";
  public static final String PROBCATCH_INFO = "probCatch";
  public static final String SPEED = "speed";

  protected Grid myGrid;

  public final int WIDTH;
  public final int HEIGHT;
  public final double SATISFIED;
  public final double PROBCATCH;

  private int iteration;
  private double simulationSpeed;


  public SimulationModel(Map<String, String> dataValues) {
    simInfo = dataValues;
    WIDTH = Integer.parseInt(simInfo.get(WIDTH_INFO));
    HEIGHT = Integer.parseInt(simInfo.get(HEIGHT_INFO));
    if (!simInfo.get(SATISFIED_INFO).equals("")) SATISFIED = Double.parseDouble(simInfo.get(SATISFIED_INFO));
    else SATISFIED = 0;
    if (!simInfo.get(PROBCATCH_INFO).equals("")) PROBCATCH = Double.parseDouble(simInfo.get(PROBCATCH_INFO));
    else PROBCATCH = 0;
    simulationSpeed = Double.parseDouble(simInfo.get(SPEED));

    myGrid = new Grid(WIDTH, HEIGHT);
    // FIXME: IMPLEMENT SIMULATIONSPEED IN XML FILES AND INCORPORATE (DOES IT GO IN HERE OR MAIN?)

    createGrid();
    initGrid();
  }

  public void updateGrid() {
    List<List<Integer>> newStates = getCellNextStates();
    myGrid.updateGrid(newStates);
  }

  protected List<List<Integer>> getCellNextStates() {
    List<List<Integer>> newStates = new ArrayList<>();
    newStates.add(new ArrayList<>());

    for (int row = 0; row < myGrid.size(); row++) {
      for (Cell c : myGrid.getRow(row)) {
        newStates.get(row).add(c.getNextState());
      }
      newStates.add(new ArrayList<>());
    }

    return newStates;
  }

  protected abstract void createGrid();

  public Grid getGrid() {
    return myGrid;
  }

  public int[] getGridSize() {
    return new int[]{Integer.parseInt(simInfo.get("width")),
        Integer.parseInt(simInfo.get("height"))};
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
  public double getSpeed() {
    return simulationSpeed;
  }

  /**
   * Sets the speed (generations per second) of the simulation
   *
   * @param newSpeed, the new speed the simulation should operate at
   */
  public void setSpeed(double newSpeed) {
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

