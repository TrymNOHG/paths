package edu.ntnu.idatt2001.group_30.paths.model;

import edu.ntnu.idatt2001.group_30.paths.model.goals.Goal;

import java.util.List;

/**
 * This class represents the facade of a game of paths.
 * This class connects a Player to a Story and has useful methods to start and maneuver through a game.
 *
 * @author Nicolai H. Brand, Trym Hamer Gudvangen
 */
public class Game {
    private final Player player;
    private final Story story;
    private final List<Goal> goals;

    /**
     * This method constructs a Game object with the given parameters.
     * @param player                    The player who will be playing game.
     * @param story                     The story for the game that the player will play through.
     * @param goals                     A list of goals that determines the desired goals of the game:
     * @throws IllegalArgumentException This exception is thrown if any argument is null.
     */
    public Game(Player player, Story story, List<Goal> goals) throws IllegalArgumentException{
        if (player == null || story == null || goals == null) {
            throw new IllegalArgumentException("Player, story, and goals cannot be null");
        }
        this.player = player;
        this.story = story;
        this.goals = goals;
    }

    /**
     * This method starts a game.
     * @return  the first passage of the story.
     */
    public Passage begin() {
        return this.story.getOpeningPassage();
        //TODO: Should we make sure the game can not begin more than one time?
        //      It depends whether or not the end user itself has a way to call begin(), or if this is something
        //      exclusively done internally. In that case, unit-tests to ensure this method is only called once may
        //      suffice.
    }

    /**
     * This method takes in a link and returns the corresponding passage of the story.
     * @param link  a link to a passage in the story.
     * @return      the corresponding passage for the link.
     */
    public Passage go(Link link) {
        return this.story.getPassage(link);
    }

    /**
     * This method returns the player for the game.
     * @return  the player object for the game.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * This method returns the story for the game.
     * @return  the story object for the game
     */
    public Story getStory() {
        return story;
    }

    /**
     * This method returns all the goals of the game.
     * @return  all the goals of the game as a List{@code <Goal>}
     */
    public List<Goal> getGoals() {
        return goals;
    }
}


