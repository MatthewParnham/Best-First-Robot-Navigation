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

    map.print();

    Search s = new Search();


//Search Test
    Pair p1 = new Pair(2,2);
    Pair p2 = new Pair(0,0);

    System.out.println(s.euclidDist(p1,p2));
    System.out.println(s.manhatDist(p1,p2));
// ----------

  }

}
