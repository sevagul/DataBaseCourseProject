package gui.dishAdding.TableDishes;

import Controller.DishController;
import Utils.Utils;
import new_model.dbClasses.KeyVals;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableDishesPanel extends JPanel {
    private JTable table;
    private TableDishesModel tableDishesModel;
    private DishController dishController;
    private DishTableListener dishTableListener;

    public void setDishTableListener(DishTableListener dishTableListener) {
        this.dishTableListener = dishTableListener;
    }

    public TableDishesPanel(DishController dishController) {
        this.dishController = dishController;
        setLayout(new BorderLayout());
        Dimension dim = getPreferredSize();
        dim.width = 300;
        setPreferredSize(dim);
        tableDishesModel = new TableDishesModel(dishController);
        table = new JTable(tableDishesModel);

        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(false);
        table.setCellSelectionEnabled(true);
        table.setRowHeight(25);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setGridColor(Color.black);
        table.setShowVerticalLines(true);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row < 0) {
                    table.getSelectionModel().clearSelection();
                    return;
                }
                table.setRowSelectionInterval(row, row);
                table.setColumnSelectionInterval(0, 1);
                if (SwingUtilities.isLeftMouseButton(e)){
                    int id = Utils.stringToNatural((String) tableDishesModel.getValueAt(row, 0));
                    dishTableListener.askedToShowTheReceipe(id);
                }
            }
        });

        setPrefferedSizes(table, 1000, new int[] {1, 10});

        JPopupMenu delPopUp = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Видалити страву");
        JMenuItem showReceipe = new JMenuItem("Показати Рецепт");
        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row < 0) {
                    return;
                }
                tableDishesModel.deleteRow(table.getSelectedRow());
                tableDishesModel.refresh();
                dishTableListener.deletedRecipe();
            }
        });
        showReceipe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dishTableListener != null){
                    int row = table.getSelectedRow();
                    int id = Utils.stringToNatural((String) tableDishesModel.getValueAt(row, 0));
                    dishTableListener.askedToShowTheReceipe(id);
                }
            }
        });
        delPopUp.add(deleteItem);
        //delPopUp.add(showReceipe);

        table.setComponentPopupMenu(delPopUp);


        add(new JScrollPane(table), BorderLayout.CENTER);
        add(table.getTableHeader(), BorderLayout.NORTH);
        add(new JLabel("Страви:"), BorderLayout.NORTH);
    }

    public void refresh() {
        tableDishesModel.refresh();
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
