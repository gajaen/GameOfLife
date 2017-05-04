package _Game;

import junit.framework.TestCase;
import javafx.scene.paint.Color;

/**
 * Created by Sivert on 04.05.2017.
 */
public class CellTest extends TestCase {

    public void testGetCellSize() throws Exception {
        Cell cell = new Cell();
        assertEquals((double)10, cell.getCellSize());

    }
    public void testGetCellColor() throws Exception {
        Cell cell = new Cell();
        assertEquals(Color.WHITE, cell.getCellColor());

    }

    public void testGetCellGap() throws Exception {
        Cell cell = new Cell();
        assertEquals(1, cell.getCellGap());

    }

}