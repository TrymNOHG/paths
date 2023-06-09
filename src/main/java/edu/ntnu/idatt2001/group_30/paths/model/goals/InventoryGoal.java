package edu.ntnu.idatt2001.group_30.paths.model.goals;

import edu.ntnu.idatt2001.group_30.paths.model.Player;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * This class represents the items that are expected in a player's inventory.
 *
 * @author Trym Hamer Gudvangen, Nicolai H. Brand.
 */
public class InventoryGoal implements Goal<List<String>> {

    private final List<String> mandatoryItems;

    /**
     * The constructor defines the items a player must have.
     * @param mandatoryItems    Expected items, given as a List{@code <String>}.
     */
    public InventoryGoal(List<String> mandatoryItems) throws IllegalArgumentException {
        Objects.requireNonNull(mandatoryItems);
        this.mandatoryItems = mandatoryItems;
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

    /**
     * This method retrieves the goal value.
     * @return Mandatory items, given as a List of String objects.
     */
    @Override
    public List<String> getGoalValue() {
        return this.mandatoryItems;
    }

    /**
     * String representation of the InventoryGoal object.
     * @return  String representation of the InventoryGoal object.
     */
    @Override
    public String toString() {
        return "Need to have the following items in your inventory: " + this.mandatoryItems;
    }
}
