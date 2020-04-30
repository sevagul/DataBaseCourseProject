package Controller;

import gui.holidayAdding.FormEvent;
import model.DishBase;
import model.Dish;
import model.Holiday;

import java.util.ArrayList;

public class MainController {
    Holiday holiday;
    DishController dishController;
    FoodController foodController;

    public MainController(){
        dishController = new DishController();
        foodController = new FoodController(dishController.getDishBase().getFoodBase());
        System.out.println(foodController.getFoodBase().size());
    }

    public void setHoliday(FormEvent event){
        ArrayList<Dish> dishes = new ArrayList<Dish>();
        for(String dishName: event.getDishes()){
            dishes.add(dishController.getDishBase().getDish(dishName));
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
    public int getPrice(){
        return holiday.countPrice();
    }
    public String getHolidayName(){
        return holiday.getName();
    }
    public int getPeopleAmount(){
        return holiday.getPeopleAmount();
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
        dishController.getDishBase().saveToFile("Base.txt");
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
