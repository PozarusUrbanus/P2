package Kvizi.Kviz1;

public class kviz1_11 {
    public static String pretvoriVDesetisko(String n, int b) {

        if (n.contains("2") && b == 2) {
            return ("Napaka pri pretvorbi sistema - števka 2");
        } else if (n.contains("G") && b == 16) {
            return ("Napaka pri pretvorbi sistema - števka G");
        }
        return (n + "(" + b + ")=" + Integer.parseInt(n, b) + "(10)");
    }

    public static void main(String[] args) {

        System.out.println(pretvoriVDesetisko("101010", 2));

    }
}
