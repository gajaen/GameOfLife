package _Game;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class GUI {

    private Controller controller;
    private Stage stage;


    public void RandomButton() {
        //Lager en ny random array for hver gang start er trykket.
        for (int i = 0; i < controller.HEIGHT; i++) {
            for (int j = 0; j < controller.WIDTH; j++) {
                controller.board[i][j] = (int) (Math.random() * 2);
            }
        }
        controller.drawCells();
        controller.drawLines();
    }

    public void init(Stage primaryStage) {

        this.stage = stage;

    }


    public void ClearButton() {
        controller.gen = 0;
        controller.timeline.stop();
        controller.initialize();

    }

    public void StartButton() {
        controller.timeline.play();

    }

    public void ColorPicker() {
        Color color = controller.colorPicker.getValue();
        if (color != null) {
            controller.cellColor = controller.colorPicker.getValue();
        }
    }

    public void StopButton() {
        controller.timeline.stop();
    }

    public void fpsSlider() {
        controller.FPS = (int) controller.sliderFPS.getValue();
    }

    public void cellSlider() {
        controller.cellSize = (int) controller.cellSlider.getValue();
    }
}