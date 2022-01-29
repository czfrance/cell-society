package cellsociety.cells;

import java.util.ArrayList;
import java.util.List;

public class Cell4Neighbors extends Cell {

  public Cell4Neighbors(int x, int y, int initState) {
    super(x, y, initState);
  }

//  @Override
//  public void initNeighbors(int width, int height, List<List<Cell>> grid) {
//    int corner = isCorner(width, height);
//    int edge = isEdge(width, height);
//    if (corner != -1) {
//      myNeighbors = cornerNeighbors(corner, grid);
//    } else if (edge != -1) {
//      myNeighbors = edgeNeighbors(edge, grid);
//    } else {
//      myNeighbors = centerNeighbors(grid);
//    }
//  }

//  @Override
//  protected List<Cell> edgeNeighbors(int code, List<List<Cell>> grid) {
//    switch (code) {
//      case TOP_EDGE:
//        return loop(0, 2, -1, 2, grid);
//      case BOTTOM_EDGE:
//        return loop(-1, 1, -1, 2, grid);
//      case RIGHT_EDGE:
//        return loop(-1, 2, -1, 1, grid);
//      case LEFT_EDGE:
//        return loop(-1, 2, 0, 2, grid);
//      default:
//        System.out.println("NOT AN EDGE");
//        return null;
//    }
//  }
  @Override
  protected List<Cell> findEdgeNeighbors(int outerStart, int outerEnd, int innerStart, int innerEnd, List<List<Cell>> grid) {
    List<Cell> neighbors = new ArrayList<>();
    int currOut = outerStart;
    int currIn;
    for (;currOut < outerEnd; currOut++) {
      currIn = innerStart;
      for (;currIn < innerEnd; currIn++) {
        if ((currOut!=0 || currIn!=0) && (currOut==0 || currIn==0)) {
          neighbors.add(grid.get(ROW + currOut).get(COLUMN + currIn));
        }
      }
    }
    return neighbors;
  }

  @Override
  protected List<Cell> cornerNeighbors(int code, List<List<Cell>> grid) {
    List<Cell> neighbors = new ArrayList<>();

    switch (code) {
      case TOP_LEFT:
        //neighbors.add(grid.get(ROW + 1).get(COLUMN + 1));
        neighbors.add(grid.get(ROW).get(COLUMN + 1));
        neighbors.add(grid.get(ROW + 1).get(COLUMN));
        break;
      case TOP_RIGHT:
        neighbors.add(grid.get(ROW + 1).get(COLUMN));
        //neighbors.add(grid.get(ROW + 1).get(COLUMN - 1));
        neighbors.add(grid.get(ROW).get(COLUMN - 1));
        break;

      case BOTTOM_LEFT:
        //neighbors.add(grid.get(ROW - 1).get(COLUMN + 1));
        neighbors.add(grid.get(ROW - 1).get(COLUMN));
        neighbors.add(grid.get(ROW).get(COLUMN + 1));
        break;
      case BOTTOM_RIGHT:
        //neighbors.add(grid.get(ROW - 1).get(COLUMN - 1));
        neighbors.add(grid.get(ROW).get(COLUMN - 1));
        neighbors.add(grid.get(ROW - 1).get(COLUMN));
        break;
      default:
        System.out.println("NOT A CORNER");
    }
    return neighbors;
  }

  @Override
  protected List<Cell> centerNeighbors(List<List<Cell>> grid) {
    List<Cell> neighbors = new ArrayList<>();
    for (int i = -1; i < 2; i++) {
      for (int j = -1; j < 2; j++) {
        if ((i!=0 || j!=0) && (i==0 || j==0)) {
          neighbors.add(grid.get(ROW + i).get(COLUMN + j));
        }
      }
    }
    return neighbors;
  }
}
