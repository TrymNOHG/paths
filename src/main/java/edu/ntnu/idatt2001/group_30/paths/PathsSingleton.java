package edu.ntnu.idatt2001.group_30.paths;

import edu.ntnu.idatt2001.group_30.paths.model.Player;
import edu.ntnu.idatt2001.group_30.paths.model.Story;
import edu.ntnu.idatt2001.group_30.paths.model.goals.*;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.scene.image.ImageView;

/**
 * This enumeration is constructed using the singleton design pattern. The implementation of this design pattern
 * restricts the amount of instances of the enumeration to one. This is essential for the enumeration's purpose.
 * The enum contains all the data that needs to be transmitted between controllers.
 *
 * @author Trym Hamer Gudvangen, Nicolai H. Brand.
 */
public enum PathsSingleton {
    INSTANCE;

    private Story story;
    private File storyFile;
    private Player player = new Player("Default", 100, 100, 100);
    private boolean passageMoving = false;
    private HealthGoal healthGoal;
    private ScoreGoal scoreGoal;
    private InventoryGoal inventoryGoal;
    private GoldGoal goldGoal;
    private ImageView characterImageView;

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
     * This method retrieves the file to the story.
     * @return  The file of the story, given as a File object.
     */
    public File getStoryFile() {
        return storyFile;
    }

    /**
     * This method changes the file of the story.
     * @param storyFile New file, given as a File object.
     */
    public void setStoryFile(File storyFile) {
        this.storyFile = storyFile;
    }

    /**
     * This method retrieves the player.
     * @return  Current player of game, given as a Player object.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * This method sets the player to a new player.
     * @param player    New player, given as a Player object.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * This method allows a goal to be changed, given the goal type.
     * @param newGoal   New goal, given as a Goal implemented Object
     */
    public void changeGoal(Goal<?> newGoal) {
        setGoal(GoalType.getGoalType(newGoal.getClass().getSimpleName()), newGoal);
    }

    /**
     * This method sets a given goal to a new goal by the goal type.
     * @param goalType  Type of goal, given as a GoalType enum.
     * @param goal      New goal, given as a Goal object.
     * @param <T>       The type of Goal.
     */
    public <T> void setGoal(GoalType goalType, Goal<?> goal) {
        switch (goalType) {
            case HEALTH_GOAL -> healthGoal = (HealthGoal) goal;
            case SCORE_GOAL -> scoreGoal = (ScoreGoal) goal;
            case INVENTORY_GOAL -> inventoryGoal = (InventoryGoal) goal;
            case GOLD_GOAL -> goldGoal = (GoldGoal) goal;
            default -> throw new IllegalArgumentException("Unsupported goal type: " + goalType);
        }
    }

    /**
     * This method retrieves the health goal.
     * @return  Health goal, given as a HealthGoal object.
     */
    public HealthGoal getHealthGoal() {
        return healthGoal;
    }

    /**
     * This method retrieves the score goal.
     * @return  Score goal, given as a ScoreGoal object.
     */
    public ScoreGoal getScoreGoal() {
        return scoreGoal;
    }

    /**
     * This method retrieves the inventory goal.
     * @return  Inventory goal, given as a InventoryGoal object.
     */
    public InventoryGoal getInventoryGoal() {
        return inventoryGoal;
    }

    /**
     * This method retrieves the gold goal.
     * @return  Gold goal, given as a GoldGoal object.
     */
    public GoldGoal getGoldGoal() {
        return goldGoal;
    }

    /**
     * This method gets a goal variable given the GoalType.
     * @param goalType  Type of goal, given as a GoalType enum.
     * @return          The goal variable.
     * @param <T>       Type of goal.
     */
    public <T> Goal<?> getGoal(GoalType goalType) {
        return switch (goalType) {
            case HEALTH_GOAL -> healthGoal;
            case SCORE_GOAL -> scoreGoal;
            case INVENTORY_GOAL -> inventoryGoal;
            case GOLD_GOAL -> goldGoal;
        };
    }

    /**
     * This method retrieves the character load out image.
     * @return  Image of the character created, given as an ImageView object.
     */
    public ImageView getCharacterImageView() {
        return characterImageView;
    }

    /**
     * This method sets the character load out.
     * @param characterImageView    New character image, given as an ImageView object.
     */
    public void setCharacterImageView(ImageView characterImageView) {
        this.characterImageView = characterImageView;
    }

    /**
     * Returns a list of all the non-null goals.
     * @return A list of all the non-null goals, given as a List of Goal objects.
     */
    public List<Goal> getGoals() {
        List<Goal> goals = Stream
            .of(healthGoal, scoreGoal, goldGoal)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

        if (inventoryGoal != null && inventoryGoal.getGoalValue().size() != 0) {
            goals.add(inventoryGoal);
        }
        return goals;
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
