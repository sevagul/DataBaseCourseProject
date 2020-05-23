package gui.dishAdding.TableIngredients;

import Controller.DishController;
import model.Dish;
import model.Product;
import model.Recipe;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class TableIngredientsModel extends AbstractTableModel {
    private ArrayList<String[]> rows;
    private String[] columnNames = {"Інгредієнт", "Кількість"};
    private DishController dishController;

    TableIngredientsModel(DishController dishController){
        rows = new ArrayList<String[]>();
        this.dishController=dishController;
    }

    public void addIngredient(String ingredient, int amount){
        //String sAmount = ((double)((int)(amount*1000)))/1000 + "";
        String[] newRow = {ingredient, amount + ""};
        rows.add(newRow);
    }

    @Override
    public String getColumnName(int column) {
        if(column > -1 && column < columnNames.length)
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

    public boolean isCellExists(int rowIndex, int columnIndex){
        if(columnIndex >= 0 && columnIndex < columnNames.length && rowIndex < rows.size() && rowIndex >=0 ){
            return true;
        }
        return false;
    }
    public void showDish(int id){
        if(getRowCount() != 0){
            clean();
        }
        Recipe recipe = dishController.getReciepe(id, 0);
        for (Product product: recipe.keySet()) {
            String name = product.getName();
            Integer amount = recipe.get(product);
            addIngredient(name, amount);
        }
    }
}
