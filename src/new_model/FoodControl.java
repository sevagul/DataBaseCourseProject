package new_model;

import model.FoodBase;
import model.FoodStuff;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class FoodControl extends ControlTable{
    private Connection con;

    public FoodControl(Connection con) {
        super( "products", con);
    }

    public void putStuff(FoodStuff stuff) {
        ArrayList<String> values = stuff.getValues();
        putRecord(values);
    }

    public FoodStuff getFoodStuff(int id){
        try{
            ResultSet res = getItem(id);
            if(res == null){
                throw new Exception("Failed get Foodstuff with id" + String.valueOf(id));
            }
            String name = res.getString("name");
            int price = res.getInt("price");
            return new FoodStuff(name, price, id);

        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public FoodStuff getFoodStuff(String name){
        try{
            ResultSet res = getItem(name);
            if(res == null){
                throw new Exception("Failed get Foodstuff with name" + name);
            }
            int id = res.getInt("id");
            int price = res.getInt("price");
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
