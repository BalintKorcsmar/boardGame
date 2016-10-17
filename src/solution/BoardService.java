package solution;

import java.util.Map;

import task.CellLocation;
import task.CellState;
import task.GameBoard;

public interface BoardService extends GameBoard{

    /**
     * <b>initBoard</b><p>{@code public void initBoard(GameBoard gameBoard)}<p>
     * This method initializes the instance of the implementation of this interface.
     * 
     * @param gameBoard
     *          an in-progress game
     * @return N/A 
     */
    public void initBoard(GameBoard gameBoard);

    /**
     * <b>getCellState</b><p>{@code public CellState getCellState(CellLocation cell)}<p>
     * This method returns the state of the cell.
     * 
     * @param cell
     *            a {@code CellLocation} value.
     * @return The {@code CellState} of the cell
     */
    public CellState getCellState(CellLocation cell);

    /**
     * <b>getBoardState</b><p>{@code public Map<CellState, Integer> getBoardState()}<p>
     * This method counts the number of empty cells, cells marked by the X and cells marked
     * by the O player and returns the values in a map.
     * 
     * @param cell
     *            a {@code CellLocation} value.
     * @return A {@code Map<CellState, Integer>} with the number X, O and empty cells.
     */
    public Map<CellState, Integer> getBoardState();

    /**
     * <b>checkRow</b>
     * <p>{@code public CellLocation checkRow(CellState cellState, CellLocation cellLocation, Condition condition)}<p>
     * This method iterates through the cells of the row specified by the {@code cellLocation} input parameter.
     * Returns the {@code CellLocation} of cell in the row that matches the specified {@code condition}
     * for the given {@code cellState} if there exists such cell.
     * 
     * @param cellState
     *            a {@code CellState} value.
     * @param cellLocation
     *            a {@code CellLocation} value.
     * @param condition
     *            a {@code Condition} parameter that specifies the condition to be checked by this method.
     * @return A {@code CellLocation} that matches the given condition. Returns {@code null} if there is
     * no such cell.
     */
    public CellLocation checkRow(CellState cellState, CellLocation cellLocation, Condition condition);

    /**
     * <b>checkAllRows</b><p>{@code public CellLocation checkAllRows(CellState cellState, Condition condition)}<p>
     * <p>{@code public CellLocation checkRow(CellState cellState, CellLocation cellLocation, Condition condition)}<p>
     * This method iterates through the rows of the board.
     * Returns the {@code CellLocation} of the cell  in any row of the board that matches the specified
     * {@code condition} for the given {@code cellState} if there exists such cell.
     * 
     * @param cellState
     *            a {@code CellState} value.
     * @param condition
     *            a {@code Condition} parameter that specifies the condition to be checked by this method.
     * @return A {@code CellLocation} that matches the given condition. Returns {@code null} if there is
     * no such cell.
     */
    public CellLocation checkAllRows(CellState cellState, Condition condition);

    /**
     * <b>checkColumn</b>
     * <p>{@code public CellLocation checkColumn(CellState cellState, CellLocation cellLocation, Condition condition)}<p>
     * This method iterates through the cells of the column specified by the {@code cellLocation} input parameter.
     * Returns the {@code CellLocation} of the cell  in the column that matches the specified {@code condition}
     * for the given {@code cellState} if there exists such cell.
     * 
     * @param cellState
     *            a {@code CellState} value.
     * @param cellLocation
     *            a {@code CellLocation} value.
     * @param condition
     *            a {@code Condition} parameter that specifies the condition to be checked by this method.
     * @return A {@code CellLocation} that matches the given condition. Returns {@code null} if there is
     * no such cell.
     */
    public CellLocation checkColumn(CellState cellState, CellLocation cellLocation, Condition condition);

    /**
     * <b>checkAllColumns</b><p>{@code public CellLocation checkAllColumns(CellState cellState, Condition condition))}<p>
     * This method iterates through the columns of the board.
     * Returns the {@code CellLocation} of the cell  in any column of the board that matches the specified
     * {@code condition} for the given {@code cellState} if there exists such cell.
     * 
     * @param cellState
     *            a {@code CellState} value.
     * @param condition
     *            a {@code Condition} parameter that specifies the condition to be checked by this method.
     * @return A {@code CellLocation} that matches the given condition. Returns {@code null} if there is
     * no such cell.
     */

    public CellLocation checkAllColumns(CellState cellState, Condition condition);

    /**
     * <b>checkMainDiagonal</b>
     * <p>{@code public CellLocation checkMainDiagonal(CellState cellState, CellLocation cellLocation, Condition condition)}<p>
     * This method iterates through the cells of the main diagonal of the board.
     * Returns the {@code CellLocation} of the cell in the main diagonal that matches the specified {@code condition}
     * for the given {@code cellState} if there exists such cell.
     * 
     * @param cellState
     *            a {@code CellState} value.
     * @param cellLocation
     *            a {@code CellLocation} value.
     * @param condition
     *            a {@code Condition} parameter that specifies the condition to be checked by this method.
     * @return A {@code CellLocation} that matches the given condition. Returns {@code null} if there is
     * no such cell.
     */
    public CellLocation checkMainDiagonal(CellState cellState, CellLocation cellLocation, Condition condition);

    /**
    * <b>checkAntiDiagonal</b>
    * <p>{@code public CellLocation checkAntiDiagonal(CellState cellState, CellLocation cellLocation, Condition condition)}<p>
    * This method iterates through the cells of the anti diagonal of the board.
    * Returns the {@code CellLocation} of the cell in the main diagonal that matches the specified {@code condition}
    * for the given {@code cellState} if there exists such cell.
    * 
    * @param cellState
    *            a {@code CellState} value.
    * @param cellLocation
    *            a {@code CellLocation} value.
    * @param condition
    *            a {@code Condition} parameter that specifies the condition to be checked by this method.
    * @return A {@code CellLocation} that matches the given condition. Returns {@code null} if there is
    * no such cell.
    */
    public CellLocation checkAntiDiagonal(CellState cellState, CellLocation cellLocation, Condition condition);

    /**
    * <b>checkDiagonals</b>
    * <p>{@code public CellLocation checkDiagonals(CellState cellState, Condition condition)}<p>
    * This method iterates through the cells of the main and anti diagonals of the board.
    * Returns the {@code CellLocation} of the cell in the main or anti diagonal that matches the specified {@code condition}
    * for the given {@code cellState} if there exists such cell.
    * 
    * @param cellState
    *            a {@code CellState} value.
    * @param cellLocation
    *            a {@code CellLocation} value.
    * @param condition
    *            a {@code Condition} parameter that specifies the condition to be checked by this method.
    * @return A {@code CellLocation} that matches the given condition. Returns {@code null} if there is
    * no such cell.
    */
    public CellLocation checkDiagonals(CellState cellState, Condition condition);
}
