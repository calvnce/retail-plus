package inventory.gui;


import inventory.model.Product;
import inventory.service.Service;
import inventory.service.product.ProductService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.PrimitiveIterator;
import java.util.stream.Collectors;

public class ProductList {
    public ProductList() {
        Service<Product> productService = new ProductService();
        List<Product> products = productService.getAll();

        JFrame frame = new JFrame("Products");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        frame.setSize(600,300);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel(new   GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.WEST;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10,10,0,5);  //top padding

        //headers for the table
        String[] columns = new String[] {
                "Sno", "Name", "Manufacture Date", "Expiry Data", "Purchase Price", "Selling Price"
        };

        final Class[] columnClass = new Class[] {
                Long.class, String.class,  LocalDate.class,LocalDate.class, Double.class, Double.class
        };
        //create table model with data
        DefaultTableModel model = new DefaultTableModel(columns,0) {
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

        for (Product product:products) {
            model.addRow(new Object[]{
                    product.getId(),
                    product.getName(),
                    product.getMfgDate(),
                    product.getExpiryDate(),
                    product.getBuyingPrice(),
                    product.getSellingPrice()
            });
        }
        //table model
        JTable table = new JTable(model);

        //add the table to the frame
        JLabel searchLabel = new JLabel("Enter Search Phrase");
        c.gridx=0;
        c.gridy=0;
        panel.add(searchLabel, c);
        JTextField searchText  = new JTextField(35);
        searchText.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                model.setRowCount(0);
                if (searchText.getText().length()==0){
                    for (Product product:products) {
                        model.addRow(new Object[]{
                                product.getId(),
                                product.getName(),
                                product.getMfgDate(),
                                product.getExpiryDate(),
                                product.getBuyingPrice(),
                                product.getSellingPrice()
                        });
                    }
                }
                else {
                    List<Product>list = products.stream()
                            .parallel().filter(p-> p.getName().toLowerCase(Locale.ROOT).contains(searchText.getText().toLowerCase(Locale.ROOT)))
                            .collect(Collectors
                                    .toList());
                    for (Product product:list) {
                        model.addRow(new Object[]{
                                product.getId(),
                                product.getName(),
                                product.getMfgDate(),
                                product.getExpiryDate(),
                                product.getBuyingPrice(),
                                product.getSellingPrice()
                        });
                    }
                }
            }

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }
        });
        c.gridx=0;
        c.gridy=1;
        panel.add(searchText, c);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setPreferredScrollableViewportSize(new Dimension(850, 250));
        table.setPreferredSize(new Dimension(850, 250));
        table.getColumnModel().getColumn(0).setPreferredWidth(25);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        //add the table to the frame
        c.insets = new Insets(30,10,10,5);  //top padding
        c.gridx=0;
        c.gridy=2;
        panel.add(new JScrollPane(table),c);
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
