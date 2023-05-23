package edu.ntnu.idatt2001.group_30.paths.view.components.table;

/**
 * This class contains the table display implementation for the action object.
 * @param <Action>  Type of table, which is for Action.
 *
 * @author Trym Hamer Gudvangen
 */
public class ActionTable<Action> extends TableDisplay<Action> {

    /**
     * This is a constructor which is used to construct a table for different actions.
     *
     * @param tableBuilder The builder used to construct a table, represented using an tableBuilder object.
     */
    public ActionTable(Builder<Action> tableBuilder) {
        super(tableBuilder);
    }
}
