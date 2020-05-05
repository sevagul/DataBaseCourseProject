package gui.dishAdding;

import Controller.DishController;
import Controller.FoodController;
import gui.holidayAdding.TextPanel;

import javax.swing.*;
import java.awt.*;

public class DatabasesPanel extends JPanel {
    TextPanel textPanel1;
    TextPanel textPanel2;
    DishController dishController;
    FoodController foodController;
    public DatabasesPanel(DishController dishController, FoodController foodController){
        this.dishController = dishController;
        this.foodController = foodController;
        setLayout(new BorderLayout());
        textPanel1 = new TextPanel();
        textPanel2 = new TextPanel();
        refresh();



        add(textPanel1, BorderLayout.EAST);
        add(textPanel2, BorderLayout.CENTER);

    }
    public void refresh(){
        textPanel1.setText(foodController.getInfo());
        textPanel2.setText(dishController.getReadableInfo());
    }
}
