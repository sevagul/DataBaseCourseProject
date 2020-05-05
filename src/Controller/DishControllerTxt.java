package Controller;

import model.Dish;
import model.DishBase;
import model.FoodBase;
import model.Recipe;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class DishControllerTxt implements DishController{
    private DishBase defaultDishBase;

    public DishControllerTxt() {
        defaultDishBase = new DishBase();
    }

    public ArrayList<String> getDishesWithType(int a){
        return defaultDishBase.getDishesWithType(a);
    }

    public boolean contains(String name){
        return defaultDishBase.contains(name);
    }
    public void addDish(String dishName, ArrayList<Integer> ingredients, ArrayList<Integer> weights, int peopleAmount, int dishType){
        defaultDishBase.addDish(dishName, new Recipe(new FoodControllerTxt(defaultDishBase.getFoodBase()), ingredients, weights, peopleAmount), dishType);
    }

    public Dish getDish(String dishName){
        return defaultDishBase.getDish(dishName);
    }
    public void saveToFile(String file){
        defaultDishBase.saveToFile(file);
    }

    public String getReadableInfo() {
        return defaultDishBase.getReadableInfo();
    }
}
