package edu.ntnu.idatt2001.group_30.paths.model.goals;

import edu.ntnu.idatt2001.group_30.paths.model.Player;

/**
 * The functional interface Goal represents a threshold, desirable result, or condition connected to the player's
 * condition.
 *
 * @author Trym Hamer Gudvangen
 */
@FunctionalInterface
public interface Goal {

    /**
     * This method checks that the player has fulfilled a certain condition of:
     * @param player    The player to be checked, given as a Player object.
     * @return          Status of player, {@code true} if the player fulfills the condition, else {@code false}.
     */
    boolean isFulfilled(Player player);

}
