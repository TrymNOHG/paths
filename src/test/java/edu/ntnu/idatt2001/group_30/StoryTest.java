package edu.ntnu.idatt2001.group_30;

import edu.ntnu.idatt2001.group_30.paths.Link;
import edu.ntnu.idatt2001.group_30.paths.Passage;
import edu.ntnu.idatt2001.group_30.paths.Story;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StoryTest {

    @Nested
    class An_instantiated_Story_object {

        @Test
        void can_be_constructed() {
            Story story = new Story("title", new Passage("valid", "valid"));
            assertEquals("title", story.getTitle());
        }

        @Test
        void cannot_be_constructed_with_empty_title() {
            Passage passage = new Passage("valid", "valid");
            /* here the title is completely empty */
            assertThrows(IllegalArgumentException.class, () -> new Story("", passage));
            /* here the title contains some spaces */
            assertThrows(IllegalArgumentException.class, () -> new Story("  ", passage));
        }

        @Test
        void cannot_be_constructed_with_null_passage() {
            assertThrows(IllegalArgumentException.class, () -> new Story("title", null));
        }
    }

    @Nested
    class A_valid_Story_object {
        @Test
        void can_get_all_passages() {
            Story story = new Story("title", new Passage("valid", "valid"));
            Passage passage = new Passage("valid", "valid");
            Passage passage2 = new Passage("valid2", "valid2");
            story.addPassage(passage);
            story.addPassage(passage2);
            assertEquals(2, story.getPassages().size());
        }

        @Test
        void can_get_passage_by_reference() {
            Passage passageInitial = new Passage("valid", "valid");
            Passage passage = new Passage("still valid", "still valid");
            Story story = new Story("title", passageInitial);
            story.addPassage(passage);
            Link linkForInitialPassage = new Link("valid", "valid");
            assertEquals(passageInitial, story.getPassage(linkForInitialPassage));
        }

        @Test
        void can_get_opening_passage() {
            Passage passage = new Passage("valid", "valid");
            Story story = new Story("title", passage);
            assertEquals(passage, story.getOpeningPassage());
        }
    }
    
}
