package Kvizi.Kviz3;

public class Kviz3_3 {
    static boolean jeAnagram(String prvaBeseda, String drugaBeseda, boolean zanemariVelikost) {
        if (zanemariVelikost) {
            prvaBeseda = prvaBeseda.toUpperCase();
            drugaBeseda = drugaBeseda.toUpperCase();
        }
        char [] prva = prvaBeseda.toCharArray();
        char [] druga = drugaBeseda.toCharArray();

        char [] rezultat = new char[prva.length];

        for (int i = 0; i < prva.length; i++) {
            for (int j = 0; j < druga.length; j++) {
                if (prva[i] == druga[j]) {
                    rezultat[i] = prva[i];
                }
            }
        }

        for (int i = 0; i < rezultat.length; i++) {
            if (prva[i] != rezultat[i] || prvaBeseda.equalsIgnoreCase(drugaBeseda)) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(jeAnagram("abc", "abc", true));

    }
}
