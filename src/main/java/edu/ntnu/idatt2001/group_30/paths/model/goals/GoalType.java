package edu.ntnu.idatt2001.group_30.paths.model.goals;

import java.util.HashMap;
import java.util.Map;

public enum GoalType {
    GOLD_GOAL("GoldGoal"),
    HEALTH_GOAL("HealthGoal"),
    INVENTORY_GOAL("InventoryGoal"),
    SCORE_GOAL("ScoreGoal");

    private final String stringVal;
    private static final Map<String, GoalType> stringToEnum = new HashMap<>();

    static {
        for (GoalType goalType : GoalType.values()) {
            stringToEnum.put(goalType.getStringVal(), goalType);
        }
    }

    GoalType(String stringVal) {
        this.stringVal = stringVal;
    }

    public static GoalType getGoalType(String goalType) {
        return stringToEnum.get(goalType);
    }

    public String getStringVal() {
        return stringVal;
    }
}
