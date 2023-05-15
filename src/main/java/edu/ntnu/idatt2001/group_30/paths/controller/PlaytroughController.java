package edu.ntnu.idatt2001.group_30.paths.controller;

import static edu.ntnu.idatt2001.group_30.paths.PathsSingleton.INSTANCE;

import edu.ntnu.idatt2001.group_30.paths.model.*;
import edu.ntnu.idatt2001.group_30.paths.model.goals.Goal;
import edu.ntnu.idatt2001.group_30.paths.model.goals.HealthGoal;
import edu.ntnu.idatt2001.group_30.paths.model.goals.ScoreGoal;
import edu.ntnu.idatt2001.group_30.paths.view.HelpView;
import edu.ntnu.idatt2001.group_30.paths.view.HomeView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

/**
 * Controller for the play-through of the game.
 * It is responsible for updating the view with the current state of the game.
 *
 * @author Nicolai H. Brand.
 */
public class PlaytroughController extends Controller {

    private Game game;

    /* reactive state */
    private final StringProperty passageTitle = new SimpleStringProperty();
    private final StringProperty passageContent = new SimpleStringProperty();
    private final ObservableList<Link> links = FXCollections.observableList(new ArrayList<>());
    private final ObservableList<String> inventory = FXCollections.observableList(new ArrayList<>());
    private final ObservableMap<Goal, Boolean> goals = FXCollections.observableMap(new HashMap<>());
    private final StringProperty health = new SimpleStringProperty();
    private final StringProperty score = new SimpleStringProperty();
    private final StringProperty gold = new SimpleStringProperty();
    private final BooleanProperty gameOver = new SimpleBooleanProperty(false);
    private final BooleanProperty gameWon = new SimpleBooleanProperty(false);
    private boolean gameAlreadyWon = false;

    /**
     * Creates a new instance of the controller.
     * It initializes the controller and starts a new game.
     */
    public PlaytroughController() {
        super(HomeView.class, HelpView.class);
        startNewGame();
    }

    /**
     * Starts a new game based on the data in the PathsSingleton.
     * It also resets the reactive properties.
     */
    public void startNewGame() {
        gameAlreadyWon = false;
        game = new Game(INSTANCE.getPlayer(), INSTANCE.getStory(), INSTANCE.getGoals());
        System.out.println("Starting new game");
        Passage openingPassage = game.begin();
        updateReactiveProperties(openingPassage);
    }

    /**
     * Makes a turn in the game.
     * The player chooses a link, and the game is updated accordingly.
     * @param link the link the player chooses.
     */
    public void chooseLink(Link link) {
        game.go(link);
        link.getActions().forEach(action -> action.execute(game.getPlayer()));
        updateReactiveProperties(getStory().getPassage(link));
    }

    /**
     * Updates the reactive properties based on the current state of the game.
     * @param passage the current passage.
     */
    private void updateReactiveProperties(Passage passage) {
        updateCurrentPassage(passage);
        updatePlayerData();
        updateGoals();
        updateGameState();
        updateInventory();
    }

    /**
     * Computes the current state of the game.
     * Updates the reactive properties based on the current state of the game.
     */
    private void updateGameState() {
        if (gameAlreadyWon) return;

        if (game.isGameWon()) {
            gameWon.setValue(true);
            gameAlreadyWon = true;
        }
        gameOver.setValue(game.isGameOver());
    }

    /**
     * Updates the reactive properties based on the current passage.
     * @param passage the current passage.
     */
    private void updateCurrentPassage(Passage passage) {
        passageTitle.setValue(passage.getTitle());
        passageContent.setValue(passage.getContent());
        links.clear();
        links.addAll(passage.getLinks());
    }

    /**
     * Updates the reactive properties based on the current player data.
     */
    private void updatePlayerData() {
        health.setValue(String.valueOf(getPlayer().getHealth()));
        score.setValue(String.valueOf(getPlayer().getScore()));
        gold.setValue(String.valueOf(getPlayer().getGold()));
    }

    /**
     * Updates the reactive properties based on the current goals and their fulfillment.
     */
    private void updateGoals() {
        goals.clear();
        game.getGoals().forEach(goal -> goals.put(goal, goal.isFulfilled(getPlayer())));
    }

    /**
     * Updates the reactive properties based on the current inventory.
     */
    private void updateInventory() {
        inventory.clear();
        inventory.addAll(getPlayer().getInventory());
    }

    /**
     * Returns the title of the current passage as an observable StringProperty.
     * @return the title of the current passage.
     */
    public StringProperty getPassageTitle() {
        return passageTitle;
    }

    /**
     * Returns the content of the current passage as an observable StringProperty.
     * @return the content of the current passage.
     */
    public StringProperty getPassageContent() {
        return passageContent;
    }

    /**
     * Returns the links of the current passage as an observable list.
     * @return the links of the current passage.
     */
    public ObservableList<Link> getLinks() {
        return links;
    }

    /**
     * Returns the inventory of the player as an observable list.
     * @return the inventory of the player.
     */
    public ObservableList<String> getInventory() {
        return inventory;
    }

    /**
     * Returns the goals of the game as an observable map.
     * @return the goals of the game.
     */
    public ObservableMap<Goal, Boolean> getGoals() {
        return goals;
    }

    /**
     * Returns the current story.
     * @return the current story.
     */
    public Story getStory() {
        return game.getStory();
    }

    /**
     * Returns the current player.
     * @return the current player.
     */
    public Player getPlayer() {
        return game.getPlayer();
    }

    /**
     * Returns the health of the player as an observable StringProperty.
     * @return the health of the player.
     */
    public StringProperty getHealth() {
        return health;
    }

    /**
     * Returns the score of the player as an observable StringProperty.
     * @return the score of the player.
     */
    public StringProperty getScore() {
        return score;
    }

    /**
     * Returns the gold of the player as an observable StringProperty.
     * @return the gold of the player.
     */
    public StringProperty getGold() {
        return gold;
    }

    /**
     * Returns the game over state as an observable BooleanProperty.
     * @return the game over state.
     */
    public BooleanProperty getGameOver() {
        return gameOver;
    }

    /**
     * Returns the game won state as an observable BooleanProperty.
     * @return the game won state.
     */
    public BooleanProperty getGameWon() {
        return gameWon;
    }
}
