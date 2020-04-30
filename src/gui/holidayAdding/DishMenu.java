package gui.holidayAdding;

import Controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DishMenu extends JPopupMenu {
    private ActionListener listener;
    private MainController controller;

    DishMenu(MainController controller){
        super();
        this.controller = controller;
        addDishType("Салат...");
        addDishType("М'ясо...");
        addDishType("Основне...");
        addDishType("Напій...");
        addDishType("Закусон...");
        for(int i = 0; i<5; i++){
            for(String dish:controller.getDishesWithType(i)){
                addDish(i, dish);
            }
        }
    }
    public JMenu getMenu(String text){
        for(Component item: this.getComponents()){
            if (item instanceof JMenu)
            {
                if(((JMenu) item).getText().equals(text))
                    return (JMenu)item;
            }
        }
        return null;
    }
    public void addMenuItem(String text){
        add(new JMenuItem(text));
    }

    public  void addDish(int type, String name){
        JMenuItem addedItem = new JMenuItem(name);
        addedItem.addActionListener(listener);
        ((JMenu)getComponent(type)).add(addedItem);
    }

    public  void addDishType(String name){
        add(new JMenu(name));
    }

    /// setters and getters
    public ActionListener getActionListener() {
        return listener;
    }

    public void refresh(){

        for(int i = 0; i < 5; i++){
            ((JMenu)getComponent(i)).removeAll();
            for(String dish:controller.getDishesWithType(i)){
                addDish(i, dish);
            }
        }

    }

    public void setActionListener(ActionListener listener) {
        this.listener = listener;
        for(Component menu: this.getComponents()){
            if (menu instanceof JMenu)
            {
                for(Component item: ((JMenu) menu).getMenuComponents()){
                    if (item instanceof JMenuItem)
                    {
                        ((JMenuItem) item).addActionListener(listener);
                    }
                }
            }
        }
    }
}
