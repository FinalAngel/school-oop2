import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ArrayListExamples {
  
  public ArrayListExamples() {
    listOperations();
    listLoops();
  }

  private void listOperations() {
    // convert from old to new style arrays
    String[] oldArray = new String[] { "Hello", "World" };
    List<String> list = Arrays.asList(oldArray);
    System.out.println(list);

    // quick create a copy of a list
    List<String> module = new ArrayList<>(List.of(oldArray));
    System.out.println(module);

    // quick access to a list without a loop
    list.subList(list.size() / 2, list.size()).contains("World");

    // now mehtods like add, remove and clear throw exceptions
    // this allows to create an unmodifiable list
    Collections.unmodifiableList(list);
    // this is great when implementing a getList method in a class
    // otherwise getList would allow for modifications

    // sorting
    List<String> movies = new ArrayList<>(List.of("The Lord of the Rings", "Star Wars", "Inception"));
    // this works perfectly for a normal list
    Collections.sort(movies);
    Arrays.sort(movies.toArray());

    /**
     * though it is more complicated when using classes, what should I sort for?
     * 
     * So now we need to use the Comparable interface in our class to define what to match for
     * public int compareTo(T other) {kleiner: -1, gleich: 0, grösser: 1}
     */
    if (movies.get(0).compareTo(movies.get(1)) < 0) {
      System.out.println("ist kleiner als");
    }
  }

  private void listLoops() {
    List<Integer> list = new ArrayList<>(List.of(0, 1, 2));

    // direct removal
    for (int i = 0; i < list.size(); i++) {
      list.remove(i);
    }

    // remove every second element
    int position = 1;
    Iterator<Integer> iter = list.iterator();

    while (iter.hasNext()) {
      iter.next();

      if (position % 2 == 0) {
        iter.remove();
      }

      position++;
    }

    // inline application
    for (Iterator<Integer> i = list.iterator(); i.hasNext();) {
    }
  }
  
  // helper
  public static int mapCountDuplicates(Map<String, Integer> map) {
      return map.values().size() - new HashSet<>(map.values()).size();
  }

}
