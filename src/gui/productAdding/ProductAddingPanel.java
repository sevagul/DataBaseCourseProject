package gui.productAdding;

import Controller.FoodController;
import gui.PanelListener;
import gui.holidayAdding.TextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProductAddingPanel extends JPanel {
    private FoodController foodController;
    private FormPanel formPanel;
    private TextPanel textPanel;
    private JPanel buttonPanel;
    private JButton homeButton;
    private JButton dishAddingButton;
    private JButton holidayAddingButton;
    private PanelListener panelListener;

    public ProductAddingPanel(FoodController foodController){
        super();
        this.foodController = foodController;

        setLayout(new BorderLayout());

        //////////////setting up components/////////////////////////////////////////////////
        formPanel = new FormPanel();
        textPanel = new TextPanel();
        textPanel.setText(foodController.getInfo());
        homeButton = new JButton("На домашню сторінку");
        dishAddingButton = new JButton("Додати свою страву");
        holidayAddingButton = new JButton("Створити свято");
        buttonPanel = new JPanel();

        Dimension dim = buttonPanel.getPreferredSize();
        dim.height = 100;
        buttonPanel.setPreferredSize(dim);
        //////////////setting up menu bar//////////////////////////////////////////////////

        formPanel.addFormListener(event -> {
            if(event.getfoodName().equals(""))
            {
                formPanel.setError("(Порожнє поле з назвою)");
                return;
            }
            if(event.getfoodPrice() == -1 || event.getfoodPrice() == 0)
            {
                formPanel.setError("(Некоректно вказана ціна)");
                return;
            }
            foodController.putProduct(event.getfoodName(), event.getfoodPrice());
            textPanel.setText(foodController.getInfo());
            formPanel.setError("()");
            formPanel.blankFields();
            panelListener.productAdded();
        });



        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(homeButton);
        buttonPanel.add(dishAddingButton);
        buttonPanel.add(holidayAddingButton);
        //////////////adding components/////////////////////////////////////////////////////



        add(formPanel, BorderLayout.WEST);
        add(textPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    public FormPanel getFormPanel() {
        return formPanel;
    }

    public void setFormPanel(FormPanel formPanel) {
        this.formPanel = formPanel;
    }

    public TextPanel getTextPanel() {
        return textPanel;
    }

    public void setTextPanel(TextPanel textPanel) {
        this.textPanel = textPanel;
    }

    public void setNavigateListener(ActionListener navigateListener){
        homeButton.addActionListener(navigateListener);
        dishAddingButton.addActionListener(navigateListener);
        holidayAddingButton.addActionListener(navigateListener);
    }

    public void setPanelListener(PanelListener panelListener){
        this.panelListener = panelListener;
    }
}
