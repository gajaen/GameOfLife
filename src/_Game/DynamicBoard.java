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



       System.out.print(height);

       int x = 10;
       int y = 10;

     //  System.out.print(x);

        List<List<Integer>> liste = new ArrayList<List<Integer>>();
        for (int i = 0; i < x; i++) {
            List<Integer> inner = new ArrayList<Integer>();
            for (int j = 0; j < y; j++)
                inner.add(0);
            liste.add(inner);
        }


   }


}
