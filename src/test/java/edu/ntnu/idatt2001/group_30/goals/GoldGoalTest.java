package edu.ntnu.idatt2001.group_30.goals;

import edu.ntnu.idatt2001.group_30.Player;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class GoldGoalTest {

    @Nested
    public class A_GoldGoal_object {

        @Test
        public void can_properly_be_instantiated() {
            GoldGoal goldGoal;

            try{
                goldGoal = new GoldGoal(10);
            }
            catch(Exception e) {
                fail();
            }
        }

        @ParameterizedTest
        @ValueSource(ints = {20, 21})
        public void returns_true_when_Player_has_more_than_or_equal_to_min_gold(int playerGold) {
            int minGoldAmount = 20;
            GoldGoal goldGoal = new GoldGoal(minGoldAmount);
            Player player = new Player("Trym", 10, 10, playerGold);
            boolean expectedStatus = true;

            boolean actualStatus = goldGoal.isFulfilled(player);

            assertEquals(expectedStatus, actualStatus);
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 9})
        public void returns_false_when_Player_has_less_than_min_gold(int playerGold) {
            int minGoldAmount = 10;
            GoldGoal goldGoal = new GoldGoal(minGoldAmount);
            Player player = new Player("Trym", 10, 10, playerGold);
            boolean expectedStatus = false;

            boolean actualStatus = goldGoal.isFulfilled(player);

            assertEquals(expectedStatus, actualStatus);
        }

        @Test
        public void throws_NullPointerException_checking_with_null_as_player() {
            GoldGoal goldGoal = new GoldGoal(10);
            Player player = null;

            assertThrows(NullPointerException.class, () -> goldGoal.isFulfilled(player));
        }
    }


}