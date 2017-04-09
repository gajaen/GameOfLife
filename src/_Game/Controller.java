package _Game;


import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    public Canvas CanvasId;
    private CanvasFrame canvasFrame;
    private GUI gui;


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

    public void colorPickerClicked(){
        gui.ColorPicker();
    }

    public void clickedStopButton(){
        gui.StopButton();
    }

    public void FPSClicked(){
    }

    public void CellSizeClicked() {
        gui.CellSize();
    }


    public void CanvasPressed(MouseEvent a) {

        canvasFrame.CanvasPressed(a);



    }

    public void openFile() throws IOException {


        canvasFrame.boardopen();



    }

    public void closeWindow(){Platform.exit();}

}

