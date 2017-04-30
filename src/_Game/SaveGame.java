package _Game;

import javafx.application.Platform;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.io.IOException;

import static java.awt.Color.BLUE;

/**
 * Created by Gajaen on 30.04.2017.
 */


public class SaveGame {
    private int HEIGHT, WIDTH, TIME, FPS;
   // private Board board;
    GraphicsContext gc;
    public Canvas saveCanvas;
    Cell cell;

    public SaveGame()  {

        cell = new Cell();


    }

    public void saveCanvas(){

      //  board.drawCells(gc);

    }

    public void fileName(){
        System.out.println("gei");
    }

    public void author(){
        System.out.println("gei");
    }

    public void description(){
        System.out.println("gei");
    }

    public void rules(){
        System.out.println("gei");
    }

    public void drawCells(GraphicsContext gc) {

        gc.setFill(cell.getCellColor());

        for (int i = 0; i < saveCanvas.getHeight(); i++) {
            for (int j = 0; j < saveCanvas.getWidth(); j++) {
            //    if (board.getBoard()[i][j] == 1) {
                    gc.fillRect(cell.getCellSize() * j - cell.getCellSize(), cell.getCellSize() * i - cell.getCellSize(), cell.getCellSize() - cell.getCellGap(), cell.getCellSize() - cell.getCellGap());
          //      }
            }
        }
    }

    public void saveBtn() throws IOException {
       // drawCells(gc);
       /* String path = "testgif2.gif";
        int width = 100;
        int height = 100;
        int timePerMilliSecond = 1000; // 1 second

        // create the GIFWriter object
        lieng.GIFWriter gwriter = new lieng.GIFWriter(width,height,path,timePerMilliSecond);

        // fill the upper half of the image with blue
        gwriter.fillRect(0, width-1, 0, height/2, BLUE);

        // insert the painted image to the animation sequence
        // and proceed to the next image
        gwriter.insertAndProceed();

        // fill the lower half of the image with blue
        gwriter.fillRect(0, width-1, height/2, height-1, BLUE);

        // insert the painted image into the animation sequence
        gwriter.insertCurrentImage();

        // close the GIF stream.
        gwriter.close();

        System.out.println("done!");*/





    }

    public void closeBtn(){
        Platform.exit();
    }


}
