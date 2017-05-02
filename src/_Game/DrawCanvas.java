package _Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class DrawCanvas {
    private StaticBoard staticBoard;
    private Controller controller;
    private CanvasFrame canvasFrame;
    private Cell cell;
    private int canvasHeight;
    private int canvasWidth;
    private byte[][] sBoard;
    int oldJ;
    int oldI;

    public DrawCanvas(int height, int width, byte[][] sBoard){
        this.canvasHeight = height;
        this.canvasWidth = width;
        this.sBoard = sBoard;
        this.cell = new Cell();

        System.out.print(sBoard);
    }


    /**
     * This method is used to draw a vertical and horizontal lines on canvas.
     *
     * @return Nothing.
     * @param gc is the first parameter to draw on canvas
     * @param lineWidth is the second choosing the thickness of line
     * @param lineColor is the third parameter and it is choosing the color of line
     *
     */

    public void drawLines(GraphicsContext gc, double lineWidth, Color lineColor) {

        gc.setStroke(lineColor);
        gc.setLineWidth(3);
        gc.strokeRect(0, 0, this.canvasWidth, this.canvasHeight);
        gc.setLineWidth(lineWidth);

        double a = cell.getCellSize();
        double b = cell.getCellSize();

        for (int i = 0; i < this.canvasHeight; i++) {
            gc.strokeLine(0, a, this.canvasWidth, a);
            a += cell.getCellSize();
        }
        for (int i = 0; i < this.canvasWidth; i++) {
            gc.strokeLine(b, 0, b, this.canvasHeight);
            b += cell.getCellSize();
        }

    }

    /**
     * This method is used to draw a cell on canvas.
     *
     * @return Nothing.
     *
     */
    public void drawCells(GraphicsContext gc) {


        gc.setFill(cell.getCellColor());

        for (int i = 0; i < this.canvasHeight; i++) {
            for (int j = 0; j < this.canvasWidth; j++) {
                if (sBoard[i][j] == 1) {
                    gc.fillRect(cell.getCellSize()  j - cell.getCellSize(), cell.getCellSize()  i - cell.getCellSize(), cell.getCellSize() - cell.getCellGap(), cell.getCellSize() - cell.getCellGap());
                }
            }
        }
    }


    /**
     * This method is used to draw when clicked on canvas.
     *
     * @return Nothing.
     * @exception Exception On input error.
     * @see Exception
     */

    public void CanvasPressed(MouseEvent a) throws Exception {

        try {

            int j = (int)(a.getX() / cell.getCellSize()) + 1;
            int i = (int)(a.getY() / cell.getCellSize()) + 1;

            if (i <= 0) {
                return;
            }
            if (j <= 0) {
                return;
            }

            if (j != oldJ || i != oldI) {
                if (sBoard[i][j] == 0) {
                    sBoard[i][j] = 1;
                }
            }

            oldJ = j;
            oldI = i;
        }

        catch (Exception e){
            //System.err.println(" Exeption: " + e.getMessage());
            System.out.println("Task interrupted");

        }}

    public void setBoard(byte[][] board) {

        this.sBoard = board;
    }

    /**
     * This method is used to get current board.
     *
     * @return int this returns current board.
     */

    public byte[][] getBoard() {

        return sBoard;
    }





    //*************************MOVE CELLS WITH ARROWS***************************

    public void moveCellsRight(){
        byte[][] rightBoard = new byte[canvasHeight][canvasWidth];

        for (int x = 1; x < canvasHeight - 1; x++) {
            for (int y = 1; y < canvasWidth - 1; y++){
                if ((sBoard[x][y] == 1))rightBoard[x][y+1] = 1;
            }
        }
        sBoard = rightBoard;
    }

    public void moveCellsUp(){

        byte[][] upBoard = new byte[canvasHeight][canvasWidth];

        for (int x = 1; x < canvasHeight - 1; x++) {
            for (int y = 1; y < canvasWidth - 1; y++){
                if ((sBoard[x][y] == 1))upBoard[x-1][y] = 1;
            }
        }
        sBoard = upBoard;
    }
    public void moveCellsLeft(){
        byte[][] leftBoard = new byte[canvasHeight][canvasWidth];

        for (int x = 1; x < canvasHeight - 1; x++) {
            for (int y = 1; y < canvasWidth - 1; y++){
                if ((sBoard[x][y] == 1))leftBoard[x][y-1] = 1;

            }
        }
        sBoard = leftBoard;
    }
    public void moveCellsDown(){
        byte[][] downBoard = new byte[canvasHeight][canvasWidth];

        for (int x = 0; x < canvasHeight; x++) {
            for (int y = 0; y < canvasWidth; y++){
                if ((sBoard[x][y] == 1))downBoard[x+1][y] = 1;
            }
        }
        sBoard = downBoard;
    }

    //**********************************************************************************


}