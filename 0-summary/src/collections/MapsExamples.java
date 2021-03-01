import java.util.HashMap;
import java.util.Map;

public class MapsExamples {
  
  public MapsExamples() {
    mapsExamples();
  }

  private void mapsExamples() {
    Map<String, Double> salary = new HashMap<>();
    salary.put("Max", 80000.0);
    salary.put("Muster", 120000.0);

    System.out.println(salary);
    // creates key/value pairs
    // [{ "Max": 80000.0 }, { "Muster": 120000.0 }]
  }

}
