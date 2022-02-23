package inventory.model;

import java.util.Objects;

public class Store {
    private long Id;
    private Product product;
    private int quantity;

    public Store() {
    }

    public Store(long id, Product product, int quantity) {
        this.Id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Id == store.Id && quantity == store.quantity && Objects.equals(product, store.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, product, quantity);
    }
}
