package gui.dishAdding;

import javax.swing.*;
import java.awt.*;

public class TableIngredientsPanel extends JPanel {
    private JTable table;
    private TableIngredientsModel tableIngredientsModel;
    public TableIngredientsPanel(){
        setLayout(new BorderLayout());
        Dimension dim = getPreferredSize();
        dim.width = 300;
        setPreferredSize(dim);
        tableIngredientsModel = new TableIngredientsModel();
        table = new JTable(tableIngredientsModel);
        System.out.println(table.getTableHeader());

        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(false);
        table.setCellSelectionEnabled(true);
        table.setRowHeight(25);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setGridColor(Color.black);
        table.setShowVerticalLines(true);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(table.getTableHeader(), BorderLayout.NORTH);
    }
    public void addIngredient(String name, int amount){
        tableIngredientsModel.addIngredient(name, amount);
    }

    public void refresh(){
        tableIngredientsModel.fireTableDataChanged();
    }

    public void clean(){
        tableIngredientsModel.clean();
        refresh();
    }
}
