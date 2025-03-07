package Kvizi.Kviz1;

public class kviz1_10 {
    public static void vDesetisko(int n) {

        String osm = Integer.toString(n);
        if (osm.contains("8")) {
            System.out.printf("Število %d ni število v osmiškem sistemu (števka 8)", n);
        } else if (osm.contains("9")) {
            System.out.printf("Število %d ni število v osmiškem sistemu (števka 9)", n);
        } else {
            int deset = Integer.parseInt(osm, 8);
            System.out.printf("%d(8) = %d(10)", n, deset);
        }
    }


    public static void main(String[] args) {
        vDesetisko(129);
    }

}
