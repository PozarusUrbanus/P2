package igra;

import java.util.Random;

public class Logika {

    static boolean konec;
    static int [][] polja = null;
    static int tocke;

    static Random rnd = new Random();//generator za nakljucna stevila

    /**
     * Metoda nastavi velikost polja na podane velikosti, nastavi točke na 0 in doda dve ploščici
     * @param velikost
     */
    public static void zacniNovoIgro(int velikost) {
        polja = new int [velikost][velikost];
        tocke = 0;
        konec = false;

        dodajPloscico();
        dodajPloscico();
    }

    /**
     * Metoda nam doda dve ploščici(2 ali 4) na polje nekam kjer tretnutno ni nobene ploščice.
     */
    static void dodajPloscico() {
        int dolzina = polja.length;
        int prazna = 0;
        //prestejem koliko je praznih
        for (int i = 0; i < dolzina; i++) {
            for(int j = 0; j < dolzina; j++) {
                if(polja[i][j] == 0) {
                    prazna++;
                }
            }
        }

        int naklj = rnd.nextInt(prazna);//random stevilo od 0 do koliko je praznih
        int stevec = 0;//se sprehajam po praznih

        for (int i = 0; i < dolzina; i++) {
            for(int j = 0; j < dolzina; j++) {
                if(polja[i][j] == 0) {
                    if (stevec == naklj) {
                        int stevilo;
                        //če je 0 je 4 (ravno 0.1 procenta)
                        if (rnd.nextInt(9) == 0) {
                            stevilo = 4;
                        } else {
                            stevilo = 2;
                        }
                        polja[i][j] = stevilo;
                    }
                    stevec++;
                }
            }
        }
    }

    public static void koncajIgro(){
        if (konec) {
            System.exit(0);
        }else {
            konec = true;
        }
    }

    public static int vrniPloscico(int i, int j) {
        return polja[i][j];
    }

    public static int vrniTocke() {




        return 0;
    }

    public static boolean jeZmagal() {
        for (int i = 0; i < polja.length; i++) {
            for (int j = 0; j < polja[i].length; j++) {
                if (polja[i][j] == 2048)
                    return true;
            }
        }
        return false;
    }

    public static boolean jeKonec() {
        return konec;
    }

    public static boolean premikLevo(int[] vrstica) {
        boolean izhod = false;
        int[] nova = new int[vrstica.length];
        int indeks = 0;

        for (int i = 0; i < vrstica.length; i++) {//gre čez vrstico in če vrstica ni enaka nič je nova tista vrstica
            if (vrstica[i] != 0) {
                nova[indeks++] = vrstica[i];
            }
        }

        for (int i = 0; i < nova.length - 1; i++) {
            if (nova[i] != 0 && nova[i] == nova[i + 1]) {
                nova[i] *= 2;
                tocke += nova[i];
                nova[i + 1] = 0;
                izhod = true;
            }
        }

        int[] koncna = new int[vrstica.length];
        indeks = 0;
        for (int i = 0; i < nova.length; i++) {
            if (nova[i] != 0) {
                koncna[indeks++] = nova[i];
            }
        }

        for (int i = 0; i < vrstica.length; i++) {
            if (vrstica[i] != koncna[i]) {
                vrstica[i] = koncna[i];
                izhod = true;
            }
        }
        return izhod;
    }

    public static void naslednjaPoteza(int smer) {
        boolean izhod = false;
        int velikost = polja.length;

        switch(smer) {
            case 0://levo
                for (int i = 0; i < velikost; i++) {
                    if (premikLevo(polja[i])) {
                        izhod = true;
                    }
                }
                break;
            case 1://desno
                for (int i = 0; i < velikost; i++) {
                    rotiraj();
                    rotiraj();
                    if (premikLevo(polja[i])) {
                        izhod = true;
                    }
                    rotiraj();
                    rotiraj();
                }
                break;
            case 2://gor
                for (int i = 0; i < velikost; i++) {
                    rotiraj();
                    rotiraj();
                    rotiraj();
                    if (premikLevo(polja[i])) {
                        izhod = true;
                    }
                    rotiraj();
                }
                break;
            case 3://dol
                for (int i = 0; i < velikost; i++) {
                    rotiraj();
                    if (premikLevo(polja[i])) {
                        izhod = true;
                    }
                    rotiraj();
                    rotiraj();
                    rotiraj();
                }
                break;
        }
        if (izhod) {
            dodajPloscico();
        }
    }
    /**
     * Zarotira igralno polje (tabela polja) za 90 stopinj
     * v smeri urinega kazalca (v desno).
     * Rezultat metode je zarotirana tabela <code>polja</code>.
     */
    static int VELIKOST = 4;
    private static void rotiraj() {
        // najprej transponiramo tabelo - zamenjamo stolpce in vrstice
        for (int i = 0; i < VELIKOST; i++) {
            for (int j = i + 1; j < VELIKOST; j++) {
                int tmp = polja[i][j];
                polja[i][j] = polja[j][i];
                polja[j][i] = tmp;
            }
        }
        // če rotiramo v desno (v smeri urinega kazalca), obrnemo še vrstni red stolpcev
        for (int i = 0; i < VELIKOST; i++) {
            for (int j = 0; j < VELIKOST / 2; j++) {
                int tmp = polja[i][j];
                polja[i][j] = polja[i][VELIKOST - 1 - j];
                polja[i][VELIKOST - 1 - j] = tmp;
            }
        }
    }

}
