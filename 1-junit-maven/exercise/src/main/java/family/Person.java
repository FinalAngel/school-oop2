package family;

import java.util.ArrayList;

public class Person {

    private final String name;
    private final Sex sex;
    private final Person mother;
    private final Person father;

    public Person(String name, Sex sex, Person mother, Person father) {
        if (mother != null && mother.sex != Sex.FEMALE || father != null && father.sex != Sex.MALE) {
            throw new IllegalArgumentException("sex mismatch");
        }
        this.name = name;
        this.sex = sex;
        this.mother = mother;
        this.father = father;
    }

    public String name() {
        return name;
    }

    public Sex sex() {
        return sex;
    }

    public Person mother() {
        return mother;
    }

    public Person father() {
        return father;
    }

    public ArrayList<Person> parents() {
        var parents = new ArrayList<Person>();
        if (mother() != null) {
            parents.add(mother());
        }
        if (father() != null) {
            parents.add(father());
        }
        return parents;
    }

    public String toString() {
        return name;
    }
}
