package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HelloPanel extends JPanel {
    private JButton holidayAddingPanelButton;
    private JButton productAddingPanelButton;
    private JButton dishAddingPanelButton;

    HelloPanel(){
        holidayAddingPanelButton = new JButton("Створити свято");
        productAddingPanelButton = new JButton("База Продуктів");
        dishAddingPanelButton = new JButton("База страв");

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.insets = new Insets(0, 0,0,0);
        gc.gridx = 0;
        gc.weightx = 100000000;
        gc.weighty = 1;
        gc.gridy = -1;

        //// adding components ///////
        addComponent(holidayAddingPanelButton, gc, false);
        addComponent(productAddingPanelButton, gc, false);
        addComponent(dishAddingPanelButton, gc, false);

    }

    public void addComponent(Component comp, GridBagConstraints gc,  boolean isfinal){

        gc.gridy++;
        gc.anchor = GridBagConstraints.EAST;
        gc.weighty = 1;

        if(isfinal) {

            gc.insets = new Insets(5, 0,0,0);
            gc.anchor = GridBagConstraints.NORTH;
            gc.weighty = 5;
        }
        add(comp, gc);
    }
    public void setNavigateListener(ActionListener navigateListener){
        dishAddingPanelButton.addActionListener(navigateListener);
        productAddingPanelButton.addActionListener(navigateListener);
        holidayAddingPanelButton.addActionListener(navigateListener);
    }
}
