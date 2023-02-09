package edu.ntnu.idatt2001.group_30.actions;

import edu.ntnu.idatt2001.group_30.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HealthActionTest {

    @Nested
    public class A_Health_Action_object {
        @Test
        public void instantiates_properly_with_valid_argument() {
            HealthAction healthAction;

            try{
                healthAction = new HealthAction(10);
            }
            catch (Exception e) {
                fail();
            }

        }

        @ParameterizedTest
        @ValueSource(ints = {-1, 0, 1})
        public void properly_adds_health_amount_to_Player_health(int healthAmount) {
            int playerStartHealth = 10;
            HealthAction healthAction = new HealthAction(healthAmount);
            Player player = new Player("Trym", 10, 10, playerStartHealth);
            int expectedHealthAfter = playerStartHealth + healthAmount;

            healthAction.execute(player);
            int actualHealthAfter = player.getHealth();

            Assertions.assertEquals(expectedHealthAfter, expectedHealthAfter);
        }

        @Test
        public void throws_IllegalArgumentException_executing_with_null_argument() {
            HealthAction healthAction = new HealthAction(10);
            Player player = null;

            assertThrows(IllegalArgumentException.class, () -> healthAction.execute(player));
        }
    }


}