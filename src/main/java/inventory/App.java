package inventory;

import inventory.data.Database;
import inventory.gui.Dashboard;
import inventory.log.CustomLogManager;

import javax.swing.*;

/**
 * Inventory Management System Main method
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        CustomLogManager.Log("Bootstrapping the application");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Dashboard().init();
            }
        });
    }
}
