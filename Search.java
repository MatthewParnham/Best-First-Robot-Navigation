import java.util.*;
import java.lang.*;

public class Search {

  public enum SearchMode {
    EUCLIDEAN, MANHATTAN
  }

  public boolean addCost;
  public SearchMode mode;
  public Grid map;


  public List<Node> openList;
  public List<Node> closedList;

  public Search(boolean addCost, SearchMode mode, Grid map) {
    this.addCost = addCost;
    this.mode = mode;
    this.map = map;
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
      return;
    }
    int size = openList.size();
    for(int i = 0; i < size; i++) {
      if(addCost) {
        if (openList.get(i).f > n.f) {
          openList.add(i,n);
          return;
        }
      } else {
        if (openList.get(i).h > n.h) {
          openList.add(i,n);
          return;
        }
      }

    }
    openList.add(size-1,n);
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

  public void printOpenList() {
    System.out.println("===Open List===");
    for (Node n : openList) {
      System.out.println(n);
    }
  }

  public List<Node> findPath() {
    //Insert initial position into fringe
    Node root = new Node(map.initialPos,0,evaluate(map.initialPos,map.goalPos));

    insertOpen(root);

    List<Node> result = new ArrayList<Node>();

    //while fringe has something in it
    while(!openList.isEmpty()) {
      //printOpenList();
      Node curr = openList.get(0);
      //System.out.println("New Iteration: Current Node: " + curr.position);
      //printOpenList();
      if(curr.position.equals(map.goalPos)) {
        Node end = curr.parent;
        while(end.parent != null) {
          result.add(end);
          end = end.parent;
        }
        return result;
      }
      openList.remove(0);
      closedList.add(curr);

      //Loop through children
      List<Pair> currNeighs = map.getNeighbors(curr.position);
      for (Pair childPos : currNeighs) {
        //System.out.println("ChildPos: " + childPos);
        //printOpenList();
        //make sure it's walkable
        if(map.get(childPos) == map.obstacle) {
          continue;
        }

        //check if node is in closed List
        if(closedContains(childPos) >= 0) {
          continue;
        }

        //Generate actual node
        Node childNode = new Node(childPos,(curr.g + 1),evaluate(childPos,map.goalPos));
        childNode.parent = curr;

        //Child is in the open list
        int childIdx = openContains(childPos);
        //System.out.println(childIdx);
        if(childIdx >= 0) {
          /*if(childNode.g < openList.get(childIdx).g) {
            openList.remove(childIdx);
          }*/
          continue;
        }

        insertOpen(childNode);
        childNode.parent = curr;

      }


    }
    return new ArrayList<Node>();
  }

}
