package cellsociety.cells;

import java.util.List;
import java.util.*;

public class FishCell extends Cell {

  private final int INTIAL_REPROTIMER;
  private int reproductionTimer;
  private final int NUTRITION_VALUE;
  private final Random DICE = new Random();

  public FishCell(int x, int y, int initState, int reproductionTimer, int nutritionValue) {
    super(x, y, initState);

    INTIAL_REPROTIMER = reproductionTimer;
    this.reproductionTimer = reproductionTimer;
    NUTRITION_VALUE = nutritionValue;

    ID = FISH;
  }

  public void move(int width, int height, List<List<Cell>> grid) {
    initNeighbors(width, height, grid);
    List<Cell> potentialMove = getPotentialMove();
    Cell selectedMove = potentialMove.get(DICE.nextInt(potentialMove.size()));

    reproductionTimer--;
    if(reproductionTimer == 0) {
      reproduction();
      reproductionTimer = INTIAL_REPROTIMER;
    }

    COLUMN = selectedMove.getColumn();
    ROW = selectedMove.getRow();

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

  private void reproduction() {

  }

  @Override
  public int getNextState() {
    return myState;
  }
}
