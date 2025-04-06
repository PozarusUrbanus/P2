package Kvizi.Kviz2;

public class Kviz2_5 {

    public static int [] duplikati (int [] tabela) {
        int [] rezerva = new int [tabela.length];
        int pozicija = 0;

        for (int i = 0; i < tabela.length; i++) {
            boolean obstaja = false;
            for (int j = 0; j < pozicija; j++) {
                if(rezerva[j] == tabela[i]) {
                    obstaja = true;
                    break;
                }
            }
            if (!obstaja) {
                rezerva[pozicija] = tabela[i];
                pozicija++;
            }
        }

        int[] rezultati = new int[pozicija];
        for (int i = 0; i < pozicija; i++) {
            rezultati[i] = rezerva[i];
        }
        return rezultati;
    }

    public static void main(String[] args) {
        int[] brez = duplikati(new int[]{1,2,1,2,1,2,1,2});
        String locilo = "";
        for (int e : brez) {
            System.out.print(locilo + e );
            locilo = ",";
        }

    }
}
