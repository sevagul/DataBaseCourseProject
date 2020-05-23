package model;

import Controller.FoodController;

import java.util.ArrayList;
import java.util.HashMap;

public class Recipe extends HashMap<Product, Integer> {
    private FoodController foodController;
    int peopleAmount;

    public Recipe(){
        super();
    }

    public Recipe(FoodController foodController) {
        this.foodController = foodController;
    }

    public Recipe(FoodController foodController, int[] ingredients, int[] weights, int peopleAmount){
        this.peopleAmount = peopleAmount;
        this.foodController = foodController;
        if(ingredients.length != weights.length)
            return;
        for(int i = 0; i < ingredients.length; i++){
            Product product = foodController.getProduct(ingredients[i]);
            if(product == null || weights[i] < 0)
                return;
        }
        for(int i = 0; i < ingredients.length; i++){
            Product product = foodController.getProduct(ingredients[i]);
            put(product, weights[i]);
        }
    }

    public Recipe(FoodController foodController, ArrayList<Integer> ingredients, ArrayList<Integer> weights, int peopleAmount){
        this.peopleAmount = peopleAmount;
        this.foodController = foodController;
        if(ingredients.size() != weights.size())
            return;
        for(int i = 0; i < ingredients.size(); i++){
            Product product = foodController.getProduct(ingredients.get(i));
            if(product == null || weights.get(i) < 0)
                return;
        }
        for(int i = 0; i < ingredients.size(); i++){
            Product product = foodController.getProduct(ingredients.get(i));
            put(product, weights.get(i));
        }
    }

    public boolean addIngredients(int[] ingredients, int[] weights, int peopleAmount){
        if(ingredients.length != weights.length)
            return false;
        for(int i = 0; i < ingredients.length; i++){
            put(foodController.getProduct(ingredients[i]), weights[i]);
        }
        return true;
    }
    public double getWeight(Product product) {
        return get(product);
    }
}
