package Kvizi.Vaje;

public class Vaja2 {
    static long fakultetaL(int n) {
        long fakulteta = 1;

        for (int i = n; i > 0; i--) {
            fakulteta = fakulteta * i;
        }
        return fakulteta;
    }
    static long stirlingL(int n) {
        return  Math.round(Math.sqrt(2 * Math.PI * n) * Math.pow((n/Math.E), n));
    }
    static double fakultetaD(int n) {
        double fakulteta = 1.0;

        for (int i = n; i > 0; i--) {
            fakulteta = fakulteta * i;
        }
        return fakulteta;
    }

    static double stirlingD(int n) {
        return  Math.sqrt(2 * Math.PI * n) * Math.pow((n/Math.E), n);
    }

    public static void main(String[] args) {

        /*System.out.println("  n              n!            Stirling(n)      napaka (%)");
        System.out.println("----------------------------------------------------------");
        for (int i = 1; i <= i+1; i++) {
            System.out.printf(" %2d %20d %20d %11.7f \n", i, fakultetaL(i), stirlingL(i), ((double) fakultetaL(i) - (double) stirlingL(i)) / fakultetaL(i) * 100);
            if (fakultetaL(i) < 0)
                break;
        }*/
        //3. pri vrednosti 21 bo premajhen long

        System.out.println("  n         n!            Stirling(n)     napaka (%)");
        System.out.println("----------------------------------------------------");
        for (int i = 1; i <= 100; i++) {
            System.out.printf("%3d %17.9E %17.9E %11.7f \n", i, fakultetaD(i), stirlingD(i), Math.abs((fakultetaD(i) - stirlingD(i)) / fakultetaD(i)) * 100);
        }

    }
}
