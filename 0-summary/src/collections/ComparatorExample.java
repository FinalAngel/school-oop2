import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorExample {

  private int someSize;

  public ComparatorExample(int someSize) {
    this.someSize = someSize;

    comparisons();
  }

  private void comparisons() {
    List<String> list1 = new ArrayList<>(List.of("short", "pretty long", "tiny", "longish", "suuuuuper long"));
    List<Integer> list2 = new ArrayList<>(List.of(1, 1, 1, 2, 2, 4, 4, 4, 4, 5));
    List<ComparatorExample> list3 = new ArrayList<>(List.of(new ComparatorExample(10)));

    // sort by length
    Collections.sort(list1, Comparator.comparing(String::length));

    // simple example
    Comparator<ComparatorExample> comp = new Comparator<ComparatorExample>() {
      public int compare(ComparatorExample first, ComparatorExample second) {
        return first.someSize - second.someSize;
      }
    };
    Collections.sort(list3, comp);

    // sort by frequency | compares two values
    Collections.sort(list2, new Comparator<Integer>() {
      public int compare(Integer n1, Integer n2) {
        if (Collections.frequency(list2, n1) != Collections.frequency(list2, n2)) {
          return Collections.frequency(list2, n1) - Collections.frequency(list2, n2);
        } else {
          return n1 - n2;
        }
      }
    });
  }

  public int compareTo(ComparatorExample other) {
    if (someSize != other.someSize) {
      return someSize - other.someSize;
    } else {
      return Integer.compare(someSize, other.someSize);
    }
  }

}
