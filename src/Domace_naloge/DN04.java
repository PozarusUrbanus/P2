package Domace_naloge;

public class DN04 {
    public static void main(String[] args) {
        String niz = args[0];
        String izhod = "";

        for (int i = 0; i < niz.length()/8; i++) {
            String crka = niz.substring(i*8, i*8+8);
            int stevilo = 0;
            for (int j = 0; j < crka.length(); j++) {
                stevilo = (stevilo << 1) | (crka.charAt(j) - '0');
            }
            char znak = (char) stevilo;
            izhod += znak;
        }
        System.out.println(izhod);
    }
}
