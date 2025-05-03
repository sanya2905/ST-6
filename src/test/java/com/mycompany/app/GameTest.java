package com.mycompany.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class GameTest {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testGameInitialization() {
        assertNotNull(game.player1);
        assertNotNull(game.player2);
        assertEquals('X', game.player1.symbol);
        assertEquals('O', game.player2.symbol);
        assertEquals(State.PLAYING, game.state);
        assertNotNull(game.board);
        assertEquals(9, game.board.length);
        for (char c : game.board) {
            assertEquals(' ', c);
        }
    }

    @Test
    public void testCheckStateHorizontalWins() {
        char[][] xWins = {
                { 'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' ' },
                { ' ', ' ', ' ', 'X', 'X', 'X', ' ', ' ', ' ' },
                { ' ', ' ', ' ', ' ', ' ', ' ', 'X', 'X', 'X' }
        };

        for (char[] board : xWins) {
            game.symbol = 'X';
            assertEquals(State.XWIN, game.checkState(board));
        }

        for (char[] board : xWins) {
            for (int i = 0; i < board.length; i++) {
                if (board[i] == 'X')
                    board[i] = 'O';
            }
            game.symbol = 'O';
            assertEquals(State.OWIN, game.checkState(board));
        }
    }

    @Test
    public void testCheckStateVerticalWins() {
        char[][] wins = {
                { 'X', ' ', ' ', 'X', ' ', ' ', 'X', ' ', ' ' },
                { ' ', 'X', ' ', ' ', 'X', ' ', ' ', 'X', ' ' },
                { ' ', ' ', 'X', ' ', ' ', 'X', ' ', ' ', 'X' }
        };

        for (char[] board : wins) {
            game.symbol = 'X';
            assertEquals(State.XWIN, game.checkState(board));
        }
    }

    @Test
    public void testCheckStateDiagonalWins() {
        char[][] wins = {
                { 'X', ' ', ' ', ' ', 'X', ' ', ' ', ' ', 'X' },
                { ' ', ' ', 'X', ' ', 'X', ' ', 'X', ' ', ' ' }
        };

        for (char[] board : wins) {
            game.symbol = 'X';
            assertEquals(State.XWIN, game.checkState(board));
        }
    }

    @Test
    public void testCheckStateDraw() {
        char[] drawBoard = {
                'X', 'O', 'X',
                'X', 'O', 'O',
                'O', 'X', 'X'
        };
        game.symbol = 'X';
        assertEquals(State.DRAW, game.checkState(drawBoard));
    }

    @Test
    public void testCheckStatePlaying() {
        char[] playingBoard = {
                'X', 'O', 'X',
                'X', ' ', 'O',
                'O', 'X', ' '
        };
        game.symbol = 'X';
        assertEquals(State.PLAYING, game.checkState(playingBoard));
    }

    @Test
    public void testGenerateMovesEmptyBoard() {
        char[] emptyBoard = new char[9];
        java.util.Arrays.fill(emptyBoard, ' ');

        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(emptyBoard, moves);

        assertEquals(9, moves.size());
        for (int i = 0; i < 9; i++) {
            assertTrue(moves.contains(i));
        }
    }

    @Test
    public void testGenerateMovesWithInitializedBoard() {
        char[] board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }

        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);
        assertEquals(9, moves.size());
    }

    @Test
    public void testGenerateMovesPartialBoard() {
        char[] board = {
                'X', ' ', 'O',
                ' ', 'X', ' ',
                'O', ' ', ' '
        };
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);

        assertEquals(5, moves.size());
        assertTrue(moves.contains(1));
        assertTrue(moves.contains(3));
        assertTrue(moves.contains(5));
        assertTrue(moves.contains(7));
        assertTrue(moves.contains(8));
    }

    @Test
    public void testGenerateMovesFullBoard() {
        char[] board = {
                'X', 'O', 'X',
                'X', 'O', 'O',
                'O', 'X', 'X'
        };
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);
        assertTrue(moves.isEmpty());
    }

    @Test
    public void testEvaluatePositionXWins() {
        char[] board = { 'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' ' };
        game.symbol = 'X';
        assertEquals(Game.INF, game.evaluatePosition(board, game.player1));
        assertEquals(-Game.INF, game.evaluatePosition(board, game.player2));
    }

    @Test
    public void testEvaluatePositionOWins() {
        char[] board = { 'O', 'O', 'O', ' ', ' ', ' ', ' ', ' ', ' ' };
        game.symbol = 'O';
        assertEquals(Game.INF, game.evaluatePosition(board, game.player2));
        assertEquals(-Game.INF, game.evaluatePosition(board, game.player1));
    }

    @Test
    public void testEvaluatePositionDraw() {
        char[] board = {
                'X', 'O', 'X',
                'X', 'O', 'O',
                'O', 'X', 'X'
        };
        assertEquals(0, game.evaluatePosition(board, game.player1));
        assertEquals(0, game.evaluatePosition(board, game.player2));
    }

    @Test
    public void testEvaluatePositionPlaying() {
        char[] board = { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
        assertEquals(-1, game.evaluatePosition(board, game.player1));
    }

    @Test
    public void testMiniMaxImmediateWin() {
        char[] board = {
                'X', 'X', ' ',
                'O', 'O', ' ',
                ' ', ' ', ' '
        };
        game.symbol = 'X';
        int move = game.MiniMax(board, game.player1);
        assertEquals(3, move);
    }

    @Test
    public void testMiniMaxEmptyBoard() {
        char[] board = new char[9];
        java.util.Arrays.fill(board, ' ');
        game.symbol = 'X';
        int move = game.MiniMax(board, game.player1);
        assertTrue(move >= 1 && move <= 9);
    }

    @Test
    public void testMiniMaxWithClearBoard() {
        char[] board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }

        game.symbol = 'X';
        int move = game.MiniMax(board, game.player1);
        assertTrue(move >= 1 && move <= 9);
    }

    @Test
    public void testMinMoveTerminalState() {
        char[] board = { 'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' ' };
        game.symbol = 'X';
        assertEquals(Game.INF, game.MinMove(board, game.player1));
    }

    @Test
    public void testMaxMoveTerminalState() {
        char[] board = { 'O', 'O', 'O', ' ', ' ', ' ', ' ', ' ', ' ' };
        game.symbol = 'O';
        assertEquals(-Game.INF, game.MaxMove(board, game.player1));
    }

    @Test
    public void testCurrentPlayerSwitch() {
        game.cplayer = game.player1;
        game.symbol = game.player1.symbol;
        assertEquals('X', game.symbol);

        game.cplayer = game.player2;
        game.symbol = game.player2.symbol;
        assertEquals('O', game.symbol);
    }

    @Test
    public void testBoardStateAfterMove() {
        char[] board = new char[9];
        java.util.Arrays.fill(board, ' ');
        board[0] = 'X';
        assertEquals('X', board[0]);
        assertEquals(' ', board[1]);
    }

    @Test
    public void testCheckStateDrawWithDifferentCombination() {
        char[] drawBoard = {
                'O', 'X', 'O',
                'O', 'X', 'X',
                'X', 'O', 'O'
        };
        game.symbol = 'X';
        assertEquals(State.DRAW, game.checkState(drawBoard));
    }
}