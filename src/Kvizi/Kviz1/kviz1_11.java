package Kvizi.Kviz1;

public class kviz1_11 {
    public static void pretvoriVDesetisko(String n, int b) {

        if (n.contains("8")) {
            System.out.printf("Število %d ni število v osmiškem sistemu (števka 8)", Integer.parseInt(n));
        } else if (n.contains("9")) {
            System.out.printf("Število %d ni število v osmiškem sistemu (števka 9)", Integer.parseInt(n));
        } else {
            int deset = Integer.parseInt(n, b);
            System.out.printf("%d(%d) = %d(10)", Integer.parseInt(n), b, deset);
        }
    }


    public static void main(String[] args) {
        pretvoriVDesetisko("FF", 16);
    }

}
