package inventory.gui;

import inventory.model.Supplier;
import inventory.service.Service;
import inventory.service.supplier.SupplierService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CreateSupplier {
    public  CreateSupplier() {
        JFrame frame = new JFrame("New Supplier");
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
        JLabel phoneNumberLabel = new JLabel("Phone number");
        c.gridx =0;
        c.gridy =1;
        panel.add(phoneNumberLabel,c);
        JLabel emailLabel = new JLabel("Email");
        c.gridx =0;
        c.gridy =2;
        panel.add(emailLabel,c);
        JLabel addressLabel = new JLabel("Address");
        c.gridx =0;
        c.gridy =3;
        panel.add(addressLabel,c);
        JTextField nameText = new JTextField(20);
        c.gridx =1;
        c.gridy =0;
        panel.add(nameText,c);
        JTextField phoneText = new JTextField(20);
        c.gridx =1;
        c.gridy =1;
        panel.add(phoneText,c);
        JTextField emailText = new JTextField(20);
        c.gridx =1;
        c.gridy =2;
        panel.add(emailText,c);

        JTextArea addressTextArea = new JTextArea(3,30);
        c.gridx =1;
        c.gridy =3;
        panel.add(addressTextArea,c);


        JButton addButton = new JButton(new AbstractAction("Save") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Supplier supplier = new Supplier();
                Service<Supplier> service = new SupplierService();
                try {
                    supplier.setName(nameText.getText());
                    supplier.setPhone(phoneText.getText());
                    supplier.setEmail(emailText.getText());
                    supplier.setAddress(addressTextArea.getText());

                    //send to database
                    service.add(supplier);
                    //show success
                    JOptionPane.showMessageDialog(null,"Record saved","Info",JOptionPane.INFORMATION_MESSAGE);
                }catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        c.gridx =0;
        c.gridy =4;
        panel.add(addButton,c);

        frame.add(panel,BorderLayout.WEST);
        frame.setVisible(true);
        }
    }
