package gui.dishAdding;

public class FormEvent {
    private String dishName;
    private String ingredient;
    private int amount;
    private int peopleAmount;
    private int dishType;

    public FormEvent(String dishName, String ingredient, int amount, int peopleAmount, int dishType) {
        this.dishName = dishName;
        this.ingredient = ingredient;
        this.amount = amount;
        this.peopleAmount = peopleAmount;
        this.dishType = dishType;
    }

    public String getDishName() {
        return dishName;
    }

    public String getIngredient() {
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