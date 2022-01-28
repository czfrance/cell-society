package cellsociety.models;

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
  protected List<List<Integer>> grid = new ArrayList<>();
  protected String simType;

  public SimulationModel(Map<String, String> dataValues) {
    simInfo = dataValues;
    createGrid();
  }

  protected void createGrid() {
    grid.add(new ArrayList<Integer>());
    int rowNum = 0;
    //c : simInfo.get(DATA_FIELDS.get(6)).toCharArray())
    //System.out.println(simInfo.get(DATA_FIELDS.get(6)).toCharArray().length);
    for (int i = 0; i < simInfo.get(DATA_FIELDS.get(6)).toCharArray().length; i++) {
      char c = simInfo.get(DATA_FIELDS.get(6)).toCharArray()[i];
      switch (c) {
        case '.' -> {grid.add(new ArrayList<Integer>()); rowNum++;}
        case '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' -> grid.get(rowNum).add(Character.getNumericValue(c));
        default -> {}
      }
    }
    System.out.println(grid.size());
  }

  public List<List<Integer>> getGrid() {
    return grid;
  }

  public int[] getGridSize() {
    return new int[] {Integer.parseInt(simInfo.get("width")), Integer.parseInt(simInfo.get("height"))};
  }

  public String getSimType() {
    return simType;
  }

  public void printGrid() {
    for (List l : grid) {
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
}
