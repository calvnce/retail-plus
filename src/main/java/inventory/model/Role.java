package inventory.model;

import java.util.Objects;

public class Role {
    private long Id;
    private String role;

    public Role() {
    }

    public Role(long id, String role) {
        Id = id;
        this.role = role;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return Id == role1.Id && Objects.equals(role, role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, role);
    }
}
