package cellsociety.cells;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import cellsociety.models.Grid;

public class FishCell extends Cell {

  private final int INITIAL_REPRODUCTION_TIMER;
  private int reproductionTimer;

  private final int NUTRITION_VALUE;
  private final Random DICE = new Random();
  private boolean isDead = false;
  private boolean isReproducing = false;

  public FishCell (FishCell cell) {
    super(cell.getColumn(), cell.getRow(), cell.getCurrentState());
    reproductionTimer = cell.reproductionTimer;
    isDead = cell.isDead;
    isReproducing = cell.isReproducing;

    INITIAL_REPRODUCTION_TIMER = cell.INITIAL_REPRODUCTION_TIMER;
    NUTRITION_VALUE = cell.NUTRITION_VALUE;

    currentState = WaTorCell.FISH;
    currentState = WaTorCell.FISH;
  }

  public FishCell(int x, int y, int initState, int reproductionTimer, int nutritionValue) {
    super(x, y, initState);

    INITIAL_REPRODUCTION_TIMER = reproductionTimer;
    this.reproductionTimer = reproductionTimer;
    NUTRITION_VALUE = nutritionValue;

    currentState = WaTorCell.FISH;
    currentState = WaTorCell.FISH;
  }

  public void update(int width, int height, Grid grid) {
    move(width, height, grid);
  }

  private void move(int width, int height, Grid grid) {
    List<Cell> potentialMove = getPotentialMove();

    if (potentialMove.size() != 0) {
      Cell selectedMove = potentialMove.get(DICE.nextInt(potentialMove.size()));
      COLUMN = selectedMove.getColumn();
      ROW = selectedMove.getRow();
      isReproducing = reproductionTimer <= 0;
    }

    reproductionTimer--;

  }

  private List<Cell> getPotentialMove() {
    List<Cell> potentialMove = new ArrayList<>();
    for (Cell c : myNeighbors) {
      if (c.getCurrentState() == WaTorCell.EMPTY && !c.isBlocked()) {
        potentialMove.add(c);
      }
    }
    return potentialMove;
  }

  public int getNutrition() {return NUTRITION_VALUE;}

  public void death() {
    isDead = true;
  }

  public boolean isDead() {return isDead;}

  public boolean isReproducing() {return isReproducing;}


  @Override
  public int getNextState() {
    return 0;
  }
  public void resetReproductionTimer() {
    reproductionTimer = INITIAL_REPRODUCTION_TIMER;
    isReproducing = false;
  }

}
