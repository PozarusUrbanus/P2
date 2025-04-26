package Domace_naloge;

import java.io.File;
import java.util.Scanner;

public class DN05black {

    static int sirina = 0;
    static int visina = 0;

    public static int[][] preberiZacetnoPostavitev(String imeDatoteke) throws Exception {
        File file = new File(imeDatoteke);
        if (!file.exists()) {
            System.out.println("Napaka: datoteka ne obstaja.");
            System.exit(1);
        }

        Scanner datoteka = new Scanner(file);

        // Read dimensions
        if (!datoteka.hasNextLine()) {
            System.out.println("Napaka: Manjka podatek o dimenzijah igralne povrsine.");
            System.exit(1);
        }

        String dimenzije = datoteka.nextLine();
        String[] dimenzijeArray = dimenzije.split("x");
        if (dimenzijeArray.length != 2) {
            System.out.println("Napaka: Nepravilen podatek o dimenzijah igralne povrsine.");
            System.exit(1);
        }

        try {
            sirina = Integer.parseInt(dimenzijeArray[0]);
            visina = Integer.parseInt(dimenzijeArray[1]);
            if (sirina <= 0 || visina <= 0) {
                System.out.println("Napaka: Dimenzija mora biti pozitivna.");
                System.exit(1);
            }
        } catch (NumberFormatException e) {
            System.out.println("Napaka: Nepravilen podatek o dimenzijah igralne povrsine.");
            System.exit(1);
        }

        int[][] povrsina = new int[visina][sirina];

        // Read number of ships
        if (!datoteka.hasNextLine()) {
            System.out.println("Napaka: Manjka podatek o stevilu ladij.");
            System.exit(1);
        }

        int stLadij;
        try {
            stLadij = Integer.parseInt(datoteka.nextLine());
            if (stLadij < 0) {
                System.out.println("Napaka: Stevilo ladij ne sme biti negativno.");
                System.exit(1);
            }
        } catch (NumberFormatException e) {
            System.out.println("Napaka: Nepravilen podatek o stevilu ladij.");
            System.exit(1);
            return null; // This line will never be reached, but it's here to satisfy the compiler.
        }

        // Read ships data
        for (int i = 0; i < stLadij; i++) {
            if (!datoteka.hasNextLine()) {
                System.out.println("Napaka: Podatek o stevilu ladij se ne ujema s stevilom vnosov.");
                System.exit(1);
            }

            String vrstica = datoteka.nextLine();
            String[] podatki = vrstica.split(" ");
            if (podatki.length != 5) {
                System.out.println("Napaka: Nepravilen podatek o postavitvi ladje.");
                System.exit(1);
            }

            int igralec, x, y, dolzina, smer = -1;
            try {
                igralec = Integer.parseInt(podatki[0]);
                x = Integer.parseInt(podatki[1]);
                y = Integer.parseInt(podatki[2]);
                dolzina = Integer.parseInt(podatki[3]);
                char smerC = podatki[4].charAt(0);

                if (igralec < 0 || igralec > 1 || x < 0 || y < 0 || dolzina <= 0) {
                    System.out.println("Napaka: Nepravilen podatek o postavitvi ladje.");
                    System.exit(1);
                }

                switch (smerC) {
                    case 'S': smer = 0; break; // Sever
                    case 'J': smer = 1; break; // Jug
                    case 'V': smer = 2; break; // Vzhod
                    case 'Z': smer = 3; break; // Zahod
                    default:
                        System.out.println("Napaka: Nepravilen podatek o postavitvi ladje.");
                        System.exit(1);
                }

                // Place the ship
                for (int j = 0; j < dolzina; j++) {
                    switch (smer) {
                        case 0: // Sever
                            if (y - j >= 0) {
                                povrsina[y - j][x] = 1 + igralec;
                            }
                            break;
                        case 1: // Jug
                            if (y + j < visina) {
                                povrsina[y + j][x] = 1 + igralec;
                            }
                            break;
                        case 2: // Vzhod
                            if (x + j < sirina) {
                                povrsina[y][x + j] = 1 + igralec;
                            }
                            break;
                        case 3: // Zahod
                            if (x - j >= 0) {
                                povrsina[y][x - j] = 1 + igralec;
                            }
                            break;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Napaka: Nepravilen podatek o postavitvi ladje.");
                System.exit(1);
            }
        }
        return povrsina;
    }

    public static void izpisiPostavitev(int[][] postavitev) {
        for (int i = 0; i < postavitev.length; i++) {
            for (int j = 0; j < postavitev[i].length; j++) {
                int igralec = postavitev[i][j];

                if (igralec > 0) {
                    int dolzina = 1;
                    int smer = -1;
                    int x = j;
                    int y = i;

                    if (x + 1 < postavitev[i].length && postavitev[i][x + 1] == igralec) {
                        smer = 0; // Horizontalno desno
                        while (x + dolzina < postavitev[i].length && postavitev[i][x + dolzina] == igralec) {
                            dolzina++;
                        }
                    } else if (y + 1 < postavitev.length && postavitev[y + 1][j] == igralec) {
                        smer = 1; // Vertikalno dol
                        while (y + dolzina < postavitev.length && postavitev[y + dolzina][j] == igralec) {
                            dolzina++;
                        }
                    }

                    System.out.println("Igralec: " + (igralec - 1) + "  Dolzina: " + dolzina + "  Smer: " + smer + "  Koordinate premca: (" + x + "," + y + ")");

                    for (int k = 0; k < dolzina; k++) {
                        if (smer == 0) {
                            postavitev[i][x + k] = -1; // Horizontalno
                        } else if (smer == 1) {
                            postavitev[y + k][x] = -1; // Vertikalno
                        }
                    }
                }
            }
        }
    }

    /*
    for (int l = 0; l < visina; l++) {
        for (int m = 0; m < sirina*2; m++) {
            for (int k = 0; k < dolzina; k++) {
                if (k == 0) {
                    if (l == y && m == x && smer == 0 && (polje[l+k][m]!= 1 || polje[l+k][m]!=12 || polje[l+k][m]!=2 || polje[l+k][m]!=21)) {//sever
                        polje[l+k][m] = igralec + 1;
                    } else if (l == y && m == x && smer == 1 && (polje[l-k][m]!=1 || polje[l-k][m]!=12 || polje[l-k][m]!=2 || polje[l-k][m]!=21)) {//jug
                        polje[l-k][m] = igralec + 1;
                    } else if (l == y && m == x && smer == 2 && (polje[l][m-k]!=1 || polje[l][m-k]!=12 || polje[l][m-k]!=2 || polje[l][m-k]!=21)) {//vzhod
                        polje[l][m-k] = igralec + 1;
                    } else if (l == y && m == x && smer == 3 && (polje[l][m+k]!=1 || polje[l][m+k]!=12 || polje[l][m+k]!=2 || polje[l][m+k]!=21)) {//zahod
                        polje[l][m+k] = igralec + 1;
                    }
                } else {
                    if (l == y && m == x && smer == 0 && (polje[l+k][m]!=1 || polje[l+k][m]!=12 || polje[l+k][m]!=2 || polje[l+k][m]!=21)) {//sever
                        polje[l+k][m] = igralec + 1 + 10;
                    } else if (l == y && m == x && smer == 1 && (polje[l-k][m]!=1 || polje[l-k][m]!=12 || polje[l-k][m]!=2 || polje[l-k][m]!=21)) {//jug
                        polje[l-k][m] = igralec + 1 + 10;
                    } else if (l == y && m == x && smer == 2 && (polje[l][m-k]!=1 || polje[l][m-k]!=12 || polje[l][m-k]!=2 || polje[l][m-k]!=21)) {//vzhod
                        polje[l][m-k] = igralec + 1 + 10;
                    } else if (l == y && m == x && smer == 3 && (polje[l][m+k]!=1 || polje[l][m+k]!=12 || polje[l][m+k]!=2 || polje[l][m+k]!=21)) {//zahod
                        polje[l][m+k] = igralec + 1 + 10;
                    }
                }
            }
        }
    }*/
    public static void main(String[] args) throws Exception {

        int[][] povrsina = preberiZacetnoPostavitev(args[1]);
        izpisiPostavitev(povrsina);
    }
}


/*
import java.io.File;
import java.util.Scanner;

public class Domace_naloge.DN05 {

    static int sirina = 0;
    static int visina = 0;

    static int x;
    static int y;

    static int stLadij = 0;
    public static int[][] preberiZacetnoPostavitev(String imeDatoteke) throws Exception {

        File dat = new File(imeDatoteke);
        if (!dat.exists()) {
            System.out.println("Napaka: datoteka ne obstaja.");
            System.exit(1);
        }
        Scanner datoteka = new Scanner(dat);

        String[] dimenzijeSez = new String[2];
        try {
            String dimenzije = datoteka.nextLine();
            dimenzijeSez = dimenzije.split("x");
        } catch (Exception e) {
            System.out.println("Napaka: Manjka podatek o dimenzijah igralne povrsine.");
            System.exit(1);
        }

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

        int[][] zapisPodatkov = new int[stLadij][5];//dvodimenzionalna tabela

        for (int i = 0; i < stLadij; i++) {// igralec (0 ali 1), x, y, dolzina, smer ladje
            String vrstica = "";
            try {
                vrstica = datoteka.nextLine();//berem ladje
            } catch (Exception e) {
                System.out.println("Napaka: Podatek o stevilu ladij se ne ujema s stevilom vnosov.");
                System.exit(1);
            }

            String[] podatki = vrstica.split(" ");

            if (podatki.length != 5) {
                System.out.println("Napaka: Nepravilen podatek o postavitvi ladje.");
                System.exit(1);
            }

            String smer = podatki[4];

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

    static void izpisiPostavitev(int[][] postavitev){
        for(int i=0; i<stLadij; i++){
            System.out.printf("Igralec: %d  Dolzina: %d  Smer: %d  Koordinate premca: (%d,%d)\n",
                    postavitev[i][0], postavitev[i][3], postavitev[i][4], postavitev[i][1], postavitev[i][2]);
        }
    }

    public static int[][] izdelajIgralnoPovrsino(int[][] postavitev) {

        int [][] polje = new int[visina][sirina*2];
        for (int i = 0; i < postavitev.length; i++) {
            int igralec = postavitev[i][0];
            x = postavitev[i][1];
            y = postavitev[i][2];
            int dolzina = postavitev[i][3];
            int smer = postavitev[i][4];

            if (igralec == 1) {
                x += sirina;
            }

            if(sirina < 10 || visina < 10) {
                x += sirina - 5;
                y += visina - 10;
            }else if (sirina > 10 || visina > 10) {
                x += sirina-visina+1;
                y += visina-sirina+1;
            }
            if (sirina < visina) {
                x += sirina - 7;
                y += visina - 17;
            }


            //Preverjam če je sploh ladja v igralnem polju
            boolean neveljavnaLadja = false;
            switch (smer) {
                case 0: //sever
                    if (y + dolzina > visina)
                        neveljavnaLadja = true;
                    break;
                case 1: //jug
                    if (y - dolzina + 1 < 0)
                        neveljavnaLadja = true;
                    break;
                case 2: //vzhod
                    if ((x - dolzina + 1 < 0) || (x - dolzina + 1 < sirina) && (igralec == 1) || (x - dolzina > sirina*2) && (igralec == 1))
                        neveljavnaLadja = true;
                    break;
                case 3: //zahod
                    if ((x + dolzina > sirina) && (igralec == 0) || (x + dolzina > sirina*2) && (igralec == 1) )
                        neveljavnaLadja = true;
                    break;
            }
            if (neveljavnaLadja) // preskočim če vidim da je neveljavna
                continue;


            // Polja ladje: največ 100 segmentov
            int[][] koordinate = new int[100][2];
            int stKoordinat = 0;
            boolean prekrivanje = false;

            for (int k = 0; k < dolzina; k++) {
                int vodoravno = x;
                int navpicno = y;

                switch (smer) {
                    case 0: navpicno += k; break;//sever
                    case 1: navpicno -= k; break;//jug
                    case 2: vodoravno -= k; break;//vzhod
                    case 3: vodoravno += k; break;//zahod
                }

                if (vodoravno < 0 || vodoravno >= sirina * 2 || navpicno < 0 || navpicno >= visina) {
                    prekrivanje = true;
                    break;
                }
                if (polje[navpicno][vodoravno] != 0) {
                    prekrivanje = true;
                    break;
                }
                koordinate[stKoordinat][0] = navpicno;
                koordinate[stKoordinat][1] = vodoravno;
                stKoordinat++;
            }

            if (!prekrivanje) {
                for (int k = 0; k < stKoordinat; k++) {
                    int ny = koordinate[k][0];
                    int nx = koordinate[k][1];
                    if (k == 0) {
                        polje[ny][nx] = igralec + 1; //premec
                    } else {
                        polje[ny][nx] = igralec + 1 + 10; //ostalo
                    }
                }
            }
        }
        return polje;
    }

    public static void izrisiIgralnoPovrsino(int[][] igralnaPovrsina) {
        for (int i = 0; i < igralnaPovrsina.length; i++) {
            if (i == 0 || (i == sirina) && (visina == sirina)) {
                for (int j = 0; j < igralnaPovrsina[i].length+3; j++) {
                    System.out.print("# ");
                }
                System.out.println();
            }
            for (int j = 0; j < igralnaPovrsina[i].length; j++) {
                if (j == 0 || j == igralnaPovrsina[i].length / 2) {
                    System.out.print("# ");
                }
                if (igralnaPovrsina[i][j] == 1 || igralnaPovrsina[i][j] == 2) {
                    System.out.print("p ");
                } else if (igralnaPovrsina[i][j] == 11 || igralnaPovrsina[i][j] == 12) {
                    System.out.print("t ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.print("#");
            System.out.println();
        }
        for (int j = 0; j < igralnaPovrsina[0].length + 3; j++) {
            System.out.print("# ");
        }
    }

    public static int[][] povecajIgralnoPovrsino(int[][] postavitev, String noveDimenzije){
        String [] nove_dimenzije = noveDimenzije.split("x");
        int[][] polja = new int[postavitev.length][nove_dimenzije.length];

        if(nove_dimenzije.length == 2){
            try {
                int s = Integer.parseInt(nove_dimenzije[0]);
                int v = Integer.parseInt(nove_dimenzije[1]);
                if (s > sirina) sirina = s;
                if (v > visina) visina = v;
            } catch (Exception e) {
                // ignoriramo - obdržimo stare dimenzije
            }
        }

        if(Integer.parseInt(nove_dimenzije[0]) > sirina && Integer.parseInt(nove_dimenzije[1]) > visina){
            sirina = Integer.parseInt(nove_dimenzije[0]);
            visina = Integer.parseInt(nove_dimenzije[1]);
        }else if (Integer.parseInt(nove_dimenzije[0]) < 10) {
            sirina = 10;
            visina = Integer.parseInt(nove_dimenzije[1]);
        }else if (Integer.parseInt(nove_dimenzije[1]) < 10) {
            sirina = Integer.parseInt(nove_dimenzije[0]);
            visina = 10;
        }


        izrisiIgralnoPovrsino(izdelajIgralnoPovrsino(postavitev));
        return polja;
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
 */