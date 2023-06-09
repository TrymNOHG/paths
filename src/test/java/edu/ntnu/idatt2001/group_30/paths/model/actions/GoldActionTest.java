package edu.ntnu.idatt2001.group_30.paths.model.actions;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idatt2001.group_30.paths.model.Player;
import edu.ntnu.idatt2001.group_30.paths.model.actions.GoldAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GoldActionTest {

    @Nested
    public class A_GoldAction_object {

        @Test
        public void instantiates_properly_with_valid_argument() {
            GoldAction goldAction;

            try {
                goldAction = new GoldAction(10);
            } catch (Exception e) {
                fail();
            }
        }

        @ParameterizedTest
        @ValueSource(ints = { -1, 0, 1 })
        public void properly_adds_gold_amount_to_Player_gold(int goldAmount) {
            int playerStartGold = 10;
            GoldAction goldAction = new GoldAction(goldAmount);
            Player player = new Player("Trym", 10, 10, playerStartGold);
            int expectedGoldAfter = playerStartGold + goldAmount;

            goldAction.execute(player);
            int actualGoldAfter = player.getGold();

            Assertions.assertEquals(expectedGoldAfter, actualGoldAfter);
        }

        @Test
        public void throws_NullPointerException_executing_with_null_argument() {
            GoldAction goldAction = new GoldAction(10);
            Player player = null;

            assertThrows(NullPointerException.class, () -> goldAction.execute(player));
        }
    }
}
