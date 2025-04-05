package Vaje;

public class vaje1 {
    static void pravokotnikStevilVrstice (int sirina, int visina) {
        for (int i = 1; i <= visina; i++) {
            for (int j = 1; j <= sirina; j++) {
                System.out.print(i%(10));
            }
            System.out.println();
        }
    }
    static void pravokotnikStevilStolpci(int sirina, int visina) {
        for (int i = 1; i <= visina; i++) {
            for (int j = 1; j <= sirina; j++) {
                System.out.print(j%(10));
            }
            System.out.println();
        }
    }
    static void pravokotnik(int odmik, int sirina, int visina) {
        for (int i = 0; i < visina; i++) {
            for (int j = 0; j < odmik; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= sirina; j++) {
                System.out.print("X");
            }
            System.out.println();
        }
    }

    static void trikotnikStevilVrstice(int visina) {
        for (int i = 1; i <= visina; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(i%10);
            }
            System.out.println();
        }
    }

    static void trikotnikStevilStolpci(int visina) {
        for (int i = 1; i <= visina; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j%10);
            }
            System.out.println();
        }
    }

    static void trikotnikStevilVrsticeObrnjen(int visina) {
        for (int i = visina; i > 0; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print(i%10);
            }
            System.out.println();
        }
    }
    static void trikotnikStevilStolpciObrnjen(int visina) {
        for (int i = visina; i > 0; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j%10);
            }
            System.out.println();
        }
    }

    static void trikotnikStevil(int visina) {
        for (int i = 1; i <= visina; i++) {
            for (int j = 0; j < visina - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i * 2 - 1; j++) {
                System.out.print(j%10);
            }
            System.out.println();
        }
    }

    static void trikotnik(int odmik, int visina) {
        for (int i = 1; i <= visina; i++) {
            for (int j = 1; j <= odmik; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < visina - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i * 2 - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static void trikotnikObrnjen(int odmik, int visina) {
        for (int i = visina; i > 0; i--) {
            for (int j = 1; j <= odmik; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < visina - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i * 2 - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static void romb(int odmik, int velikost) {
        trikotnik(odmik, velikost);
        trikotnikObrnjen(odmik + 1, velikost - 1);
    }

    static void smreka(int v) {

        int vrstic = 0;

        for (int k = 1; k <= v; k++) {
            for (int i = 1; i <= k*2; i++) {
                for (int j = 0; j < v*v - i - v*(v-2); j++) {
                    System.out.print(" ");
                }
                for (int j = 1; j <= i * 2 - 1; j++) {
                    System.out.print("*");
                    if (j == i * 2 - 1){
                        vrstic = j;
                    }
                }
                System.out.println();
            }
            if (k == v) {
                for (int l = 1; l <= vrstic / 2; l++) {
                    for (int j = 1; j<= v+1; j++) {
                        System.out.print(" ");
                    }
                    for (int j = 1; j <= v; j++) {
                        System.out.print("X");
                    }
                    System.out.println();
                }
            }
        }
    }
    static void rombA(int odmik, int velikost) {
        for (int i = 1; i <= velikost; i++) {
            for (int j = 1; j <= odmik; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < velikost - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i * 2 - 1; j++) {
                System.out.print("#");
            }
            System.out.println();
        }
        for (int i = velikost - 1 ; i > 0; i--) {
            for (int j = 1; j <= odmik; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < velikost - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i * 2 - 1; j++) {
                System.out.print("#");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        //pravokotnikStevilVrstice (7, 3);
        //pravokotnikStevilStolpci (7, 3);
        //pravokotnik(3, 7,3);
        //trikotnikStevilVrstice(3);
        //trikotnikStevilStolpci(30);
        //trikotnikStevilVrsticeObrnjen(3);
        //trikotnikStevilStolpciObrnjen(3);
        //trikotnikStevil(5);
        //trikotik(1, 5);
        //trikotnikObrnjen(1, 5);
        //romb(5,10);
        smreka(4);
        rombA(1, 5);
    }
}
