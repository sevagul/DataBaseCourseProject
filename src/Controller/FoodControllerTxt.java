package Controller;

import Utils.Utils;
import model.FoodBase;
import model.Product;

public class FoodControllerTxt implements FoodController {
    private FoodBase foodBase;
    public FoodControllerTxt(FoodBase foodBase){
        this.foodBase = foodBase;
    }

    public void putProduct(String name, int price){
        foodBase.putProduct(name, price);
        foodBase.writeToFile("FoodBase.txt");
    }

    public String getInfo(){
        String answer = "У базі є такі продукти:\n";
        String[] lines = foodBase.getInfo().split("\n");
        for(String line: lines){
            String[] words = line.split("\t");
            answer += words[0] + ". " + words[1] + ". " + words[2] + " грн(за кг/літр)\n";
        }
        return answer;
    }

    public int count(String col, String val){
        int id = Utils.stringToNatural(val);
        if (id >=0 && id <= foodBase.getNextId()) return 1;
        return 0;
    }
    public String getName(int id){
        return foodBase.getById(id).getName();
    }

    @Override
    public Product getProduct(int ingredient) {
        return foodBase.getById(ingredient);
    }
}
