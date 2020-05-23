package gui.dishAdding;

import Controller.FoodController;
import new_model.controllers.FoodControllerSql;
import new_model.dbClasses.KeyVals;
import new_model.dbClasses.SelectResult;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AutoCompletedTextField extends JPanel{
    private FoodController foodController;
    private JComboBox<String> comboBox;
    private JTextField textField;
    private String cur_text;
    private ActionListener comboListener;


    public AutoCompletedTextField(FoodController foodController){
        JPanel temp = this;
        this.foodController = foodController;

                comboBox = new JComboBox<>();
                textField = new JTextField(10);
                comboBox.setAutoscrolls(true);
                textField.addCaretListener(new TextFieldCaretListener());

                setLayout(new BoxLayout(temp, BoxLayout.PAGE_AXIS));
                textField.setMaximumSize(new Dimension(100, 30));
                textField.setMinimumSize(new Dimension(90, 30));
                comboBox.setMaximumSize(new Dimension(100, 30));
                Dimension dim = getPreferredSize();
                dim.width = 300;
                setPreferredSize(dim);
                comboListener = new ComboBoxListener();
                comboBox.addActionListener(comboListener);
                add(textField);
                //add(comboBox);


    }


    public ArrayList<String> getSuggetions(String prefix){
        return foodController.getNamesLike(prefix);

    }



    private class TextFieldCaretListener implements CaretListener{

        @Override
        public void caretUpdate(CaretEvent e) {

            System.out.println("\t\tField is like " + textField.getText());
            comboBox.removeActionListener(comboListener);
            comboBox.removeAllItems();
            comboBox.hidePopup();
            remove(comboBox);
            if(textField.getText().equals("")){
                comboBox.addActionListener(comboListener);
                return;
            }
            for (String sug: getSuggetions(textField.getText())){
                add(comboBox);
                System.out.println("adding" + sug);
                comboBox.addItem(sug);
            }
            comboBox.showPopup();
            revalidate();
            repaint();
            comboBox.addActionListener(comboListener);

        }
    }

    private class ComboBoxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {


            if(textField.getText().equals(""))
                return;
            try{
            String text = (String) comboBox.getSelectedItem();
            if(text != null){
                System.out.println("setting text" + text);
                //System.out.println(textField);
                textField.setText(text);
                remove(comboBox);
                revalidate();
                repaint();
            }} catch(Exception ex){
                System.out.println("Some stange Exception");
            }

        }
    }

    public String getText(){
        return textField.getText();
    }
    public void setText(String text){
        textField.setText(text);
    }
}


