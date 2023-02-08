package edu.ntnu.idatt2001.group_30.actions;

import edu.ntnu.idatt2001.group_30.Player;
import edu.ntnu.idatt2001.group_30.actions.Action;

/**
 * This class represents a change in the health attribute for any player.
 *
 * @author Trym Hamer Gudvangen
 */
public class GoldAction implements Action {

    private final int gold;

    /**
     * The constructor defines how much the player's gold will be changed by.
     * @param gold    Amount of gold, given as an int.
     */
    public GoldAction(int gold) {
        this.gold = gold;
    }


    /**
     * {@inheritDoc} gold.
     * @param player The player, given as a Player object.
     */
    @Override
    public void execute(Player player) {
        player.addGold(this.gold);
    }
}
