import java.util.*;
import java.io.*;

public class Grid {

  public int size;
  //2d array
  public char[][] g;

  public Pair initialPos;
  public Pair goalPos;

  public static char empty = '.';
  public static char obstacle = '+';
  public static char initial = 'i';
  public static char goal = 'g';

  Grid(File file) {
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line = br.readLine();
      this.size = Integer.parseInt(line);
      g = new char[size][size];
      int i = 0;
      while ((line = br.readLine()) != null) {
         for(int j = 0; j < size; j++) {
           g[i][j] = line.charAt(j);
           if(initial == g[i][j]) {
             initialPos = new Pair(i,j);
           }
           if(goal == g[i][j]) {
             goalPos = new Pair(i,j);
           }
         }
         i++;
      }
    } catch (Exception e) {
      System.err.println("File IO Error");
      System.exit(0);
    }
  }

  public void print() {
    for(int i = 0; i < size; i++) {
      for(int j = 0; j < size; j++) {
        System.out.print(g[i][j] + " ");
      }
      System.out.print("\n");
    }
  }

//All these are row, column
  public char get(Pair cell) {
    return g[cell.getX()][cell.getY()];
  }
  public char get(int x, int y) {
    return g[x][y];
  }
  public void set(Pair cell, char c) {
    g[cell.getX()][cell.getY()] = c;
  }
  public void set(int x, int y, char c) {
    g[x][y] = c;
  }

  public List<Pair> getNeighbors(Pair cell) {
    List<Pair> neighs = new ArrayList<Pair>();
    if(cell.getX() + 1 < size) {
      neighs.add(new Pair(cell.getX() + 1, cell.getY()));
    }
    if(cell.getY() + 1 < size) {
      neighs.add(new Pair(cell.getX(), cell.getY() + 1));
    }
    if(cell.getX() - 1 >= 0) {
      neighs.add(new Pair(cell.getX() - 1, cell.getY()));
    }
    if(cell.getY() - 1 >= 0) {
      neighs.add(new Pair(cell.getX(), cell.getY() - 1));
    }
    return neighs;
  }

}
