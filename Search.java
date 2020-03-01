import java.util.*;
import java.lang.*;

public class Search {

  public enum SearchMode {
    EUCLIDEAN, MANHATTAN
  }

  public boolean addCost;
  public SearchMode mode;
  public Grid map;

  public int currentSteps;

  public Search(boolean addCost, SearchMode mode, Grid map) {
    this.addCost = addCost;
    this.mode = mode;
    this.map = map;
  }
  public Search() {
    this.addCost = false;
    this.mode = SearchMode.MANHATTAN;
  }

  public double evaluate(Pair a, Pair b) {
    double dist;
    if (mode == SearchMode.MANHATTAN) {
      dist = manhatDist(a,b);
    }
    else { //mode == SearchMode.EUCLIDEAN
      dist = euclidDist(a,b);
    }

    if(addCost) {
      dist += (double)currentSteps;
    }
    return dist;
  }

  public double euclidDist(Pair a, Pair b) {
    int x = Math.abs(a.getX() - b.getX());
    int y = Math.abs(a.getY() - b.getY());
    return Math.sqrt(((x)*(x))+((y)*(y)));
  }
  public double manhatDist(Pair a, Pair b) {
    int x = Math.abs(a.getX() - b.getX());
    int y = Math.abs(a.getY() - b.getY());
    return (double)x+y;
  }



}
