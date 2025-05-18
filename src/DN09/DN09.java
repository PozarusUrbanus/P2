//package DN09;

import java.io.File;
import java.util.*;

public class DN09 {

    static Postaja[] postaje;
    static Linija[] linije;
    static Avtobus[] avtobusi;

    static Avtobus isciA(int ID) {
        for (Avtobus a: avtobusi) {
            if (a != null && a.getID() == ID) {
                return a;
            }
        }
        return null;
    }

    static Postaja isciP(int ID) {
        for (Postaja p: postaje) {
            if (p != null && p.getID() == ID) {
                return p;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        Map<Integer, Linija> mapaLinij = new HashMap<>();
        List<Linija> seznamLinij = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File(args[0]));
            String[] stevila = sc.nextLine().split(",");
            int stPostaj = Integer.parseInt(stevila[0]);
            int stLinij = Integer.parseInt(stevila[1]);
            int stAvtobusov = Integer.parseInt(stevila[2]);
            sc.nextLine();

            postaje = new Postaja[stPostaj];
            linije = new Linija[stLinij];
            avtobusi = new Avtobus[stAvtobusov];

            //drugi del linij
            int indPostaj = 0;//indeksPostaj
            int indAvtobusov = 0;//inbdeksAvtobusov

            for (int i = 0; i < stPostaj; i++) {

                String [] postajce = sc.nextLine().split(",");
                int ID = Integer.parseInt(postajce[0]);
                String ime = postajce[1];
                int X = Integer.parseInt(postajce[2]);
                int Y = Integer.parseInt(postajce[3]);
                String [] linijeID = postajce[4].split(";");

                for (String linijaStr : linijeID) {
                    if (linijaStr.isEmpty()) continue;
                    int linijaID = Integer.parseInt(linijaStr);
                    if (!mapaLinij.containsKey(linijaID)) {
                        Linija l = new Linija(linijaID);
                        mapaLinij.put(linijaID, l);
                        seznamLinij.add(l); // ključni del – ohranja vrstni red
                    }
                }

                String [] avtobusiPodatki = postajce[5].split("[();]");

                int cakajoci = Integer.parseInt(postajce[6]);

                Postaja p = new Postaja(ID, ime, X, Y, cakajoci);
                postaje[indPostaj++] = p;
                if (avtobusiPodatki.length > 1) {
                    for (int j = 0; j < avtobusiPodatki.length - 1; j ++) {
                        // Preskoči prazne vrednosti
                        if (avtobusiPodatki[j].trim().isEmpty() ||
                                avtobusiPodatki[j + 1].trim().isEmpty()) {
                            continue;
                        }

                        int IDavtobus = Integer.parseInt(avtobusiPodatki[j].trim());
                        int stPotnikov = Integer.parseInt(avtobusiPodatki[j + 1].trim());

                        Avtobus obstaja = isciA(IDavtobus);
                        if (obstaja == null) {
                            Avtobus a = new Avtobus(IDavtobus, stPotnikov);
                            a.setTrenutnaPostaja(p);
                            avtobusi[indAvtobusov++] = a;
                        } else {
                            obstaja.setSteviloPotnikov(stPotnikov);
                            obstaja.setTrenutnaPostaja(p);
                        }

                    }
                }
            }
            sc.nextLine();//tista prazna vrstica

            int indLinij = 0;

            for (int i = 0; i < stLinij; i++) {
                String [] linija = sc.nextLine().split(",");
                int IDlinije = Integer.parseInt(linija[0]);
                String barva = linija[1];
                String [] IDAvtobusa = linija[2].split(";");
                String [] IDPostaj = linija[3].split("\\|");

                //natavljanje vrednosti
                Linija l = mapaLinij.get(IDlinije);
                if (l == null) {
                    // V varnostnem primeru, če linija še ni bila ustvarjena
                    l = new Linija(IDlinije);
                    mapaLinij.put(IDlinije, l);
                    seznamLinij.add(l);
                }
                l.setBarva(barva);

                //dodajanje avtobusov
                for (String idStr : IDAvtobusa) {
                    if (idStr != null && !idStr.isEmpty()) {
                        int IDavtobusa = Integer.parseInt(idStr);
                        Avtobus a = isciA(IDavtobusa);
                        if (a != null) {
                            l.dodajAvtobus(a);
                        }
                    }
                }
                //dodajanje postajam
                for (int j = 0; j < IDPostaj.length ; j++) {
                    if(IDPostaj[j] != null) {
                        int IDpostaj = Integer.parseInt(IDPostaj[j]);
                        Postaja p = isciP(IDpostaj);
                        if(p != null) {
                            l.dodajPostajo(p);
                        }
                    }
                }
                linije[indLinij++] = l;
            }

            sc.close();
            linije = seznamLinij.toArray(new Linija[0]);

            String ukaz = args[1];
            //IZPISOVANJE
            if (ukaz.equals("izpisi")) izpisi();
            if (ukaz.equals("najboljObremenjena")) izpisNajboljObremenjenePostaje(Integer.parseInt(args[2]));
            if (ukaz.equals("premik")) {
                int n = Integer.parseInt(args[2]);
                izpisiPoNPremikih(n);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //1.IZPIS
    static void izpisi() {
        for (Linija l : linije) {
            if (l != null) {
                System.out.print("Linija " + l.getID() + " - ");
                Postaja[] p = l.getPostaje();
                Avtobus[] avtobuski = l.getAvtobusi();

                for (int i = 0; i < p.length && p[i] != null; i++) {
                    System.out.print(p[i].getIme());

                    // Preveri, ali je na tej postaji kak avtobus te linije
                    boolean Bus = false;
                    for (Avtobus a : avtobuski) {
                        if (a != null && a.getTrenutnaPostaja() != null &&
                                a.getTrenutnaPostaja().getID() == p[i].getID()) {
                            Bus = true;
                            break;
                        }
                    }

                    if (Bus) {
                        System.out.print(" (bus)");
                    }
                    if (i < p.length - 1 && p[i + 1] != null) {
                        System.out.print(" -> ");
                    }
                }
                System.out.println();
            }
        }
    }
    //2.IZPIS
    static void izpisNajboljObremenjenePostaje(int kapaciteta) {
        Postaja najboljsa = null;
        double najmanjseRazmerje = Double.MAX_VALUE;
        int koncnaProstaMesta = 0;
        int koncniCakajoci = 0;

        for (Postaja p : postaje) {
            if (p == null) continue;
            int cakajoci = p.getCakajoci();
            int dovoljMest = 0;

            // Poišči vse linije, ki grejo čez to postajo
            for (Linija l : linije) {

                Postaja[] postaje = l.getPostaje();
                Avtobus[] avtobuski = l.getAvtobusi();
                boolean izvedljivo = false;

                for (Postaja pa : postaje) {
                    if (pa == null) continue;
                    if (pa.getID() == p.getID()) {
                        izvedljivo = true;
                        break;
                    }
                }

                if (izvedljivo) {
                    for (Avtobus a : avtobuski) {
                        if (a == null) continue;
                        int stPotnikov = a.getSteviloPotnikov();
                        int fraj = kapaciteta - stPotnikov;
                        if (fraj < 0) fraj = 0;
                        dovoljMest += fraj;
                    }
                }
            }

            double razmerje;
            if (cakajoci != 0) {
                razmerje = (double) dovoljMest / cakajoci;
            } else {
                razmerje = dovoljMest;
            }

            if (razmerje < najmanjseRazmerje || (razmerje == najmanjseRazmerje && p.getID() < najboljsa.getID())) {
                najboljsa = p;
                najmanjseRazmerje = razmerje;
                koncnaProstaMesta = dovoljMest;
                koncniCakajoci = cakajoci;
            }
        }

        if (najboljsa != null) {
            System.out.printf("Najbolj obremenjena postaja: %d %s%n", najboljsa.getID(), najboljsa.getIme());
            System.out.printf(Locale.US, "Cakajoci: %d, Stevilo prostih mest: %d, Razmerje: %.2f", koncniCakajoci, koncnaProstaMesta, najmanjseRazmerje);
        }
    }
    //3.IZPIS
    static void naslednjeStanje(){
        for (Linija l : linije) {
            if (l == null) continue;

            Postaja[] postaje = l.getPostaje();
            Avtobus[] avtobusi = l.getAvtobusi();

            for (Avtobus a : avtobusi) {
                if (a == null || a.getTrenutnaPostaja() == null) continue;

                int index = -1;
                // Poišči trenutno pozicijo avtobusa na liniji
                for (int i = 0; i < postaje.length && postaje[i] != null; i++) {
                    if (postaje[i].getID() == a.getTrenutnaPostaja().getID()) {
                        index = i;
                        break;
                    }
                }

                if (index == -1) continue; // ne najdeno, preskoči

                // Premik naprej ali nazaj
                if (a.jeNaprej()) {
                    if (index + 1 < postaje.length && postaje[index + 1] != null) {
                        a.setTrenutnaPostaja(postaje[index + 1]);
                    } else {
                        // Obrne smer, gre nazaj
                        a.obrniSmer();
                        a.setTrenutnaPostaja(postaje[index - 1]);
                    }
                } else {
                    if (index - 1 >= 0) {
                        a.setTrenutnaPostaja(postaje[index - 1]);
                    } else {
                        // Obrne smer, gre naprej
                        a.obrniSmer();
                        a.setTrenutnaPostaja(postaje[index + 1]);
                    }
                }
            }
        }
    }

    static void izpisiPoNPremikih(int n) {
        System.out.println("Zacetno stanje:");
        izpisi(); // trenutna metoda, ki prikazuje stanje

        naslednjeStanje();
        System.out.println("\nStanje po " + n + " premikih");
        izpisi();
    }

    static void casiPrihodov(int ID, int maxRazdalja) {
        for (Linija linija : linije) {
            if (linija == null) continue;

            Postaja[] postaje = linija.getPostaje();
            Avtobus[] avtobusi = linija.getAvtobusi();

            int indexCiljne = -1;
            for (int i = 0; i < postaje.length && postaje[i] != null; i++) {
                if (postaje[i].getID() == ID) {
                    indexCiljne = i;
                    break;
                }
            }

            if (indexCiljne == -1) continue; // Če postaje ni na liniji

            for (Avtobus a : avtobusi) {
                if (a == null || a.getTrenutnaPostaja() == null) continue;

                int indexAvtobusa = -1;
                for (int i = 0; i < postaje.length && postaje[i] != null; i++) {
                    if (postaje[i].getID() == a.getTrenutnaPostaja().getID()) {
                        indexAvtobusa = i;
                        break;
                    }
                }

                if (indexAvtobusa == -1) continue;

                int razdalja = Math.abs(indexAvtobusa - indexCiljne);

                if (razdalja <= maxRazdalja && razdalja > 0) {
                    System.out.printf("Avtobus %d na liniji %d je %d postaj stran%n",
                            a.getID(), linija.getSt(), razdalja);
                }
            }
        }
    }



}

class Postaja {
    private final int ID;
    private final String ime;
    private final int x;
    private final int y;
    private final int cakajoci;

    //konstruktor
    public Postaja(int ID, String ime, int x, int y, int cakajoci) {
        this.ID = ID;
        this.ime = ime;
        this.x = x;
        this.y = y;
        this.cakajoci = cakajoci;
    }
    //getterji
    public int getID() {
        return ID;
    }
    public String getIme() {
        return ime;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getCakajoci() {
        return cakajoci;
    }

    public String toString() {
        return ID + " " + ime + " [" + x + "," + y + "] cakajoci: " + cakajoci;
    }
}

class Avtobus {
    private final int ID;
    private int steviloPotnikov;
    private Postaja trenutnaPostaja;

    public Avtobus(int ID, int steviloPotnikov) {
        this.ID = ID;
        this.steviloPotnikov = steviloPotnikov;
        this.trenutnaPostaja = null;
    }

    public int getID() {
        return ID;
    }

    public int getSteviloPotnikov() {
        return steviloPotnikov;
    }

    public void setSteviloPotnikov(int steviloPotnikov) {
        this.steviloPotnikov = steviloPotnikov;
    }

    public Postaja getTrenutnaPostaja() {
        return trenutnaPostaja;
    }

    public void setTrenutnaPostaja(Postaja postaja) {
        this.trenutnaPostaja = postaja;
    }

    public String toString() {
        return ID + " (" + steviloPotnikov + ") - " + (trenutnaPostaja != null ? trenutnaPostaja.getIme() : "null");
    }


    //dodatek za delovanje tretje naloge
    private boolean naprej = true; // true = naprej, false = nazaj

    public boolean jeNaprej() {
        return naprej;
    }

    public void obrniSmer() {
        this.naprej = !this.naprej;
    }

}

class Linija {
    private final int ID;
    private String barva;

    private final Postaja[] postaje;
    private int stPostaj;//stevilo postaj

    private final Avtobus[] avtobusi;
    private int stAvtobusov;//stevilo avtobusov

    public Linija(int ID) {
        this.ID = ID;
        this.postaje = new Postaja[10];
        this.avtobusi = new Avtobus[5];
        this.stPostaj = 0;
        this.stAvtobusov = 0;
    }

    public void setBarva(String barva) {
        this.barva = barva;
    }

    public String getBarva() {
        return barva;
    }

    public int getID() {
        return ID;
    }

    public Postaja[] getPostaje() {
        return postaje;
    }

    public Avtobus[] getAvtobusi() {
        return avtobusi;
    }

    boolean dodajPostajo(Postaja postaja) {
        //preverjamo ce je vecje stevilo postaj od dolzine kamor jih zapisujemo
        if (stPostaj >= postaje.length) return false;
        postaje[stPostaj] = postaja;
        stPostaj++;
        return true;
    }

    boolean dodajAvtobus(Avtobus avtobus) {
        //preverjamo ce je vecje stevilo postaj od dolzine kamor jih zapisujemo
        if (stAvtobusov >= avtobusi.length) return false;
        avtobusi[stAvtobusov] = avtobus;
        stAvtobusov++;
        return true;
    }

    public String toString() {
        return "Linija " + ID + " - " + postaje[0].getIme() + " -> " + postaje[1].getIme() + " (bus)";
    }
}
