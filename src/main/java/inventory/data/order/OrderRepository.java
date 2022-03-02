package inventory.data.order;

import inventory.data.Database;
import inventory.data.Repository;
import inventory.model.Order;
import inventory.model.Product;
import inventory.model.Supplier;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements Repository<Order> {
    private final Database database;
    private PreparedStatement preparedStatement = null ;
    private ResultSet resultSet = null;
    private String query;

    public OrderRepository() {
        this.database = new Database();
    }

    @Override
    public void add(Order order) {
        try {
            //Insert sql statement
            this.query = "INSERT INTO Orders (ProductId, SupplierId, Quantity, OrderAmount, OrderDate, OrderStatus) " +
                                 "VALUES (?,?,?,?,?,?)";
            //Get database connection
            this.preparedStatement=this.database.getConnection().prepareStatement(query);
            //Set the place-holder values
            this.preparedStatement.setLong(1,order.getProduct().getId());
            this.preparedStatement.setLong(2,order.getSupplier().getId());
            this.preparedStatement.setInt(3, order.getQuantity());
            this.preparedStatement.setDouble(4,order.getOrderAmount());
            this.preparedStatement.setDate(5, Date.valueOf(order.getOrderDate()));
            this.preparedStatement.setString(6, order.getOrderStatus());
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
    public void edit(Order order) {
        try {
            //Update sql statement
            this.query = "UPDATE Orders SET  OrderStatus=? WHERE Id=?";
            //Get database connection
            this.preparedStatement = this.database.getConnection().prepareStatement(query);
            //Set the place-holder values
            this.preparedStatement.setString(1,order.getOrderStatus());
            this.preparedStatement.setDouble(2,order.getOrderId());
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

    }

    @Override
    public Order getById(long id) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        try {
            //Select sql statement
            this.query = "SELECT P.Id, P.ProductName, S.SupplierName, O.Quantity, OrderAmount, OrderStatus, O.OrderDate " +
                                    "FROM Product P "+
                                    "INNER JOIN  Orders O ON P.Id = O.ProductId "+
                                    "INNER JOIN Supplier S on S.Id = O.SupplierId  ";
            //Get connection
            this.preparedStatement = this.database.getConnection().prepareStatement(this.query);

            // Get the resulting query response records
            this.resultSet = this.preparedStatement.executeQuery();

            // collect the results
            get(orders);
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
        return orders;
    }

    private void get(List<Order> orders) throws SQLException {
        while (this.resultSet.next()) {
            Product product = new Product();
            Supplier supplier = new Supplier();
            product.setName(this.resultSet.getString(2));
            supplier.setName(this.resultSet.getString(3));
            orders.add(new Order(this.resultSet.getLong(1),product,supplier,this.resultSet.getInt(4),
                    this.resultSet.getDouble(5),this.resultSet.getString(6), this.resultSet.getDate(7).toLocalDate()));
        }
    }

    @Override
    public List<Order> filter(String str) {
        List<Order> orders = new ArrayList<>();
        try {
            //Select sql statement
            this.query = "SELECT P.ProductName, S.SupplierName, O.Quantity, OrderAmount, O.OrderDate,OrderStatus " +
                    "FROM Product P " +
                    "INNER JOIN  Orders O ON P.Id = O.ProductId " +
                    "INNER JOIN Supplier S on S.Id = O.SupplierId " +
                    "WHERE OrderStatus=? ORDER BY OrderDate DESC ";
            //Get connection
            this.preparedStatement = this.database.getConnection().prepareStatement(this.query);
            //Set the place-holder values
            this.preparedStatement.setString(1,str);
            // Get the resulting query response records
            this.resultSet = this.preparedStatement.executeQuery();

            // collect the results
            get(orders);
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
        return orders;
    }
}
