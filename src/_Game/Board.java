package _Game;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board {

    private Cell cell;
    private ReadGameBoard readGameBoard;
    public int[][] board;
    int canvasWidth;
    int canvasHeigth;


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


    }


    public void drawCells(GraphicsContext gc) {

        gc.setFill(cell.getCellColor());

        for (int i = 0; i < this.canvasHeigth; i++) {
            for (int j = 0; j < this.canvasWidth; j++) {
                if (getBoard()[i][j] == 1) {
                    gc.fillRect(cell.getCellSize() * j - cell.getCellSize(), cell.getCellSize() * i - cell.getCellSize(), cell.getCellSize() - cell.getCellGap(), cell.getCellSize() - cell.getCellGap());
                }
            }
        }
    }


    public void drawLines(GraphicsContext gc, double lineWidth, Color lineColor) {

        gc.setStroke(lineColor);
        gc.setLineWidth(5);
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



    public void opened(){

        board[readGameBoard.getRownumber()+cell.getCellSize()][readGameBoard.getColumnnumber()+readGameBoard.getCnum()+cell.getCellSize()] = 1;


    }


    public void setBoardXY(int i, int j)
    {
        if (board[i][j] == 0) {

            board[i][j] = 1;
        }
    }

    public int setBoardRandom(int i, int j) {

        return  board[i][j] = (int) (Math.random() * 2);

    }
    public void setCellColor(Color color){
        this.cell.setCellColor(color);
    }

    public void setOpenBoard(int rownumber, int columnnumber, int cnum, int[][] board) {

        this.board = board;

        board[rownumber + 5][columnnumber + cnum + 4] = 1;


    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int[][] getBoard() {
        return board;
    }

  /*  public void setBoard2(int i, int j){
        if (getBoard()[i][j] == 1) {
            canvasFrame.getGc().fillRect(cell.getCellSize() * j - cell.getCellSize(), cell.getCellSize() * i - cell.getCellSize(), cell.getCellSize() - cell.getCellGap(), cell.getCellSize() - cell.getCellGap());
        }
    }*/


}
