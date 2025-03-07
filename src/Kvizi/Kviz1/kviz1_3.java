package Kvizi.Kviz1;

public class kviz1_3 {
    public static void nicli(int a, int b, int c) {
        double x1  = (-b + Math.sqrt(Math.pow(b,2) - 4 * a * c))/ (2*a);
        double x2  = (-b - Math.sqrt(Math.pow(b,2) - 4 * a * c))/ (2*a);

        if (Math.pow(b, 2) - 4 * a * c < 0){
            System.out.print("Napaka: nicli enacbe ne obstajata");
        }else {
            System.out.printf("x1=%.2f, x2=%.2f", x1, x2);
        }
    }

    public static void main(String[] args) {
        nicli(2, -16, 30);
    }
}
