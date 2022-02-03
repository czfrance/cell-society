package cellsociety.cells;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class FishCell extends Cell {

  private final int INITIAL_REPROTIMER;
  private int reproductionTimer;

  private final int NUTRITION_VALUE;
  private final Random DICE = new Random();
  private boolean isDead;
  private boolean isReproducing;

  public FishCell(int x, int y, int initState, int reproductionTimer, int nutritionValue) {
    super(x, y, initState);

    INITIAL_REPROTIMER = reproductionTimer;
    this.reproductionTimer = reproductionTimer;
    NUTRITION_VALUE = nutritionValue;

    ID = FISH;
    myState = FISH;

    isDead = false;
  }

  public void move(int width, int height, List<List<Cell>> grid) {
    initNeighbors(width, height, grid);
    List<Cell> potentialMove = getPotentialMove();
    if (potentialMove.size() != 0) {
      Cell selectedMove = potentialMove.get(DICE.nextInt(potentialMove.size()));
      COLUMN = selectedMove.getColumn();
      ROW = selectedMove.getRow();
    }

    reproductionTimer--;
    if(reproductionTimer == 0) {
      isReproducing = true;
      reproduction();
      reproductionTimer = INITIAL_REPROTIMER;
    } else isReproducing = false;
  }

  private List<Cell> getPotentialMove() {
    List<Cell> potentialMove = new ArrayList<>();
    for (Cell c : myNeighbors) {
      if (c.getID() == EMPTY) {
        potentialMove.add(c);
      }
    }
    return potentialMove;
  }

  public int getNutrition() {return NUTRITION_VALUE;}

  public FishCell reproduction() {

    return new FishCell(getColumn(), getRow(), FISH, INITIAL_REPROTIMER, NUTRITION_VALUE);
  }

  @Override
  public int getNextState() {
    return myState;
  }

  public void death() {
    isDead = true;
  }

  public boolean isDead() {return isDead;}

  public boolean isReproducing() {return isReproducing;}
}
