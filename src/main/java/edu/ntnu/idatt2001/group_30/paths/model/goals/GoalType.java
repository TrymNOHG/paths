package edu.ntnu.idatt2001.group_30.paths.model.goals;

import java.util.HashMap;
import java.util.Map;

/**
 * This enum represents the different types of goals.
 *
 * @author Trym Hamer Gudvangen
 */
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

    /**
     * This constructor creates a GoalType object based on the given string value.
     * @param stringVal   The string value of the GoalType object.
     */
    GoalType(String stringVal) {
        this.stringVal = stringVal;
    }

    /**
     * This method retrieves the GoalType object based on the given string value.
     * @param goalType The string value of the GoalType object.
     * @return         The GoalType object.
     */
    public static GoalType getGoalType(String goalType) {
        return stringToEnum.get(goalType);
    }

    /**
     * This method retrieves the string value of the GoalType object.
     * @return  The string value of the GoalType object.
     */
    public String getStringVal() {
        return stringVal;
    }
}
