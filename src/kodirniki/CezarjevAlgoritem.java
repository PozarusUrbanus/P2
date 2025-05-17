package kodirniki;

public class CezarjevAlgoritem implements Kodirnik {
    private int zamik;

    public CezarjevAlgoritem(int zamik) {
        this.zamik = zamik;
    }

    public int zakodiraj(int vrednost){
        return vrednost + zamik;
    }

    public int odkodiraj(int vrednost){
        return vrednost - zamik;
    }

    public void ponastavi(){}
}
