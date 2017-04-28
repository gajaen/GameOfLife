package _Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Sivert on 28.04.2017.
 */
public class DrawCanvas {

    StaticBoard sBoard;
    Cell cell;

    public DrawCanvas(){

    }


    /**
     * This method is used to draw the reaadgameboard pattern.
     *
     * @return Nothing.
     * @param pattern is the first parameter and it is the array for the new pattern.
     * @param gc is the second parameter and is used to drawing on the canvas.
     *
     */

    public  void drawPattern(int[][] pattern, GraphicsContext gc)

    {
        for (int row=0; row < pattern.length; row++)
        {
            for (int col=0; col < pattern[row].length; col++)
            {
                if(pattern[row][col] ==1)
                {
                    sBoard.getsBoard()[row][col] = 1;
                }
            }
        }
        drawCells(gc);

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
                if (board[i][j] == 1) {
                    gc.fillRect(cell.getCellSize() * j - cell.getCellSize(), cell.getCellSize() * i - cell.getCellSize(), cell.getCellSize() - cell.getCellGap(), cell.getCellSize() - cell.getCellGap());
                }
            }
        }
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

    public void clearCanvas() {
        gc.clearRect(0, 0, this.WIDTH, this.HEIGHT);
        gc.setFill(getBackgroundColor());
        gc.fillRect(0, 0, this.WIDTH, this.HEIGHT);

    }






}
