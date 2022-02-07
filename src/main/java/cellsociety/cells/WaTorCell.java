package cellsociety.cells;

import java.util.ArrayList;
import java.util.List;

public class WaTorCell extends Cell{

  public static final int EMPTY = 0;
  public static final int FISH = 1;
  public static final int SHARK = 2;

  private SharkCell shark;
  private FishCell fish;
  private EmptyWaTorCell empty;
  private int turnsAlive = 0;
  private int health = 0;


  public WaTorCell(int x, int y, int initState, int fishBreed, int sharkBreed, int sharkStarve) {
    super(x, y, initState);
    shark = new SharkCell(x, y, SHARK, sharkBreed, sharkStarve);
    fish = new  FishCell(x, y, FISH, fishBreed);
    empty = new EmptyWaTorCell(x, y, EMPTY);
  }

  public WaTorCell(int x, int y, int initState, int fishBreed, int sharkBreed, int sharkStarve,
      int currHealth, int currAlive) {
    super(x, y, initState);
    shark = new SharkCell(x, y, SHARK, sharkBreed, sharkStarve, currHealth, currAlive);
    fish = new FishCell(x, y, FISH, fishBreed, currAlive);
    empty = new EmptyWaTorCell(x, y, EMPTY);
  }

  @Override
  public int getNextState() {
    return getCurrentObject().getNextState();
  }

  public Cell getCurrentObject() {
    if (currentState == FISH) {
      turnsAlive = fish.getTurnsAlive();
      return fish;
    }
    else if (currentState == SHARK) {
      turnsAlive = shark.getTurnsAlive();
      health = shark.getHealth();
      return shark;
    }
    else return empty;
  }

  @Override
  public List<Cell> getEmptyAdjacentCells() {
    List<Cell> emptyCells = new ArrayList<>();
    for (Cell c : myNeighbors) {
      if (c.currentState == EMPTY) {
        emptyCells.add(c);
      }
    }
    return emptyCells;
  }

  @Override
  public String toString() {return String.format("%d ", currentState);}

  public boolean isReproducing() {
    return getCurrentObject().isReproducing();
  }

  @Override
  public int getTurnsAlive() {
    return turnsAlive;
  }

  @Override
  public int getHealth() {
    return health;
  }

//  @Override
//  public void setTurnsAlive(int alive) {
//    turnsAlive = alive;
//  }
//
//  @Override
//  public void setHealth(int myhealth) {
//    health = myhealth;
//  }
}
