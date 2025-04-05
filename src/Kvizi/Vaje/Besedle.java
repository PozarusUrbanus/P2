package Kvizi.Vaje;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Besedle {

    // Konstante barv
    static final int BELA = 0;
    static final int CRNA = 1;
    static final int RUMENA = 2;
    static final int ZELENA = 3;

    // ANSI ukazi (za barvni izpis)
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_GREEN_BG = "\u001b[42m";
    static final String ANSI_YELLOW_BG = "\u001b[43m";
    static final String ANSI_WHITE_BG = "\u001b[47;1m";
    static final String ANSI_BLACK_BG = "\u001b[40m";
    static final String ANSI_WHITE = "\u001b[37m";
    static final String ANSI_BLACK = "\u001b[30m";

    static final String abeceda = "ABCČDEFGHIJKLMNOPRSŠTUVZŽ"; // Veljavne črke
    static final int MAX_POSKUSOV = 6; // Število poskusov

    static String[] seznamBesed; // Seznam vseh možnih besed
    static String iskanaBeseda; // Iskana beseda trenutne igre
    static int[] barveAbecede; // Barve črk pri izpisu abecede

    static Scanner sc = new Scanner(System.in);

    // Izpiše znak v izbrani barvi
    static void izpisiZBarvo(char znak, int barva) {
        String slog;
        if (barva == ZELENA) {
            slog = ANSI_BLACK + ANSI_GREEN_BG;
        } else if (barva == RUMENA) {
            slog = ANSI_BLACK + ANSI_YELLOW_BG;
        } else if (barva == BELA) {
            slog = ANSI_BLACK + ANSI_WHITE_BG;
        } else {
            slog = ANSI_WHITE + ANSI_BLACK_BG;
        }
        System.out.print(slog + " " + znak + " " + ANSI_RESET);
    }

    // Prebere seznam besed iz datoteke
    static void preberiBesede(String datoteka) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(datoteka));
        seznamBesed = new String[sc.nextInt()];

        for (int i = 0; i < seznamBesed.length; i++) {
            String beseda = sc.next();
            beseda = beseda.toUpperCase();
            seznamBesed[i] = beseda;
        }
    }

    // Pripravi vse za novo igro
    static void novaIgra() {
        Random r = new Random();
        int stevilo = r.nextInt(seznamBesed.length);

        iskanaBeseda = seznamBesed[stevilo];
        barveAbecede = new int[25];
    }

    // Izpiše abecedo
    static void izpisiAbecedo() {
        System.out.print("Preostale črke: ");
        for (int i = 0; i < abeceda.length(); i++) {
            izpisiZBarvo(abeceda.charAt(i), barveAbecede[i]);
        }
        System.out.println();
    }

    // Ali je beseda veljavna?
    static boolean veljavnaBeseda( String beseda) {
        if (beseda.length() != 5) {
            System.out.println("Nepravilna dolžina besede!");
            return false;
        }
        for (int i = 0; i < 5; i++) {
            if (abeceda.indexOf(beseda.charAt(i)) < 0) {
                System.out.println("V besedi so neveljavni znaki!");
                return false;
            }
        }
        return true;
    }

    // Določi barve črk v ugibani besedi
    static int[] pobarvajBesedo(String ugibanaBeseda) {
        for (int i = 0; i < 5; i++) {
            if (iskanaBeseda.charAt(i) == ugibanaBeseda.charAt(i)) {
                izpisiZBarvo(ugibanaBeseda.charAt(i), ZELENA);
                for (int j = 0; j < abeceda.length(); j++) {
                    if (iskanaBeseda.charAt(i) == abeceda.charAt(j)) {
                        barveAbecede[j] = ZELENA;
                    }
                }
            }else if (ugibanaBeseda.indexOf(iskanaBeseda.charAt(i)) > 1) {
                izpisiZBarvo(ugibanaBeseda.charAt(i), RUMENA);
                for (int j = 0; j < abeceda.length(); j++) {
                    if (ugibanaBeseda.charAt(i) == abeceda.charAt(j)) {
                        barveAbecede[j] = RUMENA;
                    }
                }
            } else if (iskanaBeseda.charAt(i) != ugibanaBeseda.charAt(i)) {
                izpisiZBarvo(ugibanaBeseda.charAt(i), CRNA);
                for (int j = 0; j < abeceda.length(); j++) {
                    if (ugibanaBeseda.charAt(i) == abeceda.charAt(j)) {
                        barveAbecede[j] = CRNA;
                    }
                }
            }
        }
        System.out.println();
        return new int[0];
    }


    // Izpiši besedo
    static void izpisiBesedo(String ugibanaBeseda, int[] barve) {
        // TODO: implementiraj
    }


    // Izvede eno igro
    static void igra() {
        novaIgra();
        System.out.println(iskanaBeseda);

        int poskus = 1;
        boolean uganil = false;
        while (poskus <= MAX_POSKUSOV) {
            izpisiAbecedo();
            System.out.printf("Poskus %d/%d: ", poskus, MAX_POSKUSOV);
            String ugibanaBeseda = sc.nextLine().toUpperCase();

            // Preveri veljavnost
            if (!veljavnaBeseda(ugibanaBeseda))
                continue;

            // Pobarvaj crke v besedi (namigi)
            int[] barve = pobarvajBesedo(ugibanaBeseda);

            // Izpiši pobarvano besedo
            izpisiBesedo(ugibanaBeseda, barve);

            if (ugibanaBeseda.equals(iskanaBeseda)) {
                uganil = true;
                break;
            }
            poskus++;
        }

        if (uganil) {
            System.out.printf("Bravo! Besedo si uganil/a v %d poskusih.\n", poskus);
        } else {
            System.out.printf("Žal ti ni uspelo. Pravilna beseda: %s\n", iskanaBeseda);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        preberiBesede("viri/besede.txt");

        while (true) {
            igra();
            System.out.print("Nova igra? (d/n): ");
            String odg = sc.nextLine();
            if (odg.toLowerCase().charAt(0) != 'd') {
                break;
            }
        }
    }
}