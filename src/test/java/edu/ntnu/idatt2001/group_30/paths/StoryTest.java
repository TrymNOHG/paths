package edu.ntnu.idatt2001.group_30.paths;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idatt2001.group_30.paths.model.Link;
import edu.ntnu.idatt2001.group_30.paths.model.Passage;
import edu.ntnu.idatt2001.group_30.paths.model.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class StoryTest {

    @Nested
    class An_instantiated_Story_object {

        @Test
        void can_be_constructed() {
            Story story = new Story(
                "Berachad chronicles",
                new Passage("Cast magic spell", "You cast a magic spell on Berachad")
            );
            assertEquals("Berachad chronicles", story.getTitle());
        }

        @Test
        void cannot_be_constructed_with_empty_title() {
            Passage passage = new Passage("Attack Berachad", "Do you want to attack Berachad, the monster?");
            /* here the title is completely empty */
            assertThrows(IllegalArgumentException.class, () -> new Story("", passage));
            /* here the title contains some spaces */
            assertThrows(IllegalArgumentException.class, () -> new Story("  ", passage));
        }

        @Test
        void cannot_be_constructed_with_null_passage() {
            assertThrows(IllegalArgumentException.class, () -> new Story("Berachad chronicles", null));
        }
    }

    @Nested
    class A_valid_Story_object {

        @Test
        void can_get_all_passages() {
            Story story = new Story("A story of war and Eilor", new Passage("You see Eilor", "What do you do?"));
            Passage passage = new Passage("Attack Eilor", "You attack Eilor");
            Passage passage2 = new Passage("Befriend Eilor", "You befriend Eilor");
            story.addPassage(passage);
            story.addPassage(passage2);
            assertEquals(3, story.getPassages().size());
        }

        @Test
        void can_get_passage_by_reference() {
            Passage passageInitial = new Passage("Beginning", "Once upon a time...");
            Passage expectedPassage = new Passage("The dark forest", "When entering the dark forest...");
            Story story = new Story("title", passageInitial);

            story.addPassage(expectedPassage);
            Link linkForForestPassage = new Link(expectedPassage.getTitle(), expectedPassage.getTitle());
            Passage actualPassage = story.getPassage(linkForForestPassage);

            assertEquals(expectedPassage, actualPassage);
        }

        @Test
        void can_get_opening_passage() {
            Passage passage = new Passage("You see Eilor", "Once upon a time...");
            Story story = new Story("Eilor, the monster", passage);
            assertEquals(passage, story.getOpeningPassage());
        }
    }

    @Nested
    class A_valid_Story_object_with_passage_data {

        Story story;

        @BeforeEach
        void setup() {
            Passage openingPassage = new Passage("A New Beginning", "You are in a dark forest...");
            story = new Story("The Story of Callum Java", openingPassage);
            Passage passageOne = new Passage("Observing the master", "You observe the master...");
            story.addPassage(passageOne);
        }

        @Test
        void standalone_passage_can_be_removed() {
            // remove passageOne from the story which is not linked to any other passage
            boolean return_value = this.story.removePassage(new Link("Observing the master", "Observing the master"));
            assertTrue(return_value);
        }

        @Test
        void story_with_passage_with_multiple_links_cannot_be_removed() {
            Passage passageTwo = new Passage("passageTwo", "passageTwo");
            // add a link to passageOne from passageTwo
            // this means that passageOne should not be removed
            passageTwo.addLink(new Link("Observing the master", "You observe the master..."));
            story.addPassage(passageTwo);
            boolean return_value =
                this.story.removePassage(new Link("Observing the master", "You observe the master..."));
            assertFalse(return_value);
        }

        @Test
        void story_with_with_no_broken_links_returns_empty_list() {
            assertEquals(0, this.story.getBrokenLinks().size());
        }

        @Test
        void story_with_broken_links_returns_list_of_broken_links() {
            Passage passageTwo = new Passage("passageTwo", "passageTwo");
            passageTwo.addLink(new Link("BROKEN LINK", "BROKEN LINK"));
            story.addPassage(passageTwo);
            assertEquals(1, this.story.getBrokenLinks().size());
        }
    }
}
