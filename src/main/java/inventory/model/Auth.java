package inventory.model;

import java.util.Objects;

public class Auth {
    private Person person;
    private String username;
    private String password;

    public Auth() {
    }

    public Auth(Person person, String username, String password) {
        this.person = person;
        this.username = username;
        this.password = password;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auth auth = (Auth) o;
        return Objects.equals(person, auth.person)
                && Objects.equals(username, auth.username)
                && Objects.equals(password, auth.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, username, password);
    }
}