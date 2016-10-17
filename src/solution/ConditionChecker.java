package solution;

import task.CellLocation;

@FunctionalInterface
public interface ConditionChecker {
    CellLocation checkCondition(int thisCellNum, int emptyCellNum, CellLocation emptyCell);
}
