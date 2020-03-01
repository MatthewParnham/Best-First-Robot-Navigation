public class Pair {
    private int x;
    private int y;
    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX() {
      return x;
    }
    public int getY() {
      return y;
    }
    public void setX(int x) {
      this.x = x;
    }
    public void setY(int y) {
      this.y = y;
    }
    public String toString() {
      return Integer.toString(this.x) + "," + Integer.toString(this.y);
    }
    public boolean equals(Pair o){
      return (this.x == o.x) && (this.y == o.y);
    }
}
