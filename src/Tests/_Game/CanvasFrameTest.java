package _Game;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import junit.framework.TestCase;

/**
 * Created by Sivert on 04.05.2017.
 */
public class CanvasFrameTest extends TestCase {
    GraphicsContext gc;
    int height;
    int width;


    public void testGetFPS() throws Exception {
        CanvasFrame canvasFrame = new CanvasFrame(height, width, gc);
        //assertEquals(30, canvasFrame.getFPS());

    }

    public void testGetBoard() throws Exception {
        CanvasFrame canvasFrame = new CanvasFrame(height, width, gc);
        //assertEquals(, canvasFrame.getBoard());

    }

    public void testGetHEIGHT() throws Exception {
        CanvasFrame canvasFrame = new CanvasFrame(height, width, gc);
        assertEquals(800, canvasFrame.getHEIGHT());

    }

    public void testGetWIDTH() throws Exception {

    }

    public void testGetBackgroundColor() throws Exception {

    }

}