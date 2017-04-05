package _Game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Controller {
    private Stage stage;
    public Canvas CanvasId;
    public GraphicsContext gc;
    public Button startButton, stopButton, randomButton, clearButton;
    public int cellSize, TIME, cellGap, HEIGHT, WIDTH, oldJ, oldI;
    public double lineWidth;
    public int[][] board, cleanBoard;
    public Color cellColor, lineColor, backgroundColor;
    public Slider cellSlider, sliderFPS;
    public Timeline timeline;
    public ColorPicker colorPicker;

    private GUI gui;


    public int gen = 0;
    public int FPS = 120;


    public void cleanBoard() {
        gc.clearRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());
        gc.setFill(backgroundColor);
        gc.fillRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());

    }


    public void initialize() {

        gc = CanvasId.getGraphicsContext2D();
        gc.setFill(backgroundColor);
        gc.fillRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());

        //Variabler til spillbrettet
        cellSize = 10;
        cellGap = 1;
        lineWidth = 0.4;

        HEIGHT = ((int) CanvasId.getHeight());
        WIDTH = ((int) CanvasId.getWidth());

        board = new int[HEIGHT][WIDTH];
        cleanBoard = new int[HEIGHT][WIDTH];

        System.out.println("CanvasHeight = " + (int) CanvasId.getHeight());
        System.out.println("CanvasWidth = " + (int) CanvasId.getWidth());
        System.out.println("Current FPS = " + FPS);

        //FARGER
        cellColor = Color.ALICEBLUE;
        lineColor = Color.BLACK;
        backgroundColor = Color.GREY;

        drawCells();
        drawLines();
        Timeline();
    }


    public void CanvasPressed(MouseEvent a) {

        int j = ((int) a.getX() / cellSize) + 1;
        int i = ((int) a.getY() / cellSize) + 1;

        if( j != oldJ || i != oldI ) {

            if (board[i][j] == 0) {
                board[i][j] = 1;
            }
        }
        oldJ = j;
        oldI = i;

        drawCells();
        drawLines();


    }


    public void nextGeneration() {
        cleanBoard();
        //System.out.println("Generation = " + gen);
        gen++;
        int[][] nextBoard = new int[HEIGHT][WIDTH];

        for (int x = 1; x < HEIGHT - 1; x++) {
            for (int y = 1; y < WIDTH - 1; y++)

            {
                int cellNeighbors = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        cellNeighbors += board[x + i][y + j];
                    }
                }

                cellNeighbors -= board[x][y];
                if ((board[x][y] == 1) && (cellNeighbors < 2)) nextBoard[x][y] = 0;           // Mindre enn 2 rundt
                else if ((board[x][y] == 1) && (cellNeighbors > 3))
                    nextBoard[x][y] = 0;           // Fler enn 3 rundt seg
                else if ((board[x][y] == 0) && (cellNeighbors == 3))
                    nextBoard[x][y] = 1;           // Akkurat 3 rundt seg
                else nextBoard[x][y] = board[x][y];
            }
        }
        board = nextBoard;

        drawCells();
        drawLines();
        Timeline();
    }


    public void drawCells() {

        cleanBoard();
        gc = CanvasId.getGraphicsContext2D();
        gc.setFill(cellColor);

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (board[i][j] == 1) {
                    gc.fillRect(cellSize * j - cellSize, cellSize * i - cellSize, cellSize - cellGap, cellSize - cellGap);}
            }
        }
    }

    public void drawLines() {
        gc.setStroke(lineColor);
        gc.setLineWidth(5);
        gc.strokeRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());
        gc.setLineWidth(lineWidth);

        int a = cellSize;
        int b = cellSize;

        for (int i = 0; i < HEIGHT; i++) {
            gc.strokeLine(0, a, CanvasId.getWidth(), a);
            a += cellSize;
        }
        for (int i = 0; i < WIDTH; i++) {
            gc.strokeLine(b, 0, b, CanvasId.getHeight());
            b += cellSize;
        }
    }

    public void Timeline() {
        FPSClicked();
        TIME = 1000 / FPS;
        timeline = new Timeline(new KeyFrame(Duration.millis(TIME), e -> {
            nextGeneration();
            timeline.playFromStart();

        }));

    }
    public void Timeline() {

        TIME = 1000 / 120;
        timeline = new Timeline(new KeyFrame(Duration.millis(TIME), e -> {
            board.nextGeneration();
            timeline.playFromStart();

        }));
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

    public void clickedColorPicker(){
        gui.ColorPicker();
    }

    public void clickedStopButton(){
        gui.StopButton();
    }

    public void clickedFpsSlider(){
        gui.fpsSlider();
    }

    public void clickedCellSlider() {
        gui.cellSlider();
    }


    public void CanvasPressed(MouseEvent a) {
        gui.CanvasPressed(a);
    }


    public void init(Stage primaryStage) {

        this.stage = stage;

    }



    public void openFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open GOL Shape");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Run Length Encoded File", "*.RLE"),
                new FileChooser.ExtensionFilter("Text File", "*.txt"),
                new FileChooser.ExtensionFilter("All files", "*")

        );


        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            System.out.println("Choosen file " + file);
        }


        String xPattern = ("x = (\\d+)");
        String yPattern = ("y = (\\d+)");

        initialize();
        cleanBoard();
        drawLines();

        int rownumber = 5;
        int columnnumber = 0;
        int right = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                // checkin g line is empty or commented or with rule line
                if (line.isEmpty() || Pattern.matches(".*#.*", line) || Pattern.matches(".*rule.*", line)) {
                    continue;
                }

                System.out.println(line);

                // split the line with $
                Pattern p = Pattern.compile("(?<=\\$)");

                String[] items = p.split(line);

                for (String item : items) {


                    // itemTmp = 2b3o1b2o$
                    String itemTmp = item;

                    // while itemTmp is a valid form
                    while ((!itemTmp.isEmpty()) && Pattern.matches(".*b.*|.*o.*", itemTmp)) {


                        // b pattern - eg. 34b --> cnumber will be 34
                        Pattern bnumber = Pattern.compile("^(?<cnumber>\\d*?)b");
                        Matcher bmatcher = bnumber.matcher(itemTmp);

                        // o pattern eg. 3o -> onumber will be 3
                        Pattern onumber = Pattern.compile("^(?<onumber>\\d*?)o");
                        Matcher omatcher = onumber.matcher(itemTmp);

                        if (bmatcher.find()) {
                            String bNumString = bmatcher.group("cnumber");
                            int bNumInt = 1;
                            if (!bNumString.isEmpty()) {

                                bNumInt = Integer.parseInt(bNumString);
                            }
                            columnnumber = columnnumber + bNumInt;
                            itemTmp = itemTmp.replaceFirst("^\\d*?b", "");
                        } else if (omatcher.find()) {
                            String oNumString = omatcher.group("onumber");

                            int oNumInt = 1;
                            if (!oNumString.isEmpty()) {

                                oNumInt = Integer.parseInt(oNumString);
                            }


                            for (int cnum = 1; cnum <= oNumInt; cnum++) {
                                board[rownumber + 5 + right][columnnumber + cnum + 4] = 1;

                                //columnnumber = columnnumber +1;
                            }
                            columnnumber = columnnumber + oNumInt;
                            itemTmp = itemTmp.replaceFirst("^\\d*?o", "");
                        }

                    }

                    //if $ ONLY move to next row (row = row + 1 and column =0)
                    if (Pattern.matches(".*\\$", item)) {
                        columnnumber = 0;
                        rownumber = rownumber + 1;
                    }


                }
                drawCells();


            }


            drawLines();

        } catch (IOException e) {
            e.printStackTrace();

        }



    }


    public void closeWindow() {
        Platform.exit();
    }
}



