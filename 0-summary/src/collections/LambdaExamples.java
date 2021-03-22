import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class LambdaExamples {

  public LambdaExamples() {
    lambda();
    functionalInterface();
  }

  private void lambda() {
    Comparator<String> comparator;
    // simple before
    comparator = new Comparator<>() {
      public int compare(String s1, String s2) {
        return s1.length() - s2.length();
      }
    };

    // as basic lambda
    comparator = (String s1, String s2) -> {
      return s1.length() - s2.length();
    };

    // simplified without types
    comparator = (s1, s2) -> s1.length() - s2.length();
    // has access to variables from the outside too

    System.out.println(comparator);
  }

  // Runnable, Supplier<T>, Consumer<T>, BiConsumer<T, U>, Function<T, R>, BiFunction<T, U, R>
  // special: UnaryOperator<T>, BinaryOperator<T>, Predicate<T>, BiPredicate<T, U>
  // https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/function/package-summary.html
  private void functionalInterface() {
    // because Java is a OOP based language, functional coding was not
    // part of it, so we have to use special interfaces like Consumer
    String scoped = "World";

    // we need a function interface to use lambda
    Consumer<String> greeter;
    greeter = name -> System.out.println("Hello, " + name + scoped);

    System.out.println(greeter);

    // the same goes with Comparator, Runnable or AutoCloseable
    // as long as they have one method (e.g. Iterator or KeyListener wont work)
    // more examples
    List<String> list = new ArrayList<String>(List.of("Hello", "World"));
    Collections.sort(list, (i1, i2) -> i2.length() - i1.length());

    list.removeIf(s -> s.length() > 10);
    list.removeIf(Objects::isNull); // sgirt versuib

    Collections.sort(list, (x, y) -> x.compareToIgnoreCase(y));
    Collections.sort(list, String::compareToIgnoreCase); // short version

    list.forEach(x -> System.out.println(x));
    list.forEach(System.out::println);

    // sample implementation with a method
    repeat(10, () -> System.out.println("Hello world"));
    // with a passable param
    repeat_int(4, i -> System.out.println("Countdown: " + (3 - i)));
  }

  public static void repeat(int n, Runnable action) {
    for (int i = 0; i < n; i++) {
      action.run();
    }
  }

  public static void repeat_int(int n, IntConsumer action) {
    for (int i = 0; i < n; i++) {
      action.accept(i);
    }
  }

}
