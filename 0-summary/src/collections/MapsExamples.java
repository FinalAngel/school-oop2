import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapsExamples {
  
  public MapsExamples() {
    mapsExamples();
  }

  private void mapsExamples() {
    // we use Double instead of double because it might return null
    Map<String, Double> salary = new HashMap<>();
    salary.put("Max", 80000.0);
    salary.put("Muster", 120000.0);

    System.out.println(salary);
    // creates key/value pairs
    // [{ "Max": 80000.0 }, { "Muster": 120000.0 }]

    Double maxSalary = salary.get("Max"); // provides the value or null
    if (maxSalary != null) {
      System.out.println(maxSalary);
    }

    // to iterate use entrySet to generate a list on the fly from a map
    Set<Map.Entry<String, Double>> entries = salary.entrySet();
    // operations on the entrySet will affect the map itself!!!
    for (Map.Entry<String, Double> entry : entries) {
      String name = entry.getKey();
      double lohn = entry.getValue();
      System.out.println(name + ": " + lohn);
    }
  }

}
