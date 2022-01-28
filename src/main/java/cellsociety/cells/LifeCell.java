package cellsociety.cells;

import javafx.scene.paint.Color;
import java.util.*;

public class LifeCell extends Cell{

  public LifeCell(int x, int y, double size, String initState) {
    super(x, y, size, initState);
    this.setFill(Color.BLUE);
  }

  public void initNeighbors(int width, int height) {
    int corner = isCorner(width, height);
    int edge = isEdge(width, height);
    if (corner != -1) {
      cornerNeighbors(corner);
    } else if (edge != -1) {

    } else {

    }

  }
  private ArrayList<Cell> edgeNeighbors() {
    ArrayList<Cell> neighbors = new ArrayList<>();


    return neighbors;
  }

  private ArrayList<Cell> cornerNeighbors(int code) {
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
}
