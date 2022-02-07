package cellsociety.models;

import cellsociety.cells.SugarCell;
import java.util.Map;
import java.util.Random;
import cellsociety.cells.Cell;
import java.util.List;
import java.util.ArrayList;
import cellsociety.cells.AgentCell;

public class SugarModel extends SimulationModel{

  private SugarGrid mySugarGrid;

  private final int LOWSUGAR = 0;
  private final int LOWMIDSUGAR = 1;
  private final int MIDSUGAR = 2;
  private final int HIGHMIDSUGAR = 3;
  private final int HIGHSUGAR = 4;

  private final int AGENT = 5;

  private final int numAgents = 30;

  private Random DICE;

  public SugarModel(Map<String, String> dataValues, String language) {
    super(dataValues, language);
  }


  @Override
  protected void createGrid() {

    mySugarGrid = new SugarGrid(WIDTH, HEIGHT);
    DICE = new Random();

    mySugarGrid.addRow();
    int rowNum = 0;
    int colNum = 0;
    for (int i = 0; i < simInfo.get(DATA_FIELDS.get(CONFIG)).toCharArray().length; i++) {
      char c = simInfo.get(DATA_FIELDS.get(CONFIG)).toCharArray()[i];
      switch (c) {
        case '.' -> {mySugarGrid.addRow();rowNum++;colNum = 0;}
        case '0' -> {mySugarGrid.getRow(rowNum).add(new SugarCell(colNum, rowNum, LOWSUGAR, 2));colNum++;}
        case '1' -> {mySugarGrid.getRow(rowNum).add(new SugarCell(colNum, rowNum, LOWMIDSUGAR, 2));colNum++;}
        case '2' -> {mySugarGrid.getRow(rowNum).add(new SugarCell(colNum, rowNum, MIDSUGAR, 2));colNum++;}
        case '3' -> {mySugarGrid.getRow(rowNum).add(new SugarCell(colNum, rowNum, HIGHMIDSUGAR, 2));colNum++;}
        case '4' -> {mySugarGrid.getRow(rowNum).add(new SugarCell(colNum, rowNum, HIGHSUGAR, 2));colNum++;}
        default -> {}
      }
    }
    List<Cell> agentList = new ArrayList<>();
    for (int i = 0; i < numAgents; i++) {
      AgentCell cell = new AgentCell(DICE.nextInt(WIDTH), DICE.nextInt(HEIGHT), AGENT, DICE.nextInt(15), DICE.nextInt(10));
      agentList.add(cell);
      mySugarGrid.getRow(cell.getRow()).get(cell.getColumn()).setAgent(cell);

    }
    mySugarGrid.setMyAgents(agentList);
  }

  @Override
  public void updateGrid() {

    mySugarGrid.updateGrid(null);
  }

  @Override
  public SugarGrid getGrid() {return mySugarGrid;}
}
