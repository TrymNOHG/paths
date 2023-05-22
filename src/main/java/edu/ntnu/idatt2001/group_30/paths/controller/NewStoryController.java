package edu.ntnu.idatt2001.group_30.paths.controller;

import edu.ntnu.idatt2001.group_30.paths.model.Passage;
import edu.ntnu.idatt2001.group_30.paths.model.Story;
import edu.ntnu.idatt2001.group_30.paths.model.filehandling.StoryFileWriter;
import edu.ntnu.idatt2001.group_30.paths.view.views.NewStoryView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static edu.ntnu.idatt2001.group_30.paths.PathsSingleton.INSTANCE;

public class NewStoryController extends Controller{

    public NewStoryController() {
        super(NewStoryView.class);
    }

    public void addStory(String title, List<Passage> passages) throws IOException {
        Story story = new Story(title, passages.isEmpty() ? null: passages.get(0));
        passages.forEach(story::addPassage);
        INSTANCE.setStory(story);

       saveStory(story);
    }

    public void saveStory(Story story) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./src/main/resources/story-files"));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Paths files", "*.paths")
        );
        File selectedFile = fileChooser.showSaveDialog(null);

        if (selectedFile != null) {
            StoryFileWriter storyFileWriter = new StoryFileWriter();
            storyFileWriter.create(story, selectedFile);
        }
    }
}
