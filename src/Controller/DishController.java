package Controller;

import model.DishBase;
import model.FoodBase;

import java.util.ArrayList;

public class DishController {
    private DishBase defaultDishBase;

    public DishController() {
        defaultDishBase = new DishBase();
    }

    public DishBase getDishBase() {
        return defaultDishBase;
    }

    public FoodBase getFoodBase(){
        return defaultDishBase.getFoodBase();
    }

    public ArrayList<String> getDishesWithType(int a){
        return defaultDishBase.getDishesWithType(a);
    }
}
