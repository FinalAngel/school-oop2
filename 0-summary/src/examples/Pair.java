import java.util.Objects;

public class Pair<E, T> {

  private E first;
  private T second;

  public Pair(E first, T second) {
    this.first = Objects.requireNonNull(first);
    this.second = Objects.requireNonNull(second);
  }

  public E first() {
    return first;
  }

  public T second() {
    return second;
  }

  public String toString() {
    return "(" + first + ", " + second + ")";
  }

}
