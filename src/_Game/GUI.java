package _Game;

import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;


public class GUI{

    public Slider cellSlider, sliderFPS;
    public ColorPicker colorPicker;
    private Timeline timeline;
    private CanvasFrame canvasFrame;
    private Board board;
    int FPS;


    public GUI(CanvasFrame canvasFrame){

        this.canvasFrame = canvasFrame;
        this.timeline = canvasFrame.SetTimeline();

    }


    public void RandomButton() {
        canvasFrame.RandomButtonAction();
    }


    public void ClearButton() {
        timeline.stop();
        canvasFrame.clearCanvas();
        canvasFrame.clearArray();
    }


    public void StartButton() {
        timeline.playFromStart();

    }

    public void ColorPicker() {
        Color color = colorPicker.getValue();
        if (color != null) {
            canvasFrame.setCellColor(colorPicker.getValue());
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

        // board.setCellSize((int) cellSlider.getValue());
        // canvasFrame.clearCanvas();
        //canvasFrame.drawCells();
        //canvasFrame.drawLines();
    }



    public void closeWindow() {
        Platform.exit();
    }



}



