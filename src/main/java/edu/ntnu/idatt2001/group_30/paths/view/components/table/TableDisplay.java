package edu.ntnu.idatt2001.group_30.paths.view.components.table;

import java.util.function.Function;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * This class concerns itself with building a table view filled with the desired information in columns. It does so
 * by using a TableBuilder.
 *
 * @author Trym Hamer Gudvangen
 */
public class TableDisplay<T> extends TableView<T> {

    /**
     * This is a constructor which is used to construct a table.
     * @param tableBuilder The builder used to construct a table, represented using an tableBuilder object.
     */
    public TableDisplay(Builder<T> tableBuilder) {
        super();
        this.getColumns().addAll(tableBuilder.tableColumns);
    }

    /**
     * This class concerns itself with building a table view filled with the desired information of columns. In order
     * to efficiently accomplish this, as well as remove the telescoping constructor anti-pattern, the builder pattern
     * was utilized.
     */
    public static class Builder<T> {

        private final ObservableList<TableColumn<T, ?>> tableColumns;

        public Builder() {
            this.tableColumns = FXCollections.observableArrayList();
        }

        /**
         * This method attaches a desired column {@link TableDisplayColumn#TableDisplayColumn(String, String)} to the
         * tableview.
         * @param infoHeader   The name of the column, represented using a String.
         * @param variableName The attribute the information will be extracted from, represented as a String.
         * @return                 The builder itself is returned, represented as a Builder object.
         */
        public Builder<T> addColumn(String infoHeader, String variableName) {
            this.tableColumns.add(new TableDisplayColumn<T>(infoHeader, variableName).getColumn());
            return this;
        }

        /**
         * This method attaches a desired column {@link TableDisplayColumn#TableDisplayColumn(String, String)} to the
         * tableview that is complex.
         * @param infoHeader            The name of the column, represented using a String.
         * @param complexValueFunction  The attribute the information will be extracted from, represented as a String.
         * @return                      The builder itself is returned, represented as a Builder object.
         */
        public Builder<T> addColumnWithComplexValue(String infoHeader, Function<T, String> complexValueFunction) {
            TableColumn<T, String> column = new TableColumn<>(infoHeader);
            column.setCellValueFactory(cellData ->
                new SimpleStringProperty(complexValueFunction.apply(cellData.getValue()))
            );
            column.setStyle("-fx-alignment: CENTER");
            this.tableColumns.add(column);
            return this;
        }

        /**
         * This method actually constructs the table by creating an TableDisplay object.
         * @return The table view, represented using an TableDisplay object.
         */
        public TableDisplay<T> build() {
            return new TableDisplay<T>(this);
        }
    }
}
