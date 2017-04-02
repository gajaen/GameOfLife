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


public class GUI implements Initializable {
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        canvasFrame = new CanvasFrame(this.CanvasId);

        canvasFrame.clearCanvas();
        canvasFrame.drawCells();
        canvasFrame.drawLines();
        oldI = 0;
        oldJ = 0;

    }

    public void clickedRandomButton() {
        //Lager en ny random array for hver gang start er trykket.
        for (int i = 0; i < canvasFrame.getHEIGHT(); i++) {
            for (int j = 0; j < canvasFrame.getWIDTH(); j++) {
                canvasFrame.setBoardRandom(i,j);
            }
        }
        CanvasFrame drawCell = new CanvasFrame(CanvasId);

        canvasFrame.setCellColor(Color.WHITE);
        canvasFrame.drawCells();
        canvasFrame.drawLines();

        Timeline();


    }


    public void clickedClearButton() {
        timeline.stop();
        canvasFrame.cleanArray();
    }


    public void clickedStartButton() {

        timeline.playFromStart();
    }

    public void colorPickerClicked() {
        Color color = colorPicker.getValue();
        if (color != null) {
            canvasFrame.setCellColor(colorPicker.getValue());
        }
    }

    public void clickedStopButton() {

        timeline.stop();


    }

    public void FPSClicked() {
       //FPS = (int) sliderFPS.getValue();
    }

    public void CellSizeClicked() {
        canvasFrame.setCellSize((int) cellSlider.getValue());
    }
    public void Timeline() {
        FPSClicked();
        TIME = 1000 / 120;
        timeline = new Timeline(new KeyFrame(Duration.millis(TIME), e -> {
            canvasFrame.nextGeneration();
            timeline.playFromStart();

        }));

    }

    public void closeWindow() {
        Platform.exit();
    }
    public void CanvasPressed(MouseEvent a) {

        int j = ((int) a.getX() / canvasFrame.getCellSize())  + 1;
        int i = ((int) a.getY() / canvasFrame.getCellSize()) + 1;

        //int board [][] = canvasFrame.getBoard();

        if (j != oldJ || i != oldI) {

            canvasFrame.setBoardXY(i,j);


        }
        oldJ = j;
        oldI = i;

        System.out.println(i);
        System.out.println(j);

        //canvasFrame.setBoard(board);
        canvasFrame.setCellColor(Color.WHITE);
        canvasFrame.drawCells();
        Timeline();

    }


    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}



