package aplikacija;

import kodirniki.CezarjevAlgoritem;
import kodirniki.Kodiranje;
import kodirniki.Kodirnik;
import kodirniki.XORAlgoritem;

public class Sporocila {
    public static void main(String[] args) {
        Kodirnik cez = new CezarjevAlgoritem(3);
        Kodiranje kodiCez = new Kodiranje(cez);
        String besedilo = "ABCDEFGHIJ";
        System.out.println("Original besedilo CEZ: " + besedilo);
        String zakod = kodiCez.zakodiranjeBesedila(besedilo);
        System.out.println("Zakodirano besedilo CEZ: " + zakod);
        System.out.println("Odkodirano besedilo CEZ: " + kodiCez.odkodiranjeBesedila(zakod));

        Kodirnik XOR = new XORAlgoritem(besedilo);
        Kodiranje kodiXor = new Kodiranje(XOR);
        System.out.println("Original besedilo XOR: " + besedilo);
        String zakodX = kodiXor.zakodiranjeBesedila(besedilo);
        System.out.println("Zakodirano besedilo XOR: " + zakodX);
        System.out.println("Odkodirano besedilo XOR: " + kodiXor.odkodiranjeBesedila(zakodX));
    }
}
