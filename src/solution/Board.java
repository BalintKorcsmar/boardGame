package solution;

import java.util.HashMap;
import java.util.Map;

import task.CellLocation;
import task.CellState;
import task.GameBoard;

public class Board implements BoardService{
    private static final int COL_NUM = 3;
    private static final int ROW_NUM = 3;
    private static final Map<CellLocation, Integer> CELL_TO_INT = new HashMap<CellLocation, Integer> ();
    private static final Map<Integer, CellLocation> INT_TO_CELL = new HashMap<Integer, CellLocation> ();
    private final Map<CellLocation, CellState> boardMap = new HashMap<CellLocation, CellState> ();

    static {
        CELL_TO_INT.put(CellLocation.TOP_LEFT,      0);
        CELL_TO_INT.put(CellLocation.TOP_CENTRE,    1);
        CELL_TO_INT.put(CellLocation.TOP_RIGHT,     2);
        CELL_TO_INT.put(CellLocation.CENTRE_LEFT,   3);
        CELL_TO_INT.put(CellLocation.CENTRE_CENTRE, 4);
        CELL_TO_INT.put(CellLocation.CENTRE_RIGHT,  5);
        CELL_TO_INT.put(CellLocation.BOTTOM_LEFT,   6);
        CELL_TO_INT.put(CellLocation.BOTTOM_CENTRE, 7);
        CELL_TO_INT.put(CellLocation.BOTTOM_RIGHT,  8);

        INT_TO_CELL.put(0, CellLocation.TOP_LEFT);
        INT_TO_CELL.put(1, CellLocation.TOP_CENTRE);
        INT_TO_CELL.put(2, CellLocation.TOP_RIGHT);
        INT_TO_CELL.put(3, CellLocation.CENTRE_LEFT);
        INT_TO_CELL.put(4, CellLocation.CENTRE_CENTRE);
        INT_TO_CELL.put(5, CellLocation.CENTRE_RIGHT);
        INT_TO_CELL.put(6, CellLocation.BOTTOM_LEFT);
        INT_TO_CELL.put(7, CellLocation.BOTTOM_CENTRE);
        INT_TO_CELL.put(8, CellLocation.BOTTOM_RIGHT);
    }

    public Board() {}

    @Override
    public void initBoard(GameBoard gameBoard) {
        if(gameBoard == null) {
            throw new NullPointerException("The gameBoard input parameter must not be null.");
        }

        for(CellLocation cell : CellLocation.values()) {
            boardMap.put(cell, gameBoard.getCellState(cell));
        }
    }

    @Override
    public CellState getCellState(CellLocation cell) {
        return boardMap.get(cell);
    }

    @Override
    public Map<CellState, Integer> getBoardState() {
        int xCellsNum = 0;
        int oCellsNum = 0;
        Map<CellState, Integer> returnMap = new HashMap<CellState, Integer>();

        for(CellLocation cell : CellLocation.values()) {
            if(getCellState(cell) == CellState.OCCUPIED_BY_X) {
                xCellsNum++;
            } else if(getCellState(cell) == CellState.OCCUPIED_BY_O) {
                oCellsNum++;
            }
        }
        returnMap.put(CellState.OCCUPIED_BY_X, xCellsNum);
        returnMap.put(CellState.OCCUPIED_BY_O, oCellsNum);
        returnMap.put(CellState.EMPTY, ROW_NUM * COL_NUM - xCellsNum - oCellsNum);

        return returnMap;
    }

    @Override
    public CellLocation checkRow(CellState cellState, CellLocation cellLocation, Condition condition) {
        CellLocation returnLocation;
        int emptyCellNum = 0;
        int thisCellNum = 0;
        int emptyCell = 0;
        final int cell = CELL_TO_INT.get(cellLocation);
        final int row = cell / COL_NUM;

        for(int cellIter = row * COL_NUM; cellIter < (row + 1) * COL_NUM; cellIter++) {
            if(boardMap.get(INT_TO_CELL.get(cellIter)) == cellState) {
                thisCellNum++;
            } else if(boardMap.get(INT_TO_CELL.get(cellIter)) == CellState.EMPTY) {
                emptyCellNum++;
                if(cellIter == cell || condition.getEmptyCells() == 1) {
                    emptyCell = cellIter;
                }
            }
        }
        if((returnLocation = condition.getCondition().checkCondition(thisCellNum, emptyCellNum, INT_TO_CELL.get(emptyCell))) != null) {
            return returnLocation;
        } else {
            return null;
        }
    }

    @Override
    public CellLocation checkAllRows(CellState cellState, Condition condition) {
        CellLocation returnLocation;

        for(int row = 0; row < ROW_NUM; row++) {

            if((returnLocation = checkRow(cellState, INT_TO_CELL.get(row * COL_NUM), condition)) != null) {
                return returnLocation;
            }
        }
        return null;
    }

