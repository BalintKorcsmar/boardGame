package solution;

import java.util.ArrayList;

import task.CellLocation;
import task.GameBoard;

public interface Strategy {

    /**
     * <b>isGameFinished</b><p>{@code public boolean isGameFinished()}<p>
     * This method checks if the game is finished. The game can be finished because
     * either the X or the O player won the game, or there is no more empty cell left
     * and the result is a draw. The game is considered in progress if none of the
     * above criteria apply.
     * 
     * @param void
     * @return The method {@code true} if the game is finished and {@code false} otherwise. 
     */
    public boolean isGameFinished();
    
    /**
     * <b>win</b><p>{@code public CellLocation win()}<p>
     * This method checks if the game can be won in this step. If yes the method 
     * returns a {@code CellLocation} that wins the game if marked.
     * 
     * @param void
     * @return The CellLocation that the player has to mark in order to win. Returns
     * {@code null} if there is no such cell. 
     */
    public CellLocation win();

    /**
     * <b>block</b><p>{@code public CellLocation block()}<p>
     * This method checks if the opponent can win in the next step. If yes the method 
     * returns the {@code CellLocation} that blocks the opponent's winning step.
     * 
     * @param void
     * @return The CellLocation that the player has to mark in order to block. Returns
     * {@code null} if there is no such cell. 
     */
    public CellLocation block();

    /**
     * <b>fork</b><p>{@code public CellLocation fork()}<p>
     * This method checks if the current player can make a fork. If yes the method returns
     * a {@code CellLocation} that makes the fork.
     * 
     * @param void
     * @return The CellLocation that the player has to mark in order to make a fork.
     * Returns {@code null} if there is no such cell. 
     */
    public CellLocation fork();

    /**
     * <b>blockFork</b><p>{@code public CellLocation blockFork()}<p>
     * This method checks if the opponent can make a fork in the next step. If yes the
     * method returns the {@code CellLocation} that prevents the opponent's fork.
     * 
     * @param void
     * @return The CellLocation that the player has to mark in order to block a fork. Returns
     * {@code null} if there is no such cell. 
     */
    public CellLocation blockFork();

    /**
     * <b>markCentre</b><p>{@code public CellLocation markCentre()}<p>
     * This method checks if the centre cell is empty. If yes the method returns the
     * {@code CellLocation.CENTRE_CENTRE} value.
     * 
     * @param void
     * @return The CellLocation.CENTRE_CENTRE if its state is {@code CellState.EMPTY}.
     * Returns {@code null} if not. 
     */
    public CellLocation markCentre();

    /**
     * <b>markOppositeCorner</b><p>{@code public CellLocation markOppositeCorner()}<p>
     * This method checks if there exist such a pair of corners that one is empty and the 
     * other is marked by the opponent. If yes it returns the empty corner's
     * {@code CellLocation} of this pair.
     * 
     * @param void
     * @return The CellLocation of the opposite empty corner. Returns {@code null} if
     * there is no such corner. 
     */
    public CellLocation markOppositeCorner();

    /**
     * <b>markEmptyCorner</b><p>{@code public CellLocation markEmptyCorner()}<p>
     * This method checks if there is an empty corner. If yes it returns the empty corner's
     * {@code CellLocation}.
     * 
     * @param void
     * @return The CellLocation of the empty corner. Returns {@code null} if
     * there is no empty corner. 
     */
    public CellLocation markEmptyCorner();

    /**
     * <b>markEmptySide</b><p>{@code public CellLocation markEmptySide()}<p>
     * This method checks if there is an empty side cell. If yes it returns the empty
     * side's {@code CellLocation}.
     * 
     * @param void
     * @return The CellLocation of the empty side. Returns {@code null} if
     * there is no such side. 
     */
    public CellLocation markEmptySide();

    /**
     * <b>setGame</b><p>{@code public void setGame(GameBoard gameBoard)}<p>
     * This method sets the the current game.
     * @param gameBoard
     *          an in-progress game
     * @return void 
     */
    public void setGame(GameBoard gameBoard);
    public ArrayList<StrategicAction> setActions();
}
