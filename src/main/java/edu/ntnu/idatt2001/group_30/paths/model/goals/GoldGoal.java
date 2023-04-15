package edu.ntnu.idatt2001.group_30.paths.model.goals;

import edu.ntnu.idatt2001.group_30.paths.model.Player;

import java.util.Objects;

/**
 * This class represents a minimum gold threshold.
 *
 * @author Trym Hamer Gudvangen
 */
public class GoldGoal implements Goal {

    private final int minimumGold;

    /**
     * The constructor defines the minimum amount of gold a given player can have.
     * @param minimumGold    Minimum amount of gold, given as an int.
     */
    public GoldGoal(int minimumGold) {
        this.minimumGold = minimumGold;
        //TODO: Add exception?
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
}
