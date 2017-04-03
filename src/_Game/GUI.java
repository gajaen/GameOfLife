package _Game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;



public class GUI{
    public Canvas CanvasId;
    public Button startButton, stopButton, randomButton, clearButton;
    public Slider cellSlider, sliderFPS;
//    public int cellSize, HEIGHT, WIDTH, oldJ, oldI;
    private int oldJ, oldI;
    public ColorPicker colorPicker;
    private Timeline timeline;
    private CanvasFrame canvasFrame;
    int TIME;
    private Stage stage;
    private Cell cell;
    private Board board;

    public void RandomButton() {
        //Lager en ny random array for hver gang start er trykket.
        for (int i = 0; i < canvasFrame.getHEIGHT(); i++) {
            for (int j = 0; j < canvasFrame.getWIDTH(); j++) {
                board.setBoardRandom(i,j);
            }
        }
        CanvasFrame drawCell = new CanvasFrame(CanvasId);

        canvasFrame.drawCells();
        canvasFrame.drawLines();

    }


    public void ClearButton() {
        timeline.stop();
        board.cleanArray();
    }


    public void StartButton() {
        timeline.playFromStart();

    }

    public void ColorPicker() {
        Color color = colorPicker.getValue();
        if (color != null) {
            cell.setCellColor(colorPicker.getValue());
        }
    }

    public void StopButton() {
        timeline.stop();
    }

    public void FPS()
    {
    //    FPS = (int) sliderFPS.getValue();
    }


    public void CellSize() {

        cell.setCellSize((int) cellSlider.getValue());
        canvasFrame.clearCanvas();
        canvasFrame.drawCells();
        canvasFrame.drawLines();
    }



    public void closeWindow() {
        Platform.exit();
    }

    public void CanvasPressed(MouseEvent a) {

        int j = ((int) a.getX() / cell.getCellSize())  + 1;
        int i = ((int) a.getY() / cell.getCellSize()) + 1;

        //int board [][] = canvasFrame.getBoard();

        if (j != oldJ || i != oldI) {

            board.setBoardXY(i,j);

        }
        oldJ = j;
        oldI = i;

        //canvasFrame.setBoard(board);
        canvasFrame.clearCanvas();
        canvasFrame.drawCells();
        canvasFrame.drawLines();

    }


    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}



