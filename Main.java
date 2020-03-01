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
    System.out.println(map.size);
    System.out.println(map.initialPos);
    System.out.println(map.goalPos);

    System.out.println();
    System.out.println(map.get(2,1));
    System.out.println(map.get(1,2));
    System.out.println();
    map.set(0,0, Grid.obstacle);
    map.print();


  }

}
