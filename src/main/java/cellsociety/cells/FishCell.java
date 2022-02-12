package cellsociety.cells;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import cellsociety.models.Grid;

/**
 * author: Cynthia France
 */
public class FishCell extends Cell {

  private int turnsAlive;
  private int breedTurns;
  private boolean isReproducing = false;

  /**
   *
   * @param x the x location of the cell
   * @param y the y location of the cell
   * @param initState the cell's initial state
   * @param fishBreed the number of lives it must live before reproducing
   */
  public FishCell(int x, int y, int initState, int fishBreed) {
    super(x, y, initState);
    breedTurns = fishBreed;
    turnsAlive = 0;
    currentState = initState;
  }

  /**
   *
   * @param x the x location of the cell
   * @param y the y location of the cell
   * @param initState the cell's initial state
   * @param fishBreed the number of lives it must live before reproducing
   * @param currAlive the amount of lives it has lived already
   */
  public FishCell(int x, int y, int initState, int fishBreed, int currAlive) {
    super(x, y, initState);

    breedTurns = fishBreed;
    turnsAlive = currAlive;
    currentState = initState;
  }

  /**
   *
   * @return the next state of the cell
   */
  @Override
  public int getNextState() {
    turnsAlive = turnsAlive + 1;
    isReproducing = checkReproduce();
    return -1*currentState;
  }

  /**
   *
   * @return the number of lives the cell has lived
   */
  @Override
  public int getTurnsAlive() {
    return turnsAlive;
  }

  private boolean checkReproduce() {
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
