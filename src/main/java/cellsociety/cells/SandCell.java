package cellsociety.cells;

public class SandCell extends Cell{
  //Constants
  public static final int AIR = 0;
  public static final int SAND = 1;
  public static final int METAL = 2;

  public static final int BOTTOM_NEIGHBOR = 5;
  public static final int TOP_NEIGHBOR = 1;

  private int floor;
  private int ceiling = 0;

  public SandCell(int column, int row, int initState, int floor) {
    super(column, row, initState);
    this.floor = floor;
  }

  @Override
  public int getNextState() {

    if (getRow() == ceiling) return nextCeiling();

    if (currentState == METAL) return METAL;
    if (currentState == SAND) return nextSand();
    if (currentState == AIR) return nextAir();

    return currentState;
  }

  private int nextCeiling() {
    if (currentState == AIR || isBelowAir()) return AIR;
    return SAND;
  }

  private int nextAir() {
    if (isAboveSand()) return SAND;
    return AIR;
  }

  private int nextSand() {
    if (getRow() == floor - 1 || isBelowSand()) return SAND;
    return AIR;
  }

  private boolean isBelowSand() {return myNeighbors.get(BOTTOM_NEIGHBOR).getCurrentState() == SAND;}

  private boolean isAboveSand() {return myNeighbors.get(TOP_NEIGHBOR).getCurrentState() == SAND;}

  private boolean isBelowAir() {return myNeighbors.get(BOTTOM_NEIGHBOR).getCurrentState() == AIR;}

  public String toString() {return "" + currentState + " ";}
}
