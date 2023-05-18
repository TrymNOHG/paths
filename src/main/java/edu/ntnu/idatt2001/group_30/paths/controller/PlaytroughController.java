package edu.ntnu.idatt2001.group_30.paths.controller;

import static edu.ntnu.idatt2001.group_30.paths.PathsSingleton.INSTANCE;

import edu.ntnu.idatt2001.group_30.paths.model.*;
import edu.ntnu.idatt2001.group_30.paths.model.goals.Goal;
import edu.ntnu.idatt2001.group_30.paths.view.views.HelpView;
import edu.ntnu.idatt2001.group_30.paths.view.views.HomeView;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.image.ImageView;

/**
 * Controller for the play-through of the game.
 * It is responsible for updating the view with the current state of the game.
 *
 * @author Nicolai H. Brand.
 */
public class PlaytroughController extends Controller {

    private Playthrough playthrough;

    /* reactive state */
    private final StringProperty gameTitle = new SimpleStringProperty("Playing: " + INSTANCE.getStory().getTitle());
    private final StringProperty passageTitle = new SimpleStringProperty();
    private final StringProperty passageContent = new SimpleStringProperty();
    private final ObservableList<Link> links = FXCollections.observableList(new ArrayList<>());
    private final ObservableList<String> inventory = FXCollections.observableList(new ArrayList<>());
    private final ObservableMap<Goal, Boolean> goals = FXCollections.observableMap(new HashMap<>());
    private final StringProperty health = new SimpleStringProperty();
    private final StringProperty score = new SimpleStringProperty();
    private final StringProperty gold = new SimpleStringProperty();
    private final IntegerProperty playthroughState = new SimpleIntegerProperty(PlaythroughState.PLAYING.ordinal());

    /**
     * Creates a new instance of the controller.
     * It initializes the controller and starts a new game.
     */
    public PlaytroughController() {
        super(HomeView.class, HelpView.class);
        startNewPlaythrough();
    }

    /**
     * Starts a new Playthrough based on the data in the PathsSingleton.
     * It also resets the playthroughState to PLAYING.
     */
    public void startNewPlaythrough() {
        assert INSTANCE.getPlayer() != null;
        assert INSTANCE.getStory() != null;
        assert INSTANCE.getGoals() != null;

        /* clear old state */
        playthroughState.setValue(PlaythroughState.PLAYING.ordinal());

        /* start new game */
        Game game = new Game(new Player(INSTANCE.getPlayer()), INSTANCE.getStory(), INSTANCE.getGoals());
        playthrough = new Playthrough(game);
        playthrough.beginPlaythrough();
        updateReactiveProperties(playthrough.getCurrentPassage());
    }

    /**
     * Makes a turn in the game based on the given link.
     * @param link the link to follow.
     */
    public void makeTurn(Link link) {
        PlaythroughState state = playthrough.makeTurn(link);
        playthroughState.setValue(state.ordinal());
        updateReactiveProperties(playthrough.getCurrentPassage());
    }

    /**
     * Updates the reactive properties based on the current state of the game.
     * @param passage the current passage.
     */
    private void updateReactiveProperties(Passage passage) {
        updateCurrentPassage(passage);
        updatePlayerData();
        updateGoals();
        updateInventory();
        updateGameTitle();
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
        playthrough.getGame().goals().forEach(goal -> goals.put(goal, goal.isFulfilled(getPlayer())));
    }

    /**
     * Updates the reactive properties based on the current inventory.
     */
    private void updateInventory() {
        inventory.clear();
        inventory.addAll(getPlayer().getInventory());
    }

    /**
     * Updates the game title based on the game state.
     */
    private void updateGameTitle() {
        PlaythroughState state = PlaythroughState
            .fromNumber(playthroughState.getValue())
            .orElse(PlaythroughState.PLAYING);
        if (state == PlaythroughState.PLAYING) {
            gameTitle.setValue("Playing: " + INSTANCE.getStory().getTitle());
        } else if (state == PlaythroughState.DEAD) {
            gameTitle.setValue("You died and lost the game!");
        } else if (state == PlaythroughState.STUCK) {
            gameTitle.setValue("You are stuck and lost the game!");
        } else if (state == PlaythroughState.WON) {
            gameTitle.setValue("You won the game!");
        } else {
            gameTitle.setValue("You won the game, but continued playing!");
        }
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
     * Helper method that returns the current player.
     * @return the current player.
     */
    private Player getPlayer() {
        return playthrough.getGame().player();
    }

    /**
     * Returns the name of the player.
     * @return the name of the player.
     */
    public String getPlayerName() {
        return playthrough.getGame().player().getName();
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
     * Returns the title of the game as an observable StringProperty.
     * The title of the game will be the title of the story while playing. If not playing, it will show the status of the game.
     * @return the title of the game.
     */
    public StringProperty getGameTitle() {
        return gameTitle;
    }

    /**
     * Returns the image of the character as an ImageView.
     * @return the image of the character.
     */
    public ImageView getCharacterImageView() {
        return INSTANCE.getCharacterImageView();
    }

    /**
     * Gets the playthrough state as an observable IntegerProperty.
     * @return the image of the character.
     */
    public IntegerProperty getPlaythroughState() {
        return playthroughState;
    }

    /**
     * Sets the playthrough state to WON_BUT_KEEP_PLAYING.
     */
    public void setWonButKeepPlaying() {
        playthrough.setWonButKeepPlaying();
        playthroughState.setValue(PlaythroughState.WON_BUT_KEEP_PLAYING.ordinal());
    }

    /**
     * A game can only be continued if it is in the PLAYING or WON_BUT_KEEP_PLAYING state.
     * @return whether the game can be kept playing.
     */
    public boolean canKeepPlaying() {
        return (
            playthroughState.get() == PlaythroughState.PLAYING.ordinal() ||
            playthroughState.get() == PlaythroughState.WON_BUT_KEEP_PLAYING.ordinal()
        );
    }
}
