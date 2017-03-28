package _Game;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;



public class GUI extends GameOfLife{
    public Canvas CanvasId;
    public Button startButton, stopButton, randomButton, clearButton;
    public Slider cellSlider, sliderFPS;
    public ColorPicker colorPicker;


    public void clickedRandomButton() {
        //Lager en ny random array for hver gang start er trykket.
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                board[i][j] = (int) (Math.random() * 2);
            }
        }
        CanvasFrame drawCell = new CanvasFrame();

        drawCell.drawCells();
        drawCell.drawCells();
    }


    public void clickedClearButton() {
        gen = 0;
        timeline.stop();
        initialize();

    }

    public void clickedStartButton() {
        timeline.play();

    }

    public void colorPickerClicked() {
        Color color = colorPicker.getValue();
        if (color != null) {
            cellColor = colorPicker.getValue();
        }
    }

    public void clickedStopButton() {
        timeline.stop();
    }

    public void FPSClicked() {
        FPS = (int) sliderFPS.getValue();
    }

    public void CellSizeClicked() {
        cellSize = (int) cellSlider.getValue();
    }


    public void closeWindow() {
        Platform.exit();
    }
}



