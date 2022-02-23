package inventory;

import inventory.data.Database;
import inventory.log.CustomLogManager;

/**
 * Inventory Management System Main method
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        CustomLogManager.Log("Bootstrapping the application");
        new Database().createTables();
    }
}
