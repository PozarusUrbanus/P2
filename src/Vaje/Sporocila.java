package Vaje;

public class Sporocila {
    public static void main(String[] args) {
        // Cezarjev algoritm
        Kodirnik cez = new CezarjevAlgoritem(3);
        Kodiranje kodCezar = new Kodiranje(cez);
        String izvirno = "Pozdravljen svet!";
        String sifrirano = kodCezar.zakodiranjeBesedila(izvirno);
        String desifrirano = kodCezar.odkodiranjeBesedila(sifrirano);

        System.out.println("Cezarjev algoritem:");
        System.out.println("Izvirno: " + izvirno);
        System.out.println("Šifrirano: " + sifrirano);
        System.out.println("Dešifrirano: " + desifrirano);

    }
}
