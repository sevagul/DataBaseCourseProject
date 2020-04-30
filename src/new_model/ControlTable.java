package new_model;

import model.FoodStuff;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ControlTable {
    private String tableName;
    private Connection con;

    public ControlTable(String tableName, Connection con) {
        this.tableName = tableName;
        this.con = con;
    }

    public void putRecord(ArrayList<String> values) {
        try{
            String name = values.get(0);
            Statement stm = con.createStatement();
            String query = "INSERT INTO " + tableName + " VALUES(NULL";
            for (String value : values){
                query += ", '" + value + "'";
            }
            query += ")";
            System.out.println(query);
            if (!checkIfPresent(name)) { stm.executeUpdate(query); }
            else{
                throw new Exception("Item" + name + " is already present in table " + tableName + ", you can't add it");
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
            String query = "SELECT * FROM " + tableName + " WHERE name='" + name + "'";
            ResultSet res = stm.executeQuery(query);
            if(res.next()){ answ = true; }
            stm.close();

        } catch (Exception ex){
            ex.printStackTrace();
        }
        return answ;
    }

    public void dropItem(String name){
        try{
            Statement stm = con.createStatement();
            String query = "DELETE FROM " + tableName + " WHERE name='" + name + "'";
            stm.executeUpdate(query);
            stm.close();

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public ResultSet getItem(int id){
        try{
            String id_str = String.valueOf(id);
            Statement stm = con.createStatement();
            String query = "SELECT * FROM " + tableName + " WHERE id='" + id_str + "'";
            ResultSet res = stm.executeQuery(query);
            if(!res.next()){
                throw new Exception("Failed finding an item with id " + id_str + " in the table" + tableName);
            }
            return res;

        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public ResultSet getItem(String name){
        try{
            Statement stm = con.createStatement();
            String query = "SELECT * FROM " + tableName + " WHERE name='" + name + "'";
            ResultSet res = stm.executeQuery(query);
            if(!res.next()){
                throw new Exception("Failed finding an item with name " + name + " in the table " + tableName);
            }
            return res;

        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
