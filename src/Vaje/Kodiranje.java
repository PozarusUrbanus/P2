package Vaje;

public class Kodiranje {
    private Kodirnik kodirnik;

    public Kodiranje(Kodirnik kodirnik) {
        this.kodirnik = kodirnik;
    }

    public String zakodiranjeBesedila(String besedilo) {
        kodirnik.ponastavi();
        StringBuilder rezultat = new StringBuilder();
        for (int i = 0; i < besedilo.length(); i++) {
            int znak = besedilo.charAt(i);
            rezultat.append((char) kodirnik.zakodiraj(znak));
        }
        return rezultat.toString();
    }

    public String odkodiranjeBesedila(String besedilo) {
        kodirnik.ponastavi();
        StringBuilder rezultat = new StringBuilder();
        for (int i = 0; i < besedilo.length(); i++) {
            int znak = besedilo.charAt(i);
            rezultat.append((char) kodirnik.odkodiraj(znak));
        }
        return rezultat.toString();
    }
}
