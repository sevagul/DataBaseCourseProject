package gui.dishAdding;

public class FormEvent {
    private String dishName;
    private int ingredient;
    private int amount;
    private int peopleAmount;
    private int dishType;

    public FormEvent(String dishName, int ingredient, int amount, int peopleAmount, int dishType) {
        this.dishName = dishName;
        this.ingredient = ingredient;
        this.amount = amount;
        this.peopleAmount = peopleAmount;
        this.dishType = dishType;
    }

    public String getDishName() {
        return dishName;
    }

    public int getIngredient() {
        return ingredient;
    }

    public int getAmount() {
        return amount;
    }

    public int getPeopleAmount() {
        return peopleAmount;
    }

    public int getDishType() {
        return dishType;
    }
}