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

    public static void main(String[] args) throws Exception {
        if (args.length != 2 || !args[0].equals("postavitev")) {
            System.out.println("Uporaba: java DN05 postavitev <ime_datoteke>");
            System.exit(1);
        }

        int[][] povrsina = preberiZacetnoPostavitev(args[1]);
        izpisiPostavitev(povrsina);
    }
}