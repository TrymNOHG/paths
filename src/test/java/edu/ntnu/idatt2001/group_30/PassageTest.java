package edu.ntnu.idatt2001.group_30;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idatt2001.group_30.actions.GoldAction;

public class PassageTest {

    @Nested
    class An_instantiated_Passage_object {

        @Test
        void can_be_constructed() {
            Passage passage = new Passage("title", "nothing much :-)");
            assertEquals("title", passage.toString());
            assertEquals("nothing much :-)", passage.getContent());
        }

        @Test
        void cannot_be_constructed_with_empty_text() {
            /* here the text is completely empty */
            assertThrows(IllegalArgumentException.class, () -> new Passage("", "ref"));
            /* here the text contains some spaces */
            assertThrows(IllegalArgumentException.class, () -> new Passage("  ", "ref"));
        }

        @Test
        void cannot_be_constructed_with_empty_reference() {
            /* here the reference is completely empty */
            assertThrows(IllegalArgumentException.class, () -> new Passage("text", ""));
            /* here the reference contains some spaces */
            assertThrows(IllegalArgumentException.class, () -> new Passage("text", "  "));
        }
    }

    @Nested
    class A_valid_Passage_object {

        @Test
        void can_add_link() {
            Passage passage = new Passage("title", "nothing much :-)");
            Link link = new Link("text", "ref");
            passage.addLink(link);
            assertEquals(link, passage.getLinks().get(0));
            assertEquals(1, passage.getLinks().size());
        }

        @Test
        void can_get_all_links() {
            Passage passage = new Passage("title", "nothing much :-)");
            Link link = new Link("text", "ref");
            Link link2 = new Link("text2", "ref2");
            passage.addLink(link);
            passage.addLink(link2);
            assertEquals(2, passage.getLinks().size());
        }

        @Test
        void has_link_only_returns_true_if_link_exists() {
            Passage passage = new Passage("title", "nothing much :-)");
            Link link = new Link("text", "ref");
            passage.addLink(link);
            assertTrue(passage.hasLinks());
        }

        @Test
        void has_link_returns_false_if_no_links() {
            Passage passage = new Passage("title", "nothing much :-)");
            assertFalse(passage.hasLinks());
        }
    }
}
