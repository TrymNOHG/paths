package edu.ntnu.idatt2001.group_30.paths.model.goals;

import edu.ntnu.idatt2001.group_30.paths.model.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * This class represents the items that are expected in a player's inventory.
 *
 * @author Trym Hamer Gudvangen
 */
public class InventoryGoal implements Goal<List<String>> {

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
    public boolean isFulfilled(Player player) {
        Objects.requireNonNull(player);
        return new HashSet<>(player.getInventory()).containsAll(mandatoryItems);
    }

    public void concatGoals(InventoryGoal inventoryGoal) {
        this.mandatoryItems.addAll(inventoryGoal.mandatoryItems);
    }

    @Override
    public String toString() {
        return "InventoryGoal{" +
                "mandatoryItems=" + mandatoryItems +
                '}';
    }
}
