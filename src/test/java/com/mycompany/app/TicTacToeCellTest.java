package com.mycompany.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Font;

public class TicTacToeCellTest {

    private TicTacToeCell cell;

    @BeforeEach
    public void setUp() {
        cell = new TicTacToeCell(5, 1, 2);
    }

    @Test
    public void testConstructorInitialization() {
        assertEquals(5, cell.getNum());
        assertEquals(1, cell.getCol());
        assertEquals(2, cell.getRow());
        assertEquals(' ', cell.getMarker());
        assertTrue(cell.isEnabled());
        assertEquals(" ", cell.getText());

        Font font = cell.getFont();
        assertEquals("Arial", font.getName());
        assertEquals(Font.PLAIN, font.getStyle());
        assertEquals(40, font.getSize());
    }

    @Test
    public void testSetMarkerX() {
        cell.setMarker("X");
        assertEquals('X', cell.getMarker());
        assertEquals("X", cell.getText());
        assertFalse(cell.isEnabled());
    }

    @Test
    public void testSetMarkerO() {
        cell.setMarker("O");
        assertEquals('O', cell.getMarker());
        assertEquals("O", cell.getText());
        assertFalse(cell.isEnabled());
    }

    @Test
    public void testGettersConsistency() {
        assertEquals(5, cell.getNum());
        assertEquals(1, cell.getCol());
        assertEquals(2, cell.getRow());
        assertEquals(' ', cell.getMarker());

        cell.setMarker("X");
        assertEquals('X', cell.getMarker());
        assertEquals(5, cell.getNum());
        assertEquals(1, cell.getCol());
        assertEquals(2, cell.getRow());
    }

    @Test
    public void testInitialState() {
        assertTrue(cell.isEnabled());
        assertEquals(" ", cell.getText());
    }

    @Test
    public void testStateAfterSettingMarker() {
        cell.setMarker("O");
        assertFalse(cell.isEnabled());
        assertEquals("O", cell.getText());
    }

    @Test
    public void testDifferentPositions() {
        TicTacToeCell cell1 = new TicTacToeCell(0, 0, 0);
        assertEquals(0, cell1.getNum());
        assertEquals(0, cell1.getCol());
        assertEquals(0, cell1.getRow());

        TicTacToeCell cell2 = new TicTacToeCell(8, 2, 2);
        assertEquals(8, cell2.getNum());
        assertEquals(2, cell2.getCol());
        assertEquals(2, cell2.getRow());
    }

    @Test
    public void testCellVisualProperties() {
        assertTrue(cell.isVisible());
        assertNotNull(cell.getFont());
    }

    @Test
    public void testCellVisibility() {
        assertTrue(cell.isVisible());
        cell.setVisible(false);
        assertFalse(cell.isVisible());
    }

    @Test
    public void testFontChange() {
        Font newFont = new Font("Serif", Font.BOLD, 20);
        cell.setFont(newFont);
        assertEquals(newFont, cell.getFont());
    }
}
