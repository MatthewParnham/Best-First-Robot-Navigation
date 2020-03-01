import java.io.File;
import java.util.*;

public class Main {

  public static void main(String[] args) {
    if(args.length != 1) {
      System.err.println("Incorrect number of arguments.\nCorrect Usage: Java Main <filepath>");
      System.exit(0);
    }
    File f = new File(args[0]);
    if(!f.exists()) {
      System.err.println("File not found.");
      System.exit(0);
    }

    Grid map = new Grid(f);
    Grid mapCopy = new Grid(f);
    map.print();

//Search Test
    Search s = new Search(false,Search.SearchMode.MANHATTAN,map);

    List<Node> result = s.findPath();
    System.out.println("Solution: Strategy 1");
    for(Node n : result) {
      mapCopy.set(n.position,'o');
    }
    mapCopy.print();



    s = new Search(false,Search.SearchMode.EUCLIDEAN,map);
    mapCopy = new Grid(f);

    result = s.findPath();
    System.out.println("Solution: Strategy 2");
    for(Node n : result) {
      mapCopy.set(n.position,'o');
    }
    mapCopy.print();

    s = new Search(true,Search.SearchMode.MANHATTAN,map);
    mapCopy = new Grid(f);

    result = s.findPath();
    System.out.println("Solution: Strategy 3");
    for(Node n : result) {
      mapCopy.set(n.position,'o');
    }
    mapCopy.print();

    s = new Search(true,Search.SearchMode.EUCLIDEAN,map);
    mapCopy = new Grid(f);

    result = s.findPath();
    System.out.println("Solution: Strategy 4");
    for(Node n : result) {
      mapCopy.set(n.position,'o');
    }
    mapCopy.print();

  }

}
