import java.util.*;
import java.lang.*;

public class Search {

  public enum SearchMode {
    EUCLIDEAN, MANHATTAN
  }

  public boolean addCost;
  public SearchMode mode;

  public Search(boolean addCost, SearchMode mode) {
    this.addCost = addCost;
    this.mode = mode;
  }
  public Search() {
    this.addCost = false;
    this.mode = SearchMode.MANHATTAN;
  }

  public double euclidDist(Pair a, Pair b) {
    int x = Math.abs(a.getX() - b.getX());
    int y = Math.abs(a.getY() - b.getY());
    return Math.sqrt(((x)*(x))+((y)*(y)));
  }
  public int manhatDist(Pair a, Pair b) {
    int x = Math.abs(a.getX() - b.getX());
    int y = Math.abs(a.getY() - b.getY());
    return x+y;
  }

}
