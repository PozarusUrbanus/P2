package Kvizi.Vaje;
public class Vaja3 {
    private static final char crnaPika = '\u2B1B'; // črn kvadratek
    private static final char belaPika = '\u2B1C'; // prazen (bel) kvadratek

    private static final char[] abeceda = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' '};

    private static final short[] kodeZnakov16bit = {
            (short) 0b1111100111111001, // A
            (short) 0b1100101011011010, // B
            (short) 0b1111100010001111, // C
            (short) 0b1110100110011110, // D
            (short) 0b1111111010001111, // E
            (short) 0b1111100011101000, // F
            (short) 0b1111100010111111, // G
            (short) 0b1001100111111001, // H
            (short) 0b1111010001001111, // I
            (short) 0b1111000110011111, // J
            (short) 0b1011110010101001, // K
            (short) 0b1000100010001111, // L
            (short) 0b1111101110011001, // M
            (short) 0b1101101110011001, // N
            (short) 0b1111100110011111, // O
            (short) 0b1111100111111000, // TODO: dodajte znak P
            (short) 0b1111100110111111, // Q
            (short) 0b1111100111111010, // R
            (short) 0b1111100011110111, // S
            (short) 0b1111010001000100, // T
            (short) 0b1001100110011111, // U
            (short) 0b1001100110010110, // V
            (short) 0b1001100110111111, // W
            (short) 0b1001011001101001, // X
            (short) 0b1001100111110100, // Y
            (short) 0b1111001001001111, // Z
            (short) 0b0110100110010110, // 0
            (short) 0b0110001000101111, // 1
            (short) 0b1110001001001111, // TODO: dodajte znak 2
            (short) 0b1111011100011111, // 3
            (short) 0b1000100111110001, // 4
            (short) 0b1111100011110111, // 5
            (short) 0b1000111110011111, // 6
            (short) 0b1111000100010001, // 7
            (short) 0b1110101111010111, // 8
            (short) 0b1111100111110001, // 9
            0                           // presledek
    };

    public static void izpisi16bit(short kodaZnaka) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int pozicija = 15 - (i * 4 + j); //premikanje po bitih
                boolean jeCrna = (kodaZnaka & (1 << pozicija)) != 0;
                System.out.print(jeCrna ? crnaPika : belaPika);
            }
            System.out.println();
        }
    }



    public static void izpisi16bit (String niz) {
        niz = niz.toUpperCase();
        short[] kodiraniZnaki = new short[niz.length()];

        // Preslikava znakov v 16-bitne kode
        for (int i = 0; i < niz.length(); i++) {
            kodiraniZnaki[i] = kodaZnaka(niz.charAt(i));
        }

        // Izpiše preslikane znake
        izpisi16bit(kodiraniZnaki);
    }
    public static void izpisi16bit(short[] nizZnakov) {
        for (int i = 0; i < 4; i++) {  // 4 vrstice
            for (int zn = 0; zn < nizZnakov.length; zn++) {  // Gre skozi vse znake
                short kodaZnaka = nizZnakov[zn];

                for (int j = 0; j < 4; j++) {  // 4 stolpci za vsak znak
                    int bitPozicija = 15 - (i * 4 + j);
                    boolean jeCrna = (kodaZnaka & (1 << bitPozicija)) != 0;
                    System.out.print(jeCrna ? crnaPika : belaPika);
                }

                // Razmik med znaki (en bel stolpec)
                System.out.print(belaPika);
            }
            System.out.println(); // Nova vrstica po vsaki 4x4 vrstici
        }
    }

    private static short kodaZnaka(char znak) {
        for (int i = 0; i < abeceda.length; i++) {
            if (abeceda[i] == znak) {
                return kodeZnakov16bit[i];
            }
        }
        return kodeZnakov16bit[kodeZnakov16bit.length - 1]; // Če ni v abecedi, presledek
    }


    private static final long[] kodeZnakov64bit = {
            0b0001100000100100010000100100001001111110010000100100001011100111L,
            0b1111110001000010010001000111111001000001010000010100000111111110L,
            0b0011110001000010100000011000000010000000100000010100001000111100L,
            0b1111110001000010010000010100000101000001010000010100001011111100L,
            0b1111111101000001010000000111110001000000010000000100000111111111L,
            0b1111111101000001010000010100100001111000010010000100000011100000L,
            0b0011111101000001100000001001111110010001100000010100000100111110L,
            0b1110011101000010010000100111111001000010010000100100001011100111L,
            0b1111111100010000000100000001000000010000000100000001000011111111L,
            0b0011111100000100000001000000010011000100100001001000010001111000L,
            0b1110111101000100010010000111000001001000010001000100001011100111L,
            0b1110000001000000010000000100000001000000010000010100000111111111L,
            0b1100011101101010010100100101001001000010010000100100001011100111L,
            0b1100011101100010010100100100101001000110010000100100001011100111L,
            0b0011110001000010100000011000000110000001100000010100001000111100L,
            0b1111111001000001010000010100000101111110010000000100000011100000L,  //p                                                               // TODO: dodaj znak P
            0b0111111010000001100000011000000110000001100010010111111000001000L,
            0b1111111001000001010000010100000101111110010001000100001011100111L,
            0b0111110110000011100000010111110000000010100000011100000110111110L,
            0b1111111110001001000010000000100000001000000010000000100000011100L,
            0b1110011110000001100000011000000110000001100000011000000101111110L,
            0b1110011101000010010000100100001000100010001001000001010000001000L,
            0b1110011101000010010000100100001001010010010100100101001000101100L,
            0b1110011101000010001001000001100000100100001001000100001011100111L,
            0b1110011101000010001001000001010000001000000010000000100000011100L,
            0b1111111110000010100001000000100000010000001000010100000111111111L,
            0b0011110001000010100001011000100110010001101000010100001000111100L,
            0b0011000001010000000100000001000000010000000100000001000011111111L,
            0b0111111010000001000000010000011000011000011000011000000111111111L,                                                                // TODO: dodaj znak 2
            0b0111111010000001100000010000111000000001100000011000000101111110L,
            0b0000011000001010000100100010001001000010111111110000001000000111L,
            0b1111111110000001100000001111111000000001100000011000000101111110L,
            0b0111111010000001100000001111111010000001100000011000000101111110L,
            0b1111111110000001000000100000010000001000000100000001000000111000L,
            0b0111111010000001100000010111111010000001100000011000000101111110L,
            0b0111111010000001100000011000000101111111000000011000000101111110L,
            0
    };

    public static void izpisi64bit(long kodaZnaka) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int pozicija = 63 - (i * 8 + j); //premikanje po bitih
                boolean jeCrna = (kodaZnaka & (1L << pozicija)) != 0;
                System.out.print(jeCrna ? crnaPika : belaPika);
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        /*izpisi16bit((short) 0b1111100111111001); //A
        System.out.println();
        izpisi16bit((short) 0b1111100111111000); //P
        System.out.println();
        izpisi16bit((short) 0b1110001001001111); //2*/

        //izpisi16bit(new short[] {(short)0b1111100011101000, (short)0b1111100111111010, (short)0b1111010001001111});

        //izpisi16bit("Programiranje je zakon");

        //izpisi64bit(0b0111111010000001000000010000011000011000011000011000000111111111L);
    }
}
