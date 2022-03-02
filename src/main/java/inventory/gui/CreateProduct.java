package inventory.gui;

import inventory.model.Product;
import inventory.service.Service;
import inventory.service.product.ProductService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;

public class CreateProduct {
    public CreateProduct() {
        JFrame frame = new JFrame("New Product");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(600,300);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.WEST;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10,10,10,5);  //top padding
        //Labels
        JLabel nameLabel = new JLabel("Name");
        c.gridx =0;
        c.gridy =0;
        panel.add(nameLabel,c);
        JLabel expDateLabel = new JLabel("Manufacture Date");
        c.gridx =0;
        c.gridy =1;
        panel.add(expDateLabel,c);
        JLabel mfgDateLabel = new JLabel("Expiry Date");
        c.gridx =0;
        c.gridy =2;
        panel.add(mfgDateLabel,c);
        JLabel purchasePriceLabel = new JLabel("Purchase Price");
        c.gridx =0;
        c.gridy =3;
        panel.add(purchasePriceLabel,c);
        JLabel sellingPriceLabel = new JLabel("Selling Price");
        c.gridx =0;
        c.gridy =4;
        panel.add(sellingPriceLabel,c);
        JTextField nameText = new JTextField(40);
        c.gridx =1;
        c.gridy =0;
        panel.add(nameText,c);
        JFormattedTextField mfgDateText = new JFormattedTextField();
        mfgDateText.setValue(LocalDate.now());
        mfgDateText.setColumns(20);
        c.gridx =1;
        c.gridy =1;
        panel.add(mfgDateText,c);
        JFormattedTextField expireDateText = new JFormattedTextField();
        LocalDate exp = LocalDate.now(  ).plusMonths(24);
        expireDateText.setValue(exp);
        expireDateText.setColumns(20);
        c.gridx =1;
        c.gridy =2;
        panel.add(expireDateText,c);
        NumberFormat format = DecimalFormat.getInstance();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        format.setRoundingMode(RoundingMode.HALF_UP);
        JFormattedTextField purchasePriceText = new JFormattedTextField(format);
        purchasePriceText.setValue(0.0);
        purchasePriceText.setSize(50,25);
        purchasePriceText.setColumns(10);
        c.gridx =1;
        c.gridy =3;
        panel.add(purchasePriceText,c);
        JFormattedTextField sellingPriceText = new JFormattedTextField(format);
        sellingPriceText.setValue(0.0);
        sellingPriceText.setColumns(10);
        c.gridx =1;
        c.gridy =4;
        panel.add(sellingPriceText,c);


        JButton addButton = new JButton(new AbstractAction("Save") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Product product = new Product();
                Service <Product> service = new ProductService();
                try {
                    product.setName(nameText.getText());
                    product.setBuyingPrice(Double.parseDouble(purchasePriceText.getText()));
                    product.setSellingPrice(Double.parseDouble(sellingPriceText.getText()));
                    product.setMfgDate(LocalDate.parse(mfgDateText.getText()));
                    product.setExpiryDate(LocalDate.parse(expireDateText.getText()));

                    //send to database
                    service.add(product);
                    //show success
                    JOptionPane.showMessageDialog(null,"Record saved","Info",JOptionPane.INFORMATION_MESSAGE);
                }catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        c.gridx =0;
        c.gridy =6;
        panel.add(addButton,c);

        frame.add(panel,BorderLayout.WEST);
        frame.setVisible(true);
    }
}
