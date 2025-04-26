import java.io.*;
import java.util.*;

class Planet {
    String ime;
    int r;

    public Planet(String ime, int r) {
        this.ime = ime;
        this.r = r;
    }

    public String dobiIme() {
        return ime;
    }

    public double povrsina() {
        return 4 * Math.PI * r * r;
    }
}

public class DN07 {
    static Planet[] preberiPlanete(String datoteka) throws Exception {
        Planet[] planeti = new Planet[8];
        int indeks = 0;

        Scanner sc = new Scanner(new File(datoteka));

        while (indeks < 8) {
            String vrstica = sc.nextLine();
            String[] podatki = vrstica.split(":");
            String ime = podatki[0];
            planeti[indeks++] = new Planet(ime, Integer.parseInt(podatki[1]));
        }
        sc.close();
        return planeti;
    }

    public static void main(String[] args) throws Exception {
        Planet[] planeti = preberiPlanete(args[0]);
        String[] iskani = args[1].split("\\+");

        double skupnaPovrsina = 0;

        for (int i = 0; i < iskani.length; i++) {
            String iskanoIme = iskani[i];
            for (int j = 0; j < planeti.length; j++) {
                Planet planet = planeti[j];
                if (planet.dobiIme().equalsIgnoreCase(iskanoIme)) {
                    skupnaPovrsina += planet.povrsina();
                }
            }
        }
        long povrsina = Math.round(skupnaPovrsina / 1000000);

        System.out.printf("Povrsina planetov \"%s\" je %d milijonov km2%n", args[1], povrsina);
    }
}