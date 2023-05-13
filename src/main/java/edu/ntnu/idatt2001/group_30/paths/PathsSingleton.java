package edu.ntnu.idatt2001.group_30.paths;

import edu.ntnu.idatt2001.group_30.paths.model.Story;

/**
 * This enumeration is constructed using the singleton design pattern. The implementation of this design pattern
 * restricts the amount of instances of the enumeration to one. This is essential for the enumeration's purpose.
 * The enum contains all the data that needs to be transmitted between controllers.
 *
 * @author Trym Hamer Gudvangen
 */
public enum PathsSingleton {
    INSTANCE;

    private Story story;
    private boolean passageMoving = false;

    /**
     * This method gets the current selected story.
     * @return  Current selected story, given as a Story object.
     */
    public Story getStory() {
        return story;
    }

    /**
     * This method sets story being played.
     * @param story Story being played, given as a Story object.
     */
    public void setStory(Story story) {
        this.story = story;
    }

    /**
     * This method checks whether a passage is currently being moved.
     * @return  The passageMoving status, given as a boolean. {@code true} if the passage is moving, else {@code false}
     */
    public boolean isPassageMoving() {
        return passageMoving;
    }

    /**
     * This method sets the status of whether a passage is currently being moved or not.
     */
    public void setPassageMoving(boolean passageMoving) {
        this.passageMoving = passageMoving;
    }
}