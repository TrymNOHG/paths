package edu.ntnu.idatt2001.group_30.paths.model.actions;

import edu.ntnu.idatt2001.group_30.paths.model.Player;

/**
 * The Action interface provides the method signature for executing an attribute
 * action on the player.
 *
 * @author Trym Hamer Gudvangen
 */
public interface Action<T> {

    /**
     * This method changes a given player's attribute:
     * @param player The player, given as a Player object.
     */
    void execute(Player player);

    /**
     * This method retrieves the action value of a given action.
     * @return Action value, given as an Object.
     */
    T getActionValue();

    /**
     * This method ensures that all action implementations has a way to check if two action objects are equal.
     * @param o Object being compared
     * @return  Boolean representing {@code true} if the actions are equal, otherwise {@code false}
     */
    boolean equals(Object o);

}
