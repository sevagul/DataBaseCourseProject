package Controller;

import model.Product;

public interface FoodController {
    public String getInfo();
    public void putProduct(String name, int price);
    public int count(String col, String val);
    public String getName(int id);
    Product getProduct(int ingredient);
}
