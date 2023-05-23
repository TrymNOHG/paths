package edu.ntnu.idatt2001.group_30.paths.model.goals;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GoalFactoryTest {

    /*
     * this is a factory class, so the typical beforeEach arrange step is not needed
     */

    @ParameterizedTest
    @MethodSource("validGoalTypeAndValueProvider")
    void getGoal_withValidGoalTypeAndValue_returnsGoalObject() {
        GoalType goalType = GoalType.GOLD_GOAL;
        Object goalValue = "100";

        Goal<?> goal = GoalFactory.getGoal(goalType, goalValue);

        assertEquals(GoldGoal.class, goal.getClass());
    }

    private static Stream<Arguments> validGoalTypeAndValueProvider() {
        return Stream.of(
            Arguments.of(GoalType.GOLD_GOAL, "100", GoldGoal.class),
            Arguments.of(GoalType.HEALTH_GOAL, "50", HealthGoal.class),
            Arguments.of(GoalType.SCORE_GOAL, "500", ScoreGoal.class)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidGoalTypeAndValueProvider")
    void getGoal_withInvalidGoalValue_throwsIllegalArgumentException() {
        GoalType goalType = GoalType.GOLD_GOAL;
        Object goalValue = "invalid_value";

        assertThrows(IllegalArgumentException.class, () -> GoalFactory.getGoal(goalType, goalValue));
    }

    private static Stream<Arguments> invalidGoalTypeAndValueProvider() {
        return Stream.of(
            Arguments.of(GoalType.GOLD_GOAL, "invalid", GoldGoal.class),
            Arguments.of(GoalType.HEALTH_GOAL, "-1", HealthGoal.class),
            Arguments.of(GoalType.SCORE_GOAL, "", ScoreGoal.class)
        );
    }

    @Test
    void getGoal_withValidInventoryGoalValue_returnsInventoryGoalObject() {
        GoalType goalType = GoalType.INVENTORY_GOAL;
        Object goalValue = "item";

        Goal<?> goal = GoalFactory.getGoal(goalType, goalValue);

        assertEquals(InventoryGoal.class, goal.getClass());
    }
}
