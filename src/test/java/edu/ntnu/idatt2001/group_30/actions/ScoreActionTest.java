package edu.ntnu.idatt2001.group_30.actions;

import edu.ntnu.idatt2001.group_30.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ScoreActionTest {

    @Nested
    public class A_Score_Action_object {
        @Test
        public void instantiates_properly_with_valid_argument() {
            ScoreAction scoreAction;

            try{
                scoreAction = new ScoreAction(10);
            }
            catch (Exception e) {
                fail();
            }

        }

        @ParameterizedTest
        @ValueSource(ints = {-1, 0, 1})
        public void properly_adds_score_amount_to_Player_score(int scoreAmount) {
            int playerStartScore = 10;
            ScoreAction scoreAction = new ScoreAction(scoreAmount);
            Player player = new Player("Trym", 10, 10, playerStartScore);
            int expectedScoreAfter = playerStartScore + scoreAmount;

            scoreAction.execute(player);
            int actualScoreAfter = player.getScore();

            Assertions.assertEquals(expectedScoreAfter, actualScoreAfter);
        }

        @Test
        public void throws_IllegalArgumentException_executing_with_null_argument() {
            ScoreAction scoreAction = new ScoreAction(10);
            Player player = null;

            assertThrows(IllegalArgumentException.class, () -> scoreAction.execute(player));
        }
    }

}