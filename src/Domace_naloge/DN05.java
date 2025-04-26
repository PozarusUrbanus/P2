package Domace_naloge;

import java.io.File;
import java.util.Scanner;

public class DN05 {

    static int sirina = 0;
    static int visina = 0;

    static int x;
    static int y;

    static int stLadij = 0;

    /**
     * Zbiranje vseh podatkov in razvrstitev
     * @param imeDatoteke
     * @return zapis podatkov in sicer pravilno razvrscene
     * @throws Exception
     */
    public static int[][] preberiZacetnoPostavitev(String imeDatoteke) throws Exception {
        //Berem datoteko, in pregledam če obstaja
        File dat = new File(imeDatoteke);
        if (!dat.exists()) {
            System.out.println("Napaka: datoteka ne obstaja.");
            System.exit(1);
        }
        Scanner datoteka = new Scanner(dat);

        //Zapišem dimenzije v tabelo Stringov
        String[] dimenzijeSez = new String[2];
        try {
            String dimenzije = datoteka.nextLine();
            dimenzijeSez = dimenzije.split("x");
        } catch (Exception e) {
            System.out.println("Napaka: Manjka podatek o dimenzijah igralne povrsine.");
            System.exit(1);
        }

        //Pridobim podatke o sirini in visini
        try {
            sirina = Integer.parseInt(dimenzijeSez[0]);
            visina = Integer.parseInt(dimenzijeSez[1]);
        } catch (Exception e) {
            System.out.println("Napaka: Nepravilen podatek o dimenzijah igralne povrsine.");
            System.exit(1);
        }

        if (sirina <= 0 || visina <= 0) {
            System.out.println("Napaka: Dimenzija mora biti pozitivna.");
            System.exit(1);
        }

        //Pregledujem koliko je ladij
        if (!datoteka.hasNextLine()) {
            System.out.println("Napaka: Manjka podatek o stevilu ladij.");
            System.exit(1);
        }
        try {
            stLadij = Integer.parseInt(datoteka.nextLine());//število vrstic oziroma ladij
            if (stLadij < 0) {
                System.out.println("Napaka: Stevilo ladij ne sme biti negativno.");
                System.exit(1);
            }
        } catch (Exception e) {
            System.out.println("Napaka: Nepravilen podatek o stevilu ladij.");
            System.exit(1);
            return null;
        }

        //za zapis ladij po vrsti in njihovih podatkov
        int[][] zapisPodatkov = new int[stLadij][5];//dvodimenzionalna tabela

        for (int i = 0; i < stLadij; i++) {// igralec (0 ali 1), x, y, dolzina, smer ladje
            String vrstica = "";
            try {
                vrstica = datoteka.nextLine();//berem po ladjah
            } catch (Exception e) {
                System.out.println("Napaka: Podatek o stevilu ladij se ne ujema s stevilom vnosov.");
                System.exit(1);
            }

            String[] podatki = vrstica.split(" ");//znebim se presledkov ki so v datoteki

            if (podatki.length != 5) {
                System.out.println("Napaka: Nepravilen podatek o postavitvi ladje.");
                System.exit(1);
            }

            String smer = podatki[4];

            //razvrscanje smeri iz crke v stevilo
            switch (smer) {
                case "S":
                    podatki[4] = "0";
                    break; // Sever
                case "J":
                    podatki[4] = "1";
                    break; // Jug
                case "V":
                    podatki[4] = "2";
                    break; // Vzhod
                case "Z":
                    podatki[4] = "3";
                    break; // Zahod
                default:
                    System.out.println("Napaka: Nepravilen podatek o postavitvi ladje.");
                    System.exit(1);
            }

            //Zapisovanje podatkov v seznam za vsako ladjo
            try {
                for (int j = 0; j < 5; j++) {
                    zapisPodatkov[i][j] = Integer.parseInt(podatki[j]);
                }

                int igralec = zapisPodatkov[i][0];
                int x = zapisPodatkov[i][1];
                int y = zapisPodatkov[i][2];

                if (igralec != 0 && igralec != 1 || x < 0 || y < 0) {
                    System.out.println("Napaka: Nepravilen podatek o postavitvi ladje.");
                    System.exit(1);
                }
            } catch (NumberFormatException e) {
                System.out.println("Napaka: Nepravilen podatek o postavitvi ladje.");
                System.exit(1);
            }
        }
        datoteka.close();
        return zapisPodatkov;
    }

