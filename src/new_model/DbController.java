package new_model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class DbController {
    private String dataBase;
    private String user = "root";
    private String password = "root";
    public DbController(String dataBase, String user, String password) {
        this.dataBase = dataBase;
        this.user = user;
        this.password = password;
    }

    public DbController(String dataBase) {
        this.dataBase = dataBase;
        System.out.println(this.user);
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

    public SelectResult select(Connection conn, String selectQuery){
        SelectResult selectResult = new SelectResult();
        try {
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            ResultSet rs = stmt.executeQuery(selectQuery);

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            while (rs.next()) {
                HashMap<String,String> eachResult = new HashMap<String,String>();
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

    public void update(Connection conn, String updateQuery){
        try {
            PreparedStatement stmt = conn.prepareStatement(updateQuery);
            stmt.executeUpdate(updateQuery);
        } catch(SQLException sqlExc) {
            System.out.println(sqlExc.getMessage());
        }
    }

    public SelectResult select(String selectQuery){
        SelectResult selectResult = new SelectResult();
        Connection con = connectToDataBase();
        if (con != null){
            selectResult = select(con, selectQuery);
        }
        return selectResult;
    }

    public void testConnection(Connection con, String tableName, String columnName){
        try {
            //Testing the connection
            String query = "Select * from " + tableName;
            SelectResult res = select(con, query);
            System.out.println(res);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



//    public void printRes(ArrayList<HashMap<String, String>> res){
//        for(HashMap<String, String> hm: res){
//            System.out.println("");
//            for (Map.Entry mapElement : hm.entrySet()) {
//                String key = (String) mapElement.getKey();
//                String value = (String) mapElement.getValue();
//                System.out.println(key + " : " + value);
//            }
//        }
//    }
}
