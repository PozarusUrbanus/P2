package Domace_naloge;//1.
/**
 * Abstraktni razred kjer je deklariran obseg
 */
abstract class Lik {
    public abstract int obseg();
}

//kvadrat
/**
 * Razred KVADRAT, ki razširi Domace_naloge.Lik in vrne obseg kvadrata
 */
class Kvadrat extends Lik {
    private int stranica;

    public Kvadrat(int stranica) {
        this.stranica = stranica;
    }

    public int obseg() {
        return 4 * stranica;
    }
}

//pravokotnik
/**
 * Razred PRAVOKOTNIK, ki razširi Domace_naloge.Lik in vrne obseg pravokotnika
 */
class Pravokotnik extends Lik {
    private int stranica1, stranica2;

    public Pravokotnik(int stranica1, int stranica2) {
        this.stranica1 = stranica1;
        this.stranica2 = stranica2;
    }

    public int obseg() {
        return 2 * stranica1 + 2 * stranica2;
    }
}

//Nkotnik
/**
 * Razred NKOTNIKA, ki razširi Domace_naloge.Lik in vrne obseg nkotnika
 */
class NKotnik extends Lik {
    private int stranica;
    private int nKotnik;

    public NKotnik(int nKotnik, int stranica) {
        this.nKotnik = nKotnik;
        this.stranica = stranica;
    }

    public int obseg() {
        return nKotnik * stranica;
    }
}

//main
/**
 * Glavni razred, ki ima tabelo Liki v katero shranjuje Like skozi zanko gre čez vse dane argumente, jih splita po ':'
 * in na podlagi kvadrata, pravokotnika ali nkotnika izračuna njihov obseg, ter ga shrani v tabelo.
 * Metoda skupni obseg, gre čez tabelo in v vsoto sešteje vse obsege, ter izpiše.
 */
public class DN08 {
    public static void main(String[] args) {
        Lik [] Liki = new Lik[args.length];
        for (int i = 0; i < Liki.length; i++) {
            String[] razdeli = args[i].split(":");
            Lik lik = null;

            if (razdeli[0].equalsIgnoreCase("kvadrat")) {
                int stranica = Integer.parseInt(razdeli[1]);
                lik = new Kvadrat(stranica);
            }else if (razdeli[0].equalsIgnoreCase("pravokotnik")) {
                int stranica1 = Integer.parseInt(razdeli[1]);
                int stranica2 = Integer.parseInt(razdeli[2]);
                lik = new Pravokotnik(stranica1, stranica2);
            }else if (razdeli[0].equalsIgnoreCase("nkotnik")) {
                int nKotnik = Integer.parseInt(razdeli[1]);
                int stranica = Integer.parseInt(razdeli[2]);
                lik = new NKotnik(nKotnik, stranica);
            }
            Liki[i] = lik;
        }
        System.out.println(skupniObseg(Liki));
    }
    static int skupniObseg(Lik [] liki) {
        int v = 0;
        for (Lik lik : liki) {
            if (lik != null) {
                v+=lik.obseg();
            }
        }
        return v;
    }
}