    /**
     * Izpiše vn podatke (igralec, dolzina, smer, koordinate (x,y)
     * @param postavitev
     */
    static void izpisiPostavitev(int[][] postavitev){
        for(int i=0; i<stLadij; i++){
            System.out.printf("Igralec: %d  Dolzina: %d  Smer: %d  Koordinate premca: (%d,%d)\n",
                    postavitev[i][0], postavitev[i][3], postavitev[i][4], postavitev[i][1], postavitev[i][2]);
        }
    }

    /**
     * Izdelovanje povrsine kako bojo ladjice stale na mrezi
     * @param postavitev
     * @return polje - mrezo kje so ladjice
     */
    public static int[][] izdelajIgralnoPovrsino(int[][] postavitev) {
        int [][] polje = new int[visina][sirina*2];

        //gledam čez vse ladje
        for (int i = 0; i < stLadij; i++) { //lahko bi bil pogoj i<postavitev.length
            int igralec = postavitev[i][0];//shranim igralca
            x = postavitev[i][1];//shranim x
            y = postavitev[i][2];//shranim y
            int dolzina = postavitev[i][3];//shranim dolzino
            int smer = postavitev[i][4];//shranim smer

            //Ker je igralec 1 v desnem polju moramo pristeti sirino
            if (igralec == 1) {
                x += sirina;
            }

            //preverjam če je sploh ladja v igralnem polju
            boolean neveljavnaLadja = false;//na začetku je vedno veljavna
            switch (smer) {
                case 0://sever
                    if (y + dolzina > visina)
                        neveljavnaLadja = true;
                    break;
                case 1://jug
                    if (y - dolzina + 1 < 0)
                        neveljavnaLadja = true;
                    break;
                case 2://vzhod
                    if ((x - dolzina + 1 < 0) || (x - dolzina + 1 < sirina) && (igralec == 1) || (x - dolzina > sirina*2) && (igralec == 1))
                        neveljavnaLadja = true;
                    break;
                case 3://zahod
                    if ((x + dolzina > sirina) && (igralec == 0) || (x + dolzina > sirina*2) && (igralec == 1) )
                        neveljavnaLadja = true;
                    break;
            }
            if (neveljavnaLadja)//preskočim če vidim da je neveljavna
                continue;

            //najvec jih je st_ladij
            int[][] koordinate = new int[stLadij][2];//shranim koordinate
            int stKoordinat = 0;//koliko koordinat bom shranil
            boolean prekrivanje = false;

            for (int k = 0; k < dolzina; k++) {
                int vodoravno = x;
                int navpicno = y;

                switch (smer) {//nove koordinate odvisne od smeri
                    case 0: navpicno += k; break;//sever
                    case 1: navpicno -= k; break;//jug
                    case 2: vodoravno -= k; break;//vzhod
                    case 3: vodoravno += k; break;//zahod
                }

                //gledam če koordinate izven prostora
                if (vodoravno < 0 || vodoravno >= sirina * 2 || navpicno < 0 || navpicno >= visina) {
                    prekrivanje = true;
                    break;
                }
                //gledam če se prekrivajo že s katero ladjo
                if (polje[navpicno][vodoravno] != 0) {
                    prekrivanje = true;
                    break;
                }
                //shranim koordinate
                koordinate[stKoordinat][0] = navpicno;//po visini
                koordinate[stKoordinat][1] = vodoravno;//po sirini
                stKoordinat++;//pristevam da vem koliko je shranjenih
            }

            //preverjam če so prekrivanja
            if (!prekrivanje) {
                //v primeru da ga ni shranim
                for (int k = 0; k < stKoordinat; k++) {
                    int y_koor = koordinate[k][0];
                    int x_koor = koordinate[k][1];
                    if (k == 0) {
                        polje[y_koor][x_koor] = igralec + 1;//premec ladje
                    } else {
                        polje[y_koor][x_koor] = igralec + 1 + 10;//trup
                    }
                }
            }
        }
        return polje;
    }

