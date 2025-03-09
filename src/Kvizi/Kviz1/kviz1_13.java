package Kvizi.Kviz1;

public class kviz1_13 {
    public static void pitagoroviTrojcki (int x) {

        for (int a = 1; a <= x; a++) {
            for (int b = a; b <= x; b++) {
                for (int c = b; c <= x; c++) {
                    if (a*a + b*b == c*c) {
                        System.out.printf(a + " " + b + " " + c + "\n");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        pitagoroviTrojcki(25);
    }

}
