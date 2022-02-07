package cellsociety.cells;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import cellsociety.models.Grid;

public class FishCell extends Cell {

  private int turnsAlive;
  private int breedTurns;
  private boolean isReproducing = false;

  public FishCell(int x, int y, int initState, int fishBreed) {
    super(x, y, initState);
    breedTurns = fishBreed;
    turnsAlive = 0;
    currentState = initState;
  }

  public FishCell(int x, int y, int initState, int fishBreed, int currAlive) {
    super(x, y, initState);

    breedTurns = fishBreed;
    turnsAlive = currAlive;
    currentState = initState;
  }

  @Override
  public int getNextState() {
    turnsAlive = turnsAlive + 1;
    System.out.print(turnsAlive);
    isReproducing = checkReproduce();
    return -1*currentState;
  }

  //@Override
  public int getTurnsAlive() {
    System.out.println("return: " + turnsAlive);
    return turnsAlive;
  }

  public int getHealth() {
    System.out.println("return: " + turnsAlive);
    return 0;
  }

  private boolean checkReproduce() {
    System.out.printf("alive: %d breed: %d\n", turnsAlive, breedTurns);
    if (turnsAlive % breedTurns == 0) {
      return true;
    }
    return false;
  }

  @Override
  protected void death() {
    currentState = 0;
  }

  public boolean isReproducing() {return isReproducing;}
}
