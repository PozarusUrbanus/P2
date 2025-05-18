package Kvizi.Kviz3;

import java.io.File;
import java.util.Scanner;

class Tocka {
    private int x;
    private int y;

    public Tocka(int x, int y) {
        this.x = x;
        this.y = y;
    }

    static Tocka[] preberiTocke(String imeDatoteke) {
        int stevec = 0;
        try (java.util.Scanner sc = new java.util.Scanner(new java.io.File(imeDatoteke))) {
            while (sc.hasNextInt()) {
                sc.nextInt();
                sc.nextInt();
                stevec++;
            }
        } catch (Exception e) {

            return new Tocka[0];
        }

        Tocka[] tocke = new Tocka[stevec];

        try (java.util.Scanner sc2 = new java.util.Scanner(new java.io.File(imeDatoteke))) {
            for (int i = 0; i < stevec; i++) {
                int x = sc2.nextInt();
                int y = sc2.nextInt();
                tocke[i] = new Tocka(x, y);
            }
        } catch (Exception e) {

            return new Tocka[0];
        }

        return tocke;
    }


    public static String tabelaToString(Tocka[] tocke) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < tocke.length; i++) {
            sb.append("(").append(tocke[i].x).append(",").append(tocke[i].y).append(")");
            if (i != tocke.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}

public class Kviz3_5 {
    public static void main(String[] args) throws Exception {
        System.out.println(Tocka.tabelaToString(Tocka.preberiTocke("viri/tocke.txt")));
    }
}