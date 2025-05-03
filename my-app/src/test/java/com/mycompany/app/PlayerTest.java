package com.mycompany.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player();
    }

    @Test
    public void testDefaultValues() {
        assertEquals(0, player.symbol);
        assertEquals(0, player.move);
        assertFalse(player.selected);
        assertFalse(player.win);
    }

    @Test
    public void testSymbolAssignment() {
        player.symbol = 'X';
        assertEquals('X', player.symbol);

        player.symbol = 'O';
        assertEquals('O', player.symbol);
    }

    @Test
    public void testMoveAssignment() {
        player.move = 5;
        assertEquals(5, player.move);

        player.move = -1;
        assertEquals(-1, player.move);
    }

    @Test
    public void testSelectedFlag() {
        player.selected = true;
        assertTrue(player.selected);

        player.selected = false;
        assertFalse(player.selected);
    }

    @Test
    public void testWinFlag() {
        player.win = true;
        assertTrue(player.win);

        player.win = false;
        assertFalse(player.win);
    }

    @Test
    public void testCombinedState() {
        player.symbol = 'X';
        player.move = 3;
        player.selected = true;
        player.win = true;

        assertEquals('X', player.symbol);
        assertEquals(3, player.move);
        assertTrue(player.selected);
        assertTrue(player.win);
    }

    @Test
    public void testPlayerInGameContext() {
        Game game = new Game();

        assertEquals('X', game.player1.symbol);
        assertEquals('O', game.player2.symbol);
        assertFalse(game.player1.win);
        assertFalse(game.player2.win);
    }
}