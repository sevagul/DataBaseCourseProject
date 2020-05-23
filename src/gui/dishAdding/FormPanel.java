package gui.dishAdding;

import javax.swing.*;

import Controller.FoodController;
import Utils.Utils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPanel extends JPanel {
    private JTextField nameField;

    private JButton creatingRecipeButton;
    private JButton creatingButton;
    private JButton ingredientAddingButton;
    private JButton backButton;

    private FormListener formListener;
    private JLabel errorLabel;
    private AutoCompletedTextField ingredientField;
    private JTextField amountField;
    private JTextField peopleAmountField;
    JComboBox dishTypeBox;

    public FormPanel(FoodController foodController){
        ////setting up sefault parameters////////
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);
        setBorder(BorderFactory.createTitledBorder("Додавання власної страви"));

        ////setting up components/////////////
        nameField = new JTextField(10);
        creatingRecipeButton = new JButton("Створити Рецепт...");
        creatingButton = new JButton("Додати страву");
        errorLabel = new JLabel("()");
        ingredientField = new AutoCompletedTextField(foodController);
        amountField = new JTextField(5);
        amountField.setMinimumSize(amountField.getPreferredSize());
        peopleAmountField = new JTextField(5);
        ingredientAddingButton = new JButton("Додати інгредієнт");
        dishTypeBox = new JComboBox();
        backButton = new JButton("Назад");

        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
        comboBoxModel.addElement("Салат");
        comboBoxModel.addElement("М'ясо");
        comboBoxModel.addElement("Основне");
        comboBoxModel.addElement("Напій");
        comboBoxModel.addElement("Закусон");
        dishTypeBox.setModel(comboBoxModel);



        ////setting up creatingButton/////////////
        creatingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(formListener != null){
                    String ingredient = ingredientField.getText();
                    int amount = Utils.stringToNatural(amountField.getText());
                    int peopleAmount = Utils.stringToNatural(peopleAmountField.getText());
                    int type = comboBoxModel.getIndexOf(comboBoxModel.getSelectedItem()) + 1;

                    formListener.dishAdded(new FormEvent(nameField.getText() ,ingredient, amount, peopleAmount, type));
                    //getContentPane().removeAll();
                    //getContentPane().repaint();
                }
            }
        });

        ingredientAddingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(formListener != null){
                    String ingredient = ingredientField.getText();
                    int amount = Utils.stringToNatural(amountField.getText());
                    int peopleAmount = Utils.stringToNatural(peopleAmountField.getText());
                    int type = comboBoxModel.getIndexOf(comboBoxModel.getSelectedItem()) + 1;

                    formListener.ingredientAdded(new FormEvent(nameField.getText() ,ingredient, amount, peopleAmount, type));
                }
            }
        });

        creatingRecipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                System.out.println("CHECK " + getComponents().length);
                paintDishAddInfo();
            }
        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                System.out.println("CHECK " + getComponents().length);
                paintDishInfo();
            }
        });
        
        //// setting up Layout //////
        setLayout(new GridBagLayout());
        paintDishInfo();
//


        //addComponents(ingredientField, amountField, gc, false);
        //addComponents(new JLabel("(Інгредієнт)"), new JLabel("(Кількість)"), gc, false);
        //addComponent(ingredientAddingButton, gc, false);
        //addComponent(errorLabel, gc, true);
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

        gc1.insets = new Insets(0, 5,0,0);
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
    /// setters and getters
    public FormListener getFormListener() {
        return formListener;
    }
    public void addFormListener(FormListener formListener) {
        this.formListener = formListener;
    }
    public void setError(String text){
        errorLabel.setText(text);
    }
    public void addCreatingReciepeButtonListener(ActionListener listener){
        creatingRecipeButton.addActionListener(listener);
    }
    public void addBackButtonListener(ActionListener listener){
        backButton.addActionListener(listener);
    }
    public void paintDishInfo(){
        removeAll();
        System.out.println("CHECK " + getComponents().length);
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.NONE;
        gc.insets = new Insets(0, 0,0,0);

        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridy = -1;

        //// adding components ///////

        addComponents(new JLabel("Назва Страви: "), nameField, gc, false);
        addComponents(new JLabel("Тип страви: "), dishTypeBox, gc, false);
        addComponents(new JLabel("Кількість людей:"), peopleAmountField, gc, false);
        addComponent(creatingRecipeButton, gc, true);
        revalidate();
        repaint();
    }
    public void paintDishAddInfo(){
        removeAll();
        System.out.println("CHECK " + getComponents().length);
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.NONE;
        gc.insets = new Insets(0, 0,0,0);

        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridy = -1;

        //// adding components ///////

        addComponents(ingredientField, amountField, gc, false);
        addComponents(new JLabel("(Інгредієнт)"), new JLabel("(Кількість)"), gc, false);
        addComponent(ingredientAddingButton, gc, false);
        addComponent(backButton, gc, true);
        addComponent(errorLabel, gc, false);
        addComponent(creatingButton, gc, false);
        revalidate();
        repaint();
    }
    public void blankFields(){
        nameField.setText("");
        amountField.setText("");
        ingredientField.setText("");
        peopleAmountField.setText("");
    }
}
