package edu.ntnu.idatt2001.group_30.paths.view.components.table;

/**
 * This class concerns itself with the aspects intrinsic to a passage table.
 * @param <Passage> The type of the table, represented using a Passage object.
 */
public class PassageTable<Passage> extends TableDisplay<Passage> {

    /**
     * This is a constructor which is used to construct a table for different passages.
     *
     * @param tableBuilder The builder used to construct a table, represented using an tableBuilder object.
     */
    public PassageTable(Builder<Passage> tableBuilder) {
        super(tableBuilder);
    }
}
