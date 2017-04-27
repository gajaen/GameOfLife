package _Game;

import java.util.ArrayList;
import java.util.List;

public class DynamicBoard {

    private StaticBoard staticBoard;
    private int height;
    private int width;
    byte [][] board;
    private List<List<Integer>> dBoard = new ArrayList<List<Integer>>();




    public DynamicBoard(int height1, int width2, byte [][] board1) {

        this.board = board1;
        this.height = height1;

        this.width = width2;
        //byte[][] sBoard = new staticBoard.getBoard();




    }

    public void Dynamic() {
        int x = height;
        int y = width;

        int x1 = 10; //Testtall
        int y1 = 10; //Testtall


        for(int i = 0; i < x1; i++) {
            List<Integer> inner = new ArrayList<Integer>();
            for(int j = 0; j < y1; j++)
                inner.add(0);
            getdBoard().add(inner);
        }

        getdBoard().get(0).set(0, 5); // replace fordi Integer er immutable

        System.out.println("\nDynamisk 2D tabell:");
        getdBoard().forEach((l)->System.out.println(l));

        getdBoard().get(1).add(10);
        System.out.println("\nJagged 2D tabell:");
        getdBoard().forEach((l)->System.out.println(l));
    }


    public List<List<Integer>> getdBoard() {
        return dBoard;
    }


    public void setdBoard(List<List<Integer>> dBoard) {
        this.dBoard = dBoard;
    }



}




