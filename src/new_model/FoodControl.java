/*package new_model;

import model.FoodBase;
import model.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class FoodControl extends ControlTable{
    private Connection con;

    public FoodControl(String dataBase, String user, String password)
    {
        super( "products", dataBase, user, password);
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

    public Product getProduct(int id){
        try{
            ResultSet res = getItem(id);
            if(res == null){
                throw new Exception("Failed get Product with id" + String.valueOf(id));
            }
            String name = res.getString("name");
            int price = res.getInt("price");
            return new Product(name, price, id);

        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public ResultSet getItem(int id){
        try{
            String id_str = String.valueOf(id);
            String id_full =  id_str;
            Statement stm = con.createStatement();
            String query = "SELECT * FROM " + tableName + " WHERE product_id='" + id_str + "'";
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
    public Product getProduct(String name){
        try{
            ResultSet res = getItem(name);
            if(res == null){
                throw new Exception("Failed get Product with name" + name);
            }
            int id = res.getInt("id");
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
            Statement stm = con.createStatement();
            String query = "Select * from products";
            ResultSet res = stm.executeQuery(query);
            while(res.next()) {
                answer += res.getString("id") + ". ";
                answer += res.getString("name") + ". ";
                answer += res.getString("price") + "грн(за кг/літр)\n";
            }
            stm.close();

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
