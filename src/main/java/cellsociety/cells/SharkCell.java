package cellsociety.cells;

import java.util.*;
public class SharkCell extends Cell {

  private int myHealth;
  private int myState;
  private int reproductionTimer;
  private final int INITIAL_HEALTH;
  private final int INITAL_REPROTIMER;
  private Random DICE = new Random();

  public SharkCell(int x, int y, int initState, int health, int reproductionTimer) {
    super(x, y, initState);
    myHealth = health;
    ID = SHARK;
    INITAL_REPROTIMER = reproductionTimer;
    INITIAL_HEALTH = health;
  }

  public Cell move(int width, int height, List<List<Cell>> grid) {
    initNeighbors(width, height, grid);
    List<Cell> fishList = searchForFish();
    Cell selectedMove;

    if (fishList.size() == 0) {
      List<Cell> potentialMove = getPotentialMove();
      selectedMove = potentialMove.get(DICE.nextInt(potentialMove.size()));
      myHealth--;
    } else {
      selectedMove = fishList.get(DICE.nextInt(fishList.size()));
      myHealth += selectedMove.getNutrition();
    }

    if (myHealth == 0) return death();

    reproductionTimer--;
    if (reproductionTimer == 0) {
      reproduction();
      reproductionTimer = INITAL_REPROTIMER;
    }
    COLUMN = selectedMove.getColumn();
    ROW = selectedMove.getRow();


    return this;
  }

  private List<Cell> searchForFish() {
    List<Cell> fishList = new ArrayList<>();
    for (Cell c : myNeighbors) {
      if (c.getID() == FISH) {
        fishList.add(c);
      }
    }
    return fishList;
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

  public void reproduction() {

  }

  private Cell death() {
    return new EmptyCell(getColumn(), getRow(), 0);
  }

  @Override
  public int getNextState() {
    return myState;
  }
}
