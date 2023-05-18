package edu.ntnu.idatt2001.group_30.paths.controller;

import static edu.ntnu.idatt2001.group_30.paths.PathsSingleton.INSTANCE;

import edu.ntnu.idatt2001.group_30.paths.model.filehandling.StoryFileHandler;
import edu.ntnu.idatt2001.group_30.paths.view.views.PlaythroughView;
import java.io.File;
import java.io.IOException;

public class NewGameController extends Controller {

    public NewGameController() {
        super(PlaythroughView.class);
    }

    public void setStory(File storyFile) {
        StoryFileHandler storyFileHandler = new StoryFileHandler();
        try {
            INSTANCE.setStory(storyFileHandler.readStoryFromFile(storyFile));
        } catch (IOException | InstantiationException ex) {
            throw new RuntimeException(ex);
        }
    }
}
