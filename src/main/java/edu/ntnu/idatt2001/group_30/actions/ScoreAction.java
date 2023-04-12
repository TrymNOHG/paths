package edu.ntnu.idatt2001.group_30.actions;

import edu.ntnu.idatt2001.group_30.Player;

import java.util.Objects;

/**
 * This class represents a change in the score attribute for any player.
 *
 * @author Trym Hamer Gudvangen
 */
public class ScoreAction implements Action<Integer> {

    private final int points;

    /**
     * The constructor defines how many points the player's score will be changed by.
     * @param points    Amount of points, given as an int.
     */
    public ScoreAction(int points) {
        this.points = points;
        //TODO: Add exception?
    }

    /**
     * {@inheritDoc} score.
     * @param player The player, given as a Player object.
     */
    @Override
    public void execute(Player player) {
        Objects.requireNonNull(player);
        player.addScore(this.points);
    }

    /**
     * This method retrieves the point value.
     * @return  Point value, given as an int.
     */
    @Override
    public Integer getActionValue() {
        return this.points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScoreAction that)) return false;

        return points == that.points;
    }

    @Override
    public int hashCode() {
        return points;
    }
}
