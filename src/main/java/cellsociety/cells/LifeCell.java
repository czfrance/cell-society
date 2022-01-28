package cellsociety.cells;

import javafx.scene.paint.Color;
import java.util.*;

public class LifeCell extends Cell{

  public LifeCell(int x, int y, double size, String initState) {
    super(x, y, size, initState);
    this.setFill(Color.BLUE);
  }

  public void initNeighbors(int width, int height, ArrayList<ArrayList<Cell>> grid) {
    int corner = isCorner(width, height);
    int edge = isEdge(width, height);
    if (corner != -1) {
      myNeighbors = edgeNeighbors(corner, grid);
    } else if (edge != -1) {
      myNeighbors = edgeNeighbors(edge, grid);
    } else {
      myNeighbors = centerNeighbors(grid);
    }
  }

  private ArrayList<Cell> edgeNeighbors(int code, ArrayList<ArrayList<Cell>> grid) {
    ArrayList<Cell> neighbors = new ArrayList<>();

    switch (code) {
      case TOP_EDGE:

        break;
      case BOTTOM_EDGE:

        break;
      case RIGHT_EDGE:

        break;
      case LEFT_EDGE:

        break;
      default:
        System.out.println("NOT AN EDGE");
    }
    return neighbors;
  }

  private ArrayList<Cell> cornerNeighbors(int code, ArrayList<ArrayList<Cell>> grid) {
    ArrayList<Cell> neighbors = new ArrayList<>();

    switch (code) {
      case TOP_LEFT:

        break;
      case TOP_RIGHT:

        break;

      case BOTTOM_LEFT:

        break;
      case BOTTOM_RIGHT:

        break;
      default:
        System.out.println("NOT A CORNER");
    }


    return neighbors;
  }

  private ArrayList<Cell> centerNeighbors(ArrayList<ArrayList<Cell>> grid) {
    ArrayList<Cell> neighbors = new ArrayList<>();
    for (int i = -1; i < 2; i++) {
      for (int j = -1; j < 2; j++) {
        neighbors.add(grid.get(ROW + i).get(COLUMN + j));
      }
    }
    return neighbors;
  }
}
