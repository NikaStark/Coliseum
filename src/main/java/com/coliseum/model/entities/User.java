package com.coliseum.model.entities;

import com.coliseum.model.entities.enums.Role;
import com.fasterxml.uuid.Generators;

import java.util.UUID;

public class User implements Identified<UUID> {

    public static final String TABLE_NAME = "users";
    public static final String ID_COLUMN = "id";
    public static final String USERNAME_COLUMN = "username";
    public static final String PASSWORD_COLUMN = "password";
    public static final String EMAIL_COLUMN = "email";
    public static final String FIRST_NAME_COLUMN = "first_name";
    public static final String LAST_NAME_COLUMN = "last_name";
    public static final String ROLE_COLUMN = "role";
    public static final String COUNT = "count";

    public static final User GUEST_USER = new User(null, null, null, null, null, Role.GUEST);

    private UUID id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;

    public User() {
    }

    public User(String username, String password, String email, String firstName, String lastName, Role role) {
        this.id = Generators.timeBasedGenerator().generate();
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (!username.equals(user.username)) return false;
        if (!password.equals(user.password)) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (!firstName.equals(user.firstName)) return false;
        if (!lastName.equals(user.lastName)) return false;
        return role == user.role;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                '}';
    }

}
