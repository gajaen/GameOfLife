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





    public byte[][] boardCell = {
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
            {1, 0, 1, 0, 1, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 1, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 1, 1}

    };

    public byte[][] boardClean = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };




    public void initialize() {
        gc = CanvasId.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());
        cellSlider.setMin(5);
        cellSlider.setMax(50);
        cellSlider.setValue(10);
        gc.setFill(Color.GREY);
        for (int i = 0; i < boardClean.length; i++) {
            for (int j = 0; j < boardClean[0].length; j++) {
                if (boardClean[i][j] == 1) gc.fillRect(cellSize * j, cellSize * i, cellSize - 3, cellSize - 3);
            }
        }
    }





    public void clickedStartButton() {
        System.out.println("You pressed START");
        gc = CanvasId.getGraphicsContext2D();
        gc.setFill(Color.LIMEGREEN);
        for (int i = 0; i < boardCell.length; i++) {
            for (int j = 0; j < boardCell[0].length; j++) {
                if( boardCell[i][j] == 1) gc.fillRect(cellSize*j, cellSize*i, cellSize-3, cellSize-3);
            }

        }
    }



    public void clickedStopButton() {

        System.out.println("You pressed STOP");
        gc.setFill(Color.GREY);
        for (int i = 0; i < boardClean.length; i++) {
            for (int j = 0; j < boardClean[0].length; j++) {
                if (boardClean[i][j] == 1) gc.fillRect(cellSize * j, cellSize * i, cellSize - 3, cellSize - 3);
            }
        }
    }



}






