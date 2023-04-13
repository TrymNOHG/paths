package edu.ntnu.idatt2001.group_30.paths;

import edu.ntnu.idatt2001.group_30.paths.Player;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Nested
    class An_instantiated_Player_object {
        @Test
        void can_be_constructed() {
            Player player = new Player("Ola Nordmann", 1, 2, 3);
            assertEquals("Ola Nordmann", player.getName());
            assertEquals(1, player.getHealth());
            assertEquals(2, player.getScore());
            assertEquals(3, player.getGold());
        }

        @Test
        void can_be_constructed_using_builder_pattern() {
            Player player = new Player.Builder("Ola Nordmann")
                    .health(10)
                    .score(2)
                    .gold(50)
                    .build();
            assertEquals("Ola Nordmann", player.getName());
            assertEquals(10, player.getHealth());
            assertEquals(2, player.getScore());
            assertEquals(50, player.getGold());
        }

        @Test
        void can_be_constructed_using_builder_pattern_with_inventory() {
            Player player = new Player.Builder("Ola Nordmann")
                    .addToInventory("Sword")
                    .addToInventory("Shield")
                    .addToInventory("Potion")
                    .build();
            assertEquals("Ola Nordmann", player.getName());
            /* when not specified, zero is the default value */
            assertEquals(0, player.getHealth());
            assertEquals(0, player.getScore());
            assertEquals(0, player.getGold());

            assertEquals(3, player.getInventory().size());
            assertTrue(player.getInventory().contains("Sword"));
            assertTrue(player.getInventory().contains("Shield"));
            assertTrue(player.getInventory().contains("Potion"));
        }

        @Test
        void cannot_be_constructed_with_negative_health() {
            assertThrows(IllegalArgumentException.class, () -> new Player("Ola Nordmann", -1, 2, 3));
        }

        @Test
        void cannot_be_constructed_with_negative_score() {
            assertThrows(IllegalArgumentException.class, () -> new Player("Ola Nordmann", 1, -2, 3));
        }

        @Test
        void cannot_be_constructed_with_negative_gold() {
            assertThrows(IllegalArgumentException.class, () -> new Player("Ola Nordmann", 1, 2, -3));
        }

        @Test
        void cannot_be_constructed_with_empty_name() {
            /* here the name is completely empty */
            assertThrows(IllegalArgumentException.class, () -> new Player("", 1, 2, 3));
            /* here the name contains some spaces */
            assertThrows(IllegalArgumentException.class, () -> new Player("    ", 1, 2, 3));
        }

        @Test
        void can_have_health_added() {
            Player player = new Player("Ola Nordmann", 1, 2, 3);
            player.addHealth(1);
            assertEquals(2, player.getHealth());
        }

        @Test
        void will_have_zero_health_if_gets_dealth_more_damage_than_current_health() {
            /* set start health to 5 */
            Player player = new Player("Ola Nordmann", 1, 2, 3);
            /* subtract health with 6, so more than initial starting health */
            player.addHealth(-6);
            assertEquals(0, player.getHealth());
        }

    }
}
