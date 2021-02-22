import static java.util.Collections.unmodifiableSet;

import java.util.HashSet;
import java.util.Set;

public class Modul {
    private final String name;
    private final int credits;
    private final Set<Modul> voraussetzungen = new HashSet<>();

    public Modul(String name, int credits) {
        this.name = name;
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public void addVoraus(Modul modul) {
        voraussetzungen.add(modul);
    }

    public Set<Modul> getVoraus() {
        return unmodifiableSet(voraussetzungen);
    }

    public int totalCredits() {
        return totalCredits(new HashSet<>()); // mit leerem Set starten
    }

    private int totalCredits(Set<Modul> visited) {
        if (visited.contains(this)) {
            return 0;
        }
        int sum = getCredits();
//        visited.add(this);
        for (Modul m : voraussetzungen) {
            sum += m.totalCredits(visited);
        }
        return sum;
    }
}
