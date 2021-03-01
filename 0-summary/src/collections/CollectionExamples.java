import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class CollectionExamples {
  
  public CollectionExamples() {
    /**
     * Use methods like
     * add, addAll, clear, contains, containsAll...
     */
    arrayListExamples();
    linkedListExamples();
  }

  private void arrayListExamples() {
    // use generic list when using collections
    List<String> words = new ArrayList<>(List.of("A", "B", "C"));
    words.add("D");

    // remove every second element
    int i = 0;
    while (i < words.size()) {
      String s = words.get(i);
      // this is very slow on large collections, as every element
      // that follows has to be re-indexed
      if (s.length() % 2 == 0) {
        words.remove(i);
      } else {
        i++;
      }
    }
  }
  
  private void linkedListExamples() {
    List<String> words = new LinkedList<>(Arrays.asList("A", "B", "C"));

    // iterate over elements when deleting
    // this is much faster as only one node has to be removed and
    // two others need to be updated on larger collections
    Iterator<String> iter = words.iterator();
    while (iter.hasNext()) {
      String s = iter.next();
      if (s.length() % 2 == 0) {
        iter.remove();
      }
    }

    // iterate over elements for reading
    // (will be automatically transpiled to an iterator)
    for (String s : words) {
      System.out.println(s);
    }
  }

}
