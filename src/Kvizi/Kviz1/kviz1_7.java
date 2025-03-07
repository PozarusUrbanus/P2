package Kvizi.Kviz1;

public class kviz1_7 {
    public static boolean jeFibonaccijevo(int n) {
        boolean jeFib = false;
        if (n == 1 || n == 2) {
            jeFib = true;
        }
        int a = 1;
        int b = 1;
        int rez = 0;
        for (int i = 2; i < n; i++) {
            rez = a + b;
            a = b;
            b = rez;

            if (n == rez) {
                jeFib = true;
            }
        }
        return jeFib;
    }

    public static void main(String[] args) {
        System.out.println(jeFibonaccijevo(102334155));
    }



}
