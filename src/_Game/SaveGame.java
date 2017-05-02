package _Game;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Translate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.ScrollPane;
import java.awt.color.ColorSpace;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;

import static java.awt.Color.BLUE;

/**
 * Created by Gajaen on 30.04.2017.
 */


public class SaveGame implements Initializable {
    private int HEIGHT, WIDTH, TIME, FPS;
    byte [][] board ;/* = {{0,1,1,0},
                        {1,0,0,1},
                        {1,0,0,1},
                        {0,1,1,0}};*/


    public Canvas saveCanvas;
    public Canvas canvasScroll;
    public GraphicsContext gc;
    private Stage stage;
    StaticBoard staticBoard;
    public Color cellColor;
    private double cellSize;
    private int cellGap;
    ScrollPane scrollPane;


    int user_id;


    public SaveGame()  {
        cellSize = 5;
        cellGap = 1;
        cellColor = Color.BLACK;

        FXMLLoader fxmlLoader = new FXMLLoader();
        CanvasFrame canvasFrame = fxmlLoader.getController();

    }

    public void setUser(int user_id){
        this.user_id = user_id;
    }

    public void setBoard(byte[][] board1){


        this.board = board1;
        System.out.println(board);
    }


    public void saveCanvas(){

      //  board.drawCells(gc);

    }

    public void fileName(){
        System.out.println("gei");
    }

    public void author(){
        System.out.println("gei");
    }

    public void description(){
        System.out.println("gei");
    }

    public void rules(){
        System.out.println("gei");
    }



    public void drawCells(GraphicsContext gc) throws ArrayIndexOutOfBoundsException {

        this.gc = saveCanvas.getGraphicsContext2D();

        gc.setFill(cellColor);
        try {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] == 1) {
                        gc.fillRect(cellSize * j - cellSize, cellSize * i - cellSize, cellSize - cellGap, cellSize - cellGap);
                    }
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.printf("dette" + e);
        }

    }

    public void init(Stage primaryStage) {
        this.stage = stage;


    }

    public void saveBtn() throws IOException {

        FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showSaveDialog(stage);

        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("All files", "*")

        );

        if(file != null){
            System.out.println("hei");
        }
        /*

        String path = "gife.gif";
        int width = 100;
        int height = 100;
        int timePerMilliSecond = 1000; // 1 second

        // create the GIFWriter object
        lieng.GIFWriter gwriter = new lieng.GIFWriter(width,height,path,timePerMilliSecond);

        // fill the upper half of the image with blue
        gwriter.fillRect(0, width-1, 0, height/2, BLUE);

        // insert the painted image to the animation sequence
        // and proceed to the next image
        gwriter.insertAndProceed();

        // fill the lower half of the image with blue
        gwriter.fillRect(0, width-1, height/2, height-1, BLUE);

        // insert the painted image into the animation sequence
        gwriter.insertCurrentImage();

        // close the GIF stream.
        gwriter.close();


        System.out.println("done!");*/



    }

    //public void writeGoLSequenceToGIF(lieng.GIFWriter writer, SaveGame game, int counter){






    public void closeBtn(){


        drawCells(saveCanvas.getGraphicsContext2D());
//            drawCells(gc);

    }

    public void nextGeneration() {

        byte[][] nextBoard = new byte[(int)canvasScroll.getHeight()][(int)canvasScroll.getWidth()];

        for (int x = 1; x < board.length - 1; x++) {
            for (int y = 1; y < board.length - 1; y++)

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
    }



    public void strip() {

        GraphicsContext graphicScroll = canvasScroll.getGraphicsContext2D();

        Affine xform = new Affine();
        double xpadding = 0;
        double tx = xpadding;

        for (int a = 0; a > 20; a++) {
            xform.setTx(tx);
            graphicScroll.setTransform(xform);
            drawCells(graphicScroll);
            tx += board.length + xpadding;
            nextGeneration();
        }

        xform.setTx(0.0);
//        gc.setTransform(xform);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
