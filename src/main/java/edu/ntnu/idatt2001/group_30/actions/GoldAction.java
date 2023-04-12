package edu.ntnu.idatt2001.group_30.actions;

import edu.ntnu.idatt2001.group_30.Player;
import java.util.Objects;

/**
 * This class represents a change in the health attribute for any player.
 *
 * @author Trym Hamer Gudvangen
 */
public class GoldAction implements Action<Integer> {

    private final int gold;

    /**
     * The constructor defines how much the player's gold will be changed by.
     * @param gold    Amount of gold, given as an int.
     */
    public GoldAction(int gold) {
        this.gold = gold;
        //TODO: Add exception?
    }


    /**
     * {@inheritDoc} gold.
     * @param player The player, given as a Player object.
     */
    @Override
    public void execute(Player player) {
        Objects.requireNonNull(player);
        player.addGold(this.gold);
    }

    /**
     * This method retrieves the gold value.
     * @return  Gold value, given as an int.
     */
    @Override
    public Integer getActionValue() {
        return gold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GoldAction that)) return false;

        return gold == that.gold;
    }

    @Override
    public int hashCode() {
        return gold;
    }
}
