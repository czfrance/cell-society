package cellsociety.models;

import cellsociety.cells.Cell;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * author: Cynthia France/Jose Santillan
 */
public abstract class SimulationModel {

  public static final List<String> DATA_FIELDS = List.of("simulationType", "title", "author",
      "description", "width", "height", "config", "speed", "neighborType", "satisfied", "probCatch", "turnsToBreedFish",
      "turnsToBreedShark", "sharkStarve");

  public static final int SIMULATION_TYPE = 0;
  public static final int TITLE = 1;
  public static final int AUTHOR = 2;
  public static final int DESCRIPTION = 3;
  public static final int WIDTH_FIELD = 4;
  public static final int HEIGHT_FIELD = 5;
  public static final int CONFIG = 6;
  public static final int FINITE = 0;
  public static final int TOROIDAL = 1;
  public static final int TRIANGULAR_TOROIDAL = 2;
  public static final int HEXAGON = 3;

  protected Map<String, String> simInfo;
  protected String simType;

  public static final String HEIGHT_INFO = "height";
  public static final String WIDTH_INFO = "width";
  public static final String NEIGHBORTYPE_INFO = "neighborType";
  public static final String SATISFIED_INFO = "satisfied";
  public static final String PROBCATCH_INFO = "probCatch";
  public static final String FISHTURNS_INFO = "turnsToBreedFish";
  public static final String SHARKTURNS_INFO = "turnsToBreedShark";
  public static final String SHARKSTARVE_INFO = "sharkStarve";
  public static final String SPEED = "speed";

  protected Grid myGrid;

  public final int WIDTH;
  public final int HEIGHT;
  public static int NEIGHBORTYPE = TOROIDAL;
  public final double SATISFIED;
  public final double PROBCATCH;
  public final int FISHTURNS;
  public final int SHARKTURNS;
  public final int SHARKSTARVE;

  private double simulationSpeed;

  private ResourceBundle myResources;
  public static final String DEFAULT_RESOURCE_PACKAGE = "/";

  /**
   *
   * @param dataValues values from the xml file
   * @param language language to use
   */
  public SimulationModel(Map<String, String> dataValues, String language) {

    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    simInfo = dataValues;
    WIDTH = Integer.parseInt(simInfo.get(WIDTH_INFO));
    HEIGHT = Integer.parseInt(simInfo.get(HEIGHT_INFO));

    NEIGHBORTYPE = getNeighborType();

    if (!simInfo.get(SATISFIED_INFO).equals("")) SATISFIED = Double.parseDouble(simInfo.get(SATISFIED_INFO));
    else SATISFIED = 0;

    if (!simInfo.get(PROBCATCH_INFO).equals("")) PROBCATCH = Double.parseDouble(simInfo.get(PROBCATCH_INFO));
    else PROBCATCH = 0;

    if (!simInfo.get(FISHTURNS_INFO).equals("")) FISHTURNS = Integer.parseInt(simInfo.get(FISHTURNS_INFO));
    else FISHTURNS = 0;

    if (!simInfo.get(SHARKTURNS_INFO).equals("")) SHARKTURNS = Integer.parseInt(simInfo.get(SHARKTURNS_INFO));
    else SHARKTURNS = 0;

    if (!simInfo.get(SHARKSTARVE_INFO).equals("")) SHARKSTARVE = Integer.parseInt(simInfo.get(SHARKSTARVE_INFO));
    else SHARKSTARVE = 0;

    simulationSpeed = Double.parseDouble(simInfo.get(SPEED));

    myGrid = new Grid(WIDTH, HEIGHT);

    createGrid();
    initGrid();
  }

  /**
   *
   * @return simulation information
   */
  public Map<String, String> getSimInfo() {
    return simInfo;
  }

  /**
   * updates the grid to new states
   */
  public void updateGrid() {
    List<List<Integer>> newStates = getCellNextStates();
    myGrid.initNeighbors(SimulationModel.NEIGHBORTYPE);
    myGrid.updateGrid(newStates);
    System.out.println(myGrid);
  }

  protected List<List<Integer>> getCellNextStates() {
    //
    List<List<Integer>> newStates = new ArrayList<>();

    for (int row = 0; row < myGrid.size(); row++) {
      newStates.add(new ArrayList<>());
      for (Cell c : myGrid.getRow(row)) {
        int k  = c.getNextState();
        newStates.get(row).add(k);
      }

    }

    return newStates;
  }

  private int getNeighborType() {
    //
    String type = simInfo.get(NEIGHBORTYPE_INFO);
    switch (type) {
      case "finite" -> {return FINITE;}
      case "toroidal" -> {return TOROIDAL;}
      case "triangular toroidal" -> {return TRIANGULAR_TOROIDAL;}
      case "hexagon" -> {return HEXAGON;}
      default -> {return TOROIDAL;}
    }
  }

  protected abstract void createGrid();

  /**
   * This method gets the grid the SimulationModel current holds
   * @return the grid of cells
   */
  public Grid getGrid() {
    return myGrid;
  }

  /**
   * This method returns the dimensions of the Grid currently being held by the SimulationModel
   * @return An array width the 0th index being the width and 1st index being the height
   */
  public int[] getGridSize() {
    return new int[]{Integer.parseInt(simInfo.get("width")),
        Integer.parseInt(simInfo.get("height"))};
  }

  private void initGrid() {
    //Initializes the Grid for this simulation model
    for (List<Cell> l : myGrid) {
      for (Cell c : l) {
        c.initNeighbors(NEIGHBORTYPE, WIDTH, HEIGHT, myGrid);
      }
    }
  }

  /**
   * This method returns the Resource Bundle currently being used by the SimulationModel
   * @return the Resource Bundle
   */
  public ResourceBundle getMyResources() {
    return myResources;
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
        String.format("  %s = '%d',", DATA_FIELDS.get(WIDTH_FIELD), Integer.parseInt(simInfo.get(DATA_FIELDS.get(WIDTH_FIELD)))),
        String.format("  %s = '%d',", DATA_FIELDS.get(HEIGHT_FIELD), Integer.parseInt(simInfo.get(DATA_FIELDS.get(HEIGHT_FIELD)))),
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

}

