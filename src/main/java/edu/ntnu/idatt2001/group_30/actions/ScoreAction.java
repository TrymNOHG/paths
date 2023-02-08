package edu.ntnu.idatt2001.group_30.actions;

import edu.ntnu.idatt2001.group_30.Player;
import edu.ntnu.idatt2001.group_30.actions.Action;

/**
 * This class represents a change in the score attribute for any player.
 *
 * @author Trym Hamer Gudvangen
 */
public class ScoreAction implements Action {

    private final int points;

    /**
     * The constructor defines how many points the player's score will be changed by.
     * @param points    Amount of points, given as an int.
     */
    public ScoreAction(int points) {
        this.points = points;
    }

    /**
     * {@inheritDoc} score.
     * @param player The player, given as a Player object.
     */
    @Override
    public void execute(Player player) {
        player.addScore(this.points);
    }
}
