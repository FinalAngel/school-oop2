package family;

import org.junit.jupiter.api.Test;

import static family.Sex.FEMALE;
import static family.Sex.MALE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FamilyTest {

    Person erna = new Person("Erna", FEMALE, null, null);
    Person hans = new Person("Hans", MALE, null, null);
    Person anna = new Person("Anna", FEMALE, erna, hans);
    Person tobi = new Person("Tobi", MALE, erna, hans);

    @Test
    public void testRelationParentsKids() {
        assertEquals("Erna ist die Mutter von Tobi", Family.relation(erna, tobi));
        assertEquals("Tobi ist der Sohn von Erna", Family.relation(tobi, erna));

        // testing children and parents
        // 1st issue: "von" was missing
        assertEquals("Anna ist die Tochter von Erna", Family.relation(anna, erna));
        assertEquals("Anna ist die Tochter von Hans", Family.relation(anna, hans));
        assertEquals("Tobi ist der Sohn von Erna", Family.relation(tobi, erna));
        assertEquals("Tobi ist der Sohn von Hans", Family.relation(tobi, hans));
    }

    @Test
    public void testRelationGrandparents() {
        Person barb = new Person("Barb", FEMALE, anna, null);
        Person mick = new Person("Mick", MALE, barb, null);
        Person tony = new Person("Tony", MALE, null, mick);

        // 2nd issue: first and second were switched in ancestorDistance
        assertEquals("Barb ist die Grossmutter von Tony", Family.relation(barb, tony));
        assertEquals("Anna ist die Ur-Grossmutter von Tony", Family.relation(anna, tony));
        assertEquals("Erna ist die Ur-Ur-Grossmutter von Tony", Family.relation(erna, tony));
        assertEquals("Hans ist der Ur-Ur-Grossvater von Tony", Family.relation(hans, tony));

        assertEquals("Barb ist die Enkelin von Erna", Family.relation(barb, erna));
        assertEquals("Barb ist die Enkelin von Hans", Family.relation(barb, hans));
        assertEquals("Mick ist der Ur-Enkel von Erna", Family.relation(mick, erna));
        assertEquals("Tony ist der Ur-Ur-Enkel von Erna", Family.relation(tony, erna));
    }

    @Test
    public void testRelationSiblings() {
        assertEquals("Anna ist die Schwester von Tobi", Family.relation(anna, tobi));
        assertEquals("Tobi ist der Bruder von Anna", Family.relation(tobi, anna));

        // via mother, father unknown
        Person bibi = new Person("Bibi", FEMALE, erna, null);
        assertEquals("Anna ist die Halb-Schwester von Bibi", Family.relation(anna, bibi));
        assertEquals("Bibi ist die Halb-Schwester von Anna", Family.relation(bibi, anna));

        // via father, mother unknown
        Person kurt = new Person("Kurt", MALE, null, hans);
        assertEquals("Anna ist die Halb-Schwester von Kurt", Family.relation(anna, kurt));
        assertEquals("Kurt ist der Halb-Bruder von Anna", Family.relation(kurt, anna));

        // all parents known
        Person sara = new Person("Sara", FEMALE, null, null);
        Person bert = new Person("Bert", MALE, sara, hans);
        assertEquals("Anna ist die Halb-Schwester von Bert", Family.relation(anna, bert));
        assertEquals("Bert ist der Halb-Bruder von Anna", Family.relation(bert, anna));
    }

    @Test
    public void testRelationCousins() {
        // 3rd issue: cousinDegree should be first.sex() and not second.sex()

        Person pepe = new Person("Pepe", MALE, anna, null);
        Person lily = new Person("Lily", FEMALE, null, tobi);
        assertEquals("Pepe ist der Cousin von Lily", Family.relation(pepe, lily));
        assertEquals("Lily ist die Cousine von Pepe", Family.relation(lily, pepe));

        Person nico = new Person("Nico", MALE, null, pepe);
        Person dirk = new Person("Dirk", MALE, lily, null);
        assertEquals("Nico ist der Cou-Cousin von Dirk", Family.relation(nico, dirk));
        assertEquals("Dirk ist der Cou-Cousin von Nico", Family.relation(dirk, nico));

        Person jane = new Person("Jane", FEMALE, null, nico);
        Person rebi = new Person("Rebi", FEMALE, null, dirk);
        assertEquals("Jane ist die Cou-Cou-Cousine von Rebi", Family.relation(jane, rebi));
        assertEquals("Rebi ist die Cou-Cou-Cousine von Jane", Family.relation(rebi, jane));
    }

        @Test
    public void testRelationNoSpecific() {
        // 4th issue: degree++ was missing in cousinDegree

        // Erna and Hans have children together, but this does not count as related
        assertEquals("Erna und Hans sind irgendwie verwandt", Family.relation(erna, hans));
        assertEquals("Hans und Erna sind irgendwie verwandt", Family.relation(hans, erna));

        // Tobi would be Karl's uncle, but this is not supported
        Person karl = new Person("Karl", MALE, anna, null);
        assertEquals("Karl und Tobi sind irgendwie verwandt", Family.relation(karl, tobi));
        assertEquals("Tobi und Karl sind irgendwie verwandt", Family.relation(tobi, karl));
    }
    

    @Test
    public void testRelationSelf() {
        assertEquals("Anna ist sich selbst", Family.relation(anna, anna));
        assertEquals("Tobi ist sich selbst", Family.relation(tobi, tobi));
    }

    @Test
    public void testSame() {
        assertTrue(Family.sameMother(anna, tobi));
        assertTrue(Family.sameFather(anna, tobi));
    }

}
