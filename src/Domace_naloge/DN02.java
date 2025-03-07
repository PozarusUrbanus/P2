public class DN02 {
    public static void main(String[] args) {
        int s = Integer.parseInt(args[0]); //sirina
        int v = Integer.parseInt(args[1]); //visina
        int x = Integer.parseInt(args[2]); //v desno
        int y = Integer.parseInt(args[3]); //dol

        //prva vrstica
        for (int i = 0; i < (s * x) + 1 - x; i++) {
            System.out.print("*");
        }
        System.out.println();//da piše u drugo
        for (int k = 0; k < y; k++) {// zanka za število prostorov dol
            for (int i = 0; i < v-2; i++) {// zanka za visino enega prostora
                for (int j = 0; j < (s * x) + 1 - x; j++) {// zanka za v vrstici v prostorih
                    if (j % (s-1) == 0) {
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
            for (int i = 0; i < (s * x) + 1 - x; i++) { // zadnja vrstica
                System.out.print("*");
            }
            System.out.println();
        }
    }
}