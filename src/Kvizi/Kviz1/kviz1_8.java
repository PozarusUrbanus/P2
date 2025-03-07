package Kvizi.Kviz1;

public class kviz1_8 {
    public static boolean jePrastevilo(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        if (n > 0) {
            return true;
        } else
            return false;
    }

    public static void main(String[] args) {
        System.out.println(jePrastevilo(42));
    }
}
