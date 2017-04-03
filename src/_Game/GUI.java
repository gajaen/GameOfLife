package _Game;

import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class GUI{
    public Slider cellSlider;
    private int oldJ, oldI;
    public ColorPicker colorPicker;
    public Stage stage;
    public Cell cell;
    public Board board;

    public Controller controller;
    public CanvasFrame canvasFrame;
    public GUI gui;

    public void RandomButton() {
        //Lager en ny random array for hver gang start er trykket.
        for (int i = 0; i < canvasFrame.getHEIGHT(); i++) {
            for (int j = 0; j < canvasFrame.getWIDTH(); j++) {
                board.setBoardRandom(i,j);
            }
        }

        canvasFrame.drawCells();
        canvasFrame.drawLines();
    }


    public void ClearButton() {
        controller.timeline.stop();
        board.cleanArray();
    }


    public void StartButton() {
        controller.timeline.playFromStart();

    }

    public void ColorPicker() {
        Color color = colorPicker.getValue();
        if (color != null) {
            cell.setCellColor(colorPicker.getValue());
        }
    }

    public void StopButton() {
        controller.timeline.stop();
    }

    public void FPS()
    {
        // FPS = (int) sliderFPS.getValue();
    }


    public void CellSize() {

        cell.setCellSize((int) cellSlider.getValue());
        canvasFrame.clearCanvas();
        canvasFrame.drawCells();
        canvasFrame.drawLines();
    }

    public void CanvasPressed(MouseEvent a){
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



    public void closeWindow() {
        Platform.exit();
    }


    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}



