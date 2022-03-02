package inventory.gui;

import inventory.model.Product;
import inventory.model.Supplier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Dashboard extends Component {

    private JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();
        //Build second menu in the menu bar.
        menu = new JMenu("Supplier");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription(
                "This menu enables the users to manage suppliers");
        menuBar.add(menu);
        //Add new product menu
        menuItem = new JMenuItem( new AbstractAction("Add") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new CreateSupplier();
            }
        });
        menu.add(menuItem);

        //Add new product menu
        menuItem = new JMenuItem( new AbstractAction("List") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new SupplierList();
            }
        });
        menu.add(menuItem);
        //Build the Product menu.
        menu = new JMenu("Product");
        menu.setMnemonic(KeyEvent.VK_P);
        menu.getAccessibleContext().setAccessibleDescription(
                "Enables the users to manage product actions or activities");
        menuBar.add(menu);

        //Add new product menu
        menuItem = new JMenuItem( new AbstractAction("New") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new CreateProduct();
            }
        });
        menu.add(menuItem);


        submenu = new JMenu("Edit");
        submenu.setMnemonic(KeyEvent.VK_E);

        //Edit Product Menu
        menuItem = new JMenuItem("Edit",
                KeyEvent.VK_E);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_E,InputEvent.CTRL_DOWN_MASK));
        submenu.add(menuItem);

        //Remove Product Menu
        menuItem = new JMenuItem("Delete",
                KeyEvent.VK_D);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_D,InputEvent.CTRL_DOWN_MASK));
        submenu.add(menuItem);
        menu.add(submenu);

        //List Product Menu
        menuItem = new JMenuItem( new AbstractAction("List") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ProductList();
            }
        });
        menu.add(menuItem);

        //Build second menu in the menu bar.
        menu = new JMenu("Store");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription(
                "This menu enables the users to manage store products");
        menuBar.add(menu);

        //Build second menu in the menu bar.
        menu = new JMenu("Orders");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription(
                "This menu enables the users to manage orders");

        //Add new product menu
        menuItem = new JMenuItem( new AbstractAction("New") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new CreateOrder();
            }
        });
        menu.add(menuItem);

        menuBar.add(menu);


        return menuBar;
    }

    public void init() {
        //Create and set up the window.
        JFrame frame = new JFrame("Admin Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        //Display the window.
        frame.setSize(600, 300);
        frame.setVisible(true);
        frame.setResizable(false);

        // set the menu
        frame.setJMenuBar(this.createMenuBar());
    }
}
