package cellsociety.cells;

import java.util.List;

public class WaTorCell extends Cell{

  public final int EMPTY = 0;
  public final int FISH = 1;
  public final int SHARK = 2;

  public SharkCell shark;
  public FishCell fish;
  public EmptyWaTorCell empty;

  private boolean blocked;
  private int currentState;

  public WaTorCell(int x, int y, int initState){
    super(x, y, initState);
  }

  public WaTorCell(int x, int y, int initState, int code, int reproductionTimer, int nutritionValue, int health) {
    super(x, y, initState);

    switch (code) {
      case EMPTY:
        empty = new EmptyWaTorCell(x, y, initState);
        break;
      case FISH:
        fish = new FishCell(x, y, initState, reproductionTimer, nutritionValue);
        break;
      case SHARK:
        shark = new SharkCell(x, y, initState, health, reproductionTimer);
    }
    currentState = code;
    ID = code;
    blocked = false;
  }

  @Override
  public int getNextState() {
    return 0;
  }

  protected void death() {

  }
  public int getCurrentState() {
    return currentState;
  }
  public Cell getCurrentObject() {
    if (currentState == FISH) return fish;
    if (currentState == SHARK) return shark;
    return empty;
  }

  public void setShark(SharkCell c){
    currentState = SHARK;
    shark = c;
  }

  public void setFish(FishCell f) {
    currentState = FISH;
    fish = f;
  }

  public void updateCell(int width, int height, List<List<Cell>> grid) {
    blocked = false;
    if (currentState == FISH) {fish.move(width, height, grid);}
    if (currentState == SHARK) {shark.move(width, height, grid);}
  }
  public void setEmpty(boolean block){
    currentState = EMPTY;
    blocked = block;
  }
  public FishCell getFish() {
    return fish;
  }
  public SharkCell getShark() {
    return shark;
  }
  @Override
  public String toString() {
    return String.format("%d ", currentState);
  }
  public boolean isBlocked() {return blocked;}
}
