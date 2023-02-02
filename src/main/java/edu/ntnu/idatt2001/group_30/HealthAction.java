package edu.ntnu.idatt2001.group_30;

/**
 * This class represents a change in the health attribute for any player.
 *
 * @author Trym Hamer Gudvangen
 */
public class HealthAction implements Action{

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
        player.addHealth(this.health);
    }
}