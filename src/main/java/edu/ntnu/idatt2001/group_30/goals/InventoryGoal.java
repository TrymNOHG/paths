package edu.ntnu.idatt2001.group_30.goals;

import edu.ntnu.idatt2001.group_30.Player;
import edu.ntnu.idatt2001.group_30.goals.Goal;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * This class represents the items that are expected in a player's inventory.
 *
 * @author Trym Hamer Gudvangen
 */
public class InventoryGoal implements Goal {

    private final List<String> mandatoryItems;

    /**
     * The constructor defines the items a player must have.
     * @param mandatoryItems    Expected items, given as a List{@code <String>}.
     */
    public InventoryGoal(List<String> mandatoryItems) {
        this.mandatoryItems = mandatoryItems;
        //TODO: Add exception?
    }

    /**
     * {@inheritDoc} inventory.
     * @param player    The player to be checked, given as a Player object.
     * @return          Status of player, {@code true} if the player has the items, else {@code false}.
     */
    @Override
    public boolean isFulfilled(@NotNull Player player) {
        return player.getInventory().containsAll(mandatoryItems);
        //TODO: Optimize ^^^ Maybe try the hashset constructor?
    }
}
