package gui;

import Controller.FoodController;
import Controller.FoodControllerTxt;
import model.FoodBase;
import model.Product;
import new_model.FoodControllerSql;
import new_model.dataBaseRestaurant;

public class NewYear {
    private static String dataBase = "jdbc:mysql://localhost:8889/restaurant";
    public static void main(String[] args) {
        //new MainFrame();
        dataBaseRestaurant dataBaseRestaurant = new dataBaseRestaurant(dataBase);
        FoodControllerSql foodControl = dataBaseRestaurant.getFoodController();
        System.out.println(foodControl.getProduct(32));
        System.out.println(foodControl.getProduct("Хліб"));
        foodControl.putProduct(new Product("Норі", 130, 0));
        foodControl.dropItem("Норі");
        System.out.println(foodControl.getInfo());

    }
}
