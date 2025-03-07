package Kvizi.Kviz1;

public class kviz1_2 {
    public static void kalkulator(int a, int b) {
        if (b != 0) {
            int c = a + b;
            System.out.println(a + " + " +  b + " = " + c);
            c = a - b;
            System.out.println(a + " - " +  b + " = " + c);
            c = a * b;
            System.out.println(a + " x " +  b + " = " + c);
            c = a / b;
            System.out.println(a + " / " + b + " = " + c);
            c = a % b;
            System.out.println(a + " % " +  b + " = " + c);
        }
        else {
            System.out.println("Napaka: deljenje z 0");
        }
    }

    public static void main(String[] args) {
        kalkulator(42, 13);
    }
}
