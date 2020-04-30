package gui.dishAdding;

import Controller.DishController;
import gui.holidayAdding.TextPanel;

import javax.swing.*;
import java.awt.*;

public class DatabasesPanel extends JPanel {
    TextPanel textPanel1;
    TextPanel textPanel2;
    DishController dishController;
    public DatabasesPanel(DishController dishController){
        this.dishController = dishController;
        setLayout(new BorderLayout());
        textPanel1 = new TextPanel();
        textPanel2 = new TextPanel();
        textPanel1.setText(dishController.getFoodBase().getReadableInfo());
        textPanel2.setText(dishController.getDishBase().getReadableInfo());



        add(textPanel1, BorderLayout.EAST);
        add(textPanel2, BorderLayout.CENTER);

    }
    public void refresh(){
        textPanel1.setText(dishController.getFoodBase().getReadableInfo());
        textPanel2.setText(dishController.getDishBase().getReadableInfo());
    }
}
