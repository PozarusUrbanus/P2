package Kvizi.Kviz1;

public class kviz1_16 {
    public static String izracunajRazliko(String prviCas, String drugiCas) {

        String [] prvi_casi = prviCas.split(":");
        String [] drugi_casi = drugiCas.split(":");

        int prvi_sek = 0;
        int drugi_sek = 0;
        int rez = 0;

        for (int i = 0; i < 3; i++) {

            int prvi = Integer.parseInt(prvi_casi[i]);
            int drugi = Integer.parseInt(drugi_casi[i]);

            if (i == 0) {
                prvi_sek += prvi * 3600;
                drugi_sek += drugi * 3600;
            } else if (i == 1) {
                prvi_sek += prvi * 60;
                drugi_sek += drugi * 60;
            }else {
                prvi_sek += prvi;
                drugi_sek += drugi;

                if (prvi_sek < drugi_sek) {
                    rez = drugi_sek - prvi_sek;
                }else {
                    rez = prvi_sek - drugi_sek;
                }
            }
        }
        int s = rez % 60;
        int min = (rez % 3600) / 60;
        int ure = rez / 3600;

        return String.format("%02d:%02d:%02d", ure, min, s);
    }

    public static void main(String[] args) {
        System.out.println(izracunajRazliko("08:23:10", "10:10:05"));       // 00:01:05

    }


}
