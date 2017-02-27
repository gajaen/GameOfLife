package _Game;

import javafx.scene.control.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;


public class Controller {

    public Button startButton;
    public Button stopButton;
    public Canvas CanvasId;
    public GraphicsContext gc;
    public Slider cellSlider;
    public int cellSize = 50;





    public byte[][] board = {
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1, 0, 1}

    };



    public void initialize(){
        gc = CanvasId.getGraphicsContext2D();
        gc.setFill(Color.GREY);
        gc.fillRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());
        cellSlider.setMin(5);
        cellSlider.setMax(50);
        cellSlider.setValue(10);


    }

    public void clickedStartButton() {
        System.out.println("You pressed START");
        gc = CanvasId.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if( board[i][j] == 1) gc.fillRect(cellSize*j, cellSize*i, cellSize, cellSize);
            }

        }
    }

    public void clickedStopButton() {

        System.out.println("You pressed STOP");
        gc.setFill(Color.GREY);
        gc.fillRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());
    }



}






