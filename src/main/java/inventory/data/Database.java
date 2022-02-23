package inventory.data;

import inventory.log.CustomLogManager;

import javax.lang.model.element.NestingKind;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    private Connection connection = null;

    /**
     * Create a connection for database actions
     * @return connection
     */
    private  Connection getConnection() {
        try {
            // db parameters
            String url = "jdbc:sqlite:inventory.sqlite";
            // create a connection to the database
            this.connection = DriverManager.getConnection(url);

            CustomLogManager.Log("Getting Database Connection ..");

        } catch (SQLException e) {
            CustomLogManager.LogError(e.getMessage());
        }
        return  this.connection;
    }

    public  void  createTables() {
        try {
             this.connection = this.getConnection();
            PreparedStatement preparedStatement = null;

            String roleTable = "CREATE TABLE IF NOT EXISTS Role (" +
                    "Id INTEGER  PRIMARY KEY AUTOINCREMENT," +
                    "Role TEXT NOT NULL); ";

            String empTable = "CREATE TABLE IF NOT EXISTS Employee (" +
                    "Id INTEGER  PRIMARY KEY AUTOINCREMENT," +
                    "FirstName TEXT NOT NULL," +
                    "LastName TEXT NOT NULL," +
                    "Gender TEXT NOT NULL," +
                    "RoleId INT NOT NULL," +
                    "PhoneNumber TEXT NULL," +
                    "Email TEXT NOT NULL," +
                    "Address TEXT NOT NULL, " +
                    "City TEXT NOT NULL," +
                    "ZipCode INT," +
                    "State TEXT NOT NULL," +
                    "FOREIGN KEY (RoleId) REFERENCES Role (Id)); ";


            String supplierTable = "CREATE TABLE IF NOT EXISTS Supplier (" +
                    "Id INTEGER  PRIMARY KEY AUTOINCREMENT," +
                    "SupplierName TEXT NOT NULL," +
                    "PhoneNumber TEXT NOT NULL," +
                    "Email TEXT NOT NULL," +
                    "Address TEXT NOT NULL); ";

            String productTable="CREATE TABLE IF NOT EXISTS Product (" +
                    "Id INTEGER  PRIMARY KEY AUTOINCREMENT," +
                    "ProductName TEXT NOT NULL," +
                    "PurchasePrice DOUBLE NOT NULL," +
                    "SellingPrice DOUBLE NOT NULL," +
                    "MfgDate DATE NOT NULL," +
                    "ExpiryDate DATE NOT NULL); ";

            String orderTable = "CREATE TABLE IF NOT EXISTS Orders (" +
                    "Id INTEGER  PRIMARY KEY AUTOINCREMENT," +
                    "ProductId INT NOT NULL," +
                    "SupplierId INT NOT NULL," +
                    "Quantity INT NOT NULL," +
                    "OrderAmount DOUBLE NOT NULL," +
                    "OrderStatus TEXT NOT NULL," +
                    "OrderDate DATE NOT NULL," +
                    "FOREIGN KEY (ProductId) REFERENCES Product(Id)," +
                    "FOREIGN KEY (SupplierId) REFERENCES Supplier(Id)); ";

            String supplierProductTable = "CREATE TABLE IF NOT EXISTS  SupplierProduct (" +
                    "Id INTEGER  PRIMARY KEY AUTOINCREMENT," +
                    "ProductId INT NOT NULL," +
                    "SupplierId INT NOT NULL," +
                    "FOREIGN KEY (ProductId) REFERENCES Product(Id)," +
                    "FOREIGN KEY (SupplierId) REFERENCES Supplier(Id)); ";

            String storeTable = "CREATE TABLE IF NOT EXISTS Store (" +
                    "Id INTEGER  PRIMARY KEY AUTOINCREMENT," +
                    "ProductId INT NOT NULL," +
                    "QuantityAvailable INT NOT NULL," +
                    "FOREIGN KEY (ProductId) REFERENCES Product(Id)); ";

            String loginTable = "CREATE TABLE  IF NOT EXISTS   LogIn (" +
                    "Id INTEGER  PRIMARY KEY AUTOINCREMENT," +
                    "EmployeeId INT NOT NULL," +
                    "Username TEXT NOT NULL," +
                    "Password TEXT NOT NULL," +
                    "FOREIGN KEY (EmployeeId) REFERENCES Employee(Id)); ";

            String[] queries = {roleTable,empTable,supplierTable,productTable,orderTable,supplierProductTable,storeTable,loginTable};

            this.connection.setAutoCommit(false);
            int i=1;
            for (String query:queries) {
                preparedStatement = this.connection.prepareStatement(query);
                preparedStatement.execute();
                CustomLogManager.Log("Executing DDL:  "+ query );
            }
            //execute
            preparedStatement.executeBatch();

            this.connection.commit();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            CustomLogManager.LogError(e.getMessage());
        }
        finally {
            if (this.connection!=null){
                try {
                    this.connection.close();
                } catch (SQLException e) {
                    CustomLogManager.LogError(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}
