package edu.ntnu.idatt2001.group_30.paths.model.filehandling;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idatt2001.group_30.paths.model.Link;
import edu.ntnu.idatt2001.group_30.paths.model.Passage;
import edu.ntnu.idatt2001.group_30.paths.model.Story;
import edu.ntnu.idatt2001.group_30.paths.model.actions.*;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class StoryFileWriterImplTest {

    final StoryFileWriter storyFileWriter = new StoryFileWriter();
    final StoryFileReader storyFileReader = new StoryFileReader();

    @BeforeAll
    static void setFileHandlerPath() {
        Path defaultPath = FileSystems.getDefault().getPath("src", "test", "resources", "storytestfiles");
        FileHandler.changeDefaultPath(defaultPath);
    }

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
                storyFileWriter.create(story, fileName);
            } catch (Exception e) {
                if (
                    !(
                        e.getMessage().equals("You cannot overwrite a pre-existing story file") &&
                        e instanceof FileAlreadyExistsException
                    )
                ) {
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
                storyFileWriter.create(story, fileName);
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
                storyFileWriter.create(story, fileName);
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
                    FileAlreadyExistsException.class,
                    () -> storyFileWriter.create(story, fileName)
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

            storyFileWriter.create(story, fileName);
            Story storyReadFromFile = storyFileReader.parse(fileName);
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

            storyFileWriter.create(story, fileName);
            Story storyReadFromFile = storyFileReader.parse(fileName);
            Passage actualOpeningPassage = storyReadFromFile.getOpeningPassage();
            System.out.println(actualOpeningPassage.getTitle() + actualOpeningPassage.getContent());
            System.out.println(expectedOpeningPassage.getTitle() + expectedOpeningPassage.getContent());

            Assertions.assertEquals(expectedOpeningPassage, actualOpeningPassage);

            File file = getValidFile(fileName);
            file.delete();
        }

        @ParameterizedTest(name = "{index}. File name: {0}")
        @ValueSource(strings = { "Winnie the Pooh", "L.O.T.R", "The-Bible", "Story123" })
        void saves_all_the_links_of_passage_correctly(String fileName) throws IOException, InstantiationException {
            Story story = validStory();
            List<Link> expectedOpeningPassageLinks = story.getOpeningPassage().getLinks();

            storyFileWriter.create(story, fileName);
            Story storyReadFromFile = storyFileReader.parse(fileName);
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

            storyFileWriter.create(story, fileName);
            Story storyReadFromFile = storyFileReader.parse(fileName);
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
    public class A_StoryFile_with_invalid_information_such_as {

        @Test
        void a_null_story_when_creating_new_file_will_throw_NullPointerException() {
            Story story = null;

            Assertions.assertThrows(
                NullPointerException.class,
                () -> {
                    storyFileWriter.create(story, "Null story test");
                }
            );
        }

        @Test
        void a_null_file_name_when_creating_new_file_will_throw_NullPointerException() {
            Story story = validStory();

            Assertions.assertThrows(
                NullPointerException.class,
                () -> {
                    storyFileWriter.create(story, null);
                }
            );
        }
    }
}
