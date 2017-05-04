package _Game;

import junit.framework.TestCase;

/**
 * Created by Sivert on 04.05.2017.
 */
public class ReadGameBoardTest extends TestCase {
    int x;
    int y;
    public void testGetCreationDetails() throws Exception {

    }

    public void testGetPatterName() throws Exception {
        ReadGameBoard readGameBoard = new ReadGameBoard(x, y);
        assertEquals("Patter", readGameBoard.getPatterName());

    }

    public void testGetLine() throws Exception {
        ReadGameBoard readGameBoard = new ReadGameBoard(x, y);
        assertEquals("Patter", readGameBoard.getLine());

    }

}