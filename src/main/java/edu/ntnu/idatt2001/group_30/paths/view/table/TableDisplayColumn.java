package edu.ntnu.idatt2001.group_30.paths.view.table;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * This class concerns itself with the aspects intrinsic to a unit column. It, therefore, contains a method for
 * the creation of such a column.
 *
 * @author Trym Hamer Gudvangen
 */
public class TableDisplayColumn<T> {
    private TableColumn<T, ?> column;

    /**
     * This constructor focuses on constructing the desired column from the information specified.
     * @param infoHeader    The name of the column, represented using a String.
     * @param variableName  The attribute the information will be extracted from, represented as a String.
     */
    public TableDisplayColumn(String infoHeader, String variableName) {
        createUnitColumn(infoHeader, variableName);
    }

    /**
     * This method creates a table column with the specified information. It, thereafter, is added
     * as a column in the tableview provided.
     * @param infoHeader    The header of the column being added, represented as a String.
     * @param variableName  The name of the variable from the Unit, represented as a String.
     */
    private void createUnitColumn(String infoHeader, String variableName){
        this.column = new TableColumn<>(infoHeader);
        this.column.setCellValueFactory(new PropertyValueFactory<>(variableName));
        this.column.setStyle("-fx-alignment: CENTER");
    }

    /**
     * This method retrieves the unit column.
     * @return The column, represented using a TableColumn object.
     */
    public TableColumn<T, ?> getColumn() {
        return column;
    }
}
