package edu.ntnu.idatt2001.group_30;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idatt2001.group_30.actions.GoldAction;

class LinkTest {
    @Nested
    class An_instantiated_Link_object {

        @Test
        void can_be_constructed() {
            Link link = new Link("Big chungus", "ref");
            assertEquals("Big chungus", link.getText());
            assertEquals("ref", link.getReference());
        }

        @Test
        void cannot_be_constructed_with_empty_text() {
            /* here the text is completely empty */
            assertThrows(IllegalArgumentException.class, () -> new Link("", "ref"));
            /* here the text contains some spaces */ 
            assertThrows(IllegalArgumentException.class, () -> new Link("   ", "ref"));
        }

        @Test
        void cannot_be_constructed_with_empty_reference() {
            /* here the reference is completely empty */
            assertThrows(IllegalArgumentException.class, () -> new Link("You see a troll", ""));
            /* here the reference contains some spaces */
            assertThrows(IllegalArgumentException.class, () -> new Link("You see a troll", "   "));
        }
    }

    @Nested
    class A_valid_Link_object {

        @Test
        void can_add_action() {
            Link link = new Link("You see a troll", "ref");
            GoldAction goldAction = new GoldAction(10);
            link.addAction(goldAction);
            assertEquals(goldAction, link.getActions().get(0));
            assertEquals(1, link.getActions().size());
        }

        @Test
        void can_get_all_actions() {
            Link link = new Link("You see a troll", "ref");
            GoldAction goldAction = new GoldAction(10);
            GoldAction goldAction2 = new GoldAction(20);
            link.addAction(goldAction);
            link.addAction(goldAction2);
            assertEquals(2, link.getActions().size());
        }
    }

    @Nested
    class Two_Link_objects {
        @Test
        void are_equal_if_they_have_the_same_reference() {
            /* different text, same reference */
            Link link1 = new Link("You see a troll", "ref");
            Link link2 = new Link("You see a ogre", "ref");
            assertEquals(link1, link2);
        }

        @Test
        void are_not_equal_if_they_have_different_references() {
            /* same text, different reference */
            Link link1 = new Link("You see a troll", "ref");
            Link link2 = new Link("You see a troll", "ref2");
            assertNotEquals(link1, link2);
        }
    }

}