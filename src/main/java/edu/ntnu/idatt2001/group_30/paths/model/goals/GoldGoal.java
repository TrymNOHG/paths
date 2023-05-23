package edu.ntnu.idatt2001.group_30.paths.model.goals;

import edu.ntnu.idatt2001.group_30.paths.model.Player;
import java.util.Objects;

/**
 * This class represents a minimum gold threshold.
 *
 * @author Trym Hamer Gudvangen, Nicolai H. Brand
 */
public class GoldGoal implements Goal<Integer> {

    private final int minimumGold;

    /**
     * The constructor defines the minimum amount of gold a given player can have.
     * @param minimumGold    Minimum amount of gold, given as an int.
     */
    public GoldGoal(int minimumGold) {
        this.minimumGold = minimumGold;
    }

    /**
     * {@inheritDoc} gold.
     * @param player    The player to be checked, given as a Player object.
     * @return          Status of player, {@code true} if the player has enough gold, else {@code false}.
     */
    @Override
    public boolean isFulfilled(Player player) {
        Objects.requireNonNull(player);
        return player.getGold() >= this.minimumGold;
    }

    /**
     * This method retrieves the goal value.
     * @return  The minimum gold, given as an Integer.
     */
    @Override
    public Integer getGoalValue() {
        return this.minimumGold;
    }

    /**
     * String representation of the GoldGoal object.
     * @return  String representation of the GoldGoal object.
     */
    @Override
    public String toString() {
        return "Need to collect " + this.minimumGold + " gold.";
    }
}
