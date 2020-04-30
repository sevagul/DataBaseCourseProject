package gui.dishAdding;

import Controller.DishController;
import gui.PanelListener;
import gui.holidayAdding.TextPanel;
import model.Recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class DishAddingPanel extends JPanel {
        private DishController dishController;
        private FormPanel formPanel;
        private TextPanel textPanel;
        private JButton homeButton;
        private JButton foodStuffAddingButton;
        private JButton holidayAddingButton;
        private JPanel buttonPanel;
        private TableIngredientsPanel tableIngredientsPanel;
        private ArrayList<Integer> ingredients;
        private ArrayList<Integer> weights;
        private DatabasesPanel databasesPanel;
        private PanelListener panelListener;

        public DishAddingPanel(DishController dishController){
            super();
            this.dishController = dishController;

            setLayout(new BorderLayout());

            //////////////setting up components/////////////////////////////////////////////////
            formPanel = new FormPanel();
            textPanel = new TextPanel();
            tableIngredientsPanel = new TableIngredientsPanel();
            databasesPanel = new DatabasesPanel(dishController);

            textPanel.setText(dishController.getFoodBase().getInfo());
            ingredients = new ArrayList<>();
            weights = new ArrayList<>();
            //textPanel.setText(dishController.getInfo());
            homeButton = new JButton("На домашню сторінку");
            holidayAddingButton = new JButton("Створити свято");
            foodStuffAddingButton = new JButton("Додати свій продукт");
            buttonPanel = new JPanel();
            //////////////setting up menu bar//////////////////////////////////////////////////

            formPanel.addFormListener(new FormListener() {
                @Override
                public void ingredientAdded(FormEvent event) {
                    if(event.getAmount() < 0 || event.getIngredient() < 0 || event.getIngredient() >= dishController.getFoodBase().getNextId()){
                        formPanel.setError("Некорректні дані про інгредієнт");
                        return;
                    }
                    int amount = event.getAmount();
                    String name = dishController.getFoodBase().getById(event.getIngredient()).getName();
                    tableIngredientsPanel.addIngredient(name, amount);
                    tableIngredientsPanel.refresh();
                    ingredients.add(event.getIngredient());
                    weights.add(event.getAmount());
                }
                @Override
                public void dishAdded(FormEvent event) {
                    if((weights.size() != ingredients.size()) || (ingredients.size() == 0)){
                        formPanel.setError("Некоректне блюдо");
                        return;
                    }
                    if(event.getDishName().equals("")){
                        formPanel.setError("Порожнє поле з назвою");
                        return;
                    }
                    if(dishController.getDishBase().contains(event.getDishName())){
                        formPanel.setError("Блюдо з такою назвою вже є");
                        return;
                    }
                    dishController.getDishBase().addDish(event.getDishName(), new Recipe(dishController.getFoodBase(), ingredients, weights, event.getPeopleAmount()), event.getDishType());
                    formPanel.blankFields();
                    databasesPanel.refresh();
                    tableIngredientsPanel.clean();
                    panelListener.stuffAdded();
                    ingredients.clear();
                    weights.clear();
                }
            });

            buttonPanel.setLayout(new GridLayout(1, 3));
            buttonPanel.add(homeButton);
            buttonPanel.add(foodStuffAddingButton);
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
            foodStuffAddingButton.addActionListener(navigateListener);
        }
        public void refresh(){
            databasesPanel.refresh();
        }

        public void setPanelListener(PanelListener panelListener) {
            this.panelListener = panelListener;
        }
}
