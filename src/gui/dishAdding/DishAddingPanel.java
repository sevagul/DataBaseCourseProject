package gui.dishAdding;

import Controller.DishController;
import Controller.FoodController;
import gui.PanelListener;
import gui.dishAdding.TableDishes.DishTableListener;
import gui.dishAdding.TableIngredients.TableIngredientsPanel;
import gui.holidayAdding.TextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class DishAddingPanel extends JPanel {
        private DishController dishController;
        private FoodController foodController;

        private FormPanel formPanel;

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
            formPanel = new FormPanel(foodController);
            //textPanel = new TextPanel();
            tableIngredientsPanel = new TableIngredientsPanel(this.dishController);
            databasesPanel = new DatabasesPanel(this.dishController, this.foodController);

            //textPanel.setText(foodController.getInfo());
            ingredients = new ArrayList<>();
            weights = new ArrayList<>();
            //textPanel.setText(dishController.getInfo());
            homeButton = new JButton("На домашню сторінку");
            holidayAddingButton = new JButton("Створити свято");
            productAddingButton = new JButton("База Продуктів");
            buttonPanel = new JPanel();
            //////////////setting up menu bar//////////////////////////////////////////////////

            formPanel.addFormListener(new FormListener() {
                @Override
                public void ingredientAdded(FormEvent event) {
                    String ingredientStr = event.getIngredient();
                    System.out.println(ingredientStr);
                    int ingredientId = foodController.getId(ingredientStr);
                    System.out.println(ingredientId);
                    if(event.getAmount() < 0 || ingredientId < 0 ){
                        formPanel.setError("Некорректні дані про інгредієнт");
                        return;
                    }
                    int amount = event.getAmount();
                    String name = ingredientStr;
                    tableIngredientsPanel.addIngredient(name, amount);
                    tableIngredientsPanel.refresh();
                    ingredients.add(ingredientId);
                    weights.add(event.getAmount());
                }
                @Override
                public void dishAdded(FormEvent event) {
                    int peopleAmount = event.getPeopleAmount();
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
                    if(event.getPeopleAmount() <= 0){
                        formPanel.setError("Некоректна кількість людей");
                        return;
                    }
                    formPanel.setError("");
                    dishController.addDish(event.getDishName(), ingredients, weights, event.getPeopleAmount(), event.getDishType());
                    formPanel.blankFields();
                    databasesPanel.refresh();
                    tableIngredientsPanel.clean();
                    panelListener.productAdded();
                    ingredients.clear();
                    weights.clear();
                    formPanel.paintDishInfo();
                    databasesPanel.removeProducts();
                    databasesPanel.addDishes();
                }
            });
            formPanel.addCreatingReciepeButtonListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clearRecipe();
                    databasesPanel.removeDishes();
                    databasesPanel.addProducts();
                }
            });
            formPanel.addBackButtonListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clearRecipe();
                    databasesPanel.removeProducts();
                    databasesPanel.addDishes();
                }
            });

            databasesPanel.setDishTableListener(new DishTableListener() {
                @Override
                public void askedToShowTheReceipe(int id) {
                    tableIngredientsPanel.showDish(id);
                }

                @Override
                public void deletedRecipe() {
                    panelListener.productAdded();
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

        public void clearRecipe(){
            ingredients.clear();
            weights.clear();
            tableIngredientsPanel.clean();
        }
}
