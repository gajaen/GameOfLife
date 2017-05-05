package _Game;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import java.io.*;
import java.net.URL;
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
    private Sounds sounds;
    public ColorPicker colorPicker;
    public ChoiceBox patternChoiceBox, musicChoiceBox;
    private GUI gui;
    private Timeline timeline;


    public Slider sliderFPS, cellSlider;
    public StaticBoard sBoard;
    public DynamicBoard dynamicBoard;
    public Button musicStartButton, drawPattern;
    public ToolBar Toolbar;
    String line;
    public ToggleButton toggleButton;
    @FXML
    TextField textBox;
    private Timeline tl;


    int user_id = 2;
    ReadGameBoard readGameBoard;
    public Text tekst;
    double x, y;

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
        sounds = new Sounds();
        key();
        tekst.setText("");
        patternChoiceBox();
        musicChoiceBox();

    }


    public void key() {
        CanvasId.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            public void handle(javafx.scene.input.KeyEvent event) {
                canvasFrame.key(event);

            }
        });
    }

    public void musicChoiceBox(){
        sounds.startClick();
        musicChoiceBox.getItems().add("Take On Me");
        musicChoiceBox.getItems().add("Through The Fire and Flames");
        musicChoiceBox.getItems().add("Shooting Stars");
        musicChoiceBox.getItems().add("Radioactive");
        musicChoiceBox.getItems().add("Knights of Cydonia");
        musicChoiceBox.getItems().add("Shape Of You");
        musicChoiceBox.setValue("Shape Of You");
        musicStartButton.setOnAction(event -> getMusicChoice(musicChoiceBox));
    }

    public void getMusicChoice(ChoiceBox<String>musicChoiceBox) {
        switch (musicChoiceBox.getValue()) {
            case "Take On Me":
                sounds.TakeOnMe();
                break;
            case "Through The Fire and Flames":
                sounds.FireAndFlames();
                break;
            case "Shooting Stars":
                sounds.ShootingStars();
                break;
            case "Radioactive":
                sounds.Radioactive();
                break;
            case "Knights of Cydonia":
                sounds.KnightsofCydonia();
                break;
            case "Shape Of You":
                sounds.ShapeOfYou();
                break;

        }
    }


    public void clickedMusicStartButton(){

    }
    public void clickedMusicPauseButton(){
        sounds.click();
        sounds.Pause();

    }

    public void clickedMusicStopButton(){
        sounds.click();
        sounds.Stop();

    }


    @FXML
    public void clickedToolbar(){
        final double xOffset = 640;
        final double yOffset = 20;

        Toolbar.setOnMouseDragged(event ->
        {
            Window window = ((Node)event.getTarget()).getScene().getWindow();
            window.setX(event.getScreenX() - xOffset);
            window.setY(event.getScreenY() - yOffset);
        });
    }


    public void clickedStartButton() {
       int TIME = 1000/canvasFrame.getFPS();


        timeline = new Timeline(new KeyFrame(Duration.millis(TIME), e -> {

            canvasFrame.getDynamicBoard().nextGeneration();
            canvasFrame.clearCanvas();

//            try{Thread.sleep(100);} catch (Exception a){}
            canvasFrame.pressedCanvas();
            timeline.playFromStart();


        }));
    timeline.play();

    }

    public void clickedClearButton() {
        timeline.stop();
        sounds.click();
        gui.ClearButton();
    }

    public void clickedRandomButton() {
        sounds.click();
        gui.RandomButton();
    }

    public void patternChoiceBox(){
        patternChoiceBox.getItems().add("Random");
        patternChoiceBox.getItems().add("None");
        patternChoiceBox.setValue("Random");
        drawPattern.setOnAction(event -> getPatternChoice(patternChoiceBox));
    }

    public void getPatternChoice(ChoiceBox<String>patternChoiceBox) {
        switch (patternChoiceBox.getValue()) {
            case "Random":
                System.out.println(patternChoiceBox.getValue());
                canvasFrame.RandomButtonAction();
                break;
            case "None":
                System.out.println("None");
                gui.ClearButton();
                break;
        }
    }

    public void clickedToggleButton(ActionEvent event) {
        if (toggleButton.isSelected()) {
            System.out.println("Toggle Static");
        } else {
            System.out.println("Toggle Dunamic");

        }
    }
    public void clickedDrawButton(){

    }

    public void clickedExitButton(){
        sounds.click();
        Platform.exit();
    }

    public void colorPickerClicked() {
        sounds.click();
        canvasFrame.colorPicker(colorPicker);
    }

    public void clickedStopButton() {
        timeline.stop();
        sounds.click();
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
    @FXML
    private void openFile() throws IOException {
        readGameBoard = new ReadGameBoard(canvasFrame.getHEIGHT(), canvasFrame.getWIDTH());
        readGameBoard.readFile(line);
        canvasFrame.drawPattern(readGameBoard.pattern);
        tekst.setText(" Created on: " + readGameBoard.getCreationDetails(readGameBoard.file) + " File name: " + readGameBoard.file.getName() +
                "  Created by: " + readGameBoard.file.getParent() +
                "  Pattern name: " + readGameBoard.getPatterName());
        if(readGameBoard.getCell() > 0){
        canvasFrame.cellSize(readGameBoard.getCell());}

        canvasFrame.clearCanvas();

        canvasFrame.pressedCanvas();
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

