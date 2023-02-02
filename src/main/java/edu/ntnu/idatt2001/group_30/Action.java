package edu.ntnu.idatt2001.group_30;

/**
 * The functional interface Action provides the method signature for executing an attribute
 * action on the player.
 */
@FunctionalInterface
public interface Action {

    /**
     * This method changes an attribute of the given player.
     * @param player The player, given as a Player object.
     */
    void execute(Player player);

}
