package banka;

public class VarcevalniRacun extends Racun {
    private double obresti; // npr. 0.0134 za 1.34%

    public VarcevalniRacun(String stevilka, double obresti) {
        super(stevilka);
        this.obresti = obresti;
    }

    public void dodajObresti() {
        //povecajStanje(getStanje() * obresti);
    }

    @Override
    public String opisRacuna() {
        return String.format("varčevalni, obrestna mera: %.2f%%", obresti * 100);
    }
}
