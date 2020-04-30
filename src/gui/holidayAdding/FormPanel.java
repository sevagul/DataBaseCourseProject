package gui.holidayAdding;

import Controller.MainController;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormPanel extends JPanel {
    private JComboBox peopleAmountBox;
    private JTextField nameField;
    private JButton creatingButton;
    private JButton addingDishButton;
    private DishMenu dishMenu;
    private FormListener formListener;
    private ArrayList<String> dishes;
    MainController controller;

    public FormPanel(MainController controller){
        this.controller = controller;
        ////setting up sefault parameters////////
        Dimension dim = getPreferredSize();
        dim.width = 350;
        setPreferredSize(dim);
        setBorder(BorderFactory.createTitledBorder("Створи свято!:)"));

        ////setting up components/////////////
        nameField = new JTextField(10);
        nameField.setText("Новий Рік");
        peopleAmountBox = new JComboBox();
        creatingButton = new JButton("Створити свято");
        addingDishButton = new JButton("Додати блюдо...");
        dishMenu = new DishMenu(controller);
        dishes = new ArrayList<String>();

        ////setting up peopleAmountBox//////////////
        DefaultComboBoxModel peopleAmountModel = new DefaultComboBoxModel();
        peopleAmountModel.addElement("1");
        peopleAmountModel.addElement("2");
        peopleAmountModel.addElement("3");
        peopleAmountModel.addElement("4");
        peopleAmountModel.addElement("5");
        peopleAmountModel.addElement("6");
        peopleAmountModel.addElement("7");
        peopleAmountModel.addElement("інша...");
        peopleAmountBox.setModel(peopleAmountModel);
        peopleAmountBox.setEditable(true);

        ////setting up creatingButton/////////////
        creatingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String peopleAmountString = (String)peopleAmountBox.getSelectedItem();
                int peopleAmount = Utils.stringToNatural(peopleAmountString);
                if(formListener != null){
                    formListener.formEventOccured(new FormEvent(peopleAmount, dishes, nameField.getText()));
                    dishes.clear();
                    nameField.setText("");
                    peopleAmountBox.setSelectedIndex(0);
                }
            }
        });

        ////setting up addingDish -Button and -Popup///////////
        dishMenu.setActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dishes.contains(((JMenuItem)e.getSource()).getText()))
                    return;
                dishes.add(((JMenuItem)e.getSource()).getText());
                formListener.dishAdded(((JMenuItem)e.getSource()).getText());
            }
        });

        addingDishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dishMenu.show(addingDishButton, addingDishButton.getMousePosition().x, addingDishButton.getMousePosition().y);
            }
        });

        //// setting up Layout //////
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.NONE;
        gc.insets = new Insets(0, 0,0,5);

        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridy = -1;

        //// adding components ///////
        addComponents(new JLabel("Назва свята: "), nameField, gc, false);
        addComponents(new JLabel("Кількість людей: "), peopleAmountBox, gc,  false);
        addComponent(addingDishButton, gc,  false);
        addComponent(creatingButton, gc, true);


    }

    public void addComponents(Component comp1, Component comp2, GridBagConstraints gc, boolean isfinal){


        gc.gridy++;


        JPanel currentPanel = new JPanel();
        currentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc1 = new GridBagConstraints();
        gc1.fill = GridBagConstraints.NONE;
        gc1.insets = new Insets(0, 0,0,0);
        gc1.gridx = 0;
        gc1.weightx = 1;
        gc1.weighty = 1;
        gc1.gridy = 0;

        gc1.anchor = GridBagConstraints.LINE_END;
        currentPanel.add(comp1, gc1);

        gc1.gridx++;

        gc1.anchor = GridBagConstraints.LINE_START;
        currentPanel.add(comp2, gc1);

        addComponent(currentPanel, gc, isfinal);
    }

    public void addComponent(Component comp2, GridBagConstraints gc,  boolean isfinal){

        gc.gridy++;
        gc.anchor = GridBagConstraints.CENTER;
        gc.weighty = 1;

        if(isfinal) {

            gc.insets = new Insets(5, 0,0,0);
            gc.anchor = GridBagConstraints.NORTH;
            gc.weighty = 5;
        }
        add(comp2, gc);
    }


    public void refresh(){
        dishMenu.refresh();
    }

    /// setters and getter

    public FormListener getFormListener() {
        return formListener;
    }

    public void addFormListener(FormListener formListener) {
        this.formListener = formListener;
    }
}
