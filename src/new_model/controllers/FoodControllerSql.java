package new_model.controllers;

import Controller.FoodController;
import Utils.Utils;
import model.Product;
import new_model.dbClasses.KeyVals;
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
        String answ = "";
        SelectResult result = select(ALL);
        for(KeyVals keyVals : result){
            answ += keyVals.get("product_id") + ". ";
            answ += keyVals.get("name") + ". Ціна: ";
            answ += keyVals.get("price") + ".\n";
        }
        return answ;
    }

    @Override
    public void putProduct(String name, int price) {
        String[] values = new String[] {name, String.valueOf(price)};
        insert(values);
    }

    @Override
    public String getName(int id) {
        SelectResult res = select("name", "product_id='" + String.valueOf(id) + "'");
        if(res.size() != 1){
            return null;
        }
        return res.get(0).get("name");
    }

    @Override
    public Product getProduct(int ingredient) {
        KeyVals res = selectOne(ALL, "product_id="+String.valueOf(ingredient));
        if(res == null){
            return null;
        }
        int id = res.getInt("product_id");
        int price = res.getInt("price");
        String name = res.get("name");
        return new Product(name, price, id);
    }
}
