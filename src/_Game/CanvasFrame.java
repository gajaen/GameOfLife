package _Game;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.sql.Time;


public class CanvasFrame  {

    private Canvas CanvasId;
    private GraphicsContext gc;
    private int cellSize;
    private int cellGap;
    private int HEIGHT;
    private int WIDTH;
    private double lineWidth;
    public int[][] board, cleanArray;
    private Color cellColor;
    private Color lineColor;
    private Color backgroundColor;




    public int gen = 0;

    public CanvasFrame(Canvas canvas){

        CanvasId = canvas;
        // create a board object and assign to private variable

        setCellSize(10);
        setCellGap(0);
        lineWidth = 0.001;
        // (Color.GREY);

        setBackgroundColor(Color.GREY);
        setHEIGHT(((int) CanvasId.getHeight()));
        setWIDTH(((int) CanvasId.getWidth()));

        setBoard(new int[getHEIGHT()][getWIDTH()]);

        gc = CanvasId.getGraphicsContext2D();
        gc.setFill(Color.GREY);
        gc.fillRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());


    }

    public void cleanArray() {
        for (int i = 0; i < getHEIGHT(); i++) {
            for (int j = 0; j < getWIDTH(); j++) {
                board[i][j] = 0;
            }
        }
       //Kode som får cleanArray til å bli board, samme prinsipp som nextBoard
        clearCanvas();
        drawLines();
        drawCells();
    }


    public void clearCanvas() {
        gc.clearRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());
        gc.setFill(getBackgroundColor());
        gc.fillRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());
        drawLines();

    }




    public void nextGeneration() {
        clearCanvas();
        gen++;
        int[][] nextBoard = new int[getHEIGHT()][getWIDTH()];

        for (int x = 1; x < getHEIGHT() - 1; x++) {
            for (int y = 1; y < getWIDTH() - 1; y++)

            {
                int cellNeighbors = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        cellNeighbors += getBoard()[x + i][y + j];
                    }
                }

                cellNeighbors -= getBoard()[x][y];
                if ((getBoard()[x][y] == 1) && (cellNeighbors < 2)) nextBoard[x][y] = 0;           // Mindre enn 2 rundt
                else if ((getBoard()[x][y] == 1) && (cellNeighbors > 3))
                    nextBoard[x][y] = 0;           // Fler enn 3 rundt seg
                else if ((getBoard()[x][y] == 0) && (cellNeighbors == 3))
                    nextBoard[x][y] = 1;           // Akkurat 3 rundt seg
                else nextBoard[x][y] = getBoard()[x][y];
            }
        }

        setBoard(nextBoard);

        drawCells();
        drawLines();
      //  Timeline();
    }


    public void drawCells() {

        //cleanBoard();
        gc = CanvasId.getGraphicsContext2D();
        gc.setFill(getCellColor());


        for (int i = 0; i < getHEIGHT(); i++) {
            for (int j = 0; j < getWIDTH(); j++) {
                if (getBoard()[i][j] == 1) {
                    gc.fillRect(getCellSize() * j - getCellSize(), getCellSize() * i - getCellSize(), getCellSize() - getCellGap(), getCellSize() - getCellGap());
                }
            }
        }
    }

    public void drawLines() {

        gc.setStroke(lineColor);
        gc.setLineWidth(5);
        gc.strokeRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());
        gc.setLineWidth(lineWidth);

        int a = getCellSize();
        int b = getCellSize();

        for (int i = 0; i < getHEIGHT(); i++) {
            gc.strokeLine(0, a, CanvasId.getWidth(), a);
            a += getCellSize();
        }
        for (int i = 0; i < getWIDTH(); i++) {
            gc.strokeLine(b, 0, b, CanvasId.getHeight());
            b += getCellSize();
        }
}


    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    public int getCellGap() {
        return cellGap;
    }

    public void setCellGap(int cellGap) {
        this.cellGap = cellGap;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public Color getCellColor() {
        return cellColor;
    }

    public void setCellColor(Color cellColor) {
        this.cellColor = cellColor;
    }
    public void setBoardXY(int i, int j)
    {
        if (board[i][j] == 0) {

            board[i][j] = 1;
        }
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int setBoardRandom(int i, int j) {

      return  board[i][j] = (int) (Math.random() * 2);


    }
    public void setOpenBoard(int rownumber, int columnnumber, int cnum) {

        board[rownumber + 5][columnnumber + cnum + 4] = 1;
    }
}




