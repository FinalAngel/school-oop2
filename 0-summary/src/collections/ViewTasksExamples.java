import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ViewTasksExamples {
  
  // remove something
  public static void removeSignature(List<String> email) {
    int index = email.indexOf("-- ");
    if (index >= 0) {
      email.subList(index, email.size()).clear();
    }
  }

  // count names
  public static int countNames(Map<String, Map<String, String>> phoneBook, String[] names) {
    List<String> phoneNames = new ArrayList<String>(phoneBook.keySet());
    List<String> cleanNames = new ArrayList<String>(new HashSet<>(List.of(names)));
    phoneNames.retainAll(cleanNames);

    return phoneNames.size();
  }

  // merge lists
  public static Set<Object> peopleFromNameMap(Map<String, Set<String>> nameMap) {
    Set<Object> people = new HashSet<Object>();
    Set<Map.Entry<String, Set<String>>> entries = nameMap.entrySet();

    for (Map.Entry<String, Set<String>> entry : entries) {
      for (String firstName : entry.getValue()) {
        System.out.println(firstName);
        // people.add(new Person(firstName, entry.getKey()));
      }
    }

    return people;
  }

  // remove duplicates
  public static Set<Object> duplicateNumbers(Map<String, Object> phoneBook) {
    List<Object> numbers = new ArrayList<>(phoneBook.values());
    Set<Object> duplicates = new HashSet<>();

    for (int i = 0; i < numbers.size() - 1; i++) {
      var num = numbers.get(i);
      if (numbers.subList(i + 1, numbers.size()).contains(num)) {
        duplicates.add(num);
      }
    }
    
    return duplicates;
  }
}
