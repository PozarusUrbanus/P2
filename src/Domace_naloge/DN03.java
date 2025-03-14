package Domace_naloge;

import java.io.File;
import java.util.*;
public class DN03 {
    public static void main(String[] args) throws Exception {
        //pridobimo in nalozimo datoteko
        Scanner datoteka = new Scanner(new File(args[0]));

        int n = Integer.parseInt(args[1]);
        int seme = Integer.parseInt(args[2]);
        //prva vrstica datoteke je int
        int st_besed = datoteka.nextInt();

        String [] besede = new String [st_besed];
        //nalozimo iz vrstic v tabelo
        for (int i = 0; i < st_besed; i++) {
            besede[i] = datoteka.next();
        }

        Random naklj = new Random(seme);//nakljucno stevilo

        String geslo = "";
        for (int i = 0; i < n; i++) {
            String beseda = besede[naklj.nextInt(st_besed)];//najprej nakljucna beseda
            geslo += beseda.charAt(naklj.nextInt(beseda.length()));//potem nakljucni char do dolzine besede
        }
        System.out.println(geslo);
        datoteka.close();
    }
}
