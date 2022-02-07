package cellsociety.cells;

import java.util.Random;

public class SugarCell extends Cell {

  private final int spawnAgent = 1;
  private final int maxSugar;
  private int mySugar;
  private int sugarGrowBackRate;

  private AgentCell agent;
  private final Random DICE = new Random();

  public SugarCell(int col, int row, int initState, int sugarGrowBackRate) {
    super(col, row, initState);
    maxSugar = (initState + 1) * 5;
    mySugar = maxSugar;
    this.sugarGrowBackRate = sugarGrowBackRate;
  }
  @Override
  public int getNextState() {

    update();

    return currentState;
  }

  public void update() {
    if (agent != null) agent.update(myNeighbors);
    replenishSugar();
    agent = null;
  }

  private void replenishSugar() {
    mySugar += sugarGrowBackRate;
    if (mySugar > maxSugar) mySugar = maxSugar;
  }

  public void setAgent(Cell agent) {
    this.agent = (AgentCell) agent;
    mySugar -= agent.takeSugar(mySugar);

  }
  public boolean hasAgent() {return agent != null;}

  public Cell getAgent() {return agent;}
}
