package Controller;

import gui.holidayAdding.FormEvent;
import model.Dish;
import model.Holiday;
import new_model.controllers.DishControllerSql;
import new_model.controllers.FoodControllerSql;

import java.util.ArrayList;

public class MainController {
    Holiday holiday;
    DishController dishController;
    FoodController foodController;

    public MainController(){
        String dataBase = "jdbc:mysql://localhost:8889/restaurant?useSSL=false";
        dishController = new DishControllerSql(dataBase);
        foodController = new FoodControllerSql(dataBase);
    }

    public void setHoliday(FormEvent event){
        ArrayList<Dish> dishes = new ArrayList<Dish>();
        for(String dishName: event.getDishes()){
            dishes.add(dishController.getDish(dishName));
        }
        holiday = new Holiday(event.getHolidayName(), event.getPeopleAmount(), dishes);
    }

    public ArrayList<String> getDishes(){
        ArrayList<String> answer = new ArrayList<String>();
        for(Dish dish: holiday.getDishes())
        {
            answer.add(dish.getName());
        }
        return answer;
    }

    public String getInfo(){
        String answer;
        answer = "  Свято: " + holiday.getName() + "\n  Страви: \n";
        for(Dish dish: holiday.getDishes()){
            answer = answer.concat(dish.getName() + " \n");
        }
        answer = answer.concat("  Кількість людей: " + holiday.getPeopleAmount() + "\n");
        answer = answer.concat("  Приблизна вартість: " + holiday.countPrice() + "\n");
        answer += "Список Необхідних Продуктів:\n";
        answer += holiday.getProductsInfo();
        return answer;
    }

    public DishController getDishController() {
        return dishController;
    }

    public FoodController getFoodController() {
        return foodController;
    }
    public ArrayList<String> getDishesWithType(int a){
        return dishController.getDishesWithType(a);
    }
}
