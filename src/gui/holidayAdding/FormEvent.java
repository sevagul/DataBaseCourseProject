package gui.holidayAdding;

import java.util.ArrayList;

public class FormEvent {
    private String holidayName;
    private int peopleAmount;
    private ArrayList<String> dishes;

    public FormEvent(int peopleAmount, ArrayList<String> dishes, String holidayName){
        this.peopleAmount = peopleAmount;
        this.dishes = dishes;
        this.holidayName = holidayName;
    }

    public int getPeopleAmount() {
        return peopleAmount;
    }

    public void setPeopleAmount(int peopleAmount) {
        this.peopleAmount = peopleAmount;
    }

    public ArrayList<String> getDishes() {
        return dishes;
    }

    public void setDishes(ArrayList<String> dishes) {
        this.dishes = dishes;
    }

    public String getHolidayName() {
        return holidayName;
    }
}
