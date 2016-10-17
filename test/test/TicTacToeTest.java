package test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import solution.Board;
import solution.TicTacToe;

import task.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class TicTacToeTest {
       @InjectMocks 
       TicTacToe game = new TicTacToe();
       @Mock
       Board board;

       @Before
       public void setUp() {
           game.setBoard(BoardSimulator.board("winning_vs_not_losing"));
       }

       /*
        * Test case for setBoard().
        */
       @Test(expected = NullPointerException.class)
       public void testSetBoardNull() {
           game.setBoard(null);
       }

       /*
        * Test cases for checkGameFinished().
        */
       @Test
       public void testCheckGameFinishedTrueByFullBoard() {
           Map<CellState, Integer> testMap = new HashMap<CellState, Integer>();
           testMap.put(CellState.EMPTY, 0);
           testMap.put(CellState.OCCUPIED_BY_X, 5);
           testMap.put(CellState.OCCUPIED_BY_O, 4);

           when(board.getBoardState()).thenReturn(testMap);
           assertTrue(game.checkGameFinished());
       }

       @Test
       public void testCheckGameFinishedTrueByXRow() {
           Map<CellState, Integer> testMap = new HashMap<CellState, Integer>();
           testMap.put(CellState.EMPTY, 3);
           testMap.put(CellState.OCCUPIED_BY_X, 3);
           testMap.put(CellState.OCCUPIED_BY_O, 3);

           when(board.getBoardState()).thenReturn(testMap);
           when(board.checkAllRows(CellState.OCCUPIED_BY_X, TicTacToe.GAME_FINISHED_CONDITION))
           .thenReturn(CellLocation.BOTTOM_LEFT);
           assertTrue(game.checkGameFinished());
       }

       @Test
       public void testCheckGameFinishedTrueByXColumn() {
           Map<CellState, Integer> testMap = new HashMap<CellState, Integer>();
           testMap.put(CellState.EMPTY, 3);
           testMap.put(CellState.OCCUPIED_BY_X, 3);
           testMap.put(CellState.OCCUPIED_BY_O, 3);

           when(board.getBoardState()).thenReturn(testMap);
           when(board.checkAllRows(CellState.OCCUPIED_BY_X, TicTacToe.GAME_FINISHED_CONDITION))
           .thenReturn(null);
           when(board.checkAllColumns(CellState.OCCUPIED_BY_X, TicTacToe.GAME_FINISHED_CONDITION))
           .thenReturn(CellLocation.TOP_LEFT);
           assertTrue(game.checkGameFinished());
       }

       @Test
       public void testCheckGameFinishedTrueByXDiagonal() {
           Map<CellState, Integer> testMap = new HashMap<CellState, Integer>();
           testMap.put(CellState.EMPTY, 3);
           testMap.put(CellState.OCCUPIED_BY_X, 3);
           testMap.put(CellState.OCCUPIED_BY_O, 3);

           when(board.getBoardState()).thenReturn(testMap);
           when(board.checkAllRows(CellState.OCCUPIED_BY_X, TicTacToe.GAME_FINISHED_CONDITION))
           .thenReturn(null);
           when(board.checkAllColumns(CellState.OCCUPIED_BY_X, TicTacToe.GAME_FINISHED_CONDITION))
           .thenReturn(null);
           when(board.checkDiagonals(CellState.OCCUPIED_BY_X, TicTacToe.GAME_FINISHED_CONDITION))
           .thenReturn(CellLocation.TOP_LEFT);
           assertTrue(game.checkGameFinished());
       }

       @Test
       public void testCheckGameFinishedTrueByXRowOnOTurn() {
           Map<CellState, Integer> testMap = new HashMap<CellState, Integer>();
           testMap.put(CellState.EMPTY, 2);
           testMap.put(CellState.OCCUPIED_BY_X, 4);
           testMap.put(CellState.OCCUPIED_BY_O, 3);

           when(board.getBoardState()).thenReturn(testMap);
           when(board.checkAllRows(CellState.OCCUPIED_BY_O, TicTacToe.GAME_FINISHED_CONDITION))
           .thenReturn(null);
           when(board.checkAllColumns(CellState.OCCUPIED_BY_O, TicTacToe.GAME_FINISHED_CONDITION))
           .thenReturn(null);
           when(board.checkDiagonals(CellState.OCCUPIED_BY_O, TicTacToe.GAME_FINISHED_CONDITION))
           .thenReturn(null);
           when(board.checkAllRows(CellState.OCCUPIED_BY_X, TicTacToe.GAME_FINISHED_CONDITION))
           .thenReturn(CellLocation.TOP_LEFT);
           assertTrue(game.checkGameFinished());
       }

       @Test
       public void testCheckGameFinishedTrueByORowOnXTurn() {
           Map<CellState, Integer> testMap = new HashMap<CellState, Integer>();
           testMap.put(CellState.EMPTY, 3);
           testMap.put(CellState.OCCUPIED_BY_X, 3);
           testMap.put(CellState.OCCUPIED_BY_O, 3);

           when(board.getBoardState()).thenReturn(testMap);
           when(board.checkAllRows(CellState.OCCUPIED_BY_X, TicTacToe.GAME_FINISHED_CONDITION))
           .thenReturn(null);
           when(board.checkAllColumns(CellState.OCCUPIED_BY_X, TicTacToe.GAME_FINISHED_CONDITION))
           .thenReturn(null);
           when(board.checkDiagonals(CellState.OCCUPIED_BY_X, TicTacToe.GAME_FINISHED_CONDITION))
           .thenReturn(null);
           when(board.checkAllRows(CellState.OCCUPIED_BY_O, TicTacToe.GAME_FINISHED_CONDITION))
           .thenReturn(CellLocation.TOP_LEFT);
           assertTrue(game.checkGameFinished());
       }

       @Test
       public void testCheckGameFinishedFalse() {
           Map<CellState, Integer> testMap = new HashMap<CellState, Integer>();
           testMap.put(CellState.EMPTY, 2);
           testMap.put(CellState.OCCUPIED_BY_X, 4);
           testMap.put(CellState.OCCUPIED_BY_O, 3);

           when(board.getBoardState()).thenReturn(testMap);
           when(board.checkAllRows(CellState.OCCUPIED_BY_X, TicTacToe.GAME_FINISHED_CONDITION))
           .thenReturn(null);
           when(board.checkAllColumns(CellState.OCCUPIED_BY_X, TicTacToe.GAME_FINISHED_CONDITION))
           .thenReturn(null);
           when(board.checkDiagonals(CellState.OCCUPIED_BY_X, TicTacToe.GAME_FINISHED_CONDITION))
           .thenReturn(null);
           assertFalse(game.checkGameFinished());
       }

       @Test
       public void testCanWinXCanWinByRow() {
           when(board.checkAllRows(CellState.OCCUPIED_BY_X, TicTacToe.CAN_WIN_CONDITION))
                   .thenReturn(CellLocation.BOTTOM_RIGHT);
           assertEquals(game.canWin(CellState.OCCUPIED_BY_X), CellLocation.BOTTOM_RIGHT);
       }

       /*
        * Test cases for canWin().
        */
       @Test
       public void testCanWinXCanWinByColumn() {
           when(board.checkAllRows(CellState.OCCUPIED_BY_X, TicTacToe.CAN_WIN_CONDITION))
                   .thenReturn(null);
           when(board.checkAllColumns(CellState.OCCUPIED_BY_X, TicTacToe.CAN_WIN_CONDITION))
               .thenReturn(CellLocation.BOTTOM_RIGHT);
           assertEquals(game.canWin(CellState.OCCUPIED_BY_X), CellLocation.BOTTOM_RIGHT);
       }

       @Test
       public void testCanWinXCanWinByDiagonal() {
           when(board.checkAllRows(CellState.OCCUPIED_BY_X, TicTacToe.CAN_WIN_CONDITION))
               .thenReturn(null);
           when(board.checkAllColumns(CellState.OCCUPIED_BY_X, TicTacToe.CAN_WIN_CONDITION))
              .thenReturn(null);
           when(board.checkDiagonals(CellState.OCCUPIED_BY_X, TicTacToe.CAN_WIN_CONDITION))
              .thenReturn(CellLocation.BOTTOM_RIGHT);
           assertEquals(game.canWin(CellState.OCCUPIED_BY_X), CellLocation.BOTTOM_RIGHT);
       }

       @Test
       public void testCanWinXCannotWin() {
           when(board.checkAllRows(CellState.OCCUPIED_BY_X, TicTacToe.CAN_WIN_CONDITION))
               .thenReturn(null);
           when(board.checkAllColumns(CellState.OCCUPIED_BY_X, TicTacToe.CAN_WIN_CONDITION))
              .thenReturn(null);
           when(board.checkDiagonals(CellState.OCCUPIED_BY_X, TicTacToe.CAN_WIN_CONDITION))
              .thenReturn(null);
           assertNull(game.canWin(CellState.OCCUPIED_BY_X));
       }

       /*
        * Test cases for canFork().
        */
       @Test
       public void testCanForkFalse() {
           when(board.getCellState(CellLocation.TOP_LEFT))
               .thenReturn(CellState.OCCUPIED_BY_X);
           when(board.getCellState(CellLocation.TOP_CENTRE))
               .thenReturn(CellState.EMPTY);
           when(board.checkRow(CellState.OCCUPIED_BY_X, CellLocation.TOP_CENTRE, TicTacToe.CAN_FORK_CONDITION))
               .thenReturn(null);
           when(board.checkColumn(CellState.OCCUPIED_BY_X, CellLocation.TOP_CENTRE, TicTacToe.CAN_FORK_CONDITION))
               .thenReturn(null);
           when(board.checkMainDiagonal(CellState.OCCUPIED_BY_X, CellLocation.TOP_CENTRE, TicTacToe.CAN_FORK_CONDITION))
               .thenReturn(null);
           when(board.checkAntiDiagonal(CellState.OCCUPIED_BY_X, CellLocation.TOP_CENTRE, TicTacToe.CAN_FORK_CONDITION))
               .thenReturn(null);
           assertNull(game.canFork(CellState.OCCUPIED_BY_X));
       }

       @Test
       public void testCanForkTrueByRowCol() {
           when(board.getCellState(CellLocation.TOP_LEFT))
               .thenReturn(CellState.OCCUPIED_BY_X);
           when(board.getCellState(CellLocation.TOP_CENTRE))
           .thenReturn(CellState.EMPTY);
           when(board.checkRow(CellState.OCCUPIED_BY_X, CellLocation.TOP_CENTRE, TicTacToe.CAN_FORK_CONDITION))
               .thenReturn(CellLocation.TOP_CENTRE);
           when(board.checkColumn(CellState.OCCUPIED_BY_X, CellLocation.TOP_CENTRE, TicTacToe.CAN_FORK_CONDITION))
               .thenReturn(CellLocation.TOP_CENTRE);
           assertEquals(game.canFork(CellState.OCCUPIED_BY_X), CellLocation.TOP_CENTRE);
       }

       @Test
       public void testCanForkTrueByRowMainDiag() {
           when(board.getCellState(CellLocation.TOP_LEFT))
               .thenReturn(CellState.EMPTY);
           when(board.checkRow(CellState.OCCUPIED_BY_X, CellLocation.TOP_LEFT, TicTacToe.CAN_FORK_CONDITION))
               .thenReturn(CellLocation.TOP_LEFT);
           when(board.checkColumn(CellState.OCCUPIED_BY_X, CellLocation.TOP_LEFT, TicTacToe.CAN_FORK_CONDITION))
               .thenReturn(null);
           when(board.checkMainDiagonal(CellState.OCCUPIED_BY_X, CellLocation.TOP_LEFT, TicTacToe.CAN_FORK_CONDITION))
               .thenReturn(CellLocation.TOP_LEFT);
           assertEquals(game.canFork(CellState.OCCUPIED_BY_X), CellLocation.TOP_LEFT);
       }

       @Test
       public void testCanForkTrueByRowAntiDiag() {
           when(board.getCellState(CellLocation.TOP_LEFT))
           .thenReturn(CellState.OCCUPIED_BY_O);
           when(board.getCellState(CellLocation.TOP_CENTRE))
           .thenReturn(CellState.OCCUPIED_BY_X);
           when(board.getCellState(CellLocation.TOP_RIGHT))
               .thenReturn(CellState.EMPTY);
           when(board.checkRow(CellState.OCCUPIED_BY_X, CellLocation.TOP_RIGHT, TicTacToe.CAN_FORK_CONDITION))
               .thenReturn(CellLocation.TOP_RIGHT);
           when(board.checkColumn(CellState.OCCUPIED_BY_X, CellLocation.TOP_RIGHT, TicTacToe.CAN_FORK_CONDITION))
               .thenReturn(null);
           when(board.checkMainDiagonal(CellState.OCCUPIED_BY_X, CellLocation.TOP_RIGHT, TicTacToe.CAN_FORK_CONDITION))
               .thenReturn(null);
           when(board.checkAntiDiagonal(CellState.OCCUPIED_BY_X, CellLocation.TOP_RIGHT, TicTacToe.CAN_FORK_CONDITION))
               .thenReturn(CellLocation.TOP_RIGHT);
           assertEquals(game.canFork(CellState.OCCUPIED_BY_X), CellLocation.TOP_RIGHT);
       }

       @Test
       public void testCanForkTrueByColMainDiag() {
           when(board.getCellState(CellLocation.TOP_LEFT))
               .thenReturn(CellState.EMPTY);
           when(board.checkRow(CellState.OCCUPIED_BY_X, CellLocation.TOP_LEFT, TicTacToe.CAN_FORK_CONDITION))
               .thenReturn(null);
           when(board.checkColumn(CellState.OCCUPIED_BY_X, CellLocation.TOP_LEFT, TicTacToe.CAN_FORK_CONDITION))
               .thenReturn(CellLocation.TOP_LEFT);
           when(board.checkMainDiagonal(CellState.OCCUPIED_BY_X, CellLocation.TOP_LEFT, TicTacToe.CAN_FORK_CONDITION))
               .thenReturn(CellLocation.TOP_LEFT);
           assertEquals(game.canFork(CellState.OCCUPIED_BY_X), CellLocation.TOP_LEFT);
       }

       @Test
       public void testCanForkTrueByColAntiDiag() {
           when(board.getCellState(CellLocation.TOP_LEFT))
               .thenReturn(CellState.OCCUPIED_BY_O);
           when(board.getCellState(CellLocation.TOP_CENTRE))
               .thenReturn(CellState.OCCUPIED_BY_X);
           when(board.getCellState(CellLocation.TOP_RIGHT))
               .thenReturn(CellState.EMPTY);
           when(board.checkRow(CellState.OCCUPIED_BY_X, CellLocation.TOP_RIGHT, TicTacToe.CAN_FORK_CONDITION))
               .thenReturn(null);
           when(board.checkColumn(CellState.OCCUPIED_BY_X, CellLocation.TOP_RIGHT, TicTacToe.CAN_FORK_CONDITION))
               .thenReturn(CellLocation.TOP_RIGHT);
           when(board.checkMainDiagonal(CellState.OCCUPIED_BY_X, CellLocation.TOP_RIGHT, TicTacToe.CAN_FORK_CONDITION))
               .thenReturn(null);
           when(board.checkAntiDiagonal(CellState.OCCUPIED_BY_X, CellLocation.TOP_RIGHT, TicTacToe.CAN_FORK_CONDITION))
               .thenReturn(CellLocation.TOP_RIGHT);
           assertEquals(game.canFork(CellState.OCCUPIED_BY_X), CellLocation.TOP_RIGHT);
       }

       @Test
       public void testCanForkTrueByMainDiagAntiDiag() {
           when(board.getCellState(CellLocation.TOP_LEFT))
               .thenReturn(CellState.OCCUPIED_BY_O);
           when(board.getCellState(CellLocation.TOP_CENTRE))
               .thenReturn(CellState.OCCUPIED_BY_X);
           when(board.getCellState(CellLocation.TOP_RIGHT))
               .thenReturn(CellState.EMPTY);
           when(board.getCellState(CellLocation.CENTRE_LEFT))
               .thenReturn(CellState.OCCUPIED_BY_O);
           when(board.getCellState(CellLocation.CENTRE_CENTRE))
               .thenReturn(CellState.EMPTY);
           when(board.checkRow(CellState.OCCUPIED_BY_X, CellLocation.CENTRE_CENTRE, TicTacToe.CAN_FORK_CONDITION))
               .thenReturn(null);
           when(board.checkColumn(CellState.OCCUPIED_BY_X, CellLocation.CENTRE_CENTRE, TicTacToe.CAN_FORK_CONDITION))
               .thenReturn(null);
           when(board.checkMainDiagonal(CellState.OCCUPIED_BY_X, CellLocation.CENTRE_CENTRE, TicTacToe.CAN_FORK_CONDITION))
               .thenReturn(CellLocation.CENTRE_CENTRE);
           when(board.checkAntiDiagonal(CellState.OCCUPIED_BY_X, CellLocation.CENTRE_CENTRE, TicTacToe.CAN_FORK_CONDITION))
               .thenReturn(CellLocation.CENTRE_CENTRE);
           assertEquals(game.canFork(CellState.OCCUPIED_BY_X), CellLocation.CENTRE_CENTRE);
       }

       /*
        * Test cases for canMarkCentre().
        */
       @Test
       public void testCanMarkCentreFalse() {
           when(board.getCellState(CellLocation.CENTRE_CENTRE))
               .thenReturn(CellState.OCCUPIED_BY_O);
           assertNull(game.canMarkCentre());
       }

       @Test
       public void testCanMarkCentreTrue() {
           when(board.getCellState(CellLocation.CENTRE_CENTRE))
               .thenReturn(CellState.EMPTY);
           assertEquals(game.canMarkCentre(), CellLocation.CENTRE_CENTRE);
       }

       /*
        * Test cases for canMarkOppositeCorner().
        */
       @Test
       public void testCanMarkOppositeCornerTrueTopLeft() {
           Map<CellState, Integer> testMap = new HashMap<CellState, Integer>();
           testMap.put(CellState.EMPTY, 2);
           testMap.put(CellState.OCCUPIED_BY_X, 4);
           testMap.put(CellState.OCCUPIED_BY_O, 3);

           when(board.getBoardState()).thenReturn(testMap);
           when(board.getCellState(CellLocation.TOP_LEFT))
               .thenReturn(CellState.EMPTY);
           when(board.getCellState(CellLocation.BOTTOM_RIGHT))
               .thenReturn(CellState.OCCUPIED_BY_X);
           assertEquals(game.canMarkOppositeCorner(), CellLocation.TOP_LEFT);
       }

       @Test
       public void testCanMarkOppositeCornerTrueTopRight() {
           Map<CellState, Integer> testMap = new HashMap<CellState, Integer>();
           testMap.put(CellState.EMPTY, 2);
           testMap.put(CellState.OCCUPIED_BY_X, 4);
           testMap.put(CellState.OCCUPIED_BY_O, 3);

           when(board.getBoardState()).thenReturn(testMap);
           when(board.getCellState(CellLocation.TOP_LEFT))
               .thenReturn(CellState.OCCUPIED_BY_X);
           when(board.getCellState(CellLocation.TOP_RIGHT))
           .thenReturn(CellState.EMPTY);
           when(board.getCellState(CellLocation.BOTTOM_LEFT))
               .thenReturn(CellState.OCCUPIED_BY_X);
           assertEquals(game.canMarkOppositeCorner(), CellLocation.TOP_RIGHT);
       }

       @Test
       public void testCanMarkOppositeCornerTrueBottomLeft() {
           Map<CellState, Integer> testMap = new HashMap<CellState, Integer>();
           testMap.put(CellState.EMPTY, 2);
           testMap.put(CellState.OCCUPIED_BY_X, 4);
           testMap.put(CellState.OCCUPIED_BY_O, 3);

           when(board.getBoardState()).thenReturn(testMap);
           when(board.getCellState(CellLocation.TOP_LEFT))
               .thenReturn(CellState.OCCUPIED_BY_X);
           when(board.getCellState(CellLocation.TOP_RIGHT))
               .thenReturn(CellState.OCCUPIED_BY_X);
           when(board.getCellState(CellLocation.BOTTOM_LEFT))
               .thenReturn(CellState.EMPTY);
           assertEquals(game.canMarkOppositeCorner(), CellLocation.BOTTOM_LEFT);
       }

       @Test
       public void testCanMarkOppositeCornerTrueBottomRight() {
           Map<CellState, Integer> testMap = new HashMap<CellState, Integer>();
           testMap.put(CellState.EMPTY, 2);
           testMap.put(CellState.OCCUPIED_BY_X, 4);
           testMap.put(CellState.OCCUPIED_BY_O, 3);

           when(board.getBoardState()).thenReturn(testMap);
           when(board.getCellState(CellLocation.TOP_LEFT))
               .thenReturn(CellState.OCCUPIED_BY_X);
           when(board.getCellState(CellLocation.TOP_RIGHT))
               .thenReturn(CellState.OCCUPIED_BY_X);
           when(board.getCellState(CellLocation.BOTTOM_LEFT))
               .thenReturn(CellState.OCCUPIED_BY_X);
           when(board.getCellState(CellLocation.BOTTOM_RIGHT))
               .thenReturn(CellState.EMPTY);
           assertEquals(game.canMarkOppositeCorner(), CellLocation.BOTTOM_RIGHT);
       }

       @Test
       public void testCanMarkOppositeCornerFalse() {
           Map<CellState, Integer> testMap = new HashMap<CellState, Integer>();
           testMap.put(CellState.EMPTY, 2);
           testMap.put(CellState.OCCUPIED_BY_X, 4);
           testMap.put(CellState.OCCUPIED_BY_O, 3);

           when(board.getBoardState()).thenReturn(testMap);
           when(board.getCellState(CellLocation.TOP_LEFT))
               .thenReturn(CellState.OCCUPIED_BY_X);
           when(board.getCellState(CellLocation.TOP_RIGHT))
               .thenReturn(CellState.OCCUPIED_BY_X);
           when(board.getCellState(CellLocation.BOTTOM_LEFT))
               .thenReturn(CellState.OCCUPIED_BY_X);
           when(board.getCellState(CellLocation.BOTTOM_RIGHT))
               .thenReturn(CellState.OCCUPIED_BY_O);
           assertNull(game.canMarkOppositeCorner());
       }

       /*
        * Test cases for canMarkEmptyCorner().
        */
       @Test
       public void testCanMarkEmptyCornerTrueTopLeft() {
           when(board.getCellState(CellLocation.TOP_LEFT))
               .thenReturn(CellState.EMPTY);
           assertEquals(game.canMarkEmptyCorner(), CellLocation.TOP_LEFT);
       }

       @Test
       public void testCanMarkEmptyCornerTrueTopRight() {
           when(board.getCellState(CellLocation.TOP_LEFT))
               .thenReturn(CellState.OCCUPIED_BY_X);
           when(board.getCellState(CellLocation.TOP_RIGHT))
               .thenReturn(CellState.EMPTY);
           assertEquals(game.canMarkEmptyCorner(), CellLocation.TOP_RIGHT);
       }

       @Test
       public void testCanMarkEmptyCornerTrueBottomLeft() {
           when(board.getCellState(CellLocation.TOP_LEFT))
               .thenReturn(CellState.OCCUPIED_BY_O);
           when(board.getCellState(CellLocation.TOP_RIGHT))
               .thenReturn(CellState.OCCUPIED_BY_X);
           when(board.getCellState(CellLocation.BOTTOM_LEFT))
               .thenReturn(CellState.EMPTY);
           assertEquals(game.canMarkEmptyCorner(), CellLocation.BOTTOM_LEFT);
       }

       @Test
       public void testCanMarkEmptyCornerTrueBottomRight() {
           when(board.getCellState(CellLocation.TOP_LEFT))
               .thenReturn(CellState.OCCUPIED_BY_O);
           when(board.getCellState(CellLocation.TOP_RIGHT))
               .thenReturn(CellState.OCCUPIED_BY_X);
           when(board.getCellState(CellLocation.BOTTOM_LEFT))
               .thenReturn(CellState.OCCUPIED_BY_X);
           when(board.getCellState(CellLocation.BOTTOM_RIGHT))
               .thenReturn(CellState.EMPTY);
           assertEquals(game.canMarkEmptyCorner(), CellLocation.BOTTOM_RIGHT);
       }

       @Test
       public void testCanMarkEmptyCornerFalse() {
           when(board.getCellState(CellLocation.TOP_LEFT))
               .thenReturn(CellState.OCCUPIED_BY_O);
           when(board.getCellState(CellLocation.TOP_RIGHT))
               .thenReturn(CellState.OCCUPIED_BY_X);
           when(board.getCellState(CellLocation.BOTTOM_LEFT))
               .thenReturn(CellState.OCCUPIED_BY_X);
           when(board.getCellState(CellLocation.BOTTOM_RIGHT))
               .thenReturn(CellState.OCCUPIED_BY_O);
           assertNull(game.canMarkEmptyCorner());
       }

       /*
        * Test cases for canMarkEmptySide().
        */
       @Test
       public void testMarkCanEmptySideTrueTopCentre() {
           when(board.getCellState(CellLocation.TOP_CENTRE))
               .thenReturn(CellState.EMPTY);
           assertEquals(game.canMarkEmptySide(), CellLocation.TOP_CENTRE);
       }

       @Test
       public void testCanMarkEmptySideTrueCentreLeft() {
           when(board.getCellState(CellLocation.TOP_CENTRE))
               .thenReturn(CellState.OCCUPIED_BY_X);
           when(board.getCellState(CellLocation.CENTRE_LEFT))
               .thenReturn(CellState.EMPTY);
           assertEquals(game.canMarkEmptySide(), CellLocation.CENTRE_LEFT);
       }

       @Test
       public void testCanMarkEmptySideTrueCentreRight() {
           when(board.getCellState(CellLocation.TOP_CENTRE))
               .thenReturn(CellState.OCCUPIED_BY_O);
           when(board.getCellState(CellLocation.CENTRE_LEFT))
               .thenReturn(CellState.OCCUPIED_BY_X);
           when(board.getCellState(CellLocation.CENTRE_RIGHT))
               .thenReturn(CellState.EMPTY);
           assertEquals(game.canMarkEmptySide(), CellLocation.CENTRE_RIGHT);
       }

       @Test
       public void testCanMarkEmptySideTrueBottomCentre() {
           when(board.getCellState(CellLocation.TOP_CENTRE))
               .thenReturn(CellState.OCCUPIED_BY_O);
           when(board.getCellState(CellLocation.CENTRE_LEFT))
               .thenReturn(CellState.OCCUPIED_BY_X);
           when(board.getCellState(CellLocation.CENTRE_RIGHT))
               .thenReturn(CellState.OCCUPIED_BY_X);
           when(board.getCellState(CellLocation.BOTTOM_CENTRE))
               .thenReturn(CellState.EMPTY);
           assertEquals(game.canMarkEmptySide(), CellLocation.BOTTOM_CENTRE);
       }

       @Test
       public void testCanMarkEmptySideFalse() {
           when(board.getCellState(CellLocation.TOP_CENTRE))
               .thenReturn(CellState.OCCUPIED_BY_O);
           when(board.getCellState(CellLocation.CENTRE_LEFT))
               .thenReturn(CellState.OCCUPIED_BY_X);
           when(board.getCellState(CellLocation.CENTRE_RIGHT))
               .thenReturn(CellState.OCCUPIED_BY_X);
           when(board.getCellState(CellLocation.BOTTOM_CENTRE))
               .thenReturn(CellState.OCCUPIED_BY_O);
           assertNull(game.canMarkEmptySide());
       }
}
