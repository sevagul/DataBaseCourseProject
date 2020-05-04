package new_model;

import javax.swing.text.Utilities;
import javax.swing.text.html.parser.Parser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ControlTable {
    protected String tableName;
    //protected Connection con;
    private String dataBase;
    private String user = "root";
    private String password = "root";

//    public ControlTable(String tableName, Connection con) {
//        this.tableName = tableName;
//        //this.con = con;
//    }

    public ControlTable(String tableName, String dataBase, String user, String password) {
        this.tableName = tableName;
        this.dataBase = dataBase;
        this.user = user;
        this.password = password;
    }

    public ControlTable(String tableName, String dataBase) {
        this.tableName = tableName;
        this.dataBase = dataBase;
        System.out.println(this.user);
    }

    public void putRecord(ArrayList<String> values) {
        try{
            Connection con = connectToDataBase();
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
            con.close();

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public boolean checkIfPresent(String name){
        return checkIfPresent("name", name);
    }

    public boolean checkIfPresent(String col, String value){
        boolean answ=false;
        try{
            Connection con = connectToDataBase();
            Statement stm = con.createStatement();
            String query = "SELECT * FROM " + tableName + " WHERE " + col + "='" + value + "'";
            ResultSet res = stm.executeQuery(query);
            if(res.next()){ answ = true; }
            stm.close();
            con.close();

        } catch (Exception ex){
            ex.printStackTrace();
        }
        return answ;
    }

    public void dropItem(String name){
        dropItem("name", name);
    }

    public void dropItem(String col, String value){
        try{
            Connection con = connectToDataBase();
            Statement stm = con.createStatement();
            String query = "DELETE FROM " + tableName + " WHERE " + col + "='" + value + "'";
            System.out.println(query);
            stm.executeUpdate(query);
            stm.close();
            con.close();

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public ResultSet getItem(String name){
        return getItem("name", name);
    }

    public ResultSet getItem(String col, String value){
        try{
            Connection con = connectToDataBase();

            Statement stm = con.createStatement();
            String query = "SELECT * FROM " + tableName + " WHERE " + col + "='" + value + "'";
            ResultSet res = stm.executeQuery(query);
            if(!res.next()){
                throw new Exception("Failed finding an item with " + col + " " + value + " in the table " + tableName);
            }
            //stm.close();
            //con.close();
            return res;

        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

     public void updateItem(String name, String column, String value){
        updateItem("name", name, column, value);
     }

    public void updateItem(String colIndex, String valIndex, String colUp, String valNew) {
        try {
            Connection con = connectToDataBase();
            Statement stm = con.createStatement();
            String query = "UPDATE " + tableName + " SET " + colUp + "='" + valNew + "' WHERE " + colIndex + "='" + valIndex + "'";
            stm.executeUpdate(query);
            stm.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Failed updating an item with " + colIndex + " = " + valIndex + " in the table " + tableName);
        }
    }

    public Connection connectToDataBase() {
        try {
            Connection con = DriverManager.getConnection(dataBase, user, password);
            //Testing the connection
            Statement stm = con.createStatement();
            String query = "Select * from products limit 5";
            ResultSet res = stm.executeQuery(query);
            while (res.next()) {
                System.out.println(res.getString("name"));
            }
            stm.close();
            //Ended tesiing
            return con;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
