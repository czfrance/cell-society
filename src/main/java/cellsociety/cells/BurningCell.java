package cellsociety.cells;

/**
 * author: Cynthia France
 */
public class BurningCell extends Cell{

  public static final int TREE = 0;
  public static final int BURNING = 1;
  public static final int DEAD = 2;

  private double probCatch;

  /**
   *
   * @param x the x location of the cell
   * @param y the y location of the cell
   * @param initState the cell's initial state
   * @param probability the probability a tree will catch on file
   */
  public BurningCell(int x, int y, int initState, double probability) {
    super(x, y, initState);
    probCatch = probability;
  }

  /**
   *
   * @return the next state of the cell
   */
  @Override
  public int getNextState() {
    switch (currentState) {
      case TREE-> {return treeNextState();}
      default -> {return DEAD;}
    }
  }

  private boolean inDanger() {
    for (Cell c : myNeighbors) {
      if (c.getMyCurrentState() == BURNING) {
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
