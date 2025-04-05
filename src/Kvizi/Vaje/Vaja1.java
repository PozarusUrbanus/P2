package Kvizi.Vaje;

public class Vaja1 {
    //1. PRAVOKOTNIKI a
    //prva
    static void pravokotnikStevilVrstice (int sirina, int visina) {
        for (int i = 1; i <= visina; i++) {
            for (int j = 1; j <= sirina; j++) {
                if (i<10) {
                    System.out.print(i);
                    if ((j == sirina) && (i < visina)) {
                        System.out.println();
                    }
                } else {
                    System.out.print("1");
                    if ((j == sirina) && (i < visina)) {
                        System.out.println();
                    }
                }
            }
        }
    }//1. b
    //druga
    static void pravokotnikStevilStolpci(int sirina, int visina) {
        for (int i = 1; i <= visina; i++) {
            for (int j = 1; j <= sirina; j++) {
                if (i<10) {
                    System.out.print(j%10);
                    if ((j == sirina) && (i < visina)) {
                        System.out.println();
                    }
                } else {
                    System.out.print("1");
                    if ((j == sirina) && (i < visina)) {
                        System.out.println();
                    }
                }
            }
        }
    }
    //1.c
    //tretja
    static void pravokotnik(int odmik, int sirina, int visina) {
        for (int i = 0; i < visina; i++) {
            for (int j = 0; j < odmik; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= sirina; j++) {
                if (i<10) {
                    System.out.print("X");
                    if ((j == sirina) && (i < visina)) {
                        System.out.println();
                    }
                }
            }
        }
    }
    //2. TRIKOTNIKI
    //2. a
    //prva
    static void trikotnikStevilVrstice(int visina) {
        for (int i = 1; i <= visina; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(i);
            }
            System.out.println();
        }
    }
    //2. b
    //druga
    static void trikotnikStevilStolpci(int visina) {
        for (int i = 1; i <= visina; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }
    //2. c
    //tretja
    static void trikotnikStevilVrsticeObrnjen(int visina) {
        for (int i = visina; i >= 0; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print(i);
            }
            System.out.println();
        }
    }
    //2. d
    //cetrta
    static void trikotnikStevilStolpciObrnjen(int visina) {
        for (int i = visina; i >= 0; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }
    //2. e
    //peta
    static void trikotnikStevil(int visina) {
        for (int i = 1; i <= visina; i++) {
            for (int j = 0; j < visina - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i * 2 - 1; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }
    //2. f
    //šesta
    static void trikotnik(int odmik, int visina) {
        for (int i = 1; i <= visina; i++) {
            for (int j = 0; j < visina - i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < odmik; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i * 2 - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    //2. g
    //sedma
    static void trikotnikObrnjen(int odmik, int visina) {
        for (int i = visina; i>0; i--) {
            for (int j = 0; j < visina - i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < odmik; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i * 2 - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    //3. ROMB
    static void romb(int odmik, int velikost) {
        trikotnik(odmik, velikost);
        trikotnikObrnjen(odmik+1, velikost-1);
    }
    //ALI
    /*static void romb(int odmik, int velikost) {
        for (int i = 1; i <= velikost; i++) {
            for (int j = 0; j < velikost - i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < odmik; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i * 2 - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        for (int i = velikost-1; i>0; i--) {
            for (int j = 0; j < velikost - i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < odmik; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i * 2 - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }*/
    static void smreka(int v) {
        for (int k = 1; k <= v; k++) {
            for (int i = 1; i < v * k; i++) {
                for (int j = 0; j < v * k - i; j++) {
                    System.out.print(" ");
                }
                for (int j = 0; j < i * 2 - 1; j++) {
                    System.out.print("*");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        smreka(3);
        //pravokotnikStevilVrstice(7, 3);
        //System.out.println();
        //pravokotnikStevilStolpci(15, 3);
        //pravokotnik(5,7,3);

        //trikotnikStevilVrstice(3);
        //trikotnikStevilStolpci(3);
        //trikotnikStevilVrsticeObrnjen(3);
        //trikotnikStevilStolpciObrnjen(3);
        //trikotnikStevil(5);
        //trikotnik(1, 5);
        //trikotnikObrnjen(1, 5);

        //romb(2, 5);



    }
}
