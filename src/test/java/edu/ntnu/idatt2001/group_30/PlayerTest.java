package edu.ntnu.idatt2001.group_30;

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
