package cellsociety.cells;

public class FishCell extends Cell {

  private final int REPRODUCTION_TIMER;
  private final int NUTRITION_VALUE;

  public FishCell(int x, int y, int initState, int reproductionTimer, int nutritionValue) {
    super(x, y, initState);

    REPRODUCTION_TIMER = reproductionTimer;
    NUTRITION_VALUE = nutritionValue;

  }


}
