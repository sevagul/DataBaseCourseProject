package gui.dishAdding.tableProducts;

import Controller.FoodController;
import new_model.controllers.FoodControllerSql;
import new_model.dbClasses.KeyVals;
import new_model.dbClasses.SelectResult;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TableProductsModel extends AbstractTableModel {
    private String[] columnNames = {"ID", "Продукт", "Ціна"};
    private String[] columnNamesOfficial = {"product_id", "name", "price"};
    private int column_count = columnNames.length;
    private int row_count;
    private FoodController foodController;
    SelectResult allRes;

    TableProductsModel(FoodController foodController) {
        column_count = columnNames.length;
        this.foodController = foodController;
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

    public boolean isCellExists(int rowIndex, int columnIndex){
        if(columnIndex >= 0 && columnIndex < column_count && rowIndex < row_count && rowIndex >=0 ){
            return true;
        }
        return false;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (isCellExists(rowIndex,columnIndex) && columnIndex == 2);
    }

    @Override
    public void setValueAt(Object newVal, int row, int col) {
        if (!isCellEditable(row, col)){
            return;
        }
        foodController.update(new KeyVals("price", (String) newVal), new KeyVals("product_id", (String) getValueAt(row, 0)));
        refresh();
    }

    public void deleteRow(int row){
        foodController.delete(new KeyVals(columnNamesOfficial[0] , (String) getValueAt(row, 0)));
    }




    public void refresh(){
        allRes = foodController.select();
        row_count = foodController.count();
        fireTableDataChanged();
    }
}
