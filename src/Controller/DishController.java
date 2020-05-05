package Controller;

import model.Dish;

import java.util.ArrayList;

public interface DishController {
    public String getReadableInfo();
    //public void saveToFile(String file);
    public Dish getDish(String dishName);
    public ArrayList<String> getDishesWithType(int a);
    public boolean contains(String name);
    public void addDish(String dishName, ArrayList<Integer> ingredients, ArrayList<Integer> weights, int peopleAmount, int dishType);
}
