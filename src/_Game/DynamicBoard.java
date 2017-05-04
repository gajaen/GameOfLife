package _Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.*;
import java.lang.Object;
import javafx.scene.effect.Effect;
import javafx.scene.effect.*;


public class DynamicBoard{

    private Cell cell;
    private int canvasHeight;
    private int canvasWidth;
    int  oldJ ;
    int  oldI ;
    private int neighbours;

    public List<List<Byte>> dynamicBoard;



    public DynamicBoard(int height, int width, List<List<Byte>> dynamic)  {

        this.dynamicBoard = dynamic;

        this.canvasHeight = height;
        this.canvasWidth = width;

        this.cell = new Cell();
        fillBoard(dynamicBoard);

    }


    public void fillBoard(List<List<Byte>> board){

        for(int x = 0; x < canvasWidth; x++){
            List<Byte> row = new ArrayList<Byte>();
            for(int y = 0; y < canvasHeight; y++){
                row.add((byte)0);
            }
            board.add(row);
        }
    }



    public void drawCells(GraphicsContext gc ) {


        gc.setFill(cell.getCellColor());
       // System.out.println("1,1" + dynamicBoard.get(1).get(1) );
       // System.out.println("1,2 " + dynamicBoard.get(1).get(2) );
        //this.dynamicBoard = dynamicBoard;
        for (int i = 0; i <  dynamicBoard.size(); i++) {

                for (int j = 0; j < dynamicBoard.get(i).size(); j++) {
                  //  System.out.println(this.dynamicBoard.get(i).get(j) + "i = " + i + "j = " + j);
                    if (dynamicBoard.get(i).get(j) == 1) {

                        gc.fillRect(cell.getCellSize() * j - cell.getCellSize(), cell.getCellSize() * i - cell.getCellSize(), cell.getCellSize() - cell.getCellGap(), cell.getCellSize() - cell.getCellGap());

                    }

                }
            }

    }




    public int countNeighbors(int x, int y){
         neighbours = 0;
        if (x>0 && y>0 && dynamicBoard.get(x-1).get(y-1) == 1){
            neighbours+=1;
        }else{
            neighbours+=0;
        }
        if (y>0 &&dynamicBoard.get(x).get(y-1) == 1){
            neighbours+=1;
        }else{
            neighbours+=0;
        }
        if ( y>0 &&dynamicBoard.get(x+1).get(y-1) == 1){
            neighbours+=1;
        }else{
            neighbours+=0;
        }
        if (x>0 &&dynamicBoard.get(x-1).get(y) == 1){
            neighbours+=1;
        }else{
            neighbours+=0;
        }
        if (x>0 && y>0 &&dynamicBoard.get(x+1).get(y) == 1){
            neighbours+=1;
        }else{
            neighbours+=0;
        }
        if (x>=0  &&dynamicBoard.get(x-1).get(y+1) == 1){
            neighbours+=1;
        }else{
            neighbours+=0;
        }
        if (x>0 && y>0 && dynamicBoard.get(x).get(x+1) == 1){
            neighbours+=1;
        }else{
            neighbours+=0;
        }
        if (x>0 && y>0 &&dynamicBoard.get(x+1).get(y+1) == 1){
            neighbours+=1;
        }else{
            neighbours+=0;
        }
        if(x>0 && y>0 &&dynamicBoard.get(x).get(y) == 1){
            neighbours--;
        }
        return neighbours;
    }



