package Kvizi.trojka;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Matrika {
    private int [][] dimenzije;
    private int n;

    public Matrika(int [][] dimenzije) {
        this.n = dimenzije.length;
        this.dimenzije = dimenzije;
    }


    static Matrika preberiMatriko(String imeDatoteke) {
        try (Scanner sc = new Scanner(new File(imeDatoteke))) {
            int n = sc.nextInt();
            int[][] podatki = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    podatki[i][j] = sc.nextInt();
                }
            }
            return new Matrika(podatki);
        } catch (Exception e) {
            System.out.println("Datoteka ne obstaja: " + imeDatoteke);
            return null;
        }
    }

    public Matrika zmnozi(Matrika b) {
        if (this.n != b.n) {
            throw new IllegalArgumentException("Matriki morata biti enake velikosti.");
        }
        int[][] rezultat = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    rezultat[i][j] += this.dimenzije[i][k] * b.dimenzije[k][j];
                }
            }
        }
        return new Matrika(rezultat);
    }

    public void izpisi() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%3d", dimenzije[i][j]);
            }
            System.out.println();
        }
    }
}
