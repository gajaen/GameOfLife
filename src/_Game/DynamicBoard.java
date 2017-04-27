package _Game;

import java.util.ArrayList;
import java.util.List;

public class DynamicBoard {

    StaticBoard sBoard;
    private int height;
    private int width;

    public DynamicBoard(int height1, int width2){

        this.height = height1;
        this.width = width2;
    }

   public void Dynamic() {

       List<List<Integer>> liste = new ArrayList<List<Integer>>();
        for (int i = 0; i < height; i++) {
            List<Integer> inner = new ArrayList<Integer>();
            for (int j = 0; j < width; j++)
                inner.add(0);
            liste.add(inner);
        }


   }


}
