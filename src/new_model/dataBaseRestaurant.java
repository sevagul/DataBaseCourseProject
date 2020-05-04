package new_model;

import Controller.FoodController;

import java.sql.*;

public class dataBaseRestaurant {
    private FoodControllerSql foodController;
    private String dataBase;
    private String user = "root";
    private String password = "root";
    //private Connection con;
    public dataBaseRestaurant(String dataBase, String user, String password){
        this.dataBase = dataBase;
        this.password=password;
        this.user=user;
        foodController = new FoodControllerSql(dataBase, user, password);
    }
    public dataBaseRestaurant(String dataBase){
        this.dataBase = dataBase;
        foodController = new FoodControllerSql(dataBase, user, password);
    }

    public FoodControllerSql getFoodController() {
        return foodController;
    }

    //It talks too much, disable it later
    /*public void connectToDataBase(){
        try{
            con = DriverManager.getConnection(dataBase, user, password);
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
    }*/
    //This function is needed only while changing the dataBase from local to mySQL

}
