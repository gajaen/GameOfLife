package _Game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller   {
    private Stage stage;
    public Canvas CanvasId;
    public GraphicsContext gc;
    public Button startButton, stopButton, randomButton, clearButton;
    public int cellSize, TIME, cellGap, HEIGHT, WIDTH;
    public double lineWidth;
    public int[][] board, cleanBoard;
    public Color cellColor, lineColor, backgroundColor;
    public Slider cellSlider, sliderFPS;
    public Timeline timeline;
    public ColorPicker colorPicker;

    public int gen = 0;
    public int FPS = 120;


    public void cleanBoard()
    {
        gc.clearRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());
        gc.setFill(backgroundColor);
        gc.fillRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());

    }


    public void initialize()
    {
        HEIGHT = (int)CanvasId.getHeight();
        WIDTH = (int)CanvasId.getWidth();
        gc = CanvasId.getGraphicsContext2D();
        gc.setFill(backgroundColor);
        gc.fillRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());

        board = new int[HEIGHT][WIDTH];
        cleanBoard = new int[HEIGHT][WIDTH];

        System.out.println("CanvasHeight = " + (int)CanvasId.getHeight());
        System.out.println("CanvasWidth = " + (int)CanvasId.getWidth());
        System.out.println("Current FPS = " + FPS);

        //FARGER
        cellColor = Color.ALICEBLUE;
        lineColor = Color.BLACK;
        backgroundColor = Color.GREY;

        //Variabler til spillbrettet
        cellSize = 20;
        cellGap = 1;
        lineWidth = 0.3;

        colorPickerClicked();
        drawCells();
        drawLines();
        Timeline();

    }


    public  void nextGeneration()
    {
        cleanBoard();
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
                else if ((board[x][y] == 1) && (cellNeighbors > 3)) nextBoard[x][y] = 0;           // Fler enn 3 rundt seg
                else if ((board[x][y] == 0) && (cellNeighbors == 3)) nextBoard[x][y] = 1;           // Akkurat 3 rundt seg
                else nextBoard[x][y] = board[x][y];
            }
        }
        board = nextBoard;

        drawCells();
        drawLines();
        Timeline();
    }

    public void drawCells()
    {
        System.out.println("Generation = " + gen);
        gen++;

        cleanBoard();
        gc = CanvasId.getGraphicsContext2D();
        gc.setFill(cellColor);

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if( board[i][j] == 1) gc.fillRect(cellSize*j-cellSize, cellSize*i-cellSize , cellSize - cellGap, cellSize- cellGap);
            }
        }
    }

    public void drawLines()
    {
        gc.setStroke(lineColor);
        gc.setLineWidth(4);
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

    public void Timeline(){
        FPSClicked();
        TIME = 1000/FPS;
        timeline = new Timeline(new KeyFrame(Duration.millis(TIME), e -> {
            nextGeneration();
            timeline.playFromStart();

        }));

    }

    //KNAPPER
    //**********************************************************************************
    public void clickedRandomButton()
    {
        //Lager en ny random array for hver gang start er trykket.
        for (int i = 0;i < HEIGHT;i++) {
            for (int j = 0;j < WIDTH;j++) {
                board[i][j] = (int)(Math.random()*2);
            }
        }
        drawCells();
        drawLines();
    }

    public void clickedClearButton()
    {
        gen = 0;
        timeline.stop();
        initialize();

    }

    public void clickedStartButton()
    {
        clickedRandomButton();
        timeline.play();

    }


    public void clickedStopButton()
    {
        timeline.stop();
    }

    public void FPSClicked()
    {
        FPS = (int)sliderFPS.getValue();
    }
    public void CellSizeClicked()
    {
        cellSize = (int)cellSlider.getValue();
    }
    //***************************************

    public void init(Stage primaryStage)
    {

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



        String  xPattern = ("x = (\\d+)");
        String yPattern = ("y = (\\d+)");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {


                String line = scanner.nextLine();
                System.out.println(line);
                Pattern p = Pattern.compile("\\$");

                String [] items = p.split(line);
                int rownumber = 0;
                drawLines();

                for(String item: items) {

                    int columnnumber = 0;

                    if(Pattern.matches(".*b.*", item   )) {
                        System.out.println("pattern matches b");
                        if (Pattern.matches(".*\\db.*", item)) {
                            System.out.println("Pattern matches b and number");
                            Pattern bp = Pattern.compile("b");
                            System.out.println("Pattern bp combile");
                            String[] numbers = bp.split(item);
                            System.out.println("Pattern split to numbers");
                            String occureance = numbers[0];
                            System.out.println(occureance);
                            System.out.println("Pattern occurances");
                            int numbertimes = Integer.parseInt(occureance);
                            System.out.println(numbertimes);

                            columnnumber = numbertimes;

                            for (int x = 0; x <= numbertimes; x++) {
                            }

                        } else {
                            System.out.println("draw for b");
                            columnnumber = 1;
                        }
                    }

                        if(Pattern.matches(".*o.*", item ))
                        {
                            System.out.println("pattern matches o");
                            if(Pattern.matches(".*\\do.*", item))
                            {
                                System.out.println("Pattern matches o and number");
                                Pattern bp = Pattern.compile(".*(?<onumber>\\d)o");
                                Matcher matcher = bp.matcher(item);
                                String occurance = "0";

                                if(matcher.find())
                                {
                                    occurance = matcher.group("onumber");
                                }

                                System.out.println("Pattern bp o combile");
                                System.out.println("Pattern split to numbers");


                                int numbertimes = Integer.parseInt(occurance);
                                System.out.println(numbertimes);

                                for(int x = 0; x< numbertimes; x++) {

                                    //        if( board[rownumber][columnnumber] == 1) gc.fillRect(cellSize* columnnumber + cellSize * x , cellSize * rownumber , cellSize - cellGap, cellSize- cellGap);
                                    gc.setFill(Color.YELLOW);

                                    gc.fillRect(cellSize* columnnumber + cellSize * x , cellSize * rownumber , cellSize - cellGap, cellSize- cellGap);
                                    gc.fillRect(cellSize*columnnumber , cellSize*rownumber , cellSize - cellGap, cellSize- cellGap);

                                }

                            }
                            else{
                                System.out.println("draw for o");
                                gc.setFill(Color.GREEN);
                                gc.fillRect(cellSize*columnnumber , cellSize*rownumber , cellSize - cellGap, cellSize- cellGap);
                            }

                        }



                    rownumber = rownumber + 1;
                    System.out.println(item);


                }}
                } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void closeWindow()
    {
        Platform.exit();
    }

    public void colorPickerClicked()
    {
        Color color = colorPicker.getValue();
        if(color!=null)
        {
            cellColor = colorPicker.getValue();
        }



        }





}