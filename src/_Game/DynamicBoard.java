package _Game;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class DynamicBoard {

    StaticBoard sBoard;
    CanvasFrame canvasFrame;


   public void Dynamic() {


       int height = canvasFrame.getHEIGHT();
       int width = canvasFrame.getWIDTH();

       System.out.print(height);

       int x = 10;
       int y = 10;

       System.out.print(x);

        List<List<Integer>> liste = new ArrayList<List<Integer>>();
        for (int i = 0; i < x; i++) {
            List<Integer> inner = new ArrayList<Integer>();
            for (int j = 0; j < y; j++)
                inner.add(0);
            liste.add(inner);
        }


   }

}
