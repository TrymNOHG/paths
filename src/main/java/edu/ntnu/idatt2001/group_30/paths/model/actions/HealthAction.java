package edu.ntnu.idatt2001.group_30.paths.model.actions;

import edu.ntnu.idatt2001.group_30.paths.model.Player;
import java.util.Objects;

/**
 * This class represents a change in the health attribute for any player.
 *
 * @author Trym Hamer Gudvangen
 */
public class HealthAction implements Action<Integer> {

    private final int health;

    /**
     * The constructor defines how much the player's health will be changed by.
     * @param health    Amount of health, given as an int.
     */
    public HealthAction(int health) {
        this.health = health;
    }

    /**
     * {@inheritDoc} health.
     * @param player The player, given as a Player object.
     */
    @Override
    public void execute(Player player) {
        Objects.requireNonNull(player);
        player.addHealth(this.health);
    }

    /**
     * This method retrieves the health value;
     * @return Health value, given as an int.
     */
    @Override
    public Integer getActionValue() {
        return health;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HealthAction that)) return false;

        return health == that.health;
    }

    @Override
    public int hashCode() {
        return health;
    }
}
