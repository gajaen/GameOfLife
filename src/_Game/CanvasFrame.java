package _Game;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class CanvasFrame  {

    private Canvas CanvasId;
    public GraphicsContext gc;
    private int HEIGHT;
    private int WIDTH;
    private double lineWidth;
    public Color lineColor;
    private Color backgroundColor;
    public Cell cell;
    public Board board;


    public CanvasFrame(Canvas canvas){

        cell = new Cell(this.cell);
        board = new Board(this.board);

        CanvasId = canvas;
        // create a board object and assign to private variable

        cell.setCellSize(10);
        cell.setCellGap(0);
        lineWidth = 0.001;
        // (Color.GREY);


        setBackgroundColor(Color.GREY);
        setHEIGHT(((int) CanvasId.getHeight()));
        setWIDTH(((int) CanvasId.getWidth()));

        board.setBoard(new int[getHEIGHT()][getWIDTH()]);
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
        gc.setFill(cell.getCellColor());

        for (int i = 0; i < getHEIGHT(); i++) {
            for (int j = 0; j < getWIDTH(); j++) {
                if (board.getBoard()[i][j] == 1) {
                    gc.fillRect(cell.getCellSize() * j - cell.getCellSize(), cell.getCellSize() * i - cell.getCellSize(), cell.getCellSize() - cell.getCellGap(), cell.getCellSize() - cell.getCellGap());
                }
            }
        }
    }

    public void drawLines() {

        gc.setStroke(lineColor);
        gc.setLineWidth(5);
        gc.strokeRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());
        gc.setLineWidth(lineWidth);

        int a = cell.getCellSize();
        int b = cell.getCellSize();

        for (int i = 0; i < getHEIGHT(); i++) {
            gc.strokeLine(0, a, CanvasId.getWidth(), a);
            a += cell.getCellSize();
        }
        for (int i = 0; i < getWIDTH(); i++) {
            gc.strokeLine(b, 0, b, CanvasId.getHeight());
            b += cell.getCellSize();
        }
        System.out.println(cell.getCellSize());

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


    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }




}




