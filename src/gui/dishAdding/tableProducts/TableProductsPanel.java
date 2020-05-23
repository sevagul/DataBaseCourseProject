package gui.dishAdding.tableProducts;


import Controller.FoodController;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
            //table.setRowSelectionAllowed(false);
            table.setCellSelectionEnabled(true);
            table.setRowHeight(25);
            table.setIntercellSpacing(new Dimension(0, 0));
            table.setGridColor(Color.black);
            table.setShowVerticalLines(true);

            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e)){
                        int current_row = table.rowAtPoint(e.getPoint());
                        table.setRowSelectionInterval(current_row, current_row);

                        table.setColumnSelectionInterval(0, 2);
                        //foodController.delete(new KeyVals("product_id", id));
                        //tableProductsModel.refresh();
                    }
                }
            });
            setPrefferedSizes(table, 1000, new int[] {1, 10, 1});


            JPopupMenu delPopUp = new JPopupMenu();
            JMenuItem deleteItem = new JMenuItem("Видалити продукт");
            deleteItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tableProductsModel.deleteRow(table.getSelectedRow());
                    tableProductsModel.refresh();
                }
            });
            delPopUp.add(deleteItem);

            table.setComponentPopupMenu(delPopUp);


            add(new JScrollPane(table), BorderLayout.CENTER);
            add(table.getTableHeader(), BorderLayout.NORTH);
            add(new JLabel("Продукти:"), BorderLayout.NORTH);
        }

        public void refresh(){
            tableProductsModel.refresh();
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
}
