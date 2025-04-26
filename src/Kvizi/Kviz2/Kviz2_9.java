package Kvizi.Kviz2;

public class Kviz2_9 {

    public static int vsotaSkupnihCifer(int a, int b)
    {
        boolean[] A = new boolean[10];
        boolean[] B = new boolean[10];

        while (a > 0) {
            A[a % 10] = true;
            a /= 10;
        }

        while (b > 0) {
            B[b % 10] = true;
            b /= 10;
        }

        int vsota = 0;
        for (int i = 0; i < 10; i++) {
            if (A[i] && B[i]) {
                vsota += i;
            }
        }

        return vsota;
    }

    public static void main(String[] args) {
        System.out.println(vsotaSkupnihCifer(321, 21));
    }
}
