/*package new_model;

import Controller.FoodController;
import model.FoodBase;
import model.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class FoodControllerSql extends ControlTable implements FoodController {

    //public FoodControllerSql(Connection con) {
    //    super( "products", con);
    //}
    public static String myName = "products";
    public FoodControllerSql(String dataBase, String user, String password)
    {
        super( myName, dataBase, user, password);
    }
    public FoodControllerSql(String dataBase)
    {
        super( myName, dataBase);
    }

    public void putProduct(Product product) {
        ArrayList<String> values = product.getValues();
        putRecord(values);
    }

    public void putProduct(String name, int price) {
        ArrayList<String> values = new ArrayList<String>();
        values.add(name);
        values.add(String.valueOf(price));
        putRecord(values);
    }

    public ResultSet getItem(int id){
        try{
            Connection con = connectToDataBase();
            String id_str = String.valueOf(id);
            String id_full =  id_str;
            Statement stm = con.createStatement();
            String query = "SELECT * FROM " + tableName + " WHERE product_id='" + id_str + "'";
            ResultSet res = stm.executeQuery(query);
            if(!res.next()){
                throw new Exception("Failed finding an item with id " + id_str + " in the table" + tableName);
            }
            //stm.close();
            con.close();
            return res;

        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public Product getProduct(int id){
        try{
            ResultSet res = getItem(id);
            if(res == null){
                throw new Exception("Failed get Product with id_product" + String.valueOf(id));
            }
            String name = res.getString("name");
            int price = res.getInt("price");
            return new Product(name, price, id);

        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public Product getProduct(String name){
        try{
            ResultSet res = getItem(name);
            if(res == null){
                throw new Exception("Failed get Product with name" + name);
            }
            int id = res.getInt("product_id");
            int price = res.getInt("price");
            return new Product(name, price, id);

        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public String getInfo(){
        String answer = "У базі є такі продукти:\n";
        try{
            Connection con = connectToDataBase();
            Statement stm = con.createStatement();
            String query = "Select * from products";
            ResultSet res = stm.executeQuery(query);
            while(res.next()) {
                answer += res.getString("product_id") + ". ";
                answer += res.getString("name") + ". ";
                answer += res.getString("price") + "грн(за кг/літр)\n";
            }
            stm.close();
            con.close();

        } catch (Exception ex){
            ex.printStackTrace();
        }
        return answer;
    }

    public void addAllFromFile(String file){
        FoodBase foodBase = new FoodBase();
        for (Product product: foodBase){
            putProduct(product);
        }
    }


}*/
