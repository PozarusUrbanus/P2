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
            int indPostaj = 0;//indeksi
            int indAvtobusov = 0;

            for (int i = 0; i < stPostaj; i++) {

                String [] postajce = sc.nextLine().split(",");
                int ID = Integer.parseInt(postajce[0]);
                String ime = postajce[1];
                int X = Integer.parseInt(postajce[2]);
                int Y = Integer.parseInt(postajce[3]);
                String [] linijeID = postajce[4].split(";");


                String [] avtobusiPodatki = postajce[5].split("[();]");

                int cakajoci = Integer.parseInt(postajce[6]);

                Postaja p = new Postaja(ID, ime, X, Y, cakajoci);
                postaje[indPostaj++] = p;

                for (int j = 0; j < avtobusiPodatki.length - 1; j++) {
                    int IDavtobus = 0;
                    int stPotnikov = 0;
                    if (j % 2 == 0 && !avtobusiPodatki[j].isEmpty()) {
                        IDavtobus = Integer.parseInt(avtobusiPodatki[j]);
                    } else if (j % 2 == 1 && !avtobusiPodatki[j].isEmpty()) {
                        stPotnikov = Integer.parseInt(avtobusiPodatki[j]);
                    };

                    Avtobus obstaja = isciA(IDavtobus);

                    if (obstaja == null) {
                        Avtobus a = new Avtobus(IDavtobus, stPotnikov);
                        a.setTrenutnaPostaja(p);
                        avtobusi[indAvtobusov++] = a;
                    }else {
                        obstaja.setTrenutnaPostaja(p);
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
                Linija l = new Linija(IDlinije);
                l.setBarva(barva);

                //dodajanje avtobusov
                for (int j = 0; j < IDAvtobusa.length ; j++) {
                    if(IDAvtobusa[j] != null) {
                        int IDavtobusa = Integer.parseInt(IDAvtobusa[j]);
                        Avtobus a = isciA(IDavtobusa);
                        if(a != null) {
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
            }

            sc.close();
            izpisi();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    static void izpisi() {
        for (Linija l : linije) {
            if (l != null) {
                System.out.print("Linija " + l.getID() + " - ");
                Postaja[] p = l.getPostaje();
                for (int i = 0; i < p.length && p[i] != null; i++) {
                    System.out.print(p[i].getIme());
                    if (i != p.length - 1 && p[i + 1] != null) {
                        System.out.print(" -> ");
                    }
                }
            }
            System.out.println();
        }
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

