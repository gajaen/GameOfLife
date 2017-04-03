package _Game;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    public Canvas CanvasId;
    public Timeline timeline;

    int TIME;


    public Board board;
    public ReadGameBoard readGameBoard;
    public CanvasFrame canvasFrame;
    @Override
    public void initialize(URL location, ResourceBundle resources) {



        canvasFrame = new CanvasFrame(this.CanvasId);
        canvasFrame.drawCells();

/*
        cell.setCellColor(Color.LIGHTCYAN);
        canvasFrame.clearCanvas();
        canvasFrame.drawCells();
        canvasFrame.drawLines();
        oldI = 0;
        oldJ = 0;
        */
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
        gui.StartButton();
    }

    public void clickedClearButton(){
        gui.ClearButton();
    }

    public void clickedRandomButton() {
        gui.RandomButton();
    }

    public void colorPickerClicked(){
        gui.ColorPicker();
    }

    public void clickedStopButton(){
        gui.StopButton();
    }

    public void FPSClicked(){
        gui.FPS();
    }

    public void CellSizeClicked() {
        gui.CellSize();
    }


    public void CanvasPressed(MouseEvent a) {
        gui.CanvasPressed(a);
    }

    public void openFile() throws IOException {
        readGameBoard.openFile();
    }

    public void closeWindow(){
    }

    public void getStage(){
        getStage();
    }
    public void setStage(){
        setStage();
    }


}

