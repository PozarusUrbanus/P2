package zbirke;

public class Seznam {

    static String [] elementi;
    static int stElementov;


    /**
     * Naredi seznam velikosti n, ki jo vnesemo
     * @param n
     * @return true, če ga uspešno naredi ali false če je že narejen
     */
    public static boolean narediSeznam(int n) {
        if(elementi != null)
            return false;
        elementi = new String[n];
        stElementov = 0;
        return true;
    }

    /**
     * Na konec seznama doda string elementa
     * @param element
     * @return false, če je prazen in če je večje št elementov kot dolžina seznama, true pa če uspešno doda
     */
    public static boolean dodajNaKonecSeznama(String element) {
        if (elementi == null || stElementov >= elementi.length) return false;
        elementi[stElementov++] = element;
        return true;
    }

    /**
     * izpiše kaj je v seznamu
     */
    public static void izpisiSeznam() {
        System.out.println("Na seznamu so naslednji elementi:");
        if(elementi == null) {
            System.out.println("NAPAKA: Seznam ne obstaja.");
        }

        for(int i = 0; i < stElementov; i++){
            System.out.println(i+1 + ": " + elementi[i]);
        }
    }

    /**
     * Odstrani element na mestu MESTO v seznamu šteto od 1 naprej
     * @param mesto
     * @return vrne element ki je bil odstranjen
     */
    public static String odstraniIzSeznama(int mesto) {
        String element = "";
        for(int i = 1; i < stElementov; i++){
            if(i == mesto) {
                element = elementi[i-1];
                elementi[i-1] = elementi[i];
            } else if (mesto > stElementov) {
                return null;
            }
        }
        stElementov--;
        return element;
    }

    /**
     * Doda v seznam podan element, na mesto MESTO šteto od 1 naprej
     * @param element
     * @param mesto
     * @return true, če je uspešno dodano v seznam in če je mesto večje od števila vseh elementov v seznamu
     */
    public static boolean dodajVSeznam(String element, int mesto) {
        if (elementi == null || stElementov >= elementi.length || mesto == 0) {
            return false;
        } else {
            for (int i = 1; i <= stElementov; i++) {
                if (i == mesto) {
                    for (int j = stElementov; j >= mesto; j--) {
                        elementi[j] = elementi[j - 1];
                    }
                    elementi[mesto-1] = element;
                    stElementov++;
                    return true;
                }
                if (mesto > stElementov) {
                    elementi[stElementov] = element;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Izpiše kolikšna je dolžina seznama. Koliko je stvari v seznamu.
     * @return stevilo elementov
     */
    public static int dolzinaSeznama() {
        if(elementi == null)
            return -1;
        return stElementov;
    }

    /**
     * Dokončno uniči seznam. Izbriše vse
     * @return true če je seznam uničen
     */
    public static boolean uniciSeznam() {
        if(elementi == null)
            return false;

        elementi = new String[stElementov];
        return true;
    }

    public static void izpisiSeznam64Bit() {
        try {
            for (int i = 0; i < stElementov; i++) {
                Vaje.vaje3.izpisi64bit(i + elementi[i]);
            }
        }catch (Exception e) {
            System.out.println("Napaka pri izpisu. Možno da je seznam prazen.");
            System.exit(0);
        }
    }
}