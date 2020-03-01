import java.util.*;
import java.lang.*;

public class Search {

  public enum SearchMode {
    EUCLIDEAN, MANHATTAN
  }

  public boolean addCost; //Toggles whether or not g(n) is added to f(n)
  public SearchMode mode;
  public Grid map;

  public int searchTreeSize;

  public List<Node> openList;
  public List<Node> closedList;

  public Search(boolean addCost, SearchMode mode, Grid map) {
    this.addCost = addCost;
    this.mode = mode;
    this.map = map;
    this.searchTreeSize = 0;
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

//Surprisingly messy function to make sure all inserts into fringe are sorted
  public void insertOpen(Node n) {
    if(openList.isEmpty()) {
      openList.add(n);
      return;
    }
    int size = openList.size();
    for(int i = 0; i < size; i++) {
      if(addCost) { //take into account g(n) or not
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

  //Since I'm using custom Pair and node, the List.contains() does not work and we need a custom contains
  //Choosing to return an int of the idx where the element is found and -1 if not found instead of boolean
  //This allows us to easily access said element in case we need to remove it
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

  //For debugging. Prints fringe
  public void printOpenList() {
    System.out.println("===Open List===");
    for (Node n : openList) {
      System.out.println(n);
    }
  }

  public List<Node> findPath() {
    //Insert initial position into fringe
    Node root = new Node(map.initialPos,0,evaluate(map.initialPos,map.goalPos));
    searchTreeSize++;

    insertOpen(root);

    //Create List for output
    List<Node> result = new ArrayList<Node>();

    //while fringe has something in it
    while(!openList.isEmpty()) {
      //Set current to cheapest element in fringe
      Node curr = openList.get(0);
      //if current is the goal, generate a list of the path taken and return
      if(curr.position.equals(map.goalPos)) {
        Node end = curr.parent;
        while(end.parent != null) {
          result.add(end);
          end = end.parent;
        }
        return result;
      }
      //Pop curr from fringe and add to closedList
      openList.remove(0);
      closedList.add(curr);

      //Loop through children
      List<Pair> currNeighs = map.getNeighbors(curr.position);
      for (Pair childPos : currNeighs) {

        //make sure it's walkable
        if(map.get(childPos) == map.obstacle) {
          continue;
        }

        //check if node is in closed List
        if(closedContains(childPos) >= 0) {
          continue;
        }

        //Generate actual node. Doing it down here to save time/memory in case the two previous checks fail
        Node childNode = new Node(childPos,(curr.g + 1),evaluate(childPos,map.goalPos));
        childNode.parent = curr;
        searchTreeSize++;

        //Child is in the open list
        int childIdx = openContains(childPos);
        if(childIdx >= 0) {
          //Check if new node is cheaper g(n) than previous. If so, pop old one
          if(childNode.g < openList.get(childIdx).g) {
            openList.remove(childIdx);
          }
          //preventing dupes in fringe
          continue;
        }

        //Finally, checks have passed. Insert child into fringe
        insertOpen(childNode);

      }


    }
    return new ArrayList<Node>();
  }

}