    public void drawLines(GraphicsContext gc, double lineWidth, Color lineColor) {

        gc.setStroke(lineColor);
        gc.setLineWidth(1);
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

    public void nextGeneration(){

        List<List<Byte>> nextBoard = new ArrayList<List<Byte>>();

        fillBoard(nextBoard);

        for (int x = 1; x < dynamicBoard.size(); x++) {
            for (int y = 1; y < dynamicBoard.get(x).size(); y++) {
                    countNeighbors(x, y);

                    if ((dynamicBoard.get(x).get(y) == 1 && neighbours < 2 && neighbours >= 0))
                        nextBoard.get(x).set(y, ((byte) 0));

                    else if ((dynamicBoard.get(x).get(y) == 1) && (neighbours > 3)
                            )
                        dynamicBoard.get(x).set(y, (byte) 0);

                    else if ((dynamicBoard.get(x).get(y) == 0) && (neighbours == 3))
                        dynamicBoard.get(x).set(y, (byte) 1);

                    else dynamicBoard.get(x).set(y, nextBoard.get(x).get(y));

                    //System.out.println(neighbours);


            }

        }

    }



/*    public void nextGeneration() {
        System.out.println(countNeighbors(2, 2));

        List<List<Byte>> nextBoard = new ArrayList<List<Byte>>();
        fillBoard(nextBoard);

        for (int x = 0; x < dynamicBoard.size(); x++) {
            for (int y = 0; y < dynamicBoard.get(x).size(); y++)

            {
                int count = 0;
                for(int i = x - 1; i <= x + 1; i++) {
                    if (i >= 0 && i < dynamicBoard.size()) // fixed here
                        for(int j = y - 1; j <= y + 1; j++)
                            if (j >= 0 && j < dynamicBoard.get(i).size()) // fixed here
                                if (i != x || j != y)
                                    if (dynamicBoard.get(x).get(y) == 1)
                                        count++;


                }

                    //   System.out.println("2");

                int cellNeighbors = 0;
               for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if(x+i>= 0 && y+j >= 0 && x+i<dynamicBoard.size() && y+j<dynamicBoard.get(x).size())
                        cellNeighbors = cellNeighbors + dynamicBoard.get(x + i).get(y + j);

                    }
                }

             //   System.out.println ("3");

             //  cellNeighbors = getNeighbours(dynamicBoard,x,y);

             //   System.out.println("cellneighbors" + cellNeighbors + " x = " + x + " y = " + y );

                //cellNeighbors = cellNeighbors - dynamicBoard.get(x ).get(y);
                //System.out.println("cellneighbors" + cellNeighbors + " x = " + x + " y = " + y );
              //  System.out.println("dynamic value " + dynamicBoard.get(x).get(y) + "x = " + x + "y = " + y + "cell neighbor " + cellNeighbors);
                if ((dynamicBoard.get(x).get(y) == 1) && (count < 2))  nextBoard.get(x).set(y, (byte) 0);           // Mindre enn 2 rundt
                else if ((dynamicBoard.get(x).get(y)  == 1) && (count > 3))
                    nextBoard.get(x).set(y, (byte) 0);// Fler enn 3 rundt seg
                 else if ((dynamicBoard.get(x).get(y)  == 0) && (count == 3))
                    nextBoard.get(x).set(y, (byte) 1);
                else {
                  //  System.out.println("x = " + x + ", y = " + y + " dboard val = " + dynamicBoard.get(x).get(y));
                    dynamicBoard.get(x).set(y, nextBoard.get(x).get(y));
                }

            }
        }

        dynamicBoard = nextBoard;
    }

        public void nextGeneration2() {

         /*   List<List<Byte>> nextBoard =  new ArrayList<List<Byte>>();
            fillBoard(nextBoard);

        for (int x = 1; x < canvasHeight - 1; x++) {
            for (int y = 1; y < canvasWidth - 1; y++)

            {
                int cellNeighbors = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        cellNeighbors += sBoard[x + i][y + j];
                        cellNeighbors = cellNeighbors + dynamicBoard.get(x + i).get(y + j);

                    }
                }

                cellNeighbors = cellNeighbors - dynamicBoard.get(x ).get(y);
                cellNeighbors -= sBoard[x][y];

                if ((dynamicBoard.get(x).get(y) == 1) && (cellNeighbors < 2))  nextBoard.get(x).set(y, (byte) 0);           // Mindre enn 2 rundt

                if ((sBoard[x][y] == 1) && (cellNeighbors < 2)) nextBoard[x][y] = 0;           // Mindre enn 2 rundt

                else if ((dynamicBoard.get(x).get(y)  == 1) && (cellNeighbors > 3))
                else if ((sBoard[x][y] == 1) && (cellNeighbors > 3))

                    nextBoard.get(x).set(y, (byte) 0);// Fler enn 3 rundt seg
                nextBoard[x][y] = 0;           // Fler enn 3 rundt seg

                else if ((dynamicBoard.get(x).get(y)  == 0) && (cellNeighbors == 3))
                else if ((sBoard[x][y] == 0) && (cellNeighbors == 3))
                nextBoard.get(x).set(y, (byte) 1);
                nextBoard[x][y] = 1;           // Akkurat 3 rundt seg

                else nextBoard[x][y] = sBoard[x][y];
                dynamicBoard.get(x).set(y, nextBoard.get(x).get(y));

            }
        }

        sBoard = nextBoard;
            dynamicBoard = nextBoard;

        }
    }
*/




    private int getNeighbours( List<List<Byte>> board, int row, int col) {
        int neighbours = 0;
        for (int xOffset = -1; xOffset < 2; xOffset++) {
            for (int yOffset = -1; yOffset < 2; yOffset++) {
                if ((xOffset != 0 || yOffset != 0) && (board.get(row + yOffset).get(col + xOffset) != 0)) {
                    neighbours++;
                }
            }
        }
        return neighbours;
    }

    public void cleanArray() {
        for (int i = 0; i < dynamicBoard.size(); i++) {
            for (int j = 0; j < dynamicBoard.get(i).size(); j++) {
                dynamicBoard.get(i).set(j, (byte) 0);
            }
        }
    }

    public  void drawPattern(int[][] pattern, GraphicsContext gc) {
        for (int row = 0; row < pattern.length; row++) {
            for (int col = 0; col < pattern[row].length; col++) {
                if (pattern[row][col] == 1) {
                    dynamicBoard.get(row).set(col, (byte) 1);



                }
            }
        }
    }


    public void randomButton(){

        System.out.println(dynamicBoard.size());

        for (int i = 0; i < dynamicBoard.size(); i++) {
            for (int j = 0; j < dynamicBoard.get(i).size(); j++) {

                dynamicBoard.get(i).set(j, (byte) (Math.random() * 2));

            }

        }


    }




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
                if (dynamicBoard.get(i).get(j) == 0){
                    dynamicBoard.get(i).set(j,(byte)1);
                }
                else{ dynamicBoard.get(i).set(j,(byte)0);

                }

            }


