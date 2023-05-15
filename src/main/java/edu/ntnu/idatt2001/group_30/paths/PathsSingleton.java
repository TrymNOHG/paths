package edu.ntnu.idatt2001.group_30.paths;

import edu.ntnu.idatt2001.group_30.paths.model.Player;
import edu.ntnu.idatt2001.group_30.paths.model.Story;
import edu.ntnu.idatt2001.group_30.paths.model.goals.*;

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
    private Player player = new Player("Default", 100, 100, 100);
    private boolean passageMoving = false;
    private HealthGoal healthGoal;
    private ScoreGoal scoreGoal;
    private InventoryGoal inventoryGoal;
    private GoldGoal goldGoal;

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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void changeGoal(Goal<?> newGoal) {
        setGoal(GoalType.getGoalType(newGoal.getClass().getSimpleName()), newGoal);
    }

    public <T> void setGoal(GoalType goalType, Goal<?> goal) {
        switch (goalType) {
            case HEALTH_GOAL -> healthGoal = (HealthGoal) goal;
            case SCORE_GOAL -> scoreGoal = (ScoreGoal) goal;
            case INVENTORY_GOAL -> inventoryGoal = (InventoryGoal) goal;
            case GOLD_GOAL -> goldGoal = (GoldGoal) goal;
            default -> throw new IllegalArgumentException("Unsupported goal type: " + goalType);
        }
    }

    public HealthGoal getHealthGoal() {
        return healthGoal;
    }

    public ScoreGoal getScoreGoal() {
        return scoreGoal;
    }

    public InventoryGoal getInventoryGoal() {
        return inventoryGoal;
    }

    public GoldGoal getGoldGoal() {
        return goldGoal;
    }

    public <T> Goal<?> getGoal(GoalType goalType) {
        return switch (goalType) {
            case HEALTH_GOAL -> healthGoal;
            case SCORE_GOAL -> scoreGoal;
            case INVENTORY_GOAL -> inventoryGoal;
            case GOLD_GOAL -> goldGoal;
        };
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
