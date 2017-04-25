package _Game;

import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;


public class GUI{

    private Timeline timeline;
    private CanvasFrame canvasFrame;

    /**
     * Initializes canvasFrame
     *
     *  @param canvasFrame creating CanvasFrame object
     */


    public GUI(CanvasFrame canvasFrame){

        this.canvasFrame = canvasFrame;
        this.timeline = canvasFrame.SetTimeline();
    }

    /**
     * This method stop the calling the RandomButtonAction in canvasFrame.
     */

    public void RandomButton() {
        canvasFrame.RandomButtonAction();
        startNoise();
    }

    /**
     * This method stop the timeline, cleaning canvas and clearing the array.
     */

    public void ClearButton() {
        startNoise();
        timeline.stop();
        canvasFrame.clearCanvas();
        canvasFrame.clearArray();
    }

    /**
     * This method playing the timeline from start.
     */
    public void startNoise() {
        String musicFile = "button.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public void StartButton() {
        startNoise();
        timeline.playFromStart();
    }

    /**
     * This method stop the timeline.
     */

    public void StopButton() {
        timeline.stop();
        startNoise();
    }

}



