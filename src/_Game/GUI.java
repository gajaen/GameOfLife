package _Game;

import javafx.animation.Timeline;



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
    }

    /**
     * This method stop the timeline, cleaning canvas and clearing the array.
     */

    public void ClearButton() {
        timeline.stop();
        canvasFrame.clearCanvas();
        canvasFrame.clearArray();
    }

    /**
     * This method playing the timeline from start.
     */

    public void StartButton() {
        timeline.playFromStart();
    }

    /**
     * This method stop the timeline.
     */

    public void StopButton() {
        timeline.stop();
    }

}



