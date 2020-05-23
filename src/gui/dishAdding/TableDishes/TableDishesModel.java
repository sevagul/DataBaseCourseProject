package gui.dishAdding.TableDishes;

import Controller.DishController;
import new_model.dbClasses.KeyVals;
import new_model.dbClasses.SelectResult;

import javax.swing.table.AbstractTableModel;

public class TableDishesModel extends AbstractTableModel {
    private String[] columnNames = {"ID", "Страва"};
    private String[] columnNamesOfficial = {"dish_id", "name"};
    private int column_count = columnNames.length;
    private int row_count;
    private DishController dishController;
    SelectResult allRes;

    TableDishesModel(DishController dishController) {
        column_count = columnNames.length;
        this.dishController = dishController;
        refresh();
    }

    @Override
    public String getColumnName(int column) {
        if (column > -1 && column < column_count)
            return columnNames[column];
        return "Out of columnes";
    }

    @Override
    public int getRowCount() {
        return row_count;
    }

    @Override
    public int getColumnCount() {
        return column_count;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex > -1 && columnIndex < column_count && rowIndex < row_count)
            return allRes.get(rowIndex).get(columnNamesOfficial[columnIndex]);
        return null;
    }
    public void refresh(){
        allRes = dishController.select();
        row_count = dishController.count();
        fireTableDataChanged();
    }
    public void deleteRow(int row){
        dishController.delete(new KeyVals(columnNamesOfficial[0] , (String) getValueAt(row, 0)));
    }
}
