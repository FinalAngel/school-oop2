/**
 * List<String> nationalTage = new ArrayList<>(List.of(
 *  new Date(1, 8),
 *  new Date(3, 10),
 * ))
 * 
 * Collections.sort(nationalTage);
 */

public class Date implements Comparable<Date> {
  
  private int day;
  private int month;

  public Date(int day, int month) {
    this.day = day;
    this.month = month;
  }

  public int getDay() {
    return day;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public int getMonth() {
    return month;
  }

  public void setMonth(int month) {
    this.month = month;
  }

  public int compareTo(Date other) {
    // if (month < other.month) {
    //   return -1;
    // } else if (month == other.month) {
    //   return 0;
    // } else {
    //   return 1;
    // }

    // more elegant
    // return month - other.month;

    // include everything
    if (month != other.month) {
      return month - other.month;
    } else {
      return day - other.day;
    }
  }

}
