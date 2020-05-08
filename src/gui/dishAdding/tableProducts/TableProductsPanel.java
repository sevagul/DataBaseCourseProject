package gui.dishAdding.tableProducts;


import Controller.FoodController;

import javax.swing.*;
import java.awt.*;

public class TableProductsPanel extends JPanel {
        private JTable table;
        private TableProductsModel tableProductsModel;
        private FoodController foodController;
        public TableProductsPanel(FoodController foodController){
            this.foodController = foodController;
            setLayout(new BorderLayout());
            Dimension dim = getPreferredSize();
            dim.width = 300;
            setPreferredSize(dim);
            tableProductsModel = new TableProductsModel(foodController);
            table = new JTable(tableProductsModel);

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

        public void refresh(){
            tableProductsModel.fireTableDataChanged();
        }
}
