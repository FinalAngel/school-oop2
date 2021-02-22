package family;

import java.util.ArrayList;

public class Family {

    public static String relation(Person first, Person second) {
        if (first == null || second == null) {
            throw new NullPointerException();
        } else if (first == second) {
            return first + " ist sich selbst";
        }

        // children and parents
        if (first.mother() == second || first.father() == second) {
            if (first.sex() == Sex.FEMALE) {
                return first + " ist die Tochter von " + second; // forgot 'von'
            } else {
                return first + " ist der Sohn von " + second;
            }
        } else if (first == second.mother()) {
            return first + " ist die Mutter von " + second;
        } else if (first == second.father()) {
            return first + " ist der Vater von " + second;
        }

        // grandparents and above
        var distanceFirstSecond = ancestorDistance(first, second);
        if (distanceFirstSecond >= 2) {
            var ur = "Ur-".repeat(distanceFirstSecond - 2);
            if (first.sex() == Sex.FEMALE) {
                return first + " ist die " + ur + "Grossmutter von " + second;
            } else {
                return first + " ist der " + ur + "Grossvater von " + second;
            }
        }
        var distanceSecondFirst = ancestorDistance(second, first);
        if (distanceSecondFirst >= 2) {
            var ur = "Ur-".repeat(distanceSecondFirst - 2); // used wrong variable
            if (first.sex() == Sex.FEMALE) {
                return first + " ist die " + ur + "Enkelin von " + second;
            } else {
                return first + " ist der " + ur + "Enkel von " + second;
            }
        }

        // siblings
        if (sameMother(first, second) || sameFather(first, second)) {
            var halb = "Halb-";
            if (sameMother(first, second) && sameFather(first, second)) {
                halb = "";
            }
            if (first.sex() == Sex.FEMALE) {
                return first + " ist die " + halb + "Schwester von " + second;
            } else {
                return first + " ist der " + halb + "Bruder von " + second;
            }
        }

        // cousins of arbitrary degrees
        var cousinDegree = cousinDegree(first, second);
        if (cousinDegree >= 1) {
            var cou = "Cou-".repeat(cousinDegree - 1);
            if (first.sex() == Sex.FEMALE) { // used 'second' instead
                return first + " ist die " + cou + "Cousine von " + second;
            } else {
                return first + " ist der " + cou + "Cousin von " + second;
            }
        }

        // no specific relation
        return first + " und " + second + " sind irgendwie verwandt";
    }

    static boolean sameMother(Person first, Person second) {
        return first.mother() == second.mother() && first.mother() != null; // didn't take null into account
    }

    static boolean sameFather(Person first, Person second) {
        return first.father() == second.father() && first.father() != null; // same here (doesn't count as separate bug)
    }

    /**
     * Returns the "distance" between the 'first' person and their (potential)
     * ancestor, the 'second' person. A distance of 1 means 'first' is a parent
     * of 'second', 2 means a grandparent, and so on. If the first is not an
     * ancestor of the second, -1 is returned.
     */
    static int ancestorDistance(Person first, Person second) { // switched 'first' and 'second' in method
        var ancestorLevel = second.parents();
        var distance = 1;
        while (!ancestorLevel.contains(first)) {
            ancestorLevel = parentsOf(ancestorLevel);
            if (ancestorLevel.isEmpty()) {
                return -1;
            }
            distance++;
        }
        return distance;
    }

    /**
     * Returns the degree between two cousins, 'first' and 'second'. A
     * degree of 1 means normal cousins (at least one common grandparent),
     * 2 means a common great-grandparent, and so on. If 'first' and
     * 'second' are not cousins at all, a number < 1 ist returned.
     */
    static int cousinDegree(Person first, Person second) {
        var ancestorLevelFirst = parentsOf(first.parents());
        var ancestorLevelSecond = parentsOf(second.parents());
        var degree = 1;
        while (noMatch(ancestorLevelFirst, ancestorLevelSecond)) {
            ancestorLevelFirst = parentsOf(ancestorLevelFirst);
            ancestorLevelSecond = parentsOf(ancestorLevelSecond);
            if (ancestorLevelFirst.isEmpty() || ancestorLevelSecond.isEmpty()) {
                return -1;
            }
            degree++;
        }
        return degree;
    }

    static ArrayList<Person> parentsOf(ArrayList<Person> people) {
        var parents = new ArrayList<Person>();
        for (var person : people) {
            if (person.mother() != null) {
                parents.add(person.mother());
            }
            if (person.father() != null) {
                parents.add(person.father());
            }
        }
        return parents;
    }

    static boolean noMatch(ArrayList<Person> list1, ArrayList<Person> list2) {
        for (var person : list1) {
            if (list2.contains(person)) {
                return false;
            }
        }
        return true;
    }
}
