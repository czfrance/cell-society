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

  public int getRepoTimer() {return reproductionTimer;}
  public SharkCell(int x, int y, int initState, int health, int reproductionTimer) {

    super(x, y, initState);
    myHealth = health;
    ID = SHARK;
    INITAL_REPROTIMER = reproductionTimer;
    INITIAL_HEALTH = health;
    myState = SHARK;
    isDead = false;
    this.reproductionTimer = reproductionTimer;
  }

  @Override
  public void update(int width, int height, Grid grid) {
    initNeighbors(width, height, grid);

    List<Cell> fishList = searchForFish();

    Cell selectedMove;
    reproductionTimer--;

    checkForReproduction();

    if (fishList.size() == 0) {
      List<Cell> potentialMove = getPotentialMove();
      if (potentialMove.size() != 0) selectedMove = potentialMove.get(DICE.nextInt(potentialMove.size()));
      myHealth--;
    } else {
      selectedMove = fishList.get(DICE.nextInt(fishList.size()));
      ((WaTorCell)selectedMove).getFish().death();
      myHealth += ((WaTorCell) selectedMove).getFish().getNutrition();
    }

    if (myHealth == 0) {
      death();
    }

  }


  private List<Cell> searchForFish() {
    List<Cell> fishList = new ArrayList<>();
    for (Cell c : myNeighbors) {
      int k = c.getState();
      if (k == FISH) fishList.add(c);
    }
    return fishList;
  }

  private List<Cell> getPotentialMove() {
    List<Cell> potentialMove = new ArrayList<>();
    for (Cell c : myNeighbors) {
      if (c.getState() == EMPTY) potentialMove.add(c);
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

  public int getHealth() {return myHealth;}

  public boolean isReproducing() {return isReproducing;}

  private void checkForReproduction() {
    if (reproductionTimer == 0) {
      isReproducing = true;
      reproductionTimer = INITAL_REPROTIMER;

    } else isReproducing = false;
  }
}
