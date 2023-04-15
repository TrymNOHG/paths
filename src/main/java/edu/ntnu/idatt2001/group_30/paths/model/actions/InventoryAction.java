package edu.ntnu.idatt2001.group_30.paths.model.actions;

import edu.ntnu.idatt2001.group_30.paths.model.Player;

import java.util.Objects;

/**
 * This class represents a change in the inventory attribute for any player.
 *
 * @author Trym Hamer Gudvangen
 */
public class InventoryAction implements Action<String> {

    private final String item;

    /**
     * The constructor defines the item involved in changing the player's inventory.
     * @param item     The item, given as a String.
     */
    public InventoryAction(String item) {
        this.item = item;
        //TODO: Add exception?
    }

    /**
     * {@inheritDoc} inventory.
     * @param player The player, given as a Player object.
     */
    @Override
    public void execute(Player player) {
        Objects.requireNonNull(player);
        player.addToInventory(this.item);
    }

    /**
     * This method retrieves the item value.
     * @return  Item value, given as a String.
     */
    @Override
    public String getActionValue() {
        return this.item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InventoryAction that)) return false;

        return Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return item != null ? item.hashCode() : 0;
    }
}
