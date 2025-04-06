package Kvizi.Kviz2;

public class Kviz2_10 {
    public static String prepleti(String niz1, String niz2) {
        int dolzina = niz1.length() + niz2.length();
        StringBuilder niz = new StringBuilder(dolzina);

        for (int i = 0; i < Math.max(niz1.length(),niz2.length()); i++) {

            if (niz1.length() < niz2.length() && i >= niz1.length()) {
                niz.append(" ");
            }else {
                niz.append(niz1.charAt(i));
            }
            if (niz1.length() > niz2.length() && i >= niz2.length()) {
                niz.append(" ");
            }else {
                niz.append(niz2.charAt(i));
            }
        }

        return niz.toString();
    }
    public static void odpleti(String niz) {
        String niz1 = "";
        String niz2 = "";
        for (int i = 0; i < niz.length()-1; i+=2) {
            niz1 += niz.charAt(i);
            niz2 += niz.charAt(i+1);

        }
        if (niz.length() % 2 != 0) {
            niz1 += niz.charAt(niz.length() - 1);
        }

        System.out.print(niz1 + "\n" + niz2);
    }

    public static void main(String[] args) {
        //System.out.print(prepleti("pomlad", "JESEN"));
        //System.out.println(prepleti("POMLAD", "december"));
        odpleti("SdLoOmVoEvNiInJaA");

    }
}
