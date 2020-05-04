package gui.holidayAdding;

import Controller.MainController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HolidayAddingPanel extends JPanel {
    private FormPanel formPanel;
    private TextPanel textPanel;
    private JPanel buttonPanel;
    private JButton homeButton;
    private JButton productAddingButton;
    private JButton dishAddingButton;
    private MainController controller;
    private int state;

    public HolidayAddingPanel(MainController controller){
        ///////////////setting up default frame settings////////////////////////////////////
        super();
        this.controller = controller;
        state = 0;
        setLayout(new BorderLayout());
        //////////////setting up components/////////////////////////////////////////////////
        formPanel = new FormPanel(controller);
        textPanel = new TextPanel();
        buttonPanel = new JPanel();
        dishAddingButton = new JButton("Додати свою страву");
        productAddingButton = new JButton("Додати свій продукт");
        homeButton = new JButton("На домашню сторінку");
        //////////////setting up menu bar//////////////////////////////////////////////////
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(homeButton);
        buttonPanel.add(dishAddingButton);
        buttonPanel.add(productAddingButton);
        Dimension dim = buttonPanel.getPreferredSize();
        dim.height = 100;
        buttonPanel.setPreferredSize(dim);
        //////////////adding components/////////////////////////////////////////////////////
        add(formPanel, BorderLayout.WEST);
        add(textPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void setFormListener(FormListener listener) {
        formPanel.addFormListener(listener);
    }
    public TextPanel getTextPanel() {
        return textPanel;
    }
    public void refresh(){
        formPanel.refresh();
    }
    public void setNavigateListener(ActionListener navigateListener){
        homeButton.addActionListener(navigateListener);
        dishAddingButton.addActionListener(navigateListener);
        productAddingButton.addActionListener(navigateListener);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
