package Kvizi.Kviz3;

public class Kviz3_2 {
    static int[] zmnoziPolinoma(int[] a, int[] b) {
        int [] produkt = new int[a.length+b.length-1];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                produkt[i + j] += a[i] * b[j];
            }
        }
        return produkt;
    }
    public static void main(String[] args) {
        int [] a = {1,2,3};
        int [] b = {4,5,6};

        zmnoziPolinoma(a, b);


    }
}
