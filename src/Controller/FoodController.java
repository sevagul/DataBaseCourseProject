package Controller;

import model.Product;
import new_model.dbClasses.KeyVals;
import new_model.dbClasses.SelectResult;

import java.util.ArrayList;

public interface FoodController{
    String getInfo();
    void putProduct(String name, int price);
    int count(String col, String val);
    String getName(int id);
    Product getProduct(int ingredient);
    int count();
    String getValue (int row, String col);
    SelectResult select();
    void update(KeyVals price, KeyVals product_id);
    void delete(KeyVals condition);
    ArrayList<String> getNamesLike(String prefix);
    int getId(String name);
}
