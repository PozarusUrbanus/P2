package Kvizi.Kviz2;

public class Kviz2_8 {
    static String prevod(String niz) {
        StringBuilder prevedenNiz = new StringBuilder();

        int stejemo = 0;
        for (int i = 0; i < niz.length()-1; i++) {
            char prvi = niz.charAt(i);
            char drugi = niz.charAt(i+1);

            if (prvi == 'p' && drugi == 'a') {
                stejemo++;
            }
        }
        if (stejemo > 1) {
            for (int i = 0; i < niz.length(); i++) {
                char c = niz.charAt(i);
                if ((c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') && i + 2 < niz.length()) {
                    if (niz.charAt(i + 1) == 'p' && niz.charAt(i + 2) == 'a') {
                        prevedenNiz.append(c);
                        i += 2;
                    } else {
                        prevedenNiz.append(c);
                    }
                } else {
                    prevedenNiz.append(c);
                }
            }
        } else {
            for (int i = 0; i < niz.length(); i++) {
                char c = niz.charAt(i);
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    prevedenNiz.append(c);
                    prevedenNiz.append(("pa"));
                } else {
                    prevedenNiz.append(c);
                }
            }
        }

        return prevedenNiz.toString();
    }

    public static void main(String[] args) {
        System.out.println(prevod("Napaka"));



    }
}
