package new_model.controllers;

import Controller.DishController;
import Controller.FoodController;
import Utils.Utils;
import model.Dish;
import model.Product;
import model.Recipe;
import new_model.dbClasses.KeyVals;
import new_model.dbClasses.SelectResult;

import java.util.ArrayList;
import java.util.HashMap;

public class DishControllerSql extends TableController implements DishController {
    private TableController ingedientsTableController;
    private FoodController foodController;
    private String GetDishesWithTypeProcedure = "GetDishesByTypeId";
    private String GetPeopleAmountByDishIdProcedure = "GetPeopleAmountByDishId";
    private String GetDishPriceProcedure = "GetDishPrice";
    private String GetRecipeByIdProcedure = "GetRecipeById";


    public DishControllerSql(String database) {
        super(database, "Dishes");
        ingedientsTableController = new TableController(dataBase, "Ingredients");
        foodController = new FoodControllerSql(dataBase);
    }

    @Override
    public String getReadableInfo() {
        String answ = "";
        SelectResult res = select(new String[] {"name"});
        for (KeyVals name:
             res) {
            answ += getDish(name.get("name")).getInfo();
        }
        return answ;
    }

    @Override
    public Dish getDish(String dishName) {
        if (!contains("name", dishName))
            return null;
        KeyVals resDish = selectOne(ALL, "name='" + dishName + "'");
        if (resDish == null)
            return null;
        int id = resDish.getInt("dish_id");
        int type = resDish.getInt("dish_type_id");
        int people = resDish.getInt("people_number");
        String name = resDish.get("name");
        Recipe recipe = getReciepe(id, people);
        return new Dish(name, recipe, type);

    }

    public Dish getDish(int dishId) {
        if (!contains("dish_id", dishId+"")) {
            return null;
        }
        KeyVals resDish = selectOne(ALL, "dish_id='" + dishId + "'");
        if (resDish == null)
            return null;
        int id = resDish.getInt("dish_id");
        int type = resDish.getInt("dish_type_id");
        int people = resDish.getInt("people_number");
        String name = resDish.get("name");
        Recipe recipe = getReciepe(id, people);
        return new Dish(name, recipe, type);
    }

    public Recipe getReciepe(int dishId, int people) {
        ArrayList<Integer> ingredients = new ArrayList<>();
        ArrayList<Integer> weights = new ArrayList<>();
        String idStr = String.valueOf(dishId);
        if (!contains("dish_id", idStr))
            return null;
        //SelectResult resIngr = ingedientsTableController.select(ALL, "dish_id='" + idStr + "'");
        SelectResult resIngr = call(GetRecipeByIdProcedure, dishId+"");
        int numberOfPeople = getPeopleAmountById(dishId);
        if (people == 0){
            people = numberOfPeople;
        }
        for (KeyVals ingredient:
             resIngr) {
            int productId = ingredient.getInt("product_id");
            int amount = (ingredient.getInt("amount") * people / numberOfPeople) ;
            ingredients.add(productId);
            weights.add(amount);
        }
        return new Recipe(foodController, ingredients, weights, numberOfPeople);
    }

    @Override
    public ArrayList<String> getDishesWithType(int type) {
        ArrayList<String> answ = new ArrayList<>();
        SelectResult res = call(GetDishesWithTypeProcedure,  type+"");
        for (KeyVals cur:
             res) {
            answ.add(cur.get("name"));
        }
        return answ;
    }

    @Override
    public boolean contains(String name) {
        return contains("name", name);
    }

    @Override
    public void addDish(String dishName, ArrayList<Integer> ingredients, ArrayList<Integer> weights, int peopleAmount, int dishType) {
        if(contains("name", dishName)){
            return;
        }
        if (ingredients.size() != weights.size())
            return;
        addDishName(dishName, dishType, peopleAmount);
        KeyVals dishIdKv = selectOne(ALL, "name='"+dishName+"'");
        String dishId = dishIdKv.get("dish_id");
        for (int i = 0; i < ingredients.size(); i++){
            String ingr = String.valueOf(ingredients.get(i));
            String amount = String.valueOf(weights.get(i));
            ingedientsTableController.insert(new String[] {dishId, ingr, amount});
        }
    }

    public void addDishName(String name, int type, int people){
        ArrayList<String> values = new ArrayList<>();
        values.add("NULL");
        values.add(name);
        values.add(String.valueOf(type));
        values.add(String.valueOf(people));
        insert(values);
    }

    public int getPeopleAmountById(int dishId){
        SelectResult res = call(GetPeopleAmountByDishIdProcedure, dishId+"");
        return res.get(0).getInt("people_number");
    }

}
