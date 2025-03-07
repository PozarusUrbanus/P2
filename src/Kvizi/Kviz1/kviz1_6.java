package Kvizi.Kviz1;

public class kviz1_6 {
    public static void javaJavaJava(int n) {
        if (n < 0) {
            System.out.println("Napaka: negativen n");
        } else {
            for (int i = 0; i < n; i++) {
                System.out.print("     J    a   v     v  a   ");
            }
            System.out.println();
            for (int i = 0; i < n; i++) {
                System.out.print("     J   a a   v   v  a a  ");
            }
            System.out.println();
            for (int i = 0; i < n; i++) {
                System.out.print("  J  J  aaaaa   V V  aaaaa ");
            }
            System.out.println();
            for (int i = 0; i < n; i++) {
                System.out.print("   JJ  a     a   V  a     a");
            }
        }
    }
    public static void main(String[] args) {
        javaJavaJava(3);
    }
}
