package Kvizi.Kviz1;

public class kviz1_5 {
    public static String pretvoriSekunde(int sekunde) {

        if(sekunde < 0) {
            return String.format("Število sekund ne more biti negativno");
        }

        int s = sekunde % 60;
        int min = (sekunde % 3600) / 60;
        int ure = sekunde / 3600;

        return String.format("%02d:%02d:%02d", ure, min, s);
    }

    public static void main(String[] args) {
        System.out.println(pretvoriSekunde(65));       // 00:01:05
        System.out.println(pretvoriSekunde(49330));    // 13:42:10
        System.out.println(pretvoriSekunde(-12));
    }


}
