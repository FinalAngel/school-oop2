import java.util.Comparator;

/**
 * example use
 * 
 * Comparator<Date> comp = new DateComparator;
 * Collections.sort(Date, comp);
 */

public class DateComparator implements Comparator<Date> {
  
  public int compare(Date first, Date second) {
    return first.getMonth() - second.getMonth();
  }

}
