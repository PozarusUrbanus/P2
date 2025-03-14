package Vaje;

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



    public static void main(String[] args) {

        System.out.println("  n              n!            Stirling(n)      napaka (%)");
        System.out.println("----------------------------------------------------------");
        for (int i = 1; i <= i+1; i++) {
            System.out.printf(" %2d %20d %20d %11.7f \n", i, fakultetaL(i), stirlingL(i), ((double) fakultetaL(i) - (double) stirlingL(i)) / fakultetaL(i) * 100);
            if (fakultetaL(i) < 0)
                break;
        }
    }
}
