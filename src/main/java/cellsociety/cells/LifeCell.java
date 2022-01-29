package cellsociety.cells;

import javafx.scene.paint.Color;
import java.util.*;

public class LifeCell extends Cell{

  private ArrayList<LifeCell> myNeighbors;
  private String myState;

  public LifeCell(int x, int y, double size, String initState) {
    super(x, y, size, initState);
    this.setFill(Color.BLUE);
    myState = initState;
  }

  public void initNeighbors(int width, int height, ArrayList<ArrayList<LifeCell>> grid) {
    int corner = isCorner(width, height);
    int edge = isEdge(width, height);
    if (corner != -1) {
      myNeighbors = cornerNeighbors(corner, grid);
    } else if (edge != -1) {
      myNeighbors = edgeNeighbors(edge, grid);
    } else {
      myNeighbors = centerNeighbors(grid);
    }
  }

  private ArrayList<LifeCell> edgeNeighbors(int code, ArrayList<ArrayList<LifeCell>> grid) {
    switch (code) {
      case TOP_EDGE:
        return loop(0, 2, -1, 2, grid);
      case BOTTOM_EDGE:
        return loop(-1, 1, -1, 2, grid);
      case RIGHT_EDGE:
        return loop(-1, 2, -1, 1, grid);
      case LEFT_EDGE:
        return loop(-1, 2, 0, 2, grid);
      default:
        System.out.println("NOT AN EDGE");
        return null;
    }
  }

  private ArrayList<LifeCell> loop(int outerStart, int outerEnd, int innerStart, int innerEnd, ArrayList<ArrayList<LifeCell>> grid) {
    ArrayList<LifeCell> neighbors = new ArrayList<>();
    for (;outerStart < outerEnd; outerStart++) {
      for (;innerStart < innerEnd; outerStart++) {
        neighbors.add(grid.get(ROW + outerStart).get(COLUMN + innerStart));
      }
    }
    return neighbors;
  }

  private ArrayList<LifeCell> cornerNeighbors(int code, ArrayList<ArrayList<LifeCell>> grid) {
    ArrayList<LifeCell> neighbors = new ArrayList<>();

    switch (code) {
      case TOP_LEFT:
        neighbors.add(grid.get(ROW + 1).get(COLUMN + 1));
        neighbors.add(grid.get(ROW).get(COLUMN + 1));
        neighbors.add(grid.get(ROW + 1).get(COLUMN));
        break;
      case TOP_RIGHT:
        neighbors.add(grid.get(ROW + 1).get(COLUMN));
        neighbors.add(grid.get(ROW + 1).get(COLUMN - 1));
        neighbors.add(grid.get(ROW).get(COLUMN - 1));
        break;

      case BOTTOM_LEFT:
        neighbors.add(grid.get(ROW - 1).get(COLUMN + 1));
        neighbors.add(grid.get(ROW - 1).get(COLUMN));
        neighbors.add(grid.get(ROW).get(COLUMN + 1));
        break;
      case BOTTOM_RIGHT:
        neighbors.add(grid.get(ROW - 1).get(COLUMN - 1));
        neighbors.add(grid.get(ROW).get(COLUMN - 1));
        neighbors.add(grid.get(ROW -1).get(COLUMN + 1));
        break;
      default:
        System.out.println("NOT A CORNER");
    }
    return neighbors;
  }

  private ArrayList<LifeCell> centerNeighbors(ArrayList<ArrayList<LifeCell>> grid) {
    ArrayList<LifeCell> neighbors = new ArrayList<>();
    for (int i = -1; i < 2; i++) {
      for (int j = -1; j < 2; j++) {
        neighbors.add(grid.get(ROW + i).get(COLUMN + j));
      }
    }
    return neighbors;
  }

  public void nextState() {
    int numAlive = numNeighborsAlive();

    if (numAlive == 2 || numAlive == 3) myState = "1";
    else myState = "0";
  }

  private int numNeighborsAlive() {
    int numAlive = 0;
    for (LifeCell c : myNeighbors) {
      if (c.isAlive()) {
        numAlive++;
      }
    }
    return numAlive;
  }

  public boolean isAlive() {
    return myState.equals("1");
  }
}
