package new_model;

import model.FoodBase;
import model.FoodStuff;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class FoodControl {
    private Connection con;

    public FoodControl(Connection con) {
        this.con = con;
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

    public void dropProduct(String name){
        try{
            Statement stm = con.createStatement();
            String query = "DELETE FROM products WHERE name='" + name + "'";
            stm.executeUpdate(query);
            stm.close();

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public FoodStuff getFoodStuff(int id){
        try{
            String id_str = String.valueOf(id);
            Statement stm = con.createStatement();
            String query = "SELECT * FROM products WHERE id='" + id_str + "'";
            ResultSet res = stm.executeQuery(query);
            if(!res.next()){
                throw new Exception("Failed finding a FoodStuff with id " + id_str + " in the table products");
            }
            String name = res.getString("name");
            int price = res.getInt("price");
            stm.close();
            return new FoodStuff(name, price, id);

        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public FoodStuff getFoodStuff(String name){
        try{
            Statement stm = con.createStatement();
            String query = "SELECT * FROM products WHERE name='" + name + "'";
            ResultSet res = stm.executeQuery(query);
            if(!res.next()){
                throw new Exception("Failed finding a FoodStuff with name " + name + " in the table products");
            }
            int id = res.getInt("id");
            int price = res.getInt("price");
            stm.close();
            return new FoodStuff(name, price, id);

        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public void addAllFromFile(String file){
        FoodBase foodBase = new FoodBase();
        for (FoodStuff foodStuff: foodBase){
            putStuff(foodStuff);
        }
    }
}
