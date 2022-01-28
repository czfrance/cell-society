package cellsociety.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameOfLifeModel extends SimulationModel {

  private List<List<Integer>> grid = new ArrayList<>();
  public GameOfLifeModel(Map<String, String> dataValues) {
    super(dataValues);
    simType = "GameOfLife";
  }

}
