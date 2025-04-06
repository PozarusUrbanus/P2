package Kvizi.Kviz2;

public class Kviz2_11 {
    public static String vMorse(String niz) {
        final char[] abeceda = {
                ' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'Š', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '?',
                 '1', '2', '3', '4', '5', '6', '7', '8', '9', '0','.'};

        final String[] mors = {
                "", //" "
                ".-", //a
                "-...", //b
                "-.-.", //c
                "-..", //d
                ".", //e
                "..-.", //f
                "--.",//g
                "....",//h
                "..",//i
                ".---",//j
                "-.-",//k
                ".-..",//l
                "--",//m
                "-.",//n
                "---",//o
                ".--.",//p
                "--.-",//q
                "--.-",//r
                "...",//s
                "----",//š
                "-",//t
                "..-",//u
                "...-",//v
                ".--",//w
                "-..-",//x
                "-.--",//y
                "--..",//z
                "..--..",//?
                ".----", //1
                "..---", //2
                "...--", //3
                "....-", //4
                ".....", //5
                "-....", //6
                "--...", //7
                "---..", //8
                "----.", //9
                "-----", //0
                ".-.-.-" //.
        };

        int indeks = 0;

        String izhodniNiz = "";
        niz = niz.toUpperCase();

        for (int i = 0; i < niz.length(); i++) {
            char znak = niz.charAt(i);

            for (int j = 0; j < abeceda.length; j++) {
                if (abeceda[j] == znak) {
                    indeks = j;
                }
            }
            izhodniNiz = izhodniNiz + mors[indeks] + " ";
        }
        return izhodniNiz;
    }

    public static void main(String[] args) {
        System.out.print(vMorse("Kako si kaj?"));


    }
}
