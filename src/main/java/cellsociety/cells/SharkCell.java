package cellsociety.cells;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import cellsociety.models.Grid;

public class SharkCell extends Cell {

  private int myHealth;
  private int reproductionTimer;
  private final int INITIAL_HEALTH;
  //private final int INITAL_REPROTIMER;
  private boolean isDead;
  private Random DICE = new Random();
  private boolean isReproducing;
  private int breedTurns;
  private int turnsAlive;

//  public SharkCell(SharkCell cell) {
//    super(cell.getColumn(), cell.getRow(), cell.getCurrentState());
//
//    myHealth = cell.myHealth;
//    currentState = WaTorCell.SHARK;
//    INITAL_REPROTIMER = cell.INITAL_REPROTIMER;
//    INITIAL_HEALTH = cell.INITIAL_HEALTH;
//    myState =  WaTorCell.SHARK;
//    isDead = cell.isDead();
//
//  }

  public SharkCell(int x, int y, int initState, int sharkStarve, int sharkBreed) {
    //THIS ONE
    super(x, y, initState);
    myHealth = sharkStarve;
    currentState = initState;
    //INITAL_REPROTIMER = sharkBreed;
    INITIAL_HEALTH = sharkStarve;
    currentState =  WaTorCell.SHARK;
    //isDead = false;
    turnsAlive = 0;
    breedTurns = sharkBreed;
  }
  public SharkCell(int x, int y, int initState, int sharkStarve, int sharkBreed, int currHealth, int currAlive) {
    //THIS ONE
    super(x, y, initState);
    myHealth = currHealth;
    currentState = initState;
    //INITAL_REPROTIMER = sharkBreed;
    INITIAL_HEALTH = sharkStarve;
    currentState =  WaTorCell.SHARK;
    //isDead = false;
    turnsAlive = currAlive;
    breedTurns = sharkBreed;
  }

//  public SharkCell(int x, int y, int initState, int sharkStarve, int sharkBreed) {
//    //THIS ONE
//    super(x, y, initState);
//    myHealth = sharkStarve;
//    currentState =  WaTorCell.SHARK;
//    INITAL_REPROTIMER = sharkBreed;
//    INITIAL_HEALTH = sharkStarve;
//    myState =  WaTorCell.SHARK;
//    isDead = false;
//    this.reproductionTimer = sharkBreed;
//  }

//  @Override
//  public void update(int width, int height, Grid grid) {
//
//    List<Cell> fishList = searchForFish();
//
//    Cell selectedMove;
//    reproductionTimer--;
//
//    if (fishList.size() == 0) {
//      List<Cell> potentialMove = getPotentialMove();
//      if (potentialMove.size() != 0) {
//        selectedMove = potentialMove.get(DICE.nextInt(potentialMove.size()));
//        COLUMN = selectedMove.getColumn();
//        row = selectedMove.getRow();
//        checkForReproduction();
//      }
//
//      myHealth--;
//    } else {
//      selectedMove = fishList.get(DICE.nextInt(fishList.size()));
//      selectedMove.getFish().death();
//      myHealth += selectedMove.getFish().getNutrition();
//      COLUMN = selectedMove.getColumn();
//      row = selectedMove.getRow();
//      checkForReproduction();
//    }
//
//    if (myHealth == 0) {
//      death();
//    }
//
//  }

  @Override
  public int getNextState() {
    boolean success = eatFish();
    turnsAlive++;
    isReproducing = checkReproduce();
    if (success) {
      myHealth++;
      return currentState;
    }
    else {
      myHealth--;
      if (myHealth == 0) {
        return 0;
      }
      return -1*currentState;
    }
  }

  private boolean checkReproduce() {
    if (turnsAlive % breedTurns == 0) {
      return true;
    }
    return false;
  }

  public boolean isReproducing() {return isReproducing;}

  private boolean eatFish() {
    List<Cell> fishes = getFish();
    if (fishes.size() == 0) {
      return false;
    }

    Cell fish = selectFish(fishes);
    fish.death();
    return true;
  }

  private List<Cell> getFish() {
    List<Cell> fish = new ArrayList<>();
    for (Cell c : myNeighbors) {
      if (c.getMyCurrentState() == WaTorCell.FISH) {
        fish.add(c);
      }
    }
    return fish;
  }

  private Cell selectFish(List<Cell> fishes) {
    Random rand = new Random();
    int fish = rand.nextInt(fishes.size());
    return fishes.get(fish);
  }


//  private List<Cell> searchForFish() {
//    List<Cell> fishList = new ArrayList<>();
//    for (Cell c : myNeighbors) {
//      int k = c.getCurrentState();
//      if (k == WaTorCell.FISH && !c.isBlocked()) fishList.add(c);
//    }
//    return fishList;
//  }

//  private List<Cell> getPotentialMove() {
//    List<Cell> potentialMove = new ArrayList<>();
//    for (Cell c : myNeighbors) {
//      if (c.getCurrentState() == WaTorCell.EMPTY && !c.isBlocked()) potentialMove.add(c);
//    }
//    return potentialMove;
//  }

//  @Override
//  public void death() {
//    isDead = true;
//  }

//  public boolean isDead() {return isDead;}

//
//  private void checkForReproduction() {
//    isReproducing = reproductionTimer < 0;
//  }
//  public void resetReproductionTimer() {
//    reproductionTimer = INITAL_REPROTIMER;
//    isReproducing = false;
//  }
}
