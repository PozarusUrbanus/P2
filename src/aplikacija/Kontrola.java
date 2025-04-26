package aplikacija;

import vinjete.SeznamVinjet;

public class Kontrola {

    public static void main(String[] args) throws Exception {
        SeznamVinjet sv = new SeznamVinjet();

        sv.preberiPodatke(args[0]);
        //sv.izpisiVinjete();

       /* String reg = "KR321MA";
        if (sv.preveriVinjeto(reg)) {
            System.out.println("Vozilo " + reg + " ima veljavno vinjeto.");
        } else {
            System.out.println("Vozilo " + reg + " nima veljavne vinjete.");
        }*/

        String vrs = "tedenska";

        sv.izpisiVinjete(vrs);



    }
}
