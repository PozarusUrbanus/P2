package vinjete;

public class Vinjeta {

    private String reg;
    private String vinRazred;
    private String datum;
    private String vrsta;

    Vinjeta(String reg, String vinRazred, String datum, String vrsta) {
        this.reg = reg;
        this.vinRazred = vinRazred;
        this.datum = datum;
        this.vrsta = vrsta;
    }

    public String getReg() {
        return reg;
    }
    public String getVinRazred() {
        return vinRazred;
    }
    public String getDatum() {
        return datum;
    }
    public String getVrsta() {
        return vrsta;
    }

    @Override
    public String toString() {
        return String.format("%s [%s]: %s (%s)", reg, vinRazred, vrsta, datum);
    }
    /*public String toString2() {
        return String.format("%s: veljavna do %s", reg, datum.replace("3", "4"));
    }*/
}
