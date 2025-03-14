package Kvizi.Kviz1;

public class kviz1_12 {
    public static boolean jePrastevilo(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return n > 0;
    }
    public static int vsotaPrvih(int n) {
        int vsota = 0;
        int st = 2;
        int st_Prast = 0;

        while (st_Prast < n) {
            if (jePrastevilo(st)) {
                vsota += st;
                st_Prast++;
            }
            st++;
        }
        return vsota;
    }


    public static void main(String[] args) {
        System.out.println(vsotaPrvih(10));
    }

}
