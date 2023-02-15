package edu.ntnu.idatt2001.group_30.actions;

import edu.ntnu.idatt2001.group_30.Player;

/**
 * The functional interface Action provides the method signature for executing an attribute
 * action on the player.
 *
 * @author Trym Hamer Gudvangen
 */
@FunctionalInterface
public interface Action {

    /**
     * This method changes a given player's attribute:
     * @param player The player, given as a Player object.
     */
    void execute(Player player);

}
