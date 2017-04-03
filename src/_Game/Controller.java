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
import java.util.ResourceBundle;

public class Controller implements Initializable{
    public Canvas CanvasId;
    public int oldJ, oldI;
    private Timeline timeline;
    private CanvasFrame canvasFrame;
    int TIME;

    private Cell cell;
    private Board board;
    private GUI gui;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        canvasFrame = new CanvasFrame(this.CanvasId);
        cell.setCellColor(Color.LIGHTCYAN);
        canvasFrame.clearCanvas();
        canvasFrame.drawCells();
        canvasFrame.drawLines();
        oldI = 0;
        oldJ = 0;
        Timeline();
    }

    public void Timeline() {
        TIME = 1000 / 120;
        timeline = new Timeline(new KeyFrame(Duration.millis(TIME), e -> {
            board.nextGeneration();
            timeline.playFromStart();

        }));
    }

    public void clickedStartButton(){
        gui.clickedStartButton();

    }






}

