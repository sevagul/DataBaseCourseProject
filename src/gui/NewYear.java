package gui;

import model.FoodBase;
import model.FoodStuff;
import new_model.FoodBase1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class NewYear {
    public static void main(String[] args) {
        //new MainFrame();
        FoodBase1 foodBase1 = new FoodBase1("jdbc:mysql://localhost:8889/foodBase");
        foodBase1.connectToDataBase();
        System.out.println(foodBase1.checkIfPresent("Норі"));
        //foodBase1.putStuff(new FoodStuff("Норі", 1000, 0));
    }
}
