package edu.ntnu.idatt2001.group_30.paths.model;

import java.util.Arrays;
import java.util.Optional;

/**
 * The PlaythroughState enum represents the different states a playthrough can be in.
 * It is used to keep track of the current state of the playthrough, and to update it when the player makes a turn.
 * Note:
 * A good systems language (c or c++) would have automatically converted the enum symbols to integers.
 * This is not the case in Java, so we have to do it manually. Not very idiomatic, but it works.
 *
 * @author Nicolai H. Brand.
 */
public enum PlaythroughState {
    PLAYING(0),
    WON(1),
    DEAD(2),
    STUCK(3),
    WON_BUT_KEEP_PLAYING(4);

    private final int number;

    /**
     * Creates a new instance of the PlaythroughState enum.
     * @param number the number of the state.
     */
    PlaythroughState(int number) {
        this.number = number;
    }

    /**
     * Gets the number of the state.
     * @return the number of the state.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Gets the PlaythroughState from a number.
     * @param number the number of the state.
     * @return the PlaythroughState.
     */
    public static Optional<PlaythroughState> fromNumber(int number) {
        return Arrays.stream(PlaythroughState.values()).filter(value -> value.getNumber() == number).findFirst();
    }
}
