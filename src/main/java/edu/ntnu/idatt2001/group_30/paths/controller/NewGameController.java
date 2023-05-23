package edu.ntnu.idatt2001.group_30.paths.controller;

import static edu.ntnu.idatt2001.group_30.paths.PathsSingleton.INSTANCE;

import edu.ntnu.idatt2001.group_30.paths.model.filehandling.StoryFileReader;
import edu.ntnu.idatt2001.group_30.paths.view.views.NewStoryView;
import edu.ntnu.idatt2001.group_30.paths.view.views.PlaythroughView;
import java.io.File;
import java.io.IOException;

/**
 * This class is used to control the NewGameView.
 *
 * @author Trym Hamer Gudvangen
 */
public class NewGameController extends Controller {

    /**
     * Creates a new NewGameController.
     */
    public NewGameController() {
        super(PlaythroughView.class, NewStoryView.class);
    }

    /**
     * Sets the story to the storyFile.
     * @param storyFile The story to set, as a File.
     */
    public void setStory(File storyFile) {
        StoryFileReader storyFileReader = new StoryFileReader();
        try {
            INSTANCE.setStory(storyFileReader.parse(storyFile));
            INSTANCE.setStoryFile(storyFile);
        } catch (IOException | InstantiationException ex) {
            throw new RuntimeException(ex);
        }
    }
}
