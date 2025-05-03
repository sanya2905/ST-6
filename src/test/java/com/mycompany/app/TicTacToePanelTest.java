package com.mycompany.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.GridLayout;

public class TicTacToePanelTest {

    private TicTacToePanel panel;
    private Game game;

    @BeforeEach
    public void setUp() {
        System.setProperty("java.awt.headless", "true");
        panel = new TicTacToePanel(new GridLayout(3, 3));
        game = panel.getGame();
    }

    @Test
    public void testPanelInitialization() {
        TicTacToeCell[] cells = panel.getCells();
        assertEquals(9, cells.length);

        for (TicTacToeCell cell : cells) {
            assertEquals(' ', cell.getMarker());
            assertTrue(cell.isEnabled());
        }

        assertEquals(game.player1, game.cplayer);
    }

    @Test
    public void testPlayerMoveUpdatesCell() {
        TicTacToeCell cell = panel.getCells()[0];
        cell.doClick();

        assertEquals('X', cell.getMarker());
        assertFalse(cell.isEnabled());
    }

    @Test
    public void testGameStateAfterWin() {
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = 'X';

        game.symbol = 'X';
        game.state = game.checkState(game.board);
        assertEquals(State.XWIN, game.state);
    }

    @Test
    public void testBoardUpdatesAfterMove() {
        TicTacToeCell cell = panel.getCells()[0];
        cell.doClick();

        assertEquals('X', game.board[0]);
        assertEquals(' ', game.board[1]);
    }
}