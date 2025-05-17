package banka;

public class TekociRacun extends Racun {
    private double limit;

    public TekociRacun(String stevilka, double limit) {
        super(stevilka);
        this.limit = limit;
    }

    @Override
    public boolean dvig(double znesek) {
        if (znesek > limit || znesek <= 0) return false;
        return super.dvig(znesek);
    }

    @Override
    public String opisRacuna() {
        return String.format("tekoči, limit: %.2f EUR", limit);
    }
}
