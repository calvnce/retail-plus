package inventory.data.supplier;

import inventory.data.Database;
import inventory.data.Repository;
import inventory.model.Product;
import inventory.model.Supplier;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepository  implements Repository<Supplier> {
    private final Database database;
    private PreparedStatement preparedStatement = null ;
    private ResultSet resultSet = null;
    private String query;

    public SupplierRepository() {
        this.database = new Database();
    }

    @Override
    public void add(Supplier supplier) {
        try {
            //Insert sql statement
            this.query = "INSERT INTO Supplier (SupplierName, PhoneNumber, Email, Address) VALUES (?,?,?,?)";
            //Get database connection
            this.preparedStatement=this.database.getConnection().prepareStatement(query);
            //Set the place-holder values
            this.preparedStatement.setString(1,supplier.getName());
            this.preparedStatement.setString(2,supplier.getPhone());
            this.preparedStatement.setString(3,supplier.getEmail());
            this.preparedStatement.setString(4, supplier.getAddress());

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
    public void edit(Supplier supplier) {
        try {
            //Insert sql statement
            this.query = "UPDATE Supplier SET SupplierName=?, PhoneNumber=?, Email=?, Address=? WHERE Id=?";
            //Get database connection
            this.preparedStatement=this.database.getConnection().prepareStatement(query);
            //Set the place-holder values
            this.preparedStatement.setString(1,supplier.getName());
            this.preparedStatement.setString(2,supplier.getPhone());
            this.preparedStatement.setString(3,supplier.getEmail());
            this.preparedStatement.setString(4, supplier.getAddress());
            this.preparedStatement.setLong(4, supplier.getId());
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
    public void delete(long id) {
        try {
            //Insert sql statement
            this.query = "DELETE FROM Supplier WHERE Id=?";
            //Get database connection
            this.preparedStatement=this.database.getConnection().prepareStatement(query);
            //Set the place-holder values
            this.preparedStatement.setLong(1, id);
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
    public Supplier getById(long id) {
        Supplier supplier = null;
        try {
            //Select sql statement
            this.query = "SELECT * FROM Supplier WHERE Id=?";
            //Get connection
            this.preparedStatement = this.database.getConnection().prepareStatement(this.query);

            //Fill the statement placeholder
            this.preparedStatement.setLong(1,id);
            // Get the resulting query response records
            this.resultSet = this.preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst())
                supplier =new Supplier(this.resultSet.getLong(1), this.resultSet.getString(2),this.resultSet.getString(3),
                        this.resultSet.getString(4), this.resultSet.getString(5));
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
        return  supplier;
    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> suppliers = new ArrayList<>();
        try {
            //Select sql statement
            this.query = "SELECT * FROM Supplier";
            //Get connection
            this.preparedStatement = this.database.getConnection().prepareStatement(this.query);

            // Get the resulting query response records
            this.resultSet = this.preparedStatement.executeQuery();

            // collect the results
            while (this.resultSet.next()) {
                suppliers.add(new Supplier(this.resultSet.getLong(1), this.resultSet.getString(2),this.resultSet.getString(3),
                        this.resultSet.getString(4), this.resultSet.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        return suppliers;
    }

    @Override
    public List<Supplier> filter(String name) {
        List<Supplier> suppliers = new ArrayList<>();
        try {
            //Select sql statement
            this.query = "SELECT * FROM Supplier WHERE SupplierName LIKE '%" + name + "%' ";
            //Get connection
            this.preparedStatement = this.database.getConnection().prepareStatement(this.query);

            // Get the resulting query response records
            this.resultSet = this.preparedStatement.executeQuery();

            // collect the results
            while (this.resultSet.next()) {
                suppliers.add(new Supplier(this.resultSet.getLong(1), this.resultSet.getString(2), this.resultSet.getString(3),
                        this.resultSet.getString(4), this.resultSet.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        return suppliers;
    }
}
