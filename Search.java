import java.util.*;
import java.lang.*;

public class Search {

  public enum SearchMode {
    EUCLIDEAN, MANHATTAN
  }

  public boolean addCost;
  public SearchMode mode;
  public Grid map;

  public int cost;

  public List<Node> openList;
  public List<Node> closedList;

  public Search(boolean addCost, SearchMode mode, Grid map) {
    this.addCost = addCost;
    this.mode = mode;
    this.map = map;
    this.cost = 0;
    this.openList = new ArrayList<Node>();
    this.closedList = new ArrayList<Node>();
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
      dist += (double)cost;
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

  public void insertOpen(Node n) {
    if(openList.isEmpty()) {
      openList.add(n);
    }
    for(int i = 0; i < openList.size(); i++) {
      if (openList.get(i).f > n.f) {
        openList.add(i,n);
      }
    }
  }

  public int closedContains(Pair p) {
    for(int i = 0; i < closedList.size(); i++) {
      if(closedList.get(i).position.equals(p)) {
        return i;
      }
    }
    return -1;
  }
  public int openContains(Pair p) {
    for(int i = 0; i < openList.size(); i++) {
      if(openList.get(i).position.equals(p)) {
        return i;
      }
    }
    return -1;
  }

  public List<Node> findPath() {
    //Insert initial position into fringe
    Node root = new Node(map.initialPos,0,evaluate(map.initialPos,map.goalPos));

    insertOpen(root);

    List<Node> result = new ArrayList<Node>();

    //while fringe has something in it
    while(!openList.isEmpty()) {
      Node curr = openList.get(0);
      if(curr.position.equals(map.goalPos)) {
        result.add(curr);
        return result;
      }
      openList.remove(0);
      closedList.add(curr);

      List<Pair> currNeighs = map.getNeighbors(curr);
      for (Pair childPos : currNeighs) {
        Node childNode = new Node(childPos,(curr.g + 1),evaluate(childPos,map.goalPos));
        //check if node is in closed List
        if(closedContains(childPos) >= 0) {
          continue;
        }

        childIdx = openContains(childPos);
        if(childIdx == -1) {
          insertOpen(childNode);
          childNode.parent = curr;
        } else {
          if(childNode.g > openList.get(childIdx).g) {
            
          }
        }
        int currCost = cost + 1;
        if(openList.contains(child) && )
      }


    }
    return new ArrayList<Pair>();
  }

}
