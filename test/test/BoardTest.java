package test;

import solution.Board;
import solution.TicTacToe;
import task.CellState;

import static task.CellLocation.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {

    private Board boardRowTest;
    private Board boardColumnTest;
    private Board boardMainDiagonalTest;
    private Board boardAntiDiagonalTest;

    @Before
    public void setBoard() {
        boardRowTest = new Board();
        boardRowTest.initBoard(BoardSimulator.board("one_winning_move01"));
        boardColumnTest = new Board();
        boardColumnTest.initBoard(BoardSimulator.board("check_column_test_board"));
        boardMainDiagonalTest = new Board();
        boardMainDiagonalTest.initBoard(BoardSimulator.board("check_main_diagonal_test_board"));
        boardAntiDiagonalTest = new Board();
        boardAntiDiagonalTest.initBoard(BoardSimulator.board("check_anti_diagonal_test_board"));
    }

    @Test(expected = NullPointerException.class)
    public void testInitBoardNull() {
        boardRowTest.initBoard(null);
    }

    @Test
    public void getCellStateTest() {
        assertEquals(boardRowTest.getCellState(TOP_LEFT), CellState.EMPTY);
        assertEquals(boardRowTest.getCellState(TOP_CENTRE), CellState.OCCUPIED_BY_X);
        assertEquals(boardRowTest.getCellState(BOTTOM_LEFT), CellState.OCCUPIED_BY_O);
    }

    /*
     * Test cases for checkRow().
     */
    @Test
    public void checkRowTestConditionXTrue() {
        assertEquals(boardRowTest.checkRow(CellState.OCCUPIED_BY_X, TOP_LEFT, TicTacToe.CAN_WIN_CONDITION), TOP_LEFT);
    }

    @Test
    public void checkRowTestConditionOTrue() {
        assertEquals(boardRowTest.checkRow(CellState.OCCUPIED_BY_O, BOTTOM_LEFT, TicTacToe.CAN_WIN_CONDITION), BOTTOM_RIGHT);
    }

    @Test
    public void checkRowTestConditionXFalse() {
        assertNull(boardRowTest.checkRow(CellState.OCCUPIED_BY_X, CENTRE_LEFT, TicTacToe.CAN_WIN_CONDITION));
    }

    @Test
    public void checkRowTestConditionOFalse() {
        assertNull(boardRowTest.checkRow(CellState.OCCUPIED_BY_O, CENTRE_LEFT, TicTacToe.CAN_WIN_CONDITION));
    }

    /*
     * Test cases for checkAllkRows().
     */
    @Test
    public void checkAllRowsTestConditionXTrue() {
        assertEquals(boardRowTest.checkAllRows(CellState.OCCUPIED_BY_X, TicTacToe.CAN_WIN_CONDITION), TOP_LEFT);
    }

    @Test
    public void checkAllRowsTestConditionOFalse() {
        assertNull(boardColumnTest.checkAllRows(CellState.OCCUPIED_BY_O, TicTacToe.CAN_WIN_CONDITION));
    }

    /*
     * Test cases for checkColumn(). 
     */
    @Test
    public void checkColumnTestConditionXTrue() {
        assertEquals(boardColumnTest.checkColumn(CellState.OCCUPIED_BY_X, TOP_RIGHT, TicTacToe.CAN_WIN_CONDITION), TOP_RIGHT);
    }

    @Test
    public void checkColumnTestConditionOTrue() {
        assertEquals(boardColumnTest.checkColumn(CellState.OCCUPIED_BY_O, TOP_LEFT, TicTacToe.CAN_WIN_CONDITION), BOTTOM_LEFT);
    }

    @Test
    public void checkColumnTestConditionXFalse() {
        assertNull(boardColumnTest.checkColumn(CellState.OCCUPIED_BY_X, TOP_CENTRE, TicTacToe.CAN_WIN_CONDITION));
    }

    @Test
    public void checkColumnTestConditionOFalse() {
        assertNull(boardColumnTest.checkColumn(CellState.OCCUPIED_BY_O, TOP_CENTRE, TicTacToe.CAN_WIN_CONDITION));
    }

    /*
     * Test cases for checkAllColumns(). 
     */
    @Test
    public void checkAllColumnsTestConditionOTrue() {
        assertEquals(boardColumnTest.checkAllColumns(CellState.OCCUPIED_BY_O, TicTacToe.CAN_WIN_CONDITION), BOTTOM_LEFT);
    }

    @Test
    public void checkAllColumnsTestConditionXFalse() {
        assertNull(boardRowTest.checkAllColumns(CellState.OCCUPIED_BY_X, TicTacToe.CAN_WIN_CONDITION));
    }

    /*
     * Test cases for checkMainDiagonal().
     */
    @Test
    public void checkMainDiagonalTestConditionXTrue() {
        assertEquals(boardMainDiagonalTest.checkMainDiagonal(CellState.OCCUPIED_BY_X, TOP_LEFT, TicTacToe.CAN_WIN_CONDITION), CENTRE_CENTRE);
    }

    @Test
    public void checkMainDiagonalTestConditionXFalse() {
        assertNull(boardAntiDiagonalTest.checkMainDiagonal(CellState.OCCUPIED_BY_X, CENTRE_CENTRE, TicTacToe.CAN_WIN_CONDITION));
    }

    @Test
    public void checkMainDiagonalTestConditionNotMainDiagCell() {
        assertNull(boardAntiDiagonalTest.checkMainDiagonal(CellState.OCCUPIED_BY_X, CENTRE_LEFT, TicTacToe.CAN_WIN_CONDITION));
    }

    /*
     * Test cases for checkAntiDiagonal().
     */
    @Test
    public void checkAntiDiagonalTestConditionOTrue() {
        assertEquals(boardAntiDiagonalTest.checkAntiDiagonal(CellState.OCCUPIED_BY_O, TOP_RIGHT, TicTacToe.CAN_WIN_CONDITION), CENTRE_CENTRE);
    }

    @Test
    public void checkAntiDiagonalTestConditionXFalse() {
        assertNull(boardAntiDiagonalTest.checkAntiDiagonal(CellState.OCCUPIED_BY_X, CENTRE_CENTRE, TicTacToe.CAN_WIN_CONDITION));
    }

    @Test
    public void checkAntiDiagonalTestConditionNotAntiDiagCell() {
        assertNull(boardAntiDiagonalTest.checkAntiDiagonal(CellState.OCCUPIED_BY_X, CENTRE_LEFT, TicTacToe.CAN_WIN_CONDITION));
    }

    /*
     * Test cases for checkDiagonals().
     */
    @Test
    public void checkDiagonalsTestConditionXTrueByMain() {
        assertEquals(boardMainDiagonalTest.checkDiagonals(CellState.OCCUPIED_BY_X, TicTacToe.CAN_WIN_CONDITION), CENTRE_CENTRE);
    }

    @Test
    public void checkDiagonalsTestConditionOTrueByAnti() {
        assertEquals(boardAntiDiagonalTest.checkDiagonals(CellState.OCCUPIED_BY_O, TicTacToe.CAN_WIN_CONDITION), CENTRE_CENTRE);
    }

    @Test
    public void checkDiagonalsTestConditionOFalse() {
        assertNull(boardRowTest.checkDiagonals(CellState.OCCUPIED_BY_X, TicTacToe.CAN_WIN_CONDITION));
    }

    /*
     * Test cases for getBoardState().
     */
    @Test
    public void getBoardStateTest() {
        Map<CellState,Integer> testMap = boardRowTest.getBoardState();
        assertEquals(testMap.get(CellState.EMPTY), new Integer(3));
        assertEquals(testMap.get(CellState.OCCUPIED_BY_X), new Integer(3));
        assertEquals(testMap.get(CellState.OCCUPIED_BY_O), new Integer(3));
    }
}
