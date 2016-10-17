package test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import solution.StrategicAction;
import solution.Strategy;
import solution.MyConsultant;

import task.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class YourConsultantTest {

    private GameBoard board;

    @InjectMocks
    private MyConsultant consultant = new MyConsultant();

    @Mock
    private Strategy player;

    @Mock
    private ArrayList<StrategicAction> myActions;

    @Before
    public void SetUp() {
        board = BoardSimulator.board("one_winning_move01");
        myActions = player.setActions();
    }

    @Test(expected = NullPointerException.class)
    public void testSuggestNullInput() {
        consultant.suggest(null);
    }

    @Test(expected = IllegalStateException.class)
    public void testSuggestIllegalState() {
        when(player.isGameFinished()).thenReturn(true);
        consultant.suggest(board);
    }

    @Test
    public void testSuggestWinTrue() {
//        when(player.isGameFinished()).thenReturn(false);
        when(myActions.get(0).action()).thenReturn(CellLocation.BOTTOM_RIGHT);
//        when(player.win()).thenReturn(CellLocation.BOTTOM_RIGHT);
        assertEquals(consultant.suggest(board), CellLocation.BOTTOM_RIGHT);
    }

    @Test(expected = IllegalStateException.class)
    public void testSuggestWinFalse() {
        when(player.isGameFinished()).thenReturn(false);
        when(player.win()).thenReturn(null);
        consultant.suggest(board);
    }

    @Test
    public void testSuggestBlockTrue() {
        when(player.isGameFinished()).thenReturn(false);
        when(player.win()).thenReturn(null);
        when(player.block()).thenReturn(CellLocation.BOTTOM_RIGHT);
        assertEquals(consultant.suggest(board), CellLocation.BOTTOM_RIGHT);
    }

    @Test(expected = IllegalStateException.class)
    public void testSuggestBlockFalse() {
        when(player.isGameFinished()).thenReturn(false);
        when(player.win()).thenReturn(null);
        when(player.block()).thenReturn(null);
        consultant.suggest(board);
    }

    @Test
    public void testSuggestForkTrue() {
        when(player.isGameFinished()).thenReturn(false);
        when(player.win()).thenReturn(null);
        when(player.block()).thenReturn(null);
        when(player.fork()).thenReturn(CellLocation.BOTTOM_RIGHT);
        assertEquals(consultant.suggest(board), CellLocation.BOTTOM_RIGHT);
    }

    @Test(expected = IllegalStateException.class)
    public void testSuggestForkFalse() {
        when(player.isGameFinished()).thenReturn(false);
        when(player.win()).thenReturn(null);
        when(player.block()).thenReturn(null);
        when(player.fork()).thenReturn(null);
        consultant.suggest(board);
    }

    @Test
    public void testSuggestBlockForkTrue() {
        when(player.isGameFinished()).thenReturn(false);
        when(player.win()).thenReturn(null);
        when(player.block()).thenReturn(null);
        when(player.fork()).thenReturn(null);
        when(player.blockFork()).thenReturn(CellLocation.BOTTOM_RIGHT);
        assertEquals(consultant.suggest(board), CellLocation.BOTTOM_RIGHT);
    }

    @Test(expected = IllegalStateException.class)
    public void testSuggestBlockForkFalse() {
        when(player.isGameFinished()).thenReturn(false);
        when(player.win()).thenReturn(null);
        when(player.block()).thenReturn(null);
        when(player.fork()).thenReturn(null);
        when(player.blockFork()).thenReturn(null);
        consultant.suggest(board);
    }

    @Test
    public void testSuggestMarkCentreTrue() {
        when(player.isGameFinished()).thenReturn(false);
        when(player.win()).thenReturn(null);
        when(player.block()).thenReturn(null);
        when(player.fork()).thenReturn(null);
        when(player.blockFork()).thenReturn(null);
        when(player.markCentre()).thenReturn(CellLocation.BOTTOM_RIGHT);
        assertEquals(consultant.suggest(board), CellLocation.BOTTOM_RIGHT);
    }

    @Test(expected = IllegalStateException.class)
    public void testSuggestMarkCentreFalse() {
        when(player.isGameFinished()).thenReturn(false);
        when(player.win()).thenReturn(null);
        when(player.block()).thenReturn(null);
        when(player.fork()).thenReturn(null);
        when(player.blockFork()).thenReturn(null);
        when(player.markCentre()).thenReturn(null);
        consultant.suggest(board);
    }

    @Test
    public void testSuggestMarkOppositeCornerTrue() {
        when(player.isGameFinished()).thenReturn(false);
        when(player.win()).thenReturn(null);
        when(player.block()).thenReturn(null);
        when(player.fork()).thenReturn(null);
        when(player.blockFork()).thenReturn(null);
        when(player.markCentre()).thenReturn(null);
        when(player.markOppositeCorner()).thenReturn(CellLocation.BOTTOM_RIGHT);
        assertEquals(consultant.suggest(board), CellLocation.BOTTOM_RIGHT);
    }

    @Test(expected = IllegalStateException.class)
    public void testSuggestMarkOppositeCornerFalse() {
        when(player.isGameFinished()).thenReturn(false);
        when(player.win()).thenReturn(null);
        when(player.block()).thenReturn(null);
        when(player.fork()).thenReturn(null);
        when(player.blockFork()).thenReturn(null);
        when(player.markCentre()).thenReturn(null);
        when(player.markOppositeCorner()).thenReturn(null);
        consultant.suggest(board);
    }

    @Test
    public void testSuggestMarkEmptyCornerTrue() {
        when(player.isGameFinished()).thenReturn(false);
        when(player.win()).thenReturn(null);
        when(player.block()).thenReturn(null);
        when(player.fork()).thenReturn(null);
        when(player.blockFork()).thenReturn(null);
        when(player.markCentre()).thenReturn(null);
        when(player.markOppositeCorner()).thenReturn(null);
        when(player.markEmptyCorner()).thenReturn(CellLocation.BOTTOM_RIGHT);
        assertEquals(consultant.suggest(board), CellLocation.BOTTOM_RIGHT);
    }

    @Test(expected = IllegalStateException.class)
    public void testSuggestMarkEmptyCornerFalse() {
        when(player.isGameFinished()).thenReturn(false);
        when(player.win()).thenReturn(null);
        when(player.block()).thenReturn(null);
        when(player.fork()).thenReturn(null);
        when(player.blockFork()).thenReturn(null);
        when(player.markCentre()).thenReturn(null);
        when(player.markOppositeCorner()).thenReturn(null);
        when(player.markEmptyCorner()).thenReturn(null);
        consultant.suggest(board);
    }

    @Test
    public void testSuggestMarkEmptySideTrue() {
        when(player.isGameFinished()).thenReturn(false);
        when(player.win()).thenReturn(null);
        when(player.block()).thenReturn(null);
        when(player.fork()).thenReturn(null);
        when(player.blockFork()).thenReturn(null);
        when(player.markCentre()).thenReturn(null);
        when(player.markOppositeCorner()).thenReturn(null);
        when(player.markEmptyCorner()).thenReturn(null);
        when(player.markEmptySide()).thenReturn(CellLocation.BOTTOM_RIGHT);
        assertEquals(consultant.suggest(board), CellLocation.BOTTOM_RIGHT);
    }

    @Test(expected = IllegalStateException.class)
    public void testSuggestMarkEmptySideFalse() {
        when(player.isGameFinished()).thenReturn(false);
        when(player.win()).thenReturn(null);
        when(player.block()).thenReturn(null);
        when(player.fork()).thenReturn(null);
        when(player.blockFork()).thenReturn(null);
        when(player.markCentre()).thenReturn(null);
        when(player.markOppositeCorner()).thenReturn(null);
        when(player.markEmptyCorner()).thenReturn(null);
        when(player.markEmptySide()).thenReturn(null);
        consultant.suggest(board);
    }
}
