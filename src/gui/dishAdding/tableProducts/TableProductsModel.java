package gui.dishAdding.tableProducts;

import Controller.FoodController;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TableProductsModel extends AbstractTableModel {
    private String[] columnNames = {"ID", "Продукт", "Ціна"};
    private String[] columnNamesOfficial = {"product_id", "name", "price"};
    private int column_count = columnNames.length;
    private FoodController foodController;

    TableProductsModel(FoodController foodController) {
        column_count = columnNames.length;
        this.foodController = foodController;
    }

    @Override
    public String getColumnName(int column) {
        if (column > -1 && column < column_count)
            return columnNames[column];
        return "Out of columnes";
    }

    @Override
    public int getRowCount() {
        return foodController.count();
    }

    @Override
    public int getColumnCount() {
        return column_count;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex > -1 && columnIndex < 2 && rowIndex < foodController.count())
            return foodController.getValue(rowIndex, columnNamesOfficial[columnIndex]);
        return null;
    }
}
