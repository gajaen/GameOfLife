package _Game;


import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import java.util.Scanner;
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


public class Controller implements Initializable {
    public Canvas CanvasId;
    @FXML
    private CanvasFrame canvasFrame;
    public ColorPicker colorPicker;
    public ChoiceBox choiceBox, musicChoiceBox;
    private GUI gui;
    public Slider sliderFPS, cellSlider;
    public StaticBoard sBoard;
    public DynamicBoard dynamicBoard;
    public Main main;
    public Button musicStartButton;
    public ToolBar Toolbar;
    String line;
    @FXML
    TextField textBox;

    double xOffset = 0;
    double yOffset = 0;

    int user_id = 2;
    ReadGameBoard readGameBoard;
    public Text tekst;

    /**
     * Constructs and initializes the canvas and gui.
     *
     * @param location  unused.
     * @param resources unused.
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canvasFrame = new CanvasFrame((int) CanvasId.getHeight(), (int) CanvasId.getWidth(), CanvasId.getGraphicsContext2D());
        this.gui = new GUI(canvasFrame);
        key();
        tekst.setText("");
        ChoiceBox();
        MusicChoiceBox();
    }


    public void key() {
        CanvasId.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            public void handle(javafx.scene.input.KeyEvent event) {
                canvasFrame.key(event);
            }
        });
    }

    public void MusicChoiceBox(){
        musicChoiceBox.getItems().add("Take On Me");
        musicChoiceBox.getItems().add("Through The Fire and Flames");
        musicChoiceBox.getItems().add("Shooting Stars");
        musicChoiceBox.getItems().add("Radioactive");
        musicChoiceBox.getItems().add("Knights of Cydonia");
        musicChoiceBox.setValue("Take On Me");

        musicStartButton.setOnAction(event -> getChoice(musicChoiceBox));
    }

    public void getChoice(ChoiceBox<String>musicChoiceBox) {

        switch (musicChoiceBox.getValue()) {
            case "Take On Me":
                TakeOnMe();
                break;
            case "Through The Fire and Flames":
                FireAndFlames();
                break;
            case "Shooting Stars":
                ShootingStars();
                break;
            case "Radioactive":
                Radioactive();
                break;
            case "Knights of Cydonia":
                KnightsofCydonia();

        }
    }

    public void TakeOnMe(){
        String musicFile = "TakeOnMe.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public void FireAndFlames(){
        String musicFile = "FireAndFlames.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
    public void ShootingStars(){
        String musicFile = "ShootingStars.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
    public void Radioactive(){
        String musicFile = "Radioactive.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

    }
    public void KnightsofCydonia(){
        String musicFile = "KnightsofCydonia.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

    }



    
//test
    public void clickedMusicStartButton(){

    }
    public void clickedMusicPauseButton(){

    }
    public void clickedMusicStopButton(){
        String musicFile = "Button.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }



    public void clickedToolbar(){
    }
    public void clickedStartButton() {
        gui.StartButton();
    }

    public void clickedClearButton() {
        gui.ClearButton();
    }

    public void clickedRandomButton() {
        gui.RandomButton();
    }

    public void ChoiceBox(){
        choiceBox.getItems().add("Random");
        choiceBox.setValue("Random");
    }

    public void clickedExitButton(){
        Platform.exit();
    }

    public void colorPickerClicked() {
        canvasFrame.colorPicker(colorPicker);
    }

    public void clickedStopButton() {
        gui.StopButton();
    }

    public void FPSClicked() {
        int a = (int) sliderFPS.getValue();
        canvasFrame.setFPS(a);
        //canvasFrame.SetTimeline();
        canvasFrame.drawCanvas();
    }

    public void CellSizeClicked() {
        double a = cellSlider.getValue();
        canvasFrame.cellSize(a);
        canvasFrame.drawCanvas();
    }

    public void CanvasReleased() {
    }

    public void CanvasPressed(MouseEvent a) throws Exception {
        canvasFrame.CanvasPressed(a);
    }


    /**
     * This method is reading the RLE file
     *
     * @return Nothing.
     * @throws IOException On input error.
     * @see IOException
     */
    public void openFile() throws IOException {


        readGameBoard = new ReadGameBoard(canvasFrame.getHEIGHT(), canvasFrame.getWIDTH());

        readGameBoard.readFile(line);

        canvasFrame.drawPattern(readGameBoard.pattern);

        tekst.setText(" Created on: " + readGameBoard.getCreationDetails(readGameBoard.file) + " File name: " + readGameBoard.file.getName() +
                "  Created by: " + readGameBoard.file.getParent() +
                "  Pattern name: " + readGameBoard.getPatterName());

    }

    /**
     * This method is closing the window.
     */
    public void closeWindow() {
        Platform.exit();
    }

    public void saveBoard() {

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

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }

    }

    public void patternLoad() throws IOException {

        URL url = new URL(textBox.getText());
        InputStream in = url.openStream();
        Scanner scan = new Scanner(in);

        while (scan.hasNext())
        {
            String str = scan.nextLine();
//            readGameBoard.readFile(str);
            System.out.println(str);
        }
        scan.close();

        /*
        String in;


        System.out.println(textBox.getText());

        URL url = new URL(textBox.getText());
        URLConnection conn = url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));


        while ((in = br.readLine()) != null) {

            System.out.println(in);
            //readGameBoard.readFile(in);


        }

        br.close();
*/

        //   readGameBoard.readFile(in);


    }
    

}

