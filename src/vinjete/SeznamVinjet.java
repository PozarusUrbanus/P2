package vinjete;

import java.io.File;
import java.util.Scanner;

public class SeznamVinjet {

    Vinjeta[] prodaneVinjete = new Vinjeta[stVinjet];;

    static int stVinjet;

    public boolean preberiPodatke(String vir) throws Exception {
        try(Scanner sc = new Scanner(new File(vir))) {
            stVinjet = sc.nextInt();
            sc.nextLine();

            prodaneVinjete = new Vinjeta[stVinjet];

            for (int i = 0; i < stVinjet; i++) {
                String vrstica = sc.nextLine();
                String[] vinjetke = vrstica.split(";");
                String reg = vinjetke[0];
                String vinRazred = vinjetke[1];
                String datum = vinjetke[2];
                String vrsta = vinjetke[3];
                prodaneVinjete[i] = new Vinjeta(reg, vinRazred, datum, vrsta);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void izpisiVinjete() {
        System.out.printf("V sistemu so zabeležene prodane vinjete (%d):%n", stVinjet);
        for (Vinjeta v : prodaneVinjete) {
            System.out.println(v);
        }
    }

    public boolean preveriVinjeto(String reg) {
        for (Vinjeta v : prodaneVinjete) {
            if (v.getReg().equalsIgnoreCase(reg)) {
                return true;
            }
        }
        return false;
    }

    public void izpisiVinjete(String vrsta) {
        int st = 0;
        System.out.printf("V sistemu je tedenska vinjeta za naslednja vozila:%n");
        for (Vinjeta v : prodaneVinjete) {
            if (v.getVrsta().equalsIgnoreCase(vrsta)) {
                System.out.println(v);
                st++;
            }
        }
        System.out.printf("Skupaj tedenska vinjeta: %d", st);

    }

    /*void izpisiLetneVeljavnost() {
        System.out.println("Letne vinjete z datumi veljavnosti:");
        for (Vinjeta v : prodaneVinjete) {
            if (v.getVrsta().equalsIgnoreCase("letna")) {
                System.out.println(v.toString());
            }
        }
    }*/
}
