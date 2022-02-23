package inventory.model;

import java.util.Objects;

public abstract class Person {
    private long Id;
    private String address;
    private String phone;
    private String email;

    public Person() {
    }

    public Person(long id, String address, String phone, String email) {
        Id = id;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Id == person.Id
                && Objects.equals(address, person.address)
                && Objects.equals(phone, person.phone)
                && Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id,  address, phone, email);
    }
}
