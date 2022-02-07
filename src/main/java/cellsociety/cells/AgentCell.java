package cellsociety.cells;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class AgentCell extends Cell {

  private int sugarMetabolism;
  private int mySugar;
  private int sugarStealRate;
  private boolean isDead;
  private final Random DICE = new Random();

  public AgentCell(int col, int row, int initState, int takeRate, int metabolism) {
    super(col, row, initState);
    sugarStealRate = takeRate;
    sugarMetabolism = metabolism;
  }

  @Override
  public int getNextState() {

    update();

    return 0;
  }

  public void update(List<Cell> neighbors) {
    if (isDead)
      return;
    mySugar -= sugarMetabolism;
    if (mySugar < 0) {
      isDead = true;
      return;
    }
    move(neighbors);
  }

  private void move(List<Cell> neighbors) {
    List<Cell> potentialMoves = new ArrayList<>();
    for (Cell cell : neighbors) {
      if (!cell.hasAgent()) potentialMoves.add(cell);
    }

    if (potentialMoves.size() != 0) {
      Cell selected = potentialMoves.get(DICE.nextInt(potentialMoves.size()));
      column = selected.getColumn();
      row = selected.getRow();
    }
  }
  public int takeSugar(int sugarSupply) {
    if (sugarSupply >= sugarStealRate) mySugar += sugarStealRate;
    else if (sugarSupply > 0) mySugar += sugarSupply;

    return sugarStealRate;
  }

  public boolean isAlive() {return !isDead;}

}
