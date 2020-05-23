package gui.dishAdding;

import Controller.DishController;
import Controller.FoodController;
import gui.dishAdding.TableDishes.DishTableListener;
import gui.dishAdding.TableDishes.TableDishesPanel;
import gui.dishAdding.tableProducts.TableProductsPanel;
import gui.holidayAdding.TextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DatabasesPanel extends JPanel {
    TableProductsPanel tableProductsPanel;
    TableDishesPanel tableDishesPanel;
    DishController dishController;
    FoodController foodController;
    public DatabasesPanel(DishController dishController, FoodController foodController){
        this.dishController = dishController;
        this.foodController = foodController;
        setLayout(new BorderLayout());
        tableProductsPanel = new TableProductsPanel(foodController);
        tableDishesPanel = new TableDishesPanel(dishController);
        refresh();



        add(tableDishesPanel, BorderLayout.CENTER);

    }
    public void refresh(){
        tableProductsPanel.refresh();
        tableDishesPanel.refresh();
    }
    public void setDishTableListener(DishTableListener listener){
        tableDishesPanel.setDishTableListener(listener);
    }
    public void removeProducts(){
        remove(tableProductsPanel);
        revalidate();
        repaint();
    }
    public void addProducts(){
        if(isAncestorOf(tableProductsPanel))
            return;
        add(tableProductsPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    public void removeDishes(){
        remove(tableDishesPanel);
        revalidate();
        repaint();
    }
    public void addDishes(){
        if(isAncestorOf(tableDishesPanel))
            return;
        add(tableDishesPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
