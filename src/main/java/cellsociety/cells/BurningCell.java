package cellsociety.cells;

import java.util.Random;

public class BurningCell extends Cell{

  public static final int TREE = 0;
  public static final int BURNING = 1;
  public static final int DEAD = 2;

  private double probCatch;

  public BurningCell(int x, int y, int initState, double probability) {
    super(x, y, initState);
    probCatch = probability;
  }

  @Override
  public int getNextState() {
    switch (myState) {
      case TREE-> {return treeNextState();}
      default -> {return DEAD;}
    }
  }

  private boolean inDanger() {
    for (Cell c : myNeighbors) {
      if (c.getMyState() == BURNING) {
        return true;
      }
    }
    return false;
  }

  private int treeNextState() {
    if (!inDanger()) {
      return TREE;
    }

    double prob = Math.random();
    if (prob < probCatch) {
      return BURNING;
    }
    return TREE;
  }
}
