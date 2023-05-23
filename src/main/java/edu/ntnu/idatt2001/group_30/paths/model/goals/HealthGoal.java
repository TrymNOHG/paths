package edu.ntnu.idatt2001.group_30.paths.model.goals;

import edu.ntnu.idatt2001.group_30.paths.model.Player;
import java.util.Objects;

/**
 * This class represents a minimum health threshold.
 *
 * @author Trym Hamer Gudvangen, Nicolai H. Brand.
 */
public class HealthGoal implements Goal<Integer> {

    private final int minimumHealth;

    /**
     * The constructor defines the minimum amount of health a given player can have.
     * @param minimumHealth                 Minimum amount of health, given as an int.
     * @throws IllegalArgumentException     This exception is thrown if minimum health less than 0.
     */
    public HealthGoal(int minimumHealth) throws IllegalArgumentException {
        if (minimumHealth < 0) throw new IllegalArgumentException("Minimum health cannot be less than 0");
        this.minimumHealth = minimumHealth;
    }

    /**
     * {@inheritDoc} health.
     * @param player    The player to be checked, given as a Player object.
     * @return          Status of player, {@code true} if the player has enough health, else {@code false}.
     */
    @Override
    public boolean isFulfilled(Player player) {
        Objects.requireNonNull(player);
        return player.getHealth() >= this.minimumHealth;
    }

    /**
     * This method retrieves the goal value.
     * @return The minimum health goal, given as an Integer.
     */
    @Override
    public Integer getGoalValue() {
        return this.minimumHealth;
    }

    /**
     * String representation of the HealthGoal object.
     * @return  String representation of the HealthGoal object.
     */
    @Override
    public String toString() {
        return "Need to have at least " + this.minimumHealth + " health.";
    }
}
