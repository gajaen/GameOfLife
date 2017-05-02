package _Game;


import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * The Game Of Life program created for HIOA final project
 * The Controller class is the fx for fxml, all the properties in fxml are assign here.
 * The class is also implementing Initializable interface.
 *
 * @version 1.0
 * @since   2017-01-14
 */


public class Controller implements Initializable{
    public Canvas CanvasId;
    @FXML private CanvasFrame canvasFrame;
    public ColorPicker colorPicker;
    private GUI gui;
    public Slider sliderFPS, cellSlider;
    public StaticBoard sBoard;
    public DynamicBoard dynamicBoard;
    String line;

     int user_id = 2;

    public Text tekst;

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
        tekst.setText("");

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
        int a =  (int)sliderFPS.getValue();
        canvasFrame.setFPS(a);
        //canvasFrame.SetTimeline();
        canvasFrame.drawCanvas();
    }

    /**
     * This method is changing cellSize depended on cellSlider value in fxml.
     */
    public void CellSizeClicked() {
        double a = cellSlider.getValue();
        canvasFrame.cellSize(a);
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

        readGameBoard.readFile(line);

        canvasFrame.drawPattern(readGameBoard.pattern);

        tekst.setText(" Created on: " + readGameBoard.getCreationDetails(readGameBoard.file) +
                " \n File name: " + readGameBoard.file.getName() +
                " \n Created by: " + readGameBoard.file.getParent() +
                " \n Pattern name: " + readGameBoard.getPatterName() );

    }

    /**
     * This method is closing the window.
     */
    public void closeWindow(){
        Platform.exit();}

    public void saveBoard(){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/_Game/SaveBoard.fxml"));

            //  fxmlLoader.setController(saveBoardController.class);
            Scene scene = new Scene(fxmlLoader.load(), 800, 500);
            SaveGame controller = fxmlLoader.<SaveGame>getController();
            controller.setUser(user_id);
            controller.setBoard(canvasFrame.getBoard());
            Stage stage = new Stage();


            stage.setScene(scene);
            stage.show();



        }
        catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);        }

    }



}

