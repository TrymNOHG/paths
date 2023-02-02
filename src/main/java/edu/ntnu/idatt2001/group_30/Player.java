package edu.ntnu.idatt2001.group_30;

import java.util.ArrayList;
import java.util.List;

/**
 *  This class represents a Player who will experience the story. Therefore, it contains information surrounding their
 *  character, such as health, score, gold, and inventory.
 *
 * @author Trym Hamer Gudvangen
 */
public class Player {
    private final String name;
    private int health;
    private int score;
    private int gold;
    private final List<String> inventory;

    /**
     * This constructor creates a Player object with status information.
     * @param name      The name of the player, represented using a String.
     * @param health    Health of the player, represented using an int.
     * @param score     Score of the player, represented using an int.
     * @param gold      Amount of gold the player has, represented using an int.
     */
    public Player(String name, int health, int score, int gold) {
        this.name = name;
        if (health < 0) throw new IllegalArgumentException("Health cannot be less than 0");
        this.health = health;
        this.score = score;
        this.gold = gold;
        this.inventory = new ArrayList<>();
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
        this.health += health;
        //TODO: add exception handling for parameter. Alternatively, add exception handling to a setHealth method

    }

    /**
     * This method retrieves the health of the player.
     * @return  Health of the player, given as an int.
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * This method adds a given amount of points to the Player's score.
     * @param points    Points to be added, given as an int.
     */
    public void addScore(int points) {
        this.score += points;
        //TODO: add exception handling for parameter
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
        //TODO: exception handling needed
    }

    /**
     * This method retrieves the inventory of the player.
     * @return Inventory of the player, given as a List{@code <String>}.
     */
    public List<String> getInventory() {
        return this.inventory;
    }
}