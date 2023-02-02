package edu.ntnu.idatt2001.group_30;

/**
 * This class represents a change in the inventory attribute for any player.
 *
 * @author Trym Hamer Gudvangen
 */
public class InventoryAction implements Action{

    private final String item;

    /**
     * The constructor defines the item involved in changing the player's inventory.
     * @param item     The item, given as a String.
     */
    public InventoryAction(String item) {
        this.item = item;
    }

    /**
     * {@inheritDoc} inventory.
     * @param player The player, given as a Player object.
     */
    @Override
    public void execute(Player player) {
        player.addToInventory(this.item);
    }
}
