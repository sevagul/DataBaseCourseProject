package new_model;

import Utils.Utils;
import model.FoodStuff;

import java.io.*;
import java.sql.*;

public class FoodBase1 {
    private String dataBase;
    private Connection con;
    public FoodBase1(String dataBase){
        this.dataBase = dataBase;
    }
    public void putStuff(FoodStuff stuff) {
        String id = String.valueOf(stuff.getId());
        String name = stuff.getName();
        String price = String.valueOf(stuff.getPrice());

        try{
            Statement stm = con.createStatement();
            String query = "INSERT INTO products VALUES(NULL, '" + name + "', '" + price + "')";
            System.out.println(query);
            if (!checkIfPresent(name)) { stm.executeUpdate(query); }
            else{
                throw new Exception("Product is already present, can't add it");
            }
            stm.close();

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public boolean checkIfPresent(String name){
        boolean answ=false;
        try{
            Statement stm = con.createStatement();
            String query = "SELECT * FROM products WHERE name='" + name + "'";
            ResultSet res = stm.executeQuery(query);
            if(res.next()){ answ = true; }
            stm.close();

        } catch (Exception ex){
            ex.printStackTrace();
        }
        return answ;
    }

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
}
