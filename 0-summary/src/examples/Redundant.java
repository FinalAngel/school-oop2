import java.util.Objects;

public class Redundant<M, B extends M> {
  
  private final M main;
  private final B backup;


  public Redundant(M main, B backup) {
    this.main = main;
    this.backup = Objects.requireNonNull(backup);
  }

  public M get() {
    if (main == null) {
      return backup;
    }

    return main;
  }

  public B getBackup() {
    return backup;
  }

}
