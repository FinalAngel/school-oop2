import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SetsExamples {
  // it can be slow to find a specific element (contains)
  private int age;
  private int iq;
  private String name;
    
  public SetsExamples() {
    // similar to a "menge"
    Number[] smallPrimes = { 2, 3, 5, 7 };
    System.out.println(smallPrimes);

    hashSetExample();
    scannerExample();
    treeSetExample();

    /**
     * a ∪ b    = a.addAll(b)
     * a ∩ b    = a.retainAll(b)
     * a ∖ b    = a.removeAll(b)
     * a teilmenge b = a.containsAll(b)
     */
  }

  // most used one
  private void hashSetExample() {
    Set<String> avengers = new HashSet<>();
    avengers.add("Captain America");
    avengers.add("Iron Man");
    avengers.add("Thor");
    avengers.add("Iron Man");

    // creates a set without duplicates:
    // the order can be random!!!
    System.out.println(avengers);
    // ["Captain America", "Iron Man", "Thor"]

    // uses a hashCode method to calculate if it is the same element
    // .hashCode()
    // this is important when creating own objects/classes, as you have
    // to implement the hashCode methods yourself!!!
  }

  public int hashCode() {
    // similar to the equals method, but instead return an integer
    int code = age;
    code = 31 * code + iq;
    code = 31 * code + name.hashCode();
    return code;

    // or autogenerate using 
  }

  private void scannerExample() {
    Scanner scanner = new Scanner(System.in);
    Set<String> words = new HashSet<>();
    while (scanner.hasNext()) {
      words.add(scanner.next().toLowerCase());
    }
    System.out.println(words.size());
    scanner.close();

    // now do operations with contains
    while (scanner.hasNext()) {
      String next = scanner.next().toLowerCase();
      if (words.contains(next)) {
        System.out.println(next);
      } else {
        words.add(next);
      }
    }
  }

  private void treeSetExample() {}

}
