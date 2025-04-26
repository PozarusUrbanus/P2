package Domace_naloge;

import edu.princeton.cs.algs4.StdDraw;

public class DN06 {
    public static void sudoku(String [][] mreza) {
        StdDraw.setCanvasSize(600, 600);
        StdDraw.setXscale(0, 13);
        StdDraw.setYscale(13, 0);

        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        for(int x = 0; x < 9; x++){
            for(int y = 0; y < 9; y++){
                StdDraw.setPenRadius(0.0015);
                StdDraw.square(x + 2.5 , y + 2.5, 0.5);
                StdDraw.text(x+2.5, y+2.5, mreza[y][x]);
            }
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        for(int x = 0; x < 9; x+=3){
            for(int y = 0; y < 9; y+=3){
                StdDraw.setPenRadius(0.006);
                StdDraw.square(x+3.5, y+3.5, 1.5);
            }
        }
    }
    public static void main(String[] args) {
        String argument = args[0];
        String[] split = argument.split("");
        String[][] mreza = new String[9][9];
        for (int i = 0; i < mreza.length; i++) {
            for (int j = 0; j < mreza[i].length; j++) {
                    mreza[i][j] = split[j+i*9];
                    if (mreza[i][j].equals("0")){
                        mreza[i][j] = "";
                    }
            }
        }

        sudoku(mreza);
    }
}
