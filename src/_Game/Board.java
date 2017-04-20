package _Game;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Board {

    private Cell cell;
    private Controller controller;
    public int[][] board;
    int canvasWidth;
    int canvasHeigth;
    int oldJ;
    int oldI;



    public Board(int[][] boardarray, int canWidth, int canHeight) {

        this.board = boardarray;
        this.canvasHeigth = canHeight;
        this.canvasWidth = canWidth;
        this.cell = new Cell();

    }


    public void nextGeneration() {

        int[][] nextBoard = new int[canvasHeigth][canvasWidth];

        for (int x = 1; x < canvasHeigth - 1; x++) {
            for (int y = 1; y < canvasWidth - 1; y++)

            {
                int cellNeighbors = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        cellNeighbors += board[x + i][y + j];
                    }
                }

                cellNeighbors -= getBoard()[x][y];
                if ((getBoard()[x][y] == 1) && (cellNeighbors < 2)) nextBoard[x][y] = 0;           // Mindre enn 2 rundt
                else if ((getBoard()[x][y] == 1) && (cellNeighbors > 3))
                    nextBoard[x][y] = 0;           // Fler enn 3 rundt seg
                else if ((getBoard()[x][y] == 0) && (cellNeighbors == 3))
                    nextBoard[x][y] = 1;           // Akkurat 3 rundt seg
                else nextBoard[x][y] = board[x][y];
            }
        }

        board = nextBoard;
    }



    public void drawCells(GraphicsContext gc) {

        gc.setFill(cell.getCellColor());


        for (int i = 0; i < this.canvasHeigth; i++) {
            for (int j = 0; j < this.canvasWidth; j++) {
                if (board[i][j] == 1) {
                    gc.fillRect(cell.getCellSize() * j - cell.getCellSize(), cell.getCellSize() * i - cell.getCellSize(), cell.getCellSize() - cell.getCellGap(), cell.getCellSize() - cell.getCellGap());
                }
            }
        }
    }


    public void drawLines(GraphicsContext gc, double lineWidth, Color lineColor) {

        gc.setStroke(lineColor);
        gc.setLineWidth(4);
        gc.strokeRect(0, 0, this.canvasWidth, this.canvasHeigth);
        gc.setLineWidth(lineWidth);

        int a = cell.getCellSize();
        int b = cell.getCellSize();

        for (int i = 0; i < this.canvasHeigth; i++) {
            gc.strokeLine(0, a, this.canvasWidth, a);
            a += cell.getCellSize();
        }
        for (int i = 0; i < this.canvasWidth; i++) {
            gc.strokeLine(b, 0, b, this.canvasHeigth);
            b += cell.getCellSize();
        }
    }

    public void cleanArray() {
        for (int i = 0; i < canvasHeigth; i++) {
            for (int j = 0; j < canvasWidth; j++) {
                board[i][j] = 0;
            }
        }
    }

    public  void drawPattern(int[][] pattern, GraphicsContext gc)
    {


        for (int row=0; row < pattern.length; row++)
        {
            for (int col=0; col < pattern[row].length; col++)
            {
                if(pattern[row][col] ==1)
                {
                    board[row][col] = 1;

                }
            }
        }

        drawCells(gc);

    }

    public void CanvasPressed(MouseEvent a) {


        int j = ((int) a.getX() / cell.getCellSize()) + 1;
        int i = ((int) a.getY() / cell.getCellSize()) + 1;

        if( j != oldJ || i != oldI ) {

            if (board[i][j] == 0) {
                board[i][j] = 1;
            }
        }

        if( j == 2 || i == 2){
            controller.CanvasReleased();
        }


        oldJ = j;
        oldI = i;




    }


    public int setBoardRandom(int i, int j) {

        return  board[i][j] = (int) (Math.random() * 2);

    }
    public void setCellColor(Color color){

        this.cell.setCellColor(color);

    }



    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int[][] getBoard() {
        return board;
    }



}
