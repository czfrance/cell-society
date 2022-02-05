package cellsociety.cells;

import cellsociety.models.Grid;

public class WaTorCell extends Cell{

  public static final int EMPTY = 0;
  public static final int FISH = 1;
  public static final int SHARK = 2;

  private int column;
  private int row;

  private SharkCell shark;
  private FishCell fish;
  private EmptyWaTorCell empty;

  private int currentState;

  private WaTorCell previousState;

  private boolean tempBlock;

  public WaTorCell(WaTorCell cell) {
    super(cell.getColumn(), cell.getRow(), cell.getState());
    column = cell.getColumn();
    row = cell.getRow();
    shark = new SharkCell(cell.getShark());
    fish = new  FishCell(cell.getFish());
    empty = new EmptyWaTorCell(column, row, EMPTY);
    currentState = cell.getState();
  }

  public WaTorCell(int x, int y, int initState){
    super(x, y, initState);
  }

  public WaTorCell(int x, int y, int code, int reproductionTimer, int nutritionValue, int health) {
    super(x, y, code);

    empty = new EmptyWaTorCell(x, y, code);
    fish = new FishCell(x, y, code, reproductionTimer, nutritionValue);
    shark = new SharkCell(x, y, code, health, reproductionTimer);

    currentState = code;

    this.column = x;
    this.row = y;
  }

  @Override
  public void update(int width, int height, Grid grid) {
    if (currentState == EMPTY) return;
    previousState = new WaTorCell(this);
    getCurrentObject().update(width, height, grid);

    if (getCurrentObject().isDead()) currentState = EMPTY;

    column = getCurrentObject().getColumn();
    row = getCurrentObject().getRow();
  }

  public Cell reupdate() {
    return previousState;
  }
  @Override
  public int getNextState() {
    return 0;
  }
  @Override
  public int getState() {
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

  public void setEmpty(){
    currentState = EMPTY;
  }

  public FishCell getFish() {
    return fish;
  }

  public SharkCell getShark() {return shark;}

  @Override
  public String toString() {return String.format("%d ", currentState);}

  @Override
  public int getColumn() {return column;}

  @Override
  public int getRow() {return row;}

  public boolean isReproducing() {
    return getCurrentObject().isReproducing();
  }

  public void block() {tempBlock = true;}
  public void unblock() {tempBlock = false;}
  public boolean isBlocked() {return tempBlock;}
  public void setNew(int state,int repoTimer, int nutVal) {
    if (state == FISH) setFish(new FishCell(getColumn(), getRow(), state, repoTimer, nutVal));
  }
}
