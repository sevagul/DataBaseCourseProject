package model;

import Utils.Utils;

import java.io.*;
import java.util.ArrayList;

public class DishBase extends ArrayList<Dish> {
    FoodBase foodBase;
    public DishBase(){
        super();
        foodBase = new FoodBase();
        readFromFile("Base.txt");
    }
    public Dish getDish(String name){
        for(Dish dish: this){
            if(dish.getName().equals(name))
                return dish;
        }
        return null;
    }

    public void addDish(String name, Recipe recipe, int type){
        add(new Dish(name, recipe, type));
        saveToFile("Base.txt");
    }
    public void addDish(String name, Recipe recipe, int type, int id){
        add(new Dish(name, recipe, type, id));
    }

    public FoodBase getFoodBase() {
        return foodBase;
    }

    public String getReadableInfo(){
        String answer = "";
        answer += (("Блюда:") + "\n");
        for(Dish dish: this){
            answer +=((dish.getId()) + ". ");
            answer +=((dish.getName()) + " ");
            answer += ("Рецепт:\n") ;
            for(FoodStuff foodStuff: dish.keySet()){
                answer +=(foodStuff.getName() + " + ");
            }
            answer += "Кінець" + "\n";
        }
        return answer;
    }

    public ArrayList<String> getDishesWithType(int a){
        ArrayList<String> answer = new ArrayList<>();
        for(Dish dish: this){
            if(dish.getType() == a)
                answer.add(dish.getName());
        }
        return answer;
    }

    public boolean contains(String name){
        for(Dish dish:this){
            if(dish.getName().equals(name))
                return true;
        }
        return false;
    }


    public void saveToFile(String file){
        try{
            PrintWriter pw;
            pw = new PrintWriter(file);
            pw.println("Dishes:");
            for(Dish dish: this){
                pw.println(dish.getId());
                pw.println(dish.getName());
                pw.println(dish.getType());
                pw.println("Recipe:");
                for(FoodStuff foodStuff: dish.keySet()){
                    pw.println(foodStuff.getId());
                    pw.println(dish.get(foodStuff));
                }
                pw.println("Recipe end");
            }
            pw.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void readFromFile(String file){
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            clear();
            String datal = br.readLine();
            datal = br.readLine();
            while(datal != null && !datal.equals("") )
            {
                int id = Utils.stringToNatural(datal);
                String name = br.readLine();
                int type = Utils.stringToNatural(br.readLine());
                ArrayList<Integer> ingr = new ArrayList<>();
                ArrayList<Integer> weights = new ArrayList<>();
                datal = br.readLine();
                datal = br.readLine();
                while(datal != null && !datal.equals("Recipe end"))
                {
                    int prodId = Utils.stringToNatural(datal);
                    int prodAmount = Utils.stringToNatural(br.readLine());
                    weights.add(prodAmount);
                    ingr.add(prodId);
                    datal = br.readLine();
                }
                datal = br.readLine();
                addDish(name, new Recipe(foodBase, ingr, weights, 1), type, id);
            }
            br.close();
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch(IOException f){
            System.out.println(f.getMessage());
        }
    }
}

