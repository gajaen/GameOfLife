package _Game;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    public Canvas CanvasId;
    public Button startButton, stopButton, randomButton, clearButton;
    public Slider cellSlider, sliderFPS;
    private int oldJ, oldI;
    public ColorPicker colorPicker;
    private Timeline timeline;
    private CanvasFrame canvasFrame;
    int TIME;
    private Stage stage;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        canvasFrame = new CanvasFrame(this.CanvasId);
        canvasFrame.setCellColor(Color.LIGHTCYAN);
        canvasFrame.clearCanvas();
        canvasFrame.drawCells();
        canvasFrame.drawLines();
        oldI = 0;
        oldJ = 0;
        Timeline();

    }

    public static void Timeline() {
        GUI.FPSClicked();
        TIME = 1000 / 120;
        timeline = new Timeline(new KeyFrame(Duration.millis(TIME), e -> {
            canvasFrame.nextGeneration();
            timeline.playFromStart();

        }));

    }


    public void CellSizeClicked(){

    }


}

