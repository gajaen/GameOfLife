package _Game;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class CanvasFrame  {

    private Canvas CanvasId;
    private GraphicsContext gc;
    private int cellSize;
    private int cellGap;
    private int HEIGHT;
    private int WIDTH;
    private double lineWidth;
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



    public void clearCanvas() {
        gc.clearRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());
        gc.setFill(getBackgroundColor());
        gc.fillRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());
        drawLines();

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


    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }




}




