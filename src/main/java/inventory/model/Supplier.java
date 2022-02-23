package inventory.model;

import java.util.Objects;

public class Supplier extends Person{
    private  String Name;

    public Supplier() {
    }

    public Supplier(long id, String address, String phone, String email, String name) {
        super(id, address, phone, email);
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Supplier supplier = (Supplier) o;
        return Objects.equals(Name, supplier.Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Name);
    }
}
