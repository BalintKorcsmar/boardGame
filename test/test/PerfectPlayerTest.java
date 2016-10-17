package test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import solution.PerfectPlayer;
import solution.TicTacToe;

import task.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PerfectPlayerTest {
    @InjectMocks 
    PerfectPlayer player = new PerfectPlayer();

    @Mock
    TicTacToe game;

    @Before
    public void setUp() {
        player.setGame(BoardSimulator.board("one_winning_move01"));
    }

    @Test(expected = NullPointerException.class)
    public void testSetGameNull() {
        player.setGame(null);
    }

    @Test
    public void testisGameFinishedTrue() {
        boolean result;
        when(game.checkGameFinished()).thenReturn(true);
        result = player.isGameFinished();
        assertTrue(result);
    }

    @Test
    public void testisGameFinishedFalse() {
        when(game.checkGameFinished()).thenReturn(false);
        assertFalse(player.isGameFinished());
    }

    @Test
    public void testWinFalse() {
        when(game.getMyState()).thenReturn(CellState.OCCUPIED_BY_X);
        when(game.canWin(CellState.OCCUPIED_BY_X)).thenReturn(null);
        assertNull(player.win());
    }

    @Test
    public void testWinTrue() {
        when(game.getMyState()).thenReturn(CellState.OCCUPIED_BY_O);
        when(game.canWin(CellState.OCCUPIED_BY_O)).thenReturn(CellLocation.BOTTOM_RIGHT);
        assertEquals(player.win(), CellLocation.BOTTOM_RIGHT);
    }

    @Test
    public void testBlockFalse() {
        when(game.getOpponentState()).thenReturn(CellState.OCCUPIED_BY_X);
        when(game.canWin(CellState.OCCUPIED_BY_X)).thenReturn(null);
        assertNull(player.block());
    }

    @Test
    public void testBlockTrue() {
        when(game.getOpponentState()).thenReturn(CellState.OCCUPIED_BY_O);
        when(game.canWin(CellState.OCCUPIED_BY_O)).thenReturn(CellLocation.BOTTOM_RIGHT);
        assertEquals(player.block(), CellLocation.BOTTOM_RIGHT);
    }

    @Test
    public void testForkFalse() {
        when(game.getMyState()).thenReturn(CellState.OCCUPIED_BY_X);
        when(game.canFork(CellState.OCCUPIED_BY_X)).thenReturn(null);
        assertNull(player.fork());
    }

    @Test
    public void testForkTrue() {
        when(game.getMyState()).thenReturn(CellState.OCCUPIED_BY_O);
        when(game.canFork(CellState.OCCUPIED_BY_O)).thenReturn(CellLocation.BOTTOM_RIGHT);
        assertEquals(player.fork(), CellLocation.BOTTOM_RIGHT);
    }

    @Test
    public void testBlockForkFalse() {
        when(game.getOpponentState()).thenReturn(CellState.OCCUPIED_BY_X);
        when(game.canFork(CellState.OCCUPIED_BY_X)).thenReturn(null);
        assertNull(player.blockFork());
    }

    @Test
    public void testBlockForkTrue() {
        when(game.getOpponentState()).thenReturn(CellState.OCCUPIED_BY_O);
        when(game.canFork(CellState.OCCUPIED_BY_O)).thenReturn(CellLocation.BOTTOM_RIGHT);
        assertEquals(player.blockFork(), CellLocation.BOTTOM_RIGHT);
    }

    @Test
    public void testMarkCentre() {
        when(game.canMarkCentre()).thenReturn(CellLocation.BOTTOM_RIGHT);
        assertEquals(player.markCentre(), CellLocation.BOTTOM_RIGHT);
    }

    @Test
    public void testMarkOppositeCorner() {
        when(game.canMarkOppositeCorner()).thenReturn(CellLocation.BOTTOM_RIGHT);
        assertEquals(player.markOppositeCorner(), CellLocation.BOTTOM_RIGHT);
    }

    @Test
    public void testMarkEmptyCorner() {
        when(game.canMarkEmptyCorner()).thenReturn(CellLocation.BOTTOM_RIGHT);
        assertEquals(player.markEmptyCorner(), CellLocation.BOTTOM_RIGHT);
    }

    @Test
    public void testMarkEmptySide() {
        when(game.canMarkEmptySide()).thenReturn(CellLocation.BOTTOM_RIGHT);
        assertEquals(player.markEmptySide(), CellLocation.BOTTOM_RIGHT);
    }
}
