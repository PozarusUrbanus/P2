import java.io.File;
import java.util.Scanner;

public class DN05 {

    static int sirina = 0;
    static int visina = 0;

    public static int[][] preberiZacetnoPostavitev(String imeDatoteke) throws Exception {

        File dat = new File(imeDatoteke);
        if (!dat.exists()) {
            System.out.println("Napaka: datoteka ne obstaja.");
            System.exit(1);
        }
        Scanner datoteka = new Scanner(new File(imeDatoteke));
        String dimenzije = datoteka.nextLine();
        sirina = Integer.parseInt(dimenzije.split("x")[0]);
        visina = Integer.parseInt(dimenzije.split("x")[1]);

        int[][] povrsina = new int[visina][sirina];//dvodimenzionalna tabela

        int stLadij = Integer.parseInt(datoteka.nextLine());//število vrstic oziroma ladij

        for (int i = 0; i < stLadij; i++) {// igralec (0 ali 1), x, y, dolzina, smer ladje
            String vrstica = datoteka.nextLine();//berem ladje

            String[] podatki = vrstica.split(" ");

            int igralec = Integer.parseInt(podatki[0]);
            int x = Integer.parseInt(podatki[1]);
            int y = Integer.parseInt(podatki[2]);
            int dolzina = Integer.parseInt(podatki[3]);
            char smerC = podatki[4].charAt(0);
            int smer = 0; //int smeri

            switch (smerC) {
                case 'S': smer = 0; break; // Sever
                case 'J': smer = 1; break; // Jug
                case 'V': smer = 2; break; // Vzhod
                case 'Z': smer = 3; break; // Zahod
            }

            // Postavimo ladjo glede na smer
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
        }
        return povrsina;
    }

    public static void izpisiPostavitev(int[][] postavitev) {
        // Preiščemo igralno površino
        for (int i = 0; i < postavitev.length; i++) {
            for (int j = 0; j < postavitev[i].length; j++) {
                int igralec = postavitev[i][j];

                // Če je to mesto del ladje, ki še ni bila obdelana
                if (igralec > 0) {
                    // Začnemo iskati dolžino ladje
                    int dolzina = 1;
                    int smer = -1;
                    int x = j;
                    int y = i;

                    if (x + 1 < postavitev[i].length && postavitev[i][x + 1] == igralec) {
                        smer = 0; // Horizontalno desno
                        while (x + dolzina < postavitev[i].length && postavitev[i][x + dolzina] == igralec) {
                            dolzina++;
                        }
                    }
                    // Preverimo, ali je ladja vertikalna (gor/dol)
                    else if (y + 1 < postavitev.length && postavitev[y + 1][j] == igralec) {
                        smer = 1; // Vertikalno dol
                        while (y + dolzina < postavitev.length && postavitev[y + dolzina][j] == igralec) {
                            dolzina++;
                        }
                    }

                    // Izpišemo podatke o ladji
                    System.out.println("Igralec: " + (igralec - 1) + "  Dolzina: " + dolzina + "  Smer: " + smer + "  Koordinate premca: (" + x + "," + y + ")");

                    // Označimo celice ladje kot obdelane z -1
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



    public static void main(String[] args) throws Exception {

        int[][] povrsina = preberiZacetnoPostavitev(args[1]);
/*
        for (int i = 0; i < visina; i++) {
            for (int j = 0; j < sirina; j++) {
                System.out.print(povrsina[i][j] + " ");
            }
            System.out.println();
        }
*/

        izpisiPostavitev(povrsina);

    }
}
