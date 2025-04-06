package Kvizi.Kviz2;

public class Kviz2_7 {
    public static String binarnoSestej(String s, String b) {
        int dolzina = Math.max(s.length(), b.length());
        s = String.format("%" + dolzina + "s", s).replace(' ', '0');
        b = String.format("%" + dolzina + "s", b).replace(' ', '0');

        StringBuilder rezultat = new StringBuilder();
        //v = prenos (overflow)
        int v = 0;

        //od desne v levo
        for (int i = dolzina - 1; i >= 0; i--) {
            int bit1 = s.charAt(i) - '0';
            int bit2 = b.charAt(i) - '0';

            int vsota = bit1 + bit2 + v;

            rezultat.append(vsota % 2);

            v = vsota / 2;
        }

        if (v == 1) {
            rezultat.append(1);
        }
        return rezultat.reverse().toString(); //narobe obrnjeno
    }

    public static void main(String[] args) {
        System.out.println(binarnoSestej("10", "11"));


    }
}
