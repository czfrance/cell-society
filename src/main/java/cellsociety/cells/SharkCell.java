package cellsociety.cells;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

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

  public Cell move(int width, int height, List<List<Cell>> grid) {
    initNeighbors(width, height, grid);
    List<Cell> fishList = searchForFish(grid);
    Cell selectedMove;
    reproductionTimer--;
    if (reproductionTimer == 0) {
      isReproducing = true;
      reproductionTimer = INITAL_REPROTIMER;

    } else isReproducing = false;

    if (fishList.size() == 0) {
      List<Cell> potentialMove = getPotentialMove(grid);
      if (potentialMove.size() == 0) {
        myHealth--;
        return this;
      }
      selectedMove = potentialMove.get(DICE.nextInt(potentialMove.size()));
      myHealth--;
    } else {
      selectedMove = fishList.get(DICE.nextInt(fishList.size()));
      ((WaTorCell)selectedMove).getFish().death();
      myHealth += ((WaTorCell) selectedMove).getFish().getNutrition();
    }

    if (myHealth == 0) {
      death();
      return this;
    }



    COLUMN = selectedMove.getColumn();
    ROW = selectedMove.getRow();


    return this;
  }

  private List<Cell> searchForFish(List<List<Cell>> grid) {
    List<Cell> fishList = new ArrayList<>();
    for (Cell c : myNeighbors) {
      int k = ((WaTorCell) c).getCurrentState();
      if (k == FISH && !((WaTorCell) grid.get(c.getRow()).get(c.getColumn())).isBlocked()){
        fishList.add(c);
      }
    }
    return fishList;
  }

  private List<Cell> getPotentialMove(List<List<Cell>> grid) {
    List<Cell> potentialMove = new ArrayList<>();
    for (Cell c : myNeighbors) {
      if ((((WaTorCell) c).getCurrentState() == EMPTY && !((WaTorCell) grid.get(c.getRow()).get(c.getColumn())).isBlocked())) {
        potentialMove.add(c);
      }
    }
    return potentialMove;
  }

  public SharkCell reproduction() {

    return new SharkCell(getColumn(), getRow(), SHARK, INITIAL_HEALTH, INITAL_REPROTIMER);
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
}
