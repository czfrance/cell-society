package cellsociety.cells;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class SharkCell extends Cell {

  private int myHealth;
  private boolean isReproducing;
  private int breedTurns;
  private int turnsAlive;

  public SharkCell(int x, int y, int initState, int sharkStarve, int sharkBreed) {
    super(x, y, initState);
    myHealth = sharkStarve;
    currentState =  WaTorCell.SHARK;
    turnsAlive = 0;
    breedTurns = sharkBreed;
  }
  public SharkCell(int x, int y, int initState, int sharkStarve, int sharkBreed, int currHealth,
      int currAlive) {
    super(x, y, initState);
    myHealth = currHealth;
    currentState =  WaTorCell.SHARK;
    turnsAlive = currAlive;
    breedTurns = sharkBreed;
  }

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

  @Override
  public int getTurnsAlive() {
    return turnsAlive;
  }

  @Override
  public int getHealth() {
    return myHealth;
  }
}
