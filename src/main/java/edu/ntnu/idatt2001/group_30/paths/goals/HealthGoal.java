package edu.ntnu.idatt2001.group_30.paths.goals;

import edu.ntnu.idatt2001.group_30.paths.Player;

import java.util.Objects;

/**
 * This class represents a minimum health threshold.
 *
 * @author Trym Hamer Gudvangen
 */
public class HealthGoal implements Goal {

    private final int minimumHealth;

    /**
     * The constructor defines the minimum amount of health a given player can have.
     * @param minimumHealth                 Minimum amount of health, given as an int.
     * @throws IllegalArgumentException     This exception is thrown if minimum health less than 0.
     */
    public HealthGoal(int minimumHealth) throws IllegalArgumentException{
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
}
