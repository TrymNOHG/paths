package edu.ntnu.idatt2001.group_30.paths.view.components.table;

/**
 * This class concerns itself with the aspects intrinsic to a stats table.
 * @param <Player>  The type of the table, represented using a Player object.
 */
public class StatsTable<Player> extends TableDisplay<Player> {

    /**
     * This is a constructor which is used to construct a Player table.
     *
     * @param tableBuilder The builder used to construct a table, represented using an tableBuilder object.
     */
    public StatsTable(Builder<Player> tableBuilder) {
        super(tableBuilder);
    }
}
