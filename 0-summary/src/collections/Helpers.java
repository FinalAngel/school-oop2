import java.util.Random;

public class Helpers {
  
  public Helpers() {
    createRandom();
  }

  private int createRandom() {
    int size = 10;
    Random rand = new Random();

    return rand.nextInt(size); // or list.size()
  }

}
