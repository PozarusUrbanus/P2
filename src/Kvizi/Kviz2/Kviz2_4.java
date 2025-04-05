package Kvizi.Kviz2;

public class Kviz2_4 {
    public static void rotiraj(int [] tabela, int k) {
        for (int i = 0; i < k; i++){
            for (int poz = 0; poz < tabela.length-1; poz++){
                int rez = tabela[poz];
                tabela[poz] = tabela[poz+1];
                tabela[poz+1] = rez;
            }
        }
    }

    public static void main(String[] args) {
        int[] tabela = new int[]{1,2,3,4,5,6};
        rotiraj(tabela,5);
        String locilo = "";
        for (int e : tabela) {
            System.out.print(locilo + e );
            locilo = ",";
        }

    }

}
