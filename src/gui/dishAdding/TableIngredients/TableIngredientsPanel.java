package gui.dishAdding.TableIngredients;

import Controller.DishController;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class TableIngredientsPanel extends JPanel {
    private JTable table;
    private TableIngredientsModel tableIngredientsModel;
    private DishController dishController;
    private JLabel topLabel;

    public TableIngredientsPanel(DishController dishController){
        this.dishController = dishController;
        setLayout(new BorderLayout());
        Dimension dim = getPreferredSize();
        dim.width = 300;
        setPreferredSize(dim);
        tableIngredientsModel = new TableIngredientsModel(this.dishController);
        table = new JTable(tableIngredientsModel);
        //System.out.println(table.getTableHeader());

        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(false);
        table.setCellSelectionEnabled(true);
        table.setRowHeight(25);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setGridColor(Color.black);
        table.setShowVerticalLines(true);

        setPrefferedSizes(table, 1000, new int[] {5, 1});

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(table.getTableHeader(), BorderLayout.NORTH);
        topLabel = new JLabel("Інгредієнти:");
        add(topLabel, BorderLayout.NORTH);
    }
    public void addIngredient(String name, int amount){
        tableIngredientsModel.addIngredient(name, amount);
    }

    public void refresh(){
        tableIngredientsModel.fireTableDataChanged();
    }

    public void clean(){
        tableIngredientsModel.clean();

        topLabel.setText("Інгредієнти:");
        refresh();
    }

    public void setPrefferedSizes(JTable table, int prefferedSize, int [] wdths){
        double total = 0;
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            total += wdths[i];
        }


        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            int cur_size = (int)
                    (prefferedSize * (wdths[i] / total));
            column.setPreferredWidth(cur_size);
            System.out.println(cur_size);

        }
    }
    public void showDish(int id){
        tableIngredientsModel.showDish(id);
        topLabel.setText("Інгредієнти: (На " + dishController.getPeopleAmountById(id) + " людей)");
        refresh();
    }
}
