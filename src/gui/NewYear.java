package gui;

import Controller.FoodController;
import Controller.FoodControllerTxt;
import model.FoodBase;
import model.Product;
import new_model.DbController;
import new_model.FoodControllerSql;
import new_model.dataBaseRestaurant;

import java.sql.Connection;

public class NewYear {
    private static String dataBase = "jdbc:mysql://localhost:8889/restaurant?useSSL=false";
    public static void main(String[] args) {
        //new MainFrame();
        DbController dbController = new DbController(dataBase);
        //System.out.println(dbController.select("SELECT * from products limit 5"));
        dbController.update(dbController.connectToDataBase(), "SELECT * from products");
        System.out.println(dbController.select("SELECT * from products"));
    }
}
