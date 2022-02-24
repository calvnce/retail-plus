package inventory.data.product;

import inventory.data.Database;
import inventory.data.Repository;
import inventory.model.Product;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements Repository<Product> {
    private final Database database;
    private PreparedStatement preparedStatement = null ;
    private ResultSet resultSet = null;
    private String query;
    public ProductRepository() {
        this.database = new Database();
    }

    @Override
    public void add(Product product) {
        try {
            //Insert sql statement
            this.query = "INSERT INTO Product (ProductName, PurchasePrice, SellingPrice, MfgDate, ExpiryDate) VALUES (?,?,?,?,?)";
            //Get database connection
            this.preparedStatement=this.database.getConnection().prepareStatement(query);
            //Set the place-holder values
            this.preparedStatement.setString(1,product.getName());
            this.preparedStatement.setDouble(2,product.getBuyingPrice());
            this.preparedStatement.setDouble(3,product.getSellingPrice());
            this.preparedStatement.setDate(4, Date.valueOf(product.getMfgDate()));
            this.preparedStatement.setDate(5, Date.valueOf(product.getExpiryDate()));

            //execute the sql
            this.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (this.preparedStatement != null) {
                try {
                    //close the prepared statement resource
                    this.preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (this.database.getConnection() != null) {
                try {
                    this.database.getConnection().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void edit(Product product) {
        try {
            //Update sql statement
            this.query = "UPDATE Product SET  ProductName=?, PurchasePrice=?, SellingPrice=?, MfgDate=?, ExpiryDate=? WHERE Id=?";
            //Get database connection
            this.preparedStatement = this.database.getConnection().prepareStatement(query);
            //Set the place-holder values
            this.preparedStatement.setString(1,product.getName());
            this.preparedStatement.setDouble(2,product.getBuyingPrice());
            this.preparedStatement.setDouble(3,product.getSellingPrice());
            this.preparedStatement.setDate(4, Date.valueOf(product.getMfgDate()));
            this.preparedStatement.setDate(5, Date.valueOf(product.getExpiryDate()));
            this.preparedStatement.setLong(6,product.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (this.preparedStatement != null) {
                try {
                    //close the prepared statement resource
                    this.preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (this.database.getConnection() != null) {
                try {
                    this.database.getConnection().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void delete(long id) {
        try {
            //delete sql statement
            this.query = "DELETE  FROM Product WHERE Id=?";
            //Get database connection
            this.preparedStatement = this.database.getConnection().prepareStatement(this.query);
            //Set the place-holder values
            this.preparedStatement.setLong(1, id);

            //execute the delete operation
            this.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (this.preparedStatement != null) {
                try {
                    //close the prepared statement resource
                    this.preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (this.database.getConnection() != null) {
                try {
                    this.database.getConnection().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Product getById(long id) {
        Product product = null;
        try {
            //Select sql statement
            this.query = "SELECT * FROM Product WHERE Id=?";
            //Get connection
            this.preparedStatement = this.database.getConnection().prepareStatement(this.query);

            //Fill the statement placeholder
            this.preparedStatement.setLong(1,id);
            // Get the resulting query response records
            this.resultSet = this.preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst())
                product =new Product(this.resultSet.getLong(1), this.resultSet.getString(2),
                        this.resultSet.getDouble(3), this.resultSet.getDouble(4), this.resultSet.getDate(5).toLocalDate(),
                        this.resultSet.getDate(6).toLocalDate());
            //Get the result

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (this.preparedStatement != null) {
                try {
                    //close the prepared statement resource
                    this.preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (this.database.getConnection() != null) {
                try {
                    this.database.getConnection().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return  product;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try {
            //Select sql statement
            this.query = "SELECT * FROM Product";
            //Get connection
            this.preparedStatement = this.database.getConnection().prepareStatement(this.query);

            // Get the resulting query response records
            this.resultSet = this.preparedStatement.executeQuery();

            // collect the results
            while (this.resultSet.next()) {
                products.add(new Product(this.resultSet.getLong(1), this.resultSet.getString(2),
                                        this.resultSet.getDouble(3), this.resultSet.getDouble(4), this.resultSet.getDate(5).toLocalDate(),
                                        this.resultSet.getDate(6).toLocalDate()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (this.preparedStatement != null) {
                try {
                    //close the prepared statement resource
                    this.preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (this.database.getConnection() != null) {
                try {
                    this.database.getConnection().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return products;
    }
}
