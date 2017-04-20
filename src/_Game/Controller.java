package _Game;


import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static _Game.CanvasFrame.*;

public class Controller implements Initializable{
    public Canvas CanvasId;
    private CanvasFrame canvasFrame;
    public ColorPicker colorPicker;
    public Cell cell;
    private GUI gui;
    public Slider sliderFPS;





    @Override
    public void initialize(URL location, ResourceBundle resources) {

        canvasFrame = new CanvasFrame( (int)CanvasId.getHeight(),(int) CanvasId.getWidth(), CanvasId.getGraphicsContext2D());


        this.gui = new GUI(canvasFrame);


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

    public void colorPickerClicked() {

        canvasFrame.colorPicker(colorPicker);
        }


    public void clickedStopButton(){
        gui.StopButton();
    }

    public void test(){
        System.out.print("test");
    }

    public void CanvasReleased(){

    }

    public void FPSClicked(){
        canvasFrame.setFPS((int) sliderFPS.getValue());

    }

    public void CellSizeClicked() {

        gui.CellSize();
    }


    public void CanvasPressed(MouseEvent a) {

        canvasFrame.CanvasPressed(a);

    }

    public void openFile() throws IOException {


        canvasFrame.boardopen();


        ReadGameBoard readGameBoard = new ReadGameBoard(canvasFrame.getHEIGHT(),canvasFrame.getWIDTH());
        readGameBoard.readFile();
        canvasFrame.drawPattern(readGameBoard.pattern);


    }

    public void closeWindow(){Platform.exit();}

}

