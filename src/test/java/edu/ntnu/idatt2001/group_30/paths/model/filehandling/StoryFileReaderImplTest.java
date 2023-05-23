package edu.ntnu.idatt2001.group_30.paths.model.filehandling;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idatt2001.group_30.paths.exceptions.CorruptFileException;
import edu.ntnu.idatt2001.group_30.paths.exceptions.CorruptLinkException;
import edu.ntnu.idatt2001.group_30.paths.model.Link;
import edu.ntnu.idatt2001.group_30.paths.model.Passage;
import edu.ntnu.idatt2001.group_30.paths.model.Story;
import edu.ntnu.idatt2001.group_30.paths.model.actions.GoldAction;
import edu.ntnu.idatt2001.group_30.paths.model.actions.HealthAction;
import edu.ntnu.idatt2001.group_30.paths.model.actions.InventoryAction;
import edu.ntnu.idatt2001.group_30.paths.model.actions.ScoreAction;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class StoryFileReaderImplTest {

    @BeforeAll
    static void setFileHandlerPath() {
        Path defaultPath = FileSystems.getDefault().getPath("src", "test", "resources", "storytestfiles");
        FileHandler.changeDefaultPath(defaultPath);
    }

    StoryFileReader storyFileReader = new StoryFileReader();

    public File getValidFile(String fileName) {
        return FileHandler.createFile(fileName);
    }

    static Story validStory() {
        Story story = new Story("Lord of the rings", new Passage("Beginning", "Once upon a time..."));
        Passage secondChapter = new Passage("The Great Barrier", "After having completed the arduous...");
        story.addPassage(secondChapter);
        story.getOpeningPassage().addLink(new Link(secondChapter.getTitle(), secondChapter.getTitle()));
        story.getOpeningPassage().getLinks().forEach(link -> link.addAction(new GoldAction(5)));
        story.getOpeningPassage().getLinks().get(0).addAction(new ScoreAction(5));
        story.getOpeningPassage().getLinks().get(0).addAction(new HealthAction(6));
        story.getOpeningPassage().getLinks().get(0).addAction(new InventoryAction("Sword"));
        return story;
    }

    @Nested
    public class A_StoryFile_properly_reads_a_story_if_it {

        @Test
        void constructs_a_Story_correctly_when_read() throws IOException, InstantiationException {
            Story expectedStory = validStory();

            Story actualStory = storyFileReader.parse("Lord of the rings");

            assertEquals(expectedStory, actualStory);
        }
    }

    @Nested
    public class A_StoryFile_with_invalid_information_such_as {

        @Test
        void a_null_file_name_when_reading_file_will_throw_NullPointerException() {
            Assertions.assertThrows(
                NullPointerException.class,
                () -> {
                    Story story = storyFileReader.parse((String) null);
                }
            );
        }

        //TODO: change this actually test the link information
        @Test
        void corrupt_link_information_throws_CorruptLinkException_when_read() {
            Story expectedStory = validStory();

            Assertions.assertThrows(
                CorruptLinkException.class,
                () -> {
                    Story actualStory = storyFileReader.parse("Corrupt Link File");
                    assertNotEquals(expectedStory, actualStory);
                }
            );
        }

        @Test
        void file_with_improper_format_throws_CorruptFileException() {
            Story expectedStory = validStory();

            Assertions.assertThrows(
                CorruptFileException.class,
                () -> {
                    Story actualStory = storyFileReader.parse("Corrupt .paths Format");
                }
            );
        }

        @Test
        void not_existing_throws_IllegalArgumentException() {
            Story expectedStory = validStory();

            Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Story actualStory = storyFileReader.parse("File that does not exist");
                }
            );
        }

        @Test
        void action_class_throws_InstantiationException() {
            Story expectedStory = validStory();

            Assertions.assertThrows(
                InstantiationException.class,
                () -> {
                    Story actualStory = storyFileReader.parse("Corrupt Action Class");
                }
            );
        }

        @Test
        void corrupt_action_format_throws_CorruptLinkException() {
            Story expectedStory = validStory();

            Assertions.assertThrows(
                CorruptLinkException.class,
                () -> {
                    Story actualStory = storyFileReader.parse("Corrupt Action");
                }
            );
        }

        @Test
        void valid_action_class_but_invalid_value_throws_IllegalArgumentException() {
            Story expectedStory = validStory();

            Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Story actualStory = storyFileReader.parse("Corrupt Action Value");
                }
            );
        }
    }
}
