package solution;

import java.util.ArrayList;
import java.util.List;

import task.CellLocation;
import task.Consultant;
import task.GameBoard;

public class MyConsultant implements Consultant {

    private Strategy myStrategy;
    private List<StrategicAction> myActions;
    public MyConsultant() {
        myStrategy = new PerfectPlayer();
        myActions = new ArrayList<StrategicAction>();
    }

    @Override
    public CellLocation suggest(GameBoard gameBoard) {
        if(gameBoard == null) {
            throw new NullPointerException("The gameBoard input parameter must not be null.");
        }

        myStrategy.setGame(gameBoard);
        myActions = myStrategy.setActions();

        if(myStrategy.isGameFinished()) {
            throw new IllegalStateException("The game represented by the gameBoard input parameter is over.");
        }

        CellLocation suggestion = null;

        if((suggestion = myActions.get(0).action()) != null) {
            return suggestion;
        }

        for(StrategicAction action : myActions) {
            suggestion = action.action();

            if(suggestion != null) {
                return suggestion;
            }
        }

        /* The execution should never reach this return statement.*/
        throw new IllegalStateException("It is not possible that there is no valid action in a valid in progress game");

//        /* Check if the game can be won in this turn. */
//        if((suggestion = myStrategy.win()) != null) {
//            return suggestion;
//        }
//
//        /* Check if the opponent can win next turn if not blocked. */
//        if((suggestion = myStrategy.block()) != null) {
//            return suggestion;
//        }
//
//        /* Check if a fork can be created. */
//        if((suggestion = myStrategy.fork()) != null) {
//            return suggestion;
//        }
//
//        /* Check if a fork by the opponent has to blocked. */
//        if((suggestion = myStrategy.blockFork()) != null) {
//            return suggestion;
//        }
//
//        /* Check if the centre cell can be marked. */
//        if((suggestion = myStrategy.markCentre()) != null) {
//            return suggestion;
//        }
//
//        /* Check if the "opposite corner" can be marked. */
//        if((suggestion = myStrategy.markOppositeCorner()) != null) {
//            return suggestion;
//        }
//
//        /* Check if an empty corner can be marked. */
//        if((suggestion = myStrategy.markEmptyCorner()) != null) {
//            return suggestion;
//        }
//
//        /* Check if an empty side can be marked. */
//        if((suggestion = myStrategy.markEmptySide()) != null) {
//            return suggestion;
//        }
//
//        /* The execution should never reach this return statement.*/
//        throw new IllegalStateException("It is not possible that there is no valid action in a valid in progress game");
    }
}

