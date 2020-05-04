package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Recipe extends HashMap<Product, Integer> {
    private FoodBase foodBase;

    public Recipe(){
        super();
    }

    public Recipe(FoodBase foodBase) {
        this.foodBase = foodBase;
    }

    public Recipe(FoodBase foodBase, int[] ingredients, int[] weights, int peopleAmount){
        this.foodBase = foodBase;
        if(ingredients.length != weights.length)
            return;
        for(int i = 0; i < ingredients.length; i++){
            Product product = foodBase.getById(ingredients[i]);
            if(product == null || weights[i] < 0)
                return;
        }
        for(int i = 0; i < ingredients.length; i++){
            Product product = foodBase.getById(ingredients[i]);
            put(product, (int) Math.ceil(weights[i]/(double)(peopleAmount)));
        }
    }

    public Recipe(FoodBase foodBase, ArrayList<Integer> ingredients, ArrayList<Integer> weights, int peopleAmount){
        this.foodBase = foodBase;
        if(ingredients.size() != weights.size())
            return;
        for(int i = 0; i < ingredients.size(); i++){
            Product product = foodBase.getById(ingredients.get(i));
            if(product == null || weights.get(i) < 0)
                return;
        }
        for(int i = 0; i < ingredients.size(); i++){
            Product product = foodBase.getById(ingredients.get(i));
            put(product, (int) Math.ceil(weights.get(i)/(double)(peopleAmount)));
        }
    }

    public boolean addIngredients(int[] ingredients, int[] weights, int peopleAmount){
        if(ingredients.length != weights.length)
            return false;
        for(int i = 0; i < ingredients.length; i++){
            put(foodBase.get(ingredients[i]), (int) (weights[i]/(double)(peopleAmount)));
        }
        return true;
    }
    public double getWeight(Product product) {
        return get(product);
    }
}
