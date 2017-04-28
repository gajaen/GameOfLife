package _Game;

import java.util.ArrayList;
import java.util.List;

public class DynamicBoard {

    private StaticBoard staticBoard;
    private int height;
    private int width;
    private byte [][] board;
    private List<List<Byte>> dBoard = new ArrayList<>();


    public DynamicBoard(int height1, int width2, byte[][]board1) {

        this.height = height1;
        this.width = width2;
        this.board = board1;

        //byte[][] sBoard = new staticBoard.getBoard();
    }

    public void Dynamic() {

        System.out.println("\n");


        int x = height;
        int y = width;

        int x1 = 10; //Testtall
        int y1 = 10; //Testtall

        for (int i = 0; i < x1; i++) {
            List<Byte> inner = new ArrayList<Byte>();
            for (int j = 0; j < y1; j++)
                inner.add((byte)(0));
            dBoard.add(inner);
        }

        dBoard.get(1).set(1,(byte) 1);



        System.out.println("\nDynamisk 2D tabell:");
        dBoard.forEach((l) -> System.out.println(l));

        dBoard.get(2).add((byte)1);
        System.out.println("\nJagged 2D tabell:");
        dBoard.forEach((l) -> System.out.println(l));
    }
}




