package edu.ntnu.idatt2001.group_30.paths;

/**
 * This enumeration is constructed using the singleton design pattern. The implementation of this design pattern
 * restricts the amount of instances of the enumeration to one. This is essential for the enumeration's purpose.
 * The enum contains all the data that needs to be transmitted between controllers.
 *
 * @author Trym Hamer Gudvangen
 */
public enum PathsSingleton {
    INSTANCE;

    private boolean passageMoving = false;

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