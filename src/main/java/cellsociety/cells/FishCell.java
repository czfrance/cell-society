package cellsociety.cells;

import java.util.List;
import java.util.*;

public class FishCell extends Cell {

  private final int REPRODUCTION_TIMER;
  private final int NUTRITION_VALUE;
  private final Random DICE = new Random();

  public FishCell(int x, int y, int initState, int reproductionTimer, int nutritionValue) {
    super(x, y, initState);

    REPRODUCTION_TIMER = reproductionTimer;
    NUTRITION_VALUE = nutritionValue;

  }

  public void move(int width, int height, List<List<Cell>> grid) {
    initNeighbors(width, height, grid);
    ArrayList<ArrayList<Integer>> potentialMove = getPotentialMove();
    ArrayList<Integer> selectedMove = potentialMove.get(DICE.nextInt(potentialMove.size()));

    COLUMN = selectedMove.get(0);
    ROW = selectedMove.get(1);
  }

  private ArrayList<ArrayList<Integer>> getPotentialMove() {
    ArrayList<ArrayList<Integer>> potentialMove = new ArrayList<>();
    for (Cell c : myNeighbors) {
      if (c.getID() == 0) {
        ArrayList<Integer> coords = new ArrayList<>();
        coords.add(c.getColumn());
        coords.add(c.getRow());
        potentialMove.add(coords);
      }
    }
    return potentialMove;
  }

}
