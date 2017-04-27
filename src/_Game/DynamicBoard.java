package _Game;

import java.util.ArrayList;
import java.util.List;

public class DynamicBoard {


    StaticBoard staticBoard;
    private int height;
    private int width;



    public DynamicBoard(int height1, int width2) {

        this.height = height1;
        this.width = width2;
    }

    public void Dynamic() {
        List<List<Integer>> liste = new ArrayList<List<Integer>>();
        int x = height;
        int y = width;

        int x1 = 10; //Testtall
        int y1 = 10; //Testtall


        for(int i = 0; i < x1; i++) {
            List<Integer> inner = new ArrayList<Integer>();
            for(int j = 0; j < y1; j++)
                inner.add(0);
            liste.add(inner);
        }

        liste.get(0).set(0, 5); // replace fordi Integer er immutable

        System.out.println("\nDynamisk 2D tabell:");
        liste.forEach((l)->System.out.println(l));

        liste.get(1).add(10);
        System.out.println("\nJagged 2D tabell:");
        liste.forEach((l)->System.out.println(l));
    }
}




