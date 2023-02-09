package edu.ntnu.idatt2001.group_30.actions;

import edu.ntnu.idatt2001.group_30.Player;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryActionTest {

    @Nested
    public class An_InventoryAction_object {
        @Test
        public void instantiates_properly_with_valid_argument() {
            InventoryAction inventoryAction;
            String item = "Apple";

            try{
                inventoryAction = new InventoryAction(item);
            }
            catch (Exception e) {
                fail();
            }

        }

        @Test
        public void properly_adds_item_to_Player_inventory() {
            String expectedItem = "Apple";
            List<String> expectedInventory = new ArrayList<>();
            expectedInventory.add(expectedItem);
            InventoryAction inventoryAction = new InventoryAction(expectedItem);
            Player player = new Player("Trym", 10, 10, 10);


            inventoryAction.execute(player);
            List<String> actualInventory = player.getInventory();

            assertEquals(actualInventory, expectedInventory);
        }

        @Test
        public void throws_IllegalArgumentException_executing_with_null_argument() {
            InventoryAction inventoryAction = new InventoryAction("Apple");
            Player player = null;

            assertThrows(IllegalArgumentException.class, () -> inventoryAction.execute(player));
        }
    }


}