    /**
     * Izpis povrsine
     * @param igralnaPovrsina
     */
    public static void izrisiIgralnoPovrsino(int[][] igralnaPovrsina) {
        //grem cez
        for (int i = 0; i < igralnaPovrsina.length; i++) {
            //v primeru da je prva vrstica
            if (i == 0) {
                for (int j = 0; j < igralnaPovrsina[i].length+3; j++) {
                    System.out.print("# ");
                }
                System.out.println();
            }
            //gre po vrstici in izpise podatke
            for (int j = 0; j < igralnaPovrsina[i].length; j++) {
                if (j == 0 || j == igralnaPovrsina[i].length / 2) {//na sredini med poljema igralcev
                    System.out.print("# ");
                }
                if (igralnaPovrsina[i][j] == 1 || igralnaPovrsina[i][j] == 2) {
                    System.out.print("p ");//za premce
                } else if (igralnaPovrsina[i][j] == 11 || igralnaPovrsina[i][j] == 12) {
                    System.out.print("t ");//za trupe
                } else {
                    System.out.print("  ");
                }
            }
            System.out.print("#");//na koncu usake vrstice
            System.out.println();
        }
        //zadjna vrstica
        for (int j = 0; j < igralnaPovrsina[0].length + 3; j++) {
            System.out.print("# ");
        }
    }

    /**
     * Povečanje mreze na dimenzije ki jih dobimo
     * @param postavitev
     * @param noveDimenzije
     * @return postavitev
     */
    public static int[][] povecajIgralnoPovrsino(int[][] postavitev, String noveDimenzije){
        String [] nove_dimenzije = noveDimenzije.split("x");

        int novaSirina = sirina;
        int novaVisina = visina;

        try {
            if(nove_dimenzije.length == 2){
                int s = Integer.parseInt(nove_dimenzije[0]);
                int v = Integer.parseInt(nove_dimenzije[1]);
                //ce je nova vecja kot prejsnja bo nova enaka novi prav tako visina
                if (s > sirina)
                    novaSirina = s;
                if (v > visina)
                    novaVisina = v;
            }
        } catch (Exception e){
        }

        //premaknemo x in y koordinate da bojo na "sredini"
        int premikX = (novaSirina - sirina) / 2;
        int premikY = (novaVisina - visina) / 2;

        //preverjam ce je liha sirina in visina
        if ((novaSirina - sirina) % 2 != 0){
            premikX = premikX + 1;
        }
        if ((novaVisina - visina) % 2 != 0){
            premikY = premikY + 1;
        }
        //spremenim koordinate na nove koordinate
        for (int k = 0; k < postavitev.length; k++) {
            postavitev[k][1] += premikX;
            postavitev[k][2] += premikY;
        }

        //staro sirino in visino nastavimo na novo
        sirina = novaSirina;
        visina = novaVisina;

        //izris
        izrisiIgralnoPovrsino(izdelajIgralnoPovrsino(postavitev));
        return postavitev;
    }

    public static void main(String[] args) throws Exception {

        int[][] podatki = preberiZacetnoPostavitev(args[1]);

        if (args[0].equals("postavitev"))
            izpisiPostavitev(podatki);

        if (args[0].equals("povrsina"))
            izrisiIgralnoPovrsino(izdelajIgralnoPovrsino(podatki));

        if (args[0].equals("povecanje")){
            povecajIgralnoPovrsino(preberiZacetnoPostavitev(args[1]), args[2]);
        }
    }
}