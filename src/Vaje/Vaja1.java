package Vaje;

public class Vaja1 {
    //1. PRAVOKOTNIKI
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
    }
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
    //prva
    static void trikotnikStevilVrstice(int visina) {
        for (int i = 1; i <= visina; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(i);
            }
            System.out.println();
        }
    }
    //druga
    static void trikotnikStevilStolpci(int visina) {
        for (int i = 1; i <= visina; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }
    //tretja
    static void trikotnikStevilVrsticeObrnjen(int visina) {
        for (int i = visina; i >= 0; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print(i);
            }
            System.out.println();
        }
    }
    //cetrta
    static void trikotnikStevilStolpciObrnjen(int visina) {
        for (int i = visina; i >= 0; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }
    //peta
    static void trikotnikStevil(int visina) {
        for (int i = 1; i <= visina; i++) {
            for (int j = 0; j < visina - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print(i);
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        //pravokotnikStevilVrstice(7, 3);
        //System.out.println();
        //pravokotnikStevilStolpci(15, 3);
        //pravokotnik(5,7,3);

        //trikotnikStevilVrstice(3);
        //trikotnikStevilStolpci(3);
        //trikotnikStevilVrsticeObrnjen(3);
        //trikotnikStevilStolpciObrnjen(3);
        trikotnikStevil(5);

    }
}
