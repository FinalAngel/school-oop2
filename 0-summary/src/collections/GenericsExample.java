import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenericsExample<E extends Number, T> {
  private final List<E> list;
  private final E number;

  public GenericsExample(E number) {
    this.number = number;
    this.list = new ArrayList<E>();
  }

  public double getNumber() {
    // we need to use doubleValue cause number is of type Number
    // otherwise we don't get the "double" type
    return number.doubleValue();
  }
  
  public boolean add(E element) {
    return list.add(element);
  }

  // here we add a type limitation that T needs to be a type of Comparable
  // and comparable itself also needs to accept the same type
  public static <T extends Comparable<T>> void sort(List<T> list) {
    // do stuff
  }

  public static <T extends Closeable> void closeAll(List<T> list) throws IOException {
    for (T element : list) {
      element.close();
    }
  }

  // kovarianz
  public static <T extends Number> void printAll(List<T> list) {
    for (int i = 0; i < list.size(); i++) {
      Number n = list.get(i); // allowed bevause T is subtype of Number
      System.out.println(n.doubleValue());
    }
  }

  // shorthand with wildcards
  // [read only]
  public static void printAllShort(List<? extends Number> list) {
    for (int i = 0; i < list.size(); i++) {
      Number n = list.get(i); // allowed bevause T is subtype of Number
      System.out.println(n.doubleValue());
    }
  }

  // super wildcard, actually accepts a double now instead of just Double
  // [now with write]
  public static void generateDoubles(List<? super Double> list) {
    Random random = new Random();

    while (list.size() < 10) {
      list.add(random.nextDouble());
    }
  }

  // just a single method as generic
  // Arrays.<String>swap(friends, 0, 1)
  public static <T> void swap(T[] array, int i, int j) {
    T temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
  
}
