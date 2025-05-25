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
    public void setUp() {
        game = new Game();
        player = new Player();
        player.symbol = 'X';
    }
    @Test
    public void test1() {
        assertEquals(State.PLAYING, game.state);
        assertNotNull(game.board);
        assertEquals(9, game.board.length);
        for (char c : game.board) {
            assertEquals(' ', c);
        }
    }

    @Test
    public void test2() {
        Player p = new Player();
        assertEquals('\u0000', p.symbol);
        assertEquals(0, p.move);
        assertFalse(p.selected);
        assertFalse(p.win);
    }

    @Test
    public void test3() {
        char[] b = {'X',' ',' ',' ',' ',' ',' ',' ',' '};
        game.symbol = 'X';
        assertEquals(State.PLAYING, game.checkState(b));
    }

    @Test
    public void test4() {
        char[] b = {'X','O','X','X','O','O','O','X','X'};
        game.symbol = 'X';
        assertEquals(State.DRAW, game.checkState(b));
        game.symbol = 'O';
        assertEquals(State.DRAW, game.checkState(b));
    }

    @Test
    public void test5() {
        char[] b = {'X','X','X',' ',' ',' ',' ',' ',' '};
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(b));
    }

    @Test
    public void test6() {
        char[] b = {'O',' ',' ','O',' ',' ','O',' ',' '};
        game.symbol = 'O';
        assertEquals(State.OWIN, game.checkState(b));
    }

    @Test
    public void test7() {
        char[] b = {'X',' ',' ',' ','X',' ',' ',' ','X'};
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(b));
    }

    @Test
    public void test8() {
        char[] b = {' ',' ','O',' ','O',' ','O',' ',' '};
        game.symbol = 'O';
        assertEquals(State.OWIN, game.checkState(b));
    }

    @Test
    public void test9() {
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertEquals(9, moves.size());
        for (int i = 0; i < 9; i++) assertTrue(moves.contains(i));
    }

    @Test
    public void test10() {
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
    public void test11() {
        char[] b = {'X','O','X','X','O','O','O','X','X'};
        player.symbol = 'X';
        assertEquals(0, game.evaluatePosition(b, player));
    }

    @Test
    public void test12() {
        char[] b = {'X','X','X',' ',' ',' ',' ',' ',' '};
        game.symbol = 'X';
        player.symbol = 'X';
        assertEquals(Game.INF, game.evaluatePosition(b, player));
    }

    @Test
    public void test13() {
        char[] b = {'X','X','X',' ',' ',' ',' ',' ',' '};
        game.symbol = 'X';
        player.symbol = 'O';
        assertEquals(-Game.INF, game.evaluatePosition(b, player));
    }

    @Test
    public void test14() {
        char[] b = {'O','O','O',' ',' ',' ',' ',' ',' '};
        game.symbol = 'O';
        player.symbol = 'O';
        assertEquals(Game.INF, game.evaluatePosition(b, player));
    }

    @Test
    public void test15() {
        char[] b = {'O','O','O',' ',' ',' ',' ',' ',' '};
        game.symbol = 'O';
        player.symbol = 'X';
        assertEquals(-Game.INF, game.evaluatePosition(b, player));
    }

    @Test
    public void test16() {
        char[] b = {'X','O',' ',' ',' ',' ',' ',' ',' '};
        player.symbol = 'X';
        int v = game.MinMove(b, player);
        assertTrue(v >= -Game.INF && v <= Game.INF);
    }

    @Test
    public void test17() {
        char[] b = {'X','O',' ',' ',' ',' ',' ',' ',' '};
        player.symbol = 'X';
        int v = game.MaxMove(b, player);
        assertTrue(v >= -Game.INF && v <= Game.INF);
    }

    @Test
    public void test18() {
        player.symbol = 'X';
        int mv = game.MiniMax(game.board, player);
        assertTrue(mv >= 1 && mv <= 9);
    }

    @Test
    public void test19() {
        char[] b = {'X','O',' ',' ',' ',' ',' ',' ',' '};
        Utility.print(b);
    }

    @Test
    public void test20() {
        int[] a = {6,2,4,1,8,6,7,5,9};
        Utility.print(a);
    }

    @Test
    public void test21() {
        ArrayList<Integer> L = new ArrayList<>();
        L.add(7);
        L.add(6);
        Utility.print(L);
    }

    @Test
    public void test22() {
        TicTacToeCell c = new TicTacToeCell(8, 2, 1);
        assertEquals(8, c.getNum());
        assertEquals(2, c.getCol());
        assertEquals(1, c.getRow());
        assertEquals(' ', c.getMarker());
    }

    @Test
    public void test23() {
        TicTacToeCell c = new TicTacToeCell(0, 0, 0);
        c.setMarker("O");
        assertEquals('O', c.getMarker());
        assertFalse(c.isEnabled());
    }

    @Test
    public void test24() {
        TicTacToePanel panel = new TicTacToePanel(new GridLayout(3,3));
        assertEquals(9, panel.getComponentCount());
        for (Component comp : panel.getComponents()) {
            assertInstanceOf(TicTacToeCell.class, comp);
        }
    }

    @Test
    public void test25() {
        System.setProperty("java.awt.headless", "true");
        TicTacToePanel panel = new TicTacToePanel(new GridLayout(3,3));
        TicTacToeCell cell0 = (TicTacToeCell) panel.getComponent(0);
        cell0.doClick();
        char m = cell0.getMarker();
        assertTrue(m=='X' || m=='O');
    }
}