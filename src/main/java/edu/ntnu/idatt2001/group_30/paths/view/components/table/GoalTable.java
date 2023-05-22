package edu.ntnu.idatt2001.group_30.paths.view.components.table;

/**
 * This class contains the specific table display implementation for the Goal classes.
 * @param <Goal> Type of table, which is Goal.
 *
 * @author Trym Hamer Gudvangen
 */
public class GoalTable<Goal> extends TableDisplay<Goal> {

    /**
     * This is a constructor which is used to construct a table for different goals.
     *
     * @param tableBuilder The builder used to construct a table, represented using an tableBuilder object.
     */
    public GoalTable(Builder<Goal> tableBuilder) {
        super(tableBuilder);
    }
}
