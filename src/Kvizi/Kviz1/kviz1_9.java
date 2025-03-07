package Kvizi.Kviz1;

public class kviz1_9 {
    public static void izrisiZastavo(int n) {
        int d = 15 * n + 1; //dolzina
        int v = 5 * n; //visina (stevilo vrstic)
        float v_kv = 3 * n; //visina kvadrata z zvezdicami

        for (int j = 0; j < v_kv; j++) {
            if (j % 2 == 0) {
                for (int k = 0; k < n; k++) {
                    System.out.print("* * ");
                }
                for (int k = 0; k < d - v_kv - n; k++) {
                    System.out.print("=");
                }
                System.out.println();
            } else {
                for (int k = 0; k < n*2-1; k++) {
                    System.out.print(" *");
                }
                System.out.print("  ");
                for (int k = 0; k < d - v_kv - n; k++) {
                    System.out.print("=");
                }
                System.out.println();
            }
        }
        for (int k = 0; k < n*2; k++) {
            for (int j = 0; j < d; j++) {
                System.out.print("=");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        izrisiZastavo(2);
    }
}
