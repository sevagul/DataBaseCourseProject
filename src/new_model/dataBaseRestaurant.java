package new_model;

import Utils.Utils;
import model.FoodBase;
import model.FoodStuff;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.sql.*;

public class dataBaseRestaurant {
    private FoodControl foodControl;
    private String dataBase;
    private Connection con;
    public dataBaseRestaurant(String dataBase){
        this.dataBase = dataBase;
        connectToDataBase();
        foodControl = new FoodControl(con);
    }

    public FoodControl getFoodControl() {
        return foodControl;
    }

    //It talks too much, disable it later
    public void connectToDataBase(){
        try{
            String url = dataBase;
            String user = "root";
            String password = "root";
            con = DriverManager.getConnection(url, user, password);
            Statement stm = con.createStatement();
            String query = "Select * from products";
            ResultSet res = stm.executeQuery(query);
            while(res.next()) {
                System.out.println(res.getString("name"));
            }
            stm.close();

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    //This function is needed only while changing the dataBase from local to mySQL

}
