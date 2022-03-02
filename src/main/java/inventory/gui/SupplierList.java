package inventory.gui;

import inventory.model.Product;
import inventory.model.Supplier;
import inventory.service.Service;
import inventory.service.supplier.SupplierService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class SupplierList {
    public  SupplierList() {
        Service<Supplier> supplierService = new SupplierService();
        List<Supplier> suppliers = supplierService.getAll();

        JFrame frame = new JFrame("Suppliers");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        frame.setSize(600,300);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel(new   GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.WEST;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10,10,10,10);  //top padding
        //headers for the table
        String[] columns = new String[] {
                "Sno", "Name", "Phone Number", "Email", "Address"
        };

        final Class[] columnClass = new Class[] {
                Long.class, String.class, String.class, String.class,String.class,
        };
        //create table model with data
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
                return columnClass[columnIndex];
            }
        };

        for (Supplier supplier: suppliers) {
            model.addRow(new Object[]{
                    supplier.getId(),
                    supplier.getName(),
                    supplier.getPhone(),
                    supplier.getEmail(),
                    supplier.getAddress()
            });
        }
        //table model
        JTable table = new JTable(model);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setPreferredScrollableViewportSize(new Dimension(700, 250));
        table.setPreferredSize(new Dimension(700, 250));
        table.getColumnModel().getColumn(0).setPreferredWidth(25);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(300);
        //add the table to the frame
        c.gridx=0;
        c.gridy=0;
        panel.add(new JScrollPane(table),c);
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}