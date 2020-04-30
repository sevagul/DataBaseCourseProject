package gui.dishAdding;

import javax.swing.*;

import Utils.Utils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPanel extends JPanel {
    private JTextField nameField;
    private JButton creatingButton;
    private JButton ingredientAddingButton;
    private FormListener formListener;
    private JLabel errorLabel;
    private JTextField ingredientField;
    private JTextField amountField;
    private JTextField peopleAmountField;
    JComboBox dishTypeBox;

    public FormPanel(){
        ////setting up sefault parameters////////
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);
        setBorder(BorderFactory.createTitledBorder("Додавання власної страви"));

        JTable table = new JTable(2, 16);
        JScrollPane scrollpane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        table.setRowSelectionAllowed(false);
        table.setCellSelectionEnabled(true);
        table.setRowHeight(25);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setGridColor(Color.black);
        table.setShowVerticalLines(true);
        ////setting up components/////////////
        nameField = new JTextField(10);
        creatingButton = new JButton("Додати Страву");
        errorLabel = new JLabel("()");
        ingredientField = new JTextField(5);
        amountField = new JTextField(5);
        peopleAmountField = new JTextField(5);
        ingredientAddingButton = new JButton("Додати інгредієнт");
        dishTypeBox = new JComboBox();

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
                    int ingredient = Utils.stringToNatural(ingredientField.getText());
                    int amount = Utils.stringToNatural(amountField.getText());
                    int peopleAmount = Utils.stringToNatural(peopleAmountField.getText());
                    int type = comboBoxModel.getIndexOf(comboBoxModel.getSelectedItem());

                    formListener.dishAdded(new FormEvent(nameField.getText() ,ingredient, amount, peopleAmount, type));
                }
            }
        });

        ingredientAddingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(formListener != null){
                    int ingredient = Utils.stringToNatural(ingredientField.getText());
                    int amount = Utils.stringToNatural(amountField.getText());
                    int peopleAmount = Utils.stringToNatural(peopleAmountField.getText());
                    int type = comboBoxModel.getIndexOf(comboBoxModel.getSelectedItem());

                    formListener.ingredientAdded(new FormEvent(nameField.getText() ,ingredient, amount, peopleAmount, type));
                }
            }
        });
        
        //// setting up Layout //////
        setLayout(new GridBagLayout());

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
        addComponent(creatingButton, gc, false);
        addComponents(ingredientField, amountField, gc, false);
        addComponents(new JLabel("(Інгредієнт)"), new JLabel("(Кількість)"), gc, false);
        addComponent(ingredientAddingButton, gc, false);
        addComponent(errorLabel, gc, true);
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
    public void blankFields(){
        nameField.setText("");
        amountField.setText("");
        ingredientField.setText("");
        peopleAmountField.setText("");
    }
}
