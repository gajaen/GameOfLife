package _Game;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    public Canvas CanvasId;
    public int oldJ, oldI;
    private Timeline timeline;
    private CanvasFrame canvasFrame;
    public GraphicsContext gc;
    int TIME;


    private Cell cell;
    private Board board;
    private GUI gui;
    private ReadGameBoard readGameBoard;


    @Override
    public void initialize(URL location, ResourceBundle resources) {




    canvasFrame.drawCells();

       canvasFrame = new CanvasFrame(this.CanvasId);
/*
        cell = new Cell(this.cell);
         */

        canvasFrame.drawLines();
  //      canvasFrame.drawLines();

/*
        cell.setCellColor(Color.LIGHTCYAN);
        canvasFrame.clearCanvas();
        canvasFrame.drawCells();
        canvasFrame.drawLines();
        oldI = 0;
        oldJ = 0;
        Timeline();*/
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

