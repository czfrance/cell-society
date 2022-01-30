package cellsociety.cells;

import java.util.ArrayList;
import java.util.List;

public class Cell8Neighbors extends Cell {


  public Cell8Neighbors(int x, int y, int initState) {
    super(x, y, initState);
  }

  @Override
  public int getNextState() {
    return myState;
  }

//  @Override
//  protected List<Cell> findEdgeNeighbors(int outerStart, int outerEnd, int innerStart, int innerEnd, List<List<Cell>> grid) {
//    List<Cell> neighbors = new ArrayList<>();
//    int currOut = outerStart;
//    int currIn;
//    for (;currOut < outerEnd; currOut++) {
//      currIn = innerStart;
//      for (;currIn < innerEnd; currIn++) {
//        if (currOut!=0 || currIn!=0) {
//          neighbors.add(grid.get(ROW + currOut).get(COLUMN + currIn));
//        }
//      }
//    }
//    return neighbors;
//  }
//
//  @Override
//  protected List<Cell> cornerNeighbors(int code, List<List<Cell>> grid) {
//    List<Cell> neighbors = new ArrayList<>();
//
//    switch (code) {
//      case TOP_LEFT:
//        neighbors.add(grid.get(ROW + 1).get(COLUMN + 1));
//        neighbors.add(grid.get(ROW).get(COLUMN + 1));
//        neighbors.add(grid.get(ROW + 1).get(COLUMN));
//        break;
//      case TOP_RIGHT:
//        neighbors.add(grid.get(ROW + 1).get(COLUMN));
//        neighbors.add(grid.get(ROW + 1).get(COLUMN - 1));
//        neighbors.add(grid.get(ROW).get(COLUMN - 1));
//        break;
//
//      case BOTTOM_LEFT:
//        neighbors.add(grid.get(ROW - 1).get(COLUMN + 1));
//        neighbors.add(grid.get(ROW - 1).get(COLUMN));
//        neighbors.add(grid.get(ROW).get(COLUMN + 1));
//        break;
//      case BOTTOM_RIGHT:
//        neighbors.add(grid.get(ROW - 1).get(COLUMN - 1));
//        neighbors.add(grid.get(ROW).get(COLUMN - 1));
//        neighbors.add(grid.get(ROW - 1).get(COLUMN));
//        break;
//      default:
//        System.out.println("NOT A CORNER");
//    }
//    return neighbors;
//  }
//
//  @Override
//  protected List<Cell> centerNeighbors(List<List<Cell>> grid) {
//    List<Cell> neighbors = new ArrayList<>();
//    for (int i = -1; i < 2; i++) {
//      for (int j = -1; j < 2; j++) {
//        if (i!=0 || j!=0) {
//          neighbors.add(grid.get(ROW + i).get(COLUMN + j));
//        }
//      }
//    }
//    return neighbors;
//  }
}
