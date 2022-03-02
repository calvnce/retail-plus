package inventory.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Order {
    private long orderId;
    private Product product;
    private  Supplier supplier;
    private int quantity;
    private double orderAmount;
    private LocalDate orderDate;
    private String orderStatus;

    public Order() {
    }

    public Order(long orderId, Product product, Supplier supplier, int quantity,
                 double orderAmount, String orderStatus, LocalDate orderDate) {
        this.orderId = orderId;
        this.product = product;
        this.supplier = supplier;
        this.quantity = quantity;
        this.orderAmount = orderAmount;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId
                && quantity == order.quantity
                && Double.compare(order.orderAmount, orderAmount) == 0
                && Objects.equals(product, order.product)
                && Objects.equals(supplier, order.supplier)
                && Objects.equals(orderDate, order.orderDate)
                && Objects.equals(orderStatus, order.orderStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, product, supplier, quantity, orderAmount, orderDate, orderStatus);
    }
}
