package edu.ntnu.idatt2001.group_30.paths.model.goals;

import edu.ntnu.idatt2001.group_30.paths.model.Player;
import java.util.Objects;

/**
 * This class represents a minimum goal threshold.
 *
 * @author Trym Hamer Gudvangen, Nicolai H. Brand.
 */
public class ScoreGoal implements Goal<Integer> {

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
        Objects.requireNonNull(player);
        return player.getScore() >= this.minimumPoints;
    }

    /**
     * This method retrieves the goal value.
     * @return Minimum points, given as an Integer.
     */
    @Override
    public Integer getGoalValue() {
        return this.minimumPoints;
    }

    /**
     * String representation of the ScoreGoal object.
     * @return  String representation of the ScoreGoal object.
     */
    @Override
    public String toString() {
        return "Need to have at least " + this.minimumPoints + " points.";
    }
}
