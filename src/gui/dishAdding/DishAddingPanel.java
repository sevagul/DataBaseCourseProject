package gui.dishAdding;

import Controller.DishController;
import Controller.DishControllerTxt;
import Controller.FoodController;
import gui.PanelListener;
import gui.holidayAdding.TextPanel;
import model.Recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class DishAddingPanel extends JPanel {
        private DishController dishController;
        private FoodController foodController;
        private FormPanel formPanel;
        private TextPanel textPanel;
        private JButton homeButton;
        private JButton productAddingButton;
        private JButton holidayAddingButton;
        private JPanel buttonPanel;
        private TableIngredientsPanel tableIngredientsPanel;
        private ArrayList<Integer> ingredients;
        private ArrayList<Integer> weights;
        private DatabasesPanel databasesPanel;
        private PanelListener panelListener;

        public DishAddingPanel(DishController dishController, FoodController foodController){
            super();
            this.dishController = dishController;
            this.foodController = foodController;

            setLayout(new BorderLayout());

            //////////////setting up components/////////////////////////////////////////////////
            formPanel = new FormPanel();
            textPanel = new TextPanel();
            tableIngredientsPanel = new TableIngredientsPanel();
            databasesPanel = new DatabasesPanel(this.dishController, this.foodController);

            textPanel.setText(foodController.getInfo());
            ingredients = new ArrayList<>();
            weights = new ArrayList<>();
            //textPanel.setText(dishController.getInfo());
            homeButton = new JButton("На домашню сторінку");
            holidayAddingButton = new JButton("Створити свято");
            productAddingButton = new JButton("Додати свій продукт");
            buttonPanel = new JPanel();
            //////////////setting up menu bar//////////////////////////////////////////////////

            formPanel.addFormListener(new FormListener() {
                @Override
                public void ingredientAdded(FormEvent event) {
                    if(event.getAmount() < 0 || event.getIngredient() < 0 || foodController.count("product_id", String.valueOf(event.getIngredient())) != 1 ){
                        formPanel.setError("Некорректні дані про інгредієнт");
                        return;
                    }
                    int amount = event.getAmount();
                    String name = foodController.getName(event.getIngredient());
                    tableIngredientsPanel.addIngredient(name, amount);
                    tableIngredientsPanel.refresh();
                    ingredients.add(event.getIngredient());
                    weights.add(event.getAmount());
                }
                @Override
                public void dishAdded(FormEvent event) {
                    if((weights.size() != ingredients.size()) || (ingredients.size() == 0)){
                        formPanel.setError("Некоректна страва");
                        return;
                    }
                    if(event.getDishName().equals("")){
                        formPanel.setError("Порожнє поле з назвою");
                        return;
                    }
                    if(dishController.contains(event.getDishName())){
                        formPanel.setError("Страва з такою назвою вже є");
                        return;
                    }
                    dishController.addDish(event.getDishName(), ingredients, weights, event.getPeopleAmount(), event.getDishType());
                    formPanel.blankFields();
                    databasesPanel.refresh();
                    tableIngredientsPanel.clean();
                    panelListener.productAdded();
                    ingredients.clear();
                    weights.clear();
                }
            });

            buttonPanel.setLayout(new GridLayout(1, 3));
            buttonPanel.add(homeButton);
            buttonPanel.add(productAddingButton);
            buttonPanel.add(holidayAddingButton);
            Dimension dim = buttonPanel.getPreferredSize();
            dim.height = 100;
            buttonPanel.setPreferredSize(dim);

            //////////////adding components/////////////////////////////////////////////////////
            add(formPanel, BorderLayout.WEST);
            add(databasesPanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);
            add(tableIngredientsPanel, BorderLayout.EAST);
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
            holidayAddingButton.addActionListener(navigateListener);
            productAddingButton.addActionListener(navigateListener);
        }
        public void refresh(){
            databasesPanel.refresh();
        }

        public void setPanelListener(PanelListener panelListener) {
            this.panelListener = panelListener;
        }
}
