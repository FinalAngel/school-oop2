public class Main {
  public static void main(String[] args) {
    Pair<String, Double> p1 = new Pair<>("hello", 2.71);
    System.out.println(p1 + "\n");

    String fst = p1.first();
    System.out.println(fst);

    double snd = p1.second();
    System.out.println(snd);

    System.out.println();
    Pair<Integer, String> p2 = new Pair<>(42, null);
    System.out.println(p2);
  }

}
