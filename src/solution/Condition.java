package solution;

public class Condition {
    private int emptyCells;
    private ConditionChecker condition;

    public Condition(int emptyCells, ConditionChecker condition) {
        this.emptyCells = emptyCells;
        this.condition = condition;
    }

    public ConditionChecker getCondition() {
        return condition;
    }

    public int getEmptyCells() {
        return emptyCells;
    }
}
