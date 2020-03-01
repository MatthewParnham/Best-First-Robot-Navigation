import java.io.File;

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

//Search Test
    Search s = new Search(false,Search.SearchMode.MANHATTAN,map);
    System.out.println(s.mode);
    s.map.print();
    Pair p1 = new Pair(2,2);
    Pair p2 = new Pair(0,0);

    System.out.println(s.evaluate(p1,p2));

// ----------

  }

}
