package cellsociety.cells;

import cellsociety.models.Grid;
import java.util.ArrayList;
import java.util.List;

/**
 * author: Cynthia France, Jose Santillan
 */
public class WaTorCell extends Cell{

  public static final int EMPTY = 0;
  public static final int FISH = 1;
  public static final int SHARK = 2;

  private SharkCell shark;
  private FishCell fish;
  private EmptyWaTorCell empty;
  private int turnsAlive = 0;
  private int health = 0;


  /**
   *
   * @param x the x location of the cell
   * @param y the y location of the cell
   * @param initState cell's initial state
   * @param fishBreed turns for the fish to breed
   * @param sharkBreed turns for the shark to breed
   * @param sharkStarve turns for the shark to die
   */
  public WaTorCell(int x, int y, int initState, int fishBreed, int sharkBreed, int sharkStarve) {
    super(x, y, initState);
    shark = new SharkCell(x, y, SHARK, sharkBreed, sharkStarve);
    fish = new  FishCell(x, y, FISH, fishBreed);
    empty = new EmptyWaTorCell(x, y, EMPTY);
  }

  /**
   *
   * @param x the x location of the cell
   * @param y the y location of the cell
   * @param initState cell's initial state
   * @param fishBreed turns for fish to breed
   * @param sharkBreed turns for shark to breed
   * @param sharkStarve turns for shark to die
   * @param currHealth current health
   * @param currAlive number of turns lived
   */
  public WaTorCell(int x, int y, int initState, int fishBreed, int sharkBreed, int sharkStarve,
      int currHealth, int currAlive) {
    super(x, y, initState);
    shark = new SharkCell(x, y, SHARK, sharkBreed, sharkStarve, currHealth, currAlive);
    fish = new FishCell(x, y, FISH, fishBreed, currAlive);
    empty = new EmptyWaTorCell(x, y, EMPTY);
  }

  /**
   *
   * @return cell's next state
   */
  @Override
  public int getNextState() {
    return getCurrentObject().getNextState();
  }

  /**
   *
   * @return what the cell currently is (shark, fish, empty)
   */
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

  /**
   *
   * @return a list of the cells adjacent which are empty
   */
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

  /**
   *
   * @return if it's ready to reproduce
   */
  @Override
  public boolean isReproducing() {
    return getCurrentObject().isReproducing();
  }

  /**
   *
   * @return turns alive
   */
  @Override
  public int getTurnsAlive() {
    return turnsAlive;
  }

  /**
   *
   * @return health
   */
  @Override
  public int getHealth() {
    return health;
  }
}
