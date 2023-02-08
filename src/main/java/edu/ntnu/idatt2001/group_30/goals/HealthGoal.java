package edu.ntnu.idatt2001.group_30.goals;

import edu.ntnu.idatt2001.group_30.Player;
import edu.ntnu.idatt2001.group_30.goals.Goal;

/**
 * This class represents a minimum health threshold.
 *
 * @author Trym Hamer Gudvangen
 */
public class HealthGoal implements Goal {

    private final int minimumHealth;

    /**
     * The constructor defines the minimum amount of health a given player can have.
     * @param minimumHealth   Minimum amount of health, given as an int.
     */
    public HealthGoal(int minimumHealth) {
        this.minimumHealth = minimumHealth;
    }

    /**
     * {@inheritDoc} health.
     * @param player    The player to be checked, given as a Player object.
     * @return          Status of player, {@code true} if the player has enough health, else {@code false}.
     */
    @Override
    public boolean isFulfilled(Player player) {
        return player.getHealth() >= this.minimumHealth;
    }
}
