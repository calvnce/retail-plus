package inventory.gui;

import inventory.model.Order;
import inventory.model.Product;
import inventory.model.Supplier;
import inventory.service.Service;
import inventory.service.order.OrderService;
import inventory.service.product.ProductService;
import inventory.service.supplier.SupplierService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CreateOrder {
    public  CreateOrder() {
        Service<Product>productService = new ProductService();
        Service<Supplier>supplierService = new SupplierService();
        Service<Order>orderService = new OrderService();

        List<Product>products = productService.getAll();
        List<Supplier>suppliers = supplierService.getAll();
        List<Order> orders = orderService.getAll();


        String[] productNames = new String[products.size()];
        String[] supplierNames = new String[suppliers.size()];

        for (int i = 0; i < products.size(); i++) {
            productNames[i] = products.get(i).getName();
        }
        for (int i = 0; i < suppliers.size(); i++) {
            supplierNames[i] = suppliers.get(i).getName();
        }
        Arrays.sort(supplierNames);
        Arrays.sort(productNames);

        JFrame frame = new JFrame("New Order");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(900,675);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);


        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.WEST;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10,10,10,5);  //top padding
        //Labels
        JLabel productLabel = new JLabel("Product");
        c.gridx =0;
        c.gridy =0;
        panel.add(productLabel,c);
        JLabel supplierLabel = new JLabel("Supplier");
        c.gridx =0;
        c.gridy =1;
        panel.add(supplierLabel,c);
        JLabel quantityLabel = new JLabel("Quantity");
        c.gridx =0;
        c.gridy =2;
        panel.add(quantityLabel,c);
        JLabel orderDateLabel = new JLabel("Order Date");
        c.gridx =0;
        c.gridy =3;
        panel.add(orderDateLabel,c);
        JComboBox<String> productComboBox = new JComboBox<>(productNames);
        c.gridx =1;
        c.gridy =0;
        panel.add(productComboBox,c);
        JComboBox<String> supplierComboBox = new JComboBox<>(supplierNames);
        c.gridx =1;
        c.gridy =1;
        panel.add(supplierComboBox,c);
        JTextField quantityText = new JTextField(20);
        c.gridx =1;
        c.gridy =2;
        panel.add(quantityText,c);
        JFormattedTextField orderDateText = new JFormattedTextField();
        orderDateText.setValue(LocalDate.now());
        orderDateText.setColumns(20);
        c.gridx =1;
        c.gridy =3;
        panel.add(orderDateText,c);

        //headers for the table
        String[] columns = new String[] {
                "Sno", "Product", "Supplier", "Quantity", "Order Amount", "Order Date", "Order Status"
        };

        final Class[] columnClass = new Class[] {
                Long.class, String.class,  String.class,  Double.class, Double.class, LocalDate.class, String.class,
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

        JButton addButton = new JButton(new AbstractAction("Order") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Order order = new Order();
                    Product product = products.stream()
                            .parallel()
                            .filter(p->p.getName().equals(Objects.requireNonNull(productComboBox.getSelectedItem()).toString()))
                            .findFirst()
                            .orElse(null);
                    Supplier supplier = suppliers.stream()
                            .parallel()
                            .filter(s->s.getName().equals(Objects.requireNonNull(supplierComboBox.getSelectedItem()).toString()))
                            .findFirst()
                            .orElse(null);
                    order.setProduct(product);
                    order.setSupplier(supplier);
                    order.setQuantity(Integer.parseInt(quantityText.getText()));
                    order.setOrderAmount(order.getQuantity()* Objects.requireNonNull(product).getBuyingPrice());
                    order.setOrderStatus("In process");
                    order.setOrderDate(LocalDate.parse(orderDateText.getText()));

                    //send to database
                    orderService.add(order);
                    //show success
                    JOptionPane.showMessageDialog(null,"Order submitted","Info",JOptionPane.INFORMATION_MESSAGE);

                    // update table
                    updateTable(orderService.getAll(), model);

                }catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        c.gridx =0;
        c.gridy =4;
        panel.add(addButton,c);


        updateTable(orders, model);
        //table model
        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setPreferredScrollableViewportSize(new Dimension(800, 250));
        table.setPreferredSize(new Dimension(800, 150));
        table.getColumnModel().getColumn(0).setPreferredWidth(25);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);

        c.insets = new Insets(50,10,10,5);  //top padding        //add the table to the frame
        c.gridx=0;
        c.gridy=0;
        JPanel tablePanel = new JPanel(new GridBagLayout());
        tablePanel.add(new JScrollPane(table),c);

        frame.setLayout(new GridBagLayout());
        c.insets = new Insets(10,10,10,5);  //top padding        //add the table to the frame
        c.gridx=0;
        c.gridy=0;
        frame.add(panel, c);
        c.insets = new Insets(20,10,10,5);  //top padding        //add the table to the frame
        c.gridx=0;
        c.gridy=1;
        frame.add(tablePanel, c);
//        frame.pack();
        frame.setVisible(true);
    }

    private void updateTable(List<Order> orders, DefaultTableModel model) {
        model.setRowCount(0);
        for (int i = 0; i < orders.size() ; i++) {
            model.addRow(new Object[]{
                    (i + 1),
                    orders.get(i).getProduct().getName(),
                    orders.get(i).getSupplier().getName(),
                    orders.get(i).getQuantity(),
                    orders.get(i).getOrderAmount(),
                    orders.get(i).getOrderDate(),
                    orders.get(i).getOrderStatus()
            });
        }
    }
}
