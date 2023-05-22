package edu.ntnu.idatt2001.group_30.paths.model;

import java.util.Objects;

/**
 * The Playthrough class represents a playthrough of a game.
 * Its responsibility is to keep track of the current state of the playthrough, and to update it when the player makes a turn.
 * It also keeps track of the current passage the player is in, as this needs to be known in order to figure out the game state.
 *
 * @author Nicolai H. Brand.
 */
public class Playthrough {

    private final Game game;
    private PlaythroughState gameState;
    private Passage currentPassage;

    /**
     * Creates a new instance of the Playthrough class.
     * @param game the game to play through.
     */
    public Playthrough(Game game) {
        Objects.requireNonNull(game, "Game cannot be null");
        this.game = game;
        this.gameState = PlaythroughState.PLAYING;
    }

    /**
     * Begins the playthrough by starting the game.
     * @return the current state of the playthrough.
     */
    public PlaythroughState beginPlaythrough() {
        currentPassage = game.begin();
        updateGameState();
        return gameState;
    }

    /**
     * Makes a turn in the game.
     * @param link the link to follow.
     * @return the current state of the playthrough.
     */
    public PlaythroughState makeTurn(Link link) {
        Objects.requireNonNull(link, "Link cannot be null");
        currentPassage = game.go(link);
        link.getActions().forEach(action -> action.execute(game.player()));
        updateGameState();
        return gameState;
    }

    /**
     * Updates the state of the playthrough.
     * This is done by checking if the game is over, and if so, what the outcome is.
     */
    private void updateGameState() {
        if (gameState == PlaythroughState.WON_BUT_KEEP_PLAYING) {
            return;
        }

        /*
         * if you win, but also die in the same turn, this is counted as a loss: you can't win if you're dead :-)
         */
        if (game.isGameOver()) {
            gameState = PlaythroughState.DEAD;
            return;
        }

        if (game.isGameWon()) {
            gameState = PlaythroughState.WON;
            return;
        }

        if (currentPassage != null && !currentPassage.hasLinks()) {
            gameState = PlaythroughState.STUCK;
        }
    }

    /**
     * Sets the game state to WON_BUT_KEEP_PLAYING.
     * This is used when the player has won the game, but still wants to keep playing.
     */
    public void setWonButKeepPlaying() {
        gameState = PlaythroughState.WON_BUT_KEEP_PLAYING;
    }

    /**
     * Returns the current state of the playthrough.
     * @return the current state of the playthrough.
     */
    public Passage getCurrentPassage() {
        return currentPassage;
    }

    /**
     * Returns the game.
     * @return the game.
     */
    public Game getGame() {
        return game;
    }
}
