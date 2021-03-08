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

    // sorting
    List<String> movies = new ArrayList<>(List.of("The Lord of the Rings", "Star Wars", "Inception"));
    // this works perfectly for a normal list
    Collections.sort(movies);
    // or Arrays.sort()

    /**
     * though it is more complicated when using classes, what should I sort for?
     * 
     * So now we need to use the Comparable interface in our class to define what to match for
     * public int compareTo(T other) {kleiner: -1, gleich: 0, grösser: 1}
     */
    if (movies.get(0).compareTo(movies.get(1)) < 0) {
      System.out.println("ist kleiner als");
    }

    /**
     * Comparator<T> can be additionally used
     * public int compare(T first, T second)
     * implemented in "DateComparator.java"
     * 
     * or quick creation as anonymous function
     * 
     * Comparator<Module> comp = new Comparator<Module>() {
     *  public int compare(Module first, Module second) {
     *    return first.getCredits() - second.getCredits();
     *  }
     * }
     * Collections.sort(Date, comp); // can also be inline replaced in comp
     */

    /**
     * Compare data from data of unrelated sources
     * 
     * List<Student> students = ...
     * Map<Student, Double> examGrades = ...
     * 
     * Collections.sort(students, new Comperator<Student>() {
     *  public int compare(Studend first, Student second) {
     *    double firstGrade = examGrades.get(first);
     *    double secondGrade = examGrades.get(second);
     *    return Double.compare(firstGrade, secondGrade);
     *  }
     * })
     */
  }

}
