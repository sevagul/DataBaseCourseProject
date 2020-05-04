package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Holiday {
    private String name;
    private int peopleAmount;
    private ArrayList<Dish> dishes;

    public Holiday(String name, int peopleAmount, ArrayList<Dish> dishes) {
        this.name = name;
        this.peopleAmount = peopleAmount;
        this.dishes = dishes;
    }

    public int countPrice (){
        int summ = 0;
        for(Dish dish: dishes){
            summ += dish.getPrice();
        }
        return summ * peopleAmount;
    }

    public ArrayList<Dish> getDishes() {
        return dishes;
    }

    public String getName() {
        return name;
    }

    public int getPeopleAmount() {
        return peopleAmount;
    }

    public  String getProductsInfo(){
        String answer = "";
        HashMap<String, Integer> products = new HashMap<>();
        for(Dish dish: dishes){
            for(Product product: dish.keySet()){
                if(!products.keySet().contains(product.getName()))
                    products.put(product.getName(), dish.get(product));
                else{
                    products.put(product.getName()
                            ,products.get(product.getName()) +
                                    dish.get(product.getName()));
                }
            }
        }
        for(String product: products.keySet()){
            answer += (product + " " + products.get(product)*peopleAmount + " гр/мл\n");
        }
        return answer;
    }
}
