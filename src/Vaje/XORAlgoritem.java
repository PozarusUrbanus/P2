/*package Vaje;

public class XORAlgoritem implements Kodirnik {
    private String geslo;
    private int indeks;

    public XORAlgoritem(String geslo) {
        this.geslo = geslo;
        this.indeks = 0;
    }

    @Override
    public int zakodiraj(int vrednost) {
        int znakGesla = geslo.charAt(indeks % geslo.length());
        indeks++;
        return vrednost ^ znakGesla;
    }

    @Override
    public int odkodiraj(int vrednost) {
        // XOR je simetričen: šifriranje = dešifriranje
        return zakodiraj(vrednost);
    }

    @Override
    public void ponastavi() {
        indeks = 0;
    }
}
*/