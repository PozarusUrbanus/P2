package kodirniki;

public class Kodiranje {
    private Kodirnik kodirnik;

    public Kodiranje(Kodirnik kodirnik) {
        this.kodirnik = kodirnik;
    }

    public String zakodiranjeBesedila(String besedilo){
        kodirnik.ponastavi();
        StringBuilder zakodirano = new StringBuilder();

        for (int i = 0; i < besedilo.length(); i++) {
            int znak = besedilo.charAt(i);
            zakodirano.append((char) kodirnik.zakodiraj(znak));
        }

        return zakodirano.toString();
    }

    public String odkodiranjeBesedila(String besedilo){
        kodirnik.ponastavi();
        StringBuilder odkodirano = new StringBuilder();
        for (int i = 0; i < besedilo.length(); i++) {
            int znak = besedilo.charAt(i);
            odkodirano.append((char) kodirnik.odkodiraj(znak));
        }
        return odkodirano.toString();
    }


}
