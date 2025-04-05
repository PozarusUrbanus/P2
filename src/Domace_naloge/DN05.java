package Domace_naloge;

import java.io.File;
import java.util.Scanner;

public class DN05 {

    static int sirina = 0;
    static int visina = 0;

    public static int[][] preberiZacetnoPostavitev(String imeDatoteke) throws Exception {
        Scanner datoteka = new Scanner(new File(imeDatoteke));
        String dimenzije = datoteka.nextLine();
        sirina = Integer.parseInt(dimenzije.split("x")[0]);
        visina = Integer.parseInt(dimenzije.split("x")[1]);

        System.out.println(sirina);

        datoteka.close();
        return new int[0][0];
    }


    public static void main(String[] args) throws Exception {
        preberiZacetnoPostavitev("viri/vhod.txt");
    }
}
