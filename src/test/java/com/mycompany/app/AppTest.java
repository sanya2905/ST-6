package com.mycompany.app;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppTest {
    private Game game;
    private Player player;
    @BeforeEach
    public void initializeGameAndPlayer() {
        game = new Game();
        player = new Player();
        player.symbol = 'X';
    }
    @Test
    public void initialGameStateShouldBeValid() {
        assertEquals(State.PLAYING, game.state);
        assertNotNull(game.board);
        assertEquals(9, game.board.length);
        for (char c : game.board) {
            assertEquals(' ', c);
        }
    }

    @Test
    public void defaultPlayerStateShouldBeCorrect() {
        Player p = new Player();
        assertEquals('\u0000', p.symbol);
        assertEquals(0, p.move);
        assertFalse(p.selected);
        assertFalse(p.win);
    }

    @Test
    public void playingStateShouldBeDetectedCorrectly() {
        char[] b = {'X',' ',' ',' ',' ',' ',' ',' ',' '};
        game.symbol = 'X';
        assertEquals(State.PLAYING, game.checkState(b));
    }

    @Test
    public void drawShouldBeDetectedCorrectly() {
        char[] b = {'X','O','X','X','O','O','O','X','X'};
        game.symbol = 'X';
        assertEquals(State.DRAW, game.checkState(b));
        game.symbol = 'O';
        assertEquals(State.DRAW, game.checkState(b));
    }

    @Test
    public void xWinShouldBeDetectedInRow() {
        char[] b = {'X','X','X',' ',' ',' ',' ',' ',' '};
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(b));
    }

    @Test
    public void oWinShouldBeDetectedInColumn() {
        char[] b = {'O',' ',' ','O',' ',' ','O',' ',' '};
        game.symbol = 'O';
        assertEquals(State.OWIN, game.checkState(b));
    }

    @Test
    public void xWinShouldBeDetectedInDiagonal() {
        char[] b = {'X',' ',' ',' ','X',' ',' ',' ','X'};
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(b));
    }

    @Test
    public void oWinShouldBeDetectedInDiagonal() {
        char[] b = {' ',' ','O',' ','O',' ','O',' ',' '};
        game.symbol = 'O';
        assertEquals(State.OWIN, game.checkState(b));
    }

    @Test
    public void generateMovesShouldReturnAllEmptyCells() {
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertEquals(9, moves.size());
        for (int i = 0; i < 9; i++) assertTrue(moves.contains(i));
    }

    @Test
    public void generateMovesShouldReturnCorrectList() {
        char[] b = {'X','O',' ',' ','X','O','X',' ',' '};
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(b, moves);
        assertEquals(4, moves.size());
        assertTrue(moves.contains(2));
        assertTrue(moves.contains(3));
        assertTrue(moves.contains(7));
        assertTrue(moves.contains(8));
    }

    @Test
    public void evaluationShouldReturnZeroForDraw() {
        char[] b = {'X','O','X','X','O','O','O','X','X'};
        player.symbol = 'X';
        assertEquals(0, game.evaluatePosition(b, player));
    }

    @Test
    public void evaluationShouldReturnPositiveInfinityForXWin() {
        char[] b = {'X','X','X',' ',' ',' ',' ',' ',' '};
        game.symbol = 'X';
        player.symbol = 'X';
        assertEquals(Game.INF, game.evaluatePosition(b, player));
    }

    @Test
    public void evaluationShouldReturnNegativeInfinityForEnemyWin() {
        char[] b = {'X','X','X',' ',' ',' ',' ',' ',' '};
        game.symbol = 'X';
        player.symbol = 'O';
        assertEquals(-Game.INF, game.evaluatePosition(b, player));
    }

    @Test
    public void evaluationShouldReturnInfinityForOWin() {
        char[] b = {'O','O','O',' ',' ',' ',' ',' ',' '};
        game.symbol = 'O';
        player.symbol = 'O';
        assertEquals(Game.INF, game.evaluatePosition(b, player));
    }

    @Test
    public void evaluationShouldReturnMinusInfinityForOpponentWin() {
        char[] b = {'O','O','O',' ',' ',' ',' ',' ',' '};
        game.symbol = 'O';
        player.symbol = 'X';
        assertEquals(-Game.INF, game.evaluatePosition(b, player));
    }

    @Test
    public void minMoveShouldReturnFiniteValue() {
        char[] b = {'X','O',' ',' ',' ',' ',' ',' ',' '};
        player.symbol = 'X';
        int v = game.MinMove(b, player);
        assertTrue(v >= -Game.INF && v <= Game.INF);
    }

    @Test
    public void maxMoveShouldReturnFiniteValue() {
        char[] b = {'X','O',' ',' ',' ',' ',' ',' ',' '};
        player.symbol = 'X';
        int v = game.MaxMove(b, player);
        assertTrue(v >= -Game.INF && v <= Game.INF);
    }

    @Test
    public void miniMaxShouldReturnValidMoveIndex() {
        player.symbol = 'X';
        int mv = game.MiniMax(game.board, player);
        assertTrue(mv >= 1 && mv <= 9);
    }

    @Test
    public void boardShouldPrintCorrectly() {
        char[] b = {'X','O',' ',' ',' ',' ',' ',' ',' '};
        Utility.print(b);
    }

    @Test
    public void arrayShouldPrintCorrectly() {
        int[] a = {6, 2, 4, 1, 8, 6, 7, 5, 9};
        Utility.print(a);
    }

    @Test
    public void listShouldPrintCorrectly() {
        ArrayList<Integer> L = new ArrayList<>();
        L.add(7);
        L.add(6);
        Utility.print(L);
    }

    @Test
    public void cellShouldStoreAndReturnCorrectValues() {
        TicTacToeCell c = new TicTacToeCell(8, 2, 1);
        assertEquals(8, c.getNum());
        assertEquals(2, c.getCol());
        assertEquals(1, c.getRow());
        assertEquals(' ', c.getMarker());
    }

    @Test
    public void cellShouldSetMarkerAndDisable() {
        TicTacToeCell c = new TicTacToeCell(0, 0, 0);
        c.setMarker("O");
        assertEquals('O', c.getMarker());
        assertFalse(c.isEnabled());
    }

    @Test
    public void panelShouldContainNineCells() {
        TicTacToePanel panel = new TicTacToePanel(new GridLayout(3,3));
        assertEquals(9, panel.getComponentCount());
        for (Component comp : panel.getComponents()) {
            assertInstanceOf(TicTacToeCell.class, comp);
        }
    }

    @Test
    public void cellClickShouldSetMarker() {
        System.setProperty("java.awt.headless", "true");
        TicTacToePanel panel = new TicTacToePanel(new GridLayout(3,3));
        TicTacToeCell cell0 = (TicTacToeCell) panel.getComponent(0);
        cell0.doClick();
        char m = cell0.getMarker();
        assertTrue(m=='X' || m=='O');
    }
}