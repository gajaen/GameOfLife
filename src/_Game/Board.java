package _Game;


public class Board {


    public Board() {



    }

    public int[][] board;


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


    public void setBoardXY(int i, int j)
    {
        if (board[i][j] == 0) {

            board[i][j] = 1;
        }
    }

    public int setBoardRandom(int i, int j) {

        return  board[i][j] = (int) (Math.random() * 2);

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



}
