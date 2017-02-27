package _Game;

import javafx.scene.control.*;
import javafx.scene.canvas.*;


public class Controller {

    public Button startButton;
    public Button stopButton;
    public Canvas CanvasId;
    int cols = 10;
    int rows = 10;
    public GraphicsContext gc;


    public byte[][] board = {
            {1, 0, 0, 1},
            {0, 1, 1, 0},
            {0, 1, 1, 0},
            {1, 0, 0, 1}
    };

    public void initialize(){
        gc = CanvasId.getGraphicsContext2D();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if( board[i][j] == 1) gc.fillRect(10*j, 10*i, 10, 10);
            }
        }
    }

    public void clickedStartButton() {
        gc.fillRect(0, 0, 100, 100);
        System.out.println("You pressed START");
    }

    public void clickedStopButton() {

        System.out.println("You pressed STOP");
    }



}






