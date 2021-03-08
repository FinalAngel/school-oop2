import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayListExamples {
  
  public ArrayListExamples() {
    listOperations();
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
  }

}
