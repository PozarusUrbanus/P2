package Kvizi.Kviz2;

public class Kviz2_6 {

    public static double koren(int x, int d) {

        int c = 0;
        while ((c + 1) * (c + 1) <= x && (c * c <= x)) {
            c++;
        }

        double skupniKoren = c;

        for (int k = 1; k <= d; k++) {
            double cx = 0;
            double koren = skupniKoren;
            double koren2 = skupniKoren;
            double mest = Math.pow(10, -k);

            for (int m = 1; m <= 9; m++) {
                koren2 = skupniKoren + m / Math.pow(10, k);
                if ((koren2 * koren2 <= x) && (koren2 +1 / Math.pow(10, k)) * (koren2 +1 / Math.pow(10, k))>x) {
                    cx = m/Math.pow(10, k);
                    break;
                }
            }

            skupniKoren += cx;
            if (Math.abs(koren - koren2) < mest) {
                break;
            }
        }
        return Math.round(skupniKoren * Math.pow(10, d)) / Math.pow(10, d);
    }

    public static void main(String[] args) {
        System.out.printf("%.2f", koren(10, 2));


    }
}
