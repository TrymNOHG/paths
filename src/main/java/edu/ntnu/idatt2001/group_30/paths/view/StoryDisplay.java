package edu.ntnu.idatt2001.group_30.paths.view;

import edu.ntnu.idatt2001.group_30.paths.model.Story;
import edu.ntnu.idatt2001.group_30.paths.model.filehandling.FileHandler;
import edu.ntnu.idatt2001.group_30.paths.model.filehandling.StoryFileHandler;
import edu.ntnu.idatt2001.group_30.paths.view.pane.PaneSpacing;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a depiction of a story. This story can come in both the form of a Story file as well as just a
 * story object. From there, a builder, {@link Builder}, allows the user to choose what information concerning the story
 * file and/or story. The file and the story's information will be instantiated as text and displayed in the GUI.
 * Additionally, this class extends the {@link VBox} class, which means an object of StoryDisplay can be treated the
 * same as a VBox.
 *
 * @author Trym Hamer Gudvangen
 */
public class StoryDisplay extends VBox{

    /**
     * This constructor represents a display filled with a given story's information. It utilizes a builder object,
     * {@link Builder#Builder(Story)} taken as a parameter, in order to attain all the nodes which need to be attached
     * to the StoryDisplay object.
     */
    public StoryDisplay(Builder builder) {
        super(PaneSpacing.createVBoxWithSpacing(builder.hBoxList));
        this.autosize();
    }

    /**
     * This static class employs the builder design pattern. It allows for a person to construct a display by adding
     * on the desired parts of information. It, therefore, includes multiple methods for adding story information.
     */
    public static class Builder{
        private String storyFileName;

        private String location;

        private Story story;

        private final List<HBox> hBoxList;

        /**
         * This is the constructor for the builder class which takes in a file as input. With this constructor,
         * information not only surrounding the story in the file but the file itself can be extracted. This information
         * is set during the initialization phase using the method {@link #setStoryInformation()}.
         * @param fileName                 The file name containing the story, represented as String.
         * @throws IOException              This exception is thrown if the file stated is invalid.
         * @throws InstantiationException   This exception is thrown if the file being read is
         */
        public Builder(String fileName) throws IOException, InstantiationException {
            this.storyFileName = fileName;
            this.hBoxList = new ArrayList<>();
            setStoryInformation();
        }

        /**
         * This is the constructor for the builder class which takes in an Story object as input. With this constructor,
         * information only information surrounding the given story can be used.
         * @param story  The story whose information will be displayed, represented using an Story object.
         */
        public Builder(Story story) {
            this.story = story;
            this.hBoxList = new ArrayList<>();
        }

        /**
         * This method adds an HBox containing the story name to the hBoxList.
         * @return The builder itself, represented as a Builder object.
         */
        public Builder addStoryName(){
            Text storyName = new Text(story.getTitle());
            storyName.setUnderline(true);
            this.hBoxList.add(PaneSpacing.createHBoxWithSpacing(storyName));
            return this;
        }

        /**
         * This method creates the file name information of the story file.
         * @return A horizontal layout with the file name information, represented using an HBox object
         */
        public Builder addFileNameInfo(){
            if(storyFileName == null) return this;
            Text description = new Text("File Name: ");
            Text fileName = new Text(this.storyFileName);
            this.hBoxList.add(PaneSpacing.createHBoxWithSpacing(description, fileName));
            return this;
        }

        /**
         * This method creates the time saved information of the story file.
         * @return A horizontal layout with the file save information, represented using an HBox object
         */
        public Builder addTimeSavedInfo() throws IOException {
            if(storyFileName == null) return this;
            Text savedText = new Text("Saved: ");
            FileTime fileTime = Files.getLastModifiedTime(Path.of(FileHandler.getFileSourcePath(this.storyFileName)));
            Text timeSaved = new Text(fileTime.toString().substring(0, 10));
            this.hBoxList.add(PaneSpacing.createHBoxWithSpacing(savedText, timeSaved));
            return this;
        }

        /**
         * This method creates the file location information of the story file.
         * @return A horizontal layout with the file location information,represented using an HBox object
         */
        public Builder addFileLocationInfo(){
            if(storyFileName == null) return this;
            Text locationText = new Text("Location: ");
            Text pathSaved = new Text(this.location);
            pathSaved.setWrappingWidth(200);
            this.hBoxList.add(PaneSpacing.createHBoxWithSpacing(locationText, pathSaved));
            return this;
        }

        /**
         * This method retrieves the location and story information from the story file.
         * @throws IOException This exception is thrown if the file read is invalid.
         */
        private void setStoryInformation() throws IOException, InstantiationException{
            this.location = String.valueOf(FileHandler.createFile(this.storyFileName).toPath());
            this.story = new StoryFileHandler().readStoryFromFile(this.storyFileName);
        }

        /**
         * This method finalizes all the information that has been added and creates a new StoryDisplay object,
         * {@link StoryDisplay#StoryDisplay(Builder)}.
         * @return  A story display with all the information added, represented as a VBox.
         */
        public VBox build(){
            return new StoryDisplay(this);
        }
    }

}