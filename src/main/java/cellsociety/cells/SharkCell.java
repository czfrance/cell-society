package cellsociety.cells;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
 * author: Cynthia France
 */
public class SharkCell extends Cell {

  private int myHealth;
  private boolean isReproducing;
  private int breedTurns;
  private int turnsAlive;

  /**
   *
   * @param x the x location of the cell
   * @param y the y location of the cell
   * @param initState cell's initial state
   * @param sharkStarve initial health/number of turns until death
   * @param sharkBreed number of turns until breed
   */
  public SharkCell(int x, int y, int initState, int sharkStarve, int sharkBreed) {
    super(x, y, initState);
    myHealth = sharkStarve;
    currentState =  WaTorCell.SHARK;
    turnsAlive = 0;
    breedTurns = sharkBreed;
  }

  /**
   *
   * @param x the x location of the cell
   * @param y the y location of the cell
   * @param initState cell's initial state
   * @param sharkStarve initial health/number of turns until death
   * @param sharkBreed number of turns until breed
   * @param currHealth shark's current health
   * @param currAlive shark's number of lives lived
   */
  public SharkCell(int x, int y, int initState, int sharkStarve, int sharkBreed, int currHealth,
      int currAlive) {
    super(x, y, initState);
    myHealth = currHealth;
    currentState =  WaTorCell.SHARK;
    turnsAlive = currAlive;
    breedTurns = sharkBreed;
  }

  /**
   *
   * @return cell's next state
   */
  @Override
  public int getNextState() {
    boolean success = eatFish();
    turnsAlive++;
    isReproducing = checkReproduce();
    if (success) {
      myHealth++;
      return currentState;
    } else {
      myHealth--;
      if (myHealth == 0) {
        return 0;
      }
      return -1 * currentState;
    }
  }

  /**
   *
   * @return if the shark is ready to reproduce
   */
  public boolean isReproducing() {return isReproducing;}

  /**
   *
   * @return turnsAlive
   */
  @Override
  public int getTurnsAlive() {
    return turnsAlive;
  }

  /**
   *
   * @return shark's health
   */
  @Override
  public int getHealth() {
    return myHealth;
  }

  private boolean checkReproduce() {
    if (turnsAlive % breedTurns == 0) {
      return true;
    }
    return false;
  }

  private boolean eatFish() {
    List<Cell> fishes = getFish();
    if (fishes.size() == 0) {
      return false;
    }

    Cell fish = selectFish(fishes);
    fish.death();
    return true;
  }

  public List<Cell> getFish() {
    List<Cell> fish = new ArrayList<>();
    for (Cell c : myNeighbors) {
      if (c.getMyCurrentState() == WaTorCell.FISH) {
        fish.add(c);
      }
    }
    return fish;
  }

  private Cell selectFish (List < Cell > fishes) {
    Random rand = new Random();
    int fish = rand.nextInt(fishes.size());
    return fishes.get(fish);
  }
}

