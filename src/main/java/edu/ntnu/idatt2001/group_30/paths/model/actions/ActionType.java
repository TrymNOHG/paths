package edu.ntnu.idatt2001.group_30.paths.model.actions;

/**
 * This enumeration represents the different types of actions that exist: gold, health, inventory, and score.
 *
 * @author Trym Hamer Gudvangen
 */
public enum ActionType {
    GOLD_ACTION("Gold Action"),
    HEALTH_ACTION("Health Action"),
    INVENTORY_ACTION("Inventory Action"),
    SCORE_ACTION("Score Action");

    private final String displayName;

    ActionType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
