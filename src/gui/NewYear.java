package gui;

import model.FoodStuff;
import new_model.FoodControl;
import new_model.dataBaseRestaurant;

public class NewYear {
    private static String dataBase = "jdbc:mysql://localhost:8889/restaurant";
    public static void main(String[] args) {
        new MainFrame();
        /*dataBaseRestaurant dataBaseRestaurant = new dataBaseRestaurant(dataBase);
        FoodControl foodControl = dataBaseRestaurant.getFoodControl();
        System.out.println(foodControl.getFoodStuff(32));
        System.out.println(foodControl.getFoodStuff("Хліб"));
        foodControl.putStuff(new FoodStuff("Норі", 130, 0));
        foodControl.dropProduct("Норі");*/
    }
}
