package gui;

import new_model.controllers.DbController;
import new_model.dbClasses.KeyVals;

public class NewYear {
    private static String dataBase = "jdbc:mysql://localhost:8889/restaurant?useSSL=false";
    private static String products = "Products";
    public static void main(String[] args) {
        new MainFrame();
//        DbController dbController = new DbController(dataBase);
//        dbController.insertIntoTable(products, new String[] {"NULL", "Укроп", "35"});
//        dbController.updateTable(products, new KeyVals("price", "40"), new KeyVals("name", "Укроп Зелений"));
//        dbController.deleteFromTable(products, "name='Укроп'");
//
//        System.out.println(dbController.selectFromTable("Products", "COUNT(name)", "name='Укроп Зелений'"));

    }
}
