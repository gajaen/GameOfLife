package _Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import javax.lang.model.element.Element;
import java.util.*;

public class DynamicBoard {

    private StaticBoard staticBoard;
    private Controller controller;
    private CanvasFrame canvasFrame;
    private Cell cell;
    private int canvasHeight;
    private int canvasWidth;
    private byte[][] sBoard;
    public byte[][] testBoard;
    public ArrayList[][] dBoard;
    public ArrayList[][] cleanArray;
    public ArrayList[][] trueBoard;
    //public List<List> testdBoard;
    public ArrayList[][] testdBoard;
    public LinkedList testDBoard;

    public DynamicBoard(int height, int width, byte[][] board) {
        this.canvasHeight = height;
        this.canvasWidth = width;
        this.sBoard = board;
        this.cell = new Cell();
        testBoard = new byte[10][10];
        testdBoard = new ArrayList[10][10];
        System.out.println(testdBoard);
        testBoard();
    }

    public void testBoard(){

        //Starter med å lage et statisk brett
        System.out.println("\ntestBoard = Static");

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                testBoard[i][j] = (byte) (Math.random() * 2);
            }
        }

        for(byte[] inner : testBoard)
            System.out.println(Arrays.toString(inner));
        ArrayConvert();
    }


    public void ArrayConvert2(){
        System.out.println("\nArray til ArrayList");

        //gjør om statisk brett og skriver ut

        List<List> testdBoard = new ArrayList<>();

        for(byte[] array : testBoard){
           /* testdBoard.add(Arrays.asList(array));
            System.out.println(Arrays.toString(testdBoard.toArray()));;
            System.out.println(Arrays.toString(array));*/

        }

        System.out.println("testdBoard = dynamic");
        testdBoard.forEach((l)->System.out.println(l));

        for(int c = 0; c > testdBoard.size(); c++){

            System.out.println(testdBoard.get(c));
        }

        ElementArray();



    }

    public void ArrayConvert(){
        System.out.println("\nArray til ArrayList (2)");
        int x= 10;
        int y = 10;
        //testdBoard = new ArrayList[x][y];
        testdBoard[0][0] = new ArrayList();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                testdBoard[i][j] = new ArrayList();
            }
        }
        for (int a = 0; a < x; a++)
            for (int b = 0; b < y; b++) {
                testdBoard[a][b].add(testBoard[a][b]);
            }

        //testdBoard[7][0].add((byte)1);


        for (ArrayList[] inner : testdBoard)
            System.out.println(Arrays.toString(inner));

        System.out.println(testdBoard);
        ElementArray();


    }


    public void ElementArray(){
        System.out.println(testdBoard + "Element");
        System.out.println("\naddElement");

        for (ArrayList[] inner : testdBoard)
            System.out.println(Arrays.toString(inner));

        //testdBoard[0][0].add((byte)1);
        //testdBoard.forEach((l)->System.out.println(l));

        //ArrayListConvert();
    }



    public void ArrayListConvert(){
        System.out.println("\n ArrayList to Array");
    }




    //****************************************************************************

    public void ArrayConvert3() {

        staticBoard = new StaticBoard(new byte [this.canvasWidth] [this.canvasHeight], this.canvasWidth, this.canvasHeight);

        int x = canvasHeight;
        int y = canvasWidth;
        ArrayList[][] dBoard = new ArrayList[x][y];
        dBoard[0][0] = new ArrayList();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                dBoard[i][j] = new ArrayList();
            }
        }

        for (int a = 0; a < x; a++)
            for (int b = 0; b < y; b++) {
                dBoard[a][b].add(sBoard[a][b]);
            }
       // for (ArrayList[] inner : dBoard) System.out.println(Arrays.toString(inner));
    }





    public void cleanArrayTest2() {
        System.out.println(" ");

        int x = 10;
        int y = 10;
        ArrayList[][] cleanArray = new ArrayList[x][y];
        cleanArray[0][0] = new ArrayList();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                cleanArray[i][j] = new ArrayList();
                cleanArray[i][j].add((byte)0);
            }
        }
        /*for (ArrayList[] inner : cleanArray)
            System.out.println(Arrays.toString(inner));

        dBoard = cleanArray;
        */

    }


    public void randomBoard() {
        ArrayList[][] trueBoard = new ArrayList[1][1];
        trueBoard[0][0] = new ArrayList();
        trueBoard[0][0].add((byte)1);

        System.out.println(" ");
        int x = 10;
        int y = 10;
        ArrayList[][] randomBoard = new ArrayList[x][y];
        randomBoard[0][0] = new ArrayList();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                randomBoard[i][j] = new ArrayList();
                randomBoard[i][j].add((byte) (Math.random() * 2));
            }
        }
        for (ArrayList[] inner : randomBoard)
            System.out.println(Arrays.toString(inner));

        if (randomBoard[0][0].equals(trueBoard[0][0])){
                    System.out.println("[0][0] = True");
                }
        else System.out.println("[0][0] = False");
    }



    /**
     * This method is used to make a new board with rules.
     *
     * @return Nothing.
     */
    public void nextGeneration() {
       ArrayList[][] trueBoard = new ArrayList[1][1];
        trueBoard[0][0] = new ArrayList();
        trueBoard[0][0].add((byte) 1);


        ArrayList[][] nextBoard = new ArrayList[canvasHeight][canvasWidth];
  /*      for (int a = 0; a < 10; a++) {
            for (int b = 0; b < 10; b++)
                nextBoard[10][10] = new ArrayList();


            for (int x = 1; x < canvasHeight - 1; x++) {
                for (int y = 1; y < canvasWidth - 1; y++) {
                    int cellNeighbors = 0;

                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            cellNeighbors += dBoard[x + i][y + j];
                        }
                    }

                    cellNeighbors -= dBoard[x][y];
                    if ((dBoard[x][y].equals(trueBoard[0][0])) && (cellNeighbors < 2))
                        nextBoard[x][y].add((byte) 0);           // Mindre enn 2 rundt

                    else if ((dBoard[x][y].equals(trueBoard[0][0])) && (cellNeighbors > 3))
                        nextBoard[x][y].add((byte) 0);           // Fler enn 3 rundt seg

                    else if ((dBoard[x][y].equals(trueBoard[0][0])) && (cellNeighbors == 3))
                        nextBoard[x][y].add((byte) 1);           // Akkurat 3 rundt seg

                    else nextBoard[x][y] = dBoard[x][y];
                }
            }
            dBoard = nextBoard;
      }*/
    }

}




