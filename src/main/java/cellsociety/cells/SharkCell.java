package cellsociety.cells;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import cellsociety.models.Grid;

public class SharkCell extends Cell {

  private int myHealth;
  private int myState;
  private int reproductionTimer;
  private final int INITIAL_HEALTH;
  private final int INITAL_REPROTIMER;
  private boolean isDead;
  private Random DICE = new Random();
  private boolean isReproducing;

  public SharkCell(SharkCell cell) {
    super(cell.getColumn(), cell.getRow(), cell.getCurrentState());

    myHealth = cell.myHealth;
    currentState = WaTorCell.SHARK;
    INITAL_REPROTIMER = cell.INITAL_REPROTIMER;
    INITIAL_HEALTH = cell.INITIAL_HEALTH;
    myState =  WaTorCell.SHARK;
    isDead = cell.isDead();

  }
  public SharkCell(int x, int y, int initState, int health, int reproductionTimer) {

    super(x, y, initState);
    myHealth = health;
    currentState =  WaTorCell.SHARK;
    INITAL_REPROTIMER = reproductionTimer;
    INITIAL_HEALTH = health;
    myState =  WaTorCell.SHARK;
    isDead = false;
    this.reproductionTimer = reproductionTimer;
  }

  @Override
  public void update(int width, int height, Grid grid) {
    initFiniteNeighbors(width, height, grid);

    List<Cell> fishList = searchForFish();

    Cell selectedMove;
    reproductionTimer--;

    if (fishList.size() == 0) {
      List<Cell> potentialMove = getPotentialMove();
      if (potentialMove.size() != 0) {
        selectedMove = potentialMove.get(DICE.nextInt(potentialMove.size()));
        COLUMN = selectedMove.getColumn();
        ROW = selectedMove.getRow();
        checkForReproduction();
      }

      myHealth--;
    } else {
      selectedMove = fishList.get(DICE.nextInt(fishList.size()));
      selectedMove.getFish().death();
      myHealth += selectedMove.getFish().getNutrition();
      COLUMN = selectedMove.getColumn();
      ROW = selectedMove.getRow();
      checkForReproduction();
    }

    if (myHealth == 0) {
      death();
    }

  }


  private List<Cell> searchForFish() {
    List<Cell> fishList = new ArrayList<>();
    for (Cell c : myNeighbors) {
      int k = c.getCurrentState();
      if (k == WaTorCell.FISH && !c.isBlocked()) fishList.add(c);
    }
    return fishList;
  }

  private List<Cell> getPotentialMove() {
    List<Cell> potentialMove = new ArrayList<>();
    for (Cell c : myNeighbors) {
      if (c.getCurrentState() == WaTorCell.EMPTY && !c.isBlocked()) potentialMove.add(c);
    }
    return potentialMove;
  }

  public void death() {
    isDead = true;
  }

  @Override
  public int getNextState() {
    return myState;
  }

  public boolean isDead() {return isDead;}

  public boolean isReproducing() {return isReproducing;}

  private void checkForReproduction() {
    isReproducing = reproductionTimer < 0;
  }
  public void resetReproductionTimer() {
    reproductionTimer = INITAL_REPROTIMER;
    isReproducing = false;
  }
}
