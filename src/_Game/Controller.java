package _Game;


import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;

import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

/**
 * The Game Of Life program created for HIOA final project
 * The Controller class is the fx for fxml, all the properties in fxml are assign here.
 * The class is also implementing Initializable interface.
 *
 * @author  Sivert Allergodt Borgeteien & Gajaen Chandrasegaram
 * Studentnr : S315325 & S315285
 * @version 1.0
 * @since   2017-01-14
 */


public class Controller implements Initializable{
    public Canvas CanvasId;
    private CanvasFrame canvasFrame;
    public ColorPicker colorPicker;
    private GUI gui;
    public Slider sliderFPS, cellSlider;
    public Board board;



    /**
     *  Constructs and initializes the canvas and gui.
     *
     *  @param location unused.
     *  @param resources unused.
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        canvasFrame = new CanvasFrame((int) CanvasId.getHeight(), (int) CanvasId.getWidth(), CanvasId.getGraphicsContext2D());
        this.gui = new GUI(canvasFrame);
        key();


    }


    public void key(){
    CanvasId.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
        public void handle(javafx.scene.input.KeyEvent event) {
            canvasFrame.key(event);
        }
    });
    }

    /**
     * This method is calling StartButton method in GUI class.
     */

    public void clickedStartButton(){
        gui.StartButton();
    }

    /**
     * This method is calling ClearButton method in GUI class.
     */
    public void clickedClearButton(){
        gui.ClearButton();
    }

    /**
     * This method is calling RandomButton method in GUI class.
     */
    public void clickedRandomButton() {
        gui.RandomButton();
    }

    /**
     * This method is assigning the colorPicker in fxml to colorPicker in canvasFrame.
     */
    public void colorPickerClicked() {
        canvasFrame.colorPicker(colorPicker);
        }

    /**
     * This method is calling StopButton method in GUI class.
     */
    public void clickedStopButton(){
        gui.StopButton();
    }

    /**
     * This method is changing FPS depended on sliderFPS value in fxml.
     */
    public void FPSClicked(){
        canvasFrame.setFPS((int) sliderFPS.getValue());

    }

    /**
     * This method is changing cellSize depended on cellSlider value in fxml.
     */
    public void CellSizeClicked() {
        canvasFrame.cellSize((int) cellSlider.getValue());
        canvasFrame.drawCanvas();

    }
    public void CanvasReleased(){
    }

    /**
     * This method is creating a Mouseevent and assigning it to CanvasPressed in canvasFrame.
     * @param a MouseEvent.
     * @return Nothing.
     * @exception Exception On input error.
     * @see Exception
     */
    public void CanvasPressed(MouseEvent a) throws Exception {

        canvasFrame.CanvasPressed(a);

    }


    /**
     * This method is reading the RLE file
     * @return Nothing.
     * @exception IOException On input error.
     * @see IOException
     */
    public void openFile() throws IOException {


        ReadGameBoard readGameBoard = new ReadGameBoard(canvasFrame.getHEIGHT(), canvasFrame.getWIDTH());
        readGameBoard.readFile();
        System.out.println("hei");
        canvasFrame.drawPattern(readGameBoard.pattern);

    }

    /**
     * This method is closing the window.
     */
    public void closeWindow(){Platform.exit();}

}

