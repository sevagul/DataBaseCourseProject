package gui.dishAdding.TableDishes;

import model.Recipe;

public interface DishTableListener {
    void askedToShowTheReceipe(int id);
    void deletedRecipe();
}
