package cellsociety.cells;

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

  public WaTorCell(WaTorCell cell) {
    super(cell.getColumn(), cell.getRow(), cell.getState());
    column = cell.getColumn();
    row = cell.getRow();
    shark = cell.getShark();
    fish = cell.getFish();
    empty = new EmptyWaTorCell(column, row, EMPTY);
    currentState = cell.getState();
  }

  public WaTorCell(int x, int y, int initState){
    super(x, y, initState);
  }

  public WaTorCell(int x, int y, int code, int reproductionTimer, int nutritionValue, int health) {
    super(x, y, code);

    switch (code) {
      case EMPTY -> empty = new EmptyWaTorCell(x, y, code);
      case FISH -> fish = new FishCell(x, y, code, reproductionTimer, nutritionValue);
      case SHARK -> shark = new SharkCell(x, y, code, health, reproductionTimer);
    }

    currentState = code;

    this.column = x;
    this.row = y;
  }

  @Override
  public void update() {
    previousState = new WaTorCell(this);
    getCurrentObject().update();
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

  public void setEmpty(boolean block){
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

}
