package Kvizi.Kviz2;

public class Kviz2_1 {
    public static int vsotaStevk(String niz) {
        int vsota = 0;
        for (int i = 0; i < niz.length(); i++) {
            if (niz.charAt(i) == ('1' | '2')) {
                int st = niz.charAt(i) - '0';
                vsota+= st;
            }
        }
        return vsota;
    }

    public static void main(String[] args) {
        System.out.println(vsotaStevk("1abc2"));
    }

}
