package inventory.model;

import java.util.Objects;

public class Employee  extends  Person{
    private  String firstName;
    private String lastName;
    private String Gender;
    private Role role;

    public Employee() {
    }

    public Employee(long id, String address, String phone, String email, String firstName, String lastName, String gender, Role role) {
        super(id, address, phone, email);
        this.firstName = firstName;
        this.lastName = lastName;
        Gender = gender;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName)
                && Objects.equals(lastName, employee.lastName)
                && Objects.equals(Gender, employee.Gender)
                && Objects.equals(role, employee.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, Gender, role);
    }
}