package solution;

import task.CellLocation;
import static task.CellLocation.*;
import task.CellState;
import task.GameBoard;

public class TicTacToe {

    private BoardService board;

    public TicTacToe() {
        board = new Board();
    }

    private static final ConditionChecker CAN_WIN_COND = (int thisCellNum, int emptyCellNum, CellLocation emptyCell) -> {
        if(thisCellNum == 2 && emptyCellNum == 1) {
            return emptyCell;
        } else {
            return null;
        }        
    };

    public static final Condition CAN_WIN_CONDITION = new Condition(1, CAN_WIN_COND);

    private static final ConditionChecker CAN_FORK_COND = (int thisCellNum, int emptyCellNum, CellLocation emptyCell) -> {
        if(thisCellNum == 1 && emptyCellNum == 2) {
            return emptyCell;
        } else {
            return null;
        }        
    };

    public static final Condition CAN_FORK_CONDITION = new Condition(2, CAN_FORK_COND);

    private static final ConditionChecker GAME_FINISHED_COND = (int thisCellNum, int emptyCellNum, CellLocation emptyCell) -> {
        if(thisCellNum == 3) {
            return CellLocation.CENTRE_CENTRE;
        } else {
            return null;
        }        
    };

    public static final Condition GAME_FINISHED_CONDITION = new Condition(0, GAME_FINISHED_COND);

    public void setBoard(GameBoard gameBoard) {
        if(gameBoard == null) {
            throw new NullPointerException("The gameBoard input parameter must not be null.");
        }
        board.initBoard(gameBoard);
    }

    public CellState getMyState() {
        int xCellsNum = board.getBoardState().get(CellState.OCCUPIED_BY_X);
        int oCellsNum = board.getBoardState().get(CellState.OCCUPIED_BY_O);

        /* Assuming the GameBoard represents a valid game the number of Xs and Os is either
         * equal or X is greater than O by 1. In case of equal it is X's turn and O's turn
         * otherwise. */
        if(xCellsNum == oCellsNum) {
            return CellState.OCCUPIED_BY_X;
        } else {
            return CellState.OCCUPIED_BY_O;
        }
    }

    public CellState getOpponentState() {
        CellState myState = getMyState();
        if(myState == CellState.OCCUPIED_BY_X) {
            return CellState.OCCUPIED_BY_O;
        } else {
            return CellState.OCCUPIED_BY_X;
        }
    }

    private boolean checkPlayerWon(CellState cellState) {
        if((board.checkAllRows(cellState, GAME_FINISHED_CONDITION)) != null ||
   
           (board.checkAllColumns(cellState, GAME_FINISHED_CONDITION)) != null ||

           (board.checkDiagonals(cellState, GAME_FINISHED_CONDITION)) != null) {
            return true;
        }

        return false;
    }

    public boolean checkGameFinished() {
        
        CellState cellState = getMyState();
        if(board.getBoardState().get(CellState.EMPTY) == 0) {
            return true;
        }

        if(checkPlayerWon(cellState)) {
            return true;
        }

        cellState = getOpponentState();
        if(checkPlayerWon(cellState)) {
            return true;
        }

        return false;
    }

    public CellLocation canWin(CellState cellState) {
        
        CellLocation returnLocation;
        if((returnLocation = board.checkAllRows(cellState, CAN_WIN_CONDITION)) != null ||
   
        (returnLocation = board.checkAllColumns(cellState, CAN_WIN_CONDITION)) != null ||

        (returnLocation = board.checkDiagonals(cellState, CAN_WIN_CONDITION)) != null) {
            return returnLocation;
        }
        return null;
    }

    private CellLocation checkFork(CellState cellState, CellLocation cell) {

        CellLocation rowLocation = board.checkRow(cellState, cell, CAN_FORK_CONDITION);
        CellLocation colLocation = board.checkColumn(cellState, cell, CAN_FORK_CONDITION);
        if(rowLocation == colLocation && rowLocation != null) {
            return rowLocation;
        }

        CellLocation mainDiagLocation = board.checkMainDiagonal(cellState, cell, CAN_FORK_CONDITION);
        if(rowLocation == mainDiagLocation && rowLocation != null) {
            return rowLocation;
        } else if(colLocation == mainDiagLocation && colLocation != null) {
            return colLocation;
        }

        CellLocation antiDiagLocation = board.checkAntiDiagonal(cellState, cell, CAN_FORK_CONDITION);
        if(rowLocation == antiDiagLocation && rowLocation != null) {
            return rowLocation;
        } else if(colLocation == antiDiagLocation && colLocation != null) {
            return colLocation;
        } else if(mainDiagLocation == antiDiagLocation && mainDiagLocation != null) {
            return mainDiagLocation;
        }
        return null;
    }

    public CellLocation canFork(CellState cellState) {
        CellLocation returnLocation;

        for(CellLocation cell : CellLocation.values()) {
            if(board.getCellState(cell) != CellState.EMPTY) {
               continue;
            }

            if((returnLocation = checkFork(cellState, cell)) != null) {
                return returnLocation;
            }
        }
        return null;
    }

    public CellLocation canMarkCentre() {
        if(board.getCellState(CENTRE_CENTRE) == CellState.EMPTY) {
            return CellLocation.CENTRE_CENTRE;
        } else {
            return null;
        }
    }

    public CellLocation canMarkOppositeCorner() {
        if(board.getCellState(TOP_LEFT) == CellState.EMPTY && board.getCellState(BOTTOM_RIGHT) == getOpponentState()) {
            return TOP_LEFT;
        } else if(board.getCellState(TOP_RIGHT) == CellState.EMPTY && board.getCellState(BOTTOM_LEFT) == getOpponentState()) {
            return CellLocation.TOP_RIGHT;
        } else if(board.getCellState(BOTTOM_LEFT) == CellState.EMPTY && board.getCellState(TOP_RIGHT) == getOpponentState()) {
            return CellLocation.BOTTOM_LEFT;
        } else if(board.getCellState(BOTTOM_RIGHT) == CellState.EMPTY && board.getCellState(TOP_LEFT) == getOpponentState()) {
            return CellLocation.BOTTOM_RIGHT;
        } else {
            return null;
        }
    }

    public CellLocation canMarkEmptyCorner() {
        if(board.getCellState(TOP_LEFT) == CellState.EMPTY) {
            return CellLocation.TOP_LEFT;
        } else if(board.getCellState(TOP_RIGHT) == CellState.EMPTY) {
            return CellLocation.TOP_RIGHT;
        } else if(board.getCellState(BOTTOM_LEFT) == CellState.EMPTY) {
            return CellLocation.BOTTOM_LEFT;
        } else if(board.getCellState(BOTTOM_RIGHT) == CellState.EMPTY) {
            return CellLocation.BOTTOM_RIGHT;
        } else {
            return null;
        }
    }
    
    public CellLocation canMarkEmptySide() {
        if(board.getCellState(TOP_CENTRE) == CellState.EMPTY) {
            return CellLocation.TOP_CENTRE;
        } else if(board.getCellState(CENTRE_LEFT) == CellState.EMPTY) {
            return CellLocation.CENTRE_LEFT;
        } else if(board.getCellState(CENTRE_RIGHT) == CellState.EMPTY) {
            return CellLocation.CENTRE_RIGHT;
        } else if(board.getCellState(BOTTOM_CENTRE) == CellState.EMPTY) {
            return CellLocation.BOTTOM_CENTRE;
        } else {
            return null;
        }
    }
}
