import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ModulTest {

    @Test
    public void testGetters() {
        Modul modul = new Modul("OOP2", 3);
        assertEquals("OOP2", modul.getName());
        assertEquals(3, modul.getCredits());
    }

    @Test
    public void testTotalCredits() {
        Modul eipr = new Modul("EIPR", 3);
        Modul oop1 = new Modul("OOP1", 3);
        oop1.addVoraus(eipr);
        Modul oop2 = new Modul("OOP2", 3);
        oop2.addVoraus(oop1);

        int result = oop2.totalCredits();

        assertEquals(9, result);
    }

//    @Test
//    public void testTotalCreditsOhneVoraus() {
//        Modul eipr = new Modul("EIPR", 3);
//        int result = eipr.totalCredits();
//        assertEquals(3, result);
//    }

//    @Test
//    public void testTotalCreditsSelfVoraus() {
//        Modul eipr = new Modul("EIPR", 3);
//        eipr.addVoraus(eipr);
//        int result = eipr.totalCredits();
//        assertEquals(3, result);
//    }
}
