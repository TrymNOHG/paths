package edu.ntnu.idatt2001.group_30.paths.model;

import java.util.ArrayList;
import java.util.List;

/**
 *  This class represents a Player who will experience the story. Therefore, it contains information surrounding their
 *  character, such as health, score, gold, and inventory.
 *
 * @author Trym Hamer Gudvangen, Nicolai H. Brand
 */
public class Player {

    private final String name;
    private int health;
    private int score;
    private int gold;
    private final List<String> inventory;

    /**
     * This constructor creates a Player object with status information.
     * @param name                          The name of the player, represented using a String.
     * @param health                        Health of the player, represented using an int.
     * @param score                         Score of the player, represented using an int.
     * @param gold                          Amount of gold the player has, represented using an int.
     * @throws IllegalArgumentException     This exception is thrown if the health, score, gold or name arguments are invalid.
     */
    public Player(String name, int health, int score, int gold) throws IllegalArgumentException {
        if (name.isBlank()) throw new IllegalArgumentException("The name cannot be blank or empty");
        this.name = name;
        if (health < 0) throw new IllegalArgumentException("Initial health cannot be less than 0");
        this.health = health;
        if (score < 0) throw new IllegalArgumentException("Initial score cannot be less than 0");
        this.score = score;
        if (gold < 0) throw new IllegalArgumentException("Initial gold cannot be less than 0");
        this.gold = gold;
        this.inventory = new ArrayList<>();
    }

    /**
     * This constructor creates a new Player from an existing Player object.
     * This creates a deep copy of the Player object.
     * @param player    The Player object to be copied.
     */
    public Player(Player player) {
        this.name = player.name;
        this.health = player.health;
        this.score = player.score;
        this.gold = player.gold;
        this.inventory = new ArrayList<>(player.inventory);
    }

    /**
     * The Builder class is used to create a Player object.
     * @param build The Builder object that contains the information needed to create a Player object.
     */
    public Player(Builder build) {
        this.name = build.name;
        this.health = build.health;
        this.score = build.score;
        this.gold = build.gold;
        this.inventory = build.inventory;
    }

    /**
     * This method retrieves the name of the player.
     * @return  Name of the player, represented using a String.
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method adds a given amount of health to the player's health.
     * @param health    Amount of health to be added, represented using an int.
     */
    public void addHealth(int health) {
        setHealth(this.health + health);
    }

    /**
     * This method retrieves the health of the player.
     * @return  Health of the player, given as an int.
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * This method sets the players health to be the new health or 0, if the given health is less than 0.
     * @param newHealth The new health of the player, given as an int.
     */
    public void setHealth(int newHealth) {
        this.health = Math.max(newHealth, 0);
    }

    /**
     * This method adds a given amount of points to the Player's score.
     * @param points    Points to be added, given as an int.
     */
    public void addScore(int points) {
        this.score += points;
        //TODO: Can score be negative?
    }

    /**
     * This method retrieves the score of the player.
     * @return  The player's score, given as an int.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * This method adds a given amount of gold to the player's gold.
     * @param gold  Amount of gold to be added, given as an int.
     */
    public void addGold(int gold) {
        this.gold += gold;
    }

    /**
     * This method sets the gold field to the given value.
     * @param gold  New gold value, given as an int.
     */
    public void setGold(int gold) {
        this.gold = gold;
    }

    /**
     * This method retrieves the player's amount of gold.
     * @return  Player's amount of gold, represented using an int.
     */
    public int getGold() {
        return this.gold;
    }

    /**
     * This method adds a given item to the player's inventory.
     * @param item  Item to be added, represented as a String.
     */
    public void addToInventory(String item) {
        this.inventory.add(item);
    }

    /**
     * This method retrieves the inventory of the player.
     * @return Inventory of the player, given as a List{@code <String>}.
     */
    public List<String> getInventory() {
        return this.inventory;
    }

    /**
     * Static Builder class that enables the creation of a Player object using the builder pattern.
     * @author Nicolai H. Brand
     */
    public static class Builder {

        private final String name;

        private int health = 0;
        private int score = 0;
        private int gold = 0;
        private final List<String> inventory = new ArrayList<>();

        /**
         * Constructor for the Builder class.
         * @param name  Name of the player, represented using a String.
         */
        public Builder(String name) {
            this.name = name;
        }

        /**
         * This method creates a Player object using the information provided by the Builder object.
         * @return The Player object, created using the information provided by the Builder object.
         */
        public Player build() {
            return new Player(this);
        }

        /**
         * This method sets the health of the player and returns the Builder object to enable method chaining.
         * @param health   Health of the player, represented using an int.
         * @return         The Builder object, to enable method chaining.
         */
        public Builder health(int health) {
            this.health = health;
            return this;
        }

        /**
         * This method sets the score of the player and returns the Builder object to enable method chaining.
         * @param score  Score of the player, represented using an int.
         * @return       The Builder object, to enable method chaining.
         */
        public Builder score(int score) {
            this.score = score;
            return this;
        }

        /**
         * This method sets the gold of the player and returns the Builder object to enable method chaining.
         * @param gold Gold of the player, represented using an int.
         * @return     The Builder object, to enable method chaining.
         */
        public Builder gold(int gold) {
            this.gold = gold;
            return this;
        }

        /**
         * This method adds an item to the player's inventory and returns the Builder object to enable method chaining.
         * @param item  Item to be added to the player's inventory, represented using a String.
         * @return      The Builder object, to enable method chaining.
         */
        public Builder addToInventory(String item) {
            this.inventory.add(item);
            return this;
        }
    }
}