    @Override
    public CellLocation checkColumn(CellState cellState, CellLocation cellLocation, Condition condition) {
        CellLocation returnLocation;
        int emptyCellNum = 0;
        int thisCellNum = 0;
        int emptyCell = 0;
        final int cell = CELL_TO_INT.get(cellLocation);
        final int col = cell % COL_NUM;

        for(int cellIter = col; cellIter < COL_NUM * ROW_NUM; cellIter += COL_NUM) {
            if(boardMap.get(INT_TO_CELL.get(cellIter)) == cellState) {
                thisCellNum++;
            } else if(boardMap.get(INT_TO_CELL.get(cellIter)) == CellState.EMPTY) {
                emptyCellNum++;
                if(cellIter == cell || condition.getEmptyCells() == 1) {
                    emptyCell = cellIter;
                }
            }
        }
        if((returnLocation = condition.getCondition().checkCondition(thisCellNum, emptyCellNum, INT_TO_CELL.get(emptyCell))) != null) {
            return returnLocation;
        } else {
            return null;
        }
    }

    @Override
    public CellLocation checkAllColumns(CellState cellState, Condition condition) {
        CellLocation returnLocation;

        for(int col = 0; col < COL_NUM; col++) {
            if((returnLocation = checkColumn(cellState, INT_TO_CELL.get(col), condition)) != null) {
                return returnLocation;
            }
        }
        return null;
    }

    private boolean isMainDiagCell(CellLocation cell) {
        if(cell == CellLocation.TOP_LEFT || cell == CellLocation.CENTRE_CENTRE || cell == CellLocation.BOTTOM_RIGHT) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public CellLocation checkMainDiagonal(CellState cellState, CellLocation cellLocation, Condition condition) {
        if(!isMainDiagCell(cellLocation)) {
            return null;
        }
        CellLocation returnLocation;
        int emptyCellNum = 0;
        int thisCellNum = 0;
        int emptyCell = 0;
        final int cell = CELL_TO_INT.get(cellLocation);

        for(int row = 0, offset = 0; row < ROW_NUM; row++, offset++) {
            if(boardMap.get(INT_TO_CELL.get(row * ROW_NUM + offset)) == cellState) {
                thisCellNum++;
            } else if(boardMap.get(INT_TO_CELL.get(row * ROW_NUM + offset)) == CellState.EMPTY) {
                emptyCellNum++;
                if(row * ROW_NUM + offset == cell || condition.getEmptyCells() == 1) {
                    emptyCell = row * ROW_NUM + offset;
                }
            }
        }
        if((returnLocation = condition.getCondition().checkCondition(thisCellNum, emptyCellNum, INT_TO_CELL.get(emptyCell))) != null) {
            return returnLocation;
         } else {
             return null;
         }
    }

    private boolean isAntiDiagCell(CellLocation cell) {
        if(cell == CellLocation.TOP_RIGHT || cell == CellLocation.CENTRE_CENTRE || cell == CellLocation.BOTTOM_LEFT) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public CellLocation checkAntiDiagonal(CellState cellState, CellLocation cellLocation, Condition condition) {
        if(!isAntiDiagCell(cellLocation)) {
            return null;
        }
        CellLocation returnLocation;
        int emptyCellNum = 0;
        int thisCellNum = 0;
        int emptyCell = 0;
        final int cell = CELL_TO_INT.get(cellLocation);

        for(int row = 0, offset = ROW_NUM - 1; row < ROW_NUM; row++, offset--) {
            if(boardMap.get(INT_TO_CELL.get(row * ROW_NUM + offset)) == cellState) {
                thisCellNum++;
            } else if(boardMap.get(INT_TO_CELL.get(row * ROW_NUM + offset)) == CellState.EMPTY) {
                emptyCellNum++;
                if(row * ROW_NUM + offset == cell || condition.getEmptyCells() == 1) {
                    emptyCell = row * ROW_NUM + offset;
                }
            }
        }
        if((returnLocation = condition.getCondition().checkCondition(thisCellNum, emptyCellNum, INT_TO_CELL.get(emptyCell))) != null) {
            return returnLocation;
         } else {
             return null;
         }
    }

    @Override
    public CellLocation checkDiagonals(CellState cellState, Condition condition) 
    {
        CellLocation returnLocation;

        if((returnLocation = checkMainDiagonal(cellState, CellLocation.CENTRE_CENTRE, condition)) != null) {
            return returnLocation;
        }

        if((returnLocation = checkAntiDiagonal(cellState, CellLocation.CENTRE_CENTRE, condition)) != null) {
            return returnLocation;
        }
        return null;
    }
}
