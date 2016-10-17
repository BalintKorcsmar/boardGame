package solution;

import java.util.ArrayList;
import java.util.Arrays;

import task.CellLocation;
import task.CellState;
import task.GameBoard;

public class PerfectPlayer implements Strategy {

    private TicTacToe game;

    public PerfectPlayer() {
        game = new TicTacToe();
    }

    @Override
    public void setGame(GameBoard gameBoard) {
        if(gameBoard == null) {
            throw new NullPointerException("The gameBoard input parameter must not be null.");
        }
        game.setBoard(gameBoard);
    }

    public ArrayList<StrategicAction> setActions() {
        return new ArrayList<StrategicAction>(Arrays.asList(
                this::win,
                this::block,
                this::fork,
                this::blockFork,
                this::markCentre,
                this::markOppositeCorner,
                this::markEmptyCorner,
                this::markEmptySide));
    }

    @Override
    public boolean isGameFinished() {
        return game.checkGameFinished();
    }

    @Override
   public CellLocation win() {
        CellState myState = game.getMyState();
        return game.canWin(myState);
    }

    @Override
    public CellLocation block() {
        CellState oppState = game.getOpponentState();
        return game.canWin(oppState);
    }

    @Override
    public CellLocation fork() {
        CellState myState = game.getMyState();
        return game.canFork(myState);
    }

    @Override
    public CellLocation blockFork() {
        CellState oppState = game.getOpponentState();
        return game.canFork(oppState);
    }

    @Override
    public CellLocation markCentre() {
        return game.canMarkCentre();
    }

    @Override
    public CellLocation markOppositeCorner() {
        return game.canMarkOppositeCorner();
    }

    @Override
    public CellLocation markEmptyCorner() {
        return game.canMarkEmptyCorner();
    }

    @Override
    public CellLocation markEmptySide() {
        return game.canMarkEmptySide();
    }
}