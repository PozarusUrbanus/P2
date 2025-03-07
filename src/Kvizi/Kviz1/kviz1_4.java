package Kvizi.Kviz1;

public class kviz1_4 {
    public static void krog(double r, int d) {
        if (r < 0) {
            System.out.println("Napaka: negativen polmer");
        }else if (d < 0) {
            System.out.println("Napaka: negativen d");
        }else {
            String obseg = String.format("%.3f", 2 * Math.PI * r);
            String ploscina = String.format("%.3f", Math.PI * Math.pow(r, 2));
            System.out.printf("Obseg kroga s polmerom r=%.2f je " + obseg + "\n", r, d);
            System.out.printf("Ploscina kroga s polmerom r=%.2f je " + ploscina, r);
        }
    }

    public static void main(String[] args) {
        krog(7.5, 3);
    }
}