            oldJ = j;
            oldI = i;
        }

        catch (Exception e){
            //System.err.println(" Exeption: " + e.getMessage());
            System.out.println("Task interrupted");

        }}


    public void moveCellsRight(){

        List<List<Byte>> rightBoard =  new ArrayList<List<Byte>>();


        fillBoard(rightBoard);


        for (int x = 0; x < dynamicBoard.size(); x++) {
            for (int y = 0; y < dynamicBoard.get(x).size(); y++){
                if (dynamicBoard.get(x).get(y) == 1){
                    if((y +1 ) <= dynamicBoard.get(x).size()) {
                        rightBoard.get(x).set(y + 1, (byte) 1);
                    } else{
                        rightBoard.get(x).set(y, (byte) 1);

                    }
                }


            }

        }
        dynamicBoard = rightBoard;

    }


    public void moveCellsUp(){

        List<List<Byte>> upBoard =  new ArrayList<List<Byte>>();
        fillBoard(upBoard);

        for (int x = 0; x < dynamicBoard.size(); x++) {
            for (int y = 0; y < dynamicBoard.get(y).size(); y++){

                if (dynamicBoard.get(x).get(y) == 1){

                    upBoard.get(y).set(x + 1, (byte) 1);

                }


            }
        }
        dynamicBoard = upBoard;
    }

    public void moveCellsLeft() {

        List<List<Byte>> leftBoard = new ArrayList<List<Byte>>();
        fillBoard(leftBoard);


        for (int x = 0; x < dynamicBoard.size(); x++) {
            for (int y = 0; y < dynamicBoard.get(x).size(); y++){

                if (dynamicBoard.get(x).get(y) == 1) {

                    //leftBoard.get(y).set(x - 1, (byte) 1);
                    leftBoard.get(x-1).set(y,(byte)1);

                }

            }
            dynamicBoard = leftBoard;
        }
    }

    public void moveCellsDown() {
        List<List<Byte>> downBoard = new ArrayList<List<Byte>>();
        fillBoard(downBoard);

        for (int x = 1; x < dynamicBoard.size(); x++) {
            for (int y = 1; y < dynamicBoard.get(x).size(); y++){

                if (dynamicBoard.get(x).get(y) == 1) {

                    downBoard.get(x).set(y - 1, (byte) 1);

                }

            }
            dynamicBoard = downBoard;
        }
    }

    public void setCellSize(double size){
        this.cell.setCellSize(size);
    }

    public void setCellColor(Color color){

        this.cell.setCellColor(color);

    }


    public int getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(int neighbours) {
        this.neighbours = neighbours;
    }
}




