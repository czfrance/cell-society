package cellsociety.cells;

public class RPSCell extends Cell{

  private final int ROCK = 0;
  private final int PAPER = 1;
  private final int SCISSORS = 2;

  private int currentState;

  private int threshold;

  public RPSCell(int x, int y, int initState, int threshold) {
    super(x, y, initState);

    currentState = initState;
    this.threshold = threshold;
  }
  @Override
  public int getNextState() {
    int numRock = getNum(ROCK);
    int numPaper = getNum(PAPER);
    int numSci = getNum(SCISSORS);
    if (numRock > threshold) nextState = ROCK;
    else if (numPaper > threshold) nextState = PAPER;
    else if (numSci > threshold) nextState = SCISSORS;
    return nextState;
  }

  public int getNum(int obj) {
    int num = 0;
    for (Cell neighbor : myNeighbors) {
      if (neighbor.getMyCurrentState() == obj) num++;
    }
    return num;
  }
//  @Override
//  public int getCurrentState() {
//    return currentState;
//}
  public void update() {
    currentState = nextState;
  }
}
