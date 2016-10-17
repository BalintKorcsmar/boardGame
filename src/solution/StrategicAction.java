package solution;

import task.CellLocation;

@FunctionalInterface
public interface StrategicAction {
    CellLocation action();
}
