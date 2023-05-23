package edu.ntnu.idatt2001.group_30.paths.controller;

import static edu.ntnu.idatt2001.group_30.paths.PathsSingleton.INSTANCE;

import edu.ntnu.idatt2001.group_30.paths.model.Passage;
import edu.ntnu.idatt2001.group_30.paths.model.Story;
import edu.ntnu.idatt2001.group_30.paths.model.filehandling.StoryFileWriter;
import edu.ntnu.idatt2001.group_30.paths.view.views.NewStoryView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javafx.stage.FileChooser;

/**
 * This class is used to control the NewStoryView.
 *
 * @author Trym Hamer Gudvangen
 */
public class NewStoryController extends Controller {

    /**
     * Creates a new NewStoryController.
     */
    public NewStoryController() {
        super(NewStoryView.class);
    }

    /**
     * Adds a story to the PathsSingleton.
     * @param title        The title of the story, as a String.
     * @param passages     The passages of the story, as a List of Passages.
     * @throws IOException If the story could not be saved to file system.
     */
    public void addStory(String title, List<Passage> passages) throws IOException {
        Story story = new Story(title, passages.isEmpty() ? null : passages.get(0));
        passages.forEach(story::addPassage);
        INSTANCE.setStory(story);

        saveStory(story);
    }

    /**
     * This method saves the story to the file system.
     * @param story         The story to save, as a Story.
     * @throws IOException  If the story could not be saved to file system.
     */
    public void saveStory(Story story) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./src/main/resources/story-files"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Paths files", "*.paths"));
        File selectedFile = fileChooser.showSaveDialog(null);

        if (selectedFile != null) {
            StoryFileWriter storyFileWriter = new StoryFileWriter();
            storyFileWriter.create(story, selectedFile);
        } else {
            throw new FileNotFoundException("File was not saved to file system");
        }

        INSTANCE.setStoryFile(selectedFile);
    }
}
