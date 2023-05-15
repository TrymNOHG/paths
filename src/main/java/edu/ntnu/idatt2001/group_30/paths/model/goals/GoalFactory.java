package edu.ntnu.idatt2001.group_30.paths.model.goals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GoalFactory {

    public static Goal<?> getGoal(GoalType goalType, Object goalValue) throws IllegalArgumentException {
        switch (goalType) {
            case GOLD_GOAL, HEALTH_GOAL, SCORE_GOAL -> {
                if (goalValue instanceof String) {
                    int intValue = Integer.parseInt((String) goalValue);
                    return switch (goalType) {
                        case GOLD_GOAL -> new GoldGoal(intValue);
                        case HEALTH_GOAL -> new HealthGoal(intValue);
                        case SCORE_GOAL -> new ScoreGoal(intValue);
                        default -> null;
                    };
                }
            }
            case INVENTORY_GOAL -> {
                if (goalValue instanceof List<?> valueList) {
                    if (valueList.stream().allMatch(String.class::isInstance)) {
                        List<String> stringList = valueList
                            .stream()
                            .map(String.class::cast)
                            .collect(Collectors.toList());
                        return new InventoryGoal(stringList);
                    }
                } else if (goalValue instanceof String) {
                    return new InventoryGoal(Collections.singletonList((String) goalValue));
                }
            }
        }
        throw new IllegalArgumentException("Invalid goal type or value");
    }

    public static Goal<?> getGoal(String goalType, Object goalValue) throws IllegalArgumentException {
        return getGoal(GoalType.getGoalType(goalType), goalValue);
    }
}
