package new_model.controllers;

import Utils.Utils;
import new_model.dbClasses.KeyVals;
import new_model.dbClasses.SelectResult;

import java.sql.*;
import java.util.*;

public class DbController {
    protected String dataBase;
    protected String user = "root";
    protected String password = "root";

    public static ArrayList<String> ALL = new ArrayList<>();

    public DbController(String dataBase, String user, String password) {
        this.dataBase = dataBase;
        this.user = user;
        this.password = password;
    }
    public DbController(String dataBase) {
        this.dataBase = dataBase;
        System.out.println(this.user);
    }


    public SelectResult select(Connection conn, String selectQuery){
        SelectResult selectResult = new SelectResult();
        testEcho(selectQuery);
        try {
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            ResultSet rs = stmt.executeQuery(selectQuery);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            while (rs.next()) {
                KeyVals eachResult = new KeyVals();
                for (int i=1; i<=columnCount; i++) {
                    String curCol = rsmd.getColumnName(i);
                    eachResult.put(curCol, rs.getString(curCol));
                } // for
                selectResult.add(eachResult);
            } // while
        } catch(SQLException sqlExc) {
            System.out.println(sqlExc.getMessage());
        }
        return selectResult;
    }
    public SelectResult select(String selectQuery){
        SelectResult selectResult = new SelectResult();
        Connection con = connectToDataBase();
        if (con != null){
            selectResult = select(con, selectQuery);
            closeConnection(con);
        }
        return selectResult;
    }

    public void update(Connection conn, String updateQuery){
        testEcho(updateQuery);
        try {
            PreparedStatement stmt = conn.prepareStatement(updateQuery);
            stmt.executeUpdate(updateQuery);
        } catch(SQLException sqlExc) {
            System.out.println(sqlExc.getMessage());
        }
    }
    public void update(String updateQuery){
        Connection con = connectToDataBase();
        if (con != null){
            update(con, updateQuery);
            closeConnection(con);
        }

    }

    //Select from table block
    public SelectResult selectFromTable(String tableName, ArrayList<String> fields, String condition){
        String query = "SELECT ";
        if(fields.size() == 0){
            query += "* ";
        } else {
            query += fields.get(0);
            for (String field: fields.subList(1, fields.size())) {
                query += ", " + field;
            }
        }
        query += " from " + tableName;
        query += " WHERE " + condition;
        return select(query);
    }
    public SelectResult selectFromTable(String tableName, ArrayList<String> fields){
        return selectFromTable(tableName, fields, "1");
    }
    public SelectResult selectFromTable(String tableName, String[] fields, String conition){
        return selectFromTable(tableName, new ArrayList<>(Arrays.asList(fields)), conition);
    }
    public SelectResult selectFromTable(String tableName, String[] fields){
        return selectFromTable(tableName, fields, "1");
    }
    public SelectResult selectFromTable(String tableName, String field, String condition){
        return selectFromTable(tableName, new String[] {field}, condition);
    }
    public SelectResult selectFromTable(String tableName, String field){
        return selectFromTable(tableName, field, "1");
    }

    //DELETE block
    public void deleteFromTable(String tableName, String condition){
        String query = "DELETE FROM " + tableName + " WHERE " + condition;
        update(query);
    }
    public void deleteFromTable(String tableName, KeyVals condition){
        deleteFromTable(tableName, condition.getUpdateConditionAnd());
    }
    public void deleteFromTable(String tableName, String colIndex, String valIndex){
        deleteFromTable(tableName, new KeyVals(colIndex, valIndex));
    }

    //UPDATE block
    public void updateTable(String tableName, String setting, String condition){
        String query = "UPDATE " + tableName + " SET " + setting + " WHERE " + condition;
        update(query);
    }
    public void updateTable(String tableName, KeyVals setting, String condition){
        updateTable(tableName, setting.getUpdateSetting(), condition);
    }
    public void updateTable(String tableName, KeyVals setting, KeyVals condition){
        updateTable(tableName, setting.getUpdateSetting(), condition.getUpdateConditionAnd());
    }
    public void updateTable(String tableName, String colUp, String newVal, String condition){
        updateTable(tableName, new KeyVals(colUp, newVal), condition);
    }
    public void updateTable(String tableName, String colUp, String newVal, KeyVals condition){
        updateTable(tableName, new KeyVals(colUp, newVal), condition);
    }
    public void updateTable(String tableName, String colUp, String newVal, String colIndex, String valIndex){
        updateTable(tableName, new KeyVals(colUp, newVal), new KeyVals(colIndex, valIndex));
    }


    public void insertIntoTable(String tableName, ArrayList<String> values){
        String query = "INSERT INTO " + tableName + " VALUES(" + values.get(0);
        for (String value : values.subList(1, values.size())){
            query += ", '" + value + "'";
        }
        query += ")";
        update(query);
    }
    public void insertIntoTable(String tableName, String[] values){
        insertIntoTable(tableName, new ArrayList<>(Arrays.asList(values)));
    }

    public int countInTable(String tableName, String condition){
        String query = "SELECT COUNT(*) FROM " + tableName + " WHERE " + condition;
        SelectResult res =  select(query);
        if(res.size() != 1){
            return -1;
        }
        return Utils.stringToNatural(res.get(0).get("COUNT(*)")) ;
    }
    public int countInTable(String tableName, KeyVals condition){
        return countInTable(tableName, condition.getUpdateConditionAnd());
    }
    public int countInTable(String tableName, String colIndex, String valIndex){
        return countInTable(tableName, new KeyVals(colIndex, valIndex));
    }

    public Connection connectToDataBase() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(dataBase, user, password);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return con;
    }
    public void closeConnection(Connection con){
        try{
            con.close();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public void testEcho(String query){
        System.out.println("\t" + query);
    }
    protected String deleteLastComa(String str){
        return str.strip().substring(0, str.length() - 2);
    }
}
