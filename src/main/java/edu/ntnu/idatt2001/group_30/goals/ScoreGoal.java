package edu.ntnu.idatt2001.group_30.goals;

import edu.ntnu.idatt2001.group_30.Player;
import edu.ntnu.idatt2001.group_30.goals.Goal;

/**
 * This class represents a minimum goal threshold.
 *
 * @author Trym Hamer Gudvangen
 */
public class ScoreGoal implements Goal {

    private final int minimumPoints;

    /**
     * The constructor defines the minimum amount of points a given player can have.
     * @param minimumPoints    Minimum amount of points, given as an int.
     */
    public ScoreGoal(int minimumPoints) {
        this.minimumPoints = minimumPoints;
        //TODO: Add exception?
    }

    /**
     * {@inheritDoc} score.
     * @param player    The player to be checked, given as a Player object.
     * @return          Status of player, {@code true} if the player has enough points, else {@code false}.
     */
    @Override
    public boolean isFulfilled(Player player) {
        return player.getScore() >= this.minimumPoints;
    }
}
