package Vaje;
public class vaje2 {

    //1.
    static long fakultetaL (int n) {
        long fakulteta = 1;

        for (int i = 1; i <= n; i++) {
            fakulteta = fakulteta * i;
        }
        return fakulteta;
    }
    static long stirlingL (int n) {
        return Math.round(Math.sqrt(2 * Math.PI * n) * Math.pow(n/ Math.E, n));
    }

    //4.
    static double fakultetaD (int n) {
        double fakulteta = 1.0;

        for (int i = 1; i <= n; i++) {
            fakulteta = fakulteta * i;
        }
        return fakulteta;
    }

    static double stirlingD (int n) {
        return Math.sqrt(2 * Math.PI * n) * Math.pow(n/ Math.E, n);
    }

    //B
    static double izracunajPiNilakantha (int k) {

        double pi = 3.0;
        double znak = 1.0;

        for (int i = 0; i <= 1000000000; i++) {
            pi = pi + (znak * (4.0 / ((k) * (k + 1)
                    * (k + 2))));

            // Addition and subtraction
            // of alternate sequences
            znak = znak * (-1);

            // Increment by 2 according to formula
            k += 2;
        }

        return pi;
    }

    public static void main(String[] args) {
        //2.
        System.out.println("  n              n!            Stirling(n)      napaka (%)");
        System.out.println("----------------------------------------------------------");
        /*for (int i = 1; i <= i+1; i++) {
            System.out.printf("%3d %20d %20d %11.7f \n", i, fakultetaL(i), stirlingL(i), ((double) fakultetaL(i) - (double) stirlingL(i))/fakultetaL(i)*100);
            if (fakultetaL(i) < 0)
                break;
        }*/
        //4.
        /*System.out.println("  n         n!            Stirling(n)     napaka (%)");
        System.out.println("----------------------------------------------------");
        for (int i = 1; i <= 100; i++) {
            System.out.printf("%3d %17.9E %17.9E %11.7f \n", i, fakultetaD(i), stirlingD(i), (fakultetaD(i) - stirlingD(i))/fakultetaD(i)*100);
        }*/
        System.out.println("  k    Math.PI             PI (Nilakantha)     razlika");
        System.out.println("-----------------------------------------------------------------");
        for (int i = 1; i <= 22; i++) {
            System.out.printf("%3d %19.15f %.8f \n", i, Math.PI, izracunajPiNilakantha(i));
        }

    }
    //3. pri long lahko shranjujemo do n = 20 ker 21 je že preveč bitov uporabljenih in uporabi predznak za negativno število
    //A
    //V javi definiran PI je samo na 15 ker ni potrebno več za boljšo natančnost
}
