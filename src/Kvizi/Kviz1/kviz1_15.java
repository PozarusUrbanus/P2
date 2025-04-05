package Kvizi.Kviz1;

public class kviz1_15 {
    public static int [] presek(int[] tabela1, int[] tabela2) {

        int dolzina = tabela1.length;
        int dolzina2 = tabela2.length;
        int [] unija = new int[dolzina];

        for (int i = 0; i < dolzina; i++) {
            for (int j = 0; j < dolzina2; j++) {
                if (tabela1[i] == tabela2[j]) {
                    for (int k = 0; k < dolzina; k++) {
                        if (unija[k] != tabela1[i]) {
                            unija[i] = tabela1[i];
                        }
                    }
                }
            }
        }
        return unija;
    }


    public static void main(String[] args) {


        int[] a = {1, 2, 3, 8, 16, 13, 2, 3, 2};
        int[] b = {13, 2, 1, 3, 4, 5, 6, 7};
        int[] c = presek(a, b);
        String locilo = "";
        for (int el : c) {
            System.out.print(locilo + el);
            locilo = ",";
        }
    }

}
