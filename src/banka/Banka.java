package banka;

public class Banka {
    private Racun[] racuni = new Racun[500];
    private int steviloRacunov = 0;

    public boolean dodajTekociRacun(String stevilka, double limit) {
        if (najdiRacun(stevilka) != null) return false;
        if (steviloRacunov >= 500) return false;
        racuni[steviloRacunov++] = new TekociRacun(stevilka, limit);
        return true;
    }

    public boolean dodajVarcevalniRacun(String stevilka, double obresti) {
        if (najdiRacun(stevilka) != null) return false;
        if (steviloRacunov >= 500) return false;
        racuni[steviloRacunov++] = new VarcevalniRacun(stevilka, obresti);
        return true;
    }

    private Racun najdiRacun(String stevilka) {
        for (int i = 0; i < steviloRacunov; i++) {
            if (racuni[i].getStevilka().equals(stevilka)) return racuni[i];
        }
        return null;
    }

    public boolean polog(String stevilka, double znesek) {
        Racun r = najdiRacun(stevilka);
        return (r != null) && r.polog(znesek);
    }

    public boolean dvig(String stevilka, double znesek) {
        Racun r = najdiRacun(stevilka);
        return (r != null) && r.dvig(znesek);
    }

    public void dodajObresti() {
        for (int i = 0; i < steviloRacunov; i++) {
            if (racuni[i] instanceof VarcevalniRacun) {
                ((VarcevalniRacun) racuni[i]).dodajObresti();
            }
        }
    }

    public void izpisiRacune() {
        izpisiRacune(null);
    }

    public void izpisiRacune(Boolean varcevalni) {
        int st = 0;
        for (int i = 0; i < steviloRacunov; i++) {
            Racun r = racuni[i];
            if (varcevalni == null ||
                    (varcevalni && r instanceof VarcevalniRacun) ||
                    (!varcevalni && r instanceof TekociRacun)) {
                System.out.println(r);
                st++;
            }
        }
        System.out.println("Skupaj: " + st + " računov");
    }
}
