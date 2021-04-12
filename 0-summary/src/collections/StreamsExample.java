import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsExample {
  
  public StreamsExample() {

  }

  // last method is the one that gathers the data like
  // collectors: toList, toSet, groupingBy, joining, counting, averaging
  // functions: count, forEach
  // limiters: limit(int), takeWhile(i -> i < 100), skip, dropWhile, distinct
  public static List<String> basicExample() {
    List<Country> countries = new ArrayList<Country>();
    countries.add(new Country());
    countries.add(new Country());
    countries.add(new Country());

    return countries.stream()
      .filter(c -> c.getArea() < 100_000)
      .sorted(Comparator.comparing(Country::getArea))
      // a modifier can only be called once on a stream
      .map(Country::getName)
      .collect(Collectors.toList());
  }

  public static void moreStreamExamples() {
    Stream.of("one", "two", "three"); // ad hoc stream
    // scanner.tokens() // stream for scanner
    Stream.generate(() -> "hello world"); // generates endlos
    Stream.generate(Math::random);
    Stream.iterate(1, i -> i * 2);
  }

  public static void streamOperations() {
    // predicate = true/false as return
    // filter(Predicate<T> predicate);

    // returns a new stream with the mapped elements
    // map(Function<T, R> mapper);

    // can modify the map to expand into more elements
    // flatMap(Function<T, Stream<R>> mapper);
    List<String> results = List.of("3:0", "2:2", "0:1");
    Stream<String> scores = results.stream()
      .flatMap(r -> Stream.of(r.split(":")));
    System.out.println(scores.collect(Collectors.toList()));

    // collect
    results.stream().collect(Collectors.toMap(s -> s, s -> 1, Integer::sum));
    // alternative
    List<String> words = List.of("Hello", "World", "Word");
    Map<String, Integer> frequencies = new HashMap<>();
    for (String word : words) {
      if (!frequencies.containsKey(word)) {
        frequencies.put(word, 1); // first two params in merge
      } else {
        frequencies.put(word, frequencies.get(word) + 1); // lambda in merge
      }
    }
    // or in super short use merge
    words.forEach(word -> frequencies.merge(
      word, 1, (string, integer) -> frequencies.get(word) + 1)
    );

    // take while matches and return values
    results.stream()
      .takeWhile(r -> r == "3:0")
      .collect(Collectors.toList());
  }

  public static void groupingBy() {
    List<String> names = List.of(
      "Müller", "Meier", "Kobler", "Schmid", "Stalder", 
      "Suter", "Maurer", "Meier", "Meck", "Müller"
    );

    Map<Character, List<String>> groups = names.stream()
      // per default toList(), can change: s -> s.charAt(0), toSet()
      .collect(Collectors.groupingBy(s -> s.charAt(0)));
  
    for (char c : groups.keySet()) {
      String group = groups.get(c).stream().collect(Collectors.joining(", "));
      System.out.println(c + ": " + group);
    }

    /**
     * S: Schmid, Stalder, Suter
     * K: Kobler
     * M: Müller, Meier, Maurer, Meier, Meck, Mülle
     */
  }

  public static void comparator() {
    List<Country> countries = new ArrayList<Country>();
    countries.add(new Country());
    countries.add(new Country());
    countries.add(new Country());

    // bad example
    List<Country> sorted = countries.stream()
      .sorted((c1, c2) -> c1.getArea().compareTo(c2.getArea()))
      .collect(Collectors.toList());
    System.out.println(sorted);

    // use this
    List<Country> sorted2 = countries.stream()
      .sorted(Comparator.comparing(Country::getArea))
      .collect(Collectors.toList());
    System.out.println(sorted2);

    // reverse order:
    // .sorted(reverseOrder(comparing(...)))

    // or add min/max
    // .min(comparing(...))
    // .max(comparing(...))

    // sorting
    // Collections.sort(list, Comparator.comparing(Person::getLastName)
    //   .thenComparing(Person::getFirstName));
  }

  public static void primitiveStream() {
    IntStream ints1 = IntStream.of(1, 2, 3, 40, 4000);
    System.out.println(ints1);

    IntStream ints2 = IntStream.range(0, 100); // 0 to 99
    System.out.println(ints2);

    IntStream ints3 = IntStream.rangeClosed(0, 100); // 0 to 100
    System.out.println(ints3);
  }

  public static Stream<String> anonymize(Stream<String> names, Set<String> critical) {
    return names
        .map(name -> {
            if (critical.contains(name)) {
                return "?".repeat(name.length());
            } else {
                return name;
            }
        });
  }

  public static void main(String[] args) {
    Map<Integer, List<String>> groups = Stream.of("Peter Müller", "Patrick Hauser", "Manuel Schnell")
      .collect(Collectors.groupingBy(String::length));

    // {12=[Peter Müller], 14=[Patrick Hauser, Manuel Schnell]}
    System.out.println(groups);

    groups.values().stream().forEach(System.out::println);
  }
  
}
