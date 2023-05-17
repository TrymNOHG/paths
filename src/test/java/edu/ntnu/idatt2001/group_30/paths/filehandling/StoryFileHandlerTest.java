package edu.ntnu.idatt2001.group_30.paths.filehandling;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idatt2001.group_30.paths.exceptions.CorruptFileException;
import edu.ntnu.idatt2001.group_30.paths.exceptions.CorruptLinkException;
import edu.ntnu.idatt2001.group_30.paths.model.Link;
import edu.ntnu.idatt2001.group_30.paths.model.Passage;
import edu.ntnu.idatt2001.group_30.paths.model.Story;
import edu.ntnu.idatt2001.group_30.paths.model.actions.Action;
import edu.ntnu.idatt2001.group_30.paths.model.actions.GoldAction;
import edu.ntnu.idatt2001.group_30.paths.model.actions.HealthAction;
import edu.ntnu.idatt2001.group_30.paths.model.actions.InventoryAction;
import edu.ntnu.idatt2001.group_30.paths.model.actions.ScoreAction;
import edu.ntnu.idatt2001.group_30.paths.model.filehandling.FileHandler;
import edu.ntnu.idatt2001.group_30.paths.model.filehandling.StoryFileHandler;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StoryFileHandlerTest {

    @BeforeAll
    static void setFileHandlerPath() {
        Path defaultPath = FileSystems.getDefault().getPath("src", "test", "resources", "storytestfiles");
        FileHandler.changeDefaultPath(defaultPath);
    }

    StoryFileHandler storyFileHandler = new StoryFileHandler();

    public File getValidFile(String fileName) {
        return FileHandler.createFile(fileName);
    }

    static Story validStory() {
        Story story = new Story("The Hobbit", new Passage("Beginning", "Once upon a time..."));
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
    public class A_StoryFile_is_valid_if {

        @ParameterizedTest(name = "{index}. File name: {0}")
        @ValueSource(strings = { "Winnie the Pooh", "L.O.T.R", "The-Bible", "Story123" })
        void a_file_has_a_valid_name(String fileName) {
            Story story = validStory();

            try {
                storyFileHandler.createStoryFile(story, fileName);
            } catch (Exception e) {
                if (!e.getMessage().equals("You cannot overwrite a pre-existing story file")) {
                    System.out.println(e.getMessage());
                    fail("An exception was thrown when it shouldn't have.");
                }
            }

            File expectedFileCreated = getValidFile(fileName);

            Assertions.assertTrue(expectedFileCreated.isFile());
            expectedFileCreated.delete();
        }

        @ParameterizedTest(name = "{index}. File name: {0}")
        @ValueSource(strings = { "Winnie the Pooh", "L.O.T.R", "The-Bible", "Story123" })
        void files_created_can_be_accessed_and_read(String fileName) {
            Story story = validStory();

            try {
                storyFileHandler.createStoryFile(story, fileName);
            } catch (Exception e) {
                fail("An exception was thrown when it shouldn't have. " + e.getMessage());
            }

            File expectedFileCreated = getValidFile(fileName);

            Assertions.assertTrue(expectedFileCreated.canRead());
            expectedFileCreated.delete();
        }

        @ParameterizedTest(name = "{index}. File name: {0}")
        @ValueSource(strings = { "Winnie the Pooh", "L.O.T.R", "The-Bible", "Story123" })
        void the_pathing_is_correctly_set(String fileName) {
            Story story = validStory();
            boolean fileDoesNotExistAtStart = !getValidFile(fileName).exists();

            try {
                storyFileHandler.createStoryFile(story, fileName);
            } catch (Exception e) {
                fail("An exception was thrown when it shouldn't have.");
            }

            File expectedFileCreated = getValidFile(fileName);
            boolean fileDoesExistAfterWrite = expectedFileCreated.exists();

            //Then/Assert
            Assertions.assertTrue(fileDoesNotExistAtStart);
            Assertions.assertTrue(fileDoesExistAfterWrite);
            expectedFileCreated.delete();
        }

        @Test
        void it_cannot_create_new_file_with_preexisting_file_name() {
            Story story = validStory();
            String fileName = "Bones";

            File preexistingFile = getValidFile(fileName);
            if (getValidFile(fileName).isFile()) {
                Assertions.assertThrows(
                    IllegalArgumentException.class,
                    () -> storyFileHandler.createStoryFile(story, fileName)
                );
            } else fail("The file check for doesn't exist, so this test is invalid");
        }
    }

    @Nested
    public class A_StoryFile_properly_writes_a_story_to_new_file_if_it {

        @ParameterizedTest(name = "{index}. File name: {0}")
        @ValueSource(strings = { "Winnie the Pooh", "L.O.T.R", "The-Bible", "Story123" })
        void saves_the_story_title_correctly(String fileName) throws IOException, InstantiationException {
            Story story = validStory();
            String expectedTitle = story.getTitle();

            storyFileHandler.createStoryFile(story, fileName);
            Story storyReadFromFile = storyFileHandler.readStoryFromFile(fileName);
            String actualTitle = storyReadFromFile.getTitle();

            Assertions.assertEquals(expectedTitle, actualTitle);

            File file = getValidFile(fileName);
            file.delete();
        }

        @ParameterizedTest(name = "{index}. File name: {0}")
        @ValueSource(strings = { "Winnie the Pooh", "L.O.T.R", "The-Bible", "Story123" })
        void saves_the_opening_passage_after_title(String fileName) throws IOException, InstantiationException {
            Story story = validStory();
            Passage expectedOpeningPassage = story.getOpeningPassage();

            storyFileHandler.createStoryFile(story, fileName);
            Story storyReadFromFile = storyFileHandler.readStoryFromFile(fileName);
            Passage actualOpeningPassage = storyReadFromFile.getOpeningPassage();

            Assertions.assertEquals(expectedOpeningPassage, actualOpeningPassage);

            File file = getValidFile(fileName);
            file.delete();
        }

        @ParameterizedTest(name = "{index}. File name: {0}")
        @ValueSource(strings = { "Winnie the Pooh", "L.O.T.R", "The-Bible", "Story123" })
        void saves_all_the_links_of_passage_correctly(String fileName) throws IOException, InstantiationException {
            Story story = validStory();
            List<Link> expectedOpeningPassageLinks = story.getOpeningPassage().getLinks();

            storyFileHandler.createStoryFile(story, fileName);
            Story storyReadFromFile = storyFileHandler.readStoryFromFile(fileName);
            List<Link> actualOpeningPassageLinks = storyReadFromFile.getOpeningPassage().getLinks();

            Assertions.assertEquals(expectedOpeningPassageLinks, actualOpeningPassageLinks);

            File file = getValidFile(fileName);
            file.delete();
        }

        @ParameterizedTest(name = "{index}. File name: {0}")
        @ValueSource(strings = { "Winnie the Pooh", "L.O.T.R", "The-Bible", "Story123" })
        void saves_all_the_actions_of_links_correctly(String fileName) throws IOException, InstantiationException {
            Story story = validStory();
            List<Action<?>> expectedOpeningPassageActions = story.getOpeningPassage().getLinks().get(0).getActions();

            storyFileHandler.createStoryFile(story, fileName);
            Story storyReadFromFile = storyFileHandler.readStoryFromFile(fileName);
            List<Action<?>> actualOpeningPassageActions = storyReadFromFile
                .getOpeningPassage()
                .getLinks()
                .get(0)
                .getActions();

            Assertions.assertEquals(expectedOpeningPassageActions, actualOpeningPassageActions);

            File file = getValidFile(fileName);
            file.delete();
        }
    }

    @Nested
    public class A_StoryFile_properly_reads_a_story_if_it {

        @Test
        void constructs_a_Story_correctly_when_read() throws IOException, InstantiationException {
            Story expectedStory = validStory();

            Story actualStory = storyFileHandler.readStoryFromFile("The Hobbit");

            assertEquals(expectedStory, actualStory);
        }
    }

    @Nested
    public class A_StoryFile_with_invalid_information_such_as {

        @Test
        void a_null_story_when_creating_new_file_will_throw_NullPointerException() {
            Story story = null;

            Assertions.assertThrows(
                NullPointerException.class,
                () -> {
                    storyFileHandler.createStoryFile(story, "Null story test");
                }
            );
        }

        @Test
        void a_null_file_name_when_creating_new_file_will_throw_NullPointerException() {
            Story story = validStory();

            Assertions.assertThrows(
                NullPointerException.class,
                () -> {
                    storyFileHandler.createStoryFile(story, null);
                }
            );
        }

        @Test
        void a_null_file_name_when_reading_file_will_throw_NullPointerException() {
            Assertions.assertThrows(
                NullPointerException.class,
                () -> {
                    Story story = storyFileHandler.readStoryFromFile((String) null);
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
                    Story actualStory = storyFileHandler.readStoryFromFile("Corrupt Link File");
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
                    Story actualStory = storyFileHandler.readStoryFromFile("Corrupt .paths Format");
                }
            );
        }

        @Test
        void not_existing_throws_IllegalArgumentException() {
            Story expectedStory = validStory();

            Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Story actualStory = storyFileHandler.readStoryFromFile("File that does not exist");
                }
            );
        }

        @Test
        void action_class_throws_InstantiationException() {
            Story expectedStory = validStory();

            Assertions.assertThrows(
                InstantiationException.class,
                () -> {
                    Story actualStory = storyFileHandler.readStoryFromFile("Corrupt Action Class");
                }
            );
        }

        @Test
        void corrupt_action_format_throws_CorruptLinkException() {
            Story expectedStory = validStory();

            Assertions.assertThrows(
                CorruptLinkException.class,
                () -> {
                    Story actualStory = storyFileHandler.readStoryFromFile("Corrupt Action");
                }
            );
        }

        @Test
        void valid_action_class_but_invalid_value_throws_IllegalArgumentException() {
            Story expectedStory = validStory();

            Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Story actualStory = storyFileHandler.readStoryFromFile("Corrupt Action Value");
                }
            );
        }
    }
}
