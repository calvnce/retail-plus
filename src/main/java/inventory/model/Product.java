package inventory.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Product {
    private  long Id;
    private String name;
    private LocalDate mfgDate;
    private LocalDate expiryDate;
    private double buyingPrice;
    private double SellingPrice;

    public Product() {
    }

    public Product(long id, String name, double buyingPrice, double sellingPrice, LocalDate mfgDate, LocalDate expiryDate) {
        Id = id;
        this.name = name;
        this.mfgDate = mfgDate;
        this.expiryDate = expiryDate;
        this.buyingPrice = buyingPrice;
        SellingPrice = sellingPrice;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getMfgDate() {
        return mfgDate;
    }

    public void setMfgDate(LocalDate mfgDate) {
        this.mfgDate = mfgDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public double getSellingPrice() {
        return SellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        SellingPrice = sellingPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Id == product.Id
                && Double.compare(product.buyingPrice, buyingPrice) == 0
                && Double.compare(product.SellingPrice, SellingPrice) == 0
                && Objects.equals(name, product.name)
                && Objects.equals(mfgDate, product.mfgDate)
                && Objects.equals(expiryDate, product.expiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, name, mfgDate, expiryDate, buyingPrice, SellingPrice);
    }
}
