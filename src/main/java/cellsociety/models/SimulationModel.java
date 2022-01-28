package cellsociety.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimulationModel {
  // name in data file that will indicate it represents data for this type of object
  //public static final String DATA_TYPE = "Game";
  // field names expected to appear in data file holding values for this object
  public static final List<String> DATA_FIELDS = List.of("simulation type", "title", "author", "description",
      "width", "height", "config");
  public Map<String, String> information;
  private List<List<Integer>> grid = new ArrayList<>();

  public SimulationModel(Map<String, String> dataValues) {
    information = dataValues;
    createGrid();
  }

  private void createGrid() {
    grid.add(new ArrayList<Integer>());
    int rowNum = 0;
    for (char c : information.get(DATA_FIELDS.get(6)).toCharArray()) {
      switch (c) {
        case '\n' -> {grid.add(new ArrayList<>()); rowNum++;}
        //case ' ' -> {}
        case '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' -> grid.get(rowNum).add(Character.getNumericValue(c));
        default -> {}
      }

    }
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
        String.format("%s = [", information.get(DATA_FIELDS.get(0))),
        String.format("  %s = '%s',", DATA_FIELDS.get(1), information.get(DATA_FIELDS.get(1))),
        String.format("  %s = '%s',", DATA_FIELDS.get(2), information.get(DATA_FIELDS.get(2))),
        String.format("  %s = '%s',", DATA_FIELDS.get(3), information.get(DATA_FIELDS.get(3))),
        String.format("  %s = '%d',", DATA_FIELDS.get(4), Integer.parseInt(information.get(DATA_FIELDS.get(4)))),
        String.format("  %s = '%d',", DATA_FIELDS.get(5), Integer.parseInt(information.get(DATA_FIELDS.get(5)))),
        String.format("  %s = '%s',", DATA_FIELDS.get(6), information.get(DATA_FIELDS.get(6))),
        "]");
  }
}
