import java.util.*;


public class Node {

  public Node parent;
  public Pair position;
  public double f;
  public double g;
  public double h;

  public Node(Pair position, double g, double h) {
    this.position = position;
    this.parent = null;
    this.g = g;
    this.h = h;
    this.f = g + h;
  }

}
