package gui;

import Controller.MainController;
import gui.dishAdding.AutoCompletedTextField;
import gui.dishAdding.DishAddingPanel;
import gui.productAdding.ProductAddingPanel;
import gui.holidayAdding.HolidayAddingPanel;
import gui.holidayAdding.FormEvent;
import gui.holidayAdding.FormListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private MenuBar menuBar;
    private HolidayAddingPanel holidayAddingPanel;
    private ProductAddingPanel productAddingPanel;
    private DishAddingPanel dishAddingPanel;
    private MainController controller;
    private HelloPanel helloPanel;

    private ActionListener navigateListener;

    public MainFrame(){
        ///////////////setting up default frame settings///////////////////
        super("Курсова Робота з Баз Даних");
        setLayout(new BorderLayout());
        setSize(1000, 800);
        setMinimumSize(new Dimension(900, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //////////////setting up components/////////////////
        controller = new MainController();
        menuBar = new MenuBar();
        holidayAddingPanel = new HolidayAddingPanel(controller);
        helloPanel = new HelloPanel();
        dishAddingPanel = new DishAddingPanel(controller.getDishController(), controller.getFoodController());
        productAddingPanel = new ProductAddingPanel(controller.getFoodController());

        ///setting up menu bar
        Menu menu = new Menu("Вийти");
        MenuItem exit = new MenuItem("Вийти");
        menu.add(exit);
        MainFrame tthis = this;
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int answer = JOptionPane.showConfirmDialog(tthis, "Ви впевнені, що хочете вийти?");//showMessageDialog(tthis, "Ви впевнені, що хочете вийти?");
                if(answer == 0)
                    System.exit(0);
            }
        });
        menuBar.add(menu);
        setMenuBar(menuBar);
        //////////
        navigateListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().removeAll();
                getContentPane().repaint();
                if(( (JButton)e.getSource() ).getText().equals("База Продуктів"))
                    add(productAddingPanel);
                if(( (JButton)e.getSource() ).getText().equals("На домашню сторінку"))
                    add(helloPanel);
                if(( (JButton)e.getSource() ).getText().equals("База страв"))
                    add(dishAddingPanel);
                if(( (JButton)e.getSource() ).getText().equals("Створити свято"))
                    add(holidayAddingPanel);
                setVisible(true);
            }
        };

        //////////////setting helloPanel//////////////////////////////////////////////////
        helloPanel.setNavigateListener(navigateListener);

        //////////////setting up holidayAddingPanel panel/////////////////////////////////////////////////
        holidayAddingPanel.setFormListener(new FormListener() {
            @Override
            public void formEventOccured(FormEvent event) {
                holidayAddingPanel.getTextPanel().setText("");
                controller.setHoliday(event);
                holidayAddingPanel.getTextPanel().appendText(controller.getInfo());
                holidayAddingPanel.setState(1);
            }
            @Override
            public void dishAdded(String dish){
                if(holidayAddingPanel.getState() == 1){
                    holidayAddingPanel.getTextPanel().setText("");
                    holidayAddingPanel.getTextPanel().appendText("Додані страви:\n");
                    holidayAddingPanel.setState(0);
                }
                holidayAddingPanel.getTextPanel().appendText(dish + "\n");
            }
        });

        //////////////setting up productAddingPanel panel/////////////////////////////////////////////////
        productAddingPanel.setNavigateListener(navigateListener);
        productAddingPanel.setPanelListener(new PanelListener() {
            @Override
            public void productAdded() {
                dishAddingPanel.refresh();
            }
        });
        dishAddingPanel.setPanelListener(new PanelListener() {
            @Override
            public void productAdded() {
                holidayAddingPanel.refresh();
            }
        });
        holidayAddingPanel.setNavigateListener(navigateListener);
        helloPanel.setNavigateListener(navigateListener);
        dishAddingPanel.setNavigateListener(navigateListener);

        //////////////adding components/////////////////////////////////////////////////////
        add(helloPanel, BorderLayout.CENTER);
        //add(new AutoCompletedTextField(controller.getFoodController()));

        setVisible(true);
    }
}