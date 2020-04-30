package gui.dishAdding;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableIngredientsModel extends AbstractTableModel {
    private ArrayList<String[]> rows;
    private String[] columnNames = {"Інгредієнт", "Кількість"};

    TableIngredientsModel(){
        rows = new ArrayList<String[]>();
    }

    public void addIngredient(String ingredient, int amount){
        //String sAmount = ((double)((int)(amount*1000)))/1000 + "";
        String[] newRow = {ingredient, amount + ""};
        rows.add(newRow);
    }

    @Override
    public String getColumnName(int column) {
        if(column > -1 && column < 2)
            return columnNames[column];
        return "Out of columnes";
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex > -1 && columnIndex < 2 && rowIndex < rows.size())
            return rows.get(rowIndex)[columnIndex];
        return null;
    }
    public void clean(){
        rows.clear();
    }
}
