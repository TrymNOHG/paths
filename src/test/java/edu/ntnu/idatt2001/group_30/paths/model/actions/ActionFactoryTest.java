package edu.ntnu.idatt2001.group_30.paths.model.actions;

import edu.ntnu.idatt2001.group_30.paths.model.actions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ActionFactoryTest {

    @Nested
    public class ActionFactory_with_valid_value {

        @Test
        void can_get_GoldAction() {
            ActionType goldAction = ActionType.GOLD_ACTION;
            String value = "5";
            Action<Integer> expectedAction = new GoldAction(5);

            Action<?> actualAction = ActionFactory.getAction(goldAction, value);

            Assertions.assertTrue(actualAction instanceof GoldAction);
            Assertions.assertEquals(expectedAction, actualAction);
        }

        @Test
        void can_get_HealthAction() {
            ActionType healthAction = ActionType.HEALTH_ACTION;
            String value = "5";
            Action<Integer> expectedAction = new HealthAction(5);

            Action<?> actualAction = ActionFactory.getAction(healthAction, value);

            Assertions.assertTrue(actualAction instanceof HealthAction);
            Assertions.assertEquals(expectedAction, actualAction);
        }

        @Test
        void can_get_InventoryAction() {
            ActionType inventoryAction = ActionType.INVENTORY_ACTION;
            String value = "Sword";
            Action<String> expectedAction = new InventoryAction("Sword");

            Action<?> actualAction = ActionFactory.getAction(inventoryAction, value);

            Assertions.assertTrue(actualAction instanceof InventoryAction);
            Assertions.assertEquals(expectedAction, actualAction);
        }

        @Test
        void can_get_ScoreAction() {
            ActionType scoreAction = ActionType.SCORE_ACTION;
            String value = "5";
            Action<Integer> expectedAction = new ScoreAction(5);

            Action<?> actualAction = ActionFactory.getAction(scoreAction, value);

            Assertions.assertTrue(actualAction instanceof ScoreAction);
            Assertions.assertEquals(expectedAction, actualAction);
        }
    }

    @Nested
    public class ActionFactory_with_invalid_value_such_as {

        @Test
        void null_action_type_throws_NullPointerException() {
            Assertions.assertThrows(
                NullPointerException.class,
                () -> {
                    Action<?> action = ActionFactory.getAction(null, "5");
                }
            );
        }

        @Test
        void value_throws_NumberFormatException() {
            Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Action<?> action = ActionFactory.getAction(ActionType.GOLD_ACTION, "Invalid value");
                }
            );
        }
    }
}
