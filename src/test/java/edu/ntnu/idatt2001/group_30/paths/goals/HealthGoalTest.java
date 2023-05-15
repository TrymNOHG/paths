package edu.ntnu.idatt2001.group_30.paths.goals;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idatt2001.group_30.paths.model.Player;
import edu.ntnu.idatt2001.group_30.paths.model.goals.HealthGoal;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class HealthGoalTest {

    @Nested
    public class A_HealthGoal_object {

        @Test
        public void can_properly_be_instantiated() {
            HealthGoal healthGoal;

            try {
                healthGoal = new HealthGoal(10);
            } catch (Exception e) {
                fail();
            }
        }

        @ParameterizedTest
        @ValueSource(ints = { 20, 21 })
        public void returns_true_when_Player_has_more_than_or_equal_to_min_health(int playerHealth) {
            int minHealthAmount = 20;
            HealthGoal healthGoal = new HealthGoal(minHealthAmount);
            Player player = new Player("Trym", playerHealth, 10, 10);
            boolean expectedStatus = true;

            boolean actualStatus = healthGoal.isFulfilled(player);

            assertEquals(expectedStatus, actualStatus);
        }

        @ParameterizedTest
        @ValueSource(ints = { 0, 19 })
        public void returns_false_when_Player_has_less_than_min_health(int playerHealth) {
            int minHealthAmount = 20;
            HealthGoal healthGoal = new HealthGoal(minHealthAmount);
            Player player = new Player("Trym", playerHealth, 10, 10);
            boolean expectedStatus = false;

            boolean actualStatus = healthGoal.isFulfilled(player);

            assertEquals(expectedStatus, actualStatus);
        }

        @Test
        public void throws_NullPointerException_checking_with_null_as_player() {
            HealthGoal healthGoal = new HealthGoal(10);
            Player player = null;

            assertThrows(NullPointerException.class, () -> healthGoal.isFulfilled(player));
        }

        @Test
        public void throws_IllegalArgumentException_instantiating_with_health_less_than_0() {
            int health = -5;
            HealthGoal healthGoal;

            try {
                healthGoal = new HealthGoal(health);
            } catch (IllegalArgumentException e) {
                assertTrue(true);
            }
        }
    }
}
