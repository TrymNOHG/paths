package edu.ntnu.idatt2001.group_30.paths.goals;

import edu.ntnu.idatt2001.group_30.paths.model.Player;
import edu.ntnu.idatt2001.group_30.paths.model.goals.InventoryGoal;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryGoalTest {

    @Nested
    public class A_InventoryGoal_object {

        static List<String> getMandatoryInventory() {
            List<String> mandatoryInventory = new ArrayList<>();
            mandatoryInventory.add("Apple");
            mandatoryInventory.add("Sword");
            return mandatoryInventory;
        }

        @Test
        public void can_properly_be_instantiated() {
            InventoryGoal inventoryGoal;
            try{
                inventoryGoal = new InventoryGoal(getMandatoryInventory());
            }
            catch(Exception e) {
                fail();
            }
        }

        @Test
        public void returns_true_when_Player_has_more_than_or_equal_to_expected_inventory() {
            List<String> expectedInventory = getMandatoryInventory();
            InventoryGoal inventoryGoal = new InventoryGoal(getMandatoryInventory());
            Player player = new Player("Trym", 10, 10, 10);
            expectedInventory.forEach(player::addToInventory);
            boolean expectedStatus = true;

            boolean actualStatus = inventoryGoal.isFulfilled(player);

            assertEquals(expectedStatus, actualStatus);
        }

        @Test
        public void returns_false_when_Player_lacks_items_in_expected_inventory() {
            InventoryGoal inventoryGoal = new InventoryGoal(getMandatoryInventory());
            Player player = new Player("Trym", 10, 10, 10);
            player.addToInventory("Apple");
            boolean expectedStatus = false;

            boolean actualStatus = inventoryGoal.isFulfilled(player);

            assertEquals(expectedStatus, actualStatus);
        }

        @Test
        public void throws_NullPointerException_checking_with_null_as_player() {
            InventoryGoal inventoryGoal = new InventoryGoal(getMandatoryInventory());
            Player player = null;

            assertThrows(NullPointerException.class, () -> inventoryGoal.isFulfilled(player));
        }
    }

}