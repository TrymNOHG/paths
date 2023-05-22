package edu.ntnu.idatt2001.group_30.paths.view.components.table;

/**
 * This class concerns itself with the aspects intrinsic to a link table.
 * @param <Link> The type of the table, represented using a Link object.
 */
public class LinkTable<Link> extends TableDisplay<Link> {

    /**
     * This is a constructor which is used to construct a table for different links.
     *
     * @param tableBuilder The builder used to construct a table, represented using an tableBuilder object.
     */
    public LinkTable(Builder<Link> tableBuilder) {
        super(tableBuilder);
    }
}
