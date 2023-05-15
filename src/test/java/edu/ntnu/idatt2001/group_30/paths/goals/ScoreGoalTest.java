package edu.ntnu.idatt2001.group_30.paths.goals;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idatt2001.group_30.paths.model.Player;
import edu.ntnu.idatt2001.group_30.paths.model.goals.ScoreGoal;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ScoreGoalTest {

    @Nested
    public class A_ScoreGoal_object {

        @Test
        public void can_properly_be_instantiated() {
            ScoreGoal scoreGoal;

            try {
                scoreGoal = new ScoreGoal(10);
            } catch (Exception e) {
                fail();
            }
        }

        @ParameterizedTest
        @ValueSource(ints = { 20, 21 })
        public void returns_true_when_Player_has_more_than_or_equal_to_min_score(int playerScore) {
            int minScoreAmount = 20;
            ScoreGoal scoreGoal = new ScoreGoal(minScoreAmount);
            Player player = new Player("Trym", 10, playerScore, 10);
            boolean expectedStatus = true;

            boolean actualStatus = scoreGoal.isFulfilled(player);

            assertEquals(expectedStatus, actualStatus);
        }

        @ParameterizedTest
        @ValueSource(ints = { 0, 19 })
        public void returns_false_when_Player_has_less_than_min_score(int playerScore) {
            int minScoreAmount = 20;
            ScoreGoal scoreGoal = new ScoreGoal(minScoreAmount);
            Player player = new Player("Trym", 10, playerScore, 10);
            boolean expectedStatus = false;

            boolean actualStatus = scoreGoal.isFulfilled(player);

            assertEquals(expectedStatus, actualStatus);
        }

        @Test
        public void throws_NullPointerException_checking_with_null_as_player() {
            ScoreGoal scoreGoal = new ScoreGoal(10);
            Player player = null;

            assertThrows(NullPointerException.class, () -> scoreGoal.isFulfilled(player));
        }
    }
}
