package Kvizi.Kviz3;

public class Kviz3_1 {
    static int[] sestejPolinoma(int[] a, int[] b) {
        int [] vsota = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            int bi = (i < b.length) ? b[i] : 0;
            vsota[i] = a[i] + bi;
        }
        return vsota;
    }
    public static void main(String[] args) {
        int [] a = {1,2,3};
        int [] b = {4,5,6};

        sestejPolinoma(a, b);


    }
}
