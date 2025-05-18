package DN09;

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
            sc.nextLine();//PRAZNA LINIJA

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
            if (ukaz.equals("najblizja")){
                //izpisi();
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


    }


}

class Postaja {
    private int ID;
    private String ime;
    private int x;
    private int y;
    private int cakajoci;

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

    @Override
    public String toString() {
        return ID + " " + ime + " [" + x + "," + y + "] cakajoci: " + cakajoci;
    }
}

class Avtobus {
    private int ID;
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

    @Override
    public String toString() {
        return ID + " (" + steviloPotnikov + ") - " + (trenutnaPostaja != null ? trenutnaPostaja.getIme() : "null");
    }
}

class Linija {
    private int ID;
    private String barva;

    private Postaja[] postaje;
    private int stPostaj;//stevilo postaj

    private Avtobus[] avtobusi;
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

    @Override
    public String toString() {
        return "Linija " + ID + " - " + postaje[0].getIme() + " -> " + postaje[1].getIme() + " (bus)";
    }
}

