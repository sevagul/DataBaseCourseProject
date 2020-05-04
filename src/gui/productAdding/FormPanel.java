package gui.productAdding;

import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPanel extends JPanel {
    private JTextField nameField;
    private JTextField priceField;
    private JButton creatingButton;
    private FormListener formListener;
    private JLabel errorLabel;

    public FormPanel(){
        ////setting up sefault parameters////////
        Dimension dim = getPreferredSize();
        dim.width = 350;
        setPreferredSize(dim);
        setBorder(BorderFactory.createTitledBorder("Дадавання власного харчового продукту"));

        ////setting up components/////////////
        nameField = new JTextField(10);

        priceField = new JTextField(10);
        
        creatingButton = new JButton("Додати продукт");

        errorLabel = new JLabel("()");

        ////setting up creatingButton/////////////
        creatingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String foodPriceString = priceField.getText();
                int foodPrice = Utils.stringToNatural(foodPriceString);
                if(formListener != null){
                    formListener.formEventOccured(new FormEvent(foodPrice, nameField.getText()));
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
        addComponents(new JLabel("Назва продукту: "), nameField, gc, false);
        addComponents(new JLabel("Ціна продукту: "), priceField, gc,  false);
        addComponent(creatingButton, gc, false);
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
    public void addFormListener(FormListener formListener) {
        this.formListener = formListener;
    }
    public void setError(String text){
        errorLabel.setText(text);
    }
    public void blankFields(){
        nameField.setText("");
        priceField.setText("");
    }
}
