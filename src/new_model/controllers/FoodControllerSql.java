package new_model.controllers;

import Controller.FoodController;
import new_model.dbClasses.SelectResult;

import java.util.ArrayList;
import java.util.Arrays;

public class FoodControllerSql extends TableController implements FoodController {

    public FoodControllerSql(String dataBase, String user, String password) {
        super(dataBase, user, password, "products");
    }
    public FoodControllerSql(String dataBase) {
        super(dataBase, "products");
    }

    @Override
    public void insert(ArrayList<String> values) {
        values.add(0, "NULL");
        super.insert(values);
    }

    @Override
    public void insert(String[] values) {
        insert(new ArrayList<>(Arrays.asList(values)));
    }


    @Override
    public String getInfo(){
        SelectResult result = select(ALL);
        return result.toString();
    }

    @Override
    public void putProduct(String name, int price) {
        String[] values = new String[] {name, String.valueOf(price)};
        insert(values);
    }
}
