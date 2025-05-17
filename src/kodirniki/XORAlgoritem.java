package kodirniki;

public class XORAlgoritem implements Kodirnik {
    private String geslo;
    private int indeks;

    public XORAlgoritem(String geslo) {
        this.geslo = geslo;
        this.indeks = 0;
    }

    public int zakodiraj(int vrednost) {
        int znak = geslo.charAt(indeks % geslo.length());
        indeks++;
        return vrednost ^ znak;
    }

    public int odkodiraj(int vrednost) {
        return zakodiraj(vrednost);
    }

    public void ponastavi() {
        indeks = 0;
    }
}
