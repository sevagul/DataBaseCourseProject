package Controller;

import model.Product;

public interface FoodController {
    String getInfo();
    void putProduct(String name, int price);
    int count(String col, String val);
    String getName(int id);
    Product getProduct(int ingredient);
    int count();
    String getValue (int row, String col);
}
