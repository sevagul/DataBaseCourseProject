package Controller;

import model.FoodBase;

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
}
