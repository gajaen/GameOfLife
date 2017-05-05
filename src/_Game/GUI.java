package _Game;

import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;


public class GUI{

    private Timeline timeline;
    private CanvasFrame canvasFrame;

    public GUI(CanvasFrame canvasFrame){

        this.canvasFrame = canvasFrame;
        this.timeline = canvasFrame.SetTimeline();
    }

    public void RandomButton() {
        canvasFrame.RandomButtonAction();
    }

    public void ClearButton() {
        canvasFrame.clearCanvas();
        canvasFrame.clearArray();
        timeline.stop();
    }

    public void StopButton() {
        timeline.stop();
        canvasFrame.drawCanvas();
    }

}



