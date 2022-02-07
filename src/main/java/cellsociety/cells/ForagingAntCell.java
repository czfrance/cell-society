package cellsociety.cells;

public class ForagingAntCell extends Cell {

  private final int HOME = 0;
  private final int FOOD = 1;

  private int homePheromones;
  private int foodPheromones;

  private boolean hasFood;

  private int homeCol;
  private int homeRow;
  private int foodCol;
  private int foodRow;

  private int orientation;

  public ForagingAntCell(int col, int row, int initState, int[] foodHomeCoords) {
    super(col, row, initState);

    homeCol = foodHomeCoords[0];
    homeRow = foodHomeCoords[1];
    foodCol = foodHomeCoords[2];
    foodRow = foodHomeCoords[3];

  }

  @Override
  public int getNextState() {
    if (hasFood) return returnToNest();
    return findFood();
  }

  private int returnToNest() {
    int[] coords = maxHomeNeighbor(HOME);
    int maxCol = coords[0];
    int maxRow = coords[1];

    if (COLUMN == foodCol && ROW == foodRow) {

    }
    return -1;
  }

  private int findFood() {

    return -1;
  }

  private int[] maxHomeNeighbor(int code) {
    Cell ret = myNeighbors.get(0);
    for (Cell cell : myNeighbors) {
      if (code == HOME) ret = homeCompare(ret, cell);
      else if (code == FOOD) ret = foodCompare(ret, cell);
    }
    return new int[]{ret.getColumn(), ret.getRow()};
  }

  public int homePheromones() {return homePheromones;}

  public int foodPheromones() {return foodPheromones;}

  private Cell homeCompare(Cell a, Cell b) {
    return a.homePheromones() > b.homePheromones() ? b : a;
  }

  private Cell foodCompare(Cell a, Cell b) {
    return a.foodPheromones() < b.foodPheromones() ? b : a;
  }

}
