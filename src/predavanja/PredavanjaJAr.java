package predavanja;


import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class PredavanjaJAr {

    public static void tarca(){
        StdDraw.setScale(-100, 100);
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(StdDraw.YELLOW);

        for (int i = 0; i < 10; i++) {
            StdDraw.circle(0, 0, i * 10);
        }
    }

    public static void barvniKvadratki() {
        int t = 10;
        int n = 25;
        StdDraw.setScale(0, n*t);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int x = t / 2 + t * j;
                int y = t / 2 + t * i;

                StdDraw.setPenColor(new Color(j+10, i+10, 0 ));

                StdDraw.filledSquare(x,y,t/2);
            }
        }
    }


    static void kvadratnaSpirala(){
       int[][] smeri =  {{0,-1}, {-1,0},{0,1},{1,0}};
       StdDraw.setScale(-100,100);
       int smer=0;
       int n=200;
       int dolzina=5;

       double xkord=0;
       double ykord=0;

       for(int i=0; i<n; i++){
           double nx= xkord + smeri[smer][0] * dolzina;
           double ny= ykord + smeri[smer][1] + dolzina;
           StdDraw.line(xkord, ykord, nx, ny);

           xkord=nx;
           ykord=ny;
           smer=(smer + 1) % 4;
           dolzina++;
       }
    }


    static void roza(int n){
        StdDraw.setScale(-100,100);
        double trX=0;
        double trY=0;
        double d=10;
        double fi=0;
        double deltaFi=2*Math.PI/n;

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++) {
                double nX = trX + Math.cos(fi) * (j<n-1 ? d : 2.5*d);
                double nY = trY + Math.sin(fi) * (j<n-1 ? d : 2.5*d);

                StdDraw.line(trX, trY, nX, nY);
                trX = nX;
                trY = nY;
                if(j<n-1) fi = fi + deltaFi;
            }
        }
    }

    static void radar(){
        StdDraw.setScale(-100,100);
        StdDraw.setPenColor(Color.green);
        StdDraw.setPenRadius(0.01);

        StdDraw.enableDoubleBuffering();


        double kot=0;
        double deltaKot=2*Math.PI/360;

        while(true){
            StdDraw.clear(Color.BLACK);
            for(int i=0; i<4; i++) {
                StdDraw.circle(0, 0, (i * 20) + 20);
            }
            double x=Math.cos(kot)*80;
            double y=Math.sin(kot)*80;

            StdDraw.line(0,0,x,y);
            kot=kot+deltaKot;

            StdDraw.show();
            StdDraw.pause(10);
        }
    }


    static void ura(){
        StdDraw.setScale(-100,100);
        double kot=0;
        for(int i=0; i<36; i++){
            double x1= Math.cos(Math.toRadians(kot))*80;
            double y1= Math.sin(Math.toRadians(kot))*80;
            double x2= Math.cos(Math.toRadians(kot))*80;
            double y2= Math.sin(Math.toRadians(kot))*80;
            StdDraw.line(x1,y1,x2,y2);
            kot=kot+10;
        }
    }


    public static void main(String[] args) {
        barvniKvadratki();
    }
}
