package new_model.controllers;

public class DishController {
    private String dataBase;
    private TableController dishTableController;
    private TableController ingedientsTableController;

    public DishController(String database) {
        dishTableController = new TableController(dataBase, "Dishes");
        ingedientsTableController = new TableController(dataBase, "Ingredients");
    }
}